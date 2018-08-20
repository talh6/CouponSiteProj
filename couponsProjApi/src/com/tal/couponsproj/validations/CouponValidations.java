package com.tal.couponsproj.validations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


import com.tal.couponsproj.beans.Coupon;
import com.tal.couponsproj.dao.CouponDao;
import com.tal.couponsproj.enums.couponType;
import com.tal.couponsproj.enums.errorType;
import com.tal.couponsproj.exeptions.BuisnesException;
import com.tal.couponsproj.exeptions.CouponException;
import com.tal.couponsproj.logger.CouponsLogger;
import com.tal.couponsproj.utils.FinalsClass;
import com.tal.couponsproj.utils.GeneralHelpFunctions;


/**
 * class of Validations checks for coupons 
 * 
 * @author Tal Hakmon
 *
 */
public class CouponValidations {
	
		
		//Validations for Coupons
		
		//input validations 
	
		/**
		 * Validate coupon title field
		 * 
		 * @param title
		 * @throws BuisnesException
		 */
		public static void validateCouponTitle(String title) throws BuisnesException
		{
			if(!GeneralHelpFunctions.checkForInjection(title))
				throw new BuisnesException(FinalsClass.ILIGAL_COUPON_TITLE_FIELD,errorType.INPUT_ERROR);
			if(title.length()<2)
				throw new BuisnesException(FinalsClass.ILIGAL_COUPON_TITLE_FIELD,errorType.INPUT_ERROR);
		}
		
		/**
		 * Validate amount field
		 * 
		 * @param amount
		 * @throws BuisnesException
		 */
		public static void validateAmount(int amount) throws BuisnesException
		{
			if(amount < 1)
				throw new BuisnesException(FinalsClass.ILIGAL_COUPON_AMOUNT_FIELD,errorType.INPUT_ERROR);
		}
		
		/**
		 * validate string that represent date 
		 * 
		 * @param stringDate
		 * @throws BuisnesException
		 */
		public static void validateDate(String stringDate) throws BuisnesException
		{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
			try {
				@SuppressWarnings("unused")
				Date date = formatter.parse(stringDate);
			}
			catch(ParseException e)
			{
				throw new BuisnesException(FinalsClass.ILIGAL_COUPON_DATE_FIELD,errorType.INPUT_ERROR);
			}
		}
		
		/**
		 * validate coupon type 
		 *
		 * @param type
		 * @throws BuisnesException
		 */
		public static void validateCouponType(couponType type) throws BuisnesException
		{
			if(!couponType.isMember(type.toString()))
				throw new BuisnesException(FinalsClass.ILIGAL_CCOUPON_TYPE_FIELD,errorType.INPUT_ERROR);
		}
		
		/**
		 * Validate coupon massage field 
		 * 
		 * @param massage
		 * @throws BuisnesException
		 */
		public static void validateCouponMassage(String massage) throws BuisnesException
		{
			if(!GeneralHelpFunctions.checkForInjection(massage))
				throw new BuisnesException(FinalsClass.ILIGAL_COUPON_MASSAGE_FIELD,errorType.INPUT_ERROR);
			if(massage.length()<2)
				throw new BuisnesException(FinalsClass.ILIGAL_COUPON_MASSAGE_FIELD,errorType.INPUT_ERROR);
		}
		
		/**
		 * Validate coupon price
		 * 
		 * @param price
		 * @throws BuisnesException
		 */
		public static void validateCouponPrice(Double price) throws BuisnesException
		{
			if(price < 1.0)
				throw new BuisnesException(FinalsClass.ILIGAL_COUPON_PRICE_FIELD,errorType.INPUT_ERROR);
		}
		
		/**
		 * Validate coupon image field 
		 * 
		 * @param image
		 * @throws BuisnesException
		 */
		public static void validateCouponImage(String image) throws BuisnesException
		{
			if(!GeneralHelpFunctions.checkForInjection(image))
				throw new BuisnesException(FinalsClass.ILIGAL_COUPON_IMAGE_FIELD,errorType.INPUT_ERROR);
			if(image.length()<2)
				throw new BuisnesException(FinalsClass.ILIGAL_COUPON_IMAGE_FIELD,errorType.INPUT_ERROR);
		}
		
