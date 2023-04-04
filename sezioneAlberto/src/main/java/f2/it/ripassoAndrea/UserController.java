package f2.it.ripassoAndrea;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

	List<String> list = new ArrayList<String>();

	@GetMapping("/insert")
	public String insert() {
		return "inserimentoDati";
	}

	@GetMapping("/list")
	public String userList(Model model) {
		model.addAttribute("list", list);
		return "list";
	}

	@PostMapping("/newUser")
	public String newUser(@RequestParam String nome, @RequestParam String cognome) {

		list.add(nome + " " + cognome);
		return "redirect:/list";
	}

}
