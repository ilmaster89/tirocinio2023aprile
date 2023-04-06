package f2.tirocinio.relationships.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import f2.tirocinio.relationships.daos.StudenteDao;

@Controller
public class StudenteController {

	@Autowired
	StudenteDao sDao;

	@GetMapping("/")
	public String main(Model model) {
		model.addAttribute("list", sDao.findAll());
		return "main";
	}
}
