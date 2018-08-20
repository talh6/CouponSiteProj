package com.tal.expieryservice.logger;

import java.util.logging.FileHandler;
import java.util.logging.Logger;

import com.tal.couponsproj.enums.errorType;
import com.tal.couponsproj.exeptions.WriteToLogException;
import com.tal.couponsproj.utils.FinalsClass;



public class ExpieryLogger {

	private static ExpieryLogger instance = null;
	private static Logger logger;
	
	private ExpieryLogger() throws WriteToLogException
	{
		try
		{
		boolean append = true;
		FileHandler handler = new FileHandler("Logs.log", append);
		logger = Logger.getLogger("com.tal.expieryservice");
		logger.addHandler(handler);
		}
		catch (Exception e)
		{
			throw new WriteToLogException(e,FinalsClass.UNAVAILABLE_SERVICE,errorType.INTERNAL_ERROR);
		}
	}
	
	public static ExpieryLogger getInstance() throws WriteToLogException
	{
		if(instance == null) 
	         instance = new ExpieryLogger();
	    return instance;
	}
	
	public void write(String massage)
	{
		logger.warning(massage);
	}
}
