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
	private static Connection connectionRecipe = null;
	private static Statement statementRecipe = null;
	private static ResultSet rsRecipe = null;
	
	
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
		 Recipe r = null;
		 Set<Recipe> recipe= new HashSet<>();
		
		 
			try {
				connectionRecipe = DriverManager.getConnection(URL, USER, PASS);
				statementRecipe = connectionRecipe.createStatement();
				Iterator<String> it;
				it=ingredientList.iterator();
				while(it.hasNext()) {
					
					String sqlIngredient = String.format(Query.RECIPESQUERY,category,difficulty, it.next());
					rsRecipe = statementRecipe.executeQuery(sqlIngredient);
					
					while(rsRecipe.next()) {
						r = new Recipe(rsRecipe.getString(TITLE), rsRecipe.getString(PREPARATION),
								 rsRecipe.getString(DIFFICULTY),  rsRecipe.getString(CATEGORY), rsRecipe.getString(TIME), rsRecipe.getString(NECESSARY),rsRecipe.getInt(REVIEW));
						recipe.add(r);
					}
				}
			} catch(Exception eIngredient) {
				logger.log(null, CONTEXT,eIngredient);
			} finally {
				try {
					if(connectionRecipe != null)
						connectionRecipe.close();
					if(statementRecipe != null)
						statementRecipe.close();
					if(rsRecipe != null)
						rsRecipe.close();
				} catch (SQLException eIngredient) {
					logger.log(null, CONTEXT,eIngredient);
				}
			}
			return recipe;		 
	 }
	 
	 //popular recipe table
	 public static Set<Recipe> popularDao() {
		
		 Recipe r = null;
		 Set<Recipe> popularRecipe= new HashSet<>();
		 
			try {
		
				connectionRecipe = DriverManager.getConnection(URL, USER, PASS);
				statementRecipe = connectionRecipe.createStatement();
			
				String sqlPopular = String.format(Query.POPULARRECIPEQUERY);
				rsRecipe = statementRecipe.executeQuery(sqlPopular);
					
				while(rsRecipe.next()) {
					r = new Recipe(rsRecipe.getString(TITLE), rsRecipe.getString(PREPARATION),
							 rsRecipe.getString(DIFFICULTY),  rsRecipe.getString(CATEGORY), rsRecipe.getString(TIME), rsRecipe.getString(NECESSARY),rsRecipe.getInt(REVIEW));
				
				popularRecipe.add(r);
				}
				
			} catch(Exception ePopular) {
				logger.log(null, CONTEXT,ePopular);
			} finally {
				try {
					if(connectionRecipe != null)
						connectionRecipe.close();
					if(statementRecipe != null)
						statementRecipe.close();
					if(rsRecipe != null)
						rsRecipe.close();
				} catch (SQLException ePopular) {
					logger.log(null, CONTEXT,ePopular);
				}
			}
			return popularRecipe;		 
	 }
	 
	 //daily recipe 
	 public static Recipe dailyRecipeDao() {
		
		 Recipe dailyRecipe= null;
		 
			try {
				connectionRecipe = DriverManager.getConnection(URL, USER, PASS);
				statementRecipe = connectionRecipe.createStatement();
			
				String sqlDaily = String.format(Query.DAILYRECIPEQUERY);
				
				rsRecipe = statementRecipe.executeQuery(sqlDaily);
				
				while(rsRecipe.next()) {
				dailyRecipe = new Recipe(rsRecipe.getString(TITLE), rsRecipe.getString(PREPARATION),
						 rsRecipe.getString(DIFFICULTY),  rsRecipe.getString(CATEGORY), rsRecipe.getString(TIME), rsRecipe.getString(NECESSARY),rsRecipe.getInt(REVIEW));
				}
				
			} catch(Exception eDaily) {
				logger.log(null, CONTEXT,eDaily);				
			} finally {
				try {
					if(connectionRecipe != null)
						connectionRecipe.close();
					if(statementRecipe != null)
						statementRecipe.close();
					if(rsRecipe != null)
						rsRecipe.close();
				} catch (SQLException eDaily) {
					logger.log(null, CONTEXT,eDaily);
				}
			}
			return dailyRecipe;		 
	 }
	 
	 public static Recipe chooseRecipeDao(String title) {
		 Recipe r = null;
			try {
				connectionRecipe = DriverManager.getConnection(URL, USER, PASS);
				statementRecipe = connectionRecipe.createStatement();
					
					String sqlChooseRecipe = String.format(Query.RECIPEQUERY,title);
					rsRecipe = statementRecipe.executeQuery(sqlChooseRecipe);
					
					if(rsRecipe.next()) {
						r = new Recipe(rsRecipe.getString(TITLE), rsRecipe.getString(PREPARATION),
								 rsRecipe.getString(DIFFICULTY),  rsRecipe.getString(CATEGORY), rsRecipe.getString(TIME), rsRecipe.getString(NECESSARY),rsRecipe.getInt(REVIEW));
					}
				
			} catch(Exception eChooseRecipe) {
				logger.log(null, CONTEXT,eChooseRecipe);
			} finally {
				try {
					if(connectionRecipe != null)
						connectionRecipe.close();
					if(statementRecipe != null)
						statementRecipe.close();
					if(rsRecipe != null)
						rsRecipe.close();
				} catch (SQLException eChooseRecipe) {
					logger.log(null, CONTEXT,eChooseRecipe);
				}
			}
			return r;		 
			}


	 //like for recipe
	 public static boolean reviewRecipeDao(String title, int review) {
		
	        try {
	           
	            connectionRecipe = DriverManager.getConnection(URL, USER, PASS);
	            statementRecipe = connectionRecipe.createStatement();
	            String sql1Review= String.format(Query.REVIEWQUERY,review+1, title);
	           
	            statementRecipe = connectionRecipe.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	            int rsReview = statementRecipe.executeUpdate(sql1Review);
	            
	            if (rsReview != 1) {
	           
	                return false;
	            }

	            statementRecipe.close();
	            connectionRecipe.close();
	            return true;

	        } catch (SQLException seReview) {
	        	logger.log(null, CONTEXT,seReview);
	        } catch (Exception eReview) {
	        	logger.log(null, CONTEXT,eReview);
	        } finally {
	            try {
	                if (statementRecipe != null)
	                    statementRecipe.close();
	            } catch (SQLException se2Review) {
	            	logger.log(null, CONTEXT,se2Review);
	            }
	            try {
	                if (connectionRecipe != null)
	                    connectionRecipe.close();
	            } catch (SQLException seReview) {
	            	logger.log(null, CONTEXT,seReview);
	            }
	        }
	        
	        return false;
	    }
	 
	 //add like for a recipe
	 public static boolean addReviewDao(String username, String title) {
		
	        try {
	        
	            connectionRecipe = DriverManager.getConnection(URL, USER, PASS);
	            statementRecipe = connectionRecipe.createStatement();
	            String sql1AddReview= String.format(Query.SAVEREVIEWQUERY,username,true,title);
	        
	            statementRecipe = connectionRecipe.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	            int rsAddReview = statementRecipe.executeUpdate(sql1AddReview);

	            if (rsAddReview != 1) {
	        
	                return false;
	            }

	            statementRecipe.close();
	            connectionRecipe.close();
	            return true;

	        } catch (SQLException seAddReview) {
	        	logger.log(null, CONTEXT,seAddReview);
	        } catch (Exception eAddReview) {
	            
	        	logger.log(null, CONTEXT,eAddReview);
	        } finally {
	            try {
	                if (statementRecipe != null)
	                    statementRecipe.close();
	            } catch (SQLException se2AddReview) {
	            	logger.log(null, CONTEXT,se2AddReview);
	            }
	            try {
	                if (connectionRecipe != null)
	                    connectionRecipe.close();
	            } catch (SQLException seAddReview) {
	            	logger.log(null, CONTEXT,seAddReview);
	            }
	        }

	        return false;
	    }

	 public static boolean checkReviewDao(String username, String title) {
	      
	    
	        try {

	            connectionRecipe = DriverManager.getConnection(URL, USER, PASS);
	            statementRecipe = connectionRecipe.createStatement();
	            String sql1CheckReview= String.format(Query.CHECKREVIEWQUERY,username,title);

	            int rsCheckReview = statementRecipe.executeUpdate(sql1CheckReview);
	            if (rsCheckReview != 1) {
	    	        
	                return false;
	            }

	            statementRecipe.close();
	            connectionRecipe.close();
	            return true;
		} catch(Exception eCheckReview) {
			logger.log(null, CONTEXT,eCheckReview);
		} finally {
			try {
				if(connectionRecipe != null)
					connectionRecipe.close();
				if(statementRecipe != null)
					statementRecipe.close();
			} catch (SQLException eCheckReview) {
				logger.log(null, CONTEXT,eCheckReview);
			}
		}
		return false;		 
	    }
}

