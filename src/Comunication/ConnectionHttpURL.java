package Comunication;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.JSONML;
import org.json.JSONObject;
import org.json.XML;
import org.openehr.am.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.mashape.unirest.http.*;

public class ConnectionHttpURL {

	private static final String FILE_JSON = "c:/Temp/Teste/infoAtm.txt";

	private static final ConfigMaquina ConfigMaquina = null;

	public static XMLSerializer xmlSerializer = new XMLSerializer();

	static ConfigMaquina configMaquina = new ConfigMaquina();

	JSONObject objetoJson = new JSONObject();

	ManipuladorArquivo manipuladorArquivo = new ManipuladorArquivo();

	LogGenerator logGenerator = new LogGenerator();

	public static void main(String[] args) throws Exception {

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

		String agencyNumber = configMaquina.getAgency();
		String hostnameNumber = configMaquina.getHostname();

		// Unirest.setTimeouts(0, 10);
		HttpResponse<String> responseServer = Unirest.post(
				"http://10.243.151.130:9080/atmm_webservice_negocio/hostname/obter_dados_rede_atm/v1/json/post.nm")
				.header("content-type", "application/json;charset=UTF-8;").header("content-language", "en-US")
				.body("{\r\n    \"agency\":" + agencyNumber + ",\r\n    \"hostname\":" + hostnameNumber + "\r\n}")
				.asString();

		String url = "http://10.243.151.130:9080/atmm_webservice_negocio/hostname/obter_dados_rede_atm/v1/json/post.nm";

		System.out.println(responseServer.getBody());

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

		String xmlString = "<?xml version=\"1.0\"?>" + "<paises>" + "<pais sigla=\"BR\">" + "<nome>Brasil</nome>"
				+ "<populacao>196655014</populacao>" + "</pais>" + "<pais sigla=\"AR\">" + "<nome>Argentina</nome>"
				+ "<populacao>40764561</populacao>" + "</pais>" + "</paises>";

		JSONObject paisesJson = XML.toJSONObject(xmlString);

		System.out.println(paisesJson.toString());

		JSONObject novoPaisesJSON = new JSONObject(responseServer.getBody());

		String xmlStr2 = XML.toString(novoPaisesJSON);

		System.out.println(xmlStr2);

		// usa try-catch para armazenar dados XML no arquivo.
		FileWriter file = new FileWriter("C:\\Temp\\Teste\\ArquivosConfigDados_atm.xml");

		// usa o método write() de File para gravar dados XML em XMLData.txt
		file.write(xmlStr2);
		file.flush();
		System.out.println("Seus dados XML foram gravados com sucesso em XMLData.xml");

		// fecha o FileWriter
		file.close();

		// ConvertJsonXml(xmlStr2);

//				ConvertJsonTXT(jsonValueTxt);
//
//				logGenerator.generateLog(
//						"Code: " + configMaquina.getCode() + "\n" + "Message: " + configMaquina.getMessage());

	}

	public static String ConvertJsonXml(String JsonDados) {
		String xml = "";
		try {
			// JSONObject jsoObject = new JSONObject(configMaquinaList);
			xml = xml + XML.toString(JsonDados);

			FileWriter writeFile = null;
			writeFile = new FileWriter("c:/Temp/Teste/ArquivosConfigDados_atm.xml");
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
		String OSDComputerName = "";
		String OSDBradescoIP = "";
		String OSDBradescoMascara = "";
		String OSDBradescoGateway = "";

		System.out.println("Root do elemento: " + doc.getDocumentElement().getNodeName());
		NodeList nList = doc.getElementsByTagName("parameters");

		System.out.println("----------------------------");
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			// System.out.println("\nElemento corrente :" + nNode.getNodeName());
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

				agencia = eElement.getElementsByTagName("OSDJuncao").item(0).getTextContent();
				OSDComputerName = eElement.getElementsByTagName("OSDComputerName").item(0).getTextContent();
				OSDBradescoIP = eElement.getElementsByTagName("OSDBradescoIP").item(0).getTextContent();
				OSDBradescoMascara = eElement.getElementsByTagName("OSDBradescoMascara").item(0).getTextContent();
				OSDBradescoGateway = eElement.getElementsByTagName("OSDBradescoGateway").item(0).getTextContent();
				agencia = agencia.substring(agencia.length() - 4);
				OSDComputerName = OSDComputerName.substring(OSDComputerName.length() - 4);

				configMaquina.setAgency(agencia);
				configMaquina.setHostname(OSDComputerName);

				System.out.println("AGENCIA: " + agencia);
				System.out.println("ATM: " + OSDComputerName);
				System.out.println("IP: " + OSDBradescoIP);
				System.out.println("MASCARA: " + OSDBradescoMascara);
				System.out.println("GATEWAY: " + OSDBradescoGateway);

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

					if (environment.equalsIgnoreCase("TU3")){
						url = eElement.getElementsByTagName("TU3").item(0).getTextContent();
					} else if (environment.equalsIgnoreCase("TU4")){
						url = eElement.getElementsByTagName("TU4").item(0).getTextContent();
					} else if (environment.equalsIgnoreCase("TUH")){
						url = eElement.getElementsByTagName("TUH").item(0).getTextContent();
					} else if (environment.equalsIgnoreCase("TH")){
						url = eElement.getElementsByTagName("TH").item(0).getTextContent();
					}
				}
			}
			System.out.println(url);
			configMaquina.setUrlServer(url);
		

	}

}
