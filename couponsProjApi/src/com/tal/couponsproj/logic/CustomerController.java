package com.tal.couponsproj.logic;

import java.util.Collection;

import com.tal.couponsproj.beans.Customer;
import com.tal.couponsproj.dao.CouponDao;
import com.tal.couponsproj.dao.CustomerDao;
import com.tal.couponsproj.enums.errorType;
import com.tal.couponsproj.exeptions.BuisnesException;
import com.tal.couponsproj.exeptions.CouponException;
import com.tal.couponsproj.interfaces.ICustomer;
import com.tal.couponsproj.utils.FinalsClass;
import com.tal.couponsproj.validations.CustomerValidations;

/*
 * Class : Customer Controller
 * Aouther : Tal Hakmon
 * Version : 1.0
 * Date : 11.9.17
 */

public class CustomerController implements ICustomer{
	private CustomerDao customerDao = new CustomerDao();
	private CouponDao couponDao = new CouponDao();

	
	/*
	 * Public function: create customer
	 * create a new customer
	 * 
	 * Input: customer
	 * 
	 */
	@Override
	public void createCustomer(Customer customer) throws CouponException {
		
		//check for mandatory input
		CustomerValidations.ValidateMandatoryFieldsCreate(customer);
		
		//input validations
		CustomerValidations.validateCustomerName(customer.getCustName());
		CustomerValidations.validateCustomerUsername(customer.getUserName());
		CustomerValidations.validateCustomerPassword(customer.getPassword());
		
		
		//check if the customer name is already excist
		if(customerDao.getCustomerByName(customer.getCustName())!=null)
			throw new BuisnesException(FinalsClass.CUSTOMER_ALREADY_EXCIST,errorType.BUISNESS_ERROR);
		
		//create new customer
		customerDao.createCustomer(customer);
		
	}

	/*
	 * Public function: remove customer
	 * remove a customer
	 * 
	 * Input: customer id
	 * 
	 */
	@Override
	public void removeCustomer(long customerId) throws CouponException {
		
		//check if the customer is not excist
		if(customerDao.getCustomerByID(customerId)==null)
			throw new BuisnesException(FinalsClass.NO_CUSTOMER_FOUND,errorType.BUISNESS_ERROR);
		
		//delete purshe coupon history by customer
		couponDao.removeCustomerCouponByCustomer(customerId);
		
		//delete customer
		customerDao.removeCustomer(customerId);
	}

	/*
	 * Public function: update customer
	 * update a customer
	 * 
	 * Input: customer
	 * 
	 */
	@Override
	public void updateCustomer(Customer customer) throws CouponException {
			
		//check for mandatory input
		CustomerValidations.ValidateMandatoryFieldsUpdate(customer);
		
		//check if the customer is not excist
		if(customerDao.getCustomerByID(customer.getId())==null)
			throw new BuisnesException(FinalsClass.NO_CUSTOMER_FOUND,errorType.BUISNESS_ERROR);
				
		//validate input fields
		CustomerValidations.validateCustomerUsername(customer.getUserName());
		CustomerValidations.validateCustomerPassword(customer.getPassword());
		
		//logic
		customerDao.updateCustomer(customer);
	}

	/*
	 * Public function: get all the customers
	 * retrive all the customers 
	 * 
	 * Input: no input
	 * 
	 */
	@Override
	public Collection<Customer> getAllCustomers() throws CouponException {
		return customerDao.getAllCustomers();
	}

	/*
	 * Public function: login
	 * return true if the login information is excist else return false
	 * 
	 * Input: customer name , password
	 * 
	 */
	@Override
	public boolean login(String username, String password) throws CouponException {
		
		//check for mandatory input
		CustomerValidations.ValidateMandatoryFieldsLogin(username, password);
		
		//validate input fields
		CustomerValidations.validateCustomerUsername(username);
		CustomerValidations.validateCustomerPassword(password);
		
		//logic
		return customerDao.login(username,password);
	}

	/*
	 * Public function: get customer by id
	 * retrive customer by id
	 * 
	 * Input: customer id
	 * 
	 */
	@Override
	public Customer getCustomerByID(long customerId) throws CouponException {
		
		//logic
		return customerDao.getCustomerByID(customerId);
	}

	/*
	 * Public function: get customer by name
	 * retrive customer by name
	 * 
	 * Input: customer name
	 * 
	 */
	@Override
	public Customer getCustomerByName(String customerName) throws CouponException {
		
		//check for mandatory input
		CustomerValidations.ValidateMandatoryFieldsCustomerName(customerName);
	
		//Validate input filds
		CustomerValidations.validateCustomerName(customerName);
	
		//logic
		return customerDao.getCustomerByName(customerName);
	}

	/*
	 * Public function: get customer by username
	 * retrive customer by username
	 * 
	 * Input: customer username
	 * 
	 */
	@Override
	public Customer getCustomerByUsername(String username) throws CouponException {
		
		//check for mandatory input
		CustomerValidations.ValidateMandatoryFieldsCustomerUserName(username);
		
		//Validate input filds
		CustomerValidations.validateCustomerUsername(username);
		
		//logic
		return customerDao.getCustomerByUsername(username);
	}

}
