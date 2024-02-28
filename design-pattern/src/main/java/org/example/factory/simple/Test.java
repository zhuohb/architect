package org.example.factory.simple;

/**
 * @author zhuohb
 * @date 2024/2/27 14:01
 */
public class Test {
	public static void main(String[] args) {
		CourseFactory factory = new CourseFactory();
		ICourse course = factory.create(JavaCourse.class);
		course.record();
	}
}
