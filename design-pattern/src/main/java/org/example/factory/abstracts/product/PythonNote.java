package org.example.factory.abstracts.product;

/**
 * @author zhuohb
 * @date 2024/2/27 15:42
 */
public class PythonNote implements INote {
	@Override
	public void edit() {
		System.out.println("Python笔记");
	}
}
