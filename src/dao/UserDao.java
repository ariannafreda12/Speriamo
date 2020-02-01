package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.User;
import utils.Query;


public class UserDao {
	
	
	 private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
	 private static final String USER = "postgres";
	 private static final String PASS = "postgres";
	 private final static String DRIVER_CLASS_NAME = "org.postgresql.Driver";
	  private static Connection connection = null;
	
	  public static User loginDao(String username, String password) {
		 
		  Statement stmt = null;
		  User u = null;
			try {
				Class.forName(DRIVER_CLASS_NAME);
				connection = DriverManager.getConnection(URL, USER, PASS);
				stmt = connection.createStatement();
				String sql = String.format(Query.loginQuery, username, password);
				ResultSet rs = stmt.executeQuery(sql);
				
				if(rs.next()) {
					u = new User(username, password, rs.getString("email"));
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
			return u;
		}
	  
	  
	  
	
	  public static boolean registrationDao(String username, String password, String email) {
		  Statement stmt = null;
	        Connection conn = null;
	        try {
	            Class.forName("org.postgresql.Driver");
	            conn = DriverManager.getConnection(URL, USER, PASS);
	            stmt = conn.createStatement();
	            String sql1= String.format(Query.regQuery, username, password, email);
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

	            System.out.println("Registrazione effettuata con successo");
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

	  public static User foundUserDao(String username) {
			Statement stmt = null;
			User u = null;
				try {
					Class.forName(DRIVER_CLASS_NAME);
					connection = DriverManager.getConnection(URL, USER, PASS);
					stmt = connection.createStatement();
					String sql = String.format(Query.foundUserQuery, username);
					ResultSet rs = stmt.executeQuery(sql);
					
					if(rs.next()) {
						u = new User(username, rs.getString("password"), rs.getString("email"));
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
				return u;
			}




		public static boolean changePassword(String username, String password) {
			 Statement stmt = null;
		        Connection conn = null;
		        try {
		            Class.forName("org.postgresql.Driver");
		            conn = DriverManager.getConnection(URL, USER, PASS);
		            stmt = conn.createStatement();
		            String sql1= String.format(Query.changePasswordQuery, password, username);
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

		            System.out.println("Registrazione effettuata con successo");
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
