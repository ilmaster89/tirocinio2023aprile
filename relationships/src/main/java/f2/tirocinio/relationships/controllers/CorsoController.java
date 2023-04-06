package f2.tirocinio.relationships.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import f2.tirocinio.relationships.daos.CorsoDao;

@Controller
public class CorsoController {

	@Autowired
	CorsoDao cDao;

	@GetMapping("/corsi")
	public String corsi(Model model) {
		model.addAttribute("list", cDao.findAll());
		return "corsi";
	}
}
