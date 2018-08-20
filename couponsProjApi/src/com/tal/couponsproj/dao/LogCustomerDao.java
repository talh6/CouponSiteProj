package com.tal.couponsproj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.tal.couponsproj.beans.LogCustomer;
import com.tal.couponsproj.enums.errorType;
import com.tal.couponsproj.exeptions.WriteToLogException;
import com.tal.couponsproj.interfaces.ILogCustomer;
import com.tal.couponsproj.logger.CouponsLogger;
import com.tal.couponsproj.utils.FinalsClass;
import com.tal.couponsproj.utils.JdbcUtils;

public class LogCustomerDao implements ILogCustomer{
	
	private void writeToLog(Exception e) throws WriteToLogException
	{
		CouponsLogger daoLogger = CouponsLogger.getInstance();
		daoLogger.write(e.toString());
	}

	@Override
	public void insertToCustomerLog(LogCustomer logcustomer) throws WriteToLogException {
		//creating the resources
				PreparedStatement preparedStatement = null;
				Connection connection = null;
				ResultSet resultSet = null;
				
				try
				{
					//getting a connection from the connection manager
					connection = JdbcUtils.getConnection();
					//creating the SQL query
					String sql = "insert into logCustomers (actionType,customerID,customerName, userName,password,executionTimeStamp ) "
							+ "values (?,?,?,?,?,?)";
					
					// Creating a statement object which holds the SQL we're about to execute and asking for the generated keys back
					preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					
					//asking to Replace the question marks in the statement above with the following data
					preparedStatement.setString(1, logcustomer.getAction().toString());
					preparedStatement.setLong(2, logcustomer.getCustomerId());
					preparedStatement.setString(3, logcustomer.getCustName());
					preparedStatement.setString(4, logcustomer.getUserName());
					preparedStatement.setString(5, logcustomer.getPassword());
					preparedStatement.setString(6, logcustomer.getExecutionTimeStamp());
					

					
					
					//executing the update
					preparedStatement.executeUpdate();
					
				}
				catch(Exception e)
				{
					writeToLog(e);
					throw new WriteToLogException(FinalsClass.UNAVAILABLE_SERVICE,errorType.INTERNAL_ERROR);
				}
				
				finally
				{
					JdbcUtils.closeResources(connection, preparedStatement, resultSet);
				}
		
	}
	
	

}
