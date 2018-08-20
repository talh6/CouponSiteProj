package com.tal.couponsproj.beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BuyCouponInput {
	private long customerId;
	private long couponId;
	
	public BuyCouponInput(long customerId, long couponId) {
		super();
		this.customerId = customerId;
		this.couponId = couponId;
	}
	
	public BuyCouponInput() {
		super();
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public long getCouponId() {
		return couponId;
	}

	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}
	
	
}
