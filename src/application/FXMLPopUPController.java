package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Comunication.ConfigMaquina;
import Comunication.ConnectionHttpURL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class FXMLPopUPController implements Initializable {

	private static int counter = 0;

	@FXML
	private ImageView ImageView;

	@FXML
	private Label title;

	@FXML
	private Label titulo;

	@FXML
	private Button yes;

	@FXML
	private Button no;

	@FXML
	void loadModel() throws Exception {
		Main main = new Main();
		ConnectionHttpURL.main();

		if (ConfigMaquina.getCode().equals("0")) {
			main.start2("FXMLInfoNetwork.fxml");
		}

		else if (counter <= 3) {
			main.start2("FXMLPopUP.fxml");

		} else if (counter > 3) {

			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("ALERTA");
			alert.setHeaderText("NÚMERO DE TENTATIVAS EXCEDIDAS!\n POR FAVOR, REFAÇA A OPERAÇÃO!");
			// alert.setContentText("POR FAVOR, REFAÇA A OPERAÇÃO!");
			alert.show();

			main.start2("FXMLSelectModel.fxml");

		}
	}

	@FXML
	void previousScreen() throws IOException {
		Main main = new Main();
		main.start2("FXMLDataEnter.fxml");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			ConnectionHttpURL.readPropertyXMLJamNMCode(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		String code = "";
		statusCode();
		counter++;

		yes.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
			try {
				if (event.getCode() == KeyCode.ENTER) {
					loadModel();
				}
			} catch (Exception ex) {
			}
		});

		no.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
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

	public String statusCode() {

		String code = "";

		if (ConfigMaquina.getCode().equals("0")) {
			Main main = new Main();

			try {
				main.start2("FXMLInfoNetwork.fxml");
				code = ConfigMaquina.getCode();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		} else if (ConfigMaquina.getCode().equals("10")) {
			title.setText("Requisição Inválida");
			code = ConfigMaquina.getCode();	
		} else if (ConfigMaquina.getCode().equals("50")) {
			title.setText("Serviço Desabilitado no Servidor");
			code = ConfigMaquina.getCode();
		} else if (ConfigMaquina.getCode().equals("200")) {
			title.setText("Número da Máquina não Localizado");
			code = ConfigMaquina.getCode();
		} else if (ConfigMaquina.getCode().equals("201")) {
			title.setText("Sem Dados de Rede do ATM");
			code = ConfigMaquina.getCode();
		} else if (ConfigMaquina.getCode().equals("202")) {
			title.setText("Sem Autorização para Baixa de Imagem do ATM");
			code = ConfigMaquina.getCode();
		} else if (ConfigMaquina.getCode().equals("210")) {
			title.setText("Agência Informada é Inválida");
			code = ConfigMaquina.getCode();
		} else if (ConfigMaquina.getCode().equals("211")) {
			title.setText("Hostname Informado é Inválido");
			code = ConfigMaquina.getCode();
		} else if (ConfigMaquina.getCode().equals("240")) {
			title.setText("Erro no Processamento da Requisição");
			code = ConfigMaquina.getCode();
		} else if (ConfigMaquina.getCode().equals("404")) {
			title.setText("Erro de Comunicação");
			code = ConfigMaquina.getCode();
		} 

		return code;
	}

}
