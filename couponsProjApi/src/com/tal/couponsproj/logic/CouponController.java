package com.tal.couponsproj.logic;
import com.tal.couponsproj.beans.Coupon;
import com.tal.couponsproj.dao.CouponDao;
import com.tal.couponsproj.dao.CustomerDao;
import com.tal.couponsproj.enums.couponType;
import com.tal.couponsproj.enums.errorType;

import java.util.Collection;

import com.tal.couponsproj.interfaces.ICoupon;
import com.tal.couponsproj.utils.FinalsClass;
import com.tal.couponsproj.validations.CouponValidations;
import com.tal.couponsproj.exeptions.*;

/*
 * Class : Coupons Controller
 * Aouther : Tal Hakmon
 * Version : 1.0
 * Date : 11.9.17
 */

public class CouponController implements ICoupon{
	
	private CouponDao couponDao= new CouponDao();
	private CustomerDao customerDao= new CustomerDao();
	
	/*
	 * Public function: is coupon Exicst
	 * check if the coupon is excist in DB
	 * 
	 * Input: company name 
	 * 
	 * returns true is coupon is exicst or false if it isnt
	 */
	@Override
	public boolean isCouponExistsByName (String couponName ) throws CouponException
	{
		//Check for mandatory fields
		if(couponName == null)
			throw new BuisnesException(FinalsClass.COUPON_MISSING_TITLE_FIELD,errorType.INPUT_ERROR);
		
		//Validate input
		CouponValidations.validateCouponTitle(couponName);;
		
		//Logic
		return couponDao.isCouponExistsByName(couponName);
	}
	
	/*
	 * Public function: create coupon
	 * create a new coupon
	 * 
	 * Input: coupon
	 * 
	 */
	@Override
	public void createCoupon (Coupon coupon) throws CouponException
	{
		//validate mandatory fields
		CouponValidations.ValidateMandatoryFieldsCreate(coupon);
		
		//input validations
		CouponValidations.validateCouponTitle(coupon.getTitle());
		CouponValidations.validateDate(coupon.getStartDate());
		CouponValidations.validateDate(coupon.getEndDate());
		CouponValidations.validateAmount(coupon.getAmount());
		CouponValidations.validateCouponType(coupon.getType());
		CouponValidations.validateCouponMassage(coupon.getMassage());
		CouponValidations.validateCouponPrice(coupon.getPrice());
		CouponValidations.validateCouponImage(coupon.getImage());
		CouponValidations.validateCouponCompanyRef(coupon.getCompanyRef());
		
		
		//check if the end date and the start date are in order
		CouponValidations.checkDateOrders(coupon.getStartDate(),coupon.getEndDate());
		
		//check if the end date is before today
		CouponValidations.checkExpieryCoupon(coupon.getEndDate());
		
		//check if the coupon title is already excist
		if(couponDao.isCouponExistsByName(coupon.getTitle()))
			throw new BuisnesException(FinalsClass.COUPON_ALREADY_EXCIST,errorType.BUISNESS_ERROR);
		
		//create the coupon
		couponDao.createCoupon(coupon);
		
	}

	/*
	 * Public function: remove coupon
	 * remove a coupon
	 * 
	 * Input: coupon id
	 * 
	 */
	@Override
	public void removeCoupon(long couponId) throws CouponException {
		
		//Check if the compnay ID is not excist
		if(couponDao.getCouponByID(couponId) == null)
			throw new BuisnesException(FinalsClass.NO_COUPON_FOUND,errorType.BUISNESS_ERROR);
		
		//delete all the coupons from the purchase history
		couponDao.removeCustomerCouponByCoupn(couponId);
		
		//Delete the company
		couponDao.removeCoupon(couponId);
				
	}
	
	/*
	 * Public function: update coupon
	 * update a coupon
	 * 
	 * Input: coupon
	 * 
	 */
	@Override
	public void updateCoupon(Coupon coupon) throws CouponException {
		// only the end date and price can be updated
	
		//check for mandatory input
		CouponValidations.checkForMandatoryFieldUpdateCoupon(coupon);
			
		//Validate input
		CouponValidations.validateDate(coupon.getEndDate());
		CouponValidations.validateDate(coupon.getStartDate());
		CouponValidations.validateCouponPrice(coupon.getPrice());
		
		//check if the end date and the start date are in order
		CouponValidations.checkDateOrders(coupon.getStartDate(),coupon.getEndDate());
		
		//check if the end date is before today
		CouponValidations.checkExpieryCoupon(coupon.getEndDate());
		
		//check if the coupon is exsict
		if(couponDao.isCouponExistsByName(coupon.getTitle())==false)
			throw new BuisnesException(FinalsClass.NO_COUPON_FOUND,errorType.BUISNESS_ERROR);	
				
		//update the coupon
		couponDao.updateCoupon(coupon);
		
	}

