package org.example.singleton.hungry;

/**
 * @author zhuohb
 * @date 2024/2/26 18:01
 */
public class HungrySingleton {
	//先静态,后动态
	//先属性,后方法
	//先上后下
	private static final HungrySingleton hungrySingleton = new HungrySingleton();

	private HungrySingleton() {
	}

	public static HungrySingleton getInstance() {
		return hungrySingleton;
	}
}
