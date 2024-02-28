package org.example.singleton.lazy;

/**
 * @author zhuohb
 * @date 2024/2/27 16:05
 */
//懒汉式单例模式在外部需要使用的时候才进行实例化
public class LazySimpleSingleton {
	private static LazySimpleSingleton lazySimpleSingleton = null;

	private LazySimpleSingleton() {
	}

	public  static LazySimpleSingleton getInstance() {
		if (lazySimpleSingleton == null) {
			synchronized (LazySimpleSingleton.class) {
				if (lazySimpleSingleton == null) {
					lazySimpleSingleton = new LazySimpleSingleton();
				}
			}
		}
		return lazySimpleSingleton;
	}
}
