package org.example.factory.abstracts;

import org.example.factory.abstracts.factory.JavaCourseFactory;

/**
 * @author zhuohb
 * @date 2024/2/27 15:44
 */
public class Test {
	public static void main(String[] args) {
		JavaCourseFactory javaCourseFactory = new JavaCourseFactory();
		javaCourseFactory.createNote().edit();
		javaCourseFactory.createVideo().record();
	}
}
