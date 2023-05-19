package f2.tirocinio.docky;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.regex.Pattern;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import f2.tirocinio.docky.models.Calculator;

@SpringBootTest
class DockyApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testMultiplication() {
		Calculator c = new Calculator();
		assertEquals(35, c.multiply(5, 7));
	}

	@RepeatedTest(10)
	void testRandomNumber() {
		int r = (int) Math.random() * 101;
		assertTrue(r <= 100);
	}

	@ParameterizedTest
	@ValueSource(strings = { "alberto.ramponi@gmail.com", "giorgio@giorgio.com", "abc123@a123" })
	void testMail(String candidate) {
		assertTrue(Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", candidate));
	}

}
