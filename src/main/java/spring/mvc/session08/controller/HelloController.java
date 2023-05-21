package spring.mvc.session08.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
		double avg = ages.stream().mapToInt(Integer::intValue).average().getAsDouble();
		return String.format("avg=%.1f", avg);
	}

}
