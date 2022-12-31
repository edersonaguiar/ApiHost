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

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FXMLControllerModelATM  implements Initializable{

    @FXML
    private ImageView ImageView;

    @FXML
    private Label title;

    @FXML
    private Label subTitle;
    
    @FXML
    private Button next;

    @FXML
    private Button back;

    @FXML
    private ListView<Modelo> listModel;
    

	private List<Modelo> modelos = new ArrayList<>();
	
	private ObservableList<Modelo> obsModelos;

	//private static ObservableList<Modelo> obsModelos
	
	
    @Override
	public void initialize(URL location, ResourceBundle resources) {

		File file = new File("src/application/bradeco-removebg.png");
		Image image = new Image(file.toURI().toString());
		ImageView.setImage(image);
		try {
			checkModelAtm(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
    
	
	
	// TODO Pass as parameter number 1 to NCR number 2 if diebold
		public  void checkModelAtm(int executModel) throws Exception {
			File fXmlFile = new File("C:\\Temp\\Teste\\ModelosBDN.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			System.out.println("Root do elemento: " + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("BDNModelos");

			System.out.println("----------------------------");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				// System.out.println("\nElemento corrente :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;

					if (executModel == 1) {
						for (int i = 0; i != eElement.getElementsByTagName("modeloBdnNCR").getLength(); i++) {
							Modelo modelosAtm = new Modelo(
									eElement.getElementsByTagName("modeloBdnNCR").item(i).getTextContent());
							modelos.add(modelosAtm);
							
						}
					} else if (executModel == 2) {
						for (int i = 0; i != eElement.getElementsByTagName("modeloBdnDiebold").getLength(); i++) {
							Modelo modelosAtm = new Modelo(
									eElement.getElementsByTagName("modeloBdnDiebold").item(i).getTextContent());
							modelos.add(modelosAtm);
							
						}
						
					}
					
					
					//ObservableList<Modelo> modeloConvert = FXCollections.observableList(modelos);
					
					
					
					
					obsModelos = FXCollections.observableArrayList(modelos);
					
					System.out.println(obsModelos);
					
					
					//listModel.setItems(observableArrayList);
										
					listModel.setItems(obsModelos);
				}
				
			}

		}


}
