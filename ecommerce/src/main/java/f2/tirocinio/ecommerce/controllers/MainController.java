package f2.tirocinio.ecommerce.controllers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import f2.tirocinio.ecommerce.daos.AcquistoDao;
import f2.tirocinio.ecommerce.daos.ProdottoDao;
import f2.tirocinio.ecommerce.daos.UtenteDao;
import f2.tirocinio.ecommerce.models.Acquisto;
import f2.tirocinio.ecommerce.models.Prodotto;
import f2.tirocinio.ecommerce.models.Utente;
import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

	@Autowired
	UtenteDao uDao;
	@Autowired
	ProdottoDao pDao;
	@Autowired
	AcquistoDao aDao;

	@GetMapping("/")
	public String main(Model model) {
		model.addAttribute("utente", new Utente());
		return "main";
	}

	@PostMapping("/login")
	public String login(HttpSession session, Model model, Utente utente) {

		Utente u = uDao.userLogin(utente.getUsername(), utente.getPassword());

		if (u == null) {
			return "redirect:/";
		}

		u.setCarrello(new ArrayList<Prodotto>());
		session.setAttribute("loggedUser", u);
		model.addAttribute("prodotti", pDao.findAll());

		return "catalogo";

	}

	@GetMapping("/mettiNelCarrello/{id}")
	public String mettiNelCarrello(@PathVariable Integer id, HttpSession session, Model model) {

		Prodotto p = pDao.findById(id).get();
		Utente u = (Utente) session.getAttribute("loggedUser");

		u.getCarrello().add(p);

		session.setAttribute("loggedUser", u);
		model.addAttribute("prodotti", pDao.findAll());

		return "catalogo";
	}

	@GetMapping("/paginaCarrello")
	public String carrello(HttpSession session) {

		return "paginaCarrello";
	}

	@GetMapping("/svuotaCarrello")
	public String svuotaCarrello(HttpSession session) {

		Utente u = (Utente) session.getAttribute("loggedUser");
		u.getCarrello().clear();
		session.setAttribute("loggedUser", u);

		return "paginaCarrello";
	}

	@GetMapping("/confermaAcquisto")
	public String confermaAcquisto(HttpSession session) {

		Utente u = (Utente) session.getAttribute("loggedUser");
		List<Prodotto> carrello = u.getCarrello();
		Date now = Date.valueOf(LocalDate.now());

		for (Prodotto p : carrello) {
			Acquisto a = new Acquisto();
			a.setDataAcquisto(now);
			a.setUtente(u);
			a.setProdotto(p);
			aDao.save(a);
		}

		u.getCarrello().clear();
		session.setAttribute("loggedUser", u);

		return "paginaCarrello";

	}
}
