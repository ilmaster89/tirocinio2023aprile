package f2.tirocinio.docky.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import f2.tirocinio.docky.daos.UtenteDao;
import f2.tirocinio.docky.models.Utente;

@RestController
public class SecondController {

	@Autowired
	UtenteDao uDao;

	@GetMapping("/listaUtenti")
	public List<Utente> listaUtenti() {
		return uDao.findAll();
	}

	@GetMapping("/dettaglioUtente")
	public Utente dettaglioUtente(@RequestParam Integer id) {
		return uDao.findById(id).get();
	}

	@PostMapping("/saveUtente")
	public Utente saveUtente(@RequestBody Utente utente) {
		uDao.save(utente);
		return utente;

	}
}
