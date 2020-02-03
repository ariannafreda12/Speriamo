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
		 Statement statementIngredient = null;
		 Recipe r = null;
		 Set<Recipe> recipe= new HashSet<>();
		
		 
			try {
				connection = DriverManager.getConnection(URL, USER, PASS);
				statementIngredient = connection.createStatement();
				Iterator<String> it;
				it=ingredientList.iterator();
				while(it.hasNext()) {
					
					String sqlIngredient = String.format(Query.RECIPESQUERY,category,difficulty, it.next());
				
					ResultSet rsIngredient = statementIngredient.executeQuery(sqlIngredient);
					
					while(rsIngredient.next()) {
						r = new Recipe(rsIngredient.getInt(ID),rsIngredient.getString(TITLE), rsIngredient.getString(PREPARATION),
								 rsIngredient.getString(DIFFICULTY),  rsIngredient.getString(CATEGORY), rsIngredient.getString(TIME), rsIngredient.getString(NECESSARY),rsIngredient.getInt(REVIEW));
						recipe.add(r);
					}
				}
			} catch(Exception eIngredient) {
				logger.log(null, CONTEXT,eIngredient);
			} finally {
				try {
					if(connection != null)
						connection.close();
					if(statementIngredient != null)
						statementIngredient.close();
				} catch (SQLException eIngredient) {
					logger.log(null, CONTEXT,eIngredient);
				}
			}
			return recipe;		 
	 }
	 
	 //popular recipe table
	 public static Set<Recipe> popularDao() {
		 Statement statementPopular = null;
		 Recipe r = null;
		 
		 Set<Recipe> popularRecipe= new HashSet<>();
		 
			try {
		
				connection = DriverManager.getConnection(URL, USER, PASS);
				statementPopular = connection.createStatement();
			
				String sqlPopular = String.format(Query.POPULARRECIPEQUERY);
				ResultSet rsPopular = statementPopular.executeQuery(sqlPopular);
					
				while(rsPopular.next()) {
					r = new Recipe(rsPopular.getInt(ID),rsPopular.getString(TITLE), rsPopular.getString(PREPARATION),
							 rsPopular.getString(DIFFICULTY),  rsPopular.getString(CATEGORY), rsPopular.getString(TIME), rsPopular.getString(NECESSARY),rsPopular.getInt(REVIEW));
				
				popularRecipe.add(r);
				}
				
			} catch(Exception ePopular) {
				logger.log(null, CONTEXT,ePopular);
			} finally {
				try {
					if(connection != null)
						connection.close();
					if(statementPopular != null)
						statementPopular.close();
				} catch (SQLException ePopular) {
					logger.log(null, CONTEXT,ePopular);
				}
			}
			return popularRecipe;		 
	 }
	 
	 //daily recipe 
	 public static Recipe dailyRecipeDao() {
		 
		 Statement statementDaily = null;
		 Recipe dailyRecipe= null;
		 
			try {
				connection = DriverManager.getConnection(URL, USER, PASS);
				statementDaily = connection.createStatement();
			
				String sqlDaily = String.format(Query.DAILYRECIPEQUERY);
				
				ResultSet rsDaily = statementDaily.executeQuery(sqlDaily);
				
				while(rsDaily.next()) {
				dailyRecipe = new Recipe(rsDaily.getInt(ID),rsDaily.getString(TITLE), rsDaily.getString(PREPARATION),
						 rsDaily.getString(DIFFICULTY),  rsDaily.getString(CATEGORY), rsDaily.getString(TIME), rsDaily.getString(NECESSARY),rsDaily.getInt(REVIEW));
				}
				
			} catch(Exception eDaily) {
				logger.log(null, CONTEXT,eDaily);				
			} finally {
				try {
					if(connection != null)
						connection.close();
					if(statementDaily != null)
						statementDaily.close();
				} catch (SQLException eDaily) {
					logger.log(null, CONTEXT,eDaily);
				}
			}
			return dailyRecipe;		 
	 }
	 
	 public static Recipe chooseRecipeDao(String title) {
		 Statement statementChooseRecipe = null;
		 Recipe r = null;
			try {
				connection = DriverManager.getConnection(URL, USER, PASS);
				statementChooseRecipe = connection.createStatement();
					
					String sqlChooseRecipe = String.format(Query.RECIPEQUERY,title);
					
				
					ResultSet rsChooseRecipe = statementChooseRecipe.executeQuery(sqlChooseRecipe);
					
					if(rsChooseRecipe.next()) {
						r = new Recipe(rsChooseRecipe.getInt(ID),rsChooseRecipe.getString(TITLE), rsChooseRecipe.getString(PREPARATION),
								 rsChooseRecipe.getString(DIFFICULTY),  rsChooseRecipe.getString(CATEGORY), rsChooseRecipe.getString(TIME), rsChooseRecipe.getString(NECESSARY),rsChooseRecipe.getInt(REVIEW));
					}
				
			} catch(Exception eChooseRecipe) {
				logger.log(null, CONTEXT,eChooseRecipe);
			} finally {
				try {
					if(connection != null)
						connection.close();
					if(statementChooseRecipe != null)
						statementChooseRecipe.close();
				} catch (SQLException eChooseRecipe) {
					logger.log(null, CONTEXT,eChooseRecipe);
				}
			}
			return r;		 
			}


	 //like for recipe
	 public static boolean reviewRecipeDao(String title, int review) {
		  Statement statementReview = null;
	      Connection connReview = null;
	        try {
	           
	            connReview = DriverManager.getConnection(URL, USER, PASS);
	            statementReview = connReview.createStatement();
	            String sql1Review= String.format(Query.REVIEWQUERY,review+1, title);
	           
	            statementReview = connReview.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	            int rsReview = statementReview.executeUpdate(sql1Review);
	            
	            if (rsReview != 1) {
	           
	                return false;
	            }

	            statementReview.close();
	            connReview.close();
	            return true;

	        } catch (SQLException seReview) {
	        	logger.log(null, CONTEXT,seReview);
	        } catch (Exception eReview) {
	        	logger.log(null, CONTEXT,eReview);
	        } finally {
	            try {
	                if (statementReview != null)
	                    statementReview.close();
	            } catch (SQLException se2Review) {
	            	logger.log(null, CONTEXT,se2Review);
	            }
	            try {
	                if (connReview != null)
	                    connReview.close();
	            } catch (SQLException seReview) {
	            	logger.log(null, CONTEXT,seReview);
	            }
	        }
	        
	        return false;
	    }
	 
	 //add like for a recipe
	 public static boolean addReviewDao(String username, String title) {
		  Statement statementAddReview = null;
	      Connection connAddReview = null;
	        try {
	        
	            connAddReview = DriverManager.getConnection(URL, USER, PASS);
	            statementAddReview = connAddReview.createStatement();
	            String sql1AddReview= String.format(Query.SAVEREVIEWQUERY,username,true,title);
	        
	            statementAddReview = connAddReview.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	            int rsAddReview = statementAddReview.executeUpdate(sql1AddReview);

	            if (rsAddReview != 1) {
	        
	                return false;
	            }

	            statementAddReview.close();
	            connAddReview.close();
	            return true;

	        } catch (SQLException seAddReview) {
	        	logger.log(null, CONTEXT,seAddReview);
	        } catch (Exception eAddReview) {
	            
	        	logger.log(null, CONTEXT,eAddReview);
	        } finally {
	            try {
	                if (statementAddReview != null)
	                    statementAddReview.close();
	            } catch (SQLException se2AddReview) {
	            	logger.log(null, CONTEXT,se2AddReview);
	            }
	            try {
	                if (connAddReview != null)
	                    connAddReview.close();
	            } catch (SQLException seAddReview) {
	            	logger.log(null, CONTEXT,seAddReview);
	            }
	        }

	        return false;
	    }

	 public static boolean checkReviewDao(String username, String title) {
		  Statement statementCheckREview = null;
	      Connection connCheckReview = null;
	      boolean check = false;
	    
	        try {

	            connCheckReview = DriverManager.getConnection(URL, USER, PASS);
	            statementCheckREview = connCheckReview.createStatement();
	            String sql1CheckReview= String.format(Query.CHECKREVIEWQUERY,username,title);

	            ResultSet rsCheckReview = statementCheckREview.executeQuery(sql1CheckReview);
	            
	            if(rsCheckReview.next()) {
					check= rsCheckReview.getBoolean("rev");
				}
			
		} catch(Exception eCheckReview) {
			logger.log(null, CONTEXT,eCheckReview);
		} finally {
			try {
				if(connection != null)
					connection.close();
				if(statementCheckREview != null)
					statementCheckREview.close();
			} catch (SQLException eCheckReview) {
				logger.log(null, CONTEXT,eCheckReview);
			}
		}
		return check;		 
	    }
}

