package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;


public class BaiDuPoiPictureUtil {

	
	public static String getBaiDuPoiPicturePath(String url){
		
		
		try {
				URL urlUtil = new URL(url);
			
				HttpURLConnection connection = (HttpURLConnection) urlUtil.openConnection();
				connection.setConnectTimeout(30000);  
				connection.setReadTimeout(30000);  
				//JSON
				
				String line;   
		        StringBuffer stringBuffer = new StringBuffer();
		        
		        
		     	Reader reader = new InputStreamReader(connection.getInputStream(), "UTF-8");   

	            BufferedReader bufferedReader = new BufferedReader(reader);   
	            while ((line = bufferedReader.readLine()) != null) {   
	                stringBuffer.append(line);   
	                
	            }   
	 
	            String sb = stringBuffer.substring(0, stringBuffer.indexOf(" class=\"head-img\"")-1);
	   
	            
	            reader.close();
	            bufferedReader.close();
	            return sb.substring(sb.lastIndexOf("http"),sb.length());
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		
		
		
	}
	
}
