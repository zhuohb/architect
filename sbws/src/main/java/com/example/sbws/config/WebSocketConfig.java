package com.example.sbws.config;

import com.example.sbws.handler.CustomHandshakeInterceptor;
import com.example.sbws.handler.ChatWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * websocket的配置,固定写法
 * 返回的是注册表,用于配置websocket的各种设置,比如注册处理器
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		// 注册处理器
		registry.addHandler(new ChatWebSocketHandler(), "/chat")
			// 注册拦截器
			.addInterceptors(new CustomHandshakeInterceptor())
			// 跨域设置
			.setAllowedOrigins("*");
	}
}
