package org.example.prototype;

import lombok.Data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuohb
 * @date 2024/3/1 14:20
 */
@Data
public class ConcretePrototype implements Cloneable, Serializable {
	private String name;
	private int age;
	private List<String> hobbies;

	@Override
	public ConcretePrototype clone() {
		try {
			return (ConcretePrototype) super.clone();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ConcretePrototype deepClone() {
        try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(this);

			ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bis);
			return (ConcretePrototype) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}

	public ConcretePrototype deepCloneHobbies(){
		try {
			ConcretePrototype concretePrototype = (ConcretePrototype) super.clone();
			concretePrototype.hobbies = (List) ((ArrayList) concretePrototype.hobbies).clone();
			return concretePrototype;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String toString() {
		return "ConcretePrototype{" +
			"name='" + name + '\'' +
			", age=" + age +
			", hobbies=" + hobbies +
			'}';
	}
}
