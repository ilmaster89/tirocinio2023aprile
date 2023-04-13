package f2.tirocinio.schedule.controllers;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class ScheduleController {

	int x = 10;

	@Scheduled(fixedRate = 1000)
	public void countDown() {
		if (x >= 0) {
			System.out.println(x);
			x--;
		}
	}

	@Scheduled(fixedDelay = 5000)
	public void countDown2() {
		if (x >= 0) {
			System.out.println("CD2");
		}
	}
	
	@Scheduled(cron = "0 34 10 * * * ")
	public void ciao() {
		System.out.println("SONO UN'OPERAZIONE SCHEDULATA :D");
	}
}
