package org.example.factory.simple;

/**
 * @author zhuohb
 * @date 2024/2/27 14:12
 */
public class PythonCourse implements ICourse {
	@Override
	public void record() {
		System.out.println("Python课程");
	}
}
