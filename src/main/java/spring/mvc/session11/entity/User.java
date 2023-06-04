package spring.mvc.session11.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class User {
	private String name;
	private Integer age;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat
	private Date birth;

}
