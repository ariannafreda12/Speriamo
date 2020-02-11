package model;


import java.util.Set;

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
	private Set<String> ingredient;
	
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
	
	public Recipe(String title, String preparation, String difficulty, String category,  String time, String necessary,int review) {
		
		this.setId(idRec);
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
	
	public String getTitle() {
		return title;
	}
	public String getPreparation() {
		return preparation;
	}
	
	public String getDifficulty() {
		return difficulty;
	}

	public String getCategory() {
		return category;
	}
	public String getTime() {
		return time;
	}	
	
	public String getIngredients() {
        return ingredients;
    }

   
	public Set<String> getIngredient() {
        return ingredient;
    } 	
 	public String getNecessary() {
 		return necessary;
 	}
 	public int getReview() {
		return review;
	}
 	private void setId(int idRec) {
		this.idRec = idRec;	
	}

	public void setTitle(String title) {
		this.title = title;	
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}	
	public void setCategory(String category) {
		this.category = category;	
	}
	private void setTime(String time) {
		this.time = time;
	}
	
	
	public void setPreparation(String preparation) {
		this.preparation= preparation;	
	}
	 public void setIngredient(Set<String> ingList) {
    	 this.ingredient = ingList;
     }
     
     public void setNecessary(String necessary) {
 		this.necessary = necessary;	
 	}
    public void setIngredients(String ingredients) {
         this.ingredients=ingredients;
     }

	public void setReview(int review) {
		this.review = review;	
	}
	
}