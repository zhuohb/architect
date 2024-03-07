package org.example.proxy.v3;

import lombok.Data;

/**
 * @author zhuohb
 * @date 2024/3/7 15:36
 */
@Data
public class Order {
	private Object orderInfo;
	private Long createTime;
	private String id;
}
