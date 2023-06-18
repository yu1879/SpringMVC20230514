package spring.mvc.session15.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.mvc.session15.entity.Job;
import spring.mvc.session15.repository.JobDao;

@Controller
@RequestMapping("/session15/job")
public class JobController {
	@Autowired
	private JobDao jobDao;

	private int getPageCount() {
		int count = jobDao.getCount();
		int limit = jobDao.LIMIT;
		int pageCount = ((int) Math.ceil((double) count / limit));
		return pageCount;
	}

	@GetMapping("/")
	public String index(@ModelAttribute Job job, Model model) {
		model.addAttribute("_method", "POST");
		model.addAttribute("jobs", jobDao.query());
		model.addAttribute("pageCount", getPageCount());

		return "session15/job";
	}
}
