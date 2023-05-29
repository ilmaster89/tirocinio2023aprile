package f2.tirocinio.kafky.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import f2.tirocinio.kafky.daos.StudenteDao;
import f2.tirocinio.kafky.models.Studente;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MainController {

	@Autowired
	StudenteDao sDao;

	@GetMapping("/list")
	public List<Studente> list() {
		return sDao.findAll();
	}

	@PostMapping("/save")
	public Integer saveStudente(@RequestBody Studente studente) {
		try {
			sDao.save(studente);
			return 0;
		} catch (Exception e) {
			return 1;
		}
	}

	@GetMapping("/getById/{id}")
	public Studente stGetById(@PathVariable Integer id) {
		return sDao.findById(id).get();
	}

}
