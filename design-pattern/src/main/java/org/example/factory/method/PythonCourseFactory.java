package org.example.factory.method;

import org.example.factory.simple.ICourse;
import org.example.factory.simple.PythonCourse;

/**
 * @author zhuohb
 * @date 2024/2/27 14:41
 */
public class PythonCourseFactory implements ICourseFactory {
	@Override
	public ICourse create() {
		return new PythonCourse();
	}
}
