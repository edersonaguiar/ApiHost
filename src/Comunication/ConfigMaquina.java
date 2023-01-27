package Comunication;

public class ConfigMaquina {

	private static String code;
	private static String urlServer;
	private static String message;
	private static String trx;
	private static String time;
	private static String agency;
	private static String hostname;
	private static String xid;
	private static String networkMask;
	private static String networkAddress;
	private static String defaultGateway;
	private static String networkMaskJamNM;
	private static String networkAddressJamNM;
	private static String defaultGatewayJamNM;
	

	public static String getCode() {
		return code;
	}

	public static void setCode(String code) {
		ConfigMaquina.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		ConfigMaquina.message = message;
	}

	public String getTrx() {
		return trx;
	}

	public void setTrx(String trx) {
		ConfigMaquina.trx = trx;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		ConfigMaquina.time = time;
	}

	public static String getAgency() {
		return agency;
	}

	public static void setAgency(String agency) {
		ConfigMaquina.agency = agency;
	}

	public static String getHostname() {
		return hostname;
	}

	public static void setHostname(String hostname) {
		ConfigMaquina.hostname = hostname;
	}

	public static String getNetworkMask() {
		return networkMask;
	}

	public static void setNetworkMask(String networkMask) {
		ConfigMaquina.networkMask = networkMask;
	}

	public static String getNetworkAddress() {
		return networkAddress;
	}

	public static void setNetworkAddress(String networkAddress) {
		ConfigMaquina.networkAddress = networkAddress;
	}

	public static String getDefaultGateway() {
		return defaultGateway;
	}

	public static void setDefaultGateway(String defaultGateway) {
		ConfigMaquina.defaultGateway = defaultGateway;
	}

	public static String getUrlServer() {
		return urlServer;
	}

	public void setUrlServer(String urlServer) {
		ConfigMaquina.urlServer = urlServer;
	}

	@Override
	public String toString() {
		return "ConfigMaquina(" + "code=" + code + ", message= " + message + ", trx= " + trx + ", networkMask= "
				+ networkMask + ", networkAddress= " + networkAddress + ", defaultGateway=" + defaultGateway + "}";
	}

	public static String getXid() {
		return xid;
	}

	public static void setXid(String xid) {
		ConfigMaquina.xid = xid;
	}

	public static String getNetworkMaskJamNM() {
		return networkMaskJamNM;
	}

	public static void setNetworkMaskJamNM(String networkMaskJamNM) {
		ConfigMaquina.networkMaskJamNM = networkMaskJamNM;
	}

	public static String getNetworkAddressJamNM() {
		return networkAddressJamNM;
	}

	public static void setNetworkAddressJamNM(String networkAddressJamNM) {
		ConfigMaquina.networkAddressJamNM = networkAddressJamNM;
	}

	public static String getDefaultGatewayJamNM() {
		return defaultGatewayJamNM;
	}

	public static void setDefaultGatewayJamNM(String defaultGatewayJamNM) {
		ConfigMaquina.defaultGatewayJamNM = defaultGatewayJamNM;
	}

}
