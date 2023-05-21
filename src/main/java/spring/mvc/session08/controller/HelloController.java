package spring.mvc.session08.controller;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.mvc.session08.entity.User;

@Controller
@RequestMapping(value = "/hello")
public class HelloController {

	@RequestMapping(value = "/welcome")
	@ResponseBody
	public String welcome() {
		return "Welcome Spring MVC!";
	}

	@RequestMapping(value = "/sayhi")
	@ResponseBody
	public String sayHi(@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "age", required = false, defaultValue = "0") Integer age) {

		String data = String.format("Hi %s, %d", name, age);
		return data;
	}

//	@RequestMapping(value = "/bmi")
//	@ResponseBody
//	public Double bmi(@RequestParam(value = "height", required = true) Double h,
//			@RequestParam(value = "weight", required = false, defaultValue = "0") Double w) {
//		Double bmi = w / ((h / 100.0) * (h / 100.0));
//		return bmi;
//	}
	@RequestMapping("/bmi")
	@ResponseBody
	public String bmi(@RequestParam("h") Double h, @RequestParam("w") Double w) {
		double bmivalue = w / Math.pow(h / 100, 2);
		return String.format("bmi=%.2f", bmivalue);
	}

	@RequestMapping("/age")
	@ResponseBody
	public String getAvgOfAge(@RequestParam("age") List<Integer> ages) {
		double avg = ages.stream().mapToInt(age -> age.intValue())
//				mapToInt(Integer::intValue)
				.average().getAsDouble();
		return String.format("avg=%.1f", avg);
	}

	@RequestMapping(value = "/exam", produces = { "text/plain;charset=utf-8" })
	@ResponseBody
	public String getExamInfo(@RequestParam("score") List<Integer> scores) {
		IntSummaryStatistics stat = scores.stream().mapToInt(Integer::intValue).summaryStatistics();

		return String.format("最高分:%d 最低分:%d 平均:%.1f 總分:%d", stat.getMax(), stat.getMin(), stat.getAverage(),
				stat.getSum());
	}

	@RequestMapping(value = "/person")
	@ResponseBody
	public String getPerson(@RequestParam("score") Map<String, String> personMap) {

		return personMap.toString();
	}

	@RequestMapping(value = "/user")
	@ResponseBody
	public String getUser(User user) {

		return user.toString();
	}

	@RequestMapping(value = "/javaexam/{score}")
	@ResponseBody
	public String getJavaExam(@PathVariable("score") Integer score) {

		return String.format("Java :%d %s", score, (score >= 60) ? "pass" : "fail");
	}

	@RequestMapping(value = "/calc/{exp}")
	@ResponseBody
	public String calc(@PathVariable("exp") String exp,
			@RequestParam(value = "x", required = false, defaultValue = "0") Integer x,
			@RequestParam(value = "y", required = false, defaultValue = "0") Integer y) {
		int result = 0;
		switch (exp) {
		case "add":
			result = x + y;
			break;
		case "sub":
			result = x - y;
			break;

		}
		return String.format("Result :%d", result);
	}

	@RequestMapping(value = "/any/*/java?")
	@ResponseBody
	public String any() {
		return "Any";
	}
	@RequestMapping(value = "/stuent/{name:[a-z]+}-{age:\\d+}")
	@ResponseBody
	public String student(
			@PathVariable("name") String name,
			@ PathVariable("age")Integer age) {
		return name+":"+age;
	}

}
