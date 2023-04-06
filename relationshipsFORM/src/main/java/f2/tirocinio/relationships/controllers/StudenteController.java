package f2.tirocinio.relationships.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import f2.tirocinio.relationships.daos.CorsoDao;
import f2.tirocinio.relationships.daos.StudenteDao;
import f2.tirocinio.relationships.models.Studente;

@Controller
public class StudenteController {

	@Autowired
	StudenteDao sDao;
	@Autowired
	CorsoDao cDao;

	@GetMapping("/")
	public String main(Model model) {
		model.addAttribute("list", sDao.findAll());
		return "main";
	}

	@GetMapping("/insertStudente")
	public String insertStudente(Model model) {
		model.addAttribute("studente", new Studente());
		model.addAttribute("corsi", cDao.findAll());
		return "insertStudente";
	}

	@PostMapping("/newStudente")
	public String newStudente(Studente studente) {
		sDao.save(studente);
		return "redirect:/";
	}
}
