package com.tal.couponsproj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tal.couponsproj.beans.LogCoupon;
import com.tal.couponsproj.enums.actionType;
import com.tal.couponsproj.enums.errorType;
import com.tal.couponsproj.exeptions.WriteToLogException;
import com.tal.couponsproj.interfaces.ILogCoupon;
import com.tal.couponsproj.logger.CouponsLogger;
import com.tal.couponsproj.utils.FinalsClass;
import com.tal.couponsproj.utils.JdbcUtils;

public class LogCouponDao implements ILogCoupon{
	
	private void writeToLog(Exception e) throws WriteToLogException
	{
		CouponsLogger daoLogger = CouponsLogger.getInstance();
		daoLogger.write(e.toString());
	}

	@Override
	public void insertToCouponLog(LogCoupon logcoupon) throws WriteToLogException {
		//creating the resources
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		ResultSet resultSet = null;
		
		try
		{
			//getting a connection from the connection manager
			connection = JdbcUtils.getConnection();
			//creating the SQL query
			String sql = "insert into logCoupons (actionType,couponID,title, startDate,endDate,amount,type,massage,price"
					+ ",image,companyRef,exceutionTimeStamp) "
					+ "values (?,?,?,?,?,?,?,?,?,?,?,?)";
			// Creating a statement object which holds the SQL we're about to execute and asking for the generated keys back
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			//asking to Replace the question marks in the statement above with the following data
			preparedStatement.setString(1, logcoupon.getAction().toString());
			preparedStatement.setLong(2,logcoupon.getCouoponID() );
			preparedStatement.setString(3, logcoupon.getTitle());
			preparedStatement.setString(4, logcoupon.getStartDate());
			preparedStatement.setString(5, logcoupon.getEndDate());
			preparedStatement.setInt(6, logcoupon.getAmount());
			preparedStatement.setString(7, logcoupon.getType().toString());
			preparedStatement.setString(8, logcoupon.getMassage());
			preparedStatement.setDouble(9, logcoupon.getPrice());
			preparedStatement.setString(10, logcoupon.getImage());
			preparedStatement.setLong(11, logcoupon.getCompanyRef());
			preparedStatement.setString(12, logcoupon.getExecutionTimeStamp());
			
			
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

	@Override
	public void insertToLogCustomerCoupons(long customerID, long CoupomID) throws WriteToLogException {
		//creating the resources
				PreparedStatement preparedStatement = null;
				Connection connection = null;
				ResultSet resultSet = null;
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				
				try
				{
					//getting a connection from the connection manager
					connection = JdbcUtils.getConnection();
					//creating the SQL query
					//id,title, startDate,endDate,amount,type,massage,price,image,companyRef
					String sql = "insert into logCustomerCoupons (actionType,customerID,couponID,executuinTimeStamp) "
							+ "VALUES(?,?,?,?)";
					// Creating a statement object which holds the SQL we're about to execute and asking for the generated keys back
					preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					
					//asking to Replace the question marks in the statement above with the following data
					preparedStatement.setString(1,actionType.PURSHED.toString());
					preparedStatement.setLong(2,customerID);	
					preparedStatement.setLong(3,CoupomID);
					preparedStatement.setString(4,dateFormat.format(date));
					
					
					//executing the update
					preparedStatement.executeUpdate();
					
					//putting the result that we got back resultSet
					resultSet = preparedStatement.getGeneratedKeys();
					
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
