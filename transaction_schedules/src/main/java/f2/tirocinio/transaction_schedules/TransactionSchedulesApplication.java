package f2.tirocinio.transaction_schedules;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TransactionSchedulesApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionSchedulesApplication.class, args);
	}

}
