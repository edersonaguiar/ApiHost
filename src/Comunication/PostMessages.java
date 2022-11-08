package Comunication;

import java.io.FileWriter;

public class PostMessages {

	ConfigMaquina configMaquina = new ConfigMaquina();

	private static String menssageCode0 = "OK";
	private static String menssageCode50 = "SERVIÇO DESABILITADO NO SERVIDOR";
	private static String menssageCode200 = "NÚMERO DA MÁQUINA NÃO LOCALIZADO";
	private static String menssageCode201 = "SEM DADOS DE REDE DO ATM";
	private static String menssageCode202 = "SEM AUTORIZAÇÃO PARA BAIXA DE IMAGEM DO ATM";
	private static String menssageCode210 = "AGÊNCIA INFORMADA É INVÁLIDA";
	private static String menssageCode211 = "HOSTNAME INFORMADO É INVÁLIDO";
	private static String menssageCode240 = "ERRO NO PROCESSAMENTO DA REQUISIÇÃO";
	private static String menssageDefault = "CODIGO DE RETORNO INVALIDO";

	public String replyMessages(int code) {

		switch (code) {
		case 0:
			configMaquina.setMessage(menssageCode0);
			break;
		case 50:
			configMaquina.setMessage(menssageCode50);
			break;
		case 200:
			configMaquina.setMessage(menssageCode200);
			break;
		case 201:
			configMaquina.setMessage(menssageCode201);
			break;
		case 202:
			configMaquina.setMessage(menssageCode202);
			break;
		case 210:
			configMaquina.setMessage(menssageCode210);
			break;
		case 211:
			configMaquina.setMessage(menssageCode211);
			break;
		case 240:
			configMaquina.setMessage(menssageCode240);
			break;
		default:
			configMaquina.setMessage(menssageDefault);
		}

		return configMaquina.getMessage();
	}

	public void logCode(int code) {

		FileWriter writeFile = null;

		String message = replyMessages(code);

		try {

			writeFile = new FileWriter("logCode.txt");
			writeFile.write(message);

			writeFile.close();

		} catch (Exception e) {

		}
	}
}
