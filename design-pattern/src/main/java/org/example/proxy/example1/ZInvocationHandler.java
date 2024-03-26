package org.example.proxy.example1;

import java.lang.reflect.Method;

/**
 * @author zhuohb
 * @date 2024/3/26 17:20
 */
public interface ZInvocationHandler {
	Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}
