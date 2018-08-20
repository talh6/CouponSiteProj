package com.tal.couponsproj.api;

import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


import com.tal.couponsproj.beans.BuyCouponInput;
import com.tal.couponsproj.beans.Coupon;
import com.tal.couponsproj.enums.couponType;
import com.tal.couponsproj.exeptions.CouponException;
import com.tal.couponsproj.logic.CouponController;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;

@Path("/site/coupon")
public class CouponsAPI {
	CouponController couponController= new CouponController();
	
	/*
	 * -------------------------------------------------------------------------------
	 * GET methods
	 * -------------------------------------------------------------------------------
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Coupon> getAllCoupon() throws CouponException{
		return couponController.getAllCoupon();
	}
	
	@GET
	@Path("/getCouponByType")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Coupon> getCouponByType(@QueryParam("type") couponType type) throws CouponException{
		return couponController.getCouponByType(type);
	}
	
	@GET
	@Path("/getCouponById")
	@Produces(MediaType.APPLICATION_JSON)
	public Coupon getCouponByID(@QueryParam("couponId") long couponId) throws CouponException{
		return couponController.getCouponByID(couponId);
	}
	
	
	@GET
	@Path("/getCouponByName")
	@Produces(MediaType.APPLICATION_JSON)
	public Coupon getCouponByName(@QueryParam("couponName") String couponName) throws CouponException{
		return couponController.getCouponByName(couponName);
	}
	
	@GET
	@Path("/getCouponsByCustomer")
	@Produces(MediaType.APPLICATION_JSON)	
	public Collection<Coupon> getCouponsByCustomer(@QueryParam("customerId") long customerId) throws CouponException{
		return couponController.getCouponsByCustomer(customerId);
	}
	
	
	@GET
	@Path("/getCouponsByCompany")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Coupon> getCouponsByCompany(@QueryParam("companyId") long companyId) throws CouponException{
		return couponController.getCouponsByCompany(companyId);
	}
	
	
	@GET
	@Path("/getCouponsByCompanyAndExpieretionDay")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Coupon> getCouponsByCompanyAndExpieretionDay(@QueryParam("companyId") long companyId,@QueryParam("endDate") String endDate) throws CouponException{
		return couponController.getCouponsByCompanyAndExpieretionDay(companyId,endDate);
	}

	@GET
	@Path("/getCouponsByCompanyAndType")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Coupon> getCouponsByCompanyAndType(@QueryParam("companyId") long companyId,@QueryParam("type") couponType type) throws CouponException{
		return couponController.getCouponsByCompanyAndType(companyId,type);
	}
	
	@GET
	@Path("/getCouponsByCompanyAndMaxPrice")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Coupon> getCouponsByCompanyAndMaxPrice(@QueryParam("companyId") long companyId,@QueryParam("price") double price) throws CouponException{
		return couponController.getCouponsByCompanyAndMaxPrice(companyId,price);
	}
	
	@GET
	@Path("/getHistoryCouponsByType")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Coupon> getHistoryCouponsByType(@QueryParam("customerId")long customerId, @QueryParam("type") couponType type) throws CouponException{
		return couponController.getHistoryCouponsByType(customerId, type);
	}
	
	@GET
	@Path("/getHistoryCouponsByPrice")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Coupon> getHistoryCouponsByPrice(@QueryParam("customerId") long customerId,@QueryParam("price") double price) throws CouponException{
		return couponController.getHistoryCouponsByPrice(customerId, price);
	}
	
	/*
	 * -------------------------------------------------------------------------------
	 * POST methods
	 * -------------------------------------------------------------------------------
	 */
	@POST
	@Path("/createCoupon")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createCoupon (Coupon coupon) throws CouponException{
		couponController.createCoupon(coupon);
	}
	
	@POST
	@Path("/buyCoupon")
	@Consumes(MediaType.APPLICATION_JSON)
	public void buyCoupon(BuyCouponInput buyCouponInput) throws CouponException{
		couponController.buyCoupon(buyCouponInput.getCustomerId(),buyCouponInput.getCouponId());
	}
	
	/*
	 * -------------------------------------------------------------------------------
	 * PUT methods
	 * -------------------------------------------------------------------------------
	 */
	@PUT
	@Path("/updateCoupon")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateCoupon(Coupon coupon) throws CouponException  {
		couponController.updateCoupon(coupon);
	}
	
	/*
	 * -------------------------------------------------------------------------------
	 * DELETE methods
	 * -------------------------------------------------------------------------------
	 */
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/removeCoupon")
	public void removeCoupon(@QueryParam("couponId") long couponId) throws CouponException{
		couponController.removeCoupon(couponId);
	}
}