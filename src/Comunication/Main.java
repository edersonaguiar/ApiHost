package Comunication;

import java.io.FileWriter;
import org.json.simple.JSONObject;

public class Main {

	public static void main(String[] args) {
		
		FileWriter writeFile = null;
		
		JSONObject objetoJson = new JSONObject();
		
		objetoJson.put("maquina", "5034");
		objetoJson.put("agencia", "3895");
		
		try {
			writeFile = new FileWriter("ArquivosConfigdados_atm.json");
			writeFile.write(objetoJson.toJSONString());
			writeFile.close();

		} catch (Exception e) {
			
		}
		
		System.out.println(objetoJson.toJSONString());
				
	}

}
