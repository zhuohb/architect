package org.example.factory.abstracts.product;

/**
 * @author zhuohb
 * @date 2024/2/27 15:38
 */
public class JavaVideo implements IVideo {
	@Override
	public void record() {
		System.out.println("Java视频");
	}
}
