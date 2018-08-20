package com.tal.expieryservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.TimerTask;

import com.tal.couponsproj.beans.Coupon;
import com.tal.couponsproj.enums.errorType;
import com.tal.couponsproj.exeptions.BuisnesException;
import com.tal.couponsproj.exeptions.CouponException;
import com.tal.couponsproj.exeptions.WriteToLogException;
import com.tal.couponsproj.logic.CouponController;
import com.tal.expieryservice.logger.ExpieryLogger;
import com.tal.expieryservice.utils.FinalClass;

/**
 * service tread that delete expierd coupons
 * 
 * @author Tal Hakmon
 *
 */
public class ExpieryService extends TimerTask{
	CouponController couponController = new CouponController();
	
	@Override
	public void run() {
		System.out.println(FinalClass.START_TIME+ new Date());
		//writeToLog(FinalClass.START_TIME + new Date());
		
		deleteExpiryCoupons();
		
		System.out.println(FinalClass.END_TIME + new Date());
		//writeToLog(FinalClass.END_TIME + new Date());
	}

    private void deleteExpiryCoupons() {
        try 
        {
    			ArrayList <Coupon> coupons = new ArrayList<Coupon>();
    			
    			//Retrieve all the expiery coupons
    			coupons = (ArrayList<Coupon>) couponController.getAllExpieryCoupon();
    			
    			//if there are no records throw exception
    			if(coupons == null)
    			{
    				System.out.println(FinalClass.NO_RECORDS_FETCH);
    				throw new BuisnesException(FinalClass.NO_RECORDS_FETCH,errorType.BUISNESS_ERROR);
    			}
    			
    			System.out.println(FinalClass.NUMBER_OF_RESOLTS + coupons.size());
    			
    			//Delete  the coupons
    			for(Coupon coupon:coupons)
    			{
    				couponController.removeCoupon(coupon.getId());
    			}
        	} 
       catch(CouponException e)
        {
    	   		//We have to ignore the exception
        }
        
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
