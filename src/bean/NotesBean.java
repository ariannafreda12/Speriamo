package bean;

import controller.NotesManager;
import model.Notes;

public class NotesBean {
	
	private String note;
	private String username;

	public boolean validateNote(String note) {
		
		boolean checked=false;
		
		NotesManager controller = NotesManager.getInstance();
		synchronized(controller) {
		
		Notes found = controller.chooseNote(note);
			if(found != null) {
				checked=true;
			}
		
		}
		return checked;
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
