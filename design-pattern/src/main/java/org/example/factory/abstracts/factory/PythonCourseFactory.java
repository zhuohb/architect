package org.example.factory.abstracts.factory;

import org.example.factory.abstracts.product.INote;
import org.example.factory.abstracts.product.IVideo;
import org.example.factory.abstracts.product.PythonNote;
import org.example.factory.abstracts.product.PythonVideo;

/**
 * @author zhuohb
 * @date 2024/2/27 15:42
 */
public class PythonCourseFactory extends CourseFactory {
	@Override
	public INote createNote() {
		return new PythonNote();
	}

	@Override
	public IVideo createVideo() {
		return new PythonVideo();
	}
}
