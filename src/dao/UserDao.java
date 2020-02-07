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
	 private static Connection connectionUser = null;
	 private static Statement statementUser= null;
	 private static ResultSet rsUser= null;
	 
	 static Logger logger = Logger.getAnonymousLogger();
	 private static final String CONTEXT = "context";
	
	  public static User loginDao(String username, String password) {
		  User u = null;
			try {
				
				connectionUser = DriverManager.getConnection(URL, USER, PASS);
				statementUser = connectionUser.createStatement();
				String sqlLogin = String.format(Query.LOGINQUERY, username, password);
				rsUser = statementUser.executeQuery(sqlLogin);
				
				if(rsUser.next()) {
					u = new User(username, password, rsUser.getString("email"));
				}
			} catch(Exception eLogin) {
				logger.log(null, CONTEXT,eLogin);
			} finally {
				try {
					if(connectionUser != null)
						connectionUser.close();
					if(statementUser != null)
						statementUser.close();
					if(rsUser != null)
						rsUser.close();
				} catch (SQLException eLogin) {
					logger.log(null, CONTEXT,eLogin);
				}
			}
			return u;
		}
	  
	  
	  
	
	  public static boolean registrationDao(String username, String password, String email) {
		 
	        try {
	           
	            connectionUser = DriverManager.getConnection(URL, USER, PASS);
	            statementUser = connectionUser.createStatement();
	            String sqlRegistration= String.format(Query.REGQUERY, username, password, email);
	            statementUser = connectionUser.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	            int rsRegistration = statementUser.executeUpdate(sqlRegistration);

	            if (rsRegistration != 1) {
	             
	                return false;
	            }

	            statementUser.close();
	            connectionUser.close();
	            return true;

	        } catch (SQLException seRegistration) {
	        	logger.log(null, CONTEXT,seRegistration);
	        } catch (Exception eRegistration) {
	        	logger.log(null, CONTEXT,eRegistration);
	        } finally {
	            try {
	                if (statementUser != null)
	                    statementUser.close();
	            } catch (SQLException se2Registration) {
	            	logger.log(null, CONTEXT,se2Registration);
	            }
	            try {
	                if (connectionUser != null)
	                    connectionUser.close();
	            } catch (SQLException seRegistration) {
	            	logger.log(null, CONTEXT,seRegistration);
	            }
	        }
	       
	        return false;
	    }

	  public static User foundUserDao(String username) {
			
			User u = null;
				try {
					
					connectionUser = DriverManager.getConnection(URL, USER, PASS);
					statementUser = connectionUser.createStatement();
					String sqlFoundUser = String.format(Query.FOUNDUSERQUERY, username);
					rsUser = statementUser.executeQuery(sqlFoundUser);
					
					if(rsUser.next()) {
						u = new User(username, rsUser.getString("password"), rsUser.getString("email"));
					}
				} catch(Exception eFoundUser) {
					logger.log(null, CONTEXT,eFoundUser);
				} finally {
					try {
						if(connectionUser != null)
							connectionUser.close();
						if(statementUser != null)
							statementUser.close();
						if(rsUser != null)
							rsUser.close();
					} catch (SQLException eFoundUser) {
						logger.log(null, CONTEXT,eFoundUser);
					}
				}
				return u;
			}




		public static boolean changePassword(String username, String password) {
			
		        try {
		          
		            connectionUser = DriverManager.getConnection(URL, USER, PASS);
		            statementUser = connectionUser.createStatement();
		            String sqlPassword= String.format(Query.CHANGEPASSWORDQUERY, password, username);
		            statementUser = connectionUser.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		            int rsPassword = statementUser.executeUpdate(sqlPassword);

		            if (rsPassword != 1) {
		                return false;
		            }

		            // STEP 6: Clean-up dell'ambiente
		            statementUser.close();
		            connectionUser.close();

		            return true;

		        } catch (SQLException sePassword) {
		        	logger.log(null, CONTEXT,sePassword);
		        } catch (Exception ePassword) {
		        	logger.log(null, CONTEXT,ePassword);
		        } finally {
		            try {
		                if (statementUser != null)
		                    statementUser.close();
		            } catch (SQLException se2Password) {
		            	logger.log(null, CONTEXT,se2Password);
		            }
		            try {
		                if (connectionUser != null)
		                    connectionUser.close();
		            } catch (SQLException sePassword) {
		            	logger.log(null, CONTEXT,sePassword);
		            }
		        }
		  
		        return false;
		    }
}
