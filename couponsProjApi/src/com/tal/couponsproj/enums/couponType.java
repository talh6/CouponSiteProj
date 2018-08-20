package com.tal.couponsproj.enums;

import javax.xml.bind.annotation.XmlRootElement;

/* class enums couppons types
 * project coupons
 * 
 * Aouther : Tal Hakmon
 * version 1.0
 */
@XmlRootElement
public enum couponType {
	FOOD,
	VACTION,
	MOVIE,
	OTHER;

	 static public boolean isMember(String aName) {
		 couponType[] types = couponType.values();
	       for (couponType type : types)
	           if (type.toString().equals(aName))
	               return true;
	       return false;
	 }
}


//TO DO add DIC_CouponType table and get the status from there