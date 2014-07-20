package com.mendwe.service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import com.mendwe.utility.JedisFactory;

public class MendWeServiceImpl implements MendWeServiceStd {

	@Override
	public boolean uploadFile(CommonsMultipartFile multipartFile,String username) {
    	Jedis jedis = JedisFactory.getInstance().getJedisPool().getResource();
		Pipeline pipeline = jedis.pipelined();
		
		
		
		String server = "mayrit.herobo.com";
		int port = 21;
		String user = "a1839336";
		String pass = "mayank@42";
		boolean created=false;
		FTPClient ftpClient = new FTPClient();
		try {

			ftpClient.connect(server, port);
			ftpClient.login(user, pass);
			ftpClient.enterLocalPassiveMode();

			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			
			//To check whether a directory is there or not
			ftpClient.changeWorkingDirectory("/public_html/images/"+username);
		    int returnCode = ftpClient.getReplyCode();
		    if (returnCode == 550) {
		    	 created = ftpClient.makeDirectory("/public_html/images/"+username);
		    }

		    
			// APPROACH #1: uploads first file using an InputStream
			String firstLocalFile = multipartFile.getOriginalFilename();

			String firstRemoteFile = "/public_html/images/"+username+"/"+firstLocalFile;
			InputStream inputStream= multipartFile.getInputStream();
/*			multipartFile.transferTo(new File("E:/Test/Upload/" + firstLocalFile));
*//*			InputStream inputStream = new FileInputStream(file);
*/
			
			
			System.out.println("Start uploading first file");
			boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
			// ftpClient.changeWorkingDirectory("/public_html");
			inputStream.close();
			if (done) {
				jedis.sadd("IMAGE_ALL:"+username, firstLocalFile);
				pipeline.sync();
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
	
	
	@Override
	public boolean uploadPostFile(CommonsMultipartFile multipartFile,Long postId) {
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
			String firstLocalFile = multipartFile.getOriginalFilename();

			String firstRemoteFile = "/public_html/posts/"+firstLocalFile;
			InputStream inputStream= multipartFile.getInputStream();
/*			multipartFile.transferTo(new File("E:/Test/Upload/" + firstLocalFile));
*//*			InputStream inputStream = new FileInputStream(file);
*/
			
			
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
