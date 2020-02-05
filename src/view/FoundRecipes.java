package view;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import bean.RecipeBean;
import bean.UserBean;
import controller.GraphicController;
import controller.LoginManager;
import controller.RecipeManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;
import model.Recipe;

public class FoundRecipes {
	@FXML
	public Button backButton;
	@FXML
	public ImageView profileImg;
	@FXML
	public ImageView note;

	@FXML
    private static ObservableList<Recipe> list = FXCollections.observableArrayList();
	

	 Set<Recipe> recipe;	
	GraphicController gc = new GraphicController();
	
	//instance of recipe manager for get difficulty, category and ingredient list
    RecipeManager rm= RecipeManager.getInstance();
	RecipeBean rb=rm.getRecipe();
	
	static Logger logger = Logger.getAnonymousLogger();
	
	Recipe rc = new Recipe(null,null,null,null,null,null,0); 
	
	private static final String CONTEXT = "context";
	
	LoginManager lm =LoginManager.getInstance();
	UserBean ub= lm.getUser();
	
	public Set<Recipe> check(Set<Recipe> first) {
		Set<String> unique =  new HashSet<>();
		List<String> dupes = new ArrayList<>();
		Set<Recipe> secondUnique = new HashSet<>();
		
		for(Recipe a : first) {
			if(unique.contains(a.getTitle())) {
				dupes.add(a.getTitle());}
			else {
				unique.add(a.getTitle());
				secondUnique.add(a);
			}
				
		}
	
		return secondUnique;
	}
	
	public void myProfile(){
    	GraphicController graphicController = new GraphicController();
        graphicController.profilePage();
	}
	
	private void newUser(){
		GraphicController graphicController = new GraphicController();
        graphicController.registrationPage();
	}
	
