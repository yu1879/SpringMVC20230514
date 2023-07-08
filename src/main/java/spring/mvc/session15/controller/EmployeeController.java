package spring.mvc.session15.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.mvc.session15.entity.Employee;
import spring.mvc.session15.repository.EmployeeDao;

@Controller
@RequestMapping("/session15/employee")
public class EmployeeController {
	@Autowired
	private EmployeeDao employeeDao;

	@GetMapping("/")
	public String index(@ModelAttribute Employee employee, Model model, HttpSession session) {

	}

	private void setBaseModelAttribute(Model model, HttpSession session) {
		String sessionNum = session.getAttribute("num") + "";
		if (sessionNum.length() > 0 && !sessionNum.equals("null")) {
			int num = Integer.parseInt(sessionNum);
			model.addAttribute("employees", employeeDao.queryPage(num));

		} else {
			model.addAttribute("employees", employeeDao.query());

		}
		model.addAttribute("pageCount", getPageCount());
		model.addAttribute("employees", employeeDao.query());

	}

	private int getPageCount() {
		int count = employeeDao.getCount();
		int limit = employeeDao.LIMIT;
		int pageCount = ((int) Math.ceil((double) count / limit));
		return pageCount;
	}

}
