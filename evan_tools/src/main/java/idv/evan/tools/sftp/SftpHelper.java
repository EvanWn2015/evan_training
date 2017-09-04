package idv.evan.tools.sftp;

import java.io.File;
import java.io.IOException;

import com.google.common.base.Strings;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import idv.evan.tools.zip.ZipHelper;

public class SftpHelper {

	private String userName;
	private String password;
	private String host;
	private String port;
	private String channelType;
	private String connectTimeout = "0";
	private boolean isBuild = false;

	private Session session;

	private SftpHelper() {

	}

	public static SftpHelper newSftpHelper() {
		return new SftpHelper();
	}

	public SftpHelper withUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public SftpHelper withPassword(String password) {
		this.password = password;
		return this;
	}

	public SftpHelper withHost(String host) {
		this.host = host;
		return this;
	}

	public SftpHelper withChannelType(String channelType) {
		this.channelType = channelType;
		return this;
	}

	public SftpHelper withPort(String port) {
		this.port = port;
		return this;
	}
	
	public SftpHelper withConnectTimeout (String connectTimeout) {
		this.connectTimeout = connectTimeout;
		return this;
	}
	
	public SftpHelper withParameters (SftpParameter parameter) {
		this.userName = parameter.getUserName();
		this.password = parameter.getPassword();
		this.host = parameter.getHost();
		this.port = parameter.getPort();
		this.channelType = parameter.getChannelType();
		this.connectTimeout = parameter.getConnectTimeout();
		return this;
	}

	public void disconnectSession() {
		this.session.disconnect();
		this.isBuild = false;
	}
	
	public boolean isSessionConnect() {
		return this.session.isConnected();
	}
	
	public SftpHelper build() throws JSchException, NullPointerException {
		if (!checkParameters()) {
			throw new NullPointerException("Please confirm the parameters !!");
		} else {
			this.session = new JSch().getSession(this.userName, this.host, Integer.parseInt(this.port));
			this.session.setConfig("StrictHostKeyChecking", "no");
			this.session.setPassword(this.password);
			this.isBuild = true;
		}
		return this;
	}

	private boolean checkParameters() {
		String[] parameters = new String[] { this.userName, this.password, this.host, this.port, this.channelType };
		for (String s : parameters) {
			if (Strings.isNullOrEmpty(s)) {
				return false;
			}
		}
		for(String s: new String[]{this.port, this.connectTimeout}) {
			if (!s.matches("-?\\d+(\\d+)?")) {
				return false;
			}
		}
		return true;
	}

	public void uploadFile(String localPath, String remotePath) throws JSchException {
		Channel channel = getChannel();
		try {
			ChannelSftp sftp = (ChannelSftp) channel;
			sftp.put(localPath, remotePath); // upload
			sftp.exit();
			channel.disconnect();
		} catch (SftpException e) {
			System.err.printf("ERROR : %s", e.getMessage());
		}
	}
	
	public void uploadFiles(String[] localPaths, String remotePath) throws JSchException {
		Channel channel = getChannel();
		try {
			ChannelSftp sftp = (ChannelSftp) channel;
			for (String path: localPaths) {
				sftp.put(path, remotePath); // upload
			}
			sftp.exit();
			channel.disconnect();
		} catch (SftpException e) {
			System.err.printf("ERROR : %s", e.getMessage());
		}
	}

	public void downloadFile(String remotePath, String localPath) throws JSchException {
		Channel channel = getChannel();
		try {
			ChannelSftp sftp = (ChannelSftp) channel;
			sftp.get(remotePath, localPath); // download
			sftp.exit();
			channel.disconnect();
		} catch (SftpException e) {
			System.err.printf("ERROR : %s", e.getMessage());
		}
	}
	
	public void downloadFilesToZip(String[] remotePaths, String localPath, String zipName) throws JSchException {
		String tmpDir = System.getProperty("java.io.tmpdir");
		String[] tmpFiles = new String[remotePaths.length];
		Channel channel = getChannel();
		try {
			ChannelSftp sftp = (ChannelSftp) channel;
			int index = 0;
			for (String path: remotePaths) {
				sftp.get(path, tmpDir); // download
				tmpFiles[index] = tmpDir + path.substring(path.lastIndexOf("/") + 1 ,path.length()); 
				index++;
			}
			sftp.exit();
			channel.disconnect();
		
			ZipHelper zHelper = ZipHelper.newZipHelper()
					.withOutputZipFilePath(localPath)
					.withZipFileName(zipName)
					.withInputFilePathArray(tmpFiles)
					.build();
			zHelper.writeZip();
			zHelper.clear();
			
			for (String tmpPath : tmpFiles) {
				new File(tmpPath).delete();
			}
		} catch (IOException  | SftpException e) {
			System.err.printf("ERROR : %s", e.getMessage());
		}
	}

	public void removeFile(String removeFilePath) throws JSchException {
		Channel channel = getChannel();
		try {
			ChannelSftp sftp = (ChannelSftp) channel;
			sftp.rm(removeFilePath); // delete
			sftp.exit();
			channel.disconnect();
		} catch (SftpException e) {
			System.err.printf("ERROR : %s", e.getMessage());
		}
	}
	
	public void removeFiles(String[] removeFilePaths) throws JSchException {
		Channel channel = getChannel();
		try {
			ChannelSftp sftp = (ChannelSftp) channel;
			for(String path: removeFilePaths) {
				sftp.rm(path); // delete	
			}
			sftp.exit();
			channel.disconnect();
		} catch (SftpException e) {
			System.err.printf("ERROR : %s", e.getMessage());
		}
	}

	private Channel getChannel() throws JSchException {
		if (!isBuild) {
			throw new JSchException("",new RuntimeException("this Instance has not been established yet !"));
		}
		if (!this.session.isConnected()) {
			this.session.connect();
		}
		Channel channel = this.session.openChannel(this.channelType); // Default value : "sftp"
		channel.connect();
		return channel;
	}
}
