package f2.tirocinio.social.controllers;

import java.sql.Date;
import java.time.LocalDate;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import f2.tirocinio.social.daos.CommentoDao;
import f2.tirocinio.social.daos.PostDao;
import f2.tirocinio.social.daos.RuoloDao;
import f2.tirocinio.social.daos.UtenteDao;
import f2.tirocinio.social.models.Commento;
import f2.tirocinio.social.models.Post;
import f2.tirocinio.social.models.Utente;
import jakarta.servlet.http.HttpSession;

@Controller
public class UtenteController {

	@Autowired
	UtenteDao uDao;
	@Autowired
	RuoloDao rDao;
	@Autowired
	BCryptPasswordEncoder encoder;
	@Autowired
	PostDao pDao;
	@Autowired
	CommentoDao cDao;

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

	@PostMapping("/new")
	public String newUtente(Utente utente) {
		utente.setAttivo(1);
		utente.setRuolo(rDao.findById(3).get());
		utente.setPassword(encoder.encode(utente.getPassword()));
		uDao.save(utente);
		return "redirect:/";
	}

	@PostMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password, Model model,
			HttpSession session) {

		Utente u = uDao.userLogin(username);
		if (u == null) {
			model.addAttribute("msg", "Utente non trovato!");
			return "main";
		}
		if (u.getAttivo() == 0) {
			model.addAttribute("msg", "Sembra che tu sia stato bannato!");
			return "main";
		}
		if (!encoder.matches(password, u.getPassword())) {
			model.addAttribute("msg", "Password errata!");
			return "main";
		}

		Hibernate.initialize(u.getPost());
		for (Post p : u.getPost()) {
			Hibernate.initialize(p.getCommenti());
		}

		session.setAttribute("loggedUser", u);

		return "redirect:/areaPersonale";
	}

	@GetMapping("/areaPersonale")
	public String areaPersonale(HttpSession session, Model model) {

		Utente u = (Utente) session.getAttribute("loggedUser");
		if (u == null) {
			return "redirect:/";
		}
		model.addAttribute("notifications", pDao.notifications(u.getId()));
		model.addAttribute("posts", pDao.tenPosts());
		model.addAttribute("post", new Post());
		// logica delle notifiche
		return "areaPersonale";
	}

	@PostMapping("/newPost")
	public String newPost(HttpSession session, Post post) {

		Utente u = (Utente) session.getAttribute("loggedUser");
		post.setUtente(u);
		pDao.save(post);
		return "redirect:/areaPersonale";

	}

	@PostMapping("/commenta/{postId}")
	public String commenta(@PathVariable Integer postId, @RequestParam String commento, HttpSession session) {

		Utente u = (Utente) session.getAttribute("loggedUser");
		Commento c = new Commento();
		c.setCommento(commento);
		c.setVisto(0);
		c.setDataCommento(Date.valueOf(LocalDate.now()));
		c.setUtente(u);
		c.setPost(pDao.findById(postId).get());

		cDao.save(c);
		return "redirect:/areaPersonale";
	}

	@PostMapping("/commentaFromDetail/{postId}")
	public String commentaFromDetail(@PathVariable Integer postId, @RequestParam String commento, HttpSession session) {

		Utente u = (Utente) session.getAttribute("loggedUser");
		Commento c = new Commento();
		c.setCommento(commento);
		c.setVisto(0);
		c.setDataCommento(Date.valueOf(LocalDate.now()));
		c.setUtente(u);
		c.setPost(pDao.findById(postId).get());

		cDao.save(c);
		return "redirect:/postDetail/" + postId;
	}

	@GetMapping("/userProfile/{userId}")
	public String profile(@PathVariable Integer userId, Model model, HttpSession session) {
		Utente u = (Utente) session.getAttribute("loggedUser");
		if (u == null) {
			return "redirect:/";
		}
		model.addAttribute("utente", uDao.findById(userId).get());
		return "userProfile";
	}

	@PostMapping("/userBan/{userId}")
	public String userBan(@PathVariable Integer userId, @RequestParam Integer giorni) {
		Utente u = uDao.findById(userId).get();
		u.setAttivo(0);
		u.setFineBan(System.currentTimeMillis() + giorni * 24 * 60 * 60 * 1000);
		uDao.save(u);
		return "redirect:/userProfile/" + userId;
	}

	@PostMapping("/allTimeBan/{userId}")
	public String allTimeBan(@PathVariable Integer userId, @RequestParam Integer giorni) {
		Utente u = uDao.findById(userId).get();
		u.setAttivo(0);
		uDao.save(u);
		return "redirect:/userProfile/" + userId;
	}

	@PostMapping("/promote/{userId}")
	public String promote(@PathVariable Integer userId) {
		Utente u = uDao.findById(userId).get();
		u.setRuolo(rDao.findById(2).get());
		uDao.save(u);

		return "redirect:/userProfile/" + userId;
	}

	@PostMapping("/reactivateUser/{userId}")
	public String reactivateUser(@PathVariable Integer userId) {
		Utente u = uDao.findById(userId).get();
		u.setAttivo(1);
		u.setFineBan(null);
		uDao.save(u);
		return "redirect:/userProfile/" + userId;
	}

	@GetMapping("/postDetail/{postId}")
	public String postDetail(@PathVariable Integer postId, Model model, HttpSession session) {
		Utente u = (Utente) session.getAttribute("loggedUser");
		if (u == null) {
			return "redirect:/";
		}
		Post p = pDao.findById(postId).get();
		if (p.getUtente().getId() == u.getId()) {
			for (Commento c : p.getCommenti()) {
				c.setVisto(1);
			}
		}
		pDao.save(p);
		model.addAttribute("post", p);
		return "postDetail";

	}

}
