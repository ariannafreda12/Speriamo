package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import model.User;
import utils.Query;


public class UserDao {
	
	
	private UserDao() {
		    throw new IllegalStateException("Utility class");
		  }
	
	
	 private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
	 private static final String USER = "postgres";
	 private static final String PASS = "postgres";
	 private static Connection connection = null;
	 
	 static Logger logger = Logger.getAnonymousLogger();
	 private static final String CONTEXT = "context";
	
	  public static User loginDao(String username, String password) {
		 
		  Statement statementLogin = null;
		  User u = null;
			try {
				
				connection = DriverManager.getConnection(URL, USER, PASS);
				statementLogin = connection.createStatement();
				String sqlLogin = String.format(Query.LOGINQUERY, username, password);
				ResultSet rsLogin = statementLogin.executeQuery(sqlLogin);
				
				if(rsLogin.next()) {
					u = new User(username, password, rsLogin.getString("email"));
				}
			} catch(Exception eLogin) {
				logger.log(null, CONTEXT,eLogin);
			} finally {
				try {
					if(connection != null)
						connection.close();
					if(statementLogin != null)
						statementLogin.close();
				} catch (SQLException eLogin) {
					logger.log(null, CONTEXT,eLogin);
				}
			}
			return u;
		}
	  
	  
	  
	
	  public static boolean registrationDao(String username, String password, String email) {
		  Statement statementRegistration = null;
	        Connection connecctionRegistration = null;
	        try {
	           
	            connecctionRegistration = DriverManager.getConnection(URL, USER, PASS);
	            statementRegistration = connecctionRegistration.createStatement();
	            String sqlRegistration= String.format(Query.REGQUERY, username, password, email);
	            statementRegistration = connecctionRegistration.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	            int rsRegistration = statementRegistration.executeUpdate(sqlRegistration);

	            if (rsRegistration != 1) {
	             
	                return false;
	            }

	            statementRegistration.close();
	            connecctionRegistration.close();
	            return true;

	        } catch (SQLException seRegistration) {
	        	logger.log(null, CONTEXT,seRegistration);
	        } catch (Exception eRegistration) {
	        	logger.log(null, CONTEXT,eRegistration);
	        } finally {
	            try {
	                if (statementRegistration != null)
	                    statementRegistration.close();
	            } catch (SQLException se2Registration) {
	            	logger.log(null, CONTEXT,se2Registration);
	            }
	            try {
	                if (connecctionRegistration != null)
	                    connecctionRegistration.close();
	            } catch (SQLException seRegistration) {
	            	logger.log(null, CONTEXT,seRegistration);
	            }
	        }
	       
	        return false;
	    }

	  public static User foundUserDao(String username) {
			Statement statementFoundUser = null;
			User u = null;
				try {
					
					connection = DriverManager.getConnection(URL, USER, PASS);
					statementFoundUser = connection.createStatement();
					String sqlFoundUser = String.format(Query.FOUNDUSERQUERY, username);
					ResultSet rsFoundUser = statementFoundUser.executeQuery(sqlFoundUser);
					
					if(rsFoundUser.next()) {
						u = new User(username, rsFoundUser.getString("password"), rsFoundUser.getString("email"));
					}
				} catch(Exception eFoundUser) {
					logger.log(null, CONTEXT,eFoundUser);
				} finally {
					try {
						if(connection != null)
							connection.close();
						if(statementFoundUser != null)
							statementFoundUser.close();
					} catch (SQLException eFoundUser) {
						logger.log(null, CONTEXT,eFoundUser);
					}
				}
				return u;
			}




		public static boolean changePassword(String username, String password) {
			 Statement statementPassword = null;
		        Connection connectionPassword = null;
		        try {
		          
		            connectionPassword = DriverManager.getConnection(URL, USER, PASS);
		            statementPassword = connectionPassword.createStatement();
		            String sqlPassword= String.format(Query.CHANGEPASSWORDQUERY, password, username);
		            statementPassword = connectionPassword.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		            int rsPassword = statementPassword.executeUpdate(sqlPassword);

		            if (rsPassword != 1) {
		                return false;
		            }

		            // STEP 6: Clean-up dell'ambiente
		            statementPassword.close();
		            connectionPassword.close();

		            return true;

		        } catch (SQLException sePassword) {
		        	logger.log(null, CONTEXT,sePassword);
		        } catch (Exception ePassword) {
		        	logger.log(null, CONTEXT,ePassword);
		        } finally {
		            try {
		                if (statementPassword != null)
		                    statementPassword.close();
		            } catch (SQLException se2Password) {
		            	logger.log(null, CONTEXT,se2Password);
		            }
		            try {
		                if (connectionPassword != null)
		                    connectionPassword.close();
		            } catch (SQLException sePassword) {
		            	logger.log(null, CONTEXT,sePassword);
		            }
		        }
		  
		        return false;
		    }
}
