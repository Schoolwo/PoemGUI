import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class sample extends Application {
	
	Button button;
	TextArea tx;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start (Stage primaryStage) throws Exception {
		primaryStage.setTitle("Title of the Window");
		button = new Button();
		tx = new TextArea();
		tx.setPrefHeight(50); 
		tx.setPrefWidth(400); 
		button.setText("Click Here");
		button.setOnAction(new EventHandler<ActionEvent>() {
			  
	            @Override
	            public void handle(ActionEvent event) {
	            	tx.setText(wordCount.myWords());
	            }
	        });
		
		FlowPane layout = new FlowPane();
		layout.getChildren().add(button);
		layout.getChildren().add(tx);
		
		Scene scene = new Scene(layout, 500, 100);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
