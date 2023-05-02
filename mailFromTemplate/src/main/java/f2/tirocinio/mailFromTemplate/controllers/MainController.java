package f2.tirocinio.mailFromTemplate.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;

import f2.tirocinio.mailFromTemplate.classes.Order;
import f2.tirocinio.mailFromTemplate.utils.MailUtil;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class MainController {

	@Autowired
	SpringTemplateEngine engine;

	@GetMapping("/")
	public String main() {
		return "main";
	}

	@PostMapping("/register")
	public String register(@RequestParam String name, @RequestParam String surname, @RequestParam String email) {

		Context ctx = new Context();
		ctx.setVariable("user", name + " " + surname);

		String htmlString = engine.process("welcomeTemplate", ctx);
		MailUtil.sendMail(email, htmlString);

		return "redirect:/success";
	}

	@GetMapping("/success")
	public String success() {
		return "success";
	}

	@GetMapping("/order")
	public String order(HttpServletResponse response) {

		String name = "Alberto";
		String surname = "Ramponi";
		String address = "Via via 1, Milano";

		List<Order> orders = new ArrayList<Order>();
		orders.add(new Order("Banane", 5));
		orders.add(new Order("Mele", 3));

		Context ctx = new Context();

		HashMap<String, Object> variables = new HashMap<String, Object>();
		variables.put("name", name);
		variables.put("surname", surname);
		variables.put("address", address);
		variables.put("orders", orders);

		ctx.setVariables(variables);
		String htmlString = engine.process("receipt", ctx);

		ITextRenderer renderer = new ITextRenderer();

		renderer.setDocumentFromString(htmlString);
		renderer.layout();

		try {
//			response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"receipt.pdf\"");
//			response.setContentType("application/pdf");

			OutputStream os = new FileOutputStream(new File("receipt" + name + surname + ".pdf"));
			renderer.createPDF(os);

			return "redirect:/success";
		} catch (IOException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
