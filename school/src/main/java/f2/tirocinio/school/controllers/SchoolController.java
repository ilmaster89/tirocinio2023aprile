package f2.tirocinio.school.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import f2.tirocinio.school.daos.LezioneDao;
import f2.tirocinio.school.daos.RuoloDao;
import f2.tirocinio.school.daos.UtenteDao;
import f2.tirocinio.school.models.Utente;
import f2.tirocinio.school.utils.Costanti;
import jakarta.servlet.http.HttpSession;

@Controller
public class SchoolController {

	@Autowired
	UtenteDao uDao;
	@Autowired
	RuoloDao rDao;
	@Autowired
	LezioneDao lDao;

	@GetMapping("/")
	public String main() {
		return "main";
	}

	@PostMapping("/login")
	public String login(HttpSession session, Model model, @RequestParam String username,
			@RequestParam String password) {

		Utente u = uDao.userLogin(username, password);

		if (u == null) {
			return "redirect:/";
		}
		session.setAttribute(Costanti.SESSION_USER, u);

		if (u.getRuolo().getNome().equals(Costanti.STUDENT)) {
			return "redirect:/lezioni";
		} else {
			return "redirect:/register";
		}

	}

	@GetMapping("/lezioni")
	public String lezioni(Model model, HttpSession session) {

		Utente u = (Utente) session.getAttribute(Costanti.SESSION_USER);
		if (u == null) {
			return "redirect:/";
		}

		model.addAttribute("lezioni", lDao.findAll());
		return "lezioni";

	}

	@GetMapping("/register")
	public String register(Model model, HttpSession session) {

		Utente u = (Utente) session.getAttribute(Costanti.SESSION_USER);

		if (u == null || u.getRuolo().getNome().equals(Costanti.STUDENT)) {
			return "redirect:/";
		}

		model.addAttribute("ruoli", rDao.findAll());
		model.addAttribute("utente", new Utente());

		return "register";
	}

	@PostMapping("/new")
	public String newUtente(Utente utente, HttpSession session, Model model) {

		if (utente.getRuolo() == null) {
			utente.setRuolo(rDao.findById(3).get());
		}

		uDao.save(utente);
		return "redirect:/";
	}
}
