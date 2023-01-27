package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class FXMLControllerSelectModel implements Initializable {

	// FXMLControllerModelATM fXMLControllerModelATM = new FXMLControllerModelATM();
	Modelo modelo = new Modelo();

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ImageView ImageView;

	@FXML
	private Label title;

	@FXML
	private Label subTitle;

	@FXML
	private RadioButton ncrSelect;

	@FXML
	private ToggleGroup group;

	@FXML
	private RadioButton dielboldSelect;

	@FXML
	private Button next;

	private static int idModelAtm = 0;
	private static RadioButton idSelectModel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		

		next.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
	        try {
	            if (event.getCode() == KeyCode.ENTER) {
	            	loadModel();
	            }
	        } catch (Exception ex) {
	        }
	    });
		
		
		File file = new File("c:/temp/bradeco-removebg.png");
		Image image = new Image(file.toURI().toString());
		ImageView.setImage(image);
		

		try {
			loadModel();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void loadModel() throws Exception {

		RadioButton radio = (RadioButton) group.getSelectedToggle();
		System.out.println(radio);
		
		setIdSelectModel(radio);

		if (getIdSelectModel() == ncrSelect) {
			setIdModelAtm(1);
		} else if (getIdSelectModel() == dielboldSelect) {
			setIdModelAtm(2);
		}

		Main main = new Main();
		main.start2("FXMLModelATM.fxml");

	}

	public static int getIdModelAtm() {
		return idModelAtm;
	}

	public static void setIdModelAtm(int idModelAtm) {
		FXMLControllerSelectModel.idModelAtm = idModelAtm;
	}

	public static RadioButton getIdSelectModel() {
		return idSelectModel;
	}

	public static void setIdSelectModel(RadioButton radio) {
		FXMLControllerSelectModel.idSelectModel = radio;
	}

}