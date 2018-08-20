package com.tal.couponsproj.validations;

import com.tal.couponsproj.beans.Customer;
import com.tal.couponsproj.enums.errorType;
import com.tal.couponsproj.exeptions.BuisnesException;
import com.tal.couponsproj.utils.FinalsClass;
import com.tal.couponsproj.utils.GeneralHelpFunctions;

/**
 * class of calidations for customer controller
 * 
 * @author Tal Hakmon
 *
 */
public class CustomerValidations {


	//customer validations
	
	/**
	 * Validate customer name field
	 * 
	 * @param name
	 * @throws BuisnesException
	 */
	public static void validateCustomerName(String name) throws BuisnesException
	{
		if(!GeneralHelpFunctions.checkForInjection(name))
			throw new BuisnesException(FinalsClass.ILIGAL_CUSTOMER_NAME_FIELD,errorType.INPUT_ERROR);
		if(name.length()<2)
			throw new BuisnesException(FinalsClass.ILIGAL_CUSTOMER_NAME_FIELD,errorType.INPUT_ERROR);
	}
	
	/**
	 * Validate customer user name
	 * 
	 * @param username
	 * @throws BuisnesException
	 */
	public static void validateCustomerUsername(String username) throws BuisnesException
	{
		if(!GeneralHelpFunctions.checkForInjection(username))
			throw new BuisnesException(FinalsClass.ILIGAL_CUSTOMER_USERNAME_FIELD,errorType.INPUT_ERROR);
		if(username.length()<2)
			throw new BuisnesException(FinalsClass.ILIGAL_CUSTOMER_USERNAME_FIELD,errorType.INPUT_ERROR);
	}
	
	/**
	 * validagte customer password
	 * 
	 * @param password
	 * @throws BuisnesException
	 */
	public static void validateCustomerPassword(String password) throws BuisnesException
	{
		if(!GeneralHelpFunctions.checkForInjection(password))
			throw new BuisnesException(FinalsClass.ILIGAL_CUSTOMER_PASSWORD_FIELD,errorType.INPUT_ERROR);
		if(password.length()<2)
			throw new BuisnesException(FinalsClass.ILIGAL_CUSTOMER_PASSWORD_FIELD,errorType.INPUT_ERROR);
	}

	/**
	 * check if the customer didnt enter mandatory fields for create customer
	 * 
	 * @param customer
	 * @throws BuisnesException
	 */
	public static void ValidateMandatoryFieldsCreate(Customer customer) throws BuisnesException
	{
		if(customer.getCustName()==null)
			throw new BuisnesException(FinalsClass.CUSTOMER_MISSING_NAME_FIELD,errorType.INPUT_ERROR);
		if(customer.getUserName()==null)
			throw new BuisnesException(FinalsClass.CUSTOMER_MISSING_USERNAME_FIELD,errorType.INPUT_ERROR);
		if(customer.getPassword()==null)
			throw new BuisnesException(FinalsClass.CUSTOMER_MISSING_PASSWORD_FIELD,errorType.INPUT_ERROR);
	}
	
	/**
	 * Check if the customer didnt enter the mandatory fields for update company
	 * 
	 * @param customer
	 * @throws BuisnesException
	 */
	public static void ValidateMandatoryFieldsUpdate(Customer customer) throws BuisnesException
	{
		if(customer.getUserName()==null)
			throw new BuisnesException(FinalsClass.CUSTOMER_MISSING_USERNAME_FIELD,errorType.INPUT_ERROR);
		if(customer.getPassword()==null)
			throw new BuisnesException(FinalsClass.CUSTOMER_MISSING_PASSWORD_FIELD,errorType.INPUT_ERROR);
		
	}

	/**
	 * Check if the customer didnt enter the mandatory fields for login
	 * 
	 * @param username
	 * @param password
	 * @throws BuisnesException
	 */
	public static void ValidateMandatoryFieldsLogin(String username, String password) throws BuisnesException
	{
		if(username==null)
			throw new BuisnesException(FinalsClass.CUSTOMER_MISSING_USERNAME_FIELD,errorType.INPUT_ERROR);
		if(password==null)
			throw new BuisnesException(FinalsClass.CUSTOMER_MISSING_PASSWORD_FIELD,errorType.INPUT_ERROR);
		
	}
	
	/**
	 * Check if the customer didnt enter the mandatory fields customer name
	 * 
	 * @param customerName
	 * @throws BuisnesException
	 */
	public static void ValidateMandatoryFieldsCustomerName(String customerName) throws BuisnesException
	{
		if(customerName==null)
			throw new BuisnesException(FinalsClass.CUSTOMER_MISSING_USERNAME_FIELD,errorType.INPUT_ERROR);
	}

	/**
	 * Check if the customer didnt enter the mandatory fields customer user name
	 * 
	 * @param userName
	 * @throws BuisnesException
	 */
	public static void ValidateMandatoryFieldsCustomerUserName(String userName) throws BuisnesException
	{
		if(userName==null)
			throw new BuisnesException(FinalsClass.CUSTOMER_MISSING_USERNAME_FIELD,errorType.INPUT_ERROR);
	}
}
