package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		//Pane root = FXMLLoader.load(getClass().getResource("FXMLInfoNetwork.fxml"));
		Pane root = FXMLLoader.load(getClass().getResource("FXMLSelectModel.fxml"));
		//Pane root = FXMLLoader.load(getClass().getResource("FXMLModelATM.fxml"));

		//Scene scene = new Scene(root,600,440);
		Scene scene = new Scene(root,943,600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	

	public static void main(String[] args) {
		launch(args);
	}
}
