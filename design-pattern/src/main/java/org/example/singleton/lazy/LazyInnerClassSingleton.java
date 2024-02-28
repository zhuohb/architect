package org.example.singleton.lazy;

/**
 * @author zhuohb
 * @date 2024/2/27 17:28
 */
public class LazyInnerClassSingleton {
	private LazyInnerClassSingleton() {
		if (LazyHolder.LAZY != null) {
			throw new RuntimeException("不允许创建多个实例");
		}
	}
	public static final LazyInnerClassSingleton getInstance() {
		return LazyHolder.LAZY;
	}

	/**
	 * 使用时会先初始化内部类
	 * 内部类没使用的是偶,默认不加载
	 */
	private static class LazyHolder {
		private static final LazyInnerClassSingleton LAZY = new LazyInnerClassSingleton();
	}
}
