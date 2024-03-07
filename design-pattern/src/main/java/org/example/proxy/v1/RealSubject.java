package org.example.proxy.v1;

/**
 * @author zhuohb
 * @date 2024/3/7 15:15
 */
public class RealSubject implements ISubject{
	@Override
	public void request() {
		System.out.println("调用服务");
	}
}