		/**
		 * Validate company ref field
		 * 
		 * @param companyRef
		 * @throws BuisnesException
		 */
		public static void validateCouponCompanyRef(long companyRef) throws BuisnesException
		{
			if(Long.valueOf(companyRef)==null)
				throw new BuisnesException(FinalsClass.ILIGAL_COUPON_COMPANY_ID_FIELD,errorType.INPUT_ERROR);
		}
		
		/**
		 * Check if the customer didnt enter mandatory field coupon type
		 * 
		 * @param type
		 * @throws BuisnesException
		 */
		public static void checkForMandatoryFieldType(couponType type) throws BuisnesException
		{
			if(type== null)
				throw new BuisnesException(FinalsClass.CCOUPON_MISING_TYPE_FIELD ,errorType.INPUT_ERROR);
			
		}
		
		/**
		 * Check if the customer didnt enter mandatory field end DAte
		 * 
		 * @param endDate
		 * @throws BuisnesException
		 */
		public static void checkForMandatoryFieldEndDate(String endDate) throws BuisnesException
		{
			if(endDate== null)
				throw new BuisnesException(FinalsClass.COUPON_MISING_END_DATE_FIELD ,errorType.INPUT_ERROR);
			
		}
		
		/**
		 * Check if the customer didnt enter mandatory field coupon name
		 * 
		 * @param couponName
		 * @throws BuisnesException
		 */
		public static void checkForMandatoryFieldCouponName(String couponName) throws BuisnesException
		{
			if(couponName==null)
				throw new BuisnesException(FinalsClass.COUPON_MISSING_TITLE_FIELD,errorType.INPUT_ERROR);
		
		}
		
		/**
		 * Check if the customer didnt enter mandatory field for update coupon function
		 * 
		 * @param coupon
		 * @throws BuisnesException
		 */
		public static void checkForMandatoryFieldUpdateCoupon(Coupon coupon) throws BuisnesException
		{
			if(coupon.getStartDate()==null)
				throw new BuisnesException(FinalsClass.COUPON_MISING_START_DATE_FIELD,errorType.INPUT_ERROR);
			
			if(coupon.getEndDate()==null)
				throw new BuisnesException(FinalsClass.COUPON_MISING_END_DATE_FIELD ,errorType.INPUT_ERROR);
			
			if(Double.valueOf(coupon.getPrice())==null)
				throw new BuisnesException(FinalsClass.COUPON_MISING_PRICE_FIELD,errorType.INPUT_ERROR);
		}
		
		
		//buisness validations
		
		/**
		 * check if the start date is not after the end date
		 * 
		 * @param startDate
		 * @param endDate
		 * @throws BuisnesException
		 */
		public static void checkDateOrders(String startDate,String endDate) throws BuisnesException
		{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
			try {
			Date dateStart = formatter.parse(startDate);
			Date dateEnd = formatter.parse(endDate);
			
			if(dateStart.after(dateEnd))
				throw new BuisnesException(FinalsClass.ILIGAL_COUPON_DATE_FIELD,errorType.INPUT_ERROR);
			}
			catch(ParseException e)
			{
				throw new BuisnesException(FinalsClass.ILIGAL_COUPON_DATE_FIELD,errorType.INPUT_ERROR); 
			}
		}
		
		/**
		 * Check if the customer already bought the specific couponId 
		 * 
		 * @param customerId
		 * @param couponId
		 * @throws CouponException
		 */
		public static void checkIfAlreadyBoughtCoupon(long customerId,long couponId) throws CouponException
		{
			CouponDao couponDao = new CouponDao();

			ArrayList <Coupon> coupons = new  ArrayList<Coupon>();
			coupons = (ArrayList<Coupon>) couponDao.getCouponsByCustomer(customerId);
			
			if(coupons!= null)
			{
				for(Coupon coupon:coupons)
				{
					if(coupon.getId()==couponId)
						throw new BuisnesException(FinalsClass.COUPON_ALREADY_BOUGHT,errorType.BUISNESS_ERROR);
				}
			}
		}
		
