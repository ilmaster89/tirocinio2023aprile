package f2.tirocinio.customQueries.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import f2.tirocinio.customQueries.daos.UtenteDao;
import f2.tirocinio.customQueries.models.Utente;
import jakarta.servlet.http.HttpSession;

@Controller
public class UtenteController {

	@Autowired
	UtenteDao uDao;

	@GetMapping("/")
	public String main(Model model) {
		model.addAttribute("utente", new Utente());
		return "main";
	}

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("utente", new Utente());
		return "register";
	}

	@PostMapping("/newUser")
	public String newUser(Utente utente) {
		utente.setAttivo(1);
		uDao.save(utente);
		return "redirect:/";
	}

	@PostMapping("/login")
	public String login(Utente utente, HttpSession session) {

		Utente u = uDao.userLogin(utente.getUsername(), utente.getPassword());

		if (u != null) {

			session.setAttribute("loggedUser", u);
			return "redirect:/areaPersonale";
		}

		return "redirect:/";

	}

	@GetMapping("/areaPersonale")
	public String ap(HttpSession session) {

		Utente u = (Utente) session.getAttribute("loggedUser");
		if (u == null) {
			return "redirect:/";
		}
		return "areaPersonale";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
