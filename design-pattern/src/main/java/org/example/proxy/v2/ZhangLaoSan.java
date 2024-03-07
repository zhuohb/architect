package org.example.proxy.v2;

/**
 * @author zhuohb
 * @date 2024/3/7 15:22
 */
public class ZhangLaoSan implements IPerson {
	private ZhangSan zhangSan;
	public ZhangLaoSan(ZhangSan zhangSan) {
		this.zhangSan = zhangSan;
	}

	@Override
	public void findLove() {
		System.out.println("张老三开始物色对象");
		zhangSan.findLove();
		System.out.println("双方同意交往，确立恋爱关系");
	}
}
