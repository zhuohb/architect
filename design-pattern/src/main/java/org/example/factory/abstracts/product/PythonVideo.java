package org.example.factory.abstracts.product;

/**
 * @author zhuohb
 * @date 2024/2/27 15:42
 */
public class PythonVideo implements IVideo{
	@Override
	public void record() {
		System.out.println("Python视频");
	}
}
