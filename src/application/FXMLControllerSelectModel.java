package application;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FXMLControllerSelectModel implements Initializable {

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
	private CheckBox ncrSelect;

	@FXML
	private CheckBox dielboldSelect;




	@Override
	public void initialize(URL location, ResourceBundle resources) {

		File file = new File("src/application/bradeco-removebg.png");
		Image image = new Image(file.toURI().toString());
		ImageView.setImage(image);
	    loadModel();

	}

	  @FXML
	    void loadModel() {

		  System.out.println(ncrSelect.selectedProperty().getValue());
		  System.out.println(dielboldSelect.selectedProperty().getValue());
		  
	    }
	
	
}