package com.example.sbws.handler;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MyWebSocketHandler extends TextWebSocketHandler {
	/**
	 * 存放所有在线的客户端会话
	 * key是websocket的sessionId
	 */
	private static final ConcurrentMap<String, WebSocketSession> onlineSessions = new ConcurrentHashMap<>();

	/**
	 * 存放用户ID与WebSocketSession的映射关系
	 */
	private static final ConcurrentMap<String, String> userSessionMap = new ConcurrentHashMap<>();

	/**
	 * 存放WebSocketSession与用户ID的映射关系
	 */
	private static final ConcurrentMap<String, String> sessionUserMap = new ConcurrentHashMap<>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) {
		System.out.println("已建立连接: " + session.getId());
		// 将会话添加到在线集合中
		onlineSessions.put(session.getId(), session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println("收到消息: " + message.getPayload());

		// 假设消息格式为 JSON，例如: {"type":"login","userId":"user123"}
		// 或者 {"type":"message","to":"user456","content":"Hello"}
		// 解析消息类型并处理

		// 示例：简单地根据消息内容判断消息类型
		String payload = message.getPayload();
		if (payload.startsWith("LOGIN:")) {
			handleLogin(session, payload);
		} else if (payload.startsWith("MESSAGE:")) {
			handleMessage(session, payload);
		} else {
			session.sendMessage(new TextMessage("未知消息类型"));
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
		System.out.println("连接已关闭: " + session.getId());

		// 从在线集合中移除会话
		onlineSessions.remove(session.getId());

		// 获取用户ID并从用户映射中移除
		String userId = sessionUserMap.get(session.getId());
		if (userId != null) {
			userSessionMap.remove(userId);
			sessionUserMap.remove(session.getId());
			System.out.println("用户退出: " + userId);
		}
	}

	/**
	 * 处理登录消息
	 *
	 * @param session 当前会话
	 * @param payload 消息内容，例如 "LOGIN:user123"
	 */
	private void handleLogin(WebSocketSession session, String payload) {
		String userId = payload.split(":")[1].trim();
		System.out.println("用户登录: " + userId);

		// 将用户ID与当前会话关联
		userSessionMap.put(userId, session.getId());
		sessionUserMap.put(session.getId(), userId);

		// 向当前用户发送登录成功的消息
		try {
			session.sendMessage(new TextMessage("登录成功: " + userId));
		} catch (Exception e) {
			System.err.println("发送登录成功消息时出错: " + e.getMessage());
		}
	}

	/**
	 * 处理消息
	 *
	 * @param session 当前会话
	 * @param payload 消息内容，例如 "MESSAGE:user456:Hello"
	 */
	private void handleMessage(WebSocketSession session, String payload) {
		String[] parts = payload.split(":");
		if (parts.length < 3) {
			System.err.println("消息格式错误");
			return;
		}

		String toUser = parts[1].trim();
		String content = payload.substring(payload.indexOf(parts[1]) + parts[1].length() + 1);

		String fromUser = sessionUserMap.get(session.getId());
		if (fromUser == null) {
			System.err.println("未登录用户尝试发送消息");
			return;
		}

		System.out.println(fromUser + " 发送给 " + toUser + ": " + content);

		// 查找目标用户会话
		String toSessionId = userSessionMap.get(toUser);
		if (toSessionId != null) {
			WebSocketSession toSession = onlineSessions.get(toSessionId);
			if (toSession != null && toSession.isOpen()) {
				try {
					toSession.sendMessage(new TextMessage(fromUser + " 发送: " + content));
				} catch (Exception e) {
					System.err.println("发送消息时出错: " + e.getMessage());
				}
			} else {
				System.err.println("目标用户不在线");
			}
		} else {
			System.err.println("目标用户不存在");
		}
	}

	/**
	 * 广播消息给所有在线客户端
	 *
	 * @param message        要广播的消息
	 * @param excludeSession 不广播到的会话
	 */
	private void broadcastMessage(String message, WebSocketSession excludeSession) {
		for (WebSocketSession session : onlineSessions.values()) {
			try {
				// 如果会话是打开的，并且不是发送消息的客户端，则发送消息
				if (session.isOpen() && !session.getId().equals(excludeSession.getId())) {
					session.sendMessage(new TextMessage(message));
				}
			} catch (Exception e) {
				System.err.println("广播消息时出错: " + e.getMessage());
			}
		}
	}
}
