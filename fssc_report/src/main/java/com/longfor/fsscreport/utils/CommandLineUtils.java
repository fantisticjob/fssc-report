package com.longfor.fsscreport.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;


/**
 * NC 存储过程调用（批量）
 */
public class CommandLineUtils {

	public static void exeCmd(String commandStr) {
	    BufferedReader br = null;
	    try {
	      Runtime.getRuntime().exec("cd /home/lhadmin/jarFile");
	      
	      Process p = Runtime.getRuntime().exec(commandStr);
	      br = new BufferedReader(new InputStreamReader(p.getInputStream()));
	      String line = null;
	      StringBuilder sb = new StringBuilder();
	      while ((line = br.readLine()) != null) {
	        sb.append(line + "\n");
	      }
	      System.out.println(sb.toString());
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    finally
	    {
	      if (br != null)
	      {
	        try {
	          br.close();
	        } catch (Exception e) {
	          e.printStackTrace();
	        }
	      }
	    }
	  }
	
}
