package com.tal.couponsproj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import com.tal.couponsproj.utils.FinalsClass;

import com.tal.couponsproj.beans.Customer;
import com.tal.couponsproj.enums.errorType;
import com.tal.couponsproj.exeptions.CouponException;
import com.tal.couponsproj.exeptions.GeneralException;
import com.tal.couponsproj.interfaces.ICustomer;
import com.tal.couponsproj.logger.CouponsLogger;
import com.tal.couponsproj.utils.JdbcUtils;

public class CustomerDao implements ICustomer{

	/*
	 * Class : Customer Dao
	 * Aouther : Tal Hakmon
	 * Version : 1.0
	 * Date : 11.9.17
	 */
	
	
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
	 * Public function: create customer
	 * create a new customer
	 * 
	 * Input: customer
	 * 
	 */
	@Override
	public void createCustomer(Customer customer) throws CouponException {
		//creating the resources
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		ResultSet resultSet = null;
		
		try
		{
			//getting a connection from the connection manager
			connection = JdbcUtils.getConnection();
			//creating the SQL query
			String sql = "insert into customers (custName, userName,password ) values (?, ?, ?)";
			// Creating a statement object which holds the SQL we're about to execute and asking for the generated keys back
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			//asking to Replace the question marks in the statement above with the following data
			preparedStatement.setString(1, customer.getCustName());
			preparedStatement.setString(2, customer.getUserName());
			preparedStatement.setString(3, customer.getPassword());

			
			
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
	 * Public function: remove customer
	 * remove a customer
	 * 
	 * Input: customer id
	 * 
	 */
	@Override
	public void removeCustomer(long customerId) throws CouponException {
		//creating the resources
				PreparedStatement preparedStatement = null;
				Connection connection = null;
				ResultSet resultSet = null;
				
				try
				{
					//getting a connection from the connection manager
					connection = JdbcUtils.getConnection();
					//creating the SQL query
					String sql = "delete from customers where ID = ?";
					// Creating a statement object which holds the SQL we're about to execute and asking for the generated keys back
					preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					
					//asking to Replace the question marks in the statement above with the following data
					preparedStatement.setLong(1, customerId);
					
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
	 * Public function: update customer
	 * update a customer
	 * 
	 * Input: customer
	 * 
	 */
	@Override
	public void updateCustomer(Customer customer) throws CouponException {
		//creating the resources
				PreparedStatement preparedStatement = null;
				Connection connection = null;
				ResultSet resultSet = null;
				
				try
				{
					//getting a connection from the connection manager
					connection = JdbcUtils.getConnection();
					//creating the SQL query
					String sql = "update customers set userName =?,password=? where ID=?";
					// Creating a statement object which holds the SQL we're about to execute and asking for the generated keys back
					preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					
					//asking to Replace the question marks in the statement above with the following data
					preparedStatement.setString(1, customer.getUserName());
					preparedStatement.setString(2, customer.getPassword());
					preparedStatement.setLong(3, customer.getId());
					
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
	 * Public function: get customer by id
	 * retrive customer by id
	 * 
	 * Input: customer id
	 * 
	 */
	public Customer getCustomerByID(long customerId) throws CouponException {
		//creating the resources
				PreparedStatement preparedStatement = null;
				Connection connection = null;
				ResultSet resultSet = null;
				Customer customer = new Customer();
				
				try {
				//getting a connection from the connection manager
				connection = JdbcUtils.getConnection();
				
				//creating the SQL query
				String sql = "SELECT * from customers WHERE ID=?";
				
				// Creating a statement object which holds the SQL we're about to execute and asking for the generated keys back
				preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				
				//asking to Replace the question marks in the SQL statement above with with the following data
				preparedStatement.setLong(1, customerId);
				
				//if there are no results, return null
				resultSet = preparedStatement.executeQuery();
				if (!resultSet.next()) 
				{
					return null;
				}
				
				//if there are results, get them and put them in a Company object in resultSet.
				customer.setId(resultSet.getLong("ID"));
				customer.setCustName(resultSet.getString("custName"));
				customer.setPassword(resultSet.getString("password"));
				customer.setUserName(resultSet.getString("userName"));

				return customer;
				
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
	 * Public function: get customer by name
	 * retrive customer by name
	 * 
	 * Input: customer name
	 * 
	 */
	public Customer getCustomerByName(String customerName) throws CouponException{
		//creating the resources
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		ResultSet resultSet = null;
		Customer customer = new Customer();
		
		try {
		//getting a connection from the connection manager
		connection = JdbcUtils.getConnection();
		
		//creating the SQL query
		String sql = "SELECT * from customers WHERE custName=?";
		
		// Creating a statement object which holds the SQL we're about to execute and asking for the generated keys back
		preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		//asking to Replace the question marks in the SQL statement above with with the following data
		preparedStatement.setString(1, customerName);
		
		//if there are no results, return null
		resultSet = preparedStatement.executeQuery();
		if (!resultSet.next()) 
		{
			return null;
		}
		
		//if there are results, get them and put them in a Company object in resultSet.
		customer.setId(resultSet.getLong("ID"));
		customer.setCustName(resultSet.getString("custName"));
		customer.setPassword(resultSet.getString("password"));
		customer.setUserName(resultSet.getString("userName"));

		return customer;
		
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
	 * Public function: get all the customers
	 * retrive all the customers 
	 * 
	 * Input: no input
	 * 
	 */
	@Override
	public Collection<Customer> getAllCustomers() throws CouponException {
		//creating the resources
				PreparedStatement preparedStatement = null;
				Connection connection = null;
				ResultSet resultSet = null;
				ArrayList <Customer> customers = new ArrayList<Customer>();
				
				try {
				//getting a connection from the connection manager
				connection = JdbcUtils.getConnection();
				
				//creating the SQL query
				String sql = "SELECT * from customers";
				
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
					Customer customer = new Customer();
					customer.setId(resultSet.getLong("ID"));
					customer.setCustName(resultSet.getString("custName"));
					customer.setPassword(resultSet.getString("password"));
					customer.setUserName(resultSet.getString("userName"));
					
					customers.add(customer);	
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
				return customers;
	}

	/*
	 * Public function: login
	 * return true if the login information is excist else return false
	 * 
	 * Input: customer name , password
	 * 
	 */
	@Override
	public boolean login(String userName, String password) throws CouponException {
		//creating the resources
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		ResultSet resultSet = null;
		
		try {
		//getting a connection from the connection manager
		connection = JdbcUtils.getConnection();
		
		//creating the SQL query
		String sql = "SELECT * from customers WHERE password=? and userName =?";
		
		// Creating a statement object which holds the SQL we're about to execute and asking for the generated keys back
		preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		//asking to Replace the question marks in the SQL statement above with with the following data
		preparedStatement.setString(1, password);
		preparedStatement.setString(2, userName);
		
		
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
	 * Public function: get customer by username
	 * retrive customer by username
	 * 
	 * Input: customer username
	 * 
	 */
	@Override
     public Customer getCustomerByUsername(String username) throws CouponException {
		//creating the resources
				PreparedStatement preparedStatement = null;
				Connection connection = null;
				ResultSet resultSet = null;
				Customer customer = new Customer();
				
				try {
				//getting a connection from the connection manager
				connection = JdbcUtils.getConnection();
				
				//creating the SQL query
				String sql = "SELECT * from customers WHERE userName=?";
				
				// Creating a statement object which holds the SQL we're about to execute and asking for the generated keys back
				preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				
				//asking to Replace the question marks in the SQL statement above with with the following data
				preparedStatement.setString(1, username);
				
				//if there are no results, return null
				resultSet = preparedStatement.executeQuery();
				if (!resultSet.next()) 
				{
					return null;
				}
				
				//if there are results, get them and put them in a Company object in resultSet.
				customer.setId(resultSet.getLong("ID"));
				customer.setCustName(resultSet.getString("custName"));
				customer.setPassword(resultSet.getString("password"));
				customer.setUserName(resultSet.getString("userName"));

				return customer;
				
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

}
