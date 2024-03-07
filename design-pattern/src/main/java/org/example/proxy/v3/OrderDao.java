package org.example.proxy.v3;

/**
 * @author zhuohb
 * @date 2024/3/7 15:37
 */
public class OrderDao {
	public int insert(Order order) {
		System.out.println("OrderDao创建Order成功");
		return 1;
	}
}
