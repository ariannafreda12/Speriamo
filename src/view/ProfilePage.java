package view;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import bean.NotesBean;
import bean.RecipeBean;
import bean.UserBean;
import bean.UserRecipeBean;
import controller.GraphicController;
import controller.LoginManager;
import controller.NotesManager;
import controller.RecipeManager;
import controller.UserProfileManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;
import model.Notes;
import model.UserProfile;

public class ProfilePage {
	
	@FXML
	public ImageView logOutimg;
	@FXML
	public ImageView note;
	
	@FXML
    private static ObservableList<UserProfile> list = FXCollections.observableArrayList();
	
	@FXML
    private static ObservableList<Notes> notesList = FXCollections.observableArrayList();
	

	Set<UserProfile> userprofile;
	Set<Notes> notes = new HashSet<>();
	
	GraphicController gc = new GraphicController();
	
	static Logger logger = Logger.getAnonymousLogger();
	private static final String CONTEXT = "context";
	
	//instance of UserProfileManager for get user's recipes
	
	UserProfileManager upm= UserProfileManager.getInstance();
	UserRecipeBean upb=new UserRecipeBean();
	UserProfile up = new UserProfile(null);
	
	RecipeManager rm= RecipeManager.getInstance();
	RecipeBean rb=rm.getRecipe();
	
	LoginManager lm =LoginManager.getInstance();
	UserBean ub= lm.getUser();
	
	
	NotesManager nm = NotesManager.getInstance();
	NotesBean nb = new NotesBean();
	Notes nc = new Notes(null,null);
	
	public void createNote()  {
    	GraphicController graphicController = new GraphicController();
        graphicController.notePage();
	}

	public void closeProfilePage(MouseEvent event)  {
		((Node)(event.getSource())).getScene().getWindow().hide();
    
	}
	public void start() {
			
		Stage ingStage = new Stage();
		FXMLLoader loader = new FXMLLoader (ProfilePage.class.getResource("profilePage.fxml"));
		AnchorPane root;
		try {
			root = loader.load();
			ingStage.getIcons().add(new Image("img/icon.png"));
	        ingStage.setTitle("WhatEat?!" );
	        ingStage.setResizable(false);
	        Scene scene = new Scene(root, 800, 600);
	        
	        //create new label for insert user
	        final Label userLabel = new Label();
	        userLabel.setText(ub.getUsername());
	        userLabel.setLayoutY(93);
	        userLabel.setLayoutX(323);
	        userLabel.setFont(Font.font("System", FontPosture.ITALIC, 22));
	        
	        //create new label for mail
	        final Label mailLabel = new Label();
	        mailLabel.setText(ub.getEmail());
	        mailLabel.setLayoutY(93);
	        mailLabel.setLayoutX(550);
	        mailLabel.setFont(Font.font("System", FontPosture.ITALIC, 22));
	        
	        TableView<Notes> tableView2 = new TableView<>();
	        tableView2.setPrefWidth(300);
	        TableColumn<Notes,String> column2 = new TableColumn<> ("Notes");
	        column2.setCellValueFactory(new PropertyValueFactory<>("note"));      
	        tableView2.getColumns().add(column2);
	        VBox vibox = new VBox(tableView2);
	        ScrollPane spNote = new ScrollPane(vibox);
	        spNote.setLayoutX(182);
	        spNote.setLayoutY(260);
	        spNote.setPrefSize(246, 280);
	        spNote.setHbarPolicy(ScrollBarPolicy.NEVER);
	        spNote.setVbarPolicy(ScrollBarPolicy.ALWAYS);
	       
	        TableView<UserProfile> tableView = new TableView<>();
	        tableView.setPrefWidth(300);
	        TableColumn<UserProfile,String> column1 = new TableColumn<> ("Title");
	        column1.setCellValueFactory(new PropertyValueFactory<>("title"));      
	        tableView.getColumns().add(column1);
	        VBox vbox = new VBox(tableView);
	        ScrollPane spRec = new ScrollPane(vbox);
	        spRec.setLayoutX(472);
	        spRec.setLayoutY(260);
	        spRec.setPrefSize(309, 280);
	        spRec.setHbarPolicy(ScrollBarPolicy.NEVER);
	        spRec.setVbarPolicy(ScrollBarPolicy.ALWAYS);
	 
	        root.getChildren().addAll(userLabel, mailLabel, spNote, spRec);
	        ingStage.setScene(scene);
	        ingStage.show();
	        
	        userprofile=UserProfileManager.userRecipe(LoginManager.getInstance().getUser().getUsername()); 
	        list.clear();
			if (userprofile!= null) {
				
			//cycle for found recipes
				for(UserProfile s : userprofile) {
					
					//add title to the list
					list.add(new UserProfile (s.getTitle())); 
					tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
					tableView.setItems(list);		
				}
			}
		
	      
			tableView.addEventHandler(MouseEvent.MOUSE_CLICKED, (event -> {
					         	
	            	
	            	
	            	
	            try { 
	                   	
	            	
	            	
	            	up = upm.chooseUserRecipe(tableView.getSelectionModel().getSelectedCells().get(0).getTableColumn().getCellObservableValue(tableView.getSelectionModel().getSelectedCells().get(0).getRow()).getValue().toString());
	            	
	            	if(up!=null) { 
	              		upb.setURecBeanTitle(up.getTitle());
	              		upb.setURecBeanCategory(up.getCategory());
	              		upb.setURecBeanDifficulty(up.getDifficulty());
	              		upb.setURecBeanNecessary(up.getNecessary());
	              		upb.setURecBeanPreparation(up.getPreparation());
	              		upb.setURecBeanUsername(up.getUsername());
	              		upb.setURecBeanTime(up.getTime());
	              		
	            		upm.setUserProfile(upb);
	            		closeProfilePage(event);
	            		gc.userRecipePage();
	            	}
	            	
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }));
				
				
				notes=NotesManager.userNotes(ub.getUsername()); 
			  	notesList.clear();
				if (notes!= null) {
				
				//cycle for found recipes
					for(Notes n : notes) {
						//add title to the list
						notesList.add(new Notes (n.getNote())); 
						tableView2.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
						tableView2.setItems(notesList);
					}
				}
			
			tableView2.addEventHandler(MouseEvent.MOUSE_CLICKED, (event -> {	          
	            try { 	            	
	            	nc = nm.chooseNote(tableView2.getSelectionModel().getSelectedCells().get(0).getTableColumn().getCellObservableValue(tableView2.getSelectionModel().getSelectedCells().get(0).getRow()).getValue().toString());
	            	
					if(nb.validateNote(tableView2.getSelectionModel().getSelectedCells().get(0).getTableColumn().getCellObservableValue(tableView2.getSelectionModel().getSelectedCells().get(0).getRow()).getValue().toString())) { 
	              		nb.setNote(nc.getNote());
	              		nb.setUsername(nc.getUsername());
	              		nm.setNote(nb);
	              		closeProfilePage(event);
	            		gc.openNotePage();
	            	}
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	            
	        }));
			
	        
		} catch (IOException e1) {
			
			logger.log(null, CONTEXT,e1);
		}
		
    
        
       
		
		  	
	}
}