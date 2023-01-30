package Comunication;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.http.HttpException;
import org.json.JSONML;
import org.json.JSONObject;
import org.json.XML;
import org.openehr.am.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.mashape.unirest.http.*;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.codehaus.jackson.JsonNode;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.utils.*;
import com.mashape.unirest.http.Unirest;

import application.FXMLControllerSelectModel;
import application.Main;

public class ConnectionHttpURL extends ConfigMaquina {

	private static final String FILE_JSON = "c:/Temp/Teste/infoAtm.txt";

	// private static final ConfigMaquina ConfigMaquina = null;

	public static XMLSerializer xmlSerializer = new XMLSerializer();

	static ConfigMaquina ConfigMaquina = new ConfigMaquina();

	JSONObject objetoJson = new JSONObject();

	ManipuladorArquivo manipuladorArquivo = new ManipuladorArquivo();

	LogGenerator logGenerator = new LogGenerator();

	private String url;

	// private String response;

	public static void main() throws Exception {

		ConnectionHttpURL obj = new ConnectionHttpURL();

		System.out.println("Testing Post JanNM - Send Http POST request");
		obj.sendPost();

	}

	private void sendPost() throws Exception {

		// url is missing?
		// String url = "https://selfsolve.apple.com/wcResults.do";

		// String url = JOptionPane.showInputDialog("URL: ");

		// String url = "https://mockbin.org/bin/88a73107-8e14-41c1-9000-bf4e6d9c0332";

		// String path = "c:/Temp/Teste/infoAtm.txt";
		// String agency = manipuladorArquivo.leitor(path, 0);
		// String hostname = manipuladorArquivo.leitor(path, 1);

		// System.out.println(agency + hostname);

		readPropertyXML(true);
		checkEnvironment(true);

		String agencyNumber = ConfigMaquina.getAgency();
		String hostnameNumber = ConfigMaquina.getHostname();
		url = ConfigMaquina.getUrlServer();

		System.out.println(url);
		System.out.println(agencyNumber);
		System.out.println(hostnameNumber);

//		JOptionPane.showConfirmDialog(null, "Erro ao baixar os dados de rede do ATM \n Deseja continuar?");
//		
//		JOptionPane.showMessageDialog(null, "Dados de Rede Atualizados\n"
//				+ "Network Address: 10.255.225.48\n"
//				+ "Network Mask: 255.255.0.0\n"
//				+ "Default Gateway: 142.72.112.41");

		// Unirest.setTimeouts(0, 3);

		try {
			HttpResponse<String> response = Unirest.post(url).header("content-type", "application/json;charset=UTF-8")
					.header("content-language", "en-US")
					.body("{\r\n  \"agency\": " + agencyNumber + ",\r\n  \"hostname\": " + hostnameNumber + "\r\n}")
					.asString();

			String bodyResponse = removeAcentos(response.getBody());
			if (response.getStatus() == 404) {
				ConfigMaquina.setCode("404");
				System.out.println(response.getStatus() + "ERRO DE COMUNICAÇÃO");
				
				ConvertJsonXml("{" + "parameters" + ":" +"{" +
					  "code"+":"+ "404"+","+
					  "message"+":"+ "ERRO DE COMUNICACAO" +
					"}" + "}");
				readPropertyXMLJamNMCode(true);


			} else {

				ConvertJsonXml("{" + "parameters" + ":" + bodyResponse +

						"}");
				readPropertyXMLJamNMCode(true);

			}

		} catch (Exception e) {

			readPropertyXMLJamNMCode(true);

		}

		// Document document = convertStringToXml(response.getBody());

		// String xml = convertXmlToString(document);

//		
//		JSONObject jsoObject = new JSONObject(response.getBody());
//
//		 Document document = convertStringToXml(jsoObject.toString());
//
//	        // XML Document to String
//	        String xml = convertXmlToString(document);
//	        System.out.println(xml);
//	        

		// ConfigMaquina.setTrx(response.getStatusText());

		// responseServer.getBody();

		// URL urlConnection = new URL(null, "https://redmine.xxx.cz/time_entries.xml",
		// new sun.net.www.protocol.https.Handler());

		// HttpURLConnection httpClient = (HttpURLConnection) new
		// URL(url).openConnection();

		// HttpsURLConnection httpClient = (HttpsURLConnection) new
		// URL(url).openConnection();

		// add reuqest header
		// httpClient.setRequestMethod("POST");
		// httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");
		// httpClient.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		// httpClient.setRequestProperty("Accept", "application/json");
		// httpClient.setRequestProperty("Content-type", "application/json");
		// httpClient.setRequestProperty("text/html", "charset=UTF-8");
		// httpClient.addRequestProperty("agency", "3987");
		// httpClient.addRequestProperty("hostname", "7002");

		// Send post request
		// httpClient.setDoOutput(true);
		// try (DataOutputStream wr = new
		// DataOutputStream(httpClient.getOutputStream())) {
		// wr.writeBytes(jsonInputString);
		// wr.flush();

		// s }

//		HttpRequest request = HttpRequest.newBuilder()
//				  .uri(URI.create(url))
//				  .POST(HttpRequest.BodyPublishers.ofString(jsonInputString))
//				  .build();
//
//		//int responseCode = httpClient.getResponseCode();
//
//		
//
//			System.out.println("\nSending 'POST' request to URL : " + url);
//			System.out.println("URL_SERVER : " + configMaquina.getUrlServer());
//
//			System.out.println("networkMask : " + configMaquina.getNetworkMask());
//			System.out.println("networkAddress : " + configMaquina.getNetworkAddress());
//			System.out.println("defaultGateway : " + configMaquina.getDefaultGateway());
//			System.out.println("code : " + configMaquina.getCode());
//			System.out.println("message : " + configMaquina.getMessage());
//			System.out.println("trx : " + configMaquina.getTrx());
//
//			String jsonValueTxt = "{\"networkMask\":" + configMaquina.getNetworkMask() + "," + "\"networkAddress\":\""
//					+ configMaquina.getNetworkAddress() + "\"," + "\"defaultGateway\":\""
//					+ configMaquina.getDefaultGateway() + "\"}";
//
//			String jsonValueXml = "{\"code\":" + configMaquina.getCode() + "," + "\"networkMask\":\""
//					+ configMaquina.getNetworkMask() + "\"," + "\"trx\":\"" + configMaquina.getTrx() + "\","
//					+ "\"message\":\"" + configMaquina.getMessage() + "\"," + "\"networkAddress\":\""
//					+ configMaquina.getNetworkAddress() + "\"," + "\"defaultGateway\":\""
//					+ configMaquina.getDefaultGateway() + "\"," + "\"Environment\":\"" + configMaquina.getUrlServer()
//					+ "\"}";

		// print result

		// onvertJsonXml(xmlStr2);

//				ConvertJsonTXT(jsonValueTxt);
//
//				logGenerator.generateLog(
//						"Code: " + configMaquina.getCode() + "\n" + "Message: " + configMaquina.getMessage());

	}

