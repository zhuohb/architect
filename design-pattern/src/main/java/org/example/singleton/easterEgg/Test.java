package org.example.singleton.easterEgg;

import org.example.singleton.lazy.ExecutorThread;

/**
 * @author zhuohb
 * @date 2024/2/28 17:38
 */
public class Test {
	public static void main(String[] args) {
		System.out.println(ThreadLocalSingleton.getInstance());
		System.out.println(ThreadLocalSingleton.getInstance());
		System.out.println(ThreadLocalSingleton.getInstance());
		System.out.println(ThreadLocalSingleton.getInstance());
		System.out.println(ThreadLocalSingleton.getInstance());

		Thread t1 = new Thread(new ExecutorThread());
		Thread t2 = new Thread(new ExecutorThread());
		t1.start();
		t2.start();
		System.out.println("end");
	}
}
