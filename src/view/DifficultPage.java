package view;


import java.io.FileInputStream;

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
	
	
		
	//logout
	public void logOut(MouseEvent me) throws Exception {
		LoginManager controller = new LoginManager();
	    controller.resetUser();
	    ((Node)(me.getSource())).getScene().getWindow().hide();
	    GraphicController graphicController = new GraphicController();
	    Stage stage = null;
	    graphicController.start(stage);	
	}
	
	//create a new note
	public void createNote() throws Exception {
    	GraphicController graphicController = new GraphicController();
        graphicController.notePage();
	}
	//go to user profile
	public void myProfile(){
		    	GraphicController graphicController = new GraphicController();
		        graphicController.profilePage();
			}
	
	private void newUser() throws Exception {
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
	
	public void start() throws Exception {
		Stage diffStage = new Stage();
		FXMLLoader loader = new FXMLLoader (Login.class.getResource("difficult_level.fxml"));
		AnchorPane root = loader.load();
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
        ImageView userView = new ImageView(imageP);
        userView.setFitHeight(30);
        userView.setFitWidth(34);
        userView.setLayoutX(712);
        userView.setLayoutY(7);
        userView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
				myProfile();
			} catch (Exception e) {
				e.printStackTrace();
			}
        });
        
        FileInputStream inputN = new FileInputStream("src\\img\\icons8-libretto-a-spirale-legato-80.png");
        Image imageN = new Image(inputN);
        ImageView noteView = new ImageView(imageN);
        noteView.setFitHeight(39);
        noteView.setFitWidth(38);
        noteView.setLayoutX(490);
        noteView.setLayoutY(3);
        noteView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
				createNote();
			} catch (Exception e) {
				e.printStackTrace();
			}
        });
        
        FileInputStream inputL = new FileInputStream("src\\img\\icons8-uscita-100.png");
        Image imageL = new Image(inputL);
        ImageView logoutView = new ImageView(imageL);
        logoutView.setFitHeight(30);
        logoutView.setFitWidth(28);
        logoutView.setLayoutX(758);
        logoutView.setLayoutY(7);
        logoutView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
				logOut(event);
			} catch (Exception e) {
				e.printStackTrace();
			}
        });
        
        if (lm.getUser().getUsername()=="Chef"){
			noteView.setVisible(false);
			userView.setVisible(false);
			FileInputStream inputR = new FileInputStream("src\\img\\icons8-nome-100.png");
	        Image imageR = new Image(inputR);
	        ImageView rView = new ImageView(imageR);
	        rView.setFitHeight(30);
	        rView.setFitWidth(34);
	        rView.setLayoutX(712);
	        rView.setLayoutY(7);
	        root.getChildren().add(rView);
	        rView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
	            try {
					newUser();
				} catch (Exception e) {
					e.printStackTrace();
				}
	        });
	        
        }
        
        root.getChildren().addAll(hi, noteView, userView, logoutView);
        
        Scene scene = new Scene(root, 800, 600);
        diffStage.setScene(scene);
        diffStage.show();     
	}
}
