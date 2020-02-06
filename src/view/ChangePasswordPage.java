package view;

import java.io.IOException;
import java.util.logging.Logger;

import bean.UserBean;
import controller.GraphicController;
import controller.LoginManager;
import exception.EmptyFieldexception;
import exception.FieldsNotMatchingException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ChangePasswordPage {
	
	@FXML 
	private TextField tx0;
	
	@FXML 
	private TextField tx1;
	
	@FXML 
	private PasswordField tx2;
	
	private static final String ATTENTION = "Attention!";
	
	static Logger logger = Logger.getAnonymousLogger();
	 private static final String CONTEXT = "context";
	 
	public void closeCPassPage(ActionEvent actionEvent)  {
		GraphicController graphicController = new GraphicController();
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
		try {
			graphicController.start(null);
		} catch (Exception e) {
		
			logger.log(null, CONTEXT,e);
		}
	    
		}
	
	public void changePassword(ActionEvent actionEvent) {
		
		LoginManager dbm = LoginManager.getInstance();
		boolean pChanged;
		try {
			if ((tx0.getText().isEmpty()) ||(tx1.getText().isEmpty()) || (tx2.getText().isEmpty())) {
            
				throw new EmptyFieldexception();
        }
			
			
			String user = tx0.getText().toString();
			String psw1 = tx1.getText().toString();
        
			String psw2 = tx2.getText().toString();
			UserBean ub = new UserBean();
            ub.setUsername(user);
            if (psw1.length()<6){
            	tx1.setText("");
            	tx2.setText("");
            	Alert alert = new Alert(AlertType.WARNING);
    			alert.setTitle("Warning");
    			alert.setHeaderText(ATTENTION);
    			alert.setContentText("Password must be \nat least six characters");
    			alert.showAndWait();
    			}
            
            if (ub.foundUser()) {
               if (!psw1.equals( psw2 )) {
            	   	throw new FieldsNotMatchingException();
            	   	}
               
               
               pChanged= dbm.changePassword(user, psw1);
               if(pChanged) {
            	   Alert alert = new Alert(AlertType.CONFIRMATION);
          			alert.setTitle("Success");
          			alert.setHeaderText("Success!");
          			alert.setContentText("Password changed successfully");
          			alert.showAndWait();
          			closeCPassPage(actionEvent);
            }
            else {
            	Alert alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Error");
    			alert.setHeaderText("User not found");
    			alert.setContentText("Incorrect username");
    			alert.showAndWait();
            	

            }
            }
		}
        	
        
		catch (EmptyFieldexception e) {
            Alert alert = new Alert( Alert.AlertType.WARNING );
            alert.setTitle( "" );
            alert.setHeaderText(ATTENTION);
            alert.setContentText( "To change Password, enter your username and the new password" );
            alert.showAndWait();
        } catch (FieldsNotMatchingException e) {
        	tx1.setText("");
        	tx2.setText("");
            Alert alert = new Alert( Alert.AlertType.ERROR );
            alert.setTitle( "" );
            alert.setHeaderText(ATTENTION);
            alert.setContentText( "The two password fields do not match!" );
            alert.showAndWait();
        }
	      
	}
	
	public void back(ActionEvent e) {
		((Node)(e.getSource())).getScene().getWindow().hide();
    	GraphicController graphicController = new GraphicController();
        try {
			graphicController.start(null);
		} catch (Exception e1) {
			logger.log(null, CONTEXT,e1);
		}
	}

	public void start() {
		Stage passStage = new Stage();
		FXMLLoader loader = new FXMLLoader(ChangePasswordPage.class.getResource("changePasswordPage.fxml"));
		AnchorPane root;
		try {
			root = loader.load();
			passStage.getIcons().add(new Image("img/icon.png"));
	        passStage.setTitle("WhatEat?!" );
	        passStage.setResizable(false);
	        Scene scene = new Scene(root, 800, 572);
	        passStage.setScene(scene);
	        passStage.show();
		} catch (IOException e) {
			logger.log(null, CONTEXT,e);
		}
		
		
		
	}
		
}

