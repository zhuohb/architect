package org.example.proxy.v2;

/**
 * @author zhuohb
 * @date 2024/3/7 15:24
 */
public class Test {
	public static void main(String[] args) {
		JdkMeiPo jdkMeiPo = new JdkMeiPo();
		IPerson zhangSan = jdkMeiPo.getInstance(new ZhangSan());
		zhangSan.findLove();

		IPerson zhaoLiu = jdkMeiPo.getInstance(new ZhaoLiu());
		zhaoLiu.findLove();
	}
}
