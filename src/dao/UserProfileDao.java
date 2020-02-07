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
	
	private static Connection connectionProfile = null;
	private static Statement statementProfile = null;
	private static ResultSet rsProfile=null;
	
	
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
		 
	        try {
	           
	            connectionProfile = DriverManager.getConnection(URL, USER, PASS);
	            statementProfile = connectionProfile.createStatement();
	            String sql1SaveUserRecipe= String.format(Query.SAVEQUERY, title, preparation, difficulty,category,time,necessary,username);
	            statementProfile = connectionProfile.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	            int rsSaveUserRecipe = statementProfile.executeUpdate(sql1SaveUserRecipe);

	            if (rsSaveUserRecipe != 1) {
	               
	                return false;
	            }

	            // STEP 6: Clean-up dell'ambiente
	            statementProfile.close();
	            connectionProfile.close();
	            return true;

	        } catch (SQLException seSaveUserRecipe) {
	        	logger.log(null, CONTEXT,seSaveUserRecipe);
	        } catch (Exception eSaveUserRecipe) {
	            // Errore nel loading del driver
	        	logger.log(null, CONTEXT,eSaveUserRecipe);
	        } finally {
	            try {
	                if (statementProfile != null)
	                    statementProfile.close();
	            } catch (SQLException se2SaveUserRecipe) {
	            	logger.log(null, CONTEXT,se2SaveUserRecipe);
	            }
	            try {
	                if (connectionProfile != null)
	                    connectionProfile.close();
	            } catch (SQLException seSaveUserRecipe) {
	            	logger.log(null, CONTEXT,seSaveUserRecipe);
	            }
	        }
	      
	        return false;
	    }
	
	public static Set<UserProfile> userRecipeDao(String username) {
		
		 UserProfile userprofile = null;
		 Set<UserProfile> up= new HashSet<>();
		 
			try {
			
				connectionProfile = DriverManager.getConnection(URL, USER, PASS);
				statementProfile = connectionProfile.createStatement();
				String sqlUserRecipe = String.format(Query.PROFILEQUERY, username);
				rsProfile = statementProfile.executeQuery(sqlUserRecipe);
				while(rsProfile.next()) {
				userprofile = new UserProfile(rsProfile.getString(TITLE), rsProfile.getString(PREPARATION),
								rsProfile.getString(DIFFICULTY), rsProfile.getString(CATEGORY), rsProfile.getString(TIME), rsProfile.getString(NECESSARY),username);
			
								up.add(userprofile);
								
					}
				
		
			} catch(Exception eUserRecipe) {
				logger.log(null, CONTEXT,eUserRecipe);
			} finally {
				try {
					if(connectionProfile != null)
						connectionProfile.close();
					if(statementProfile != null)
						statementProfile.close();
					if(rsProfile != null)
						rsProfile.close();
				} catch (SQLException eUserRecipe) {
					logger.log(null, CONTEXT,eUserRecipe);
				}
			}
			return up;		 
	 }
	 public static UserProfile chooseUserRecipeDao(String title) {
	
		 UserProfile up = null;
			try {
			
				connectionProfile = DriverManager.getConnection(URL, USER, PASS);
				statementProfile= connectionProfile.createStatement();
					
					String sqlChooseUserRecipe = String.format(Query.USERRECIPEQUERY,title);
					
			
					rsProfile = statementProfile.executeQuery(sqlChooseUserRecipe);
					if(rsProfile.next()) {
					
						up = new UserProfile(rsProfile.getString(TITLE), rsProfile.getString(PREPARATION),rsProfile.getString(DIFFICULTY),  rsProfile.getString(CATEGORY), rsProfile.getString(TIME),rsProfile.getString(NECESSARY),rsProfile.getString(USERNAME));
				
					}
				
			} catch(Exception eChooseUserRecipe) {
				logger.log(null, CONTEXT,eChooseUserRecipe);
			} finally {
				try {
					if(connectionProfile != null)
						connectionProfile.close();
					if(statementProfile != null)
						statementProfile.close();
					if(rsProfile != null)
						rsProfile.close();
				} catch (SQLException eChooseUserRecipe) {
					logger.log(null, CONTEXT,eChooseUserRecipe);
				}
			}
			return up;		 
	 }
	 
	 public static boolean deleteRecipeDao(String title, String username) {
		
	        try {
	        
	            connectionProfile = DriverManager.getConnection(URL, USER, PASS);
	            statementProfile = connectionProfile.createStatement();
	            String sql1DeleteRecipe= String.format(Query.DELETERECIPEQUERY,title,username);
	        
	            statementProfile = connectionProfile.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	            int rsDeleteRecipe = statementProfile.executeUpdate(sql1DeleteRecipe);

	            if (rsDeleteRecipe != 1) {
	        
	                return false;
	            }

	            // STEP 6: Clean-up dell'ambiente
	            statementProfile.close();
	            connectionProfile.close();
	            return true;

	        } catch (SQLException seDeleteRecipe) {
	        	logger.log(null, CONTEXT,seDeleteRecipe);
	        } catch (Exception eDeleteRecipe) {
	        	logger.log(null, CONTEXT,eDeleteRecipe);
	        } finally {
	            try {
	                if (statementProfile != null)
	                    statementProfile.close();
	            } catch (SQLException se2DeleteRecipe) {
	            	logger.log(null, CONTEXT,se2DeleteRecipe);
	            }
	            try {
	                if (connectionProfile != null)
	                    connectionProfile.close();
	            } catch (SQLException seDeleteRecipe) {
	            	logger.log(null, CONTEXT,seDeleteRecipe);
	            }
	        }
	        return false;
	    }
public static UserProfile favRecipeDao(String username) {
		 UserProfile favRecipe= null;
		 
			try {
				
				connectionProfile = DriverManager.getConnection(URL, USER, PASS);
				statementProfile = connectionProfile.createStatement();
			
				String sqlFavRecipe = String.format(Query.FAVQUERY, username);
				rsProfile = statementProfile.executeQuery(sqlFavRecipe);
				
				while(rsProfile.next()) {
				favRecipe = new UserProfile(rsProfile.getString(TITLE), rsProfile.getString(PREPARATION),
							 rsProfile.getString(DIFFICULTY),  rsProfile.getString(CATEGORY), rsProfile.getString(TIME), rsProfile.getString(NECESSARY),username);
				
				}
				
			} catch(Exception eFavRecipe) {
				logger.log(null, CONTEXT,eFavRecipe);				
			} finally {
				try {
					if(connectionProfile != null)
						connectionProfile.close();
					if(statementProfile != null)
						statementProfile.close();
					if(rsProfile != null)
						rsProfile.close();
				} catch (SQLException eFavRecipe) {
					logger.log(null, CONTEXT,eFavRecipe);
				}
			}
			return favRecipe;		 
	 }
	 
}
