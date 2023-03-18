package com.springjpa.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.springjpa.scheduler.service.SchedulerService;

@Service
@Component
public class SchedulerTask {
	@Autowired
	SchedulerService schedulerService;

	@Scheduled(cron = "${pg.cron.expression.oneminute}")
	public void updateStateDroneEvery1minute() {
		try {
			System.out.println("Scheduller Update Packet everyMinutes........ ");
			schedulerService.updateStateDroneEvery1minute();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Scheduled(cron = "${pg.cron.expression.twominute}")
	public void updateStateDroneEvery2minute() {
		try {
			System.out.println("Scheduller Update Packet twoMinutes........ ");
			//schedulerService.updateStateDroneEvery2minute();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Scheduled(cron = "${pg.cron.expression.threeminute}")
	public void updateStateDroneEvery3minute() {
		try {
			System.out.println("Scheduller Update Packet threeMinutes........ ");
			//schedulerService.updateStateDroneEvery3minute();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Scheduled(cron = "${pg.cron.expression.fourminute}")
	public void updateStateDroneEvery4minute() {
		try {
			System.out.println("Scheduller Update Packet fourMinutes........ ");
			//schedulerService.updateStateDroneEvery4minute();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Scheduled(cron = "${pg.cron.expression.fiveminute}")
	public void updateStateDroneEvery5minute() {
		try {
			System.out.println("Scheduller Update Packet fiveMinutes........ ");
			//schedulerService.updateStateDroneEvery5minute();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
