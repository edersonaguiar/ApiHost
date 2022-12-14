package Comunication;

public class ConfigMaquina {

	private int code;
	private String urlServer;
	private String message;
	private String trx;
	private String time;
	private String agency;
	private String hostname;
	private String networkMask;
	private String networkAddress;
	private String defaultGateway;
	

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTrx() {
		return trx;
	}

	public void setTrx(String trx) {
		this.trx = trx;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getNetworkMask() {
		return networkMask;
	}

	public void setNetworkMask(String networkMask) {
		this.networkMask = networkMask;
	}

	public String getNetworkAddress() {
		return networkAddress;
	}

	public void setNetworkAddress(String networkAddress) {
		this.networkAddress = networkAddress;
	}

	public String getDefaultGateway() {
		return defaultGateway;
	}

	public void setDefaultGateway(String defaultGateway) {
		this.defaultGateway = defaultGateway;
	}

	public String getUrlServer() {
		return urlServer;
	}

	public void setUrlServer(String urlServer) {
		this.urlServer = urlServer;
	}

	@Override
	public String toString() {
		return "ConfigMaquina(" + "code=" + code + ", message= " + message + ", trx= " + trx + ", networkMask= "
				+ networkMask + ", networkAddress= " + networkAddress + ", defaultGateway=" + defaultGateway + "}";
	}

}
