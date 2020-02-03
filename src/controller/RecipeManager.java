package controller;


import java.util.Set;

import bean.RecipeBean;
import dao.RecipeDao;
import model.Recipe;

public class RecipeManager {
	
	private static RecipeManager instance = null;
	private RecipeBean recipe;//singleton

    
    public static RecipeManager getInstance() {

        if (instance == null) {
            instance = new RecipeManager();
        }
        return instance;
    }
    
    public RecipeBean getRecipe() {
        return recipe;
     }

    public void setRecipe(RecipeBean r){
        recipe = r;
    }
     
    public static Set<Recipe> foundIngredient(Set<String> ingredients,String category, String difficulty) {
    	return RecipeDao.ingredientsDao(ingredients,category,difficulty);
    }
    
    public static Set<Recipe> popularRecipe() {
    	return RecipeDao.popularDao();
    }
    
    public static Recipe dailyRecipe() {
    	return RecipeDao.dailyRecipeDao();
    }
    

    
    
    public static Recipe chooseRecipe(String title) {
    	return RecipeDao.chooseRecipeDao(title);
    }

	public boolean reviewRecipe(String title, int review) {
		return RecipeDao.reviewRecipeDao(title, review);
	}
	
	public boolean addReviewRecipe(String username, String title) {
		return RecipeDao.addReviewDao(username, title);
	}
	public boolean checkReviewRecipe(String username, String title) {
		return RecipeDao.checkReviewDao(username, title);
	}
}
