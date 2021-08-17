package com.longfor.fsscreport.utils;


import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 获取properties文件的key
 * @author ChenZiyao
 */
public  class GetProperties {

	private  final String DEFAULT_ENCODING = "UTF-8";

	 /**
       * 根据key读取value
       * 
       * @param key      
       * @return
       * @return String  
       * @throws
       */
		public  String getProperties(String key) {
		    String value = "";
		    InputStream inputStream = null;
		    
		    try {
	            InputStream stream  = getClass().getClassLoader().getResourceAsStream("config.properties");

				inputStream = new BufferedInputStream(stream);
		      
				Properties prop = new Properties();
				prop.load(new InputStreamReader(inputStream, DEFAULT_ENCODING));
				value = prop.getProperty(key);
				
		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		    	try {
		    		inputStream.close();
		    		inputStream = null;
		    	} catch (Exception e) {
		    		
		    	}
		    }
		    return value;
	  }
}
