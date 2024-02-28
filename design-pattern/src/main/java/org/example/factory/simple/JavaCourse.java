package org.example.factory.simple;

/**
 * @author zhuohb
 * @date 2024/2/27 13:57
 */
public class JavaCourse implements ICourse{
	@Override
	public void record() {
		System.out.println("Java课程");
	}
}
