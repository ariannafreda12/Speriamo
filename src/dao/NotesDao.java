package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Notes;
import utils.Query;

public class NotesDao {
	
	private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String USER = "postgres";
	private static final String PASS = "postgres";
	private static Connection connection = null;
	
	private NotesDao() {
	    throw new IllegalStateException("Utility class");
	  }
	
	public static ArrayList <Notes> userNotesDao(String username) {
		 Statement stmt = null;
		 Notes notes = null;
		 ArrayList<Notes> n= new ArrayList <Notes>();
		 
			try {
				connection = DriverManager.getConnection(URL, USER, PASS);
				stmt = connection.createStatement();
				
					
					String sql = String.format(Query.NOTESQUERY, username);
					ResultSet rs = stmt.executeQuery(sql);
					
					while(rs.next()) {
						
						notes = new Notes(rs.getString("note"),username);
								
								n.add(notes);
								
					}
				
		
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(connection != null)
						connection.close();
					if(stmt != null)
						stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return n;		 
	 }
	
	 public static boolean addNoteDao(String note, String username) {
		  Statement stmt = null;
	      Connection conn = null;
	        try {
	            
	            conn = DriverManager.getConnection(URL, USER, PASS);
	            stmt = conn.createStatement();
	            String sql1= String.format(Query.SAVENOTEQUERY,note,username);

	            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	            int rs = stmt.executeUpdate(sql1);
	            if (rs != 1) {
	                return false;
	            }

	            // STEP 6: Clean-up dell'ambiente
	            stmt.close();
	            conn.close();
	            return true;

	        } catch (SQLException se) {
	            // Errore durante l'apertura della connessione
	            se.printStackTrace();
	        } catch (Exception e) {
	            // Errore nel loading del driver
	            e.printStackTrace();
	        } finally {
	            try {
	                if (stmt != null)
	                    stmt.close();
	            } catch (SQLException se2) {
	                se2.printStackTrace();
	            }
	            try {
	                if (conn != null)
	                    conn.close();
	            } catch (SQLException se) {
	                se.printStackTrace();
	            }
	        }
	        return false;
	    }
	
	 public static Notes chooseNoteDao(String note) {
		 Statement stmt = null;
		 Notes notes = null;
		 try {
				connection = DriverManager.getConnection(URL, USER, PASS);
				stmt = connection.createStatement();
					String sql = String.format(Query.OPENNOTEQUERY,note);
					ResultSet rs = stmt.executeQuery(sql);
					
					while(rs.next()) {
						
						notes = new Notes(note,rs.getString("username"));
					}
				
		
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(connection != null)
						connection.close();
					if(stmt != null)
						stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return notes;		 
	 }
	 public static boolean modifyNoteDao(String note, String noteModified) {
		  Statement stmt = null;
	      Connection conn = null;
	        try {
	            conn = DriverManager.getConnection(URL, USER, PASS);
	            stmt = conn.createStatement();
	            String sql1= String.format(Query.MODIFYNOTEQUERY,noteModified,note);
	            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	            int rs = stmt.executeUpdate(sql1);

	            if (rs != 1) {
	                System.out.println("Errore nella Query.");
	                return false;
	            }

	            // STEP 6: Clean-up dell'ambiente
	            stmt.close();
	            conn.close();
	            return true;

	        } catch (SQLException se) {
	            // Errore durante l'apertura della connessione
	            se.printStackTrace();
	        } catch (Exception e) {
	            // Errore nel loading del driver
	            e.printStackTrace();
	        } finally {
	            try {
	                if (stmt != null)
	                    stmt.close();
	            } catch (SQLException se2) {
	                se2.printStackTrace();
	            }
	            try {
	                if (conn != null)
	                    conn.close();
	            } catch (SQLException se) {
	                se.printStackTrace();
	            }
	        }
	        return false;
	    }
	 public static boolean deleteNoteDao(String note, String username) {
		  Statement stmt = null;
	      Connection conn = null;
	        try {
	            conn = DriverManager.getConnection(URL, USER, PASS);
	            stmt = conn.createStatement();
	            String sql1= String.format(Query.DELETENOTEQUERY,note,username);
	            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	            int rs = stmt.executeUpdate(sql1);

	            if (rs != 1) {
	                return false;
	            }
	            stmt.close();
	            conn.close();
	            return true;

	        } catch (SQLException se) {
	            se.printStackTrace();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (stmt != null)
	                    stmt.close();
	            } catch (SQLException se2) {
	                se2.printStackTrace();
	            }
	            try {
	                if (conn != null)
	                    conn.close();
	            } catch (SQLException se) {
	                se.printStackTrace();
	            }
	        }
	        return false;
	    }
	

}
