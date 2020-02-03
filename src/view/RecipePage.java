package view;

import java.io.FileInputStream;
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
	
	public void myProfile() throws Exception {
    	GraphicController graphicController = new GraphicController();
        graphicController.profilePage();
	}
	
	private void newUser() throws Exception {
		GraphicController graphicController = new GraphicController();
        graphicController.registrationPage();
	}
	
	public void logOut(MouseEvent me) throws Exception {
		LoginManager controller = new LoginManager();
        controller.resetUser();
        ((Node)(me.getSource())).getScene().getWindow().hide();
        GraphicController graphicController = new GraphicController();
        Stage stage = null;
        graphicController.start(stage);
	}
	
	public void createNote() throws Exception {
    	GraphicController graphicController = new GraphicController();
        graphicController.notePage();
	}

	public void start() throws Exception {
			
		Stage ingStage = new Stage();
		FXMLLoader loader = new FXMLLoader (RecipePage.class.getResource("recipePage.fxml"));
		AnchorPane root = loader.load();
		ingStage.getIcons().add(new Image("img/icon.png"));
        ingStage.setTitle("WhatEat?!" );
        ingStage.setResizable(false);
        Scene scene = new Scene(root, 800, 600);

        final Label hi= new Label();
        hi.setText("Hi " + ub.getUsername());
        hi.setLayoutY(10);
        hi.setLayoutX(600);
        hi.setFont(Font.font("System", FontPosture.ITALIC, 18));
        
        FileInputStream inputP = new FileInputStream("src\\img\\icons8-nome-100.png");
        Image imageP = new Image(inputP);
        ImageView userView = new ImageView(imageP);
        userView.setLayoutX(1);
        userView.setLayoutY(2);
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
        noteView.setLayoutX(1);
        noteView.setLayoutY(2);
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
        
        //create new label for insert title
        final Label titleLabel = new Label();
        titleLabel.setText(rb.getRecBeanTitle());
        titleLabel.setLayoutY(84);
        titleLabel.setLayoutX(53);
        titleLabel.setPrefWidth(726);
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setFont(Font.font("System",FontWeight.BOLD, FontPosture.ITALIC, 32));
        
        //create new label for insert difficulty level
        final Label diffLabel = new Label();
        diffLabel.setText(rb.getDifficulty());
        diffLabel.setLayoutY(150);
        diffLabel.setLayoutX(298);
        diffLabel.setFont(Font.font("System", FontPosture.ITALIC, 20));
        
        //create new label for insert time
        final Label timeLabel = new Label();
        timeLabel.setText(rb.getTime());
        timeLabel.setLayoutY(150);
        timeLabel.setLayoutX(496);
        timeLabel.setFont(Font.font("System", FontPosture.ITALIC, 20));
        
        //create new label for insert category
        final Label catLabel = new Label();
        catLabel.setText(rb.getCategory());
        catLabel.setLayoutY(150);
        catLabel.setLayoutX(637);
        catLabel.setFont(Font.font("System", FontPosture.ITALIC, 20));
        
 
        TextArea necLabel = new TextArea(rb.getNecessary());
        necLabel.setWrapText(true);
        necLabel.setFont(Font.font("System", FontPosture.ITALIC, 18));
        necLabel.setLayoutX(130);
        necLabel.setLayoutY(244);
        necLabel.setPrefSize(282, 256);
        necLabel.setEditable(false);
     
        TextArea prepLabel = new TextArea(rb.getPreparation());
        prepLabel.setLayoutX(445);
        prepLabel.setLayoutY(244);
        prepLabel.setPrefSize(320, 256);
        prepLabel.setWrapText(true);
        prepLabel.setFont(Font.font("System", FontPosture.ITALIC, 18));
        prepLabel.setEditable(false);
        
        reviewLabel.setText("Leave review");
    	reviewLabel.setFont(Font.font("System", FontWeight.BOLD, FontPosture.ITALIC, 20));
    	reviewLabel.setLayoutX(533);
    	reviewLabel.setLayoutY(511);
    	
    	likebtn.setText("Like");
    	likebtn.setLayoutX(665);
    	likebtn.setLayoutY(505);
    	likebtn.setFont(Font.font("System", FontWeight.BOLD, FontPosture.ITALIC,18));
    	
    	likebtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if ((rm.reviewRecipe(rb.getRecBeanTitle(), rb.getReview()))==true) {
					if ((rm.addReviewRecipe(ub.getUsername(), rb.getRecBeanTitle()))==true) {
					
					 Alert alert = new Alert(AlertType.CONFIRMATION);
						alert.setTitle("Success");
						alert.setHeaderText("Success!");
						alert.setContentText("Thanks for your opinion");
						alert.showAndWait();
						likebtn.setVisible(false);
						reviewLabel.setText("You like it!");
						
				}
			
				}
			}
		});
    	
    	if ((rm.checkReviewRecipe(ub.getUsername(), rb.getRecBeanTitle()))==true) {
			likebtn.setVisible(false);
			reviewLabel.setText("You like it!");		
		}
    	
    	if (lm.getUser().getUsername()=="Chef"){
			noteView.setVisible(false);
			userView.setVisible(false);
			likebtn.setVisible(false);
			reviewLabel.setVisible(false);
        }
        
        root.getChildren().addAll(reviewLabel,likebtn);
        root.getChildren().addAll(titleLabel, catLabel, diffLabel, timeLabel, necLabel, prepLabel);
        ingStage.setScene(scene);
        ingStage.show();
             
	}
	
	public void saveRecipe(MouseEvent e) throws Exception {
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
		else if((name!="Chef" && upm.saveRecipe(rb.getRecBeanTitle(), rb.getPreparation(), rb.getDifficulty(), rb.getCategory(), rb.getTime(), rb.getNecessary(), ub.getUsername()))==true) {
		
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
