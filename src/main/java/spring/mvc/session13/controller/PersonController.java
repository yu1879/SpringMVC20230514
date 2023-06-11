package spring.mvc.session13.controller;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spring.mvc.session13.entity.Person;

@Controller
@RequestMapping("/session13/person")
public class PersonController {
	private List<Person> people = new CopyOnWriteArrayList<>();
	private String sort = "asc";

	private List<Person> getPeople() {
		Comparator<Person> comparator = Comparator.comparingInt(Person::getAge);
		if (sort.equals("desc")) {
			comparator = comparator.reversed();
		}
		return people.stream().sorted(comparator).collect(Collectors.toList());

	}

	@GetMapping("/")
	public String index(@RequestParam(value = "sort", required = false, defaultValue = "") String sort, Model model,
			@ModelAttribute Person person) {
		if (!sort.equals("")) {
			this.sort = sort;
		}

		model.addAttribute("people", getPeople());
		return "session13/person";
	}

	@PostMapping("/")
	public String add(@RequestParam(value = "sort", required = false, defaultValue = "") String sort, Model model,
			@ModelAttribute @Valid Person person, BindingResult result) {
		if (!sort.equals("")) {
			this.sort = sort;
		}

		if (result.hasErrors()) {
			model.addAttribute("people", getPeople());
			return "session13/person";

		}
		people.add(person);
		return "redirect:./";

	}

}
