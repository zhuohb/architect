package org.example.proxy.v3;

/**
 * @author zhuohb
 * @date 2024/3/7 15:47
 */
public class Test {
	public static void main(String[] args) {
		Order order = new Order();
		order.setCreateTime(System.currentTimeMillis());

		IOrderService instance = (IOrderService)new OrderServiceDynamicProxy().getInstance(new OrderService());
		instance.createOrder(order);
	}
}
