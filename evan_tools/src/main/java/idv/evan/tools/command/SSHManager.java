package idv.evan.tools.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import com.google.common.base.Strings;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SSHManager {

	private String USER_NAME;
	private String CONNECTION_IP;
	private Integer PORT;
	private String PASSWORD;
	private Session SESSION;
	private Integer TIMEOUT = 6000;
	private String knownHostsFileName;
	private boolean IS_BUILD = false;

	private SSHManager() {
	}

	public static SSHManager newInstance() {
		return new SSHManager();
	}

	public SSHManager withUserName(String userName) {
		this.USER_NAME = userName;
		return this;
	}

	public SSHManager withPassword(String password) {
		this.PASSWORD = password;
		return this;
	}

	public SSHManager withConnectionIP(String connectionIP) {
		this.CONNECTION_IP = connectionIP;
		return this;
	}

	public SSHManager withKnownHostsFileName(String fileName) {
		this.knownHostsFileName = fileName;
		return this;
	}

	public SSHManager withConnectionPort(Integer connectionPort) {
		this.PORT = connectionPort.intValue();
		return this;
	}

	public SSHManager withTimeOut(Integer timeOut) {
		this.TIMEOUT = timeOut.intValue();
		return this;
	}

	public SSHManager build() {
		Object[] verifiedValues = new Object[] { this.USER_NAME, this.PASSWORD, this.CONNECTION_IP, this.PORT };
		verifiedValues = Arrays.stream(verifiedValues).filter(s -> s == null).peek(System.out::println)
				.toArray(size -> new Object[size]);
		this.IS_BUILD = verifiedValues.length == 0;
		connect();
		return this;
	}

	private void connect() {
		JSch jsch = new JSch();
		try {
			if (!Strings.isNullOrEmpty(this.knownHostsFileName)) {
				jsch.setKnownHosts(this.knownHostsFileName);
			}
			this.SESSION = jsch.getSession(this.USER_NAME, this.CONNECTION_IP, this.PORT);
			this.SESSION.setPassword(this.PASSWORD);
			this.SESSION.setConfig("StrictHostKeyChecking", "on");
			this.SESSION.connect(this.TIMEOUT);
		} catch (JSchException e) {
			System.err.printf("ERROR: %s", e.getMessage());
		}
	}

	public void sendCommand(String command) throws JSchException {

		if (this.IS_BUILD) {
			Channel channel = this.SESSION.openChannel("exec");
			((ChannelExec) channel).setCommand(command);
			channel.connect();
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(channel.getInputStream()));
				String line = null;
				while ((line = br.readLine()) != null) {
					System.out.printf("%s%n", line);
				}
				System.out.println(line);
				channel.disconnect();
			} catch (IOException e) {
				System.err.printf("ERROR: %s", e.getMessage());
			} finally {
				this.IS_BUILD = false;
				this.SESSION.disconnect();
			}
		} else {
			throw new JSchException("is Not Built yet!");
		}
	}

}
