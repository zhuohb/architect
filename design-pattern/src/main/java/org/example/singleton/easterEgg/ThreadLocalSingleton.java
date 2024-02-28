package org.example.singleton.easterEgg;

/**
 * @author zhuohb
 * @date 2024/2/28 17:36
 */
public class ThreadLocalSingleton {
	private static final ThreadLocal<ThreadLocalSingleton> threadLocalInstance = ThreadLocal.withInitial(ThreadLocalSingleton::new);


	private ThreadLocalSingleton() {
	}

	public static ThreadLocalSingleton getInstance() {
		return threadLocalInstance.get();
	}
}
