package view;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import bean.RecipeBean;
import bean.UserBean;
import controller.GraphicController;
import controller.LoginManager;
import controller.RecipeManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class IngredientPage {
	
	
	@FXML
	public Button searchBtn;

	RecipeManager rm= RecipeManager.getInstance();
	RecipeBean rb=rm.getRecipe();
	
	LoginManager lm = LoginManager.getInstance();
	UserBean ub = lm.getUser();
	
	Set<String> choiceList = new HashSet<>();
	Set<CheckBox> cb = new HashSet<>();
	
	static Logger logger = Logger.getAnonymousLogger();
	private static final String CONTEXT = "context";
	
	private static final String SYSTEM = "System";
	
	String[] varius = {"Butter", "Cheese", "Chocolate", "Eggs", "Flour", "Honey", "Jam", "Milk", "Oil", "Pasta", "Rice", "Salt", "Sugar", "Water", "Yeast", "Yoghurt"};
	String[] sauces = {"Ketchup", "Mayonnaise", "Mustard", "Vinegar"};
	String[] herbspices = {"Basil", "Chives", "Coriander", "Dill", "Origan", "Parsley", "Rosemary", "Sage", "Thyme", "Cinnamon", "Cumin", "Curry", "Nutmeg", "Paprika", "Pepper", "Saffron"};
	String[] snack = {"Olives", "Walnuts", "Peanuts", "Nuts"};
	String[] vegetables = {"Artichoke", "Asparagus", "Aubergine", "Avocado", "Beansprouts", "Beetroot", "Broad beans", "Broccoli", "Cabbage", "Carrot", "Cauliflower", "Celery", "Chilli pepper", "Courgette", "Cucumber", "French beans", "Garlic", "Ginger", "Leek","Lettuce", "Mushroom", "Onion", "Peas", "Pepper", "Potato", "Pumkin", "Radish", "Rocket", "Tomato", "Turnip", "Spinach", "Squash"};
	String[] fruit = {"Apple","Apricot", "Banana", "Blackberry", "Blueberry", "Cherry", "Coconut", "Fig", "Grape", "Kiwi", "Lemon", "Lime", "Mango", "Melon", "Orange", "Peach", "Pear", "Pineapple", "Plum", "Raspberry", "Strawberry"};
	String[] fish = {"Anchovy", "Cod", "Herring", "Mackerel", "Pilchard", "Plaice", "Salmon", "Sardine", "Sole", "Trout", "Tuna"};
	String[] meat = {"Bacon", "Beef", "Chicken", "Duck", "Ham", "Lamb", "Liver", "Nince", "Salami", "Sausage", "Turkey", "Veal"};
	
	
	//method to add checkBox in a grid pane of n column
	private void addCheckbox(GridPane gpane, String[] strings, int column){
        int row = 0;
        int col = 0;
        
        for(int i=0; i<strings.length; i++){
            CheckBox checkB = new CheckBox(strings[i]);
            cb.add(checkB);
            gpane.add(checkB, col, row);
            col = ++col % column;
            row = col == 0 ? ++row : row;
        }
	}
	
	public void myProfileIngredientPage(){
    	GraphicController graphicController = new GraphicController();
        graphicController.profilePage();
	}
	
	public void logOutIngredientPage(MouseEvent me){
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
	
	public void createNoteIngredientPage(){
    	GraphicController graphicController = new GraphicController();
        graphicController.notePage();
	}
	
	private void newUserIngredientPage(){
		GraphicController graphicController = new GraphicController();
        graphicController.registrationPage();
	}
	
	public void start(){
		
		Stage ingStage = new Stage();
		FXMLLoader loader = new FXMLLoader (IngredientPage.class.getResource("chooseIngredients.fxml"));
		AnchorPane rootIngPage;
		try {
			rootIngPage = loader.load();
			ingStage.getIcons().add(new Image("img/icon.png"));
	        ingStage.setTitle("WhatEat?!" );
	        ingStage.setResizable(false);
	        Scene scene = new Scene(rootIngPage, 800, 600);
	        
	        final Label hi= new Label();
	        hi.setText("Hi " + ub.getUsername());
	        hi.setLayoutY(10);
	        hi.setLayoutX(600);
	        hi.setFont(Font.font(SYSTEM, FontPosture.ITALIC, 18));
	        
	        FileInputStream inputP = new FileInputStream("src\\img\\icons8-nome-100.png");
	        Image imageP = new Image(inputP);
	        ImageView userViewIngredientPage = new ImageView(imageP);
	        userViewIngredientPage.setFitHeight(30);
	        userViewIngredientPage.setFitWidth(34);
	        userViewIngredientPage.setLayoutX(712);
	        userViewIngredientPage.setLayoutY(7);
	        userViewIngredientPage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
	            try {
					myProfileIngredientPage();
				} catch (Exception e) {
					e.printStackTrace();
				}
	        });
	        
	        FileInputStream inputN = new FileInputStream("src\\img\\icons8-libretto-a-spirale-legato-80.png");
	        Image imageN = new Image(inputN);
	        ImageView noteViewIngredientPage = new ImageView(imageN);
	        noteViewIngredientPage.setFitHeight(39);
	        noteViewIngredientPage.setFitWidth(38);
	        noteViewIngredientPage.setLayoutX(490);
	        noteViewIngredientPage.setLayoutY(3);
	        noteViewIngredientPage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
	            try {
					createNoteIngredientPage();
				} catch (Exception e) {
					e.printStackTrace();
				}
	        });
	        
	        FileInputStream inputL = new FileInputStream("src\\img\\icons8-uscita-100.png");
	        Image imageL = new Image(inputL);
	        ImageView logoutViewIngredientPage = new ImageView(imageL);
	        logoutViewIngredientPage.setFitHeight(30);
	        logoutViewIngredientPage.setFitWidth(28);
	        logoutViewIngredientPage.setLayoutX(758);
	        logoutViewIngredientPage.setLayoutY(7);
	        logoutViewIngredientPage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
	            try {
					logOutIngredientPage(event);
				} catch (Exception e) {
					e.printStackTrace();
				}
	        });
	        
	        if (lm.getUser().getUsername()=="Chef"){
				noteViewIngredientPage.setVisible(false);
				userViewIngredientPage.setVisible(false);
				FileInputStream inputR = new FileInputStream("src\\img\\icons8-nome-100.png");
		        Image imageR = new Image(inputR);
		        ImageView rViewIngredientPage = new ImageView(imageR);
		        rViewIngredientPage.setFitHeight(30);
		        rViewIngredientPage.setFitWidth(34);
		        rViewIngredientPage.setLayoutX(712);
		        rViewIngredientPage.setLayoutY(7);
		        rootIngPage.getChildren().add(rViewIngredientPage);
		        rViewIngredientPage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
		            try {
						newUserIngredientPage();
					} catch (Exception e) {
						e.printStackTrace();
					}
		        });
		        
	        }
	        
	        rootIngPage.getChildren().addAll(hi, noteViewIngredientPage, userViewIngredientPage, logoutViewIngredientPage);
	              
	        ScrollPane spIng = new ScrollPane();
	        spIng.setLayoutX(79);
	        spIng.setLayoutY(104);
	        spIng.setPrefSize(650,400);
	        spIng.setHbarPolicy(ScrollBarPolicy.NEVER);
	        spIng.setVbarPolicy(ScrollBarPolicy.ALWAYS);
	        
	        AnchorPane anc = new AnchorPane();
	       
	        Label titleLabel = new Label("Select ingredients:");
	        titleLabel.setAlignment(Pos.CENTER);
	        titleLabel.setFont(Font.font(SYSTEM,FontWeight.BOLD, FontPosture.ITALIC, 20));
	        
	        Label variusLabel = new Label("Varius:");
	        variusLabel.setFont(Font.font(SYSTEM, FontWeight.BOLD, FontPosture.ITALIC, 14 ));
	        
	        Label saucesLabel = new Label("Sauces:");
	        saucesLabel.setFont(Font.font(SYSTEM, FontWeight.BOLD, FontPosture.ITALIC, 14 ));
	        
	        Label herbspicesLabel = new Label("Herbs e Spices:");
	        herbspicesLabel.setFont(Font.font(SYSTEM, FontWeight.BOLD, FontPosture.ITALIC, 14 ));
	        
	        Label snackLabel = new Label("Snack:");
	        snackLabel.setFont(Font.font(SYSTEM, FontWeight.BOLD, FontPosture.ITALIC, 14 ));
	        
	        Label vegetablesLabel = new Label("Vegetables:");
	        vegetablesLabel.setFont(Font.font(SYSTEM, FontWeight.BOLD, FontPosture.ITALIC, 14 ));
	        
	        Label fruitLabel = new Label("Fruit:");
	        fruitLabel.setFont(Font.font(SYSTEM, FontWeight.BOLD, FontPosture.ITALIC, 14 ));

	        Label fishLabel = new Label("Fish:");
	        fishLabel.setFont(Font.font(SYSTEM, FontWeight.BOLD, FontPosture.ITALIC, 14 ));
	        
	        Label meatLabel = new Label("Meat:");
	        meatLabel.setFont(Font.font(SYSTEM, FontWeight.BOLD, FontPosture.ITALIC, 14 ));
	        
	        ColumnConstraints col = new ColumnConstraints(130);
	        
	        GridPane grid1 = new GridPane(); 
	        grid1.getColumnConstraints().addAll(col,col, col, col);
	        grid1.setHgap(20); 
			grid1.setVgap(10);
			grid1.setPadding (new Insets (10));
			addCheckbox(grid1, varius, 4);
			
			GridPane grid2 = new GridPane(); 
	        grid2.getColumnConstraints().addAll(col,col, col, col);
			grid2.setHgap(20); 
			grid2.setVgap(10);
			grid2.setMinWidth(1500);
			grid2.setMaxWidth(1500);
			grid2.setPrefWidth(1500);
			grid2.setPadding (new Insets (10));
			addCheckbox(grid2, sauces, 4);
			
			GridPane grid3 = new GridPane();
			grid3.getColumnConstraints().addAll(col, col, col, col);
			grid3.setHgap(20); 
			grid3.setVgap(10);
			grid3.setPadding (new Insets (10));
			addCheckbox(grid3, herbspices, 4);
			
			GridPane grid4 = new GridPane(); 
			grid4.getColumnConstraints().addAll(col, col, col, col);
			grid4.setHgap(20); 
			grid4.setVgap(10);
			grid4.setPadding (new Insets (10));
			addCheckbox(grid4, snack, 4);
			
			GridPane grid5 = new GridPane(); 
			grid5.getColumnConstraints().addAll(col, col, col, col);
			grid5.setHgap(20); 
			grid5.setVgap(10);
			grid5.setPadding (new Insets (10));
			addCheckbox(grid5, vegetables, 4);
			
			GridPane grid6 = new GridPane(); 
			grid6.getColumnConstraints().addAll(col, col, col, col);
			grid6.setHgap(20); 
			grid6.setVgap(10);
			grid6.setPadding (new Insets (10));
			addCheckbox(grid6, fruit, 4);
			
			GridPane grid7 = new GridPane(); 
			grid7.getColumnConstraints().addAll(col, col, col, col);
			grid7.setHgap(20); 
			grid7.setVgap(10);
			grid7.setPadding (new Insets (10));
			addCheckbox(grid7, fish, 4);
			
			GridPane grid8 = new GridPane(); 
			grid8.getColumnConstraints().addAll(col, col, col, col);
			grid8.setHgap(20); 
			grid8.setVgap(10);
			grid8.setPadding (new Insets (10));
			addCheckbox(grid8, meat, 4);
			
			for(CheckBox checkbox : cb){
				checkbox.setOnAction(evt -> 
			         isSelected(checkbox)
			    );
			}
			
			VBox vbox = new VBox();
		    vbox.getChildren().addAll(titleLabel,variusLabel, grid1, saucesLabel, grid2, herbspicesLabel,grid3, snackLabel, grid4, vegetablesLabel, grid5, fruitLabel, grid6, fishLabel, grid7, meatLabel, grid8);
		    vbox.setPadding (new Insets (15));
	        anc.getChildren().add(vbox);
	        spIng.setContent(anc);
			rootIngPage.getChildren().add(spIng);
			ingStage.setScene(scene);
		    ingStage.show();
		} catch (IOException e1) {
			logger.log(null, CONTEXT,e1);
		}
		
	    
	}
	
	public Set<String> isSelected(CheckBox cbox) {
		if(cbox.isSelected()) {
			choiceList.add(cbox.getText());
		}else {
			choiceList.remove(cbox.getText());
		}
		rb.setRecBeanIngredient(choiceList);
		rm.setRecipe(rb);
		return choiceList;
	}
	
	public void selectIngredients(ActionEvent e){
		
		((Node)(e.getSource())).getScene().getWindow().hide();
	    GraphicController graphicController = new GraphicController();
	    graphicController.foundPage();
	}
	
	public void back(ActionEvent e){
		((Node)(e.getSource())).getScene().getWindow().hide();
    	GraphicController graphicController = new GraphicController();
        graphicController.difficultPage();
	}
		
}
