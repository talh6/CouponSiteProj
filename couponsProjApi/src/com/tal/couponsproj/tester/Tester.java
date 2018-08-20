package com.tal.couponsproj.tester;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.tal.couponsproj.beans.Company;
import com.tal.couponsproj.beans.Coupon;
import com.tal.couponsproj.beans.Customer;
import com.tal.couponsproj.beans.LogCompany;
import com.tal.couponsproj.beans.LogCoupon;
import com.tal.couponsproj.beans.LogCustomer;
import com.tal.couponsproj.dao.CompanyDao;
import com.tal.couponsproj.dao.CouponDao;
import com.tal.couponsproj.dao.CustomerDao;
import com.tal.couponsproj.dao.LogCompanyDao;
import com.tal.couponsproj.dao.LogCouponDao;
import com.tal.couponsproj.dao.LogCustomerDao;
import com.tal.couponsproj.enums.actionType;
import com.tal.couponsproj.enums.couponType;
import com.tal.couponsproj.enums.loginType;
import com.tal.couponsproj.exeptions.CouponException;
import com.tal.couponsproj.logic.CompanyController;
import com.tal.couponsproj.logic.CouponController;
import com.tal.couponsproj.utils.FinalsClass;
import com.tal.couponsproj.utils.loginClass;
import com.tal.couponsproj.validations.CouponValidations;

public class Tester {

	
	public static void main(String[] args) throws SQLException, CouponException, SecurityException, IOException
	{
		try
		{	
			String username = "Avi username2";
			String password = "abc123";
			
			loginClass.login(username, password, loginType.CUSTOMER);
			System.out.println("Successs");
		}
		catch (Exception e)
		{
			String message = e.getMessage();
			System.out.println("the dec is : " + message);
		}
	}
	

}
