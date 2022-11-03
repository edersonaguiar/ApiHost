package Comunication;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ConfigMaquinaImpl {

	public static void main(String[] args) {
		
		JSONObject jSONObject;
		JSONParser parser = new JSONParser();
		
		ConfigMaquina configMaquina = new ConfigMaquina();
		
		try {
			
			jSONObject = (JSONObject) parser.parse(new FileReader("ArquivosConfigdados_atm.json"));
			
			configMaquina.setCode((String) jSONObject.get("code"));
			configMaquina.setMessage((String) jSONObject.get("message"));
			configMaquina.setTrx((String) jSONObject.get("trx"));
			configMaquina.setNetworkMask((String) jSONObject.get("networkMask"));
			configMaquina.setNetworkAddress((String) jSONObject.get("networkAddress"));
			configMaquina.setDefaultGateway((String) jSONObject.get("defaultGateway"));
			
			System.out.println("Retorno JAMNM = " + configMaquina.toString());
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			
		}
		
		
		
	}

}
