package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import model.UserProfile;
import utils.Query;

public class UserProfileDao {
	private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String USER = "postgres";
	private static final String PASS = "postgres";
	
	private static Connection connection = null;
	
	
	private static final String TITLE = "title";
	private static final String CATEGORY= "category";
	private static final String DIFFICULTY = "difficulty";
	private static final String PREPARATION = "preparation";
	private static final String NECESSARY = "necessary";
	private static final String USERNAME = "username";
	private static final String TIME = "time";
	
	 static Logger logger = Logger.getAnonymousLogger();
	 private static final String CONTEXT = "context";
	
	private UserProfileDao() {
	    throw new IllegalStateException("Utility class");
	  }
	
	
	 public static boolean saveRecipeDao(String title, String preparation, String difficulty, String category, String time, String necessary,String username) {
		  Statement statementSaveUserRecipe = null;
	      Connection connSaveUserRecipe = null;
	        try {
	           
	            connSaveUserRecipe = DriverManager.getConnection(URL, USER, PASS);
	            statementSaveUserRecipe = connSaveUserRecipe.createStatement();
	            String sql1SaveUserRecipe= String.format(Query.SAVEQUERY, title, preparation, difficulty,category,time,necessary,username);
	            statementSaveUserRecipe = connSaveUserRecipe.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	            int rsSaveUserRecipe = statementSaveUserRecipe.executeUpdate(sql1SaveUserRecipe);

	            if (rsSaveUserRecipe != 1) {
	               
	                return false;
	            }

	            // STEP 6: Clean-up dell'ambiente
	            statementSaveUserRecipe.close();
	            connSaveUserRecipe.close();
	            return true;

	        } catch (SQLException seSaveUserRecipe) {
	        	logger.log(null, CONTEXT,seSaveUserRecipe);
	        } catch (Exception eSaveUserRecipe) {
	            // Errore nel loading del driver
	        	logger.log(null, CONTEXT,eSaveUserRecipe);
	        } finally {
	            try {
	                if (statementSaveUserRecipe != null)
	                    statementSaveUserRecipe.close();
	            } catch (SQLException se2SaveUserRecipe) {
	            	logger.log(null, CONTEXT,se2SaveUserRecipe);
	            }
	            try {
	                if (connSaveUserRecipe != null)
	                    connSaveUserRecipe.close();
	            } catch (SQLException seSaveUserRecipe) {
	            	logger.log(null, CONTEXT,seSaveUserRecipe);
	            }
	        }
	      
	        return false;
	    }
	
	public static Set<UserProfile> userRecipeDao(String username) {
		 Statement statementUserRecipe = null;
		 UserProfile userprofile = null;
		 Set<UserProfile> up= new HashSet<>();
		 
			try {
			
				connection = DriverManager.getConnection(URL, USER, PASS);
				statementUserRecipe = connection.createStatement();
				
					
					String sqlUserRecipe = String.format(Query.PROFILEQUERY, username);
			
					ResultSet rsUserRecipe = statementUserRecipe.executeQuery(sqlUserRecipe);
					
					while(rsUserRecipe.next()) {
						
						userprofile = new UserProfile(rsUserRecipe.getString(TITLE), rsUserRecipe.getString(PREPARATION),
								rsUserRecipe.getString(DIFFICULTY), rsUserRecipe.getString(CATEGORY), rsUserRecipe.getString(TIME), rsUserRecipe.getString(NECESSARY),username);
			
								up.add(userprofile);
								
					}
				
		
			} catch(Exception eUserRecipe) {
				logger.log(null, CONTEXT,eUserRecipe);
			} finally {
				try {
					if(connection != null)
						connection.close();
					if(statementUserRecipe != null)
						statementUserRecipe.close();
				} catch (SQLException eUserRecipe) {
					logger.log(null, CONTEXT,eUserRecipe);
				}
			}
			return up;		 
	 }
	 public static UserProfile chooseUserRecipeDao(String title) {
		 Statement statementChooseUserRecipe = null;
		 UserProfile up = null;
			try {
			
				connection = DriverManager.getConnection(URL, USER, PASS);
				statementChooseUserRecipe = connection.createStatement();
					
					String sqlChooseUserRecipe = String.format(Query.USERRECIPEQUERY,title);
					
			
					ResultSet rsChooseUserRecipe = statementChooseUserRecipe.executeQuery(sqlChooseUserRecipe);
					
					if(rsChooseUserRecipe.next()) {
					
						up = new UserProfile(rsChooseUserRecipe.getString(TITLE), rsChooseUserRecipe.getString(PREPARATION),rsChooseUserRecipe.getString(DIFFICULTY),  rsChooseUserRecipe.getString(CATEGORY), rsChooseUserRecipe.getString(TIME),rsChooseUserRecipe.getString(NECESSARY),rsChooseUserRecipe.getString(USERNAME));
				
					}
				
			} catch(Exception eChooseUserRecipe) {
				logger.log(null, CONTEXT,eChooseUserRecipe);
			} finally {
				try {
					if(connection != null)
						connection.close();
					if(statementChooseUserRecipe != null)
						statementChooseUserRecipe.close();
				} catch (SQLException eChooseUserRecipe) {
					logger.log(null, CONTEXT,eChooseUserRecipe);
				}
			}
			return up;		 
	 }
	 
