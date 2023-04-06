package f2.tirocinio.relationships.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import f2.tirocinio.relationships.daos.ProfessoreDao;

@Controller
public class ProfessoreController {

	@Autowired
	ProfessoreDao pDao;

	@GetMapping("/professori")
	public String professori(Model model) {
		model.addAttribute("list", pDao.findAll());
		return "professori";

	}
}
