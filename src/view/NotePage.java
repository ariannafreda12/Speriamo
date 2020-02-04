package view;


import bean.NotesBean;
import bean.UserBean;
import controller.LoginManager;
import controller.NotesManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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

public class NotePage {

	private Button saveNotebtn = new Button();
	private Label writeNoteLabel = new Label();
	
	LoginManager lm = LoginManager.getInstance();
	UserBean u = lm.getUser();
	
	NotesManager nm= NotesManager.getInstance();
	NotesBean n=nm.getNote();
	
	private static final String SYSTEM = "System";
	
	public void start (){
		
		AnchorPane rootNotePage = new AnchorPane();
		Stage window = new Stage();
		window.getIcons().add(new Image("img/icon.png"));
		window.setTitle("WhatEat?!" );
        window.setResizable(false);
        BackgroundSize size = new BackgroundSize(400,400,true, true, true, false);
        BackgroundImage myBI= new BackgroundImage(new Image("img/note1.jpg"),BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,size );
    
        rootNotePage.setBackground(new Background(myBI));
		Scene scene = new Scene(rootNotePage, 400, 400);
		window.setScene(scene);	
		rootNotePage.setPrefSize(500, 500);
		writeNoteLabel.setText("Write note");
		writeNoteLabel.setLayoutY(20);
		writeNoteLabel.setLayoutX(120);
		writeNoteLabel.setFont(Font.font(SYSTEM, FontWeight.BOLD, FontPosture.ITALIC, 22));
	        
		saveNotebtn.setText("Save");
		saveNotebtn.setLayoutX(300);
		saveNotebtn.setLayoutY(350);
		saveNotebtn.setFont(Font.font(SYSTEM, FontWeight.BOLD, FontPosture.ITALIC, 18));
		
		TextArea txtNote = new TextArea ();
		txtNote.setLayoutX(60);
		txtNote.setLayoutY(68);
		txtNote.setPrefSize(246, 280);
		txtNote.setFont(Font.font(SYSTEM, FontPosture.ITALIC, 20));
        
        VBox vibox = new VBox(txtNote);
        ScrollPane sp = new ScrollPane(vibox);
        sp.setLayoutX(60);
        sp.setLayoutY(60);
        sp.setPrefSize(246, 280);
        sp.setHbarPolicy(ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        
        saveNotebtn.setOnAction(new EventHandler<ActionEvent>() {
 			public void handle(ActionEvent event) {
 				if ((nm.addNotes(txtNote.getText(), u.getUsername()))) {
 					 Alert alert = new Alert(AlertType.CONFIRMATION);
 						alert.setTitle("Success");
 						alert.setHeaderText("Success!");
 						alert.setContentText("Note saved");
 						alert.showAndWait();
 						((Node)(event.getSource())).getScene().getWindow().hide();		
 				}else {
 					 Alert alert = new Alert(AlertType.CONFIRMATION);
						alert.setTitle("Information");
						alert.setHeaderText("Information!");
						alert.setContentText("Note already saved");
						alert.showAndWait();
						((Node)(event.getSource())).getScene().getWindow().hide();	
 				}
 			}
 		});
       
		rootNotePage.getChildren().add(sp);
		rootNotePage.getChildren().addAll(writeNoteLabel,saveNotebtn);
		window.setScene(scene);
		window.show();
	}
}
