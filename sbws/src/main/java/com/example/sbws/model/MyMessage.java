package com.example.sbws.model;

import lombok.Data;

@Data
public class MyMessage {
	private String content;

	public MyMessage() {
	}

	public MyMessage(String content) {
		this.content = content;
	}
}
