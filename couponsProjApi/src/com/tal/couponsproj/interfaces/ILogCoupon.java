package com.tal.couponsproj.interfaces;

import com.tal.couponsproj.beans.LogCoupon;
import com.tal.couponsproj.exeptions.CouponException;

public interface ILogCoupon {
	public void insertToCouponLog (LogCoupon logcoupon) throws CouponException;
	
	public void insertToLogCustomerCoupons(long customerID,long CoupomID) throws CouponException;
}
