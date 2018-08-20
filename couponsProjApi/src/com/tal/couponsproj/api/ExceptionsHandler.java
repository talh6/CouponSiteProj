package com.tal.couponsproj.api;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.tal.couponsproj.exeptions.BuisnesException;
import com.tal.couponsproj.exeptions.GeneralException;
 
@Provider
public class ExceptionsHandler extends Exception implements ExceptionMapper<Throwable> 
{
    @Override
    public Response toResponse(Throwable exception) 
    {
    	
    	//GeneralException
    	if (exception instanceof GeneralException){
    		GeneralException e = (GeneralException) exception;
    		
    		String message = e.getMessage();
    		return Response.status(600).entity(message).build();
    	}
    	
    	//BuisnesException
    	if (exception instanceof BuisnesException){
    		BuisnesException e = (BuisnesException) exception;
    		
    		String message = e.getMessage();
    		return Response.status(605).entity(message).build();
    	}    	
    		//if its not both send general fail
        return Response.status(500).entity("General failure").build();
    }
}