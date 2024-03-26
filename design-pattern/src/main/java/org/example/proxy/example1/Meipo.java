package org.example.proxy.example1;

import java.lang.reflect.Method;

/**
 * @author zhuohb
 * @date 2024/3/26 18:07
 */
public class Meipo implements ZInvocationHandler {
	private IPerson target;
	public IPerson getInstance(IPerson target){
		this.target = target;
		Class<?> clazz =  target.getClass();
		return (IPerson) ZProxy.newProxyInstance(new ZClassLoader(),clazz.getInterfaces(),this);
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		before();
		Object result = method.invoke(this.target,args);
		after();
		return result;
	}

	private void after() {
		System.out.println("双方同意，开始交往");
	}

	private void before() {
		System.out.println("我是媒婆，已经收集到你的需求，开始物色");
	}
}
