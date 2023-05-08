package f2.tirocinio.account.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import f2.tirocinio.account.daos.UtenteDao;
import f2.tirocinio.account.models.Utente;
import f2.tirocinio.account.utils.MailUtils;

@Controller
public class MainController {

	@Autowired
	UtenteDao uDao;

	@GetMapping("/")
	public String main(Model model) {
		model.addAttribute("utente", new Utente());
		return "register";
	}

	@PostMapping("/newUser")
	public String newUser(Utente utente) {

		utente.setAttivo(0);
		String uuid = UUID.randomUUID().toString();
		utente.setUuid(uuid);
		uDao.save(utente);

		String mailBody = "<div>Ciao,<br>il tuo account è stato creato correttamente."
				+ "<br>Clicca qui sotto per attivarlo:<br><a href='http://localhost:8080/attiva?id=" + uuid
				+ "'>Attiva</a>";

		MailUtils.sendMail(utente.getMail(), mailBody, null);

		return "attivazioneInCorso";
	}

	@GetMapping("/attiva")
	public String attiva(@RequestParam String id) {

		Utente u = uDao.utenteByUUID(id);
		if (u == null) {
			return "error";
		}

		u.setAttivo(1);
		u.setUuid(null);

		uDao.save(u);

		return "success";

	}

}