	/*
	 * Public function: get all the coupons
	 * retrive all the coupons 
	 * 
	 * Input: no input
	 * 
	 */
	@Override
	public Collection<Coupon> getAllCoupon() throws CouponException {
		return couponDao.getAllCoupon();
	}

	/*
	 * Public function: get coupon by coupon type
	 * retrive all the coupons by coupon type 
	 * 
	 * Input: coupon type
	 * 
	 */
	@Override
	public Collection<Coupon> getCouponByType(couponType type) throws CouponException {
		
		//check for mandatory fields
		CouponValidations.checkForMandatoryFieldType(type);
		
		//validate input
		CouponValidations.validateCouponType(type);
		
		//logic
		return couponDao.getCouponByType(type);
	}

	/*
	 * Public function: get coupon by company
	 * retrive all the coupons by company
	 * 
	 * Input: company ID
	 * 
	 */
	@Override
	public Collection<Coupon> getCouponsByCompany(long companyId) throws CouponException {

		//logic
		return couponDao.getCouponsByCompany(companyId);
		
	}

	/*
	 * Public function: get coupon by customer id
	 * retrive all the coupons by customer id
	 * 
	 * Input: customer ID
	 * 
	 */
	@Override
	public Collection<Coupon> getCouponsByCustomer(long customerId) throws CouponException {

		//logic
		return couponDao.getCouponsByCustomer(customerId);
				
	}

	/*
	 * Public function: get coupon by id
	 * retrive coupon by id
	 * 
	 * Input: coupon id
	 * 
	 */
	@Override
	public Coupon getCouponByID(long couponId) throws CouponException {
		
		//logic
		return couponDao.getCouponByID(couponId);
			
	}
	
	/*
	 * Public function: get coupon by name
	 * retrive coupon by name
	 * 
	 * Input: coupon name
	 * 
	 */
	@Override
	public Coupon getCouponByName(String couponName) throws CouponException {
		
		//Check for mandatory input
		CouponValidations.checkForMandatoryFieldCouponName(couponName);
		
		//validate the input
		CouponValidations.validateCouponTitle(couponName);
		
		//logic
		return couponDao.getCouponByName(couponName);
	}
	
	/*
	 * Public function: get coupons by expieretion Day 
	 * retrieve all the coupons by expiry date and company id 
	 * 
	 * Input: companyID,expiery date
	 * 
	 */
	@Override
	public Collection<Coupon> getCouponsByCompanyAndExpieretionDay(long companyId, String endDate) throws CouponException {
		//Check for mandatory input
		CouponValidations.checkForMandatoryFieldEndDate(endDate);
		
		//validate input
		CouponValidations.validateDate(endDate);
		
		//logic
		return couponDao.getCouponsByCompanyAndExpieretionDay(companyId,endDate);
			
	}

	/*
	 * Public function: get coupons by type and copmany
	 * retrieve all the coupons by type and company
	 * 
	 * Input: companyID,coupon type
	 * 
	 */
	@Override
	public Collection<Coupon> getCouponsByCompanyAndType(long companyId, couponType type) throws CouponException {
		
		//check mandatory input
		CouponValidations.checkForMandatoryFieldType(type);
		
		//validate input
		CouponValidations.validateCouponType(type);
		
		//logic
		return couponDao.getCouponsByCompanyAndType(companyId,type);
	}

	/*
	 * Public function: get coupons by max price 
	 * retrieve all the coupons by max price and company id 
	 * 
	 * Input: companyID,price
	 * 
	 */
	@Override
	public Collection<Coupon> getCouponsByCompanyAndMaxPrice(long companyId, double price) throws CouponException {
		
		//validate input
		CouponValidations.validateCouponPrice(price);
		
		//logic
		return couponDao.getCouponsByCompanyAndMaxPrice(companyId,price);
			
	}
	
	/*
	 * Public function: get coupon by coupon type
	 * retrive all the coupons by coupon type 
	 * 
	 * Input: coupon type
	 * 
	 */
	@Override
	public Collection<Coupon> getHistoryCouponsByType(long customerId, couponType type) throws CouponException {
		
		//Check for mandatory input
		CouponValidations.checkForMandatoryFieldType(type);
		
		//validate input
		CouponValidations.validateCouponType(type);
		
		//logic
		return couponDao.getHistoryCouponsByType(customerId,type);
	}

	/*
	 * Public function: get History By price
	 * retrieve thre purche history of the customer by coupon price
	 * 
	 * Input: companyID,coupon price 
	 * 
	 */
	@Override
	public Collection<Coupon> getHistoryCouponsByPrice(long customerId, double price) throws CouponException {

				//validate input
				CouponValidations.validateCouponPrice(price);
				
				//logic
				return couponDao.getHistoryCouponsByPrice(customerId,price);
	}

