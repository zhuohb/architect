package com.example.sbws.handler;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 还可以扩展的方向:
 * 1.未接收的私聊消息:未上线用户的消息.类似微信上线可以接收到一定时间内的消息
 * 2.群聊
 */
public class ChatWebSocketHandler extends TextWebSocketHandler {

	/**
	 * 速率限制器,令牌桶算法
	 * 防止服务器资源被过度消耗
	 * 仅开发时候用,RateLimiter为beta
	 * 生产场景应有更成熟的方案
	 */
	private static final RateLimiter RATE_LIMITER = RateLimiter.create(100);

	/**
	 * 在线用户
	 * key为sessionId value为session
	 */
	private static final ConcurrentMap<String, WebSocketSession> ONLINE_SESSION_MAP = new ConcurrentHashMap<>();
	/**
	 * 用户id和sessionId的映射
	 */
	private static final ConcurrentMap<String, String> USER_SESSION_MAP = new ConcurrentHashMap<>();
	/**
	 * sessionId和用户id的映射
	 */
	private static final ConcurrentMap<String, String> SESSION_USER_MAP = new ConcurrentHashMap<>();

	/**
	 * 建立连接的回调
	 *
	 * @param session
	 * @throws IOException
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws IOException {
		// 在连接建立之后尝试获取令牌
		if (!RATE_LIMITER.tryAcquire()) {
			session.close(CloseStatus.POLICY_VIOLATION);
			return;
		}

		// 从 session 中获取用户 ID
		String userId = (String) session.getAttributes().get("userId");
		ONLINE_SESSION_MAP.put(session.getId(), session);
		USER_SESSION_MAP.put(userId, session.getId());
		SESSION_USER_MAP.put(session.getId(), userId);

		System.out.println("用户 " + userId + " 已连接");
	}

	/**
	 * 用于处理从客户端发送的文本消息, 它是 WebSocket 通信的核心部分,允许服务器接收到客户端的消息后执行自定义的业务逻辑
	 *
	 * @param session
	 * @param message
	 * @throws Exception
	 */
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String payload = message.getPayload();
		// 验证用户
		String userId = SESSION_USER_MAP.get(session.getId());
		if (userId == null) {
			session.sendMessage(new TextMessage("请先认证"));
			return;
		}
		// 业务逻辑
		if (payload.startsWith("BROADCAST:")) {
			handleBroadcastMessage(session, payload);
		} else if (payload.startsWith("PRIVATE:")) {
			handlePrivateMessage(session, payload);
		} else {
			// 等待扩展
			session.sendMessage(new TextMessage("用户 " + userId + " 发送消息: " + payload));
		}
	}

	/**
	 * 关闭连接的回调
	 *
	 * @param session
	 * @param status
	 */
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
		ONLINE_SESSION_MAP.remove(session.getId());
		String userId = SESSION_USER_MAP.remove(session.getId());
		if (userId != null) {
			USER_SESSION_MAP.remove(userId);
			System.out.println("用户退出: " + userId);
		}
	}

	/**
	 * 广播消息
	 *
	 * @param session
	 * @param payload
	 * @throws Exception
	 */
	private void handleBroadcastMessage(WebSocketSession session, String payload) throws Exception {
		String userId = SESSION_USER_MAP.get(session.getId());

		String message = payload.substring("BROADCAST:".length()).trim();

		// 向除自身外的用户广播消息
		for (Map.Entry<String, WebSocketSession> entry : ONLINE_SESSION_MAP.entrySet()) {
			String sessionId = entry.getKey();
			WebSocketSession onlineSession = entry.getValue();
			if (!session.getId().equals(sessionId) && onlineSession.isOpen()) {
				onlineSession.sendMessage(new TextMessage("用户 " + userId + " 广播: " + message));
			}
		}
	}

	/**
	 * 私聊消息
	 *
	 * @param session
	 * @param payload
	 * @throws Exception
	 */
	private void handlePrivateMessage(WebSocketSession session, String payload) throws Exception {
		String userId = SESSION_USER_MAP.get(session.getId());
		// 验证消息格式
		String[] parts = payload.split(":");
		if (parts.length < 3) {
			session.sendMessage(new TextMessage("无效的私聊消息格式"));
			return;
		}
		// 拿到私聊的用户
		String receiverUserId = parts[1];
		String messageContent = payload.substring("PRIVATE:".length() + receiverUserId.length() + 1);
		String receiverSessionId = USER_SESSION_MAP.get(receiverUserId);
		if (receiverSessionId == null) {
			session.sendMessage(new TextMessage("用户 " + receiverUserId + " 不在线"));
			return;
		}
		WebSocketSession receiverSession = ONLINE_SESSION_MAP.get(receiverSessionId);
		if (receiverSession == null || !receiverSession.isOpen()) {
			session.sendMessage(new TextMessage("用户 " + receiverUserId + " 不在线"));
			return;
		}

		receiverSession.sendMessage(new TextMessage("用户 " + userId + " 私聊消息: " + messageContent));
		session.sendMessage(new TextMessage("你的私聊消息已发送给 " + receiverUserId + ": " + messageContent));
	}
}
