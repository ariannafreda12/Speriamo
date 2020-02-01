package utils;

public class Query {
	
	//query for login
	public static String loginQuery= "SELECT * FROM users WHERE username = '%s' AND password = '%s';";
	
	//query for registration
	public static String regQuery = "INSERT INTO users VALUES('%s', '%s', '%s');";
	
	
	//query for select recipe
	public static String recipesQuery= "SELECT * FROM recipes JOIN recipeingredient ON recipes.id_recipe=recipeingredient.id_rec JOIN ingredients ON recipeingredient.id_in=ingredients.id_ing WHERE  category = '%s' AND difficulty = '%s'  AND description = '%s'ORDER BY review DESC;";
		
	//query for found user
	public static String foundUserQuery= "SELECT * FROM users WHERE username = '%s';";
		
	//query for change password
	public static String changePasswordQuery= "UPDATE users SET password = '%s' WHERE username = '%s';";
	
	//query for a single recipe
	public static String recipe1Query= "SELECT * FROM recipes WHERE title= '%s';";
	
	//query for save recipe
	public static String saveQuery = "INSERT INTO recipeuser VALUES('%s', '%s', '%s','%s', '%s', '%s', '%s');";
	
	//query for like recipe
	public static String reviewQuery= "UPDATE recipes SET review = '%s' WHERE title = '%s';";
	
	//query for save like
	public static String saveReviewQuery = "INSERT INTO review VALUES('%s', '%s', '%s');";
	
	//query for save like
	public static String checkReviewQuery = "SELECT rev FROM review WHERE username = '%s' AND title = '%s';";
	
	//query for see user's recipe
	public static String profileQuery= "SELECT * FROM recipeuser WHERE username= '%s';";
	
	//query for see user's notes
	public static String notesQuery= "SELECT * FROM notes WHERE username= '%s';";
	
	//query for save note
	public static String saveNoteQuery = "INSERT INTO notes VALUES('%s', '%s');";
	
	//query for a single note
	public static String openNoteQuery= "SELECT * FROM notes WHERE note= '%s';";
	
	//query for modify note
	public static String modifyNoteQuery= "UPDATE notes SET note= '%s' WHERE note = '%s';";
	
	//query for a single user recipe
	public static String userRecipeQuery= "SELECT * FROM recipeuser WHERE title= '%s';";
	
	//query for delete a user note
	public static String deleteNoteQuery= "DELETE FROM notes WHERE note= '%s' AND username= '%s' ;";
	
	//query for delete a user recipe
	public static String deleteRecipeQuery= "DELETE FROM recipeuser WHERE title= '%s' AND username= '%s' ;";
	
	//query for popular recipes
	public static String popularRecipeQuery= "SELECT * FROM recipes WHERE review > 10 ORDER BY review DESC;";
	
	//query for popular recipes
	public static String dailyRecipeQuery= "SELECT * FROM recipes ORDER BY RANDOM() LIMIT 1 ;";
	
	public static String favQuery= "SELECT * FROM recipeuser WHERE username= '%s' ORDER BY RANDOM() LIMIT 1 ;";
	
}