	public static String ConvertJsonXml(String JsonDados) {

		String xml = "";

		try {

			JSONObject jsoObject = new JSONObject(JsonDados);
			xml = xml + XML.toString(jsoObject);

			FileWriter writeFile = null;
			writeFile = new FileWriter("c:/Temp/Teste/DadosJamNM.xml");
			writeFile.write(xml);

			writeFile.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		xml = xml + "";
		return xml;
	}

	private void ConvertJsonTXT(String jsonValueTxt) {

		String txt = jsonValueTxt;

		try {
			JSONObject jsoObject = new JSONObject(jsonValueTxt);

			FileWriter writeFile = null;
			writeFile = new FileWriter("c:/Temp/Teste/ArquivosConfigDados.txt");
			writeFile.write(txt);

			writeFile.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public static void readPropertyXML(Boolean execut) throws Exception {
		File fXmlFile = new File("C:\\Temp\\Teste\\htalog.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		String agencia = "";
		String osdComputerName = "";
		String osdBradescoXID = "";
		String osdBradescoIP = "";
		String osdBradescoMascara = "";
		String osdBradescoGateway = "";

		System.out.println("Root do elemento: " + doc.getDocumentElement().getNodeName());
		NodeList nList = doc.getElementsByTagName("parameters");

		System.out.println("----------------------------");
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			// System.out.println("\nElemento corrente :" + nNode.getNodeName());
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

				agencia = eElement.getElementsByTagName("OSDJuncao").item(0).getTextContent();
				osdComputerName = eElement.getElementsByTagName("OSDComputerName").item(0).getTextContent();
				osdBradescoXID = eElement.getElementsByTagName("OSDBradescoXID").item(0).getTextContent();
				osdBradescoIP = eElement.getElementsByTagName("OSDBradescoIP").item(0).getTextContent();
				osdBradescoMascara = eElement.getElementsByTagName("OSDBradescoMascara").item(0).getTextContent();
				osdBradescoGateway = eElement.getElementsByTagName("OSDBradescoGateway").item(0).getTextContent();
				//agencia = agencia.substring(agencia.length() - 4);
				//osdComputerName = osdComputerName.substring(osdComputerName.length() - 4);

				ConfigMaquina.setAgency(agencia);
				ConfigMaquina.setHostname(osdComputerName);
				ConfigMaquina.setXid(osdBradescoXID);
				ConfigMaquina.setNetworkAddress(osdBradescoIP);
				ConfigMaquina.setNetworkMask(osdBradescoMascara);
				ConfigMaquina.setDefaultGateway(osdBradescoGateway);

				System.out.println("AGENCIA: " + agencia);
				System.out.println("ATM: " + osdComputerName);
				System.out.println("IP: " + osdBradescoIP);
				System.out.println("MASCARA: " + osdBradescoMascara);
				System.out.println("GATEWAY: " + osdBradescoGateway);

			}
		}

	}

	public static void checkEnvironment(Boolean execut) throws Exception {
		File fXmlFile = new File("C:\\Temp\\Teste\\apiConfig.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		String environment = "";
		String url = "";

		System.out.println("Root do elemento: " + doc.getDocumentElement().getNodeName());
		NodeList nList = doc.getElementsByTagName("SERVERS");

		System.out.println("----------------------------");
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			// System.out.println("\nElemento corrente :" + nNode.getNodeName());
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

				environment = eElement.getElementsByTagName("ENVIRONMENT").item(0).getTextContent();

			}
		}

		NodeList sList = doc.getElementsByTagName("SERVERS");

		for (int temp = 0; temp < sList.getLength(); temp++) {
			Node nNode = sList.item(temp);
			// System.out.println("\nElemento corrente :" + nNode.getNodeName());
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

				if (environment.equalsIgnoreCase("TU3")) {
					url = eElement.getElementsByTagName("TU3").item(0).getTextContent();
				} else if (environment.equalsIgnoreCase("TU4")) {
					url = eElement.getElementsByTagName("TU4").item(0).getTextContent();
				} else if (environment.equalsIgnoreCase("TUH")) {
					url = eElement.getElementsByTagName("TUH").item(0).getTextContent();
				} else if (environment.equalsIgnoreCase("TH")) {
					url = eElement.getElementsByTagName("TH").item(0).getTextContent();
				}
			}
		}
		// System.out.println(url);
		ConfigMaquina.setUrlServer(url);

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

	private static Document convertStringToXml(String xmlString) {
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
			writeFile = new FileWriter("c:/Temp/Teste/htalog2.xml");
			writeFile.write(xmlString);

			writeFile.close();

			return doc;

		} catch (ParserConfigurationException | IOException | SAXException e) {
			throw new RuntimeException(e);
		}

	}

