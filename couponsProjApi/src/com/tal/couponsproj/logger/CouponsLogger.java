package com.tal.couponsproj.logger;

import java.util.logging.FileHandler;
import java.util.logging.Logger;

import com.tal.couponsproj.enums.errorType;
import com.tal.couponsproj.exeptions.WriteToLogException;
import com.tal.couponsproj.utils.FinalsClass;

/**
 * Class of logger forthe project 
 * The class is singleTon so its only one logger
 * 
 * @author Tal Hakmon
 *
 */
public class CouponsLogger {
	
	private static CouponsLogger instance = null;
	private static Logger logger;
	
	private CouponsLogger() throws WriteToLogException
	{
		try
		{
		boolean append = true;
		FileHandler handler = new FileHandler("Logs.log", append);
		logger = Logger.getLogger("com.tal.couponsproj");
		logger.addHandler(handler);
		}
		catch (Exception e)
		{
			throw new WriteToLogException(e,FinalsClass.UNAVAILABLE_SERVICE,errorType.INTERNAL_ERROR);
		}
	}
	
	public static CouponsLogger getInstance() throws WriteToLogException
	{
		if(instance == null) 
	         instance = new CouponsLogger();
	    return instance;
	}
	
	public void write(String massage)
	{
		logger.warning(massage);
	}
	
		

}
