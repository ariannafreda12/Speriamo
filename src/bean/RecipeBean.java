package bean;

import java.util.HashSet;
import java.util.Set;

import controller.RecipeManager;
import model.Recipe;

public class RecipeBean {
    

	private String titleBean;
	private int idRec;
	private String preparation;
	private String difficulty;
	private String category;
	private Set<String> ingredients = new HashSet<>();
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
	
	public int getRecBeanId() {
		return idRec;
	}
	
	public void setRecBeanId(int idRec) {
		this.idRec = idRec;	
	}

	public void setRecBeanTitle(String titleBean) {
		this.titleBean = titleBean;	
	}
	
	public String getRecBeanTitle() {
		return titleBean;
	}
	
	public void setRecBeanPreparation(String preparation) {
		this.preparation= preparation;	
	}

	public String getRecBeanPreparation() {
		return preparation;
	}
	
	public void setRecBeanDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}	
	
	public String getRecBeanDifficulty() {
		return difficulty;
	}


	public void setRecBeanCategory(String category) {
		this.category = category;	
	}
	
	public String getRecBeanCategory() {
		return category;
	}

	public void setRecBeanTime(String time) {
		this.time = time;
	}
	
	public String getrecBeanTime() {
		return time;
	}	
	
	public Set<String> getRecBeanIngredients() {
        return ingredients;
    }
    
     public void setRecBeanIngredient(Set<String> ingList) {
    	 this.ingredients = ingList;
     }
     public void setRecBeanNecessary(String necessary) {
  		this.necessary = necessary;	
  	}
  	
  	public String getRecBeanNecessary() {
  		return necessary;
  	}
  	public int getRecBeanReview() {
 		return review;
 	}
 	
 	public void setRecBeanReview(int review) {
 		this.review = review;	
 	}
}
