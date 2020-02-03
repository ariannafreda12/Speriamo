package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

import model.Recipe;
import utils.Query;

public class RecipeDao {
	
	
	
	private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String USER = "postgres";
	private static final String PASS = "postgres";
	private static Connection connection = null;
	
	private static final String ID = "id_recipe";
	private static final String TITLE = "title";
	private static final String CATEGORY= "category";
	private static final String DIFFICULTY = "difficulty";
	private static final String PREPARATION = "preparation";
	private static final String NECESSARY = "necessary";
	private static final String REVIEW = "review";
	private static final String TIME = "time";
	
	 static Logger logger = Logger.getAnonymousLogger();
	 private static final String CONTEXT = "context";
	
	private RecipeDao() {
	    throw new IllegalStateException("Utility class");
	  }
	
	//find recipe by ingredient
	 public static Set<Recipe> ingredientsDao(Set <String> ingredientList, String category,String difficulty) {
		 Statement stmt = null;
		 Recipe r = null;
		 Set<Recipe> recipe= new HashSet<>();
		
		 
			try {
				connection = DriverManager.getConnection(URL, USER, PASS);
				stmt = connection.createStatement();
				Iterator<String> it;
				it=ingredientList.iterator();
				while(it.hasNext()) {
					
					String sql = String.format(Query.RECIPESQUERY,category,difficulty, it.next());
				
					ResultSet rs = stmt.executeQuery(sql);
					
					while(rs.next()) {
						r = new Recipe(rs.getInt(ID),rs.getString(TITLE), rs.getString(PREPARATION),
								 rs.getString(DIFFICULTY),  rs.getString(CATEGORY), rs.getString(TIME), rs.getString(NECESSARY),rs.getInt(REVIEW));
						recipe.add(r);
					}
				}
			} catch(Exception e) {
				logger.log(null, CONTEXT,e);
			} finally {
				try {
					if(connection != null)
						connection.close();
					if(stmt != null)
						stmt.close();
				} catch (SQLException e) {
					logger.log(null, CONTEXT,e);
				}
			}
			return recipe;		 
	 }
	 
	 //popular recipe table
	 public static Set<Recipe> popularDao() {
		 Statement stmt = null;
		 Recipe r = null;
		 
		 Set<Recipe> popularRecipe= new HashSet<>();
		 
			try {
		
				connection = DriverManager.getConnection(URL, USER, PASS);
				stmt = connection.createStatement();
			
				String sql = String.format(Query.POPULARRECIPEQUERY);
				ResultSet rs = stmt.executeQuery(sql);
					
				while(rs.next()) {
					r = new Recipe(rs.getInt(ID),rs.getString(TITLE), rs.getString(PREPARATION),
							 rs.getString(DIFFICULTY),  rs.getString(CATEGORY), rs.getString(TIME), rs.getString(NECESSARY),rs.getInt(REVIEW));
				
				popularRecipe.add(r);
				}
				
			} catch(Exception e) {
				logger.log(null, CONTEXT,e);
			} finally {
				try {
					if(connection != null)
						connection.close();
					if(stmt != null)
						stmt.close();
				} catch (SQLException e) {
					logger.log(null, CONTEXT,e);
				}
			}
			return popularRecipe;		 
	 }
	 
	 //daily recipe 
	 public static Recipe dailyRecipeDao() {
		 
		 Statement stmt = null;
		 Recipe dailyRecipe= null;
		 
			try {
				connection = DriverManager.getConnection(URL, USER, PASS);
				stmt = connection.createStatement();
			
				String sql = String.format(Query.DAILYRECIPEQUERY);
				
				ResultSet rs = stmt.executeQuery(sql);
				
				while(rs.next()) {
				dailyRecipe = new Recipe(rs.getInt(ID),rs.getString(TITLE), rs.getString(PREPARATION),
						 rs.getString(DIFFICULTY),  rs.getString(CATEGORY), rs.getString(TIME), rs.getString(NECESSARY),rs.getInt(REVIEW));
				}
				
			} catch(Exception e) {
				logger.log(null, CONTEXT,e);				
			} finally {
				try {
					if(connection != null)
						connection.close();
					if(stmt != null)
						stmt.close();
				} catch (SQLException e) {
					logger.log(null, CONTEXT,e);
				}
			}
			return dailyRecipe;		 
	 }
	 
