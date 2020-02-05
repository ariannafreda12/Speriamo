package controller;


import java.util.Set;
import bean.UserRecipeBean;
import dao.UserProfileDao;
import model.UserProfile;

public class UserProfileManager {
	
	private static UserProfileManager instance = null;
	private UserRecipeBean userprofile;//singleton



    public static UserProfileManager getInstance() {

        if (instance == null) {
            instance = new UserProfileManager();
        }
        return instance;
    }
    
    public UserRecipeBean getUserProfile() {
        return userprofile;
     }

    public void setUserProfile(UserRecipeBean up){
    	userprofile = up;
    }
    public boolean saveRecipe(String title, String preparation, String difficulty, String category, String time, String necessary,String username) {
    	return UserProfileDao.saveRecipeDao(title, preparation, difficulty, category, time, necessary, username);
    }
    
    public static Set<UserProfile> userRecipe(String username) {
    	return UserProfileDao.userRecipeDao(username);
    }
    public UserProfile chooseUserRecipe(String title) {
    	return UserProfileDao.chooseUserRecipeDao(title);
    }
    
    public  boolean deleteRecipe(String title, String username) {
    	return UserProfileDao.deleteRecipeDao(title, username);
    }
    
    public static UserProfile favRecipe(String username) {
    	return UserProfileDao.favRecipeDao(username);
    }
    


}
