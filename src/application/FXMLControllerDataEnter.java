package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JFormattedTextField;

import org.json.XML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import Comunication.ConfigMaquina;
import Comunication.ConnectionHttpURL;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import application.MaskTextField;

public class FXMLControllerDataEnter implements Initializable {

	@FXML
	private ImageView ImageView;

	@FXML
	private Label title;

	@FXML
	private Button back;

	@FXML
	private Button next;

	@FXML
	private Label numberBdn;

	@FXML
	private Label juncao;

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
	private MaskTextField bdnCamp;

	@FXML
	private MaskTextField xidCamp;

	@FXML
	private MaskTextField juncaoCamp;

	@FXML
	private MaskTextField ipNetworkAddressDig;

	@FXML
	private MaskTextField ipNetworkMaskDig;

	@FXML
	private MaskTextField ipNetworkGatewayDig;

	@FXML
    void loadModel() throws Exception {
		
		if(juncaoCamp.getText().equals("") || bdnCamp.getText().equals("") || xidCamp.getText().equals("")
			|| ipNetworkAddressDig.getText().equals("") || ipNetworkMaskDig.getText().equals("")
			|| ipNetworkGatewayDig.getText().equals("")) {
			
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("ALERTA");
			alert.setHeaderText("POR FAVOR, PREENCHA TODOS OS CAMPOS!");
			alert.show();
		} else {
		
		ConfigMaquina.setAgency(juncaoCamp.getText());
		ConfigMaquina.setHostname(bdnCamp.getText());
		ConfigMaquina.setXid(xidCamp.getText());
		ConfigMaquina.setNetworkAddress(ipNetworkAddressDig.getText());
		ConfigMaquina.setNetworkMask(ipNetworkMaskDig.getText());
		ConfigMaquina.setDefaultGateway(ipNetworkGatewayDig.getText());
		
		String str = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
	            "<parameters>\n" +
	            "        <OSDJuncao>"+ ConfigMaquina.getAgency() +"</OSDJuncao>\n" +
	            "        <OSDComputerName>"+ ConfigMaquina.getHostname()+"</OSDComputerName>\n" +
	            "        <OSDBradescoIP>"+ ConfigMaquina.getNetworkAddress()+"</OSDBradescoIP>\n" +
	            "        <OSDBradescoMascara>"+ ConfigMaquina.getNetworkMask()+"</OSDBradescoMascara>\n" +
	            "        <OSDBradescoGateway>"+ ConfigMaquina.getDefaultGateway()+"</OSDBradescoGateway>\n" +
	            "        <OSDBradescoXID>"+ ConfigMaquina.getXid()+"</OSDBradescoXID>\n" +
	            "        <OSDBradescoBDNModelo>"+ ConfigMaquina.getModelATM()+"</OSDBradescoBDNModelo>\n" +
	          
	           
	            "</parameters>";

		  // String to XML Document
        Document document = convertStringToXml(str);

        // XML Document to String
        String xml = convertXmlToString(document);
        System.out.println(xml);
		
		Main main = new Main();
        ConnectionHttpURL.main();
        

		//FXMLControllerDataBase.readPropertyXML(true);
		
		if (ConfigMaquina.getCode().equals("0")) {
			main.start2("FXMLInfoNetwork.fxml");
		} else {

			main.start2("FXMLPopUP.fxml");
		}
	}
 }

	@FXML
	public void previousScreen() throws Exception {

		Main main = new Main();
		main.start2("FXMLModelATM.fxml");

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		ipNetworkMaskDig.setMask("NNNNNNNNNNNNNNN");
		bdnCamp.setMask("AAAAAA");
		xidCamp.setMask("AAAAAAAA");
		juncaoCamp.setMask("AAAA");
		ipNetworkAddressDig.setMask("NNNNNNNNNNNNNNN");
		ipNetworkGatewayDig.setMask("NNNNNNNNNNNNNNN");

		next.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
			try {
				if (event.getCode() == KeyCode.ENTER) {
					loadModel();
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

	}

	private static String convertXmlToString(Document doc) {
		DOMSource domSource = new DOMSource(doc);
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = null;
		try {
			transformer = tf.newTransformer();
			transformer.transform(domSource, result);
		} catch (TransformerException e) {
			throw new RuntimeException(e);
		}
		return writer.toString();
	}

	public static Document convertStringToXml(String xmlString) {
		String xml = "";

//		String inputData = "{\"OSDJuncao\":" + ConfigMaquina.getAgency() + "," + "\"osdComputerName\":\""
//				+ ConfigMaquina.getHostname() + "\"," + "\"osdBradescoXID\":\"" + ConfigMaquina.getXid() + "\","
//				+ "\"osdBradescoIP\":\"" + ConfigMaquina.getNetworkAddress() + "\"," + "\"osdBradescoMascara\":\""
//				+ ConfigMaquina.getNetworkMask() + "\"," + "\"osdBradescoGateway\":\""
//				+ ConfigMaquina.getDefaultGateway() + "\"}";

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {

			// optional, but recommended
			// process XML securely, avoid attacks like XML External Entities (XXE)
			dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

			DocumentBuilder builder = dbf.newDocumentBuilder();

			Document doc = builder.parse(new InputSource(new StringReader(xmlString)));

			FileWriter writeFile = null;
			writeFile = new FileWriter("c:/Temp/Teste/htalog.xml");
			writeFile.write(xmlString);

			writeFile.close();

			return doc;

		} catch (ParserConfigurationException | IOException | SAXException e) {
			throw new RuntimeException(e);
		}

	}
	
	
}
