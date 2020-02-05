package view;

import java.io.FileInputStream;
import java.io.IOException;
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
	
	public Label reviewLabel = new Label();
	public Button likebtn = new Button();	
	
	static Logger logger = Logger.getAnonymousLogger();
	private static final String CONTEXT = "context";
	
	private static final String SYSTEM = "System";
	
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
			e.printStackTrace();
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
				 check=true;
			 }
		
	}
		return check;
		

         
}

	public void startRecPage() {
			
		Stage ingStage = new Stage();
		FXMLLoader loader = new FXMLLoader (RecipePage.class.getResource("recipePage.fxml"));
		AnchorPane root;
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
	        Image imageP = new Image(inputP);
	        ImageView userViewRecipePage = new ImageView(imageP);
	        userViewRecipePage.setLayoutX(1);
	        userViewRecipePage.setLayoutY(2);
	        userViewRecipePage.setFitHeight(30);
	        userViewRecipePage.setFitWidth(34);
	        userViewRecipePage.setLayoutX(712);
	        userViewRecipePage.setLayoutY(7);
	        userViewRecipePage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
	            try {
					myProfile();
				} catch (Exception e) {
					e.printStackTrace();
				}
	        });
	        
	        FileInputStream inputN = new FileInputStream("src\\img\\icons8-libretto-a-spirale-legato-80.png");
	        Image imageN = new Image(inputN);
	        ImageView noteViewRecipePage = new ImageView(imageN);
	        noteViewRecipePage.setLayoutX(1);
	        noteViewRecipePage.setLayoutY(2);
	        noteViewRecipePage.setFitHeight(39);
	        noteViewRecipePage.setFitWidth(38);
	        noteViewRecipePage.setLayoutX(490);
	        noteViewRecipePage.setLayoutY(3);
	        noteViewRecipePage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
	            try {
					createNoteRecipePage();
				} catch (Exception e) {
					e.printStackTrace();
				}
	        });
	                
	        FileInputStream inputL = new FileInputStream("src\\img\\icons8-uscita-100.png");
	        Image imageL = new Image(inputL);
	        ImageView logoutViewRecipePage = new ImageView(imageL);
	        logoutViewRecipePage.setFitHeight(30);
	        logoutViewRecipePage.setFitWidth(28);
	        logoutViewRecipePage.setLayoutX(758);
	        logoutViewRecipePage.setLayoutY(7);
	        logoutViewRecipePage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
	            try {
					logOutRecipePagde(event);
				} catch (Exception e) {
					e.printStackTrace();
				}
	        });
	        
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
							 Alert alert = new Alert(AlertType.CONFIRMATION);
							 alert.setTitle("Success");
							 alert.setHeaderText("Success!");
							 alert.setContentText("Thanks for your opinion");
							 alert.showAndWait();
							 likebtn.setVisible(false);
							 reviewLabel.setText("You like it!");
							 
						 }
							
							
					
					
				try {
					event.wait(0);
				} catch (InterruptedException e) {
					logger.log(null, CONTEXT,e);
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
			e1.printStackTrace();
		}
	}
		
		
	
	public void saveRecipe(){
		String name=lm.getUser().getUsername();
		if(name=="Chef") {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Hi Chef");
			alert.setHeaderText("Sorry!");
			alert.setContentText("For save this and other recipes as favourite, create an account!");
			alert.showAndWait();
			
			GraphicController graphicController = new GraphicController();
            graphicController.registrationPage();
		}
		else if((name!="Chef" && upm.saveRecipe(rb.getRecBeanTitle(), rb.getRecBeanPreparation(), rb.getRecBeanDifficulty(), rb.getRecBeanCategory(), rb.getrecBeanTime(), rb.getRecBeanNecessary(), ub.getUsername()))==true) {
		
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
