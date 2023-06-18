package spring.mvc.session15.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.mvc.session15.entity.Job;
import spring.mvc.session15.repository.EmployeeDao;
import spring.mvc.session15.repository.JobDao;

@Controller
@RequestMapping("/session15/job")
public class JobController {
	@Autowired
	private JobDao jobDao;
	@Autowired
	private EmployeeDao employeeDao;

	private int getPageCount() {
		int count = jobDao.getCount();
		int limit = jobDao.LIMIT;
		int pageCount = ((int) Math.ceil((double) count / limit));
		return pageCount;
	}

	@GetMapping("/")
	public String index(@ModelAttribute Job job, Model model, HttpSession session) {
		model.addAttribute("_method", "POST");
//		model.addAttribute("jobs", jobDao.query());
//		model.addAttribute("pageCount", getPageCount());
		setBaseModelAttribute(model, session);
		return "session15/job";
	}

	@GetMapping("/page/{pageNo}")
	public String page(@PathVariable("pageNo") int pageNo, @ModelAttribute Job job, Model model, HttpSession session) {
		if (pageNo < 0) {
			session.setAttribute("num", pageNo);
			return "redirect:../";
		}
		session.setAttribute("num", pageNo);
		model.addAttribute("_method", "POST");
//		model.addAttribute("pageCount", getPageCount());
//		model.addAttribute("employees", employeeDao.query());
		setBaseModelAttribute(model, session);
		return "session15/job";
	}

	@GetMapping("{jid}")
	public String get(@PathVariable("jid") Integer jid, Model model, HttpSession session) {
		model.addAttribute("_method", "PUT");
		model.addAttribute("job", jobDao.get(jid));

		setBaseModelAttribute(model, session);

		return "session15/job";

	}

	@PostMapping("/")
	public String add(@ModelAttribute @Valid Job job, BindingResult result, Model model, HttpSession session) {
		if (result.hasErrors()) {
			model.addAttribute("_method", "POST");
			setBaseModelAttribute(model, session);
			return "sessio15/job";
		}
		jobDao.add(job);
		String sessionNum = session.getAttribute("num") + "";
		if (sessionNum.length() > 0) {
			session.setAttribute("num", getPageCount());
		}
		return "redirect:./";
	}

	@PutMapping("/")
	public String update(@ModelAttribute @Valid Job job, BindingResult result, Model model, HttpSession session) {
		if (result.hasErrors()) {
			model.addAttribute("_method", "POST");
			setBaseModelAttribute(model, session);
			return "sessio15/job";
		}
		jobDao.add(job);
		String sessionNum = session.getAttribute("num") + "";
		if (sessionNum.length() > 0) {
			session.setAttribute("num", getPageCount());
		}
		return "redirect:./";
	}

	private void setBaseModelAttribute(Model model, HttpSession session) {
		String sessionNum = session.getAttribute("num") + "";
		if (sessionNum.length() > 0 && !sessionNum.equals("null")) {
			int num = Integer.parseInt(sessionNum);
			model.addAttribute("jobs", jobDao.queryPage(num));

		} else {
			model.addAttribute("jobs", jobDao.query());

		}
		model.addAttribute("pageCount", getPageCount());
		model.addAttribute("employees", employeeDao.query());

	}

}
