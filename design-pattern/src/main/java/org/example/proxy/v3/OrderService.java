package org.example.proxy.v3;

/**
 * @author zhuohb
 * @date 2024/3/7 15:37
 */
public class OrderService implements IOrderService {
	private OrderDao orderDao;

	public OrderService() {
		this.orderDao = new OrderDao();
	}

	@Override
	public int createOrder(Order order) {
		System.out.println("OrderService调用OrderDao创建Order");
		return orderDao.insert(order);
	}
}
