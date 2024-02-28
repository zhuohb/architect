package org.example.singleton.destroy;

import java.io.Serializable;

/**
 * @author zhuohb
 * @date 2024/2/27 17:39
 * 序列化破坏单例
 */
public class SerializableSingleton implements Serializable {
	private static final SerializableSingleton INSTANCE = new SerializableSingleton();

	private SerializableSingleton() {
	}

	public static SerializableSingleton getInstance() {
		return INSTANCE;
	}

	/**
	 * 重写readResolve方法，只不过是覆盖了反序列化出来的对象
	 * 还是创建了两次，发生在JVM层面，相对来说比较安全
	 * 之前反序列化出来的对象会被GC回收
	 *
	 * @return
	 */
	private Object readResolve() {
		return INSTANCE;
	}
}
