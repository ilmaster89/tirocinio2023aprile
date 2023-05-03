package f2.tirocinio.mailFromTemplate.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
public class ThymeConfig {

	@Bean
	ClassLoaderTemplateResolver resolver() {

		ClassLoaderTemplateResolver res = new ClassLoaderTemplateResolver();

		res.setPrefix("templates/");
		res.setSuffix(".html");
		res.setTemplateMode(TemplateMode.HTML);
		res.setCharacterEncoding("UTF-8");

		return res;

	}

	@Bean
	SpringTemplateEngine engine() {

		SpringTemplateEngine eng = new SpringTemplateEngine();
		eng.setTemplateResolver(resolver());

		return eng;

	}
}
