package com.tal.couponsproj.exeptions;
import com.tal.couponsproj.enums.errorType;

/**
 * an Exception that represent all the project exceptions
 * we need this exception to know if it was a coupon project exeption
 * and not an run tume  or external exeption
 * 
 * @author user
 *
 */
@SuppressWarnings("serial")
public class CouponException extends Exception{
	private errorType error;
	
	
	public CouponException ()
	{
		super();
	}
	
	public CouponException(String message)
	{
		super(message);
	}

	//only if we initiate the exception throw
	public CouponException(String message,errorType error)
	{
		super(message);
		this.error= error;
	}
	
	
	//only if we wrap up exception
	public CouponException (Exception e,String message,errorType error)
	{
		super(message,e);
		this.error= error;
	}
	
	public errorType getErrorType()
	{
		return this.error;
	}
	
}


