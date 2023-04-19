package f2.tirocinio.social.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import f2.tirocinio.social.daos.UtenteDao;

@Controller
public class ScheduledController {

	@Autowired
	UtenteDao uDao;

	@Scheduled(cron = "0 3 17 * * *")
	public void reactivateUsers() {
		uDao.reactivateUsers(System.currentTimeMillis());
	}
}
