package org.example.factory.abstracts.factory;

import org.example.factory.abstracts.product.INote;
import org.example.factory.abstracts.product.IVideo;
import org.example.factory.abstracts.product.JavaNote;
import org.example.factory.abstracts.product.JavaVideo;

/**
 * @author zhuohb
 * @date 2024/2/27 15:40
 */
public class JavaCourseFactory extends CourseFactory {
	@Override
	public INote createNote() {
		super.init();
		return new JavaNote();
	}

	@Override
	public IVideo createVideo() {
		super.init();
		return new JavaVideo();
	}
}
