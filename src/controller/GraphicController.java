package controller;

import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import view.ChangePasswordPage;
import view.DifficultPage;
import view.FoundRecipes;
import view.HomePage;
import view.IngredientPage;
import view.Login;
import view.MealPage;
import view.NotePage;
import view.OpenNotePage;
import view.ProfilePage;
import view.RecipePage;
import view.Registration;
import view.UserRecipePage;

public class GraphicController extends Application {
	
	static Logger logger = Logger.getAnonymousLogger();

	@Override
	public void start(Stage primaryStage) throws Exception {
		Login login = new Login();
		login.start(primaryStage);
	}

	public void homePage(){
		HomePage homepage = new HomePage();
		try {
			homepage.start();
		} catch (Exception e) {
			logger.log(null, "context",e);
		}
	}
	
	public void registrationPage() {
		Registration regPage = new Registration();
		try {
			regPage.start();
		} catch (Exception e) {
			logger.log(null, "context",e);
		}
	}

	public void difficultPage() {
		DifficultPage difPage= new DifficultPage();
		try {
			difPage.start();
		} catch (Exception e) {
			logger.log(null, "context",e);
		}		
	}

	public void ingredientPage(){
		IngredientPage ingPage = new IngredientPage();
		try {
			ingPage.start();
		} catch (Exception e) {
			
			logger.log(null, "context",e);
		}
	}

	public void changePasswordPage(){
		ChangePasswordPage cpassPage= new ChangePasswordPage();
		try {
			cpassPage.start();
		} catch (Exception e) {
			logger.log(null, "context",e);
		}
	}

	public void foundPage(){
		FoundRecipes frecPage= new FoundRecipes();
		try {
			frecPage.start();
		} catch (Exception e) {
			logger.log(null, "context",e);
		}
		
	}

	public void showRecipe(){
		RecipePage recPage= new RecipePage();
		try {
			recPage.start();
		} catch (Exception e) {
			logger.log(null, "context",e);
		}
		
	}

	public void mealPage(){
		MealPage mealpage= new MealPage();
		try {
			mealpage.start();
		} catch (Exception e) {
		
			logger.log(null, "context",e);
		}
		
	}


	public void profilePage(){
		ProfilePage profpage= new ProfilePage();
		try {
			profpage.start();
		} catch (Exception e) {
			logger.log(null, "context",e);
		}

		
	}

	public void userRecipePage(){
		UserRecipePage userRecPage = new UserRecipePage();
		try {
			userRecPage.start();
		} catch (Exception e) {
			logger.log(null, "context",e);
		}
		
	}


	public void notePage(){
		NotePage notePage = new NotePage();
		try {
			notePage.start();
		} catch (Exception e) {
			logger.log(null, "context",e);
		}
		
	}
	
	public void openNotePage(){
		OpenNotePage openNotePage = new OpenNotePage();
		try {
			openNotePage.start();
		} catch (Exception e) {
			logger.log(null, "context",e);
		}
		
	}
}
