package com.tal.couponsproj.api;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.tal.couponsproj.beans.Customer;
import com.tal.couponsproj.exeptions.CouponException;
import com.tal.couponsproj.logic.CustomerController;

@Path("/site/customer")
public class CustomerAPI {
	private CustomerController customerController = new CustomerController();
	
	/*
	 * -------------------------------------------------------------------------------
	 * POST methods
	 * -------------------------------------------------------------------------------
	 */
	@POST
	@Path("/createCustomer")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createCustomer(Customer customer) throws CouponException{
		customerController.createCustomer(customer);
	}
	
	/*
	 * -------------------------------------------------------------------------------
	 * DELETE methods
	 * -------------------------------------------------------------------------------
	 */
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/removeCustomer")
	public void removeCustomer(@QueryParam("customerId")long customerId) throws CouponException{
		customerController.removeCustomer(customerId);
	}
	
	/*
	 * -------------------------------------------------------------------------------
	 * PUT methods
	 * -------------------------------------------------------------------------------
	 */
	@PUT
	@Path("/updateCustomer")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateCustomer(Customer customer) throws CouponException{
		customerController.updateCustomer(customer);
	}
	
	/*
	 * -------------------------------------------------------------------------------
	 * GET methods
	 * -------------------------------------------------------------------------------
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Customer> getAllCustomers() throws CouponException{
		return  customerController.getAllCustomers();
	}
	
	@GET
	@Path("/getCustomerByID")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getCustomerByID(@QueryParam("customerId") long customerId) throws CouponException{
		return customerController.getCustomerByID(customerId);
	}
	
	@GET
	@Path("/getCustomerByName")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getCustomerByName(@QueryParam("customerName") String customerName) throws CouponException{
		return customerController.getCustomerByName(customerName);
	}
	
	@GET
	@Path("/getCustomerByUsername")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getCustomerByUsername(String username) throws CouponException{
		return customerController.getCustomerByUsername(username);
	}
	

}
