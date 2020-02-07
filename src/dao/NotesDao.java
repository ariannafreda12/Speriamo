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
	private static Statement statementNote = null;
	private static Connection connectionNote = null;
	
	
	static Logger logger = Logger.getAnonymousLogger();
	private static final String CONTEXT = "context";
	
	
	private NotesDao() {
	    throw new IllegalStateException("Utility class");
	  }
	
	public static Set<Notes> userNotesDao(String username) {
		 
		 Notes notes = null;
		 Set<Notes> n = new HashSet<>();
		 
		 
			try {
				connectionNote = DriverManager.getConnection(URL, USER, PASS);
				statementNote = connectionNote.createStatement();
				
					
					String sqlUserNote = String.format(Query.NOTESQUERY, username);
					ResultSet rsUserNote = statementNote.executeQuery(sqlUserNote);
					
					while(rsUserNote.next()) {
						
						notes = new Notes(rsUserNote.getString("note"),username);
								
								n.add(notes);
								
					}
				
		
			} catch(Exception eUserNote) {
				logger.log(null, CONTEXT,eUserNote);
			} finally {
				
			      try {
		                if (statementNote != null)
		                    statementNote.close();
		            } catch (SQLException se2UserNote) {
		            	logger.log(null, CONTEXT,se2UserNote);
		            }
		            try {
		                if (connectionNote != null)
		                    connectionNote.close();
		            } catch (SQLException seUserNote) {
		            	logger.log(null, CONTEXT,seUserNote);
		            }
		           
			}
			return n;		 
	 }
	
	 public static boolean addNoteDao(String note, String username) {
		 
	    
	        try {
	        
	            connectionNote = DriverManager.getConnection(URL, USER, PASS);
	           statementNote = connectionNote.createStatement();
	            String sqlAddNote= String.format(Query.SAVENOTEQUERY,note,username);

	            statementNote = connectionNote.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	            int rsAddNote = statementNote.executeUpdate(sqlAddNote);
	            if (rsAddNote != 1) {
	                return false;
	            }
	            
	           

	           
	         statementNote.close();
	         connectionNote.close();
	           
	          
	            return true;
	        } catch (SQLException seAddNote) {
	           
	        	logger.log(null, CONTEXT,seAddNote);
	        } catch (Exception eAddNote) {
	           
	        	logger.log(null, CONTEXT,eAddNote);
	        } finally {
	            try {
	               
	                    statementNote.close();
	            } catch (SQLException se2AddNote) {
	            	logger.log(null, CONTEXT,se2AddNote);
	            }
	            try {
	               
	                    connectionNote.close();
	            } catch (SQLException seAddNote) {
	            	logger.log(null, CONTEXT,seAddNote);
	            }
	           
	            
	        }
	        return false;
	    }
	 
	
	 public static Notes chooseNoteDao(String note) {
		
		 Notes notes = null;
		 try {
				connectionNote = DriverManager.getConnection(URL, USER, PASS);
				statementNote = connectionNote.createStatement();
					String sqlChooseNote = String.format(Query.OPENNOTEQUERY,note);
					ResultSet rsChooseNote = statementNote.executeQuery(sqlChooseNote);
					
					while(rsChooseNote.next()) {
						
						notes = new Notes(note,rsChooseNote.getString("username"));
					}
				
		
			} catch(Exception eChooseNote) {
				logger.log(null, CONTEXT,eChooseNote);
			} finally {
				try {
					if(connectionNote != null)
						connectionNote.close();
					if(statementNote != null)
						statementNote.close();
				} catch (SQLException eChooseNote) {
					logger.log(null, CONTEXT,eChooseNote);
				}
			}
			return notes;		 
	 }
	 public static boolean modifyNoteDao(String note, String noteModified) {
		 
	      
	        try {
	            connectionNote = DriverManager.getConnection(URL, USER, PASS);
	            statementNote = connectionNote.createStatement();
	            String sql1ModifyNote= String.format(Query.MODIFYNOTEQUERY,noteModified,note);
	            statementNote = connectionNote.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	            int rsModifyNote = statementNote.executeUpdate(sql1ModifyNote);

	            if (rsModifyNote != 1) {
	               
	                return false;
	            }

	            statementNote.close();
	            connectionNote.close();
	            return true;

	          
	           

	        } catch (SQLException seModifyNote) {
	            // Errore durante l'apertura della connessione
	        	logger.log(null, CONTEXT,seModifyNote);
	        } catch (Exception eModifyNote) {
	            // Errore nel loading del driver
	        	logger.log(null, CONTEXT,eModifyNote);
	        } finally {
	            try {
	                if (statementNote != null)
	                    statementNote.close();
	            } catch (SQLException se2ModifyNote) {
	            	logger.log(null, CONTEXT,se2ModifyNote);
	            }
	            try {
	                if (connectionNote != null)
	                    connectionNote.close();
	            } catch (SQLException seModifyNote) {
	            	logger.log(null, CONTEXT,seModifyNote);
	            }
	        }
	        return false;
	    }
	 public static boolean deleteNoteDao(String note, String username) {
		 
	        try {
	            connectionNote = DriverManager.getConnection(URL, USER, PASS);
	            statementNote = connectionNote.createStatement();
	            String sql1DeleteNote= String.format(Query.DELETENOTEQUERY,note,username);
	            statementNote = connectionNote.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	            int rsDeleteNote = statementNote.executeUpdate(sql1DeleteNote);

	            if (rsDeleteNote != 1) {
	                return false;
	            }

	            statementNote.close();
	            connectionNote.close();
	            return true;
	          

	        } catch (SQLException seDeleteNote) {
	        	logger.log(null, CONTEXT,seDeleteNote);
	        } catch (Exception eDeleteNote) {
	        	logger.log(null, CONTEXT,eDeleteNote);
	        } finally {
	            try {
	                if (statementNote != null)
	                    statementNote.close();
	            } catch (SQLException se2DeleteNote) {
	            	logger.log(null, CONTEXT,se2DeleteNote);
	            }
	            try {
	                if (connectionNote != null)
	                    connectionNote.close();
	            } catch (SQLException seDeleteNote) {
	            	logger.log(null, CONTEXT,seDeleteNote);
	            }
	        }
	        return false;
	    }
	

}
