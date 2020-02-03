package view;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
	

	ArrayList <Recipe> recipe;	
	GraphicController gc = new GraphicController();
	
	//instance of recipe manager for get difficulty, category and ingredient list
    RecipeManager rm= RecipeManager.getInstance();
	RecipeBean rb=rm.getRecipe();
	
	LoginManager lm =LoginManager.getInstance();
	UserBean ub= lm.getUser();
	
	public ArrayList<Recipe> check(ArrayList<Recipe> first) {
		Set<String> unique =  new HashSet<String>();
		List<String> dupes = new ArrayList<String>();
		ArrayList<Recipe> secondUnique = new ArrayList<Recipe>();
		
		for(Recipe a : first) {
			if(unique.contains(a.getTitle())) {
				dupes.add(a.getTitle());}
			else {
				unique.add(a.getTitle());
				secondUnique.add(a);
			}
				
		}
		
		System.out.println(unique);
		System.out.println(dupes);
		return secondUnique;
	}
	
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
		FXMLLoader loader = new FXMLLoader (FoundRecipes.class.getResource("found_recipes.fxml"));
		AnchorPane root = loader.load();
		ingStage.getIcons().add(new Image("img/icon.png"));
        ingStage.setTitle("WhatEat?!" );
        ingStage.setResizable(false);
        Scene scene = new Scene(root, 800, 600);
        ingStage.setScene(scene);
        
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
        
        TableView<Recipe> tableView = new TableView();
        
        tableView.setPrefWidth(675);
        TableColumn<Recipe,String> column1 = new TableColumn<Recipe,String> ("Title");
        column1.setCellValueFactory(new PropertyValueFactory<>("title"));      
        tableView.getColumns().add(column1);
        
        TableColumn<Recipe,String> column2 = new TableColumn<Recipe,String> ("Preparation");
        column2.setCellValueFactory(new PropertyValueFactory<>("preparation"));      
        tableView.getColumns().add(column2);
        
        TableColumn<Recipe,String> column3 = new TableColumn<Recipe,String> ("Time");
        column3.setCellValueFactory(new PropertyValueFactory<>("time"));      
        tableView.getColumns().add(column3);
        
        TableColumn<Recipe,String> column4= new TableColumn<Recipe,String> ("Like");
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
        ingStage.show();
        
        
		recipe=RecipeManager.foundIngredient(rb.getIngredients(),rb.getCategory(),rb.getDifficulty()); 
		list.clear();
		if (recipe!= null) {
		//cycle for found recipes
			ArrayList <Recipe> secondList = check(recipe);
			
			for(Recipe s : secondList) {
				
				//add title to the list
				list.add(new Recipe (s.getTitle(), s.getPreparation(), s.getTime(),s.getReview())); 
				tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
				tableView.setItems(list);
			}
		}else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText("Information!");
			alert.setContentText("There are not available recipes! Search again.");
			alert.showAndWait();
		}
		
		
		tableView.addEventHandler(MouseEvent.MOUSE_CLICKED, (event -> {
            
          
            try { 
            	
            	TablePosition<?, ?> pos= tableView.getSelectionModel().getSelectedCells().get(0);
            	int row = pos.getRow();
            	TableColumn col = pos.getTableColumn();
            	String t = (String) col.getCellObservableValue(row).getValue().toString();
            	Recipe rc = new Recipe(0,null,null,null,null,null,null,0); 
            	rc= rm.chooseRecipe(t);
            	
            	if(rb.validateRec(t)== true) { 
              		rb.setRecBeanTitle(t);
              		rb.setPreparation(rc.getPreparation());
              		rb.setNecessary(rc.getNecessary());
              		rb.setTime(rc.getTime());
            		rm.setRecipe(rb);
            		gc.showRecipe();
            	}
            	
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
	}

	public void back(ActionEvent e) {
		list.clear();
		((Node)(e.getSource())).getScene().getWindow().hide();
    	GraphicController graphicController = new GraphicController();
        graphicController.ingredientPage();
	}
		
}