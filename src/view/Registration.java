package view;

import java.io.IOException;
import java.util.StringTokenizer;
import java.util.logging.Logger;
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
	public Button account;
	@FXML
	public TextField usernameField;
	@FXML
	public TextField emailField;
	@FXML
	public PasswordField passwordField;
	@FXML
	public Text goToLogin1;
	
	
	
	private static final String WARNING = "Warning";
	private static final String ATTENTION = "Attention!";
	
	
	LoginManager lm= LoginManager.getInstance();
	
	static Logger logger = Logger.getAnonymousLogger();
	 private static final String CONTEXT = "context";
	
	UserBean ub = new UserBean();
	
	GraphicController graphicController = new GraphicController();
	 String lastToken = null;
	
	public void closeRegPage(ActionEvent actionEvent)  {
		
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
		try {
			graphicController.start(null);
		} catch (Exception e) {
		
			logger.log(null, CONTEXT,e);
		}
	    
		}
	
	public boolean checkMail(String mail) {
		 boolean result = true;
	        // Create the Pattern using the regex
	           Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
	    
	           // Match the given string with the pattern
	           Matcher m = p.matcher(mail);
	    
	           // check whether match is found
	           boolean matchFound = m.matches();
	    
	           StringTokenizer st = new StringTokenizer(mail, ".");
	          
	           while (st.hasMoreTokens()) {
	               lastToken = st.nextToken();
	           }
	    
	           // validate the country code
	           if (matchFound && lastToken.length() >= 2
	                   && mail.length() - 1 != lastToken.length()) {
	    
	              result = true;
	           } else {
	               result = false;
	           }
	           return result;
		
	}
	
	
	//Method to create a new account
	public void registration(ActionEvent actionEvent){
		
        String username = usernameField.getText();
        String password = passwordField.getText();
        String email = emailField.getText();


        boolean resultM = checkMail(email);
       
        
        if (username.equals("") ||password.equals("") || email.equals("")){
        	Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(WARNING);
			alert.setHeaderText(ATTENTION);
			alert.setContentText("Enter username, password and/or email.");
			alert.showAndWait();
        }
        
        if (username.length()>=6 && password.length()>=6){
        	if(resultM) {
			 if (LoginManager.register(username, password, email)){
	              
				 	closeRegPage(actionEvent);
	                //go to main  page to do login
	                Alert alertRegistration = new Alert(AlertType.CONFIRMATION);
	    			alertRegistration.setTitle("Success");
	    			alertRegistration.setHeaderText("Success!");
	    			alertRegistration.setContentText("Registration successful");
	    			alertRegistration.showAndWait();  
	            } else {
	                Alert alert = new Alert(AlertType.WARNING);
	    			alert.setTitle(WARNING);
	    			alert.setHeaderText(ATTENTION);
	    			alert.setContentText("Registration failed.\n User already registered");
	    			alert.showAndWait();
	            }
			 }else {
	                Alert alert = new Alert(AlertType.WARNING);
	    			alert.setTitle(WARNING);
	    			alert.setHeaderText(ATTENTION);
	    			alert.setContentText("Email not valid");
	    			alert.showAndWait();
	    			emailField.setText("");  
        	}
        } else {
        	  Alert alert = new Alert(AlertType.WARNING);
  			alert.setTitle(WARNING);
  			alert.setHeaderText(ATTENTION);
  			alert.setContentText("Username and password must be \nat least six characters");
  			alert.showAndWait();
  			usernameField.setText("");
  	        passwordField.setText("");  			
        }         
    }
	
	//go back to login page
	public void goToLogin(MouseEvent mouseEvent){
		((Node)(mouseEvent.getSource())).getScene().getWindow().hide();
        try {
			graphicController.start(null);
		} catch (Exception e) {
			logger.log(null, CONTEXT,e);
		}	
	}
	
	public void start() {
		Stage regStage = new Stage();
		FXMLLoader loader = new FXMLLoader (Registration.class.getResource("registration.fxml"));
		AnchorPane root;
		try {
			root = loader.load();
			regStage.getIcons().add(new Image("img/icon.png"));
	        regStage.setTitle("WhatEat?!" );
	        regStage.setResizable(false);
	        Scene scene = new Scene(root, 800, 600);
	        regStage.setScene(scene);
	        regStage.show();
		} catch (IOException e) {
			logger.log(null, CONTEXT,e);
		}
	
	}
}
