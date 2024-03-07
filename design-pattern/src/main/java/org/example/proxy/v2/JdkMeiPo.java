package org.example.proxy.v2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zhuohb
 * @date 2024/3/7 15:27
 */
public class JdkMeiPo implements InvocationHandler {
	private IPerson target;

	public IPerson getInstance(IPerson target) {
		this.target = target;
		Class<?> clazz = target.getClass();
		return (IPerson) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
		before();
		Object invoke = method.invoke(this.target, args);
		after();
		return invoke;
	}

	private void after() {
		System.out.println("媒婆：收尾款");
	}
	private  void  before() {
		System.out.println("媒婆：开始物色");
	}
}
