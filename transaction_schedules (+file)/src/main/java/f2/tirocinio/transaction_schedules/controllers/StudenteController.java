package f2.tirocinio.transaction_schedules.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import f2.tirocinio.transaction_schedules.daos.StudenteDao;
import f2.tirocinio.transaction_schedules.daos.UtenteDao;
import f2.tirocinio.transaction_schedules.models.Utente;

@Controller
public class StudenteController {

	private final String BASE_PATH = "C:\\Users\\Alberto\\eclipse-workspace\\transaction_schedules\\src\\main\\resources\\static\\";
	@Autowired
	StudenteDao sDao;
	@Autowired
	UtenteDao uDao;

	@GetMapping("/")
	public String main(Model model) {
		model.addAttribute("studenti", sDao.studentiAttivi());
		return "main";
	}

	@GetMapping("/ritira")
	public String ritira(@RequestParam List<Integer> ids) {

		sDao.ritiraStudenti(ids);
		return "redirect:/";
	}

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("utente", new Utente());
		return "register";
	}

	@PostMapping("/newUser")
	public String newUser(Utente utente, @RequestParam MultipartFile image) {

		Path savePath = Paths.get(BASE_PATH + image.getOriginalFilename());
		try {
			Files.write(savePath, image.getBytes());
			utente.setImg(image.getOriginalFilename());
			uDao.save(utente);
			return "redirect:/listaUtenti";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "reidirect:/register";
		}

	}

	@GetMapping("/listaUtenti")
	public String listaUtenti(Model model) {
		model.addAttribute("listaUtenti", uDao.findAll());
		return "listaUtenti";
	}
}
