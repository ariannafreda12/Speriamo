package utils;

public class Query {
	
	//query for login
	public static final String loginQuery= "SELECT * FROM users WHERE username = '%s' AND password = '%s';";
	
	//query for registration
	public static final String regQuery = "INSERT INTO users VALUES('%s', '%s', '%s');";
	
	
	//query for select recipe
	public static final String recipesQuery= "SELECT * FROM recipes JOIN recipeingredient ON recipes.id_recipe=recipeingredient.id_rec JOIN ingredients ON recipeingredient.id_in=ingredients.id_ing WHERE  category = '%s' AND difficulty = '%s'  AND description = '%s'ORDER BY review DESC;";
		
	//query for found user
	public static final String foundUserQuery= "SELECT * FROM users WHERE username = '%s';";
		
	//query for change password
	public static final String changePasswordQuery= "UPDATE users SET password = '%s' WHERE username = '%s';";
	
	//query for a single recipe
	public static final String recipe1Query= "SELECT * FROM recipes WHERE title= '%s';";
	
	//query for save recipe
	public static final String saveQuery = "INSERT INTO recipeuser VALUES('%s', '%s', '%s','%s', '%s', '%s', '%s');";
	
	//query for like recipe
	public static final String reviewQuery= "UPDATE recipes SET review = '%s' WHERE title = '%s';";
	
	//query for save like
	public static final String saveReviewQuery = "INSERT INTO review VALUES('%s', '%s', '%s');";
	
	//query for save like
	public static final String checkReviewQuery = "SELECT rev FROM review WHERE username = '%s' AND title = '%s';";
	
	//query for see user's recipe
	public static final String profileQuery= "SELECT * FROM recipeuser WHERE username= '%s';";
	
	//query for see user's notes
	public static final String notesQuery= "SELECT * FROM notes WHERE username= '%s';";
	
	//query for save note
	public static final String saveNoteQuery = "INSERT INTO notes VALUES('%s', '%s');";
	
	//query for a single note
	public static final String openNoteQuery= "SELECT * FROM notes WHERE note= '%s';";
	
	//query for modify note
	public static final String modifyNoteQuery= "UPDATE notes SET note= '%s' WHERE note = '%s';";
	
	//query for a single user recipe
	public static final String userRecipeQuery= "SELECT * FROM recipeuser WHERE title= '%s';";
	
	//query for delete a user note
	public static final String deleteNoteQuery= "DELETE FROM notes WHERE note= '%s' AND username= '%s' ;";
	
	//query for delete a user recipe
	public static final  String deleteRecipeQuery= "DELETE FROM recipeuser WHERE title= '%s' AND username= '%s' ;";
	
	//query for popular recipes
	public static final String popularRecipeQuery= "SELECT * FROM recipes WHERE review > 10 ORDER BY review DESC;";
	
	//query for popular recipes
	public static final String dailyRecipeQuery= "SELECT * FROM recipes ORDER BY RANDOM() LIMIT 1 ;";
	
	public static final String favQuery= "SELECT * FROM recipeuser WHERE username= '%s' ORDER BY RANDOM() LIMIT 1 ;";
	
}
