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
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;

public class MealPage {
	
	@FXML
	public Text txtStarters;
	@FXML
	public Text txtMain;
	@FXML
	public Text txtSecond;
	@FXML
	public Text txtSideDishes;
	@FXML
	public Text txtBreakfast;
	@FXML
	public Text txtDessert;
	
	LoginManager lm =LoginManager.getInstance();
	UserBean ub= lm.getUser();
		
	RecipeBean rb = new RecipeBean();
	RecipeManager rm= RecipeManager.getInstance();
	
	public void chooseStarters(MouseEvent starters) throws Exception{
		rb.setRecBeanCategory("starters");
		rm.setRecipe(rb);
		showDiffPage(starters);
	}
		
	public void chooseMainCourses(MouseEvent mainCourses) throws Exception {
		rb.setRecBeanCategory("main course");
		rm.setRecipe(rb);
		showDiffPage(mainCourses);		
	}
	
	public void choosSecondCourses(MouseEvent secCourses) throws Exception {
		rb.setRecBeanCategory("second course");
		rm.setRecipe(rb);
		showDiffPage(secCourses);	
	}

	public void chooseSide(MouseEvent side) throws Exception {
		rb.setRecBeanCategory("side");
		rm.setRecipe(rb);
		showDiffPage(side);
	}
	
	public void chooseBreakfast(MouseEvent breakfast) throws Exception {
		rb.setRecBeanCategory("breakfast");
		rm.setRecipe(rb);
		showDiffPage(breakfast);
	}
	
	public void chooseDessert(MouseEvent dessert) throws Exception {
		rb.setRecBeanCategory("dessert");
		rm.setRecipe(rb);
		showDiffPage(dessert);
  	}
	
	public void showDiffPage(MouseEvent e) throws Exception {
		
		((Node)(e.getSource())).getScene().getWindow().hide();
    	GraphicController graphicController = new GraphicController();
        graphicController.difficultPage();
	}
	
	public void myProfile() throws Exception {
    	GraphicController graphicController = new GraphicController();
        graphicController.profilePage();
	}
	
	public void back(ActionEvent e) throws Exception {
		((Node)(e.getSource())).getScene().getWindow().hide();
    	GraphicController graphicController = new GraphicController();
        graphicController.homePage();
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
	
	private void newUser() throws Exception {
		GraphicController graphicController = new GraphicController();
        graphicController.registrationPage();
	}
	
	public void start() throws Exception {
		
		Stage mealStage = new Stage();
		FXMLLoader loader = new FXMLLoader(MealPage.class.getResource("meal.fxml"));
		AnchorPane root = loader.load();
		mealStage.getIcons().add(new Image("img/icon.png"));
        mealStage.setTitle("WhatEat?!" );
        mealStage.setResizable(false);

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
        mealStage.setScene(scene);
        mealStage.show();
	
		
	}

	
}
