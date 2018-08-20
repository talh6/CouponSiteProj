package com.tal.couponsproj.dao;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.tal.couponsproj.beans.Company;
import com.tal.couponsproj.enums.errorType;
import com.tal.couponsproj.exeptions.CouponException;
import com.tal.couponsproj.exeptions.GeneralException;
import com.tal.couponsproj.interfaces.ICompany;
import com.tal.couponsproj.logger.CouponsLogger;
import com.tal.couponsproj.utils.FinalsClass;
import com.tal.couponsproj.utils.JdbcUtils;
/*
 * Class : Company Dao
 * Aouther : Tal Hakmon
 * Version : 1.0
 * Date : 11.9.17
 */

public class CompanyDao implements ICompany{
	

	
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
	 * Public function: create company
	 * create a new company
	 * 
	 * Input: company
	 * 
	 */
	@Override
	public void createCompany(Company company) throws CouponException {
		//creating the resources
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		ResultSet resultSet = null;
		
		try
		{
			//getting a connection from the connection manager
			connection = JdbcUtils.getConnection();
			//creating the SQL query
			String sql = "insert into company (compName, password, email) values (?, ?, ?)";
			// Creating a statement object which holds the SQL we're about to execute and asking for the generated keys back
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			//asking to Replace the question marks in the statement above with the following data
			preparedStatement.setString(1, company.getCompName());
			preparedStatement.setString(2, company.getPassword());
			preparedStatement.setString(3, company.getEmail());
			
			
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
	 * Public function: remove company
	 * remove a company
	 * 
	 * Input: company id
	 * 
	 */
	@Override
	public void removeCompany(long companyId) throws CouponException {
		//creating the resources
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		ResultSet resultSet = null;
		
		try
		{
			//getting a connection from the connection manager
			connection = JdbcUtils.getConnection();
			//creating the SQL query
			String sql = "delete from company where ID = ?";
			// Creating a statement object which holds the SQL we're about to execute and asking for the generated keys back
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			//asking to Replace the question marks in the statement above with the following data
			preparedStatement.setLong(1, companyId);
			
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
	 * Public function: update company
	 * update a company
	 * 
	 * Input: company
	 * 
	 */
	@Override
	public void updateCompany(Company company) throws CouponException {
		//creating the resources
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		ResultSet resultSet = null;
		
		try
		{
			//getting a connection from the connection manager
			connection = JdbcUtils.getConnection();
			//creating the SQL query
			String sql = "update company set  password =?,email=? where ID=?";
			// Creating a statement object which holds the SQL we're about to execute and asking for the generated keys back
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			//asking to Replace the question marks in the statement above with the following data
			preparedStatement.setString(1, company.getPassword());
			preparedStatement.setString(2, company.getEmail());
			preparedStatement.setLong(3, company.getId());
			
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
	 * Public function: get company by name
	 * retrive company by name
	 * 
	 * Input: company name
	 * 
	 */
	public Company getCompanyByName(String companyName) throws CouponException{
		//creating the resources
				PreparedStatement preparedStatement = null;
				Connection connection = null;
				ResultSet resultSet = null;
				Company company = new Company();
				
				try {
				//getting a connection from the connection manager
				connection = JdbcUtils.getConnection();
				
				//creating the SQL query
				String sql = "SELECT * from company WHERE compName=?";
				
				// Creating a statement object which holds the SQL we're about to execute and asking for the generated keys back
				preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				
				//asking to Replace the question marks in the SQL statement above with with the following data
				preparedStatement.setString(1, companyName);
				
				//if there are no results, return null
				resultSet = preparedStatement.executeQuery();
				if (!resultSet.next()) 
				{
					return null;
				}
				
				//if there are results, get them and put them in a Company object in resultSet.
				company.setId(resultSet.getLong("ID"));
				company.setCompName(resultSet.getString("compName"));
				company.setPassword(resultSet.getString("password"));
				company.setEmail(resultSet.getString("email"));

				return company;
				
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
	 * Public function: get company by id
	 * retrive company by id
	 * 
	 * Input: company id
	 * 
	 */
	@Override
	public Company getCompanyByID(long companyId) throws CouponException {
		//creating the resources
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		ResultSet resultSet = null;
		Company company = new Company();
		
		try {
		//getting a connection from the connection manager
		connection = JdbcUtils.getConnection();
		
		//creating the SQL query
		String sql = "SELECT * from company WHERE ID=?";
		
		// Creating a statement object which holds the SQL we're about to execute and asking for the generated keys back
		preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		//asking to Replace the question marks in the SQL statement above with with the following data
		preparedStatement.setLong(1, companyId);
		
		//if there are no results, return null
		resultSet = preparedStatement.executeQuery();
		if (!resultSet.next()) 
		{
			return null;
		}
		
		//if there are results, get them and put them in a Company object in resultSet.
		company.setId(resultSet.getLong("ID"));
		company.setCompName(resultSet.getString("compName"));
		company.setPassword(resultSet.getString("password"));
		company.setEmail(resultSet.getString("email"));

		return company;
		
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
	 * Public function: get all the companies
	 * retrive all the companies 
	 * 
	 * Input: no input
	 * 
	 */
	@Override
	public Collection<Company> getAllCompanies() throws CouponException {
		//creating the resources
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		ResultSet resultSet = null;
		ArrayList <Company> compenies = new ArrayList<Company>();
		
		try {
		//getting a connection from the connection manager
		connection = JdbcUtils.getConnection();
		
		//creating the SQL query
		String sql = "SELECT * from company";
		
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
			Company company = new Company();
			company.setId(resultSet.getLong("ID"));
			company.setCompName(resultSet.getString("compName"));
			company.setPassword(resultSet.getString("password"));
			company.setEmail(resultSet.getString("email"));
			
			compenies.add(company);	
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
		return compenies;
	}
	
	/*
	 * Public function: login
	 * return true if the login information is excist else return false
	 * 
	 * Input: company name , password
	 * 
	 */
	@Override
	public boolean login(String compName, String password) throws CouponException {
		//creating the resources
				PreparedStatement preparedStatement = null;
				Connection connection = null;
				ResultSet resultSet = null;
				
				try {
				//getting a connection from the connection manager
				connection = JdbcUtils.getConnection();
				
				//creating the SQL query
				String sql = "SELECT * from company WHERE password=? and compName =?";
				
				// Creating a statement object which holds the SQL we're about to execute and asking for the generated keys back
				preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				
				//asking to Replace the question marks in the SQL statement above with with the following data
				preparedStatement.setString(1, password);
				preparedStatement.setString(2, compName);
				
				
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
	
}
