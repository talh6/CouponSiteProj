package com.tal.couponsproj.exeptions;
import com.tal.couponsproj.enums.errorType;

/**
 * we send this exception ,
 * only if we got exception that we dont want to show the client side
 * 
 * @author Tal Hakmon
 *
 */
@SuppressWarnings("serial")
public class GeneralException extends CouponException{
	private errorType error;
	
	public GeneralException ()
	{
		super();
	}
	
	public GeneralException(String message)
	{
		super(message);
	}

	//only if we initiate the exception throw
	public GeneralException(String message,errorType error)
	{
		super(message,error);
	}
	
	
	//only if we wrap up exception
	public GeneralException (Exception e,String message,errorType error)
	{
		super(e,message,error);
	}
	
	public errorType getErrorType()
	{
		return this.error;
	}

	

}
