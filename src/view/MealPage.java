package view;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Logger;

import bean.RecipeBean;
import bean.UserBean;
import controller.GraphicController;
import controller.LoginManager;
import controller.RecipeManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;

public class MealPage {
	
	@FXML
	public Text txtStarters;
	@FXML
	public Text txtMain;
	@FXML
	public Text txtSecond;
	@FXML
	public Text txtSideDishes;
	@FXML
	public Text txtBreakfast;
	@FXML
	public Text txtDessert;
	
	LoginManager lm =LoginManager.getInstance();
	UserBean ub= lm.getUser();
		
	RecipeBean rb = new RecipeBean();
	RecipeManager rm= RecipeManager.getInstance();
	
	static Logger logger = Logger.getAnonymousLogger();
	 private static final String CONTEXT = "context";
	
	public void chooseStarters(MouseEvent starters){
		rb.setRecBeanCategory("starters");
		rm.setRecipe(rb);
		showDiffPage(starters);
	}
		
	public void chooseMainCourses(MouseEvent mainCourses) {
		rb.setRecBeanCategory("main course");
		rm.setRecipe(rb);
		showDiffPage(mainCourses);		
	}
	
	public void choosSecondCourses(MouseEvent secCourses) {
		rb.setRecBeanCategory("second course");
		rm.setRecipe(rb);
		showDiffPage(secCourses);	
	}

	public void chooseSide(MouseEvent side) {
		rb.setRecBeanCategory("side");
		rm.setRecipe(rb);
		showDiffPage(side);
	}
	
	public void chooseBreakfast(MouseEvent breakfast){
		rb.setRecBeanCategory("breakfast");
		rm.setRecipe(rb);
		showDiffPage(breakfast);
	}
	
	public void chooseDessert(MouseEvent dessert){
		rb.setRecBeanCategory("dessert");
		rm.setRecipe(rb);
		showDiffPage(dessert);
  	}
	
	public void showDiffPage(MouseEvent e)  {
		
		((Node)(e.getSource())).getScene().getWindow().hide();
    	GraphicController graphicController = new GraphicController();
        graphicController.difficultPage();
	}
	
	public void myProfile()  {
    	GraphicController graphicController = new GraphicController();
        graphicController.profilePage();
	}
	
	public void back(ActionEvent e) {
		((Node)(e.getSource())).getScene().getWindow().hide();
    	GraphicController graphicController = new GraphicController();
        graphicController.homePage();
	}
	
	public void logOutMealPage(MouseEvent me){
		LoginManager controller = new LoginManager();
        controller.resetUser();
        ((Node)(me.getSource())).getScene().getWindow().hide();
        GraphicController graphicController = new GraphicController();
        Stage stage = null;
        try {
			graphicController.start(stage);
		} catch (Exception e) {
			logger.log(null, CONTEXT,e);

		}
		
	}
	
	public void createNoteMealPage(){
    	GraphicController graphicController = new GraphicController();
        graphicController.notePage();
	}
	
	private void newUserMealPage(){
		GraphicController graphicController = new GraphicController();
        graphicController.registrationPage();
	}
	
	public void start(){
		
		Stage mealStage = new Stage();
		FXMLLoader loader = new FXMLLoader(MealPage.class.getResource("meal.fxml"));
		AnchorPane root;
		try {
			root = loader.load();
			mealStage.getIcons().add(new Image("img/icon.png"));
	        mealStage.setTitle("WhatEat?!" );
	        mealStage.setResizable(false);

	        final Label hi= new Label();
	        hi.setText("Hi " + ub.getUsername());
	        hi.setLayoutY(10);
	        hi.setLayoutX(600);
	        hi.setFont(Font.font("System", FontPosture.ITALIC, 18));
	        
	        FileInputStream inputP = new FileInputStream("src\\img\\icons8-nome-100.png");
	        Image imageP = new Image(inputP);
	        ImageView userViewMealPage = new ImageView(imageP);
	        userViewMealPage.setFitHeight(30);
	        userViewMealPage.setFitWidth(34);
	        userViewMealPage.setLayoutX(712);
	        userViewMealPage.setLayoutY(7);
	        userViewMealPage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
	            try {
					myProfile();
				} catch (Exception e) {
					e.printStackTrace();
				}
	        });
	        
	        FileInputStream inputN = new FileInputStream("src\\img\\icons8-libretto-a-spirale-legato-80.png");
	        Image imageN = new Image(inputN);
	        ImageView noteViewMealPage = new ImageView(imageN);
	        noteViewMealPage.setFitHeight(39);
	        noteViewMealPage.setFitWidth(38);
	        noteViewMealPage.setLayoutX(490);
	        noteViewMealPage.setLayoutY(3);
	        noteViewMealPage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
	            try {
					createNoteMealPage();
				} catch (Exception e) {
					e.printStackTrace();
				}
	        });
	        
	        FileInputStream inputL = new FileInputStream("src\\img\\icons8-uscita-100.png");
	        Image imageL = new Image(inputL);
	        ImageView logoutViewMealPage = new ImageView(imageL);
	        logoutViewMealPage.setFitHeight(30);
	        logoutViewMealPage.setFitWidth(28);
	        logoutViewMealPage.setLayoutX(758);
	        logoutViewMealPage.setLayoutY(7);
	        logoutViewMealPage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
	            try {
					logOutMealPage(event);
				} catch (Exception e) {
					e.printStackTrace();
				}
	        });
	        
	        if (lm.getUser().getUsername()=="Chef"){
				noteViewMealPage.setVisible(false);
				userViewMealPage.setVisible(false);
				FileInputStream inputR = new FileInputStream("src\\img\\icons8-nome-100.png");
		        Image imageR = new Image(inputR);
		        ImageView rViewMealPage = new ImageView(imageR);
		        rViewMealPage.setFitHeight(30);
		        rViewMealPage.setFitWidth(34);
		        rViewMealPage.setLayoutX(712);
		        rViewMealPage.setLayoutY(7);
		        root.getChildren().add(rViewMealPage);
		        rViewMealPage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
		            try {
						newUserMealPage();
					} catch (Exception e) {
						e.printStackTrace();
					}
		        });
		        
	        }
	        
	        root.getChildren().addAll(hi, noteViewMealPage, userViewMealPage, logoutViewMealPage);
	        
	        Scene scene = new Scene(root, 800, 600);
	        mealStage.setScene(scene);
	        mealStage.show();
		
		} catch (IOException e1) {
			logger.log(null, CONTEXT,e1);
		}
		
		
	}

	
}
