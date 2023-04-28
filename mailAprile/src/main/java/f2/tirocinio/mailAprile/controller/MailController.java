package f2.tirocinio.mailAprile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import f2.tirocinio.mailAprile.utils.MailUtil;

@Controller
public class MailController {

	@GetMapping("/")
	public String main() {
		return "main";
	}

	@PostMapping("/sendMail")
	public String sendMail(@RequestParam String recipient, @RequestParam String body) {

		// MailUtil.sendMail(recipient, "Mail da ILMIOSITO.WELLA", body);
		MailUtil.sendMultiMail(recipient, "Mail figa", body);

		return "redirect:/";
	}
}
