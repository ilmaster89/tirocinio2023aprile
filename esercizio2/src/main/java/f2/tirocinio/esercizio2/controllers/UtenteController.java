package f2.tirocinio.esercizio2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import f2.tirocinio.esercizio2.daos.UtenteDao;
import f2.tirocinio.esercizio2.models.Utente;

@Controller
public class UtenteController {

	@Autowired
	UtenteDao uDao;

	@GetMapping("/")
	public String main() {
		return "main";
	}

	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("list", uDao.utentiAttivi());
		return "list";
	}

	@GetMapping("/insert")
	public String insert(Model model) {
		model.addAttribute("utente", new Utente());
		return "insert";
	}

	@PostMapping("/newUser")
	public String newUser(Utente utente) {
		utente.setAttivo(1);
		uDao.save(utente);
		return "redirect:/list";
	}

	@GetMapping("/update/{id}")
	public String update(Model model, @PathVariable Integer id) {

		model.addAttribute("utente", uDao.findById(id).get());
		return "update";
	}

	@PostMapping("/updateUtente")
	public String updateUtente(Utente utente) {
		uDao.save(utente);
		return "redirect:/list";
	}

	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable Integer id) {
		Utente u = uDao.findById(id).get();
		u.setAttivo(0);
		uDao.save(u);
		return "redirect:/list";
	}
}
