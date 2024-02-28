package org.example.singleton.lazy;

import java.lang.reflect.Constructor;

/**
 * @author zhuohb
 * @date 2024/2/27 17:10
 */
public class Test {
	public static void main(String[] args) {
        try {
			Class<?> clazz = LazyInnerClassSingleton.class;
			//通过反射获取私有的构造方法
            Constructor<?> constructor = clazz.getDeclaredConstructor(null);
			//强制访问
			constructor.setAccessible(true);
			//暴力初始化
			Object o1 = constructor.newInstance();
			//调用了两次构造方法，相当于new了两次
			Object o2 = constructor.newInstance();
			System.out.println(o1 == o2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
