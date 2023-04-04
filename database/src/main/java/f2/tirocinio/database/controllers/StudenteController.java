package f2.tirocinio.database.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import f2.tirocinio.database.daos.StudenteDao;
import f2.tirocinio.database.models.Studente;

@Controller
public class StudenteController {

	@Autowired
	StudenteDao sDao;

	@GetMapping("/")
	public String main() {
		return "main";
	}

	@GetMapping("/list")
	public String list(Model model) {

		// findAll
		// select * from studenti
		List<Studente> list = sDao.findAll();

		model.addAttribute("list", list);

		return "list";
	}

	@GetMapping("/insert")
	public String insert(Model model) {

		model.addAttribute("studente", new Studente());

		return "insert";
	}

	@PostMapping("/newStudente")
	public String newStudente(Studente studente) {

		sDao.save(studente);
		return "redirect:/list";

	}
}
