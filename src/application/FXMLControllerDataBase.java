package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Comunication.ConfigMaquina;
import Comunication.ConnectionHttpURL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class FXMLControllerDataBase implements Initializable {

	public static ConfigMaquina configMaquina = new ConfigMaquina();
	public static ConnectionHttpURL connectionHttpURL = new ConnectionHttpURL();

	@FXML
	private ImageView ImageView;

	@FXML
	private Label title;

	@FXML
	private Label numberBdn;

	@FXML
	private TextField bdnCamp;

	@FXML
	private TextField xidCamp;

	@FXML
	private Label juncao;

	@FXML
	private TextField juncaoCamp;

	@FXML
	private Label xid;

	@FXML
	private Label titulo;

	@FXML
	private Label networkAddressDig;

	@FXML
	private Label networkMaskDig;

	@FXML
	private Label defaultGatewayDig;

	@FXML
	private TextField ipNetworkAddressDig;

	@FXML
	private TextField ipNetworkMaskDig;

	@FXML
	private TextField ipNetworkGatewayDig;

	@FXML
	private Button finalize;

	@FXML
	private Button back;

	@FXML
	public void previousScreen() throws IOException {

		Main main = new Main();
		main.start2("FXMLDataEnter.fxml");

	}

	@FXML
	private void closeAction() {
		Stage stage = (Stage) finalize.getScene().getWindow(); // Obtendo a janela atual
		stage.close(); // Fechando o Stage
	}

	@FXML
	private void executDados() throws Exception {

		ConnectionHttpURL.readPropertyXML(true);
		ConnectionHttpURL.readPropertyXMLJamNM(true);

		if(ConfigMaquina.getDefaultGateway().equals(ConnectionHttpURL.getDefaultGatewayJamNM())
				&& ConfigMaquina.getNetworkAddress().equals(ConnectionHttpURL.getNetworkAddressJamNM())
				&& ConfigMaquina.getNetworkMask().equals(ConnectionHttpURL.getNetworkMaskJamNM())){
			
			ipNetworkAddressDig.setText(ConfigMaquina.getNetworkAddressJamNM());
			ipNetworkMaskDig.setText(ConfigMaquina.getNetworkMaskJamNM());
			ipNetworkGatewayDig.setText(ConfigMaquina.getDefaultGatewayJamNM());

			bdnCamp.setText(ConfigMaquina.getHostname());
			juncaoCamp.setText(ConfigMaquina.getAgency());
			xidCamp.setText(ConfigMaquina.getXid());
		} else {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("ALERTA");
			alert.setHeaderText("DADOS DE REDE ATUALIZADOS PELO O MAINFRAME!");
			//alert.setContentText("POR FAVOR, REFAÇA A OPERAÇÃO!");
			alert.show();
			
			ipNetworkAddressDig.setText(ConfigMaquina.getNetworkAddressJamNM());
			ipNetworkMaskDig.setText(ConfigMaquina.getNetworkMaskJamNM());
			ipNetworkGatewayDig.setText(ConfigMaquina.getDefaultGatewayJamNM());

			bdnCamp.setText(ConfigMaquina.getHostname());
			juncaoCamp.setText(ConfigMaquina.getAgency());
			xidCamp.setText(ConfigMaquina.getXid());
			
			String str = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
		            "<parameters>\n" +
		            "        <OSDJuncao>"+ ConfigMaquina.getAgency() +"</OSDJuncao>\n" +
		            "        <OSDComputerName>"+ ConfigMaquina.getHostname()+"</OSDComputerName>\n" +
		            "        <OSDBradescoIP>"+ ConfigMaquina.getNetworkAddressJamNM()+"</OSDBradescoIP>\n" +
		            "        <OSDBradescoMascara>"+ ConfigMaquina.getNetworkMaskJamNM()+"</OSDBradescoMascara>\n" +
		            "        <OSDBradescoGateway>"+ ConfigMaquina.getDefaultGatewayJamNM()+"</OSDBradescoGateway>\n" +
		            "        <OSDBradescoXID>"+ ConfigMaquina.getXid()+"</OSDBradescoXID>\n" +
		            "        <OSDBradescoBDNModelo>"+ ConfigMaquina.getModelATM()+"</OSDBradescoBDNModelo>\n" +
		          
		           
		            "</parameters>";
			
			FXMLControllerDataEnter fXMLControllerDataEnter = new FXMLControllerDataEnter();
			
			fXMLControllerDataEnter.convertStringToXml(str);
		
		}
	
		

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		

			finalize.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
				try {
					if (event.getCode() == KeyCode.ENTER) {
						closeAction();
					}
				} catch (Exception ex) {
				}
			});

			back.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
				try {
					if (event.getCode() == KeyCode.ENTER) {
						previousScreen();
					}
				} catch (Exception ex) {
				}
			});

			File file = new File("c:/temp/bradeco-removebg.png");
			Image image = new Image(file.toURI().toString());
			ImageView.setImage(image);

			try {
				executDados();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
}
