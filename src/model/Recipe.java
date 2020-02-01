package model;

import java.util.ArrayList;

public class Recipe {
	
	private String title;
	private int idRec;
	private String preparation;
	private String difficulty;
	private String category;
	private String ingredients;
	private String time;
	private String necessary;
	private int review;
	private ArrayList<String> ingredient;
	
	public Recipe (String title, String preparation, String time,int review) {
		this.title=title;
		this.preparation = preparation; 
		this.time = time;
		this.review=review;
	}
	
	public Recipe (String title, int review) {
		this.title=title;
		this.review=review;
	}
	
	public Recipe(int id_recipe, String title, String preparation, String difficulty, String category,  String time, String necessary,int review) {
		
		this.setId(id_recipe);
		this.setTitle(title);
		this.setPreparation(preparation);
		this.setDifficulty(difficulty);
		this.setCategory(category);
		this.setTime(time);
		this.setNecessary(necessary);
		this.setReview(review);
		
	}
	
	public Recipe() {
		
	}

	public int getId() {
		return idRec;
	}
	
	private void setId(int idRec) {
		this.idRec = idRec;	
	}

	public void setTitle(String title) {
		this.title = title;	
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setPreparation(String preparation) {
		this.preparation= preparation;	
	}

	public String getPreparation() {
		return preparation;
	}
	
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}	
	
	public String getDifficulty() {
		return difficulty;
	}

	public void setCategory(String category) {
		this.category = category;	
	}
	
	public String getCategory() {
		return category;
	}


	private void setTime(String time) {
		this.time = time;
	}
	
	public String getTime() {
		return time;
	}	
	
	public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients=ingredients;
    }

	public ArrayList<String> getIngredient() {
        return ingredient;
    }
    
     public void setIngredient(ArrayList <String> ingList) {
    	 this.ingredient = ingList;
     }
     
     public void setNecessary(String necessary) {
 		this.necessary = necessary;	
 	}
 	
 	public String getNecessary() {
 		return necessary;
 	}
 	public int getReview() {
		return review;
	}
	
	public void setReview(int review) {
		this.review = review;	
	}
	
}