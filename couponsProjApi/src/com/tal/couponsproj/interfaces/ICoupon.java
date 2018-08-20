package com.tal.couponsproj.interfaces;
import java.util.Collection;

import com.tal.couponsproj.beans.Coupon;
import com.tal.couponsproj.enums.couponType;
import com.tal.couponsproj.exeptions.CouponException;

public interface ICoupon {
	
	public boolean isCouponExistsByName (String couponName ) throws CouponException;
	
	public void createCoupon (Coupon coupon) throws CouponException;
	
	public void removeCoupon(long couponId) throws CouponException;
	
	public void updateCoupon(Coupon coupon) throws CouponException;
	
	public Coupon getCouponByID(long couponId) throws CouponException;
	
	public Coupon getCouponByName(String couponName) throws CouponException;
	
	public Collection<Coupon> getAllExpieryCoupon() throws CouponException;
	
	public Collection<Coupon> getAllCoupon() throws CouponException;
	
	public Collection<Coupon> getCouponByType(couponType type) throws CouponException;
	
	public Collection<Coupon> getCouponsByCompany(long companyId) throws CouponException;
	
	public Collection<Coupon> getCouponsByCustomer(long customerId) throws CouponException;
	
	public Collection<Coupon> getCouponsByCompanyAndExpieretionDay(long companyId,String endDate) throws CouponException;
	
	public Collection<Coupon> getCouponsByCompanyAndType(long companyId,couponType type) throws CouponException;
	
	public Collection<Coupon> getCouponsByCompanyAndMaxPrice(long companyId,double price) throws CouponException;
	
	public Collection<Coupon> getHistoryCouponsByType(long customerId,couponType type) throws CouponException;
	
	public Collection<Coupon> getHistoryCouponsByPrice(long customerId,double price) throws CouponException;
	
	public void updateCustomerCoupon(long customerId,long couponId) throws CouponException;
	
	public void updateCouponQuantity(long couponID,int quantity) throws CouponException;
	
	public void removeCustomerCouponByCoupn(long couponId) throws CouponException;
	
	public void removeCustomerCouponByCustomer(long customerId) throws CouponException;
}
