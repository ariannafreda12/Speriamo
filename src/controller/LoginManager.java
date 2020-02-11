package controller;


import dao.UserDao;
import model.User;
import bean.UserBean;

public class LoginManager {
	private static LoginManager instance = null;         //singleton
	private UserBean user;



    public static LoginManager getInstance() {

        if (instance == null) {
            instance = new LoginManager();
        }
        return instance;
    }
    
    public UserBean getUser() {
        return user;
     }

    public void setUser(UserBean u){
        user = u;
    }

    public User login(String nickname, String password) {
        User data = null;
        data = UserDao.loginDao(nickname, password);
        return data;
    }
    
    public static boolean register(String username, String password, String email) {
    	return UserDao.registrationDao(username, password, email);
    }

    public  User foundUser(String username){
    	return UserDao.foundUserDao(username);
    }

    public  boolean changePassword(String username, String password) {
    	return UserDao.changePassword(username,password);
 
    }
    public void resetUser() {
        LoginManager.getInstance().getUser().reset();
    }
}
