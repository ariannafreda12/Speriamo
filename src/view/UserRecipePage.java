package view;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

import bean.RecipeBean;
import bean.UserBean;
import bean.UserRecipeBean;
import controller.GraphicController;
import controller.LoginManager;
import controller.RecipeManager;
import controller.UserProfileManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class UserRecipePage {
	
	LoginManager lm = LoginManager.getInstance();
	UserBean u = lm.getUser();
	
	RecipeManager rm= RecipeManager.getInstance();
	RecipeBean rb=rm.getRecipe();

	UserProfileManager upm= UserProfileManager.getInstance();
	UserRecipeBean upb=upm.getUserProfile();
	
	GraphicController graphicController = new GraphicController();
	
	private Button deletebtn = new Button();
	@FXML
	private Button backButton;
	
	static Logger logger = Logger.getAnonymousLogger();
	 private static final String CONTEXT = "context";
	
	private static final String SYSTEM = "System";
	
	public void start() {
		
		Stage ingStage = new Stage();
		FXMLLoader loader = new FXMLLoader (RecipePage.class.getResource("userProfilePage.fxml"));
		AnchorPane rootUserRecipe;
		try {
			rootUserRecipe = loader.load();
			ingStage.getIcons().add(new Image("img/icon.png"));
	        ingStage.setTitle("WhatEat?!" );
	        ingStage.setResizable(false);
	        Scene scene = new Scene(rootUserRecipe, 800, 600);
	        
	        //create new label for insert title
	        final Label titleLabel = new Label();
	        titleLabel.setText(upb.getURecBeanTitle());
	        titleLabel.setLayoutY(84);
	        titleLabel.setLayoutX(53);
	        titleLabel.setPrefWidth(726);
	        titleLabel.setAlignment(Pos.CENTER);
	        titleLabel.setFont(Font.font(SYSTEM,FontWeight.BOLD, FontPosture.ITALIC, 36));
	        
	        
	        //create new label for insert difficulty level
	        final Label diffLabel = new Label();
	        diffLabel.setText(upb.getURecBeanDifficulty());
	        diffLabel.setLayoutY(150);
	        diffLabel.setLayoutX(298);
	        diffLabel.setFont(Font.font(SYSTEM, FontPosture.ITALIC, 22));
	        
	        //create new label for insert time
	        final Label timeLabel = new Label();
	        timeLabel.setText(upb.getURecBeanTime());
	        timeLabel.setLayoutY(150);
	        timeLabel.setLayoutX(496);
	        timeLabel.setFont(Font.font(SYSTEM, FontPosture.ITALIC, 22));
	        
	        //create new label for insert category
	        final Label catLabel = new Label();
	        catLabel.setText(upb.getURecBeanCategory());
	        catLabel.setLayoutY(150);
	        catLabel.setLayoutX(637);
	        catLabel.setFont(Font.font(SYSTEM, FontPosture.ITALIC, 22));
	        
	        
	        TextArea necLabel = new TextArea(upb.getURecBeanNecessary());
	        necLabel.setWrapText(true);
	        necLabel.setFont(Font.font(SYSTEM, FontPosture.ITALIC, 18));
	        necLabel.setLayoutX(130);
	        necLabel.setLayoutY(244);
	        necLabel.setPrefSize(282, 256);
	        necLabel.setEditable(false);
	     
	        TextArea prepLabel = new TextArea(upb.getURecBeanPreparation());
	        prepLabel.setLayoutX(445);
	        prepLabel.setLayoutY(244);
	        prepLabel.setPrefSize(320, 256);
	        prepLabel.setWrapText(true);
	        prepLabel.setFont(Font.font(SYSTEM, FontPosture.ITALIC, 18));
	        prepLabel.setEditable(false);
	        
	        deletebtn.setText("Delete");
	        deletebtn.setLayoutX(665);
	        deletebtn.setLayoutY(505);
	        deletebtn.setFont(Font.font(SYSTEM, FontWeight.BOLD, FontPosture.ITALIC,18));
	        
	        deletebtn.setOnAction(new EventHandler<ActionEvent>() {
	 			public void handle(ActionEvent event) {
	 				Alert dialogoAllerta = new Alert(AlertType.CONFIRMATION, "Are you sure you want to delete the recipe?");
	 				Optional<ButtonType> risposta = dialogoAllerta.showAndWait();
	 				
	 				if(risposta.isPresent() && risposta.get() == ButtonType.OK) {
	 					((Node)(event.getSource())).getScene().getWindow().hide();
	 					if ((upm.deleteRecipe(upb.getURecBeanTitle(), upb.getURecBeanUsername()))) {
	 	 					 
	 	 						try {
	 	 							graphicController.profilePage();
	 	 				        	Alert alertRecipe = new Alert(AlertType.CONFIRMATION);
	 	 	 						alertRecipe.setTitle("Success");
	 	 	 						alertRecipe.setHeaderText("Success!");
	 	 	 						alertRecipe.setContentText("Recipe deleted");
	 	 	 						alertRecipe.showAndWait();
	 								
	 							} catch (Exception e) {
	 								logger.log(null, CONTEXT,e);
	 							}	
	 	 				}else {
	 	 					Alert alertDeleteRecipe = new Alert(AlertType.INFORMATION);
	 							alertDeleteRecipe.setTitle("Information");
	 							alertDeleteRecipe.setHeaderText("Information!");
	 							alertDeleteRecipe.setContentText("Recipe not deleted succesfully");
	 							alertDeleteRecipe.showAndWait();
	 							((Node)(event.getSource())).getScene().getWindow().hide();
	 							}
	 					}
	 			}
	 		});
	        
	        rootUserRecipe.getChildren().add(deletebtn);
	        rootUserRecipe.getChildren().addAll(titleLabel);
	        rootUserRecipe.getChildren().addAll(catLabel);
	        rootUserRecipe.getChildren().addAll(diffLabel);
	        rootUserRecipe.getChildren().addAll(timeLabel);
	        rootUserRecipe.getChildren().addAll(necLabel);
	        rootUserRecipe.getChildren().addAll(prepLabel);
	        
	        ingStage.setScene(scene);
	        ingStage.show();
	             
		} catch (IOException e1) {
			logger.log(null, CONTEXT,e1);
		}
		
	}
	
	//BACK BUTTON
	public void back(ActionEvent e) {
		((Node)(e.getSource())).getScene().getWindow().hide();
        graphicController.profilePage();
	}
	


}
