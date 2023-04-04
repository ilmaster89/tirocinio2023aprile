package f2.tirocinio.aprile.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class ThymeController {

	@GetMapping("/")
	public String mainPage() {
		return "main";
	}

	@GetMapping("/nome")
	public String ciao(Model model) {

		model.addAttribute("myName", "Hamza");
		return "salutoPersonale";

	}

	@GetMapping("/greeting")
	public String greetin(HttpSession session, Model model, @RequestParam String nome, @RequestParam String cognome) {

		String greet = "Benvenuto nel mio sito, " + nome + " " + cognome + "!";
		model.addAttribute("greeting", greet);

		return "greeting";
	}
}
