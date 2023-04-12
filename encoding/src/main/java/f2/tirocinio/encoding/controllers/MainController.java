package f2.tirocinio.encoding.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import f2.tirocinio.encoding.daos.UtenteDao;
import f2.tirocinio.encoding.models.Utente;

@Controller
public class MainController {

	@Autowired
	UtenteDao uDao;
	@Autowired
	BCryptPasswordEncoder encoder;

	@GetMapping("/")
	public String main(Model model) {
		model.addAttribute("msg", "");
		return "main";
	}

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("utente", new Utente());
		return "register";
	}

	@PostMapping("/newUser")
	public String newUser(Utente utente) {

		String rawPassword = utente.getPassword();
		String encodedPassword = encoder.encode(rawPassword);
		utente.setPassword(encodedPassword);

		uDao.save(utente);
		return "main";
	}

	@PostMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password, Model model) {

		Utente u = uDao.userLogin(username);

		if (u == null) {
			model.addAttribute("msg", "Utente non trovato!");
			return "main";
		}

		if (!encoder.matches(password, u.getPassword())) {
			model.addAttribute("msg", "La password che hai inserito non è valida!");
			return "main";
		}

		return "areaPersonale";

	}
}
