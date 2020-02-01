package view;

import java.util.StringTokenizer;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import bean.UserBean;
import controller.GraphicController;
import controller.LoginManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Registration {
	@FXML
	public Button createAccount;
	@FXML
	public TextField usernameField;
	@FXML
	public TextField emailField;
	@FXML
	public PasswordField passwordField;
	@FXML
	public Text goToLogin1;
	
	LoginManager lm= LoginManager.getInstance();
	
	UserBean ub = new UserBean();
	
	
	//Method to create a new account
	public void createAccount(ActionEvent actionEvent) throws Exception {
		
        String username = usernameField.getText();
        String password = passwordField.getText();
        String email = emailField.getText();


        boolean result = true;
        // Create the Pattern using the regex
           Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
    
           // Match the given string with the pattern
           Matcher m = p.matcher(email);
    
           // check whether match is found
           boolean matchFound = m.matches();
    
           StringTokenizer st = new StringTokenizer(email, ".");
           String lastToken = null;
           while (st.hasMoreTokens()) {
               lastToken = st.nextToken();
           }
    
           // validate the country code
           if (matchFound && lastToken.length() >= 2
                   && email.length() - 1 != lastToken.length()) {
    
              result = true;
           } else {
               result = false;
           }
        
        if (username.equals("") ||password.equals("") || email.equals("")){
        	Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning");
			alert.setHeaderText("Attention!");
			alert.setContentText("Enter username, password and/or email.");
			alert.showAndWait();
        }
        
        if (username.length()>=6 && password.length()>=6){
        	if(result==true) {
			 if (LoginManager.register(username, password, email)){
	                GraphicController graphicController = new GraphicController();
	                ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
	                graphicController.start(null);
	                //go to main  page to do login
	                Alert alert = new Alert(AlertType.CONFIRMATION);
	    			alert.setTitle("Success");
	    			alert.setHeaderText("Success!");
	    			alert.setContentText("Registration successful");
	    			alert.showAndWait();  
	            } else {
	                Alert alert = new Alert(AlertType.WARNING);
	    			alert.setTitle("Warning");
	    			alert.setHeaderText("Attention!");
	    			alert.setContentText("Registration failed.\n User already registered");
	    			alert.showAndWait();
	            }
			 }else {
	                Alert alert = new Alert(AlertType.WARNING);
	    			alert.setTitle("Warning");
	    			alert.setHeaderText("Attention!");
	    			alert.setContentText("Email not valid");
	    			alert.showAndWait();
	    			emailField.setText("");  
        	}
        } else {
        	  Alert alert = new Alert(AlertType.WARNING);
  			alert.setTitle("Warning");
  			alert.setHeaderText("Attention!");
  			alert.setContentText("Username and password must be \nat least six characters");
  			alert.showAndWait();
  			usernameField.setText("");
  	        passwordField.setText("");  			
        }         
    }
	
	//go back to login page
	public void goToLogin(MouseEvent mouseEvent) throws Exception{
		((Node)(mouseEvent.getSource())).getScene().getWindow().hide();
    	GraphicController graphicController = new GraphicController();
        graphicController.start(null);	
	}
	
	public void start() throws Exception {
		Stage regStage = new Stage();
		FXMLLoader loader = new FXMLLoader (Registration.class.getResource("registration.fxml"));
		AnchorPane root = loader.load();
		regStage.getIcons().add(new Image("img/icon.png"));
        regStage.setTitle("WhatEat?!" );
        regStage.setResizable(false);
        Scene scene = new Scene(root, 800, 600);
        regStage.setScene(scene);
        regStage.show();
	}
}