	 public static boolean deleteRecipeDao(String title, String username) {
		  Statement statementDeleteRecipe = null;
	      Connection connectionDeleteRecipe = null;
	        try {
	        
	            connectionDeleteRecipe = DriverManager.getConnection(URL, USER, PASS);
	            statementDeleteRecipe = connectionDeleteRecipe.createStatement();
	            String sql1DeleteRecipe= String.format(Query.DELETERECIPEQUERY,title,username);
	        
	            statementDeleteRecipe = connectionDeleteRecipe.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	            int rsDeleteRecipe = statementDeleteRecipe.executeUpdate(sql1DeleteRecipe);

	            if (rsDeleteRecipe != 1) {
	        
	                return false;
	            }

	            // STEP 6: Clean-up dell'ambiente
	            statementDeleteRecipe.close();
	            connectionDeleteRecipe.close();
	            return true;

	        } catch (SQLException seDeleteRecipe) {
	        	logger.log(null, CONTEXT,seDeleteRecipe);
	        } catch (Exception eDeleteRecipe) {
	        	logger.log(null, CONTEXT,eDeleteRecipe);
	        } finally {
	            try {
	                if (statementDeleteRecipe != null)
	                    statementDeleteRecipe.close();
	            } catch (SQLException se2DeleteRecipe) {
	            	logger.log(null, CONTEXT,se2DeleteRecipe);
	            }
	            try {
	                if (connectionDeleteRecipe != null)
	                    connectionDeleteRecipe.close();
	            } catch (SQLException seDeleteRecipe) {
	            	logger.log(null, CONTEXT,seDeleteRecipe);
	            }
	        }
	        return false;
	    }
public static UserProfile favRecipeDao(String username) {
		 
		 Statement statementFavRecipe = null;
		 UserProfile favRecipe= null;
		 
			try {
				
				connection = DriverManager.getConnection(URL, USER, PASS);
				statementFavRecipe = connection.createStatement();
			
				String sqlFavRecipe = String.format(Query.FAVQUERY, username);
				ResultSet rsFavRecipe = statementFavRecipe.executeQuery(sqlFavRecipe);
				
				while(rsFavRecipe.next()) {
				favRecipe = new UserProfile(rsFavRecipe.getString(TITLE), rsFavRecipe.getString(PREPARATION),
							 rsFavRecipe.getString(DIFFICULTY),  rsFavRecipe.getString(CATEGORY), rsFavRecipe.getString(TIME), rsFavRecipe.getString(NECESSARY),username);
				
				}
				
			} catch(Exception eFavRecipe) {
				logger.log(null, CONTEXT,eFavRecipe);				
			} finally {
				try {
					if(connection != null)
						connection.close();
					if(statementFavRecipe != null)
						statementFavRecipe.close();
				} catch (SQLException eFavRecipe) {
					logger.log(null, CONTEXT,eFavRecipe);
				}
			}
			return favRecipe;		 
	 }
	 
}
