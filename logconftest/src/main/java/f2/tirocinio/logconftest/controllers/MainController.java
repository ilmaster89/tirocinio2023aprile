package f2.tirocinio.logconftest.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/")
	public String main() {

		Properties props = new Properties();
		Logger logger = LoggerFactory.getLogger("f2.tirocinio.logger");
		logger.info("This is an info");
		logger.warn("This is a warning");

		try {
			props.load(new FileInputStream(new File("config.properties")));
			System.out.println("Name" + props.get("user_name"));
			System.out.println("Surname " + props.get("user_surname"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
