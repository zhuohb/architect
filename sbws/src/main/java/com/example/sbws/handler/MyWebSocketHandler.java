package com.example.sbws.handler;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
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
		// 从请求参数中获取 Token
		String token = session.getHandshakeHeaders().getFirst("Authorization");
		if (token == null || !validateToken(token)) {
			try {
				session.close(CloseStatus.NOT_ACCEPTABLE.withReason("未授权"));
			} catch (IOException e) {
				System.err.println("关闭连接时出错: " + e.getMessage());
			}
			return;
		}
		// 验证通过，记录用户会话
		String userId = extractUserIdFromToken(token);
		onlineSessions.put(session.getId(), session);
		userSessionMap.put(userId, session.getId());
		sessionUserMap.put(session.getId(), userId);

		System.out.println("用户 " + userId + " 已连接");
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String payload = message.getPayload();
		if (payload.startsWith("LOGIN:")) {
			handleLogin(session, payload);
		} else {
			// 检查用户是否已登录
			String userId = sessionUserMap.get(session.getId());
			if (userId == null) {
				session.sendMessage(new TextMessage("请先登录"));
				return;
			}

			// 处理其他消息
			handleMessage(session, payload);
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
		try {
			String userId = payload.split(":")[1].trim();
			if (validateUser(userId)) {
				userSessionMap.put(userId, session.getId());
				sessionUserMap.put(session.getId(), userId);
				session.sendMessage(new TextMessage("登录成功: " + userId));
			} else {
				session.sendMessage(new TextMessage("登录失败: 用户不存在"));
			}
		} catch (IOException e) {
			e.printStackTrace();
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

	private boolean validateToken(String token) {
		// todo 实现 Token 验证逻辑
		return true;
	}

	private String extractUserIdFromToken(String token) {
		// todo 从 Token 中提取用户 ID
		return "user123";
	}

	private boolean validateUser(String userId) {
		// todo 实现用户验证逻辑
		return true;
	}
}
