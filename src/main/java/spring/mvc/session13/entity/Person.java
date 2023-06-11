package spring.mvc.session13.entity;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Person {
	@NotEmpty(message = "姓名不可以是空值")
	@Size(min = 2, max = 50, message = "姓名必須介於{min}~{max}個字之間")
	private String name;
	@NotNull(message = "年齡不可以是空值")
	@Range(min = 18, max = 99, message = "年齡必須介於{min}~{max}個字之間")
	private Integer age;
	@NotNull(message = "會員設定不可以是空值")

	private Boolean member;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@NotNull(message = "生日日期設定不可以是空值")
	@Past(message = "生日不可以大於現在日期")
	private Date birth;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Boolean getMember() {
		return member;
	}

	public void setMember(Boolean member) {
		this.member = member;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", member=" + member + ", birth=" + birth + "]";
	}

}
