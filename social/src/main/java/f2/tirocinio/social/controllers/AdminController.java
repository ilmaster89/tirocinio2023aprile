package f2.tirocinio.social.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import f2.tirocinio.social.daos.RuoloDao;
import f2.tirocinio.social.daos.UtenteDao;
import f2.tirocinio.social.models.Utente;

@RestController
public class AdminController {

	@Autowired
	UtenteDao uDao;
	@Autowired
	BCryptPasswordEncoder encoder;
	@Autowired
	RuoloDao rDao;

	@PostMapping("/createAdmin")
	public void createAdmin(@RequestBody Utente utente) {
		utente.setAttivo(1);
		utente.setPassword(encoder.encode(utente.getPassword()));
		utente.setRuolo(rDao.findById(1).get());
		uDao.save(utente);
	}
}
