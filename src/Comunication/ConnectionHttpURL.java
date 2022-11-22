package Comunication;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import org.json.XML;
import org.openehr.am.serialize.XMLSerializer;
import javax.swing.JOptionPane;

public class ConnectionHttpURL {

	public static XMLSerializer xmlSerializer = new XMLSerializer();

	ConfigMaquina configMaquina = new ConfigMaquina();

	JSONObject objetoJson = new JSONObject();
	
	PostMessages postMessages = new PostMessages();
	
	ManipuladorArquivo manipuladorArquivo = new ManipuladorArquivo();
	

	public static void main(String[] args) throws Exception {
		
		ConnectionHttpURL obj = new ConnectionHttpURL();

		/*System.out.println("Testing 1 - Send Http GET request");
		obj.sendGet();
*/
		System.out.println("Testing Post JanNM - Send Http POST request");
		obj.sendPost();
		

	}

/*	private void sendGet() throws Exception {

//    	HttpResponse<String> responsee = Unirest.post("http://mockbin.org/bin/88a73107-8e14-41c1-9000-bf4e6d9c0332?foo=bar&foo=baz")
//    			  .header("cookie", "foo=bar; bar=baz")
//    			  .body("foo=bar&bar=baz")
//    			  .asString();

//        String url = "https://www.google.com/search?q=mkyong";
		String url = "https://mockbin.org/bin/88a73107-8e14-41c1-9000-bf4e6d9c0332";

		HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();

		// optional default is GET
		httpClient.setRequestMethod("GET");

		// add request header
		httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");
		
		if(httpClient.getResponseCode() == 200) {
			System.out.println(postMessages.replyMessages(httpClient.getResponseCode()));
			postMessages.logCode(httpClient.getResponseCode());
		}
		
			
		configMaquina.setCode(httpClient.getResponseCode());
		configMaquina.setTrx(httpClient.getHeaderField("trx"));
		configMaquina.setNetworkMask(httpClient.getHeaderField("networkmask"));
		configMaquina.setMessage(httpClient.getHeaderField("message"));
		configMaquina.setNetworkAddress(httpClient.getHeaderField("networkaddress"));
		configMaquina.setDefaultGateway(httpClient.getHeaderField("defaultgateway"));

		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("networkMask : " + configMaquina.getNetworkMask());
		System.out.println("networkAddress : " + configMaquina.getNetworkAddress());
		System.out.println("defaultGateway : " + configMaquina.getDefaultGateway());
		System.out.println("code : " + configMaquina.getCode());
		System.out.println("message : " + configMaquina.getMessage());
		System.out.println("trx : " + configMaquina.getTrx());

		try (BufferedReader in = new BufferedReader(new InputStreamReader(httpClient.getInputStream()))) {

			StringBuilder response = new StringBuilder();
			String line;

			while ((line = in.readLine()) != null) {
				response.append(line);
			}

			// print result
			System.out.println(response.toString());

		}

	}*/

	private void sendPost() throws Exception {

		// url is missing?
		// String url = "https://selfsolve.apple.com/wcResults.do";
		
		String url = JOptionPane.showInputDialog("URL: ");
		
		//String url = "https://mockbin.org/bin/88a73107-8e14-41c1-9000-bf4e6d9c0332";
		
		//URL urlConnection = new URL(null, "https://redmine.xxx.cz/time_entries.xml", new sun.net.www.protocol.https.Handler());

		HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();


		//HttpsURLConnection httpClient = (HttpsURLConnection) new URL(url).openConnection();

		// add reuqest header
		httpClient.setRequestMethod("POST");
		httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");
		httpClient.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		httpClient.setRequestProperty("Content-type", "application/json");;
		httpClient.setRequestProperty("text/html", "charset=UTF-8");
		
		
		String urlParameters = "88a73107-8e14-41c1-9000-bf4e6d9c0332";

		
		
		// Send post request
		httpClient.setDoOutput(true);
		try (DataOutputStream wr = new DataOutputStream(httpClient.getOutputStream())) {
			wr.writeBytes(urlParameters);
			wr.flush();
		}
		
		if(httpClient.getResponseCode() != 0) {
			System.out.println(postMessages.replyMessages(httpClient.getResponseCode()));
			postMessages.logCode(httpClient.getResponseCode());
		
		}
		int responseCode = httpClient.getResponseCode();

		
		configMaquina.setCode(httpClient.getResponseCode());
		configMaquina.setUrlServer("DEV");
		configMaquina.setTrx(httpClient.getHeaderField("trx"));
		configMaquina.setNetworkMask(httpClient.getHeaderField("networkmask"));
		configMaquina.setMessage(httpClient.getHeaderField("message"));
		configMaquina.setNetworkAddress(httpClient.getHeaderField("networkaddress"));
		configMaquina.setDefaultGateway(httpClient.getHeaderField("defaultgateway"));

		
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("URL_SERVER : " + configMaquina.getUrlServer());
		
		System.out.println("networkMask : " + configMaquina.getNetworkMask());
		System.out.println("networkAddress : " + configMaquina.getNetworkAddress());
		System.out.println("defaultGateway : " + configMaquina.getDefaultGateway());
		System.out.println("code : " + configMaquina.getCode());
		System.out.println("message : " + configMaquina.getMessage());
		System.out.println("trx : " + configMaquina.getTrx());

		String jsonValueTxt = "{\"code\":" + configMaquina.getCode() + "," + "\"networkMask\":\""
				+ configMaquina.getNetworkMask() + "\"," + "\"trx\":\"" + configMaquina.getTrx() + "\","
				+ "\"message\":\"" + configMaquina.getMessage() + "\"," + "\"networkAddress\":\""
				+ configMaquina.getNetworkAddress() + "\"," + "\"defaultGateway\":\""
				+ configMaquina.getDefaultGateway() + "\"," + "\"URL_SERVER\":\""
				+ configMaquina.getUrlServer() + "\"}";
		
		String jsonValueXml = "{\"code\":" + configMaquina.getCode() + "," + "\"networkMask\":\""
				+ configMaquina.getNetworkMask() + "\"," + "\"trx\":\"" + configMaquina.getTrx() + "\","
				+ "\"message\":\"" + configMaquina.getMessage() + "\"," + "\"networkAddress\":\""
				+ configMaquina.getNetworkAddress() + "\"," + "\"defaultGateway\":\""
				+ configMaquina.getDefaultGateway() + "\"," + "\"URL_SERVER\":\""
				+ configMaquina.getUrlServer() + "\"}";

		try (BufferedReader in = new BufferedReader(new InputStreamReader(httpClient.getInputStream()))) {

			String line;
			StringBuilder response = new StringBuilder();

			while ((line = in.readLine()) != null) {
				response.append(line);
			}

			// print result
			System.out.println(response.toString());
			ConvertJsonXml(jsonValueXml);
			ConvertJsonTXT(jsonValueTxt);
			
			String path = "infoAtm.txt";
			manipuladorArquivo.leitor(path);

		}

	}

	private static String ConvertJsonXml(String jsonValueXml) {
		String xml = "";
		try {
			JSONObject jsoObject = new JSONObject(jsonValueXml);
			xml = xml + XML.toString(jsoObject);

			FileWriter writeFile = null;
			writeFile = new FileWriter("ArquivosConfigDados_atm.xml");
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
			writeFile = new FileWriter("ArquivosConfigDados.txt");
			writeFile.write(txt);

			writeFile.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	
}
