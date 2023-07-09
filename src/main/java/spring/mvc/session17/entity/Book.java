package spring.mvc.session17.entity;

public class Book {
	private String name;
	private Long age;

	@Override
	public String toString() {
		return "Book [name=" + name + ", age=" + age + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

}
