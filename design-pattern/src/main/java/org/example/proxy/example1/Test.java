package org.example.proxy.example1;

/**
 * @author zhuohb
 * @date 2024/3/26 18:09
 */
public class Test {
	public static void main(String[] args) {
		Meipo ZMeipo = new Meipo();
		IPerson zhangsan = ZMeipo.getInstance(new ZhangSan());
		zhangsan.findLove();
	}
}
