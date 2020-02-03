package model;



public class UserProfile {
	
	private String title;
	private String preparation;
	private String difficulty;
	private String category;
	private String time;
	private String necessary;
	private String username;
	
	
	public UserProfile (String title) {
		this.title=title;
	}
	
	public UserProfile(String title, String preparation, String difficulty, String category,  String time, String necessary,String username) {
		
		
		this.setTitle(title);
		this.setPreparation(preparation);
		this.setDifficulty(difficulty);
		this.setCategory(category);
		this.setTime(time);
		this.setNecessary(necessary);
		this.setUsername(username);
		
	}
	

	public UserProfile() {
		
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
