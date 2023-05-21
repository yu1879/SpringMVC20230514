package spring.mvc.session09;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/showdata")
public class ShowDataController {
	@RequestMapping("/case1")

	public ModelAndView case1() {
		String data = new Date() + "";

		String view = "/clock.jsp";
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", data);
		mav.setViewName(view);
		return mav;
	}
}
