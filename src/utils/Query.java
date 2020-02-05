package utils;

public class Query {
	
	  private Query() {
		    throw new IllegalStateException("Utility class");
		  }
	
	//query for login
	public static final String LOGINQUERY= "SELECT * FROM users WHERE username = '%s' AND password = '%s';";
	
	//query for registration
	public static final String REGQUERY = "INSERT INTO users VALUES('%s', '%s', '%s');";
	
	
	//query for select recipe
	public static final String RECIPESQUERY= "SELECT * FROM recipes JOIN recipeingredient ON recipes.id_recipe=recipeingredient.id_rec JOIN ingredients ON recipeingredient.id_in=ingredients.id_ing WHERE  category = '%s' AND difficulty = '%s'  AND description = '%s'ORDER BY review DESC;";
		
	//query for found user
	public static final String FOUNDUSERQUERY= "SELECT * FROM users WHERE username = '%s';";
		
	//query for change password
	public static final String CHANGEPASSWORDQUERY= "UPDATE users SET password = '%s' WHERE username = '%s';";
	
	//query for a single recipe
	public static final String RECIPEQUERY= "SELECT * FROM recipes WHERE title= '%s';";
	
	//query for save recipe
	public static final String SAVEQUERY = "INSERT INTO recipeuser VALUES('%s', '%s', '%s','%s', '%s', '%s', '%s');";
	
	//query for like recipe
	public static final String REVIEWQUERY= "UPDATE recipes SET review = '%s' WHERE title = '%s';";
	
	//query for save like
	public static final String SAVEREVIEWQUERY = "INSERT INTO review VALUES('%s', '%s', '%s');";
	
	//query for save like
	public static final String CHECKREVIEWQUERY = "SELECT rev FROM review WHERE username = '%s' AND title = '%s';";
	
	//query for see user's recipe
	public static final String PROFILEQUERY= "SELECT * FROM recipeuser WHERE username= '%s';";
	
	//query for see user's notes
	public static final String NOTESQUERY= "SELECT * FROM notes WHERE username= '%s';";
	
	//query for save note
	public static final String SAVENOTEQUERY = "INSERT INTO notes VALUES('%s', '%s');";
	
	//query for a single note
	public static final String OPENNOTEQUERY= "SELECT * FROM notes WHERE note= '%s';";
	
	//query for modify note
	public static final String MODIFYNOTEQUERY= "UPDATE notes SET note= '%s' WHERE note = '%s';";
	
	//query for a single user recipe
	public static final String USERRECIPEQUERY= "SELECT * FROM recipeuser WHERE title= '%s';";
	
	//query for delete a user note
	public static final String DELETENOTEQUERY= "DELETE FROM notes WHERE note= '%s' AND username= '%s' ;";
	
	//query for delete a user recipe
	public static final  String DELETERECIPEQUERY= "DELETE FROM recipeuser WHERE title= '%s' AND username= '%s' ;";
	
	//query for popular recipes
	public static final String POPULARRECIPEQUERY= "SELECT * FROM recipes WHERE review > 10 ORDER BY review DESC;";
	
	//query for popular recipes
	public static final String DAILYRECIPEQUERY= "SELECT * FROM recipes ORDER BY RANDOM() LIMIT 1 ;";
	
	public static final String FAVQUERY= "SELECT * FROM recipeuser WHERE username= '%s' ORDER BY RANDOM() LIMIT 1 ;";
	
}
