package spring.mvc.session09;

import java.util.Date;

import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/showdata")
public class ShowDataController {
	@RequestMapping("/case1")

	public ModelAndView case1() {
		String data = new Date() + "";

		String view = "session09/clock";
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", data);
		mav.setViewName(view);
		return mav;
	}

	@RequestMapping(value = "case2", method = RequestMethod.GET)
	public String case2(Model model) {
		String data = new Date() + "";
		model.addAttribute("data", data);
		return "session09/clock";

	}
}
