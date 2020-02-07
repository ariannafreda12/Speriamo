package view;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import bean.RecipeBean;
import bean.UserBean;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;



public class RecipePage{
	
	@FXML
	public ImageView logOutimg;
	
	RecipeManager rm= RecipeManager.getInstance();
	RecipeBean rb= rm.getRecipe();
	
	LoginManager lm = LoginManager.getInstance();
	UserBean ub = lm.getUser();
	
	UserProfileManager upm= UserProfileManager.getInstance();
	
	private Label reviewLabel = new Label();
	private Button likebtn = new Button();	
	
	static Logger logger = Logger.getAnonymousLogger();
	private static final String CONTEXT= "context";
	
	
	private static final String SYSTEM = "System";
	
	Stage ingStage = new Stage();
	FXMLLoader loader = new FXMLLoader (RecipePage.class.getResource("recipePage.fxml"));
	AnchorPane root;
	
	public void myProfile(){
    	GraphicController graphicController = new GraphicController();
        graphicController.profilePage();
	}
	
	private void newUserRecipePage(){
		GraphicController graphicController = new GraphicController();
        graphicController.registrationPage();
	}
	
	public void logOutRecipePagde(MouseEvent me){
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
	
	public void createNoteRecipePage() {
    	GraphicController graphicController = new GraphicController();
        graphicController.notePage();
	}
	
	public boolean reviewStudy(String title,String username,int rev) {
		
		boolean check= false;
		if ((rm.reviewRecipe(title, rev))) {
			check=rm.addReviewRecipe(username, title);
			 if(check) {
				 Alert alert = new Alert(AlertType.CONFIRMATION);
				 alert.setTitle("Success");
				 alert.setHeaderText("Success!");
				 alert.setContentText("Thanks for your opinion");
				 alert.showAndWait();
				 check=true;
			 }
		
	}
		return check; 
}
	
	public ImageView inputProfileR(FileInputStream inR) {
		 Image imageP = new Image(inR);
	     ImageView userR = new ImageView(imageP);
	     userR.setFitHeight(30);
	     userR.setFitWidth(34);
	     userR.setLayoutX(712);
	     userR.setLayoutY(7);
	     userR.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
	            try {
					myProfile();
				} catch (Exception e) {
					e.printStackTrace();
				}
	        });
	     return userR;
		
	}
	
	public ImageView inputNoteR(FileInputStream inpNR) {
		 Image imageN = new Image(inpNR);
	     ImageView noteVR = new ImageView(imageN);
	     noteVR.setFitHeight(39);
	     noteVR.setFitWidth(38);
	     noteVR.setLayoutX(490);
	     noteVR.setLayoutY(3);
	     noteVR.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
	            try {
					createNoteRecipePage();
				} catch (Exception e) {
					e.printStackTrace();
				}
	        });
	     return noteVR;
	}
	public ImageView inputLogOutR(FileInputStream inpLR) {
		 Image imageL = new Image(inpLR);
	     ImageView logoutR = new ImageView(imageL);
	     logoutR.setFitHeight(30);
	     logoutR.setFitWidth(28);
	     logoutR.setLayoutX(758);
	     logoutR.setLayoutY(7);
	     logoutR.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
	            try {
	            	logOutRecipePagde(event);
				} catch (Exception e) {
					e.printStackTrace();
				}
	        });
	     return logoutR;
	}

	public void startRecPage() {
			
	
		try {
			root = loader.load();
			ingStage.getIcons().add(new Image("img/icon.png"));
	        ingStage.setTitle("WhatEat?!" );
	        ingStage.setResizable(false);
	        Scene scene = new Scene(root, 800, 600);

	        final Label hi= new Label();
	        hi.setText("Hi " + ub.getUsername());
	        hi.setLayoutY(10);
	        hi.setLayoutX(600);
	        hi.setFont(Font.font(SYSTEM, FontPosture.ITALIC, 18));
	        
	        FileInputStream inputP = new FileInputStream("src\\img\\icons8-nome-100.png");
	        ImageView userViewRecipePage = inputProfileR(inputP);
	       
	        FileInputStream inputN = new FileInputStream("src\\img\\icons8-libretto-a-spirale-legato-80.png");
	        ImageView noteViewRecipePage = inputNoteR(inputN);
	       
	        
	        FileInputStream inputL = new FileInputStream("src\\img\\icons8-uscita-100.png");
	        ImageView logoutViewRecipePage =inputLogOutR(inputL);
	                
	        if (lm.getUser().getUsername()=="Chef"){
				noteViewRecipePage.setVisible(false);
				userViewRecipePage.setVisible(false);
				FileInputStream inputR = new FileInputStream("src\\img\\icons8-nome-100.png");
		        Image imageR = new Image(inputR);
		        ImageView rViewRecipePage = new ImageView(imageR);
		        rViewRecipePage.setFitHeight(30);
		        rViewRecipePage.setFitWidth(34);
		        rViewRecipePage.setLayoutX(712);
		        rViewRecipePage.setLayoutY(7);
		        root.getChildren().add(rViewRecipePage);
		        rViewRecipePage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
		            try {
						newUserRecipePage();
					} catch (Exception e) {
						e.printStackTrace();
					}
		        });
		        
	        }
	        
	        root.getChildren().addAll(hi, noteViewRecipePage, userViewRecipePage, logoutViewRecipePage);
	        
	        //create new label for insert title
	        final Label titleLabel = new Label();
	        titleLabel.setText(rb.getRecBeanTitle());
	        titleLabel.setLayoutY(84);
	        titleLabel.setLayoutX(53);
	        titleLabel.setPrefWidth(726);
	        titleLabel.setAlignment(Pos.CENTER);
	        titleLabel.setFont(Font.font(SYSTEM,FontWeight.BOLD, FontPosture.ITALIC, 32));
	        
	        //create new label for insert difficulty level
	        final Label diffLabel = new Label();
	        diffLabel.setText(rb.getRecBeanDifficulty());
	        diffLabel.setLayoutY(150);
	        diffLabel.setLayoutX(298);
	        diffLabel.setFont(Font.font(SYSTEM, FontPosture.ITALIC, 20));
	        
	        //create new label for insert time
	        final Label timeLabel = new Label();
	        timeLabel.setText(rb.getrecBeanTime());
	        timeLabel.setLayoutY(150);
	        timeLabel.setLayoutX(496);
	        timeLabel.setFont(Font.font(SYSTEM, FontPosture.ITALIC, 20));
	        
	        //create new label for insert category
	        final Label catLabel = new Label();
	        catLabel.setText(rb.getRecBeanCategory());
	        catLabel.setLayoutY(150);
	        catLabel.setLayoutX(637);
	        catLabel.setFont(Font.font(SYSTEM, FontPosture.ITALIC, 20));
	        
	 
	        TextArea necLabel = new TextArea(rb.getRecBeanNecessary());
	        necLabel.setWrapText(true);
	        necLabel.setFont(Font.font(SYSTEM, FontPosture.ITALIC, 18));
	        necLabel.setLayoutX(130);
	        necLabel.setLayoutY(244);
	        necLabel.setPrefSize(282, 256);
	        necLabel.setEditable(false);
	     
	        TextArea prepLabel = new TextArea(rb.getRecBeanPreparation());
	        prepLabel.setLayoutX(445);
	        prepLabel.setLayoutY(244);
	        prepLabel.setPrefSize(320, 256);
	        prepLabel.setWrapText(true);
	        prepLabel.setFont(Font.font(SYSTEM, FontPosture.ITALIC, 18));
	        prepLabel.setEditable(false);
	        
	        reviewLabel.setText("Leave review");
	    	reviewLabel.setFont(Font.font(SYSTEM, FontWeight.BOLD, FontPosture.ITALIC, 20));
	    	reviewLabel.setLayoutX(533);
	    	reviewLabel.setLayoutY(511);
	    	
	    	likebtn.setText("Like");
	    	likebtn.setLayoutX(665);
	    	likebtn.setLayoutY(505);
	    	likebtn.setFont(Font.font(SYSTEM, FontWeight.BOLD, FontPosture.ITALIC,18));
	    	likebtn.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					
						boolean checkRev=reviewStudy(rb.getRecBeanTitle(),ub.getUsername(),rb.getRecBeanReview());
						 if(checkRev) {
							 likebtn.setVisible(false);
							 reviewLabel.setText("You like it!");
							 
						 }
							
							
					
					
				try {
					event.wait(0);
				} catch (InterruptedException e) {
					logger.log(Level.WARNING, "Interrupted!", e);
				    Thread.currentThread().interrupt();
				  }
					}
				}
			);
	    	
	    	if ((rm.checkReviewRecipe(ub.getUsername(), rb.getRecBeanTitle()))) {
				likebtn.setVisible(false);
				reviewLabel.setText("You like it!");		
			}
	    	
	    	if (lm.getUser().getUsername()=="Chef"){
				noteViewRecipePage.setVisible(false);
				userViewRecipePage.setVisible(false);
				likebtn.setVisible(false);
				reviewLabel.setVisible(false);
	        }
	        
	        root.getChildren().addAll(reviewLabel,likebtn);
	        root.getChildren().addAll(titleLabel, catLabel, diffLabel, timeLabel, necLabel, prepLabel);
	        ingStage.setScene(scene);
	        ingStage.show();
		} catch (IOException e1) {
			logger.log(null, CONTEXT,e1);
		}
	}
		
		
	
	public void saveRecipe(){
		String name=lm.getUser().getUsername();
		if(name.equals("Chef")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Hi Chef");
			alert.setHeaderText("Sorry!");
			alert.setContentText("For save this and other recipes as favourite, create an account!");
			alert.showAndWait();
			
			GraphicController graphicController = new GraphicController();
            graphicController.registrationPage();
		}
		else if((!name.equals("Chef") && upm.saveRecipe(rb.getRecBeanTitle(), rb.getRecBeanPreparation(), rb.getRecBeanDifficulty(), rb.getRecBeanCategory(), rb.getrecBeanTime(), rb.getRecBeanNecessary(), ub.getUsername()))) {
		
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Success");
			alert.setHeaderText("Success!");
			alert.setContentText("Saved recipe successful");
			alert.showAndWait();
		}
		else  {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText("Information!");
			alert.setContentText("Recipe already saved");
			alert.showAndWait();
		}
	}
	
	
   
       
}
