package org.example.singleton.enums;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;

/**
 * @author zhuohb
 * @date 2024/2/27 17:53
 */
public class Test {
	public static void main(String[] args) {
		try {
			Class<EnumSingleton> clazz = EnumSingleton.class;
			Constructor<EnumSingleton> c = clazz.getDeclaredConstructor(String.class,int.class);
			c.setAccessible(true);
			c.newInstance("zhuohb", 666);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
