package com.longfor.fsscreport.utils;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
	


/**
 * oa消息推送工具类
 * @author chenziyao
 *
 */
public class PushOAMessage {
	
    /**
	 * 远程调用公共类
	 * @param uuid
	 * @return
	 */
    public static  String  doPostJson(String uuid)  {
    	
    	
    	GetProperties properties = new GetProperties();
    	// 测试
    	JSONObject object = new JSONObject();
		object.put("todoId",uuid);
		object.put("systemNo", "财务共享平台");//代办所属系统
	
		object.put("todoStatus", "1");
		
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(properties.getProperties("oaurl"));
            // 创建请求内容
            httpPost.setHeader("HTTP Method","POST");
            httpPost.setHeader("Connection","Keep-Alive");
            httpPost.setHeader("Content-Type","application/json;charset=utf-8");
            //测试
            //httpPost.setHeader("X-Gaia-Api-key", "0cda6d9e-7a91-4c1f-b8f3-d150aa60db89");
            //正式
            httpPost.setHeader("X-Gaia-Api-key", "67d5f63d-8278-46ae-abfd-65b98550ba3e");
            
            StringEntity entity = new StringEntity(object.toJSONString(), Charset.forName("UTF-8"));
            
            entity.setContentType("application/json;charset=utf-8");
            httpPost.setEntity(entity);

            // 执行http请求
            response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
           e.printStackTrace();
        } finally {
            try {
                if(response!=null){
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return resultString;
    }
    
}
