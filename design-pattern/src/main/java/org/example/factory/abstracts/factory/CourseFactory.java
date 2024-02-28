package org.example.factory.abstracts.factory;

import org.example.factory.abstracts.product.INote;
import org.example.factory.abstracts.product.IVideo;

/**
 * @author zhuohb
 * @date 2024/2/27 15:37
 */
public abstract class CourseFactory {
	public void init(){
		System.out.println("初始化基础数据");
	}
	public abstract INote createNote();
	public abstract IVideo createVideo();
}
