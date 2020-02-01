package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.UserProfile;
import utils.Query;

public class UserProfileDao {
	private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String USER = "postgres";
	private static final String PASS = "postgres";
	private final static String DRIVER_CLASS_NAME = "org.postgresql.Driver";
	private static Connection connection = null;
	
	
	 public static boolean saveRecipeDao(String title, String preparation, String difficulty, String category, String time, String necessary,String username) {
		  Statement stmt = null;
	      Connection conn = null;
	        try {
	            Class.forName("org.postgresql.Driver");
	            conn = DriverManager.getConnection(URL, USER, PASS);
	            stmt = conn.createStatement();
	            String sql1= String.format(Query.saveQuery, title, preparation, difficulty,category,time,necessary,username);
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
	
	public static ArrayList <UserProfile> userRecipeDao(String username) {
		 Statement stmt = null;
		 UserProfile userprofile = null;
		 ArrayList<UserProfile> up= new ArrayList <UserProfile>();
		 
			try {
				Class.forName(DRIVER_CLASS_NAME);
				connection = DriverManager.getConnection(URL, USER, PASS);
				stmt = connection.createStatement();
				
					
					String sql = String.format(Query.profileQuery, username);
					System.out.println("query:\n"+sql);
					ResultSet rs = stmt.executeQuery(sql);
					
					while(rs.next()) {
						
						userprofile = new UserProfile(rs.getString("title"), rs.getString("preparation"),
								rs.getString("difficulty"), rs.getString("category"), rs.getString("time"), rs.getString("necessary"),username);
								System.out.println(rs.getString("title"));
								up.add(userprofile);
								
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
			return up;		 
	 }
	 public static UserProfile chooseUserRecipeDao(String title) {
		 Statement stmt = null;
		 UserProfile up = null;
			try {
				Class.forName(DRIVER_CLASS_NAME);
				connection = DriverManager.getConnection(URL, USER, PASS);
				stmt = connection.createStatement();
					
					String sql = String.format(Query.userRecipeQuery,title);
					
					System.out.println("query:\n"+sql);
					ResultSet rs = stmt.executeQuery(sql);
					
					if(rs.next()) {
					
						up = new UserProfile(rs.getString("title"), rs.getString("preparation"),rs.getString("difficulty"),  rs.getString("category"), rs.getString("time"),rs.getString("necessary"),rs.getString("username"));
						System.out.println(rs.getString("title"));	
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
			return up;		 
	 }
	 
	 public static boolean deleteRecipeDao(String title, String username) {
		  Statement stmt = null;
	      Connection conn = null;
	        try {
	            Class.forName("org.postgresql.Driver");
	            conn = DriverManager.getConnection(URL, USER, PASS);
	            stmt = conn.createStatement();
	            String sql1= String.format(Query.deleteRecipeQuery,title,username);
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
public static UserProfile favRecipeDao(String username) {
		 
		 Statement stmt = null;
		 UserProfile favRecipe= null;
		 
			try {
				Class.forName(DRIVER_CLASS_NAME);
				connection = DriverManager.getConnection(URL, USER, PASS);
				stmt = connection.createStatement();
			
				String sql = String.format(Query.favQuery, username);
				System.out.println("query:\n"+sql);
				ResultSet rs = stmt.executeQuery(sql);
				
				while(rs.next()) {
				favRecipe = new UserProfile(rs.getString("title"), rs.getString("preparation"),
							 rs.getString("difficulty"),  rs.getString("category"), rs.getString("time"), rs.getString("necessary"),username);
				System.out.println(rs.getString("title"));
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
			return favRecipe;		 
	 }
	 
}
