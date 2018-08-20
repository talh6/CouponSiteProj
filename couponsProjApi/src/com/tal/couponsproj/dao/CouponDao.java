package com.tal.couponsproj.dao;
import com.tal.couponsproj.beans.Coupon;
import com.tal.couponsproj.enums.couponType;
import com.tal.couponsproj.enums.errorType;
import com.tal.couponsproj.exeptions.GeneralException;
import com.tal.couponsproj.exeptions.CouponException;
import com.tal.couponsproj.interfaces.ICoupon;
import com.tal.couponsproj.logger.CouponsLogger;
import com.tal.couponsproj.utils.JdbcUtils;
import com.tal.couponsproj.utils.FinalsClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Collection;
/*
 * Class : Coupons Dao
 * Aouther : Tal Hakmon
 * Version : 1.0
 * Date : 11.9.17
 */

public class CouponDao implements ICoupon{
	
	/*
	 * Private function : write to logger
	 * 
	 * Input: Exception
	 * 
	 * Logic:
	 * write to log exception info
	 */
	private void writeToLog(Exception e) throws CouponException
	{
		CouponsLogger daoLogger = CouponsLogger.getInstance();
		daoLogger.write(e.toString());
	}
	
	/*
	 * Public function: is coupon Exicst
	 * check if the coupon is excist in DB
	 * 
	 * Input: company name 
	 * 
	 * returns true is coupon is exicst or false if it isnt
	 */
	public boolean isCouponExistsByName (String couponName ) throws CouponException
	{
		//creating the resources
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		ResultSet resultSet = null;
		
		try {
		//getting a connection from the connection manager
		connection = JdbcUtils.getConnection();
		
		//creating the SQL query
		String sql = "SELECT * from coupons WHERE title=?";
		
		// Creating a statement object which holds the SQL we're about to execute and asking for the generated keys back
		preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		//asking to Replace the question marks in the SQL statement above with with the following data
		preparedStatement.setString(1, couponName);
		
		
		//if there are no results, return null
		resultSet = preparedStatement.executeQuery();
		if (!resultSet.next()) 
		{
			return false;
		}
		
		//if there are results, get them and put them in a Company object in resultSet.
		return true;

		//if there was an exception in the "try" block above, it is caught here and notifies a level above.
		} 
		catch (SQLException e) 
		{
			writeToLog(e);
			throw new GeneralException(FinalsClass.UNAVAILABLE_SERVICE,errorType.INTERNAL_ERROR);
		} 
		finally 
		{
			//closing the resources
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
		
	}
	
	/*
	 * Public function: create coupon
	 * create a new coupon
	 * 
	 * Input: coupon
	 * 
	 */
	public void createCoupon (Coupon coupon) throws CouponException
	{
		//creating the resources
				PreparedStatement preparedStatement = null;
				Connection connection = null;
				ResultSet resultSet = null;
				
				try
				{
					//getting a connection from the connection manager
					connection = JdbcUtils.getConnection();
					//creating the SQL query
					String sql = "insert into coupons (title, startDate,endDate,amount,type,massage,price,image,companyRef) values (?,?,?,?,?,?,?,?,?)";
					// Creating a statement object which holds the SQL we're about to execute and asking for the generated keys back
					preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					
					//asking to Replace the question marks in the statement above with the following data
					preparedStatement.setString(1, coupon.getTitle());
					preparedStatement.setString(2, coupon.getStartDate());
					preparedStatement.setString(3, coupon.getEndDate());
					preparedStatement.setInt(4, coupon.getAmount());
					preparedStatement.setString(5, coupon.getType().toString());
					preparedStatement.setString(6, coupon.getMassage());
					preparedStatement.setDouble(7, coupon.getPrice());
					preparedStatement.setString(8, coupon.getImage());
					preparedStatement.setLong(9,coupon.getCompanyRef());
					
					//executing the update
					preparedStatement.executeUpdate();
					
				}
				catch(Exception e)
				{
					writeToLog(e);
					throw new GeneralException(FinalsClass.UNAVAILABLE_SERVICE,errorType.INTERNAL_ERROR);
				}
				
				finally
				{
					JdbcUtils.closeResources(connection, preparedStatement, resultSet);
				}
	}


	/*
	 * Public function: remove coupon
	 * remove a coupon
	 * 
	 * Input: coupon id
	 * 
	 */
	@Override
	public void removeCoupon(long couponId) throws CouponException {
		//creating the resources
				PreparedStatement preparedStatement = null;
				Connection connection = null;
				ResultSet resultSet = null;
				
				try
				{
					//getting a connection from the connection manager
					connection = JdbcUtils.getConnection();
					//creating the SQL query
					String sql = "delete from coupons where ID = ?";
					// Creating a statement object which holds the SQL we're about to execute and asking for the generated keys back
					preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					
					//asking to Replace the question marks in the statement above with the following data
					preparedStatement.setLong(1, couponId);
					
					//executing the update
					preparedStatement.executeUpdate();
					
				}
				catch(Exception e)
				{
					writeToLog(e);
					throw new GeneralException(FinalsClass.UNAVAILABLE_SERVICE,errorType.INTERNAL_ERROR);
				}
				
				finally
				{
					JdbcUtils.closeResources(connection, preparedStatement, resultSet);
				}
		
	}

	/*
	 * Public function: update coupon
	 * update a coupon
	 * 
	 * Input: coupon
	 * 
	 */
	@Override
	public void updateCoupon(Coupon coupon) throws CouponException {
		//creating the resources
				PreparedStatement preparedStatement = null;
				Connection connection = null;
				ResultSet resultSet = null;
				
				try
				{
					//getting a connection from the connection manager
					connection = JdbcUtils.getConnection();
					//creating the SQL query
					//id,title, startDate,endDate,amount,type,massage,price,image,companyRef
					String sql = "update coupons set endDate=?,price =? where id=?";
					// Creating a statement object which holds the SQL we're about to execute and asking for the generated keys back
					preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					
					//asking to Replace the question marks in the statement above with the following data
					preparedStatement.setString(1, coupon.getEndDate());
					preparedStatement.setDouble(2, coupon.getPrice());
					
					preparedStatement.setLong(3, coupon.getId());
					
					//executing the update
					preparedStatement.executeUpdate();
					
				}
				catch(Exception e)
				{
					writeToLog(e);
					throw new GeneralException(FinalsClass.UNAVAILABLE_SERVICE,errorType.INTERNAL_ERROR);
				}
				
				finally
				{
					JdbcUtils.closeResources(connection, preparedStatement, resultSet);
				}
		
	}

	/*
	 * Public function: get coupon by id
	 * retrive coupon by id
	 * 
	 * Input: coupon id
	 * 
	 */
	public Coupon getCouponByID(long couponId) throws CouponException {
		//creating the resources
				PreparedStatement preparedStatement = null;
				Connection connection = null;
				ResultSet resultSet = null;
				
				try {
				//getting a connection from the connection manager
				connection = JdbcUtils.getConnection();
				
				//creating the SQL query
				String sql = "SELECT * from coupons WHERE ID=?";
				
				// Creating a statement object which holds the SQL we're about to execute and asking for the generated keys back
				preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				
				//asking to Replace the question marks in the SQL statement above with with the following data
				preparedStatement.setLong(1, couponId);
				
				//if there are no results, return null
				resultSet = preparedStatement.executeQuery();
				if (!resultSet.next()) 
				{
					return null;
				}
				
				//if there are results, get them and put them in a Company object in resultSet.
				Coupon coupon = new Coupon();
				
				coupon.setId(resultSet.getLong("id"));
				coupon.setTitle(resultSet.getString("title"));
				coupon.setStartDate(resultSet.getString("startDate"));
				coupon.setEndDate(resultSet.getString("endDate"));
				coupon.setAmount(resultSet.getInt("amount"));
				coupon.setType(couponType.valueOf(resultSet.getString("type")));
				coupon.setMassage(resultSet.getString("massage"));
				coupon.setPrice(resultSet.getDouble("price"));
				coupon.setImage(resultSet.getString("image"));
				coupon.setCompanyRef(resultSet.getLong("companyRef"));
				
				return coupon;
				//if there was an exception in the "try" block above, it is caught here and notifies a level above.
				} 
				catch (SQLException e) 
				{
					writeToLog(e);
					throw new GeneralException(FinalsClass.UNAVAILABLE_SERVICE,errorType.INTERNAL_ERROR);
				} 
				finally 
				{
					//closing the resources
					JdbcUtils.closeResources(connection, preparedStatement, resultSet);
				}
				
	}

	/*
	 * Public function: get coupon by name
	 * retrive coupon by name
	 * 
	 * Input: coupon name
	 * 
	 */
	public Coupon getCouponByName(String couponName) throws CouponException{
		//creating the resources
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		ResultSet resultSet = null;
		
		try {
		//getting a connection from the connection manager
		connection = JdbcUtils.getConnection();
		
		//creating the SQL query
		String sql = "SELECT * from coupons WHERE title=?";
		
		// Creating a statement object which holds the SQL we're about to execute and asking for the generated keys back
		preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		//asking to Replace the question marks in the SQL statement above with with the following data
		preparedStatement.setString(1, couponName);
		
		//if there are no results, return null
		resultSet = preparedStatement.executeQuery();
		if (!resultSet.next()) 
		{
			return null;
		}
		
		//if there are results, get them and put them in a Company object in resultSet.
		Coupon coupon = new Coupon();
		
		coupon.setId(resultSet.getLong("id"));
		coupon.setTitle(resultSet.getString("title"));
		coupon.setStartDate(resultSet.getString("startDate"));
		coupon.setEndDate(resultSet.getString("endDate"));
		coupon.setAmount(resultSet.getInt("amount"));
		coupon.setType(couponType.valueOf(resultSet.getString("type")));
		coupon.setMassage(resultSet.getString("massage"));
		coupon.setPrice(resultSet.getDouble("price"));
		coupon.setImage(resultSet.getString("image"));
		coupon.setCompanyRef(resultSet.getLong("companyRef"));
		
		return coupon;
		//if there was an exception in the "try" block above, it is caught here and notifies a level above.
		} 
		catch (SQLException e) 
		{
			writeToLog(e);
			throw new GeneralException(FinalsClass.UNAVAILABLE_SERVICE,errorType.INTERNAL_ERROR);
		} 
		finally 
		{
			//closing the resources
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
		
	}

	/*
	 * Public function: get all the coupons
	 * retrive all the coupons 
	 * 
	 * Input: no input
	 * 
	 */
	@Override
	public Collection<Coupon> getAllCoupon() throws CouponException {
		//creating the resources
				PreparedStatement preparedStatement = null;
				Connection connection = null;
				ResultSet resultSet = null;
				ArrayList <Coupon> coupons = new ArrayList<Coupon>();
				
				try {
				//getting a connection from the connection manager
				connection = JdbcUtils.getConnection();
				
				//creating the SQL query
				String sql = "SELECT * from coupons";
				
				// Creating a statement object which holds the SQL we're about to execute and asking for the generated keys back
				preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				
				//if there are no results, return null
				resultSet = preparedStatement.executeQuery();
				if (!resultSet.next()) 
				{
					return null;
				}
				
				//if there are results, get them and put them in a Company object in resultSet.
				do
				{
					Coupon coupon = new Coupon();
					
					coupon.setId(resultSet.getLong("id"));
					coupon.setTitle(resultSet.getString("title"));
					coupon.setStartDate(resultSet.getString("startDate"));
					coupon.setEndDate(resultSet.getString("endDate"));
					coupon.setAmount(resultSet.getInt("amount"));
					coupon.setType(couponType.valueOf(resultSet.getString("type")));
					coupon.setMassage(resultSet.getString("massage"));
					coupon.setPrice(resultSet.getDouble("price"));
					coupon.setImage(resultSet.getString("image"));
					coupon.setCompanyRef(resultSet.getLong("companyRef"));
					
					coupons.add(coupon);	
				}
				while(resultSet.next());
					
				//if there was an exception in the "try" block above, it is caught here and notifies a level above.
				} 
				catch (SQLException e) 
				{
					writeToLog(e);
					throw new GeneralException(FinalsClass.UNAVAILABLE_SERVICE,errorType.INTERNAL_ERROR);
				} 
				finally 
				{
					//closing the resources
					JdbcUtils.closeResources(connection, preparedStatement, resultSet);
				}
				return coupons;
	}

	/*
	 * Public function: get coupon by coupon type
	 * retrive all the coupons by coupon type 
	 * 
	 * Input: coupon type
	 * 
	 */
	@Override
	public Collection<Coupon> getCouponByType(couponType type) throws CouponException {

		//creating the resources
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		ResultSet resultSet = null;
		ArrayList <Coupon> coupons = new ArrayList<Coupon>();
		
		try {
		//getting a connection from the connection manager
		connection = JdbcUtils.getConnection();
		
		//creating the SQL query
		String sql = "SELECT * from coupons where type = ?";
		
		// Creating a statement object which holds the SQL we're about to execute and asking for the generated keys back
		preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		preparedStatement.setString(1,type.toString() );
		
		//if there are no results, return null
		resultSet = preparedStatement.executeQuery();
		if (!resultSet.next()) 
		{
			return null;
		}
		
		//if there are results, get them and put them in a Company object in resultSet.
		do
		{
			//id,title, startDate,endDate,amount,type,massage,price,image,companyRef
			Coupon coupon = new Coupon();
			
			coupon.setId(resultSet.getLong("id"));
			coupon.setTitle(resultSet.getString("title"));
			coupon.setStartDate(resultSet.getString("startDate"));
			coupon.setEndDate(resultSet.getString("endDate"));
			coupon.setAmount(resultSet.getInt("amount"));
			coupon.setType(couponType.valueOf(resultSet.getString("type")));
			coupon.setMassage(resultSet.getString("massage"));
			coupon.setPrice(resultSet.getDouble("price"));
			coupon.setImage(resultSet.getString("image"));
			coupon.setCompanyRef(resultSet.getLong("companyRef"));
			
			coupons.add(coupon);	
		}
		while(resultSet.next());
			
		//if there was an exception in the "try" block above, it is caught here and notifies a level above.
		} 
		catch (SQLException e) 
		{
			writeToLog(e);
			throw new GeneralException(FinalsClass.UNAVAILABLE_SERVICE,errorType.INTERNAL_ERROR);
		} 
		finally 
		{
			//closing the resources
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
		return coupons;
	}
	
	/*
	 * Public function: get coupon by company
	 * retrive all the coupons by company
	 * 
	 * Input: company ID
	 * 
	 */
	public Collection<Coupon> getCouponsByCompany(long companyId) throws CouponException {
		//creating the resources
				PreparedStatement preparedStatement = null;
				Connection connection = null;
				ResultSet resultSet = null;
				ArrayList <Coupon> coupons = new ArrayList<Coupon>();
				
				try {
				//getting a connection from the connection manager
				connection = JdbcUtils.getConnection();
				
				//creating the SQL query
				String sql = "SELECT * from coupons where companyRef = ?";
				
				// Creating a statement object which holds the SQL we're about to execute and asking for the generated keys back
				preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				
				preparedStatement.setLong(1,companyId );
				
				//if there are no results, return null
				resultSet = preparedStatement.executeQuery();
				if (!resultSet.next()) 
				{
					return null;
				}
				
				//if there are results, get them and put them in a Company object in resultSet.
				do
				{
					//id,title, startDate,endDate,amount,type,massage,price,image,companyRef
					Coupon coupon = new Coupon();
					
					coupon.setId(resultSet.getLong("id"));
					coupon.setTitle(resultSet.getString("title"));
					coupon.setStartDate(resultSet.getString("startDate"));
					coupon.setEndDate(resultSet.getString("endDate"));
					coupon.setAmount(resultSet.getInt("amount"));
					coupon.setType(couponType.valueOf(resultSet.getString("type")));
					coupon.setMassage(resultSet.getString("massage"));
					coupon.setPrice(resultSet.getDouble("price"));
					coupon.setImage(resultSet.getString("image"));
					coupon.setCompanyRef(resultSet.getLong("companyRef"));
					
					coupons.add(coupon);	
				}
				while(resultSet.next());
					
				//if there was an exception in the "try" block above, it is caught here and notifies a level above.
				} 
				catch (SQLException e) 
				{
					writeToLog(e);
					throw new GeneralException(FinalsClass.UNAVAILABLE_SERVICE,errorType.INTERNAL_ERROR);
				} 
				finally 
				{
					//closing the resources
					JdbcUtils.closeResources(connection, preparedStatement, resultSet);
				}
				return coupons;
	}

	/*
	 * Public function: get coupon by customer id
	 * retrive all the coupons by customer id
	 * 
	 * Input: customer ID
	 * 
	 */
	@Override
	public Collection<Coupon> getCouponsByCustomer(long customerId) throws CouponException {
		//creating the resources
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		ResultSet resultSet = null;
		ArrayList <Coupon> coupons = new ArrayList<Coupon>();
		
		try {
		//getting a connection from the connection manager
		connection = JdbcUtils.getConnection();
		
		//creating the SQL query
		String sql = "SELECT * from coupons "
				+ "where id in "
				+ "(select  couponID from customer_coupons where customerID = ?) ";
		
		// Creating a statement object which holds the SQL we're about to execute and asking for the generated keys back
		preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		preparedStatement.setLong(1,customerId );
		
		//if there are no results, return null
		resultSet = preparedStatement.executeQuery();
		if (!resultSet.next()) 
		{
			return null;
		}
		
		//if there are results, get them and put them in a Company object in resultSet.
		do
		{
			//id,title, startDate,endDate,amount,type,massage,price,image,companyRef
			Coupon coupon = new Coupon();
			
			coupon.setId(resultSet.getLong("id"));
			coupon.setTitle(resultSet.getString("title"));
			coupon.setStartDate(resultSet.getString("startDate"));
			coupon.setEndDate(resultSet.getString("endDate"));
			coupon.setAmount(resultSet.getInt("amount"));
			coupon.setType(couponType.valueOf(resultSet.getString("type")));
			coupon.setMassage(resultSet.getString("massage"));
			coupon.setPrice(resultSet.getDouble("price"));
			coupon.setImage(resultSet.getString("image"));
			coupon.setCompanyRef(resultSet.getLong("companyRef"));
			
			coupons.add(coupon);	
		}
		while(resultSet.next());
			
		//if there was an exception in the "try" block above, it is caught here and notifies a level above.
		} 
		catch (SQLException e) 
		{
			writeToLog(e);
			throw new GeneralException(FinalsClass.UNAVAILABLE_SERVICE,errorType.INTERNAL_ERROR);
		} 
		finally 
		{
			//closing the resources
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
		return coupons;
	}

	/*
	 * Public function: update customer coupon
	 * update the join table of coupons and customers
	 * 
	 * Input: customer ID, coupon ID
	 * 
	 */
	public void updateCustomerCoupon(long customerId,long couponId) throws CouponException {
		//creating the resources
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		ResultSet resultSet = null;
		
		try
		{
			//getting a connection from the connection manager
			connection = JdbcUtils.getConnection();
			//creating the SQL query
			//id,title, startDate,endDate,amount,type,massage,price,image,companyRef
			String sql = "insert into customer_coupons (customerID,couponID) VALUES(?,?)";
			// Creating a statement object which holds the SQL we're about to execute and asking for the generated keys back
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			//asking to Replace the question marks in the statement above with the following data
			preparedStatement.setLong(1,customerId);
			preparedStatement.setLong(2,couponId);	
			
			//executing the update
			preparedStatement.executeUpdate();
			
		}
		catch(Exception e)
		{
			writeToLog(e);
			throw new GeneralException(FinalsClass.UNAVAILABLE_SERVICE,errorType.INTERNAL_ERROR);
		}
		
		finally
		{
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
	}

	/*
	 * Public function: update coupon quantity
	 * update quantity of coupon
	 * 
	 * Input: coupon ID quantity - new quantity of th coupon
	 * 
	 */
	public void updateCouponQuantity(long couponID,int quantity) throws CouponException{
		//creating the resources
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		ResultSet resultSet = null;
		
		try
		{
			//getting a connection from the connection manager
			connection = JdbcUtils.getConnection();
			//creating the SQL query
			//id,title, startDate,endDate,amount,type,massage,price,image,companyRef
			String sql = "update coupons set amount=?  where id=?";
			// Creating a statement object which holds the SQL we're about to execute and asking for the generated keys back
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			//asking to Replace the question marks in the statement above with the following data
			preparedStatement.setLong(1,quantity );
			preparedStatement.setLong(2, couponID);
			
			//executing the update
			preparedStatement.executeUpdate();

		}
		catch(Exception e)
		{
			writeToLog(e);
			throw new GeneralException(FinalsClass.UNAVAILABLE_SERVICE,errorType.INTERNAL_ERROR);
		}
		
		finally
		{
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
	}

	/*
	 * Public function: remove customer coupon by coupon
	 * remove from the join table of coupons and customers
	 * 
	 * Input: coupon ID
	 * 
	 */
	public void removeCustomerCouponByCoupn(long couponId) throws CouponException {
		//creating the resources
				PreparedStatement preparedStatement = null;
				Connection connection = null;
				ResultSet resultSet = null;
				
				try
				{
					//getting a connection from the connection manager
					connection = JdbcUtils.getConnection();
					//creating the SQL query
					//id,title, startDate,endDate,amount,type,massage,price,image,companyRef
					String sql = "delete from customer_coupons where couponID = ?";
					// Creating a statement object which holds the SQL we're about to execute and asking for the generated keys back
					preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					
					//asking to Replace the question marks in the statement above with the following data
					preparedStatement.setLong(1,couponId);	
					
					//executing the update
					preparedStatement.executeUpdate();
					
				}
				catch(Exception e)
				{
					writeToLog(e);
					throw new GeneralException(FinalsClass.UNAVAILABLE_SERVICE,errorType.INTERNAL_ERROR);
				}
				
				finally
				{
					JdbcUtils.closeResources(connection, preparedStatement, resultSet);
				}
	}

	/*
	 * Public function: remove customer coupon by customer
	 * remove from the join table of coupons and customers
	 * 
	 * Input: customer ID
	 * 
	 */
	public void removeCustomerCouponByCustomer(long customerId) throws CouponException{
		//creating the resources
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		ResultSet resultSet = null;
		
		try
		{
			//getting a connection from the connection manager
			connection = JdbcUtils.getConnection();
			//creating the SQL query
			//id,title, startDate,endDate,amount,type,massage,price,image,companyRef
			String sql = "delete from customer_coupons where customerID = ?";
			// Creating a statement object which holds the SQL we're about to execute and asking for the generated keys back
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			//asking to Replace the question marks in the statement above with the following data
			preparedStatement.setLong(1,customerId);	
			
			//executing the update
			preparedStatement.executeUpdate();
			
		}
		catch(Exception e)
		{
			writeToLog(e);
			throw new GeneralException(FinalsClass.UNAVAILABLE_SERVICE,errorType.INTERNAL_ERROR);
		}
		
		finally
		{
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
}

	/*
	 * Public function: get coupons by max price 
	 * retrieve all the coupons by max price and company id 
	 * 
	 * Input: companyID,price
	 * 
	 */
	@Override
	public Collection<Coupon> getCouponsByCompanyAndMaxPrice(long companyId,double price) throws CouponException {
		//creating the resources
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		ResultSet resultSet = null;
		ArrayList <Coupon> coupons = new ArrayList<Coupon>();
		
		try {
		//getting a connection from the connection manager
		connection = JdbcUtils.getConnection();
		
		//creating the SQL query
		String sql = "SELECT * from coupons where price <= ? and companyRef = ? ";
		
		// Creating a statement object which holds the SQL we're about to execute and asking for the generated keys back
		preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		preparedStatement.setDouble(1, price);
		preparedStatement.setLong(2,companyId );
		
		//if there are no results, return null
		resultSet = preparedStatement.executeQuery();
		if (!resultSet.next()) 
		{
			return null;
		}
		
		//if there are results, get them and put them in a Company object in resultSet.
		do
		{
			//id,title, startDate,endDate,amount,type,massage,price,image,companyRef
			Coupon coupon = new Coupon();
			
			coupon.setId(resultSet.getLong("id"));
			coupon.setTitle(resultSet.getString("title"));
			coupon.setStartDate(resultSet.getString("startDate"));
			coupon.setEndDate(resultSet.getString("endDate"));
			coupon.setAmount(resultSet.getInt("amount"));
			coupon.setType(couponType.valueOf(resultSet.getString("type")));
			coupon.setMassage(resultSet.getString("massage"));
			coupon.setPrice(resultSet.getDouble("price"));
			coupon.setImage(resultSet.getString("image"));
			coupon.setCompanyRef(resultSet.getLong("companyRef"));
			
			coupons.add(coupon);	
		}
		while(resultSet.next());
			
		//if there was an exception in the "try" block above, it is caught here and notifies a level above.
		} 
		catch (SQLException e) 
		{
			writeToLog(e);
			throw new GeneralException(FinalsClass.UNAVAILABLE_SERVICE,errorType.INTERNAL_ERROR);
		} 
		finally 
		{
			//closing the resources
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
		return coupons;
	}

	/*
	 * Public function: get coupons by expieretion Day 
	 * retrieve all the coupons by expiry date and company id 
	 * 
	 * Input: companyID,expiery date
	 * 
	 */
	@Override
	public Collection<Coupon> getCouponsByCompanyAndExpieretionDay(long companyId,String endDate) throws CouponException {
		//creating the resources
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		ResultSet resultSet = null;
		ArrayList <Coupon> coupons = new ArrayList<Coupon>();
		
		try {
		//getting a connection from the connection manager
		connection = JdbcUtils.getConnection();
		
		//creating the SQL query
		String sql = "select *"
				+" from Coupons"
				+" where DATEDIFF(endDate,?)<1"
				+" and companyRef= ?";
		
		// Creating a statement object which holds the SQL we're about to execute and asking for the generated keys back
		preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		preparedStatement.setString(1,endDate);
		preparedStatement.setLong(2,companyId );
		
		//if there are no results, return null
		resultSet = preparedStatement.executeQuery();
		if (!resultSet.next()) 
		{
			return null;
		}
		
		//if there are results, get them and put them in a Company object in resultSet.
		do
		{
			//id,title, startDate,endDate,amount,type,massage,price,image,companyRef
			Coupon coupon = new Coupon();
			
			coupon.setId(resultSet.getLong("id"));
			coupon.setTitle(resultSet.getString("title"));
			coupon.setStartDate(resultSet.getString("startDate"));
			coupon.setEndDate(resultSet.getString("endDate"));
			coupon.setAmount(resultSet.getInt("amount"));
			coupon.setType(couponType.valueOf(resultSet.getString("type")));
			coupon.setMassage(resultSet.getString("massage"));
			coupon.setPrice(resultSet.getDouble("price"));
			coupon.setImage(resultSet.getString("image"));
			coupon.setCompanyRef(resultSet.getLong("companyRef"));
			
			coupons.add(coupon);	
		}
		while(resultSet.next());
			
		//if there was an exception in the "try" block above, it is caught here and notifies a level above.
		} 
		catch (SQLException e) 
		{
			writeToLog(e);
			throw new GeneralException(FinalsClass.UNAVAILABLE_SERVICE,errorType.INTERNAL_ERROR);
		} 
		finally 
		{
			//closing the resources
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
		return coupons;
	}

	/*
	 * Public function: get History By type
	 * retrieve thre purche history of the customer by coupon type
	 * 
	 * Input: companyID,coupon type
	 * 
	 */
	@Override
	public Collection<Coupon> getHistoryCouponsByType(long customerId,couponType type) throws CouponException {
		//creating the resources
				PreparedStatement preparedStatement = null;
				Connection connection = null;
				ResultSet resultSet = null;
				ArrayList <Coupon> coupons = new ArrayList<Coupon>();
				
				try {
				//getting a connection from the connection manager
				connection = JdbcUtils.getConnection();
				
				//creating the SQL query
				String sql = "select *" 
						+" from Coupons" 
						+ " where id in (select couponID from customer_coupons where customerID = ?)"
						+ " and type = ?";
				
				// Creating a statement object which holds the SQL we're about to execute and asking for the generated keys back
				preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				
				preparedStatement.setLong(1,customerId);
				preparedStatement.setString(2,type.toString() );
				
				//if there are no results, return null
				resultSet = preparedStatement.executeQuery();
				if (!resultSet.next()) 
				{
					return null;
				}
				
				//if there are results, get them and put them in a Company object in resultSet.
				do
				{
					//id,title, startDate,endDate,amount,type,massage,price,image,companyRef
					Coupon coupon = new Coupon();
					coupon.setId(resultSet.getLong("id"));
					coupon.setTitle(resultSet.getString("title"));
					coupon.setStartDate(resultSet.getString("startDate"));
					coupon.setEndDate(resultSet.getString("endDate"));
					coupon.setAmount(resultSet.getInt("amount"));
					coupon.setType(couponType.valueOf(resultSet.getString("type")));
					coupon.setMassage(resultSet.getString("massage"));
					coupon.setPrice(resultSet.getDouble("price"));
					coupon.setImage(resultSet.getString("image"));
					coupon.setCompanyRef(resultSet.getLong("companyRef"));
					
					coupons.add(coupon);	
				}
				while(resultSet.next());
					
				//if there was an exception in the "try" block above, it is caught here and notifies a level above.
				} 
				catch (SQLException e) 
				{
					writeToLog(e);
					throw new GeneralException(FinalsClass.UNAVAILABLE_SERVICE,errorType.INTERNAL_ERROR);
				} 
				finally 
				{
					//closing the resources
					JdbcUtils.closeResources(connection, preparedStatement, resultSet);
				}
				return coupons;
	}

	/*
	 * Public function: get History By price
	 * retrieve thre purche history of the customer by coupon price
	 * 
	 * Input: companyID,coupon price 
	 * 
	 */
	@Override
	public Collection<Coupon> getHistoryCouponsByPrice(long customerId,double price) throws CouponException {
		//creating the resources
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		ResultSet resultSet = null;
		ArrayList <Coupon> coupons = new ArrayList<Coupon>();
		
		try {
		//getting a connection from the connection manager
		connection = JdbcUtils.getConnection();
		
		//creating the SQL query
		String sql = "select *" 
				+" from Coupons" 
				+ " where id in (select couponID from customer_coupons where customerID = ?)"
				+ " and price = ?";
		
		// Creating a statement object which holds the SQL we're about to execute and asking for the generated keys back
		preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		preparedStatement.setLong(1,customerId);
		preparedStatement.setDouble(2,price);
		
		//if there are no results, return null
		resultSet = preparedStatement.executeQuery();
		if (!resultSet.next()) 
		{
			return null;
		}
		
		//if there are results, get them and put them in a Company object in resultSet.
		do
		{
			//id,title, startDate,endDate,amount,type,massage,price,image,companyRef
			Coupon coupon = new Coupon();
			coupon.setId(resultSet.getLong("id"));
			coupon.setTitle(resultSet.getString("title"));
			coupon.setStartDate(resultSet.getString("startDate"));
			coupon.setEndDate(resultSet.getString("endDate"));
			coupon.setAmount(resultSet.getInt("amount"));
			coupon.setType(couponType.valueOf(resultSet.getString("type")));
			coupon.setMassage(resultSet.getString("massage"));
			coupon.setPrice(resultSet.getDouble("price"));
			coupon.setImage(resultSet.getString("image"));
			coupon.setCompanyRef(resultSet.getLong("companyRef"));
			
			coupons.add(coupon);	
		}
		while(resultSet.next());
			
		//if there was an exception in the "try" block above, it is caught here and notifies a level above.
		} 
		catch (SQLException e) 
		{
			writeToLog(e);
			throw new GeneralException(FinalsClass.UNAVAILABLE_SERVICE,errorType.INTERNAL_ERROR);
		} 
		finally 
		{
			//closing the resources
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
		return coupons;
	}

	/*
	 * Public function: get coupons by type and copmany
	 * retrieve all the coupons by type and company
	 * 
	 * Input: companyID,coupon type
	 * 
	 */
	@Override
	public Collection<Coupon> getCouponsByCompanyAndType(long companyId, couponType type) throws CouponException {
		//creating the resources
				PreparedStatement preparedStatement = null;
				Connection connection = null;
				ResultSet resultSet = null;
				ArrayList <Coupon> coupons = new ArrayList<Coupon>();
				
				try {
				//getting a connection from the connection manager
				connection = JdbcUtils.getConnection();
				
				//creating the SQL query
				String sql = "SELECT * from coupons where type = ? and companyRef = ? ";
				
				// Creating a statement object which holds the SQL we're about to execute and asking for the generated keys back
				preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				
				preparedStatement.setString(1,type.toString());
				preparedStatement.setLong(2,companyId );
				
				//if there are no results, return null
				resultSet = preparedStatement.executeQuery();
				if (!resultSet.next()) 
				{
					return null;
				}
				
				//if there are results, get them and put them in a Company object in resultSet.
				do
				{
					//id,title, startDate,endDate,amount,type,massage,price,image,companyRef
					Coupon coupon = new Coupon();
					coupon.setId(resultSet.getLong("id"));
					coupon.setTitle(resultSet.getString("title"));
					coupon.setStartDate(resultSet.getString("startDate"));
					coupon.setEndDate(resultSet.getString("endDate"));
					coupon.setAmount(resultSet.getInt("amount"));
					coupon.setType(couponType.valueOf(resultSet.getString("type")));
					coupon.setMassage(resultSet.getString("massage"));
					coupon.setPrice(resultSet.getDouble("price"));
					coupon.setImage(resultSet.getString("image"));
					coupon.setCompanyRef(resultSet.getLong("companyRef"));
					
					coupons.add(coupon);	
				}
				while(resultSet.next());
					
				//if there was an exception in the "try" block above, it is caught here and notifies a level above.
				} 
				catch (SQLException e) 
				{
					writeToLog(e);
					throw new GeneralException(FinalsClass.UNAVAILABLE_SERVICE,errorType.INTERNAL_ERROR);
				} 
				finally 
				{
					//closing the resources
					JdbcUtils.closeResources(connection, preparedStatement, resultSet);
				}
				return coupons;
	}
	
	/**
	 * Retrieve all the expierd coupons
	 */
	@Override
	public Collection<Coupon> getAllExpieryCoupon() throws CouponException {
		//creating the resources
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		ResultSet resultSet = null;
		ArrayList <Coupon> coupons = new ArrayList<Coupon>();
		
		try {
		//getting a connection from the connection manager
		connection = JdbcUtils.getConnection();
		
		//creating the SQL query
		String sql = "SELECT * from coupons WHERE endDate <CURDATE()";
		
		// Creating a statement object which holds the SQL we're about to execute and asking for the generated keys back
		preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		//if there are no results, return null
		resultSet = preparedStatement.executeQuery();
		if (!resultSet.next()) 
		{
			return null;
		}
		
		//if there are results, get them and put them in a Company object in resultSet.
		do
		{
			Coupon coupon = new Coupon();
			
			coupon.setId(resultSet.getLong("id"));
			coupon.setTitle(resultSet.getString("title"));
			coupon.setStartDate(resultSet.getString("startDate"));
			coupon.setEndDate(resultSet.getString("endDate"));
			coupon.setAmount(resultSet.getInt("amount"));
			coupon.setType(couponType.valueOf(resultSet.getString("type")));
			coupon.setMassage(resultSet.getString("massage"));
			coupon.setPrice(resultSet.getDouble("price"));
			coupon.setImage(resultSet.getString("image"));
			coupon.setCompanyRef(resultSet.getLong("companyRef"));
			
			coupons.add(coupon);	
		}
		while(resultSet.next());
			
		//if there was an exception in the "try" block above, it is caught here and notifies a level above.
		} 
		catch (SQLException e) 
		{
			writeToLog(e);
			throw new GeneralException(FinalsClass.UNAVAILABLE_SERVICE,errorType.INTERNAL_ERROR);
		} 
		finally 
		{
			//closing the resources
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
		return coupons;
	}
	
}

