package com.tal.couponsproj.beans;

import javax.xml.bind.annotation.XmlRootElement;

/* class Customer
 * project coupons
 * 
 * Aouther : Tal Hakmon
 * version 1.0
 */
@XmlRootElement
public class Customer {
	
	private long id;
	private String custName;
	private String password;
	private String userName;
	
	// Constructors area
	
	public Customer()
	{
		super();
	}
	
	//constractor for insert new customer , the id is auto increased
	public Customer(String custName,String password,String userName)
	{
		this.custName=custName;
		this.password = password;
		this.userName= userName;
	}
	
	//constructor for new customer
	public Customer(long id,String custName,String password,String userName)
	{
		this(custName,password,userName);
		this.id=id;
	}
	
	
	public String toString()
	{
		return ("Customer ID: "+ this.id + " customer name is: "+ this.custName);
	}

	// Get and Set area
	
	
	//ID
	public long getId()
	{
		return this.id;
	}
	
	public void setId(long id)
	{
		this.id=id;
	}
	
	//custName
	public String getCustName()
	{
		return this.custName;
	}
	
	public void setCustName(String custName)
	{
		this.custName=custName;
	}
	
	//userName
	public String getUserName()
	{
		return this.userName;
	}
	
	public void setUserName(String userName)
	{
		this.userName=userName;
	}
	
	//password
	public String getPassword()
	{
		return this.password;
	}
	
	public void setPassword(String password)
	{
		this.password=password;
	}
}
