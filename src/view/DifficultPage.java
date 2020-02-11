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
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DifficultPage {

	@FXML
	public Text beginnerTxt;
	@FXML
	public Text mediumTxt;
	@FXML
	public Text advancedTxt;
	@FXML
	public Button backButton;
		
	RecipeManager rm= RecipeManager.getInstance();
	RecipeBean rb=rm.getRecipe();
	
	LoginManager lm =LoginManager.getInstance();
	UserBean ub= lm.getUser();
	static Logger logger = Logger.getAnonymousLogger();
	private static final String CONTEXT = "context";
	
	
		
	//logout
	public void logOutDifficultPage(MouseEvent me){
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
	
	//create a new note
	public void createNotedifficultPage(){
    	GraphicController graphicController = new GraphicController();
        graphicController.notePage();
	}
	//go to user profile
	public void myProfileDifficultPage(){
		    	GraphicController graphicController = new GraphicController();
		        graphicController.profilePage();
			}
	
	private void newUserDifficultPage(){
		GraphicController graphicController = new GraphicController();
        graphicController.registrationPage();
	}
	
	public void beginner(MouseEvent beginner){
		rb.setRecBeanDifficulty("beginner");
		rm.setRecipe(rb);
		((Node)(beginner.getSource())).getScene().getWindow().hide();
		GraphicController graphicController = new GraphicController();
		graphicController.ingredientPage();
	}
	
	public void medium(MouseEvent medium){
		rb.setRecBeanDifficulty("medium");
		rm.setRecipe(rb);
	    ((Node)(medium.getSource())).getScene().getWindow().hide();
		GraphicController graphicController = new GraphicController();
		graphicController.ingredientPage();	
	}

	public void advanced(MouseEvent advanced){
		rb.setRecBeanDifficulty("advanced");
		rm.setRecipe(rb);
		((Node)(advanced.getSource())).getScene().getWindow().hide();
		GraphicController graphicController = new GraphicController();
		graphicController.ingredientPage();
	}
	
	public void back(ActionEvent e) {
		((Node)(e.getSource())).getScene().getWindow().hide();
    	GraphicController graphicController = new GraphicController();
        graphicController.mealPage();
	}	
	
	public void start(){
		Stage diffStage = new Stage();
		FXMLLoader loader = new FXMLLoader (Login.class.getResource("difficult_level.fxml"));
		AnchorPane root;
		try {
			root = loader.load();
			diffStage.getIcons().add(new Image("img/icon.png"));
	        diffStage.setTitle("WhatEat?!" );
	        diffStage.setResizable(false);
	        
	        final Label hi= new Label();
	        hi.setText("Hi " + ub.getUsername());
	        hi.setLayoutY(10);
	        hi.setLayoutX(600);
	        hi.setFont(Font.font("System", FontPosture.ITALIC, 18));
	        
	        FileInputStream inputP = new FileInputStream("src\\img\\icons8-nome-100.png");
	        Image imageP = new Image(inputP);
	        ImageView userViewDifficultPage = new ImageView(imageP);
	        userViewDifficultPage.setFitHeight(30);
	        userViewDifficultPage.setFitWidth(34);
	        userViewDifficultPage.setLayoutX(712);
	        userViewDifficultPage.setLayoutY(7);
	        userViewDifficultPage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
	            try {
					myProfileDifficultPage();
				} catch (Exception e) {
					e.printStackTrace();
				}
	        });
	        
	        FileInputStream inputN = new FileInputStream("src\\img\\icons8-libretto-a-spirale-legato-80.png");
	        Image imageN = new Image(inputN);
	        ImageView noteViewDifficultPage = new ImageView(imageN);
	        noteViewDifficultPage.setFitHeight(39);
	        noteViewDifficultPage.setFitWidth(38);
	        noteViewDifficultPage.setLayoutX(490);
	        noteViewDifficultPage.setLayoutY(3);
	        noteViewDifficultPage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
	            try {
					createNotedifficultPage();
				} catch (Exception e) {
					e.printStackTrace();
				}
	        });
	        
	        FileInputStream inputL = new FileInputStream("src\\img\\icons8-uscita-100.png");
	        Image imageL = new Image(inputL);
	        ImageView logoutViewDifficultPage = new ImageView(imageL);
	        logoutViewDifficultPage.setFitHeight(30);
	        logoutViewDifficultPage.setFitWidth(28);
	        logoutViewDifficultPage.setLayoutX(758);
	        logoutViewDifficultPage.setLayoutY(7);
	        logoutViewDifficultPage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
	            try {
					logOutDifficultPage(event);
				} catch (Exception e) {
					e.printStackTrace();
				}
	        });
	        
	        if (lm.getUser().getUsername()=="Chef"){
				noteViewDifficultPage.setVisible(false);
				userViewDifficultPage.setVisible(false);
				FileInputStream inputR = new FileInputStream("src\\img\\icons8-nome-100.png");
		        Image imageR = new Image(inputR);
		        ImageView rViewDifficultPage = new ImageView(imageR);
		        rViewDifficultPage.setFitHeight(30);
		        rViewDifficultPage.setFitWidth(34);
		        rViewDifficultPage.setLayoutX(712);
		        rViewDifficultPage.setLayoutY(7);
		        root.getChildren().add(rViewDifficultPage);
		        rViewDifficultPage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
		            try {
						newUserDifficultPage();
					} catch (Exception e) {
						e.printStackTrace();
					}
		        });
		        
	        }
	        
	        root.getChildren().addAll(hi, noteViewDifficultPage, userViewDifficultPage, logoutViewDifficultPage);
	        
	        Scene scene = new Scene(root, 800, 600);
	        diffStage.setScene(scene);
	        diffStage.show();     
		} catch (IOException e1) {
		
			logger.log(null, CONTEXT,e1);
		}
		
	}
}
