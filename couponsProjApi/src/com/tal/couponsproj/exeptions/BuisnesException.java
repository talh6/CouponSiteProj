package com.tal.couponsproj.exeptions;
import com.tal.couponsproj.enums.errorType;

/**
 * Exception that we use when we want to notify the client of business error
 * @author Tal Hakmon
 *
 */
@SuppressWarnings("serial")
public class BuisnesException extends CouponException{
	private errorType error;
	private String message;
		
		public BuisnesException ()
		{
			super();
		}
		
		public BuisnesException(String message)
		{
			super(message);
		}

		//only if we initiate the exception throw
		public BuisnesException(String message,errorType error)
		{
			super(message,error);
		}
		
		
		//only if we wrap up exception
		public BuisnesException (Exception e,String message,errorType error)
		{
			super(e,message,error);
		}
		
		public errorType getErrorType()
		{
			return this.error;
		}
		
		
	}



	
