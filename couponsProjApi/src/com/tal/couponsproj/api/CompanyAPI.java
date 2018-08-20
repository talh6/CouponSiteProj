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

import com.tal.couponsproj.beans.Company;
import com.tal.couponsproj.exeptions.CouponException;
import com.tal.couponsproj.logic.CompanyController;

@Path("/site/company")
public class CompanyAPI {
	private CompanyController companyController = new CompanyController();
	
	/*
	 * -------------------------------------------------------------------------------
	 * POST methods
	 * -------------------------------------------------------------------------------
	 */
	@POST
	@Path("/createCompany")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createCompany(Company company) throws CouponException{
		companyController.createCompany(company);
	}
	
	/*
	 * -------------------------------------------------------------------------------
	 * DELETE methods
	 * -------------------------------------------------------------------------------
	 */
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/removeCompany")
	public void removeCompany(@QueryParam("companyId") long companyId) throws CouponException{
		companyController.removeCompany(companyId);
	}
	
	/*
	 * -------------------------------------------------------------------------------
	 * PUT methods
	 * -------------------------------------------------------------------------------
	 */
	@PUT
	@Path("/updateCompany")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateCompany(Company company) throws CouponException{
		companyController.updateCompany(company);
	}
	
	/*
	 * -------------------------------------------------------------------------------
	 * GET methods
	 * -------------------------------------------------------------------------------
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Company> getAllCompanies() throws CouponException {
		return companyController.getAllCompanies();
	}
	
	@GET
	@Path("/getCompanyByID")
	@Produces(MediaType.APPLICATION_JSON)
	public Company getCompanyByID(@QueryParam("companyId") long companyId) throws CouponException{
		return companyController.getCompanyByID(companyId);
	}
	
	@GET
	@Path("/getCompanyByName")
	@Produces(MediaType.APPLICATION_JSON)
	public Company getCompanyByName(@QueryParam("companyName") String companyName) throws CouponException{
		return companyController.getCompanyByName(companyName);
	}
	
	
}
