package com.example.sbws.handler;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * 自定义握手拦截器
 *
 */
@Component
public class CustomHandshakeInterceptor implements HandshakeInterceptor {

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
		// 从请求头中获取 Token
		String token = request.getHeaders().getFirst("Authorization");
		if (token == null || !validateToken(token)) {
			// 验证失败，拒绝连接
			return false;
		}

		// 从 Token 中提取用户信息
		String userId = extractUserIdFromToken(token);
		// 将用户 ID 传给 WebSocketHandler
		attributes.put("userId", userId);

		// 验证成功，允许连接
		return true;
	}

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
		// 可以在这里处理握手后的逻辑
	}

	/**
	 * Token 验证逻辑
	 *
	 * @param token
	 * @return
	 */
	private boolean validateToken(String token) {
		// 实现验证逻辑
		return true;
	}

	/**
	 * 从 Token 中提取用户 ID
	 *
	 * @param token
	 * @return
	 */
	private String extractUserIdFromToken(String token) {
		// 实现提取逻辑
		return "user123";
	}
}
