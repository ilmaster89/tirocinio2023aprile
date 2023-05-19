package f2.tirocinio.docky.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import f2.tirocinio.docky.daos.UtenteDao;

@Controller
public class MainController {

	@Autowired
	UtenteDao uDao;

	@GetMapping("/")
	public String main(Model model) {
		model.addAttribute("utenti", uDao.findAll());
		return "main";
	}
}
