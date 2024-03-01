package org.example.Builder;

/**
 * @author zhuohb
 * @date 2024/3/1 15:23
 */
public class Test {
	public static void main(String[] args) {
		CourseBuilder builder = new CourseBuilder()
			.addName("设计模式")
			.addPPT("PPT")
			.addVideo("Video")
			.addNote("Note")
			.addHomework("Homework");
		System.out.println(builder.build());
	}
}
