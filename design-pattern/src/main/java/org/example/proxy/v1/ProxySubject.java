package org.example.proxy.v1;

/**
 * @author zhuohb
 * @date 2024/3/7 15:16
 */
public class ProxySubject implements ISubject {
	private ISubject subject;

	public ProxySubject(ISubject subject) {
		this.subject = subject;
	}

	@Override
	public void request() {
		beforeRequest();
		subject.request();
		afterRequest();
	}

	public void beforeRequest() {
		System.out.println("前置处理");
	}

	public void afterRequest() {
		System.out.println("后置处理");
	}

}
