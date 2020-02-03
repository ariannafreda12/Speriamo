package bean;

import java.util.ArrayList;
import controller.RecipeManager;
import model.Recipe;

public class RecipeBean {
    

	private String title;
	private int idRec;
	private String preparation;
	private String difficulty;
	private String category;
	private ArrayList <String> ingredients = new ArrayList <String>();
	private String time;
	private String necessary;
	private int review;

		
	
public boolean validateRec(String title) {
	
		boolean checked=false;
		
		RecipeManager controller = RecipeManager.getInstance();
		synchronized(controller) {
		
		Recipe found = RecipeManager.chooseRecipe(title);
			if(found != null) {
				checked=true;
				
				
			}
		
			
		}
		return checked;
	}
	
	public int getId() {
		return idRec;
	}
	
	public void setId(int idRec) {
		this.idRec = idRec;	
	}

	public void setRecBeanTitle(String title) {
		this.title = title;	
	}
	
	public String getRecBeanTitle() {
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

	public void setTime(String time) {
		this.time = time;
	}
	
	public String getTime() {
		return time;
	}	
	
	public ArrayList<String> getIngredients() {
        return ingredients;
    }
    
     public void setIngredient(ArrayList <String> ingList) {
    	 this.ingredients = ingList;
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
