package com.tal.couponsproj.interfaces;

import com.tal.couponsproj.beans.LogCustomer;
import com.tal.couponsproj.exeptions.CouponException;

public interface ILogCustomer {

	public void insertToCustomerLog (LogCustomer logcustomer) throws CouponException;
}
