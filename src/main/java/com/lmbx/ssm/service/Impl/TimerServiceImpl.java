package com.lmbx.ssm.service.Impl;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Service;

import com.lmbx.ssm.service.TimerService;
@Service
public class TimerServiceImpl implements TimerService {

	//@Scheduled(cron="0/5 * * * * ? ")
	@Override
	public void Text() {
		// TODO Auto-generated method stub
		System.out.println("text timer");
	}

}
