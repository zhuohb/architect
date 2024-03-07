package org.example.proxy.v3;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhuohb
 * @date 2024/3/7 15:42
 */
public class OrderServiceStaticProxy implements IOrderService {
	private SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
	private OrderService orderService;
	public OrderServiceStaticProxy(OrderService orderService) {
		this.orderService = orderService;
	}

	@Override
	public int createOrder(Order order) {
		before();
		Long time = order.getCreateTime();
		Integer dbRouter = Integer.valueOf(yearFormat.format(time));
		System.out.println("静态代理类自动分配到【DB_" + dbRouter + "】数据源处理数据");
		DynamicDataSourceEntry.set(dbRouter);
		orderService.createOrder(order);
		after();
		return 0;
	}
	private void before() {
		System.out.println("代理方法之前");
	}
	private  void after() {
		System.out.println("代理方法之后");
	}
}
