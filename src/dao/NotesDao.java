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
	private final static String DRIVER_CLASS_NAME = "org.postgresql.Driver";
	private static Connection connection = null;
	
	public static ArrayList <Notes> userNotesDao(String username) {
		 Statement stmt = null;
		 Notes notes = null;
		 ArrayList<Notes> n= new ArrayList <Notes>();
		 
			try {
				Class.forName(DRIVER_CLASS_NAME);
				connection = DriverManager.getConnection(URL, USER, PASS);
				stmt = connection.createStatement();
				
					
					String sql = String.format(Query.notesQuery, username);
					System.out.println("query:\n"+sql);
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
	            Class.forName("org.postgresql.Driver");
	            conn = DriverManager.getConnection(URL, USER, PASS);
	            stmt = conn.createStatement();
	            String sql1= String.format(Query.saveNoteQuery,note,username);
	            System.out.println("query:\n"+sql1);
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
	        System.out.println("Query fallita");
	        return false;
	    }
	
	 public static Notes chooseNoteDao(String note) {
		 Statement stmt = null;
		 Notes notes = null;
		
		 
			try {
				Class.forName(DRIVER_CLASS_NAME);
				connection = DriverManager.getConnection(URL, USER, PASS);
				stmt = connection.createStatement();
				
					
					String sql = String.format(Query.openNoteQuery,note);
					System.out.println("query:\n"+sql);
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
	            Class.forName("org.postgresql.Driver");
	            conn = DriverManager.getConnection(URL, USER, PASS);
	            stmt = conn.createStatement();
	            String sql1= String.format(Query.modifyNoteQuery,noteModified,note);
	            System.out.println("query:\n"+sql1);
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
	        System.out.println("Query fallita");
	        return false;
	    }
	 public static boolean deleteNoteDao(String note, String username) {
		  Statement stmt = null;
	      Connection conn = null;
	        try {
	            Class.forName("org.postgresql.Driver");
	            conn = DriverManager.getConnection(URL, USER, PASS);
	            stmt = conn.createStatement();
	            String sql1= String.format(Query.deleteNoteQuery,note,username);
	            System.out.println("query:\n"+sql1);
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
	        System.out.println("Query fallita");
	        return false;
	    }
	

}
