package controller;

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
			e.printStackTrace();
		}
	}
	
	public void registrationPage() throws Exception {
		Registration regPage = new Registration();
		regPage.start();
	}

	public void difficultPage() throws Exception {
		DifficultPage difPage= new DifficultPage();
		difPage.start();		
	}

	public void ingredientPage() throws Exception {
		IngredientPage ingPage = new IngredientPage();
		ingPage.start();
	}

	public void changePasswordPage() throws Exception {
		ChangePasswordPage cpassPage= new ChangePasswordPage();
		cpassPage.start();
	}

	public void foundPage() throws Exception{
		FoundRecipes frecPage= new FoundRecipes();
		frecPage.start();
		
	}

	public void showRecipe() throws Exception {
		RecipePage recPage= new RecipePage();
		recPage.start();
		
	}

	public void mealPage() throws Exception {
		MealPage mealpage= new MealPage();
		mealpage.start();
		
	}


	public void profilePage() throws Exception {
		ProfilePage profpage= new ProfilePage();
		profpage.start();

		
	}

	public void userRecipePage() throws Exception{
		UserRecipePage userRecPage = new UserRecipePage();
		userRecPage.start();
		
	}


	public void notePage() throws Exception{
		NotePage notePage = new NotePage();
		notePage.start();
		
	}
	
	public void openNotePage() throws Exception{
		OpenNotePage openNotePage = new OpenNotePage();
		openNotePage.start();
		
	}
}
