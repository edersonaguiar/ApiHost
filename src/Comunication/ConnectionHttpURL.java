package Comunication;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONML;
import org.json.JSONObject;
import org.json.XML;
import org.openehr.am.serialize.XMLSerializer;
import com.mashape.unirest.http.*;

public class ConnectionHttpURL {

	private static final String FILE_JSON = "c:/Temp/Teste/infoAtm.txt";

	private static final ConfigMaquina ConfigMaquina = null;

	public static XMLSerializer xmlSerializer = new XMLSerializer();

	ConfigMaquina configMaquina = new ConfigMaquina(ConfigMaquina);
	

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

		//String url = "https://mockbin.org/bin/88a73107-8e14-41c1-9000-bf4e6d9c0332";

		String path = "c:/Temp/Teste/infoAtm.txt";
		String agency = manipuladorArquivo.leitor(path, 0);
		String hostname = manipuladorArquivo.leitor(path, 1);

		// System.out.println(agency + hostname);
		
		
		
		
		String jsonInputString = agency + hostname ;
		
		System.out.println(jsonInputString);
		
		
		
		//Unirest.setTimeouts(0, 10);
	    HttpResponse<String> responseServer = Unirest.post("http://10.243.151.130:9080/atmm_webservice_negocio/hostname/obter_dados_rede_atm/v1/json/post.nm")
	      .header("content-type", "application/json;charset=UTF-8;")
	      .header("content-language", "en-US")
	      .body("{\r\n    \"agency\": 3987,\r\n    \"hostname\": 7002\r\n}")
	      .asString();
		
		String url = "http://10.243.151.130:9080/atmm_webservice_negocio/hostname/obter_dados_rede_atm/v1/json/post.nm";
		
	    System.out.println(responseServer.getBody());

	  

		// URL urlConnection = new URL(null, "https://redmine.xxx.cz/time_entries.xml",
		// new sun.net.www.protocol.https.Handler());

		//HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();

		// HttpsURLConnection httpClient = (HttpsURLConnection) new
		// URL(url).openConnection();

		// add reuqest header
		//httpClient.setRequestMethod("POST");
		//httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");
		//httpClient.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		//httpClient.setRequestProperty("Accept", "application/json");
		//httpClient.setRequestProperty("Content-type", "application/json");
		//httpClient.setRequestProperty("text/html", "charset=UTF-8");
		//httpClient.addRequestProperty("agency", "3987");
		//httpClient.addRequestProperty("hostname", "7002");
		
		
		
		// Send post request
	//	httpClient.setDoOutput(true);
		//try (DataOutputStream wr = new DataOutputStream(httpClient.getOutputStream())) {
			//wr.writeBytes(jsonInputString);
			//wr.flush();
			
	//s	}
		
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
				ConvertJsonXml(responseServer.getBody().toString());
				
//				ConvertJsonTXT(jsonValueTxt);
//
//				logGenerator.generateLog(
//						"Code: " + configMaquina.getCode() + "\n" + "Message: " + configMaquina.getMessage());

				
	}

	public static String ConvertJsonXml(String JsonDados) {
		String xml = "";
		try {
			//JSONObject jsoObject = new JSONObject(configMaquinaList);
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

}
