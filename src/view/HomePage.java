package view;

import java.awt.Desktop;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import bean.RecipeBean;
import bean.UserBean;
import controller.GraphicController;
import controller.LoginManager;
import controller.RecipeManager;
import controller.UserProfileManager;
import exception.EmptyFieldexception;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.Recipe;
import model.UserProfile;

public class HomePage {

	
	@FXML
    private static ObservableList<Recipe> list = FXCollections.observableArrayList();
	
	Set<Recipe> popularRecipe = new HashSet<>();	
	Recipe daily = new Recipe();	
	UserProfile fav = new UserProfile();
	GraphicController gc = new GraphicController();
	
	Recipe rc = new Recipe(null,null,null,null,null,null,0); 
	
    RecipeManager rm= RecipeManager.getInstance();
	RecipeBean rb= new RecipeBean();
	
	LoginManager lm =LoginManager.getInstance();
	UserBean ub= lm.getUser();
	
	private static final String SYSTEM = "System";
	Alert alert = new Alert(AlertType.WARNING);
	
	static Logger logger = Logger.getAnonymousLogger();
	 private static final String CONTEXT = "context";
	 
	 Stage ingStage = new Stage();
	 FXMLLoader loader = new FXMLLoader (FoundRecipes.class.getResource("homePage.fxml"));
	 AnchorPane root;

