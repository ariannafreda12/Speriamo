package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
	
	private UserProfileDao() {
	    throw new IllegalStateException("Utility class");
	  }
	
	
	 public static boolean saveRecipeDao(String title, String preparation, String difficulty, String category, String time, String necessary,String username) {
		  Statement stmt = null;
	      Connection conn = null;
	        try {
	           
	            conn = DriverManager.getConnection(URL, USER, PASS);
	            stmt = conn.createStatement();
	            String sql1= String.format(Query.SAVEQUERY, title, preparation, difficulty,category,time,necessary,username);
	            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	            int rs = stmt.executeUpdate(sql1);

	            if (rs != 1) {
	               
	                return false;
	            }

	            // STEP 6: Clean-up dell'ambiente
	            stmt.close();
	            conn.close();
	            return true;

	        } catch (SQLException se) {
	            // Errore durante l'apertura della connessione
	            se.printStackTrace();
	        } catch (Exception e) {
	            // Errore nel loading del driver
	            e.printStackTrace();
	        } finally {
	            try {
	                if (stmt != null)
	                    stmt.close();
	            } catch (SQLException se2) {
	                se2.printStackTrace();
	            }
	            try {
	                if (conn != null)
	                    conn.close();
	            } catch (SQLException se) {
	                se.printStackTrace();
	            }
	        }
	      
	        return false;
	    }
	
	public static ArrayList <UserProfile> userRecipeDao(String username) {
		 Statement stmt = null;
		 UserProfile userprofile = null;
		 ArrayList<UserProfile> up= new ArrayList <UserProfile>();
		 
			try {
			
				connection = DriverManager.getConnection(URL, USER, PASS);
				stmt = connection.createStatement();
				
					
					String sql = String.format(Query.PROFILEQUERY, username);
			
					ResultSet rs = stmt.executeQuery(sql);
					
					while(rs.next()) {
						
						userprofile = new UserProfile(rs.getString(TITLE), rs.getString(PREPARATION),
								rs.getString(DIFFICULTY), rs.getString(CATEGORY), rs.getString(TIME), rs.getString(NECESSARY),username);
			
								up.add(userprofile);
								
					}
				
		
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(connection != null)
						connection.close();
					if(stmt != null)
						stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return up;		 
	 }
	 public static UserProfile chooseUserRecipeDao(String title) {
		 Statement stmt = null;
		 UserProfile up = null;
			try {
			
				connection = DriverManager.getConnection(URL, USER, PASS);
				stmt = connection.createStatement();
					
					String sql = String.format(Query.USERRECIPEQUERY,title);
					
			
					ResultSet rs = stmt.executeQuery(sql);
					
					if(rs.next()) {
					
						up = new UserProfile(rs.getString(TITLE), rs.getString(PREPARATION),rs.getString(DIFFICULTY),  rs.getString(CATEGORY), rs.getString(TIME),rs.getString(NECESSARY),rs.getString(USERNAME));
				
					}
				
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(connection != null)
						connection.close();
					if(stmt != null)
						stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return up;		 
	 }
	 
	 public static boolean deleteRecipeDao(String title, String username) {
		  Statement stmt = null;
	      Connection conn = null;
	        try {
	        
	            conn = DriverManager.getConnection(URL, USER, PASS);
	            stmt = conn.createStatement();
	            String sql1= String.format(Query.DELETERECIPEQUERY,title,username);
	        
	            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	            int rs = stmt.executeUpdate(sql1);

	            if (rs != 1) {
	        
	                return false;
	            }

	            // STEP 6: Clean-up dell'ambiente
	            stmt.close();
	            conn.close();
	            return true;

	        } catch (SQLException se) {
	            // Errore durante l'apertura della connessione
	            se.printStackTrace();
	        } catch (Exception e) {
	            // Errore nel loading del driver
	            e.printStackTrace();
	        } finally {
	            try {
	                if (stmt != null)
	                    stmt.close();
	            } catch (SQLException se2) {
	                se2.printStackTrace();
	            }
	            try {
	                if (conn != null)
	                    conn.close();
	            } catch (SQLException se) {
	                se.printStackTrace();
	            }
	        }
	        return false;
	    }
public static UserProfile favRecipeDao(String username) {
		 
		 Statement stmt = null;
		 UserProfile favRecipe= null;
		 
			try {
				
				connection = DriverManager.getConnection(URL, USER, PASS);
				stmt = connection.createStatement();
			
				String sql = String.format(Query.FAVQUERY, username);
				ResultSet rs = stmt.executeQuery(sql);
				
				while(rs.next()) {
				favRecipe = new UserProfile(rs.getString(TITLE), rs.getString(PREPARATION),
							 rs.getString(DIFFICULTY),  rs.getString(CATEGORY), rs.getString(TIME), rs.getString(NECESSARY),username);
				
				}
				
			} catch(Exception e) {
				e.printStackTrace();				
			} finally {
				try {
					if(connection != null)
						connection.close();
					if(stmt != null)
						stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return favRecipe;		 
	 }
	 
}
