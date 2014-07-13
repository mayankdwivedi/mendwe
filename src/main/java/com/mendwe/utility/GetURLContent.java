package com.mendwe.utility;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
 
public class GetURLContent {
	
	
	public static void main(String[] args) {
		Jedis jedis = JedisFactory.getInstance().getJedisPool().getResource();
		Pipeline pipeline = jedis.pipelined();

		URL url;
 
		try {
			// get URL content
			url = new URL("http://timesofindia.indiatimes.com/rssfeeds/1221656.cms");
			URLConnection conn = url.openConnection();
 
			// open the stream and put it into BufferedReader
			BufferedReader br = new BufferedReader(
                               new InputStreamReader(conn.getInputStream()));
 
			String inputLine;
 
			//save to this filename
			String fileName = "E:/workspace20140706/RSSFeeds/src/test.html";
			File file = new File(fileName);
 
			/*if (!file.exists()) {
				file.createNewFile();
			}*/
 
			//use FileWriter to write file
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
 
			while ((inputLine = br.readLine()) != null) {
				bw.write(inputLine);
			}
 
			bw.close();
			br.close();
 
			System.out.println("Done");
			
			String fileData=new String(Files.readAllBytes(Paths.get(fileName)));
			Map<String,String> newsMap=new HashMap<String,String>();
			
			Pattern p = Pattern.compile("<title>(.+?)</title>");
		    Matcher m = p.matcher(fileData);
		    
            Integer i=0;
		    while ((m.find())&&(i<7))
		    { 
		      // get the matching group
		      String codeGroup = m.group(1);
		       
		      // print the group
		      if(i>1){
		    	  
		    	  newsMap.put(Integer.toString(i),codeGroup);
		      System.out.println(codeGroup);
		      }
		      i++;
		    }
		 jedis.hmset("NEWS_ALL", newsMap);
		 pipeline.sync();
		 JedisFactory.getInstance().returnJedisResource(jedis);
		    
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
 
	}
}