	/*
	 * Public function: update customer coupon
	 * update the join table of coupons and customers
	 * 
	 * Input: customer ID, coupon ID
	 * 
	 */
	@Override
	public void updateCustomerCoupon(long customerId, long couponId) throws CouponException {
		
		//check if the coupon is not exist we throw exception
		if(couponDao.getCouponByID(couponId) == null)
				throw new BuisnesException(FinalsClass.NO_COUPON_FOUND,errorType.BUISNESS_ERROR);
		
		//check if the customer is not excist
		if(customerDao.getCustomerByID(customerId)==null)
			throw new BuisnesException(FinalsClass.NO_CUSTOMER_FOUND,errorType.BUISNESS_ERROR);
		
		//logic
		couponDao.updateCustomerCoupon(customerId, couponId);
	}
	
	/*
	 * Public function: update coupon quantity
	 * update quantity of coupon
	 * 
	 * Input: coupon ID quantity - new quantity of th coupon
	 * 
	 */
	@Override
	public void updateCouponQuantity(long couponID, int quantity) throws CouponException {
		
		//check if the coupon is not exist we throw exception
		if(couponDao.getCouponByID(couponID) == null)
				throw new BuisnesException(FinalsClass.NO_COUPON_FOUND,errorType.BUISNESS_ERROR);
		
		
		//logic
		couponDao.updateCouponQuantity(couponID, quantity);
	}

	
	/*
	 * Public function: remove customer coupon by coupon
	 * remove from the join table of coupons and customers
	 * 
	 * Input: coupon ID
	 * 
	 */
	@Override
	public void removeCustomerCouponByCoupn(long couponId) throws CouponException {
		//check if the coupon is not exist we throw exception
		if(couponDao.getCouponByID(couponId) == null)
				throw new BuisnesException(FinalsClass.NO_COUPON_FOUND,errorType.BUISNESS_ERROR);
		
		//logic
		couponDao.removeCustomerCouponByCoupn(couponId);
		
	}

	/*
	 * Public function: remove customer coupon by customer
	 * remove from the join table of coupons and customers
	 * 
	 * Input: customer ID
	 * 
	 */
	@Override
	public void removeCustomerCouponByCustomer(long customerId) throws CouponException {
		//check if the customer is not excist
		if(customerDao.getCustomerByID(customerId)==null)
			throw new BuisnesException(FinalsClass.NO_CUSTOMER_FOUND,errorType.BUISNESS_ERROR);
		
		//logic
		couponDao.removeCustomerCouponByCustomer(customerId);
		
	}

	
	/*
	 * Public function: buy coupon
	 * buy ont coupon by a customer ( customer Id) ,
	 * coupon ( coupon Id)
	 * 
	 * Input: customerId,CouponId
	 * 
	 */
	public void buyCoupon(long customerId,long couponId) throws CouponException
	{
	
		Coupon coupon = couponDao.getCouponByID(couponId);
		
		//check if the coupon is not exist we throw exception
		if(coupon == null)
				throw new BuisnesException(FinalsClass.NO_COUPON_FOUND,errorType.BUISNESS_ERROR);
		
		//check if the customer is not excist
		if(customerDao.getCustomerByID(customerId)==null)
			throw new BuisnesException(FinalsClass.NO_CUSTOMER_FOUND,errorType.BUISNESS_ERROR);
		
		//check if the amount of the coupons is enough
		CouponValidations.checkCouponAmount(coupon.getAmount(),1);
		
		//Check if the customer already bought this coupon
		CouponValidations.checkIfAlreadyBoughtCoupon(customerId,couponId);
		
		//check for expiration coupon
		CouponValidations.checkExpieryCoupon(coupon.getEndDate());
		
		//buy the coupon
		buyCouponAction(couponId,couponId);
		
	}
	
	/*
	 * Public function: buy coupon action
	 * buy ont coupon by a customer ( customer Id) ,
	 * coupon ( coupon Id)
	 * 
	 * Input: customerId,CouponId
	 * 
	 */
	private void buyCouponAction(long customerId,long couponId) throws CouponException
	{
		CouponDao couponDao = new CouponDao();
		
		Coupon coupon = couponDao.getCouponByID(couponId);
		int newAmount = coupon.getAmount() -1 ; 
		couponDao.updateCouponQuantity(couponId, newAmount);
		couponDao.updateCustomerCoupon(customerId, couponId);
	}

	/**
	 * Retrieve all the expierd coupons
	 */
	@Override
	public Collection<Coupon> getAllExpieryCoupon() throws CouponException {
		return couponDao.getAllExpieryCoupon();
	}
	
	
	
}
