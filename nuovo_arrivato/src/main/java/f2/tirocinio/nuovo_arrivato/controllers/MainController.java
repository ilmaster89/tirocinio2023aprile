package f2.tirocinio.nuovo_arrivato.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import f2.tirocinio.nuovo_arrivato.classes.Libro;

@RestController
public class MainController {

	@GetMapping("/")
	public String main() {
		return "Ciao, benvenuto nella mia app!";
	}

	@GetMapping("/sum")
	public Integer sum(@RequestParam Integer n1, @RequestParam Integer n2) {
		return n1 + n2;
	}

	@PostMapping("/saveBook")
	public void saveBook(@RequestBody Libro libro) {
		System.out.println(libro);
	}
}
