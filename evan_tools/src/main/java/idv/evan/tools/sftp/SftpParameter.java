package idv.evan.tools.sftp;

public class SftpParameter {

	private String userName;
	private String password;
	private String host;
	private String port;
	private String channelType;
	private String connectTimeout = "0";

	private SftpParameter() {
	}

	public static SftpParameter newSftpParameter() {
		return new SftpParameter();
	}

	public SftpParameter withUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public SftpParameter withPassword(String password) {
		this.password = password;
		return this;
	}

	public SftpParameter withHost(String host) {
		this.host = host;
		return this;
	}

	public SftpParameter withPort(String port) {
		this.port = port;
		return this;
	}

	public SftpParameter withChannelType(String channelType) {
		this.channelType = channelType;
		return this;
	}

	public SftpParameter withConnectTimeout(String connectTimeout) {
		this.connectTimeout = connectTimeout;
		return this;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getHost() {
		return host;
	}

	public String getPort() {
		return port;
	}

	public String getChannelType() {
		return channelType;
	}

	public String getConnectTimeout() {
		return connectTimeout;
	}

}
