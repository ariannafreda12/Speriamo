package controller;




import java.util.Set;

import bean.NotesBean;
import dao.NotesDao;
import model.Notes;

public class NotesManager {
	
	private static NotesManager instance = null;
	private NotesBean note;//singleton

    

    public static NotesManager getInstance() {

        if (instance == null) {
            instance = new NotesManager();
        }
        return instance;
    }
    
    public NotesBean getNote() {
        return note;
     }

    public void setNote(NotesBean n){
    	note = n;
    }

    public static Set<Notes> userNotes(String username) {
    	return (Set<Notes>) NotesDao.userNotesDao(username);
    }
    
    public  boolean addNotes(String note, String username) {
    	return NotesDao.addNoteDao(note, username);
    }
    public  Notes chooseNote(String note) {
    	return NotesDao.chooseNoteDao(note);
    }
    
    public  boolean modifyNote(String note, String noteModified) {
    	return NotesDao.modifyNoteDao(note, noteModified);
    }
    public  boolean deleteNotes(String note, String username) {
    	return NotesDao.deleteNoteDao(note, username);
    }
}
