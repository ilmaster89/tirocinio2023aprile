package f2.tirocinio.relationships.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import f2.tirocinio.relationships.daos.CorsoDao;
import f2.tirocinio.relationships.daos.InsegnamentoDao;
import f2.tirocinio.relationships.daos.ProfessoreDao;
import f2.tirocinio.relationships.models.Insegnamento;
import f2.tirocinio.relationships.models.Professore;

@Controller
public class ProfessoreController {

	@Autowired
	ProfessoreDao pDao;
	@Autowired
	CorsoDao cDao;
	@Autowired
	InsegnamentoDao iDao;

	@GetMapping("/professori")
	public String professori(Model model) {
		model.addAttribute("list", pDao.findAll());
		return "professori";

	}

	@GetMapping("/insertProfessore")
	public String insertProfessore(Model model) {
		model.addAttribute("professore", new Professore());
		model.addAttribute("corsi", cDao.findAll());
		return "insertProfessore";
	}

	@GetMapping("/newProf")
	public String newProf(@RequestParam String nome, @RequestParam String cognome, @RequestParam List<Integer> corsi) {

		Professore p = new Professore();
		p.setNome(nome);
		p.setCognome(cognome);

		pDao.save(p);

		for (Integer id : corsi) {
			Insegnamento i = new Insegnamento();
			i.setProfessore(p);
			i.setCorso(cDao.findById(id).get());
			iDao.save(i);
		}

		return "redirect:/insertProfessore";
	}
}
