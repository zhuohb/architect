package org.example.singleton.hungry;

/**
 * @author zhuohb
 * @date 2024/2/27 16:03
 */
public class HungryStaticSingleton {
	private static final HungryStaticSingleton hungryStaticSingleton;

	static {
		hungryStaticSingleton = new HungryStaticSingleton();
	}

	private HungryStaticSingleton() {
	}

	public static HungryStaticSingleton getInstance() {
		return hungryStaticSingleton;
	}
}
