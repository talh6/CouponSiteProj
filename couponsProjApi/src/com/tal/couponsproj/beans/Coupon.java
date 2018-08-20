package com.tal.couponsproj.beans;

import javax.xml.bind.annotation.XmlRootElement;

import com.tal.couponsproj.enums.couponType;

/* class Coupon
 * project coupons
 * 
 * Aouther : Tal Hakmon
 * version 1.0
 */
@XmlRootElement
public class Coupon {
	private long id;
	private String title;
	private String startDate;
	private String endDate;
	private int amount;
	private couponType type;
	private String massage;
	private double price;
	private String image;
	private long companyRef;
	
	// Constructors area
	public Coupon()
	{
		super();
	}
	
	//constractor for insert new coupon , the id is auto increased
	public Coupon(String title,String startDate,String endDate,int amount,
		    couponType type,String massage,double price,String image,long companyRef)
	{
		this.title= title;
		this.startDate= startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.type = type;
		this.massage = massage;
		this.price = price;
		this.image = image;
		this.companyRef= companyRef;
	}
	
	//constructor for new coupon
	public Coupon(long id,String title,String startDate,String endDate,int amount,
			    couponType type,String massage,double price,String image,long companyRef)
	{
		this(title,startDate,endDate,amount,type,massage,price,image,companyRef);
		this.id=id;
	}
	
	//ToString
		public String toString()
		{
			return ("Coupon id :"+this.id +" title:"+ this.title + " company ref is:"+ this.companyRef);
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
	public double getPrice()
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
