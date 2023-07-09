package spring.mvc.session15.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.mvc.session15.entity.Employee;
import spring.mvc.session15.entity.Job;
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

	@PostMapping("/")
	public String add(@ModelAttribute @Valid Employee employee, BindingResult result, Model model,
			HttpSession session) {
		if (result.hasErrors()) {
			model.addAttribute("_method", "POST");
			setBaseModelAttribute(model, session);
			return "sessio15/employee";
		}
		employeeDao.add(employee);
		String sessionNum = session.getAttribute("num") + "";
		if (sessionNum.length() > 0) {
			session.setAttribute("num", getPageCount());
		}
		return "redirect:./";
	}

	@DeleteMapping("/")
	public String delete(Employee employee, HttpSession session) {
		Integer eid = employee.getEid();
		jobDao.delete(eid);

		try {
			int num = Integer.parseInt(session.getAttribute("num") + "");
			int pageCount = getPageCount();
			if (num > pageCount) {
				session.setAttribute("num", pageCount);

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:./";

	}

	@GetMapping("{eid}")
	public String get(@PathVariable("eid") Integer eid, Model model, HttpSession session) {
		model.addAttribute("_method", "PUT");
		model.addAttribute("employee", employeeDao.get(eid));

		setBaseModelAttribute(model, session);

		return "session15/employee";

	}

	@PutMapping("/")
	public String update(@ModelAttribute @Valid Employee employee, BindingResult result, Model model,
			HttpSession session) {
		if (result.hasErrors()) {
			model.addAttribute("_method", "POST");
			setBaseModelAttribute(model, session);
			return "sessio15/employee";
		}
		employeeDao.add(employee);
		String sessionNum = session.getAttribute("num") + "";
		if (sessionNum.length() > 0) {
			session.setAttribute("num", getPageCount());
		}
		employeeDao.update(employee);

		return "redirect:./";
	}

	private void setBaseModelAttribute(Model model, HttpSession session) {
		String sessionNum = session.getAttribute("num") + "";
		int pageCount = getPageCount();
		if (sessionNum.length() > 0 && !sessionNum.equals("null")) {
			int num = Integer.parseInt(sessionNum);
			if (num > pageCount) {
				num = pageCount;
				session.setAttribute("num", num);
			}

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
