package com.tal.couponsproj.interfaces;

import java.util.Collection;

import com.tal.couponsproj.beans.Customer;
import com.tal.couponsproj.exeptions.CouponException;

public interface ICustomer {
	
	public void createCustomer(Customer customer) throws CouponException;
	
	public void removeCustomer(long customerId) throws CouponException;
	
	public void updateCustomer(Customer customer) throws CouponException;
	
	public Customer getCustomerByID(long customerId) throws CouponException;
	
	public Customer getCustomerByName(String customerName) throws CouponException;
	
	public Customer getCustomerByUsername(String username) throws CouponException;
	
	public Collection <Customer> getAllCustomers() throws CouponException;
	
	public boolean login(String custName,String password) throws CouponException;
	

}
