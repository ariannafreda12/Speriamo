package test;

import static org.junit.Assert.assertEquals;


import org.junit.jupiter.api.Test;

import bean.NotesBean;


class TestDNotContr {

	@Test
	void testDNoteContr() {
		String note= "ciao";
		String note2="";
		NotesBean nb= new NotesBean();
		nb.setNote(note);
		note2=nb.getNote();
		assertEquals("ciao",note2);
		
	}
	

}
