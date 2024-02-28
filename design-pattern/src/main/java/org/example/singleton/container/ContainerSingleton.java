package org.example.singleton.container;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhuohb
 * @date 2024/2/28 17:26
 */
public class ContainerSingleton {
	private ContainerSingleton() {
	}

	private static Map<String, Object> ioc = new ConcurrentHashMap<>();

	public static Object getBean(String className) {
		synchronized (ioc) {
			if (!ioc.containsKey(className)) {
				Object obj = null;
				try {
					obj = Class.forName(className).newInstance();
					ioc.put(className, obj);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				return obj;
			} else {
				return ioc.get(className);
			}
		}
	}
}
