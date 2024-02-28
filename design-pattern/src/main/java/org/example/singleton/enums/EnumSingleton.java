package org.example.singleton.enums;

/**
 * @author zhuohb
 * @date 2024/2/27 17:51
 */
public enum EnumSingleton {
	INSTANCE;
	private Object data;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static EnumSingleton getInstance() {
		return INSTANCE;
	}
}
