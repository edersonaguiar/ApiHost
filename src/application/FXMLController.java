package application;

import java.net.URL;
import java.util.ResourceBundle;

import Comunication.ConfigMaquina;
import Comunication.ConnectionHttpURL;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FXMLController implements Initializable {
	
	public static ConfigMaquina configMaquina = new ConfigMaquina();
	public static ConnectionHttpURL connectionHttpURL = new ConnectionHttpURL();
	
	
    @FXML
    private Label networkAddressDig;

    @FXML
    private TextField ipNetworkAddressDig;

    @FXML
    private Label defaultGatewayDig;

    @FXML
    private TextField ipNetworkMaskDig;

    @FXML
    private TextField ipNetworkGatewayDig;

    @FXML
    private Label titulo;

    @FXML
    private Label networkMaskDig;

    @FXML
    private Label dadosDigitados;

    @FXML
    private Label networkAddressBase;

    @FXML
    private TextField ipNetworkAddressBase;

    @FXML
    private Label networkMaskBase;

    @FXML
    private Label defaultGatewayBase;

    @FXML
    private TextField ipNetworkMaskBase;

    @FXML
    private TextField ipNetworkGatewayBase;

    @FXML
    private Label dadosBaseDeDados;

    @FXML
    private Label numberBdn;

    @FXML
    private TextField bdnCamp;

    @FXML
    private Label xid;

    @FXML
    private TextField xidCamp;

    @FXML
    private Label juncao;

    @FXML
    private TextField juncaoCamp;
    

//	@FXML
//	private void acaoDoBotao(ActionEvent event) {
//		//System.out.println("OK");
//		ipNetworkAddress.setText("10.255.225.48");
//		ipNetworkMask.setText("255.255.0.0");
//		ipNetworkGateway.setText("142.72.112.41");
//	}
	
	@FXML
	private void executDados() throws Exception {
		
		ConnectionHttpURL.readPropertyXML(true);
		
		
		
		bdnCamp.setText(connectionHttpURL.getHostname());
		juncaoCamp.setText(connectionHttpURL.getAgency());
		xidCamp.setText(connectionHttpURL.getXid());
		
		ipNetworkAddressDig.setText(connectionHttpURL.getNetworkAddress());
		ipNetworkMaskDig.setText(connectionHttpURL.getNetworkMask());
		ipNetworkGatewayDig.setText(connectionHttpURL.getDefaultGateway());
	}
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			executDados();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
