package f2.tirocinio.transaction_schedules.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import f2.tirocinio.transaction_schedules.daos.StudenteDao;

@Controller
public class StudenteController {

	@Autowired
	StudenteDao sDao;

	@GetMapping("/")
	public String main(Model model) {
		model.addAttribute("studenti", sDao.studentiAttivi());
		return "main";
	}

	@GetMapping("/ritira")
	public String ritira(@RequestParam List<Integer> ids) {

		sDao.ritiraStudenti(ids);
		return "redirect:/";
	}
}
