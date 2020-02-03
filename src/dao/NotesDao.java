package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import model.Notes;
import utils.Query;

public class NotesDao {
	
	private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String USER = "postgres";
	private static final String PASS = "postgres";
	private static Connection connection = null;
	
	static Logger logger = Logger.getAnonymousLogger();
	private static final String CONTEXT = "context";
	
	private NotesDao() {
	    throw new IllegalStateException("Utility class");
	  }
	
	public static Set<Notes> userNotesDao(String username) {
		 Statement statementUserNote = null;
		 Notes notes = null;
		 Set<Notes> n = new HashSet<Notes>();
		 
		 
			try {
				connection = DriverManager.getConnection(URL, USER, PASS);
				statementUserNote = connection.createStatement();
				
					
					String sqlUserNote = String.format(Query.NOTESQUERY, username);
					ResultSet rsUserNote = statementUserNote.executeQuery(sqlUserNote);
					
					while(rsUserNote.next()) {
						
						notes = new Notes(rsUserNote.getString("note"),username);
								
								n.add(notes);
								
					}
				
		
			} catch(Exception eUserNote) {
				logger.log(null, CONTEXT,eUserNote);
			} finally {
				try {
					if(connection != null)
						connection.close();
					if(statementUserNote != null)
						statementUserNote.close();
				} catch (SQLException eUserNote) {
					logger.log(null, CONTEXT,eUserNote);
				}
			}
			return n;		 
	 }
	
	 public static boolean addNoteDao(String note, String username) {
		  Statement statementAddNote = null;
	      Connection connecctionAddNote = null;
	        try {
	            
	            connecctionAddNote = DriverManager.getConnection(URL, USER, PASS);
	            statementAddNote = connecctionAddNote.createStatement();
	            String sqlAddNote= String.format(Query.SAVENOTEQUERY,note,username);

	            statementAddNote = connecctionAddNote.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	            int rsAddNote = statementAddNote.executeUpdate(sqlAddNote);
	            if (rsAddNote != 1) {
	                return false;
	            }

	            
	            return true;

	        } catch (SQLException seAddNote) {
	            // Errore durante l'apertura della connessione
	        	logger.log(null, CONTEXT,seAddNote);
	        } catch (Exception eAddNote) {
	            // Errore nel loading del driver
	        	logger.log(null, CONTEXT,eAddNote);
	        } finally {
	            try {
	                if (statementAddNote != null)
	                    statementAddNote.close();
	            } catch (SQLException se2AddNote) {
	            	logger.log(null, CONTEXT,se2AddNote);
	            }
	            try {
	                if (connecctionAddNote != null)
	                    connecctionAddNote.close();
	            } catch (SQLException seAddNote) {
	            	logger.log(null, CONTEXT,seAddNote);
	            }
	           
	            
	        }
	        return false;
	    }
	
	 public static Notes chooseNoteDao(String note) {
		 Statement statementChooseNote = null;
		 Notes notes = null;
		 try {
				connection = DriverManager.getConnection(URL, USER, PASS);
				statementChooseNote = connection.createStatement();
					String sqlChooseNote = String.format(Query.OPENNOTEQUERY,note);
					ResultSet rsChooseNote = statementChooseNote.executeQuery(sqlChooseNote);
					
					while(rsChooseNote.next()) {
						
						notes = new Notes(note,rsChooseNote.getString("username"));
					}
				
		
			} catch(Exception eChooseNote) {
				logger.log(null, CONTEXT,eChooseNote);
			} finally {
				try {
					if(connection != null)
						connection.close();
					if(statementChooseNote != null)
						statementChooseNote.close();
				} catch (SQLException eChooseNote) {
					logger.log(null, CONTEXT,eChooseNote);
				}
			}
			return notes;		 
	 }
	 public static boolean modifyNoteDao(String note, String noteModified) {
		  Statement statementModifyNote = null;
	      Connection connectionModifyNote = null;
	        try {
	            connectionModifyNote = DriverManager.getConnection(URL, USER, PASS);
	            statementModifyNote = connectionModifyNote.createStatement();
	            String sql1ModifyNote= String.format(Query.MODIFYNOTEQUERY,noteModified,note);
	            statementModifyNote = connectionModifyNote.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	            int rsModifyNote = statementModifyNote.executeUpdate(sql1ModifyNote);

	            if (rsModifyNote != 1) {
	               
	                return false;
	            }

	          
	            return true;

	        } catch (SQLException seModifyNote) {
	            // Errore durante l'apertura della connessione
	        	logger.log(null, CONTEXT,seModifyNote);
	        } catch (Exception eModifyNote) {
	            // Errore nel loading del driver
	        	logger.log(null, CONTEXT,eModifyNote);
	        } finally {
	            try {
	                if (statementModifyNote != null)
	                    statementModifyNote.close();
	            } catch (SQLException se2ModifyNote) {
	            	logger.log(null, CONTEXT,se2ModifyNote);
	            }
	            try {
	                if (connectionModifyNote != null)
	                    connectionModifyNote.close();
	            } catch (SQLException seModifyNote) {
	            	logger.log(null, CONTEXT,seModifyNote);
	            }
	        }
	        return false;
	    }
	 public static boolean deleteNoteDao(String note, String username) {
		  Statement statementDeleteNote = null;
	      Connection connectionDeleteNote = null;
	        try {
	            connectionDeleteNote = DriverManager.getConnection(URL, USER, PASS);
	            statementDeleteNote = connectionDeleteNote.createStatement();
	            String sql1DeleteNote= String.format(Query.DELETENOTEQUERY,note,username);
	            statementDeleteNote = connectionDeleteNote.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	            int rsDeleteNote = statementDeleteNote.executeUpdate(sql1DeleteNote);

	            if (rsDeleteNote != 1) {
	                return false;
	            }
	            
	            return true;

	        } catch (SQLException seDeleteNote) {
	        	logger.log(null, CONTEXT,seDeleteNote);
	        } catch (Exception eDeleteNote) {
	        	logger.log(null, CONTEXT,eDeleteNote);
	        } finally {
	            try {
	                if (statementDeleteNote != null)
	                    statementDeleteNote.close();
	            } catch (SQLException se2DeleteNote) {
	            	logger.log(null, CONTEXT,se2DeleteNote);
	            }
	            try {
	                if (connectionDeleteNote != null)
	                    connectionDeleteNote.close();
	            } catch (SQLException seDeleteNote) {
	            	logger.log(null, CONTEXT,seDeleteNote);
	            }
	        }
	        return false;
	    }
	

}
