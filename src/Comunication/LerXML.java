package Comunication;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

public class LerXML {

	static ConfigMaquina configMaquina = new ConfigMaquina();
	
	public static void main(String[] args) throws Exception{	
		readPropertyXML(true);		
	}
	
	public static String readPropertyXML(Boolean execut) throws Exception{
		File fXmlFile = new File("htalog.xml");
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
			//System.out.println("\nElemento corrente :" + nNode.getNodeName());
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				
				
				 agencia = eElement.getElementsByTagName("OSDJuncao").item(0).getTextContent();
				 OSDComputerName = eElement.getElementsByTagName("OSDComputerName").item(0).getTextContent();
				 OSDBradescoIP = eElement.getElementsByTagName("OSDBradescoIP").item(0).getTextContent();
				 OSDBradescoMascara = eElement.getElementsByTagName("OSDBradescoMascara").item(0).getTextContent();
				 OSDBradescoGateway = eElement.getElementsByTagName("OSDBradescoGateway").item(0).getTextContent();
				 agencia = agencia.substring(agencia.length()-4);
				 OSDComputerName = OSDComputerName.substring(OSDComputerName.length()-4);
				
				configMaquina.setAgency(agencia);
				configMaquina.setHostname(OSDComputerName);
				
				
				System.out.println("AGENCIA: " + agencia);
				System.out.println("ATM: " + OSDComputerName);
				System.out.println("IP: " + OSDBradescoIP);
				System.out.println("MASCARA: " + OSDBradescoMascara);
				System.out.println("GATEWAY: " + OSDBradescoGateway);
				
			}
		}
		
		return agencia + OSDComputerName;

	}
}