	public void myProfile(){
		GraphicController graphicController = new GraphicController();
		graphicController.profilePage();
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
	
	public void createNote() {
     	GraphicController graphicController = new GraphicController();
       	graphicController.notePage(); 
	}
	
	public ImageView inputProfileHPage(FileInputStream inH) {
		 Image imageP = new Image(inH);
	     ImageView userH = new ImageView(imageP);
	     userH.setFitHeight(30);
	     userH.setFitWidth(34);
	     userH.setLayoutX(712);
	     userH.setLayoutY(7);
	     userH.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
	            try {
					myProfile();
				} catch (Exception e) {
					e.printStackTrace();
				}
	        });
	     return userH;
		
	}
	
	public ImageView inputNoteHPage(FileInputStream inpNH) {
		 Image imageN = new Image(inpNH);
	     ImageView noteVH = new ImageView(imageN);
	     noteVH.setFitHeight(39);
	     noteVH.setFitWidth(38);
	     noteVH.setLayoutX(490);
	     noteVH.setLayoutY(3);
	     noteVH.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
	            try {
	            	createNote();
				} catch (Exception e) {
					e.printStackTrace();
				}
	        });
	     return noteVH;
	}
	public ImageView inputLogOutHPage(FileInputStream inpLH) {
		 Image imageL = new Image(inpLH);
	     ImageView logoutH = new ImageView(imageL);
	     logoutH.setFitHeight(30);
	     logoutH.setFitWidth(28);
	     logoutH.setLayoutX(758);
	     logoutH.setLayoutY(7);
	     logoutH.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
	            try {
	            	logOut(event);
				} catch (Exception e) {
					e.printStackTrace();
				}
	        });
	     return logoutH;
	}
	
	private Label titleLabel = new Label("Discover popular recipes:");
	private Label recLabel = new Label();
	private Label search= new Label();
	private final Label hi= new Label();
	private TableView<Recipe> tableView = new TableView<>();
	private Label title= new Label();
    private Label cat= new Label();
    private Label diff= new Label();
    private Label prep= new Label();
    private TextArea prepLabel = new TextArea();
    private TextArea tf = new TextArea();                     
    private Button se= new Button();
    private TextArea necLabel = new TextArea();
	
	public void startHomePage(){
		
		try {
			root = loader.load();
			ingStage.getIcons().add(new Image("img/icon.png"));
	        ingStage.setTitle("WhatEat?!" );
	        ingStage.setResizable(false);
	        Scene scene = new Scene(root, 800, 600);
	        ingStage.setScene(scene);
	        
	       
	        titleLabel.setAlignment(Pos.TOP_LEFT);
	        titleLabel.setFont(Font.font(SYSTEM,FontWeight.BOLD, FontPosture.ITALIC, 20));
	        titleLabel.setTextFill(Color.FIREBRICK);
	        titleLabel.setLayoutX(79);
	        titleLabel.setLayoutY(129);
	        
	        
	        recLabel.setAlignment(Pos.TOP_LEFT);
	        recLabel.setFont(Font.font(SYSTEM,FontWeight.BOLD, FontPosture.ITALIC, 20));
	        recLabel.setTextFill(Color.FIREBRICK);
	        recLabel.setLayoutX(405);
	        recLabel.setLayoutY(129);
	        
	        
	        search.setFont(Font.font(SYSTEM, FontWeight.BOLD, FontPosture.ITALIC, 18));
	        search.setText(" Search a cooking class near you");
	        search.setTextFill(Color.FIREBRICK);
	        search.setLayoutX(80);
	        search.setLayoutY(350);
	        
	        
	        hi.setText("Hi " + ub.getUsername());
	        hi.setLayoutY(10);
	        hi.setLayoutX(600);
	        hi.setFont(Font.font(SYSTEM, FontPosture.ITALIC, 18));
	        
	    
	        FileInputStream inputPH = new FileInputStream("src\\img\\icons8-nome-100.png");
	        ImageView userView= inputProfileHPage(inputPH);
	       
	        FileInputStream inputNH = new FileInputStream("src\\img\\icons8-libretto-a-spirale-legato-80.png");
	        ImageView noteView = inputNoteHPage(inputNH);
	       
	        
	        FileInputStream inputLH = new FileInputStream("src\\img\\icons8-uscita-100.png");
	        ImageView logoutView =inputLogOutHPage(inputLH);
	        
	      if (lm.getUser().getUsername()=="Chef"){
				noteView.setVisible(false);
				userView.setVisible(false);
	        }
	        
	        root.getChildren().addAll(hi, noteView, userView, logoutView, titleLabel, recLabel);
	        
	       
	        tableView.setPrefWidth(300);
	        
	        TableColumn<Recipe,String> column1 = new TableColumn<>("Title");
	        column1.setCellValueFactory(new PropertyValueFactory<>("title"));      
	        tableView.getColumns().add(column1);
	        
	        TableColumn<Recipe,String> column2= new TableColumn<> ("Like");
	        column2.setCellValueFactory(new PropertyValueFactory<>("review"));      
	        tableView.getColumns().add(column2);
	        
	        VBox vbox = new VBox(tableView);
	        ScrollPane scroll = new ScrollPane(vbox);
	        scroll.setLayoutX(79);
	        scroll.setLayoutY(175);
	        scroll.setPannable(true);
	        scroll.setPrefSize(300, 160);
	        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
	        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); 
	               
	        VBox vb = new VBox();
	        
	        
	        title.setFont(Font.font(SYSTEM, FontWeight.BOLD, FontPosture.ITALIC, 18));
	       
	        cat.setFont(Font.font(SYSTEM, FontPosture.ITALIC, 16));
	        
	        diff.setFont(Font.font(SYSTEM, FontPosture.ITALIC, 16));
	        
	        prep.setFont(Font.font(SYSTEM, FontPosture.ITALIC, 16));
	       
	       
	        necLabel.setWrapText(true);
	        necLabel.setFont(Font.font(SYSTEM, FontPosture.ITALIC, 14));
	        necLabel.setPrefSize(330, 80);
	        necLabel.setEditable(false);
	        
	        prepLabel.setPrefSize(330, 150);
	        prepLabel.setWrapText(true);
	        prepLabel.setFont(Font.font(SYSTEM, FontPosture.ITALIC, 14));
	        prepLabel.setEditable(false);
	           
	        vb.getChildren().addAll(title, cat, diff, prep, necLabel, prepLabel);
	        vb.setSpacing(5); 
	        
	        ScrollPane scroll2 = new ScrollPane(vb);
	        scroll2.setLayoutX(400);
	        scroll2.setLayoutY(175);
	        scroll2.setPannable(true);
	        scroll2.setPrefSize(340, 370);
	        scroll2.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
	        scroll2.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
	        
	     
	       
	        tf.setWrapText(true);
	        tf.setFont(Font.font(SYSTEM, FontPosture.ITALIC, 18));
	        tf.setPrefSize(290, 10);
	        tf.setMaxHeight(10);
	        tf.setPromptText("Insert your city");
	        tf.setLayoutX(75);
	        tf.setLayoutY(380);
	      
	        se.setText("Find it!");
	        se.setFont(Font.font(SYSTEM, FontWeight.BOLD, FontPosture.ITALIC, 16));
	        se.setPrefSize(100,10);
	        se.setLayoutX(170);
	        se.setLayoutY(445);
	               
	        se.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {  
				try {
					if(!tf.getText().isEmpty()) {				
		 		    Desktop.getDesktop().browse(new URI("https://www.google.com/search?q=" + URLEncoder.encode("cooking course in ", "UTF-8") + URLEncoder.encode(tf.getText(), "UTF-8")));
		 		    }
					else {
						throw new EmptyFieldexception();
					
					}
				} catch (URISyntaxException|IOException e) {
					
					logger.log(null, CONTEXT,e);
				} catch(EmptyFieldexception e) {
					
					alert.setTitle("Warning");
					alert.setHeaderText("Attention!");
					alert.setContentText("Enter your city. Please.");
					alert.showAndWait();
					
				}
	 				
	        });      
	    
	        root.getChildren().addAll(scroll,scroll2,search, tf, se);
	             
	         if (lm.getUser().getUsername()=="Chef")    {
	        	daily=RecipeManager.dailyRecipe(); 
	        	recLabel.setText("Try daily recipe:");
	        	title.setText("  " + daily.getTitle());
	        	cat.setText("  Category:  " + daily.getCategory());
	        	diff.setText("  Difficulty:  " + daily.getDifficulty());
	        	prep.setText("  Preparation time:  " + daily.getTime());
	        	necLabel.setText(daily.getNecessary());
	        	prepLabel.setText(daily.getPreparation());
	        } else if ((fav=UserProfileManager.favRecipe(lm.getUser().getUsername()))!=null) {
	        	
	        	recLabel.setText("One of your favourite recipe:");
	        	title.setText("  " + fav.getTitle());
	        	cat.setText("  Category:  " + fav.getCategory());
	        	diff.setText("  Difficulty:  " + fav.getDifficulty());
	        	prep.setText("  Preparation time:  " + fav.getTime());
	        	necLabel.setText(fav.getNecessary());
	        	prepLabel.setText(fav.getPreparation());
	        	
	        }else{
	        	recLabel.setText("You haven't a favourite recipe yet");
	        	title.setVisible(false);
	        	cat.setVisible(false);
	        	diff.setVisible(false);
	        	prep.setVisible(false);
	        	necLabel.setVisible(false);
	        	prepLabel.setVisible(false);
	        	
	        }
	        
	        ingStage.show();
	        
			popularRecipe=RecipeManager.popularRecipe(); 
		
			if (popularRecipe!= null) {
			//cycle for found recipes
				
				for(Recipe s : popularRecipe) {
					
					//add title to the list
					list.add(new Recipe (s.getTitle(), s.getReview())); 
					tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
					tableView.setItems(list);
				}
			}else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText("Information!");
				alert.setContentText("There are not popular recipes yet!");
				alert.showAndWait();
			}
			
			
			tableView.addEventHandler(MouseEvent.MOUSE_CLICKED, (event -> {
	          
	            try {         	
	            	
	            	rc= rm.chooseRecipe(tableView.getSelectionModel().getSelectedCells().get(0).getTableColumn().getCellObservableValue(tableView.getSelectionModel().getSelectedCells().get(0).getRow()).getValue().toString());
	            	
	            	if(rc!=null) { 
	              		rb.setRecBeanTitle(rc.getTitle());
	              		rb.setRecBeanDifficulty(rc.getDifficulty());
	              		rb.setRecBeanCategory(rc.getCategory());
	              		rb.setRecBeanPreparation(rc.getPreparation());
	              		rb.setRecBeanNecessary(rc.getNecessary());
	              		rb.setRecBeanTime(rc.getTime());
	            		rm.setRecipe(rb);
	            		gc.showRecipe();
	            		
	            	}
	            	
	            } catch (Exception e) {
	            	logger.log(null, CONTEXT,e);
	            }
			}));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		

	}

	public void go(ActionEvent e){
		list.clear();
		((Node)(e.getSource())).getScene().getWindow().hide();
    	GraphicController graphicController = new GraphicController();
        graphicController.mealPage();
	}
}
