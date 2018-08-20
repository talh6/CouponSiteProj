package com.tal.couponsproj.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tal.couponsproj.beans.Company;
import com.tal.couponsproj.enums.errorType;
import com.tal.couponsproj.exeptions.BuisnesException;
import com.tal.couponsproj.utils.FinalsClass;
import com.tal.couponsproj.utils.GeneralHelpFunctions;


/**
 * validations class for company
 * 
 * @author Tal Hakmon
 *
 */
public class CompanyValidations {

	//Validations for Companys
	
	/**
	 * This function validate email adress 
	 * 
	 * @param email
	 * @throws BuisnesException
	 */
	public static void validateEmail(String email) throws BuisnesException
	{
		if(!GeneralHelpFunctions.checkForInjection(email))
			throw new BuisnesException(FinalsClass.ILIGAL_MAIL_ADRESS,errorType.INPUT_ERROR);
		
		//Email reg pattern
		final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"	+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		
	    Pattern pattern = Pattern.compile(EMAIL_PATTERN);
	    Matcher matcher = pattern.matcher(email);
		if(matcher.matches()==false)
			throw new BuisnesException(FinalsClass.ILIGAL_MAIL_ADRESS,errorType.INPUT_ERROR);
			
	}
	
	/**
	 * Validate compnay name 
	 * 
	 * @param name
	 * @throws BuisnesException
	 */
	public static void validateCompanyName(String name) throws BuisnesException
	{
		if(!GeneralHelpFunctions.checkForInjection(name))
			throw new BuisnesException(FinalsClass.ILIGAL_COMPANY_NAME,errorType.INPUT_ERROR);
		if(name.length()<2)
			throw new BuisnesException(FinalsClass.ILIGAL_COMPANY_NAME,errorType.INPUT_ERROR);	
	}
	
	/**
	 * Validate password field
	 * 
	 * @param password
	 * @throws BuisnesException
	 */
	public static void validatePassword(String password) throws BuisnesException
	{
		if(!GeneralHelpFunctions.checkForInjection(password))
			throw new BuisnesException(FinalsClass.ILIGAL_PASSWORD,errorType.INPUT_ERROR);
		if(password.length()<4)
			throw new BuisnesException(FinalsClass.ILIGAL_PASSWORD,errorType.INPUT_ERROR);
	}
	
	/**
	 * check if the we didnt got one of the mandatory fields for create company
	 * 
	 * @param company
	 * @throws BuisnesException
	 */
	public static void ValidateForMandatoryFieldsCreateCompany(Company company) throws BuisnesException
	{
		//check if compnay name is missing
		if(company.getCompName()==null)
			throw new BuisnesException(FinalsClass.COMPANY_NAME_MISSING,errorType.INPUT_ERROR);
		
		//check if password is missing
		if(company.getPassword()==null)
			throw new BuisnesException(FinalsClass.COMPANY_PASSWORD_MISSING,errorType.INPUT_ERROR);
		
		if(company.getEmail()==null)
			throw new BuisnesException(FinalsClass.COMPANY_EMAIL_MISSING,errorType.INPUT_ERROR);
	}
	
	/**
	 * check if the we didnt got one of the mandatory fields for update company
	 * 
	 * @param company
	 * @throws BuisnesException
	 */
	public static void ValidateForMandatoryFieldsUpdateCompany(Company company) throws BuisnesException
	{
		//check if company ID is missing
		if(Long.valueOf(company.getId())==null)
			throw new BuisnesException(FinalsClass.COMPANY_ID_MISSING,errorType.INPUT_ERROR);
		
		//check if password is missing
		if(company.getPassword()==null)
			throw new BuisnesException(FinalsClass.COMPANY_PASSWORD_MISSING,errorType.INPUT_ERROR);
		
		if(company.getEmail()==null)
			throw new BuisnesException(FinalsClass.COMPANY_EMAIL_MISSING,errorType.INPUT_ERROR);
		
	}
	
	/**
	 * check if we didnt get mandatory fields for update company
	 * 
	 * @param companyName
	 * @throws BuisnesException
	 */
	public static void ValidateForMandatoryFieldsCompanyName(String companyName) throws BuisnesException
	{
		if(companyName==null)
			throw new BuisnesException(FinalsClass.COMPANY_NAME_MISSING,errorType.INPUT_ERROR);
		
	}

	/**
	 * check if we didnt get mandatory fields for update comany login
	 * 
	 * @param compName
	 * @param password
	 * @throws BuisnesException
	 */
	public static void ValidateForMandatoryFieldsCompanyLogin(String compName, String password) throws BuisnesException
	{
		if(compName==null )
			throw  new BuisnesException(FinalsClass.COMPANY_NAME_MISSING,errorType.INPUT_ERROR);
		if(password==null)
			throw  new BuisnesException(FinalsClass.COMPANY_PASSWORD_MISSING,errorType.INPUT_ERROR);
	}
	
}
