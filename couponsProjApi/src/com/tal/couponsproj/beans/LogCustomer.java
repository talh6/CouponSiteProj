package com.tal.couponsproj.beans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tal.couponsproj.enums.actionType;

public class LogCustomer {
	private long logID;
	private actionType action;
	private String executionTimeStamp;
	private long customerId;
	private String custName;
	private String password;
	private String userName;
	
	public LogCustomer()
	{
		super();
	}
	
	public LogCustomer(actionType action ,Customer customer)
	{
		this.action =action;
		this.customerId=customer.getId();
		this.custName=customer.getCustName();
		this.password = customer.getPassword();
		this.userName= customer.getUserName();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		this.executionTimeStamp = dateFormat.format(date);
	}
	
	public LogCustomer(actionType action , String executionTimeStamp,long customerId,String custName,String password,String userName)
	{
		this.action =action;
		this.executionTimeStamp = executionTimeStamp;
		this.customerId=customerId;
		this.custName=custName;
		this.password = password;
		this.userName= userName;
	}
	
	public LogCustomer(long logID,actionType action , String executionTimeStamp,long customerId,String custName,String password,String userName)
	{
		this.customerId=customerId;
		this.custName=custName;
		this.password = password;
		this.userName= userName;
		this.logID = logID;
		this.action =action;
		this.executionTimeStamp = executionTimeStamp;
	}
	

	//ToString
	public String toString()
	{
		return ("LogID: " + this.logID +" Customer ID: "+ this.customerId + " Customer Name : "+ this.custName);
	}
	
	//logID
	public long getLogId()
	{
		return this.logID;
	}
	
	public void setLogId(long logID)
	{
		this.logID = logID;
	}
	
	//action
	public actionType getAction()
	{
		return this.action;
	}
	
	public void setAction(actionType action)
	{
		this.action = action;
	}
	
	//executionTimeStamp
	public String getExecutionTimeStamp()
	{
		return this.executionTimeStamp;
	}
	
	public void setExecutionTimeStamp(String executionTimeStamp)
	{
		this.executionTimeStamp = executionTimeStamp;
	}
	
	//customerId
	public long getCustomerId()
	{
		return this.customerId;
	}
		
	public void setCustomerId(long id)
	{
		this.customerId=id;
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
