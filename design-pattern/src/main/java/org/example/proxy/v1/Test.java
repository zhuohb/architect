package org.example.proxy.v1;

/**
 * @author zhuohb
 * @date 2024/3/7 15:18
 */
public class Test {
	public static void main(String[] args) {
		ProxySubject prosy = new ProxySubject(new RealSubject());
		prosy.request();

	}
}
