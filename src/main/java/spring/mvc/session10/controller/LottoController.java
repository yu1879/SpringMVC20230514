package spring.mvc.session10.controller;

import java.awt.image.MultiPixelPackedSampleModel;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spring.mvc.session10.service.LottoService;

@Controller
@RequestMapping("/lotto")
public class LottoController {
	@Autowired
	private LottoService lottoService;

	@GetMapping("/")
	public String index(Model model) {

		List<Set<Integer>> lottos = lottoService.querAll();
		model.addAttribute("lottos", lottos);

		return "session10/lotto";

	}

	@GetMapping("/add")
	public String add(Model model, RedirectAttributes attr) {

		Set<Integer> lotto = lottoService.add();
		model.addAttribute("lotto", lotto);

		return "redirect:./";

	}
}
