package org.example.factory.simple;

/**
 * @author zhuohb
 * @date 2024/2/27 14:13
 */
public class CourseFactory {
	public ICourse create(Class<? extends ICourse> clazz) {
		try {
			if (null != clazz) {
				return clazz.newInstance();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return null;
	}
}
