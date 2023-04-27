package f2.tirocinio.transaction_schedules.controllers;

import org.springframework.stereotype.Controller;

@Controller
public class ScheduleController {

	int x = 10;
	Long oldTime = 0L;

//	@Scheduled(fixedRate = 2000)
//	public void scriviCiao() {
//		System.out.println("Ciao!");
//	}

//	@Scheduled(fixedRate = 1000)
//	public void countDown() {
//		if (x >= 0) {
//			System.out.println(x);
//			x--;
//		}
//	}

//	@Scheduled(fixedDelay = 2000)
//	public void delay() {
//		if (oldTime == 0) {
//			oldTime = System.currentTimeMillis();
//		}
//
//		Long newTime = System.currentTimeMillis();
//		System.out.println(newTime - oldTime);
//		oldTime = newTime;
//	}
//
//	@Scheduled(cron = "0 48 10 * * * ")
//	public void cronOp() {
//		System.out.println("Sono una cron operation! :D");
//	}
}
