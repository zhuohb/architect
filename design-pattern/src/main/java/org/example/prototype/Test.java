package org.example.prototype;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuohb
 * @date 2024/3/1 14:24
 */
public class Test {
	public static void main(String[] args) {
		//创建原型对象
		ConcretePrototype concretePrototype = new ConcretePrototype();
		concretePrototype.setName("zhuohb");
		concretePrototype.setAge(18);
		List<String> hobbies = new ArrayList<>();
		hobbies.add("篮球");
		hobbies.add("足球");
		concretePrototype.setHobbies(hobbies);
		System.out.println(concretePrototype);
		//拷贝原型对象
		ConcretePrototype clone = concretePrototype.deepCloneHobbies();
		clone.getHobbies().add("游泳");

		System.out.println("原型对象:" + concretePrototype);
		System.out.println("克隆对象:" + clone);
	}
}
