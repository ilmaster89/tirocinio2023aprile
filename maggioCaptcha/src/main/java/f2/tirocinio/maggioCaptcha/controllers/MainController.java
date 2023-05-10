package f2.tirocinio.maggioCaptcha.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import f2.tirocinio.maggioCaptcha.classes.CaptchaResult;

@Controller
public class MainController {

	private final String SECRET = "6Lc1IvklAAAAAFWshulgS40Yc1g-DHPCEV0ds4yR";
	private final String URL = "https://www.google.com/recaptcha/api/siteverify";

	@GetMapping("/")
	public String main() {
		return "main";
	}

	@PostMapping("/login")
	public String login(@RequestParam("g-recaptcha-response") String response) {

		String reqBody = "secret=" + SECRET + "&response=" + response;

		HttpHeaders reqHeaders = new HttpHeaders();
		reqHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		HttpEntity<String> request = new HttpEntity<String>(reqBody, reqHeaders);

		RestTemplate rt = new RestTemplate();

		CaptchaResult result = rt.postForEntity(URL, request, CaptchaResult.class).getBody();

		if (result.isSuccess())
			return "redirect:/success";

		return "redirect:/failure";
	}

	@GetMapping("/success")
	public String success() {
		return "success";
	}

	@GetMapping("/failure")
	public String failure() {
		return "failure";
	}
}
