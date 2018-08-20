package com.tal.couponsproj.exeptions;

import com.tal.couponsproj.enums.errorType;

/**
 * This class is for specific exception ,
 * if the logger fail to run,
 * we need to know if the logger fail in order to ignore it
 * because its not mandatory action
 * 
 * @author Tal Hakmon
 *
 */
@SuppressWarnings("serial")
public class WriteToLogException extends CouponException{
	private errorType error;
	
	public WriteToLogException ()
	{
		super();
	}
	
	public WriteToLogException(String message)
	{
		super(message);
	}

	//only if we initiate the exception throw
	public WriteToLogException(String message,errorType error)
	{
		super(message,error);
	}
	
	
	//only if we wrap up exception
	public WriteToLogException (Exception e,String message,errorType error)
	{
		super(e,message,error);
	}
	
	public errorType getErrorType()
	{
		return this.error;
	}


}
