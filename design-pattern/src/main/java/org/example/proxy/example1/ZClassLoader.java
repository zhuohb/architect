package org.example.proxy.example1;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * @author zhuohb
 * @date 2024/3/26 17:23
 */
public class ZClassLoader extends ClassLoader{


	private File classPathFile;
	public ZClassLoader(){
		String classPath = ZClassLoader.class.getResource("").getPath();
		this.classPathFile = new File(classPath);
	}

	@Override
	protected Class<?> findClass(String name) {

		String className = ZClassLoader.class.getPackage().getName() + "." + name;
		if(classPathFile  != null){
			File classFile = new File(classPathFile,name.replaceAll("\\.","/") + ".class");
			if(classFile.exists()){
				FileInputStream in = null;
				ByteArrayOutputStream out = null;
				try{
					in = new FileInputStream(classFile);
					out = new ByteArrayOutputStream();
					byte [] buff = new byte[1024];
					int len;
					while ((len = in.read(buff)) != -1){
						out.write(buff,0,len);
					}
					return defineClass(className,out.toByteArray(),0,out.size());
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
