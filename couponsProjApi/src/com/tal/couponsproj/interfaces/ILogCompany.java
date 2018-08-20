package com.tal.couponsproj.interfaces;

import com.tal.couponsproj.beans.LogCompany;
import com.tal.couponsproj.exeptions.CouponException;

public interface ILogCompany {

	public void insertToCompenyLog (LogCompany logcompany) throws CouponException;
}