		/**
		 * check if a the amount is bigger then the quantity 
		 * 
		 * @param currAmount
		 * @param quntityBuy
		 * @throws BuisnesException
		 */
		public static void checkCouponAmount(int currAmount,int quntityBuy) throws BuisnesException
		{
			//clalc the new amount
			int newAmount =  currAmount - quntityBuy;
			
			//if the ampunt of the coupon is 0 we throw exception
			if(newAmount<=0)
				throw new BuisnesException(FinalsClass.NO_MORE_AMOUNT,errorType.BUISNESS_ERROR);
			
		}
		
		/**
		 * Check if the end date is not before the current date
		 * 
		 * @param endDate
		 * @throws CouponException
		 */
		public static void checkExpieryCoupon(String endDate) throws CouponException
		{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
			Calendar currentDate = Calendar.getInstance(); //Get the current date
			String date = formatter.format(currentDate.getTime());
			
			Date dateNow;
			Date dateEnd;
			
			//if we cant parse todays date its a internal problem 
			try {
				dateNow = formatter.parse(date);
			} catch (ParseException e1) {
				CouponsLogger logger = CouponsLogger.getInstance();
				logger.write(FinalsClass.DATE_GENERETE_FAIL);
				throw new BuisnesException(FinalsClass.UNAVAILABLE_SERVICE,errorType.INTERNAL_ERROR);
			}
			
			//if we cant parse the input date its an input problem
			try {
				dateEnd = formatter.parse(endDate);
			} catch (ParseException e) {
				throw new BuisnesException(FinalsClass.ILIGAL_COUPON_DATE_FIELD,errorType.INPUT_ERROR);
			}
			
			if(dateNow.after(dateEnd))
				throw new BuisnesException(FinalsClass.EXPIERED_COUPON,errorType.BUISNESS_ERROR);
			
		}
		
		/**
		 * Check for mandatory fields for create coupon function
		 * 
		 * @param coupon
		 * @throws BuisnesException
		 */
		public static void ValidateMandatoryFieldsCreate (Coupon coupon) throws BuisnesException
		{
			//check for mandatory input
			if(coupon.getTitle()==null)
				throw new BuisnesException(FinalsClass.COUPON_MISSING_TITLE_FIELD,errorType.INPUT_ERROR);
			
			if(coupon.getStartDate()==null)
				throw new BuisnesException(FinalsClass.COUPON_MISING_START_DATE_FIELD,errorType.INPUT_ERROR);
			
			if(coupon.getEndDate()==null)
				throw new BuisnesException(FinalsClass.COUPON_MISING_END_DATE_FIELD ,errorType.INPUT_ERROR);
			
			if(Double.valueOf(coupon.getAmount())==null)
				throw new BuisnesException(FinalsClass.COUPON_MISING_AMOUNT_FIELD ,errorType.INPUT_ERROR);
			
			if(coupon.getType()==null)
				throw new BuisnesException(FinalsClass.CCOUPON_MISING_TYPE_FIELD ,errorType.INPUT_ERROR);
			
			if(coupon.getMassage()==null)
				throw new BuisnesException(FinalsClass.COUPON_MISING_MASSAGE_FIELD,errorType.INPUT_ERROR);
			
			if(Double.valueOf(coupon.getPrice())==null)
				throw new BuisnesException(FinalsClass.COUPON_MISING_PRICE_FIELD,errorType.INPUT_ERROR);
			
			if(coupon.getImage()==null)
				throw new BuisnesException(FinalsClass.COUPON_MISING_IMAGE_FIELD,errorType.INPUT_ERROR);
			
			if(Long.valueOf(coupon.getCompanyRef())==null)
				throw new BuisnesException(FinalsClass.COUPON_MISING_COMPANY_ID_FIELD,errorType.INPUT_ERROR);
		}

	
}
