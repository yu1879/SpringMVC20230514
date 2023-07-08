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
import spring.mvc.session15.repository.JobDao;

@Controller
@RequestMapping("/session15/employee")
public class EmployeeController {
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private JobDao jobDao;

	@GetMapping("/")
	public String index(@ModelAttribute Employee employee, Model model, HttpSession session) {
		model.addAttribute("_method", "POST");
		setBaseModelAttribute(model, session);
		return "session15/employee";

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
		model.addAttribute("jobs", jobDao.query());

	}

	private int getPageCount() {
		int count = employeeDao.getCount();
		int limit = employeeDao.LIMIT;
		int pageCount = ((int) Math.ceil((double) count / limit));
		return pageCount;
	}

}
