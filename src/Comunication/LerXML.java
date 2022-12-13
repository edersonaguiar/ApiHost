package Comunication;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

public class LerXML {

	public static void main(String[] args) throws Exception{	
		lerXML();		
	}
	
	private static void lerXML() throws Exception{
		File fXmlFile = new File("htalog.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		
		System.out.println("Root do elemento: " + doc.getDocumentElement().getNodeName());
		NodeList nList = doc.getElementsByTagName("parameters");
		
		System.out.println("----------------------------");
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			//System.out.println("\nElemento corrente :" + nNode.getNodeName());
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				
				
				String agencia = eElement.getElementsByTagName("OSDJuncao").item(0).getTextContent();
				String OSDComputerName = eElement.getElementsByTagName("OSDComputerName").item(0).getTextContent();
				String OSDBradescoIP = eElement.getElementsByTagName("OSDBradescoIP").item(0).getTextContent();
				String OSDBradescoMascara = eElement.getElementsByTagName("OSDBradescoMascara").item(0).getTextContent();
				String OSDBradescoGateway = eElement.getElementsByTagName("OSDBradescoGateway").item(0).getTextContent();
				agencia = agencia.substring(agencia.length()-4);
				OSDComputerName = OSDComputerName.substring(OSDComputerName.length()-4);
				
				
				
				
				System.out.println("AGENCIA: " + agencia);
				System.out.println("ATM: " + OSDComputerName);
				System.out.println("IP: " + OSDBradescoIP);
				System.out.println("MASCARA: " + OSDBradescoMascara);
				System.out.println("GATEWAY: " + OSDBradescoGateway);
			}
		}
	}
}