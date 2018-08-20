package com.tal.couponsproj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.tal.couponsproj.beans.LogCompany;
import com.tal.couponsproj.enums.errorType;
import com.tal.couponsproj.exeptions.WriteToLogException;
import com.tal.couponsproj.interfaces.ILogCompany;
import com.tal.couponsproj.logger.CouponsLogger;
import com.tal.couponsproj.utils.FinalsClass;
import com.tal.couponsproj.utils.JdbcUtils;

public class LogCompanyDao implements ILogCompany{

	private void writeToLog(Exception e) throws WriteToLogException
	{
		CouponsLogger daoLogger = CouponsLogger.getInstance();
		daoLogger.write(e.toString());
	}
	@Override
	public void insertToCompenyLog(LogCompany logcompany) throws WriteToLogException {
		//creating the resources
				PreparedStatement preparedStatement = null;
				Connection connection = null;
				ResultSet resultSet = null;
				
				try
				{
					//getting a connection from the connection manager
					connection = JdbcUtils.getConnection();
					//creating the SQL query
					String sql = "insert into logCompany (actionType,companyID,companyName, password, email,executionTimeStamp) values (?,?,?,?,?,?)";
					// Creating a statement object which holds the SQL we're about to execute and asking for the generated keys back
					preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					
					//asking to Replace the question marks in the statement above with the following data
					preparedStatement.setString(1, logcompany.getAction().toString());
					preparedStatement.setLong(2, logcompany.getCompanyId());
					preparedStatement.setString(3, logcompany.getCompName());
					preparedStatement.setString(4, logcompany.getPassword());
					preparedStatement.setString(5,logcompany.getEmail());
					preparedStatement.setString(6,logcompany.GetExecutionTimeStamp());
					
					
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
