package org.example.factory.method;

import org.example.factory.simple.ICourse;
import org.example.factory.simple.JavaCourse;

/**
 * @author zhuohb
 * @date 2024/2/27 14:41
 */
public class JavaCourseFactory implements ICourseFactory{
	@Override
	public ICourse create() {
		return new JavaCourse();
	}
}
