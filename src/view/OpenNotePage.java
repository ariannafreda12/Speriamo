package view;

import java.util.Optional;

import bean.NotesBean;
import bean.UserBean;
import controller.GraphicController;
import controller.LoginManager;
import controller.NotesManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class OpenNotePage {
	
	private Button saveNotebtn = new Button();
	private Button deleteNotebtn = new Button();
	private Label writeNoteLabel = new Label();
	
	GraphicController graphicController = new GraphicController();
	
	LoginManager lm = LoginManager.getInstance();
	UserBean u = lm.getUser();
	
	NotesManager nm= NotesManager.getInstance();
	NotesBean nb=nm.getNote();
	
	private static final String SYSTEM = "System";
	
	public void startOpenNotePage (){
		
		
		AnchorPane root = new AnchorPane();
		Stage window = new Stage();
		window.getIcons().add(new Image("img/icon.png"));
		window.setTitle("WhatEat?!" );
        window.setResizable(false);
        BackgroundSize size = new BackgroundSize(400,400,true, true, true, false);
        BackgroundImage myBI= new BackgroundImage(new Image("img/note1.jpg"),BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,size );
    
        root.setBackground(new Background(myBI));
		Scene scene = new Scene(root, 400, 400);
		window.setScene(scene);	
		root.setPrefSize(500, 500);
		writeNoteLabel.setText("Write note");
		writeNoteLabel.setLayoutY(20);
		writeNoteLabel.setLayoutX(120);
		writeNoteLabel.setFont(Font.font(SYSTEM, FontWeight.BOLD, FontPosture.ITALIC, 22));
	        
		saveNotebtn.setText("Save");
		saveNotebtn.setLayoutX(300);
		saveNotebtn.setLayoutY(350);
		saveNotebtn.setFont(Font.font(SYSTEM, FontWeight.BOLD, FontPosture.ITALIC, 18));
		
		
		deleteNotebtn.setText("Delete");
		deleteNotebtn.setLayoutX(200);
		deleteNotebtn.setLayoutY(350);
		deleteNotebtn.setFont(Font.font(SYSTEM, FontWeight.BOLD, FontPosture.ITALIC, 18));
		
		TextArea txtNote = new TextArea ();
		txtNote.setLayoutX(60);
		txtNote.setLayoutY(68);
		txtNote.setPrefSize(246, 280);
		txtNote.setFont(Font.font(SYSTEM, FontPosture.ITALIC, 20));
		txtNote.setText(nb.getNote());
        
        VBox vibox = new VBox(txtNote);
        ScrollPane sp = new ScrollPane(vibox);
        sp.setLayoutX(60);
        sp.setLayoutY(60);
        sp.setPrefSize(246, 280);
        sp.setHbarPolicy(ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        
        saveNotebtn.setOnAction(new EventHandler<ActionEvent>() {
 			public void handle(ActionEvent event) {
 				if ((nm.modifyNote(nb.getNote(), txtNote.getText()))) {
 					 Alert alertMod = new Alert(AlertType.CONFIRMATION);
 						alertMod.setTitle("Success");
 						alertMod.setHeaderText("Success!");
 						alertMod.setContentText("Note modified");
 						alertMod.showAndWait();
 						((Node)(event.getSource())).getScene().getWindow().hide();
 						
 				        try {
							graphicController.profilePage();
						} catch (Exception e) {
							
							e.printStackTrace();
						}
 						
 						
 				}
 				else {
 					Alert alertSave = new Alert(AlertType.INFORMATION);
						alertSave.setTitle("Information");
						alertSave.setHeaderText("Information!");
						alertSave.setContentText("Note already saved");
						alertSave.showAndWait();
						((Node)(event.getSource())).getScene().getWindow().hide();
 					
 				}
 			}
 		});
       
        deleteNotebtn.setOnAction(new EventHandler<ActionEvent>() {
 			public void handle(ActionEvent event) {
 				Alert dialogoAllerta = new Alert(AlertType.CONFIRMATION, "Are you sure you want to delete the note?");
 				Optional<ButtonType> risposta = dialogoAllerta.showAndWait();
 				
 				if(risposta.isPresent() && risposta.get() == ButtonType.OK) {
 					((Node)(event.getSource())).getScene().getWindow().hide();
 					if ((nm.deleteNotes(nb.getNote(), nb.getUsername()))) {
 	 					
 	 					try {
 								graphicController.profilePage();
 								Alert alert = new Alert(AlertType.CONFIRMATION);
 	 	 						alert.setTitle("Success");
 	 	 						alert.setHeaderText("Success!");
 	 	 						alert.setContentText("Note deleted");
 	 	 						alert.showAndWait();
 							} catch (Exception e) {
 								
 								e.printStackTrace();
 							}
 	 						
 	 						
 	 				}
 					else {
 	 					Alert alert = new Alert(AlertType.INFORMATION);
 							alert.setTitle("Information");
 							alert.setHeaderText("Information!");
 							alert.setContentText("Note not deleted succesfully");
 							alert.showAndWait();
 							((Node)(event.getSource())).getScene().getWindow().hide();
 							}
 					}
 				
 				

 				
 			
 			}
 		});
		root.getChildren().add(sp);
		root.getChildren().addAll(writeNoteLabel,saveNotebtn,deleteNotebtn);
		window.setScene(scene);
		window.show();
	}

}