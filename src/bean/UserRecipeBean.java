package bean;

public class UserRecipeBean {
	private String title;
	private String preparation;
	private String difficulty;
	private String category;
	private String time;
	private String necessary;
	private String username;
	
	
	

	public void setURecBeanTitle(String title) {
		this.title = title;	
	}
	
	public String getURecBeanTitle() {
		return title;
	}
	
	public void setURecBeanPreparation(String preparation) {
		this.preparation= preparation;	
	}

	public String getURecBeanPreparation() {
		return preparation;
	}
	
	public void setURecBeanDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}	
	
	public String getURecBeanDifficulty() {
		return difficulty;
	}

	public void setURecBeanCategory(String category) {
		this.category = category;	
	}
	
	public String getURecBeanCategory() {
		return category;
	}


	public void setURecBeanTime(String time) {
		this.time = time;
	}
	
	public String getURecBeanTime() {
		return time;
	}	
	
	
     public void setURecBeanNecessary(String necessary) {
 		this.necessary = necessary;	
 	}
 	
 	public String getURecBeanNecessary() {
 		return necessary;
 	}
 	public String getURecBeanUsername() {
		return username;
	}
	
	public void setURecBeanUsername(String username) {
		this.username = username;	
	}

}
