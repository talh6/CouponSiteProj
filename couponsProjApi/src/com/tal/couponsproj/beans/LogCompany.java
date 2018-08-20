package com.tal.couponsproj.beans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tal.couponsproj.enums.actionType;

public class LogCompany {
	private long logID;
	private actionType action;
	private String executionTimeStamp;
	private long companyId;
	private String compName;
	private String password;
	private String email;
	
	public LogCompany()
	{
		super();
	}
	
	public LogCompany(actionType action ,Company company)
	{
		this.companyId = company.getId();
		this.compName = company.getCompName();
		this.password = company.getPassword();
		this.email= company.getEmail();
		this.action =action;
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		this.executionTimeStamp = dateFormat.format(date);
	}
	
	public LogCompany(actionType action , String executionTimeStamp, long companyId, String compName,String password,String email)
	{
		this.companyId = companyId;
		this.compName = compName;
		this.password = password;
		this.email= email;
		this.action =action;
		this.executionTimeStamp = executionTimeStamp;
	}
	
	public LogCompany(long logID,actionType action , String executionTimeStamp,long companyId ,String compName,String password,String email)
	{
		this.companyId = companyId;
		this.compName = compName;
		this.password = password;
		this.email= email;
		this.logID= logID;
		this.action =action;
		this.executionTimeStamp = executionTimeStamp;
	}
	
	
	//ToString
	public String toString()
	{
		return ("LogID: " + this.logID +" Company ID: "+ this.companyId + " Company name : "+ this.compName + " Company email : "+ this.email);
	}
	
	//get and set 
	
	
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
	public String GetExecutionTimeStamp()
	{
		return this.executionTimeStamp;
	}
	
	public void SetExecutionTimeStamp(String executionTimeStamp)
	{
		this.executionTimeStamp = executionTimeStamp;
	}
	
	
		
	//companyId
	public long getCompanyId()
	{
		return this.companyId;
	}
		
	public void setCompanyId(long companyId)
	{
		this.companyId=companyId;
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
