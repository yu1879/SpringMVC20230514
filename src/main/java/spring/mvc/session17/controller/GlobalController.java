package spring.mvc.session17.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalController {

	@ExceptionHandler({ RuntimeException.class, SQLException.class })
	public String globalException(Exception ex, Model model, HttpServletRequest request) {
		String referer = request.getHeader("Referer");
		model.addAttribute("referer", referer);
		model.addAttribute("ex", "@ControllerAdvice 全局例外：" + ex);
		return "session17/error";
	}

//	@ModelAttribute(name = "globalData")
//	public Map<String, Object> globalModelData() {
//		Map<String, Object> map = new HashMap<>();
//		map.put("copyright", "巨匠電腦股份有限公司");
//		map.put("email", "contact@pcschool.com");
//		return map;
//	}

}
