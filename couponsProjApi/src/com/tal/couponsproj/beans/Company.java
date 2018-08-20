package com.tal.couponsproj.beans;

import javax.xml.bind.annotation.XmlRootElement;

/* class company
 * project coupons
 * 
 * Aouther : Tal Hakmon
 * version 1.0
 */
@XmlRootElement
public class Company {
	private long id;
	private String compName;
	private String password;
	private String email;

	// Constructors area
	 
	
	public Company()
	{
		super();
	}
	
	//constractor for insert new cmpany , the id is auto increased
	public Company(String compName,String password,String email)
	{
		this.compName = compName;
		this.password = password;
		this.email= email;
	}
	
	//constructor for new company
	public Company(long id,String compName,String password,String email)
	{
		this(compName,password,email);
		this.id=id;
	}
	
	//ToString
	public String toString()
	{
		return ("Company ID: "+ this.id + " Company name : "+ this.compName + " Company email : "+ this.email);
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
	
	//compName
	public String getCompName()
	{
		return this.compName;
	}
	
	public void setCompName(String compName)
	{
		this.compName=compName;
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
	
	//email
	public String getEmail()
	{
		return this.email;
	}
	
	public void setEmail(String email)
	{
		this.email=email;
	}	
		
	
}
