package f2.tirocinio.redisTWO.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import redis.clients.jedis.JedisPooled;

@Controller
public class MainController {

	@Autowired
	JedisPooled jedis;

	@GetMapping("/")
	public String main() {
		if (jedis.get("user") == null) {
			return "ko";
		}

		return "ok";
	}

}
