package f2.tirocinio.aprile.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {



	@GetMapping("/saluto")
	public String saluto(@RequestParam("nome") String nomeUtente, @RequestParam("cognome") String cognomeUtente) {
		return "Ciao " + nomeUtente + " " + cognomeUtente + "!";
	}
	
	@GetMapping("/somma")
	public Integer somma(@RequestParam("n1") Integer n1, @RequestParam("n2") Integer n2) {
		return n1 + n2;
	}

	@GetMapping("/differenza")
	public Integer differenza(@RequestParam("n1") Integer n1, @RequestParam("n2") Integer n2) {
		return n1 - n2;
	}

	@GetMapping("/prodotto")
	public Integer prodotto(@RequestParam("n1") Integer n1, @RequestParam("n2") Integer n2) {
		return n1 * n2;
	}

	@GetMapping("/divisione")
	public String divisione(@RequestParam("n1") Integer n1, @RequestParam("n2") Integer n2) {

		if (n2 == 0) {
			return "Non è possibile dividere per zero!";
		} else {
			return n1 / n2 + " con il resto di " + n1 % n2;
		}
	}
}
