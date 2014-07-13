package com.mendwe.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class MendWeServiceImpl implements MendWeServiceStd {

	@Override
	public boolean uploadFile() {
		String server = "mayrit.herobo.com";
		int port = 21;
		String user = "a1839336";
		String pass = "mayank@42";

		FTPClient ftpClient = new FTPClient();
		try {

			ftpClient.connect(server, port);
			ftpClient.login(user, pass);
			ftpClient.enterLocalPassiveMode();

			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

			// APPROACH #1: uploads first file using an InputStream
			File firstLocalFile = new File(
					"C:/Users/Mayank Dwivedi/Desktop/downloadc.jpg");

			String firstRemoteFile = "/public_html/images/downloadc.jpg";
			InputStream inputStream = new FileInputStream(firstLocalFile);

			System.out.println("Start uploading first file");
			boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
			// ftpClient.changeWorkingDirectory("/public_html");
			inputStream.close();
			if (done) {
				System.out.println("The first file is uploaded successfully.");
			}

		} catch (IOException ex) {
			System.out.println("Error: " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			try {
				if (ftpClient.isConnected()) {
					ftpClient.logout();
					ftpClient.disconnect();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return false;
	}

}
