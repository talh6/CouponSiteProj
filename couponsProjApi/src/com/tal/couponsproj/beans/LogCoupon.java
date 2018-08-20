package com.tal.couponsproj.beans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tal.couponsproj.enums.actionType;
import com.tal.couponsproj.enums.couponType;

public class LogCoupon {
	private long logID;
	private actionType action;
	private String executionTimeStamp;
	private long couoponID;
	private String title;
	private String startDate;
	private String endDate;
	private int amount;
	private couponType type;
	private String massage;
	private double price;
	private String image;
	private long companyRef;
	
	public LogCoupon()
	{
		super();
	}
	
	public LogCoupon(actionType action,Coupon coupon)
	{
		this.couoponID=coupon.getId();
		this.title= coupon.getTitle();
		this.startDate= coupon.getStartDate();
		this.endDate = coupon.getEndDate();
		this.amount = coupon.getAmount();
		this.type = coupon.getType();
		this.massage = coupon.getMassage();
		this.price = coupon.getPrice();
		this.image = coupon.getImage();
		this.companyRef= coupon.getCompanyRef();
		this.action =action;
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		this.executionTimeStamp = dateFormat.format(date);
	}
	
	public LogCoupon(actionType action,String executionTimeStamp,long couoponID,String title,String startDate,String endDate,int amount,
		    couponType type,String massage,double price,String image,long companyRef)
	{
		this.couoponID=couoponID;
		this.title= title;
		this.startDate= startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.type = type;
		this.massage = massage;
		this.price = price;
		this.image = image;
		this.companyRef= companyRef;
		this.action =action;
		this.executionTimeStamp = executionTimeStamp;
	}
	
	public LogCoupon(long logID,actionType action,String executionTimeStamp,long couoponID,String title,String startDate,String endDate,int amount,
			    couponType type,String massage,double price,String image,long companyRef)
	{
		this.logID= logID;
		this.couoponID=couoponID;
		this.title= title;
		this.startDate= startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.type = type;
		this.massage = massage;
		this.price = price;
		this.image = image;
		this.companyRef= companyRef;
		this.action =action;
		this.executionTimeStamp = executionTimeStamp;
	}
	
	//ToString
	public String toString()
	{
		return ("Coupon id :"+this.couoponID +" title:"+ this.title + " company ref is:"+ this.companyRef);
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
	
	//ID
	public long getCouoponID()
	{
		return this.couoponID;
	}
	
	public void setId(long couoponID)
	{
		this.couoponID=couoponID;
	}
	
	//title
	public String getTitle()
	{
		return this.title;
	}
	
	public void setTitle(String title)
	{
		this.title=title;
	}
	
	//startDate
	public String getStartDate()
	{
		return this.startDate;
	}
	
	public void setStartDate(String startDate)
	{
		this.startDate=startDate;
	}
	
	//endDate
	public String getEndDate()
	{
		return this.endDate;
	}
	
	public void setEndDate(String endDate)
	{
		this.endDate=endDate;
	}
	
	//amount
	public int getAmount()
	{
		return this.amount;
	}
	
	public void setAmount(int amount)
	{
		this.amount=amount;
	}
	

	//CouponType
	public  couponType getType()
	{
		return this.type;
	}
	
	public void setType(couponType type)
	{
		this.type=type;
	}
	
	//massage
	public String getMassage()
	{
		return this.massage;
	}
	
	public void setMassage(String massage)
	{
		this.massage=massage;
	}
	
	//price
	public Double getPrice()
	{
		return this.price;
	}
	
	public void setPrice(double price)
	{
		this.price=price;
	}
	
	//image
	public String getImage()
	{
		return this.image;
	}
	
	public void setImage(String image)
	{
		this.image=image;
	}
	
	//companyRef
	public long getCompanyRef()
	{
		return this.companyRef;
	}
	
	public void setCompanyRef(long companyRef)
	{
		this.companyRef = companyRef;
	}

}
