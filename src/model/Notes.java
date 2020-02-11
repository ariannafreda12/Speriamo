package model;

public class Notes {

	private String note;
	private String username;
	
	
	public Notes (String note) {
		this.note=note;
	}
	
	public Notes(String note, String username) {
		
		
		this.setNote(note);
		this.setUsername(username);
		
	}
	

	public void setNote(String note) {
		this.note = note;	
	}
	
	public String getNote() {
		return note;
	}

 	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;	
	}


}
