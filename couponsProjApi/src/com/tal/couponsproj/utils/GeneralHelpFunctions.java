package com.tal.couponsproj.utils;

import com.tal.couponsproj.exeptions.WriteToLogException;
import com.tal.couponsproj.logger.CouponsLogger;

/**
 * class of functions that are in use of all the classes
 * 
 * @author Tal Hakmon
 *
 */
public class GeneralHelpFunctions {

	//Help functions
	public static void writeToLog(String massage)
		{
			try
			{
				CouponsLogger logicLogger = CouponsLogger.getInstance();
				logicLogger.write(massage);
			}
			catch(WriteToLogException e)
			{
				//Do nothing write to log is not a mandatory action 
			}
			
		}
		
	public static boolean checkForInjection(String str) {
				 String[] forbitenInput = {"SELECT","DROP","INSERT","DELETE","'","=", "*", "FROM","WHERE"};
					
				 for(String input:forbitenInput)
				 {
					 if(str.equals(input) || str.equals(input.toLowerCase()))
					 {
						 writeToLog(FinalsClass.SQL_INJECTION_WARNING);
						 return false;
					 }
						 
				 }
				
				 return true ;
			}

}
