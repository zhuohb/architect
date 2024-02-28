package org.example.singleton.lazy;

/**
 * @author zhuohb
 * @date 2024/2/27 16:07
 */
public class ExecutorThread implements Runnable {
	@Override
	public void run() {
		LazySimpleSingleton lazySimpleSingleton = LazySimpleSingleton.getInstance();
		System.out.println(Thread.currentThread().getName() + ":" + lazySimpleSingleton);
	}
}
