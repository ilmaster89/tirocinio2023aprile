package f2.tirocinio.maggioIDENTITY.controllers;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;

@Controller
public class MainController {

	@GetMapping("/")
	public String main() {
		return "main";
	}

	@PostMapping("/login")
	public String login(@RequestParam String credential, Model model) {

		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
				.build();
		try {
			GoogleIdToken token = verifier.verify(credential);
			Payload pl = token.getPayload();
			model.addAttribute("user", pl.get("name"));
			model.addAttribute("pic", pl.get("picture"));

			return "success";
		} catch (GeneralSecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "failure";
		}
	}
}
