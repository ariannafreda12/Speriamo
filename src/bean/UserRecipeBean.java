package bean;

public class UserRecipeBean {
	public String title;
	public String preparation;
	public String difficulty;
	public String category;
	public String time;
	public String necessary;
	public String username;
	
	
	

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


	public void setTime(String time) {
		this.time = time;
	}
	
	public String getTime() {
		return time;
	}	
	
	
     public void setNecessary(String necessary) {
 		this.necessary = necessary;	
 	}
 	
 	public String getNecessary() {
 		return necessary;
 	}
 	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;	
	}

}
