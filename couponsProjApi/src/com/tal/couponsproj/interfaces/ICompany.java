package com.tal.couponsproj.interfaces;

import java.util.Collection;

import com.tal.couponsproj.beans.Company;
import com.tal.couponsproj.exeptions.CouponException;

public interface ICompany {
	
	public void createCompany (Company company) throws CouponException;
	
	public void removeCompany (long companyId) throws CouponException;
	
	public void updateCompany (Company company) throws CouponException;
	
	public Company getCompanyByID(long companyId) throws CouponException;
	
	public Company getCompanyByName(String companyName) throws CouponException;
	
	public Collection <Company> getAllCompanies() throws CouponException;
	
	public boolean login(String compName,String password) throws CouponException;
}
