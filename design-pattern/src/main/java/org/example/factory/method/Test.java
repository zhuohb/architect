package org.example.factory.method;

import org.example.factory.simple.CourseFactory;
import org.example.factory.simple.ICourse;
import org.example.factory.simple.JavaCourse;

/**
 * @author zhuohb
 * @date 2024/2/27 14:01
 */
public class Test {
	public static void main(String[] args) {
		PythonCourseFactory factory = new PythonCourseFactory();
		ICourse course = factory.create();
		course.record();
	}
}
