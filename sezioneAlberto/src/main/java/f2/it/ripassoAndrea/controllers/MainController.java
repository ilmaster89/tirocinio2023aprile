package f2.it.ripassoAndrea.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

	@GetMapping("/")
	public String main() {
		return "main";
	}

	@GetMapping("/saluto")
	public String saluto(Model model) {

		model.addAttribute("saluto", "Ciao Alberto!");
		return "saluto";
	}

	@GetMapping("/salutoPersonale")
	public String salutoPersonale(Model model, @RequestParam String nome, @RequestParam String cognome) {

		model.addAttribute("saluto", "Ciao " + nome + " " + cognome + "!");
		return "saluto";

	}

	@PostMapping("/salutoPersonaleFORM")
	public String salutoPersonaleFORM(Model model, @RequestParam String nome, @RequestParam String cognome) {

		model.addAttribute("saluto", "Ciao " + nome + " " + cognome + "!");
		return "saluto";

	}

	@GetMapping("/result")
	public String result(Model model, @RequestParam Integer n1, @RequestParam Integer n2, @RequestParam String op) {

		String result;

		if (op.equals("sum")) {
			result = String.valueOf(n1 + n2);
		} else if (op.equals("difference")) {
			result = String.valueOf(n1 - n2);
		} else if (op.equals("product")) {
			result = String.valueOf(n1 * n2);
		} else {

			result = (n2 != 0) ? String.valueOf(n1 / n2) + " con il resto di " + (n1 % n2)
					: "Non si può dividere per zero!";
		}

		model.addAttribute("result", result);
		return "opResult";

	}

}
