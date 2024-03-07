package org.example.proxy.v3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhuohb
 * @date 2024/3/7 16:16
 */
public class OrderServiceDynamicProxy implements InvocationHandler {
	private SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
	private Object target;

	public Object getInstance(Object target) {
		this.target = target;
		Class<?> clazz = target.getClass();
		return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		before(args[0]);
		Object invoke = method.invoke(target, args);
		after();
		return invoke;

	}

	private void before(Object target) {
		try {
			System.out.println("代理方法之前");
			Long time = (Long) target.getClass().getMethod("getCreateTime").invoke(target);
			Integer dbRouter = Integer.valueOf(yearFormat.format(new Date(time)));
			System.out.println("动态代理类自动分配到【DB_" + dbRouter + "】数据源处理数据");
			DynamicDataSourceEntry.set(dbRouter);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void after() {
		System.out.println("代理方法之后");
	}
}
