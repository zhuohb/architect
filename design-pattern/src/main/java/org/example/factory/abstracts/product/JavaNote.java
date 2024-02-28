package org.example.factory.abstracts.product;

/**
 * @author zhuohb
 * @date 2024/2/27 15:39
 */
public class JavaNote implements INote {
	@Override
	public void edit() {
		System.out.println("Java笔记");
	}
}
