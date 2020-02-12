package view;


import java.io.IOException;
import java.util.logging.Logger;

import bean.UserBean;
import controller.GraphicController;
import controller.LoginManager;
import exception.EmptyFieldexception;
import exception.UsernameNotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;

public class Login {
		@FXML
	    public Button btnLogin;
		@FXML
		public Button btnVisitor;
		@FXML
	    public PasswordField passField;
		@FXML
	    public TextField userField;
		@FXML
		public Text registrationText;
		
		LoginManager lm = LoginManager.getInstance();
		
		static Logger logger = Logger.getAnonymousLogger();
		private static final String CONTEXT = "context";
		
	    //login method
	    public void loginMethod(ActionEvent actionEvent)  {
	    	
	    	String user = userField.getText();
			String pass = passField.getText();
			
			
			
			try {
				if (user.isEmpty() || pass.isEmpty()) {
					throw new EmptyFieldexception();
				}else {
						
						UserBean ub = new UserBean();
			            ub.setPassword( pass );
			            ub.setUsername(user);
			            
			           
			            
			            if (ub.validate()) 
			            {
			            	ub.setEmail(lm.login(user,pass).getEmail());
			            	lm.setUser(ub);
			            	((Node)(actionEvent.getSource())).getScene().getWindow().hide();
			            	GraphicController graphicController = new GraphicController();
			            	graphicController.homePage();
			            }
			            else {
			            	throw new UsernameNotFoundException();
			            }
					}
		
				 
			}catch(EmptyFieldexception e) {
				
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning");
				alert.setHeaderText("Attention!");
				alert.setContentText("To log in, enter your username and password.");
				alert.showAndWait();
				
			} catch(UsernameNotFoundException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("User not found");
				alert.setContentText("Incorrect username and/or password.");
				alert.showAndWait();
			} 
	    }
	  
	    public void forgotPassword(MouseEvent mouseEvent){
	    	
	    	((Node)(mouseEvent.getSource())).getScene().getWindow().hide();
        	GraphicController graphicController = new GraphicController();
            graphicController.changePasswordPage();
	    }
	    
	    //forRegistrationPage
	    public void goToRegistrationPage(MouseEvent mouseEvent){
	    	
	    	((Node)(mouseEvent.getSource())).getScene().getWindow().hide();
        	GraphicController graphicController = new GraphicController();
            graphicController.registrationPage();
	    	         
	    }
	   
	    public void goTo(MouseEvent mouseEvent){
	    	
	    	UserBean ub = new UserBean();
            ub.setUsername("Chef");
            ub.setPassword("xxx");
            ub.setEmail("chef@user.it");
            lm.setUser(ub);
	    	((Node)(mouseEvent.getSource())).getScene().getWindow().hide();
        	GraphicController graphicController = new GraphicController();
            graphicController.homePage();
	    	         
	    }

	    public void start(){
	    	Stage loginStage = new Stage();
	    	FXMLLoader loader = new FXMLLoader (Login.class.getResource("login.fxml"));
			AnchorPane root;
			try {
				root = loader.load();
				loginStage.getIcons().add(new Image("img/icon.png"));
	        	loginStage.setTitle("WhatEat?!" );
	        	loginStage.setResizable(false);
	        	Scene scene = new Scene(root, 800, 600);
	        	loginStage.setScene(scene);
	        	loginStage.show();
			} catch (IOException e) {
				logger.log(null, CONTEXT,e);
			}
			
	}

}
