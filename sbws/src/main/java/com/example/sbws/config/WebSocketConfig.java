package com.example.sbws.config;

import com.example.sbws.handler.CustomHandshakeInterceptor;
import com.example.sbws.handler.ChatWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(new ChatWebSocketHandler(), "/chat")
			.addInterceptors(new CustomHandshakeInterceptor())
			.setAllowedOrigins("*");
	}
}
