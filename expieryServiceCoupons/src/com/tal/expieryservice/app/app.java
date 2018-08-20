package com.tal.expieryservice.app;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.tal.couponsproj.exeptions.WriteToLogException;
import com.tal.expieryservice.logger.ExpieryLogger;
import com.tal.expieryservice.service.ExpieryService;
import com.tal.expieryservice.utils.FinalClass;

public class app {
	
	public static void main(String args[]) 
	{	
		TimerTask timerTask = new ExpieryService();
		
		// running timer task as daemon thread
		Timer timer = new Timer(true);
		
		timer.scheduleAtFixedRate(timerTask, FinalClass.TIME_TO_BEGIN, FinalClass.WAIT_UNTILL_MIDNIGHT);
		
		System.out.println(FinalClass.TIMER_STACK_BEGIN + new Date());
        

	}
	
	/**
	 * write to log file massage
	 * 
	 * @param massage
	 */
	public static void writeToLog(String massage)
	{
		try
		{
			ExpieryLogger expieryLogger = ExpieryLogger.getInstance();
			expieryLogger.write(massage);
		}
		catch(WriteToLogException e)
		{
			//Do nothing write to log is not a mandatory action 
		}
		
	}
}
