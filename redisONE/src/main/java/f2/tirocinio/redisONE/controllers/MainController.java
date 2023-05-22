package f2.tirocinio.redisONE.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import redis.clients.jedis.JedisPooled;

@Controller
public class MainController {

	@Autowired
	JedisPooled jedis;

	@GetMapping("/")
	public String main() {
		return "main";
	}

	@PostMapping("/login")
	public String login(@RequestParam String username) {
		if (username.equals("alberto")) {
			jedis.set("user", "alberto");
			return "ok";
		}
		return "main";
	}
}
