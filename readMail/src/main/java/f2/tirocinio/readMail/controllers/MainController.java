package f2.tirocinio.readMail.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import f2.tirocinio.readMail.utils.MailUtil;

@Controller
public class MainController {

	@GetMapping("/")
	public String read() {
		MailUtil.readMail();

		return null;
	}
}
