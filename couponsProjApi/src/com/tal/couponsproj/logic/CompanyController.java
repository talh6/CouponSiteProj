package com.tal.couponsproj.logic;

import java.util.ArrayList;
import java.util.Collection;

import com.tal.couponsproj.beans.Company;
import com.tal.couponsproj.beans.Coupon;
import com.tal.couponsproj.dao.CompanyDao;
import com.tal.couponsproj.dao.CouponDao;
import com.tal.couponsproj.enums.errorType;
import com.tal.couponsproj.exeptions.BuisnesException;
import com.tal.couponsproj.exeptions.CouponException;
import com.tal.couponsproj.interfaces.ICompany;
import com.tal.couponsproj.utils.FinalsClass;
import com.tal.couponsproj.validations.CompanyValidations;


/*
 * Class : Company Dao
 * Aouther : Tal Hakmon
 * Version : 1.0
 * Date : 11.9.17
 */

public class CompanyController implements ICompany{
	private CompanyDao companyDao = new CompanyDao();
	private CouponDao couponDao = new CouponDao();
	

	/*
	 * Public function: create company
	 * create a new company
	 * 
	 * Input: company
	 * 
	 */
	@Override
	public void createCompany(Company company) throws CouponException {
		
		//check for mandatory input
		CompanyValidations.ValidateForMandatoryFieldsCreateCompany(company);
		
		//input Validations
		CompanyValidations.validateEmail(company.getEmail());
		CompanyValidations.validateCompanyName(company.getCompName());
		CompanyValidations.validatePassword(company.getPassword());
		
		//Check if the company name os already excist
		if(companyDao.getCompanyByName(company.getCompName())!=null)
			throw new BuisnesException(FinalsClass.COMPANY_NAME_ALREADY_EXIST,errorType.BUISNESS_ERROR);
		
		//Create a new company after validation
		companyDao.createCompany(company);
		
	}

	/*
	 * Public function: remove company
	 * remove a company
	 * 
	 * Input: company id
	 * 
	 */
	@Override
	public void removeCompany(long companyId) throws CouponException {
		
		//Check if the compnay ID is not excist
		if(companyDao.getCompanyByID(companyId) == null)
			throw new BuisnesException(FinalsClass.NO_COMPANY_FOUND,errorType.BUISNESS_ERROR);
		
		removeCompanyAction(companyId);

	}
	
	/*
	 * private function: remove company
	 * remove a company
	 * 
	 * Input: company id
	 * 
	 */
	private void removeCompanyAction(long companyId) throws CouponException
	{
		//Delete coupon of the same company 
		ArrayList <Coupon> coupons = new  ArrayList<Coupon>();
		coupons = (ArrayList<Coupon>) couponDao.getCouponsByCompany(companyId);
		
		if(coupons !=null) {
			for(Coupon coupon:coupons)
			{
				couponDao.removeCoupon(coupon.getId());
				couponDao.removeCustomerCouponByCoupn(coupon.getId());
			}
		}
		//Delete the company
		companyDao.removeCompany(companyId);	
	}

	/*
	 * Public function: update company
	 * update a company
	 * 
	 * Input: company
	 * 
	 */
	@Override
	public void updateCompany(Company company) throws CouponException {
		//Check for mandatory input 
		CompanyValidations.ValidateForMandatoryFieldsUpdateCompany(company);
		
		//Validate input values
		CompanyValidations.validatePassword(company.getPassword());
		CompanyValidations.validateEmail(company.getEmail());
		
		
		//Logic
		companyDao.updateCompany(company);
	}

	/*
	 * Public function: get all the companies
	 * retrive all the companies 
	 * 
	 * Input: no input
	 * 
	 */
	@Override
	public Collection<Company> getAllCompanies() throws CouponException {
		return companyDao.getAllCompanies();
	}

	/*
	 * Public function: login
	 * return true if the login information is excist else return false
	 * 
	 * Input: company name , password
	 * 
	 */
	@Override
	public boolean login(String compName, String password) throws CouponException {
		
		//check for mandatory fields
		CompanyValidations.ValidateForMandatoryFieldsCompanyLogin(compName, password);
		
		//Validate input fields
		CompanyValidations.validateCompanyName(compName);
		CompanyValidations.validatePassword(password);
	
		//Logic for login method
		return companyDao.login(compName, password);
	}

	/*
	 * Public function: get customer by id
	 * retrive customer by id
	 * 
	 * Input: customer id
	 * 
	 */
	@Override
	public Company getCompanyByID(long companyId) throws CouponException {
		
		//Logic
		return companyDao.getCompanyByID(companyId);
		
	}

	/*
	 * Public function: get company by name
	 * retrive company by name
	 * 
	 * Input: company name
	 * 
	 */
	@Override
	public Company getCompanyByName(String companyName) throws CouponException {
		
		//check for mandatory input
		CompanyValidations.ValidateForMandatoryFieldsCompanyName(companyName);
		
		//Validate input
		CompanyValidations.validateCompanyName(companyName);
		
		//Logic
		return companyDao.getCompanyByName(companyName);
		
	}


}