	 public static Recipe chooseRecipeDao(String title) {
		 Statement stmt = null;
		 Recipe r = null;
			try {
				connection = DriverManager.getConnection(URL, USER, PASS);
				stmt = connection.createStatement();
					
					String sql = String.format(Query.RECIPEQUERY,title);
					
				
					ResultSet rs = stmt.executeQuery(sql);
					
					if(rs.next()) {
						r = new Recipe(rs.getInt(ID),rs.getString(TITLE), rs.getString(PREPARATION),
								 rs.getString(DIFFICULTY),  rs.getString(CATEGORY), rs.getString(TIME), rs.getString(NECESSARY),rs.getInt(REVIEW));
					}
				
			} catch(Exception e) {
				logger.log(null, CONTEXT,e);
			} finally {
				try {
					if(connection != null)
						connection.close();
					if(stmt != null)
						stmt.close();
				} catch (SQLException e) {
					logger.log(null, CONTEXT,e);
				}
			}
			return r;		 
			}


	 //like for recipe
	 public static boolean reviewRecipeDao(String title, int review) {
		  Statement stmt = null;
	      Connection conn = null;
	        try {
	           
	            conn = DriverManager.getConnection(URL, USER, PASS);
	            stmt = conn.createStatement();
	            String sql1= String.format(Query.REVIEWQUERY,review+1, title);
	           
	            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	            int rs = stmt.executeUpdate(sql1);
	            
	            if (rs != 1) {
	           
	                return false;
	            }

	            stmt.close();
	            conn.close();
	            return true;

	        } catch (SQLException se) {
	        	logger.log(null, CONTEXT,se);
	        } catch (Exception e) {
	        	logger.log(null, CONTEXT,e);
	        } finally {
	            try {
	                if (stmt != null)
	                    stmt.close();
	            } catch (SQLException se2) {
	            	logger.log(null, CONTEXT,se2);
	            }
	            try {
	                if (conn != null)
	                    conn.close();
	            } catch (SQLException se) {
	            	logger.log(null, CONTEXT,se);
	            }
	        }
	        
	        return false;
	    }
	 
	 //add like for a recipe
	 public static boolean addReviewDao(String username, String title) {
		  Statement stmt = null;
	      Connection conn = null;
	        try {
	        
	            conn = DriverManager.getConnection(URL, USER, PASS);
	            stmt = conn.createStatement();
	            String sql1= String.format(Query.SAVEREVIEWQUERY,username,true,title);
	        
	            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	            int rs = stmt.executeUpdate(sql1);

	            if (rs != 1) {
	        
	                return false;
	            }

	            stmt.close();
	            conn.close();
	            return true;

	        } catch (SQLException se) {
	        	logger.log(null, CONTEXT,se);
	        } catch (Exception e) {
	            
	        	logger.log(null, CONTEXT,e);
	        } finally {
	            try {
	                if (stmt != null)
	                    stmt.close();
	            } catch (SQLException se2) {
	            	logger.log(null, CONTEXT,se2);
	            }
	            try {
	                if (conn != null)
	                    conn.close();
	            } catch (SQLException se) {
	            	logger.log(null, CONTEXT,se);
	            }
	        }

	        return false;
	    }

	 public static boolean checkReviewDao(String username, String title) {
		  Statement stmt = null;
	      Connection conn = null;
	      boolean check = false;
	    
	        try {

	            conn = DriverManager.getConnection(URL, USER, PASS);
	            stmt = conn.createStatement();
	            String sql1= String.format(Query.CHECKREVIEWQUERY,username,title);

	            ResultSet rs = stmt.executeQuery(sql1);
	            
	            if(rs.next()) {
					check= rs.getBoolean("rev");
				}
			
		} catch(Exception e) {
			logger.log(null, CONTEXT,e);
		} finally {
			try {
				if(connection != null)
					connection.close();
				if(stmt != null)
					stmt.close();
			} catch (SQLException e) {
				logger.log(null, CONTEXT,e);
			}
		}
		return check;		 
	    }
}

