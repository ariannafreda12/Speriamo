package bean;

import controller.NotesManager;
import model.Notes;

public class NotesBean {
	
	public String note;
	public String username;

	public boolean validateNote(String note) {
		
		NotesManager controller = NotesManager.getInstance();
		synchronized(controller) {
		
		Notes found = controller.chooseNote(note);
			if(found != null) {
				return true;	
			}
			return false;
		}
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
