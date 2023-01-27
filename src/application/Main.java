package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;



public class Main extends Application {
	
	
	
	private static Stage stage;
	private static Scene fxmlInfoScene;
	private static Scene fxmlSelectModelScene;
	private static Scene fxmlEnterDataScene;
	private static Scene fxmlModelAtmScene;
	private static Scene fxmlPopUp;
	private static int idModelAtm;

	
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		stage = primaryStage;
		
		Pane fxmlSelectModel = FXMLLoader.load(getClass().getResource("FXMLSelectModel.fxml"));
		fxmlSelectModelScene = new Scene(fxmlSelectModel,970,584);
		
//		
//		if(fXMLControllerSelectModel.loadModel()) {
//			Pane fxmlModelAtm = FXMLLoader.load(getClass().getResource("FXMLModelATM.fxml"));
//			fxmlModelAtmScene = new Scene(fxmlModelAtm,970,584);
//			stage.setScene(fxmlModelAtmScene);
//		} else {
//			Pane fxmlModelAtm = FXMLLoader.load(getClass().getResource("FXMLModelATM.fxml"));
//			fxmlModelAtmScene = new Scene(fxmlModelAtm,970,584);
//			stage.setScene(fxmlModelAtmScene);
//		}
		
		
		//Pane fxmlModelAtm = FXMLLoader.load(getClass().getResource("FXMLModelATM.fxml"));
		//fxmlModelAtmScene = new Scene(fxmlModelAtm,970,584);
		
		
		//Pane fxmlInfoNetwork = FXMLLoader.load(getClass().getResource("FXMLInfoNetwork.fxml"));
		//fxmlInfoScene =  new Scene(fxmlInfoNetwork,970,584);
		

		//Pane fxmlEnterData = FXMLLoader.load(getClass().getResource("FXMLEnterData.fxml"));
		//fxmlEnterDataScene = new Scene(fxmlEnterData,970,584);
		
		
		//Scene scene = new Scene(root,970,584);
		primaryStage.setScene(fxmlSelectModelScene);
		primaryStage.show();
	
		
	}
	
	public void start2(String tela) throws IOException{
		
		
		
		Pane fxmlEnterData = FXMLLoader.load(getClass().getResource(tela));
		fxmlEnterDataScene = new Scene(fxmlEnterData,970,584);
		stage.setScene(fxmlEnterDataScene);
		stage.show();
		
		
	}
	
    
//	public static int selectScreen(int idScreen) {
//		
//		int idModelSelected = 0;
//		FXMLControllerModelATM fXMLControllerModelATM = new FXMLControllerModelATM();
//
//		
//		if(idScreen == 1) {
//			stage.setScene(fxmlModelAtmScene);
//			
//			idModelSelected = 1;
//			
//		} else {
//			stage.setScene(fxmlModelAtmScene);
//			idModelSelected = 2;
//			
//		}
//		
//		return idModelSelected;
//	}


	public static void main(String[] args) {
		launch(args);
	}
}