	public void logOut(MouseEvent me){
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
	
	public void createNoteFoundRecPage(){
    	GraphicController graphicController = new GraphicController();
        graphicController.notePage();
	}
	
	public void startFoundRec(){
		Stage ingStageFoundRecPage = new Stage();
		FXMLLoader loader = new FXMLLoader (FoundRecipes.class.getResource("found_recipes.fxml"));
		AnchorPane root;
		try {
			root = loader.load();
			ingStageFoundRecPage.getIcons().add(new Image("img/icon.png"));
	        ingStageFoundRecPage.setTitle("WhatEat?!" );
	        ingStageFoundRecPage.setResizable(false);
	        Scene scene = new Scene(root, 800, 600);
	        ingStageFoundRecPage.setScene(scene);
	        
	        final Label hi= new Label();
	        hi.setText("Hi " + ub.getUsername());
	        hi.setLayoutY(10);
	        hi.setLayoutX(600);
	        hi.setFont(Font.font("System", FontPosture.ITALIC, 18));
	        
	        FileInputStream inputP = new FileInputStream("src\\img\\icons8-nome-100.png");
	        Image imageP = new Image(inputP);
	        ImageView userViewFoundRecPage = new ImageView(imageP);
	        userViewFoundRecPage.setFitHeight(30);
	        userViewFoundRecPage.setFitWidth(34);
	        userViewFoundRecPage.setLayoutX(712);
	        userViewFoundRecPage.setLayoutY(7);
	        userViewFoundRecPage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
	            try {
					myProfile();
				} catch (Exception e) {
					e.printStackTrace();
				}
	        });
	        
	        FileInputStream inputN = new FileInputStream("src\\img\\icons8-libretto-a-spirale-legato-80.png");
	        Image imageN = new Image(inputN);
	        ImageView noteViewFoundRecPage = new ImageView(imageN);
	        noteViewFoundRecPage.setFitHeight(39);
	        noteViewFoundRecPage.setFitWidth(38);
	        noteViewFoundRecPage.setLayoutX(490);
	        noteViewFoundRecPage.setLayoutY(3);
	        noteViewFoundRecPage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
	            try {
					createNoteFoundRecPage();
				} catch (Exception e) {
					e.printStackTrace();
				}
	        });
	        
	        FileInputStream inputL = new FileInputStream("src\\img\\icons8-uscita-100.png");
	        Image imageL = new Image(inputL);
	        ImageView logoutViewFoundRecPage = new ImageView(imageL);
	        logoutViewFoundRecPage.setFitHeight(30);
	        logoutViewFoundRecPage.setFitWidth(28);
	        logoutViewFoundRecPage.setLayoutX(758);
	        logoutViewFoundRecPage.setLayoutY(7);
	        logoutViewFoundRecPage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
	            try {
					logOut(event);
				} catch (Exception e) {
					e.printStackTrace();
				}
	        });
	        
	        if (lm.getUser().getUsername()=="Chef"){
				noteViewFoundRecPage.setVisible(false);
				userViewFoundRecPage.setVisible(false);
				FileInputStream inputR = new FileInputStream("src\\img\\icons8-nome-100.png");
		        Image imageR = new Image(inputR);
		        ImageView rViewFoundRecPage = new ImageView(imageR);
		        rViewFoundRecPage.setFitHeight(30);
		        rViewFoundRecPage.setFitWidth(34);
		        rViewFoundRecPage.setLayoutX(712);
		        rViewFoundRecPage.setLayoutY(7);
		        root.getChildren().add(rViewFoundRecPage);
		        rViewFoundRecPage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
		            try {
						newUser();
					} catch (Exception e) {
						e.printStackTrace();
					}
		        });
		        
	        }
	        
	        root.getChildren().addAll(hi, noteViewFoundRecPage, userViewFoundRecPage, logoutViewFoundRecPage);
	        
	        TableView<Recipe> tableView = new TableView<>();
	        
	        tableView.setPrefWidth(675);
	        TableColumn<Recipe,String> column1 = new TableColumn<> ("Title");
	        column1.setCellValueFactory(new PropertyValueFactory<>("title"));      
	        tableView.getColumns().add(column1);
	        
	        TableColumn<Recipe,String> column2 = new TableColumn<> ("Preparation");
	        column2.setCellValueFactory(new PropertyValueFactory<>("preparation"));      
	        tableView.getColumns().add(column2);
	        
	        TableColumn<Recipe,String> column3 = new TableColumn<> ("Time");
	        column3.setCellValueFactory(new PropertyValueFactory<>("time"));      
	        tableView.getColumns().add(column3);
	        
	        TableColumn<Recipe,String> column4= new TableColumn<> ("Like");
	        column4.setCellValueFactory(new PropertyValueFactory<>("review"));      
	        tableView.getColumns().add(column4);
	        
	        VBox vbox = new VBox(tableView);
	        ScrollPane scroll = new ScrollPane(vbox);
	        scroll.setLayoutX(79);
	        scroll.setLayoutY(104);
	        scroll.setPannable(true);
	        scroll.setPrefSize(678, 408);
	        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
	        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); 
	        root.getChildren().add(scroll);
	        ingStageFoundRecPage.show();
	        
	        
			recipe=RecipeManager.foundIngredient(rb.getRecBeanIngredients(),rb.getRecBeanCategory(),rb.getRecBeanDifficulty()); 
			list.clear();
			if (recipe!= null) {
			//cycle for found recipes
				Set<Recipe> secondList = check(recipe);
				
				for(Recipe s : secondList) {
					
					//add title to the list
					list.add(new Recipe (s.getTitle(), s.getPreparation(), s.getTime(),s.getReview())); 
					tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
					tableView.setItems(list);
				}
			}else {
				Alert alertFoundRec = new Alert(AlertType.INFORMATION);
				alertFoundRec.setTitle("Information");
				alertFoundRec.setHeaderText("Information!");
				alertFoundRec.setContentText("There are not available recipes! Search again.");
				alertFoundRec.showAndWait();
			}
			
			
			tableView.addEventHandler(MouseEvent.MOUSE_CLICKED, (event -> {
	            
	          
	            try {  	
	            	
	           	            
	            	rc= rm.chooseRecipe(tableView.getSelectionModel().getSelectedCells().get(0).getTableColumn().getCellObservableValue(tableView.getSelectionModel().getSelectedCells().get(0).getRow()).getValue().toString());
	            	
	            	if(rb.validateRec(tableView.getSelectionModel().getSelectedCells().get(0).getTableColumn().getCellObservableValue(tableView.getSelectionModel().getSelectedCells().get(0).getRow()).getValue().toString())) { 
	              		rb.setRecBeanTitle(rc.getTitle());
	              		rb.setRecBeanPreparation(rc.getPreparation());
	              		rb.setRecBeanNecessary(rc.getNecessary());
	              		rb.setRecBeanTime(rc.getTime());
	              		rb.setRecBeanReview(rc.getReview());
	            		rm.setRecipe(rb);
	            		gc.showRecipe();
	            	}
	            	
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}

	public void back(ActionEvent e) {
		list.clear();
		((Node)(e.getSource())).getScene().getWindow().hide();
    	GraphicController graphicController = new GraphicController();
        graphicController.ingredientPage();
	}
		
}