	public static void readPropertyXMLJamNM(Boolean execut) throws Exception {
		File fXmlFile = new File("C:\\Temp\\Teste\\DadosJamNM.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);

		String osdBradescoIP = "";
		String osdBradescoMascara = "";
		String osdBradescoGateway = "";
		String code = "";
		String osdBradescoMessage = "";

		System.out.println("Root do elemento: " + doc.getDocumentElement().getNodeName());
		NodeList nList = doc.getElementsByTagName("parameters");

		System.out.println("----------------------------");
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			// System.out.println("\nElemento corrente :" + nNode.getNodeName());
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

				osdBradescoIP = eElement.getElementsByTagName("networkAddress").item(0).getTextContent();
				osdBradescoMascara = eElement.getElementsByTagName("networkMask").item(0).getTextContent();
				osdBradescoGateway = eElement.getElementsByTagName("defaultGateway").item(0).getTextContent();
				osdBradescoMessage = eElement.getElementsByTagName("message").item(0).getTextContent();
				code = eElement.getElementsByTagName("code").item(0).getTextContent();

				ConfigMaquina.setNetworkAddressJamNM(osdBradescoIP);
				ConfigMaquina.setNetworkMaskJamNM(osdBradescoMascara);
				ConfigMaquina.setDefaultGatewayJamNM(osdBradescoGateway);
				ConfigMaquina.setMessage(osdBradescoMessage);
				ConfigMaquina.setCode(code);

				// is.setEncoding("UTF-8"); -> This line causes error! Content is not allowed in
				// prolog

				System.out.println("IP: " + osdBradescoIP);
				System.out.println("MASCARA: " + osdBradescoMascara);
				System.out.println("GATEWAY: " + osdBradescoGateway);

			}
		}

	}

	public static void readPropertyXMLJamNMCode(Boolean execut) throws Exception {
		File fXmlFile = new File("C:\\Temp\\Teste\\DadosJamNM.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);

		String osdBradescoIP = "";
		String osdBradescoMascara = "";
		String osdBradescoGateway = "";
		String code = "";
		String osdBradescoMessage = "";

		System.out.println("Root do elemento: " + doc.getDocumentElement().getNodeName());
		NodeList nList = doc.getElementsByTagName("parameters");

		System.out.println("----------------------------");
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			// System.out.println("\nElemento corrente :" + nNode.getNodeName());
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

				osdBradescoMessage = eElement.getElementsByTagName("message").item(0).getTextContent();
				code = eElement.getElementsByTagName("code").item(0).getTextContent();

				ConfigMaquina.setMessage(osdBradescoMessage);
				ConfigMaquina.setCode(code);

				// is.setEncoding("UTF-8"); -> This line causes error! Content is not allowed in
				// prolog

				System.out.println("IP: " + osdBradescoIP);
				System.out.println("MASCARA: " + osdBradescoMascara);
				System.out.println("GATEWAY: " + osdBradescoGateway);

			}
		}

	}

	public static String removeAcentos(String texto) {
		String acentos[][] = new String[][] { { "á", "a" }, { "Á", "A" }, { "à", "a" }, { "À", "A" }, { "â", "a" },
				{ "Â", "A" }, { "ã", "a" }, { "Ã", "A" }, { "é", "e" }, { "É", "E" }, { "ê", "e" }, { "Ê", "E" },
				{ "í", "i" }, { "Í", "I" }, { "ó", "o" }, { "Ó", "O" }, { "ô", "o" }, { "Ô", "O" }, { "õ", "o" },
				{ "Õ", "O" }, { "ú", "u" }, { "Ú", "U" }, { "ç", "c" }, { "Ç", "C" } };

		for (int i = 0; i < acentos.length; i++)
			texto = texto.replace(acentos[i][0], acentos[i][1]);

		return texto;
	}

}
