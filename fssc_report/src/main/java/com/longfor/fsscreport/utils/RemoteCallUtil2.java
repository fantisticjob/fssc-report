package com.longfor.fsscreport.utils;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.druid.sql.ast.expr.SQLCaseExpr.Item;
import com.alibaba.fastjson.JSONObject;
import com.longfor.fsscreport.nc.entity.Items;


/**
 * nc远程调用工具2
 * @author chenziyao
 *
 */
@SuppressWarnings("all")
public class RemoteCallUtil2 {
	
	
	private  final    Logger log = LoggerFactory.getLogger(RemoteCallUtil2.class);
	
	private  JSONObject json = new JSONObject();
	
	public   JSONObject sendSyncRequest(Map<String,String> map) {
		GetProperties properties = new GetProperties();
	    HttpURLConnection httpConn = null;
	    BufferedReader reader = null;
	    log.info("远程连接:"+properties.getProperties("ncss"));
	    try {
	    	URL url = new URL(properties.getProperties("ncss"));
	        httpConn = (HttpURLConnection) url.openConnection();
	        httpConn.setRequestProperty("Content-Type","text/xml; charset=utf-8"); // 设置在header中
	        httpConn.setRequestMethod("POST");
	        httpConn.setDoOutput(true);
	        httpConn.setDoInput(true);
	        httpConn.connect();
	        OutputStream outputStream = httpConn.getOutputStream();
	        
	        String param= makeParameter( map);
	        outputStream.write(param.getBytes());
	        
	        // 获取服务器响应状态
	        int code = httpConn.getResponseCode();
	        String tempString = null;
	        StringBuilder retMsg = new StringBuilder();
		    log.info("远程调用响应码:"+code);
	        if (code == HttpURLConnection.HTTP_OK) {
	            reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(),"UTF-8"));
	            while ( (tempString = reader.readLine()) != null ) {
	                retMsg.append(tempString);
	            }
	            List<JSONObject> list = readStringXml(retMsg.toString(),map);
	            if(list.size() >0) {
	            	 json.put("data",list);
	            	 json.put("result", "nc取数成功");
	 	        	 json.put("status", "200");
	            }
	            if(map.get("type").equals("FRPlugin") &&  code==200){
	            	
	            	 json.put("result", "nc取数失败");
	 	        	 json.put("status", "0");
	            }
	            return json;
	        } else {
	        	json.put("result", "远程调用失败");
	        	json.put("status", "-2");
	        	log.info("远程调用失败");
	            return json;
	        }
	    } catch ( Exception e ) {
	    	log.error("远程调用异常"+e);
	        e.printStackTrace();
	        json.put("result", "远程调用异常");
	        return json;
	    } finally {
	    	if (httpConn!=null || null != reader) {
	            try {
	            	httpConn.disconnect();
	                reader.close();
	                reader = null;
	                httpConn = null;
	            } catch (IOException e) {
	            	
	            }
	        }
	    }
	}
	 
	
	private   String makeParameter(Map<String,String> map) {
		
		String xml="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ilh=\"http://report.hk.itf.nc/ILhReport\">"
				+ "		<soapenv:Header/>"
				+ "			<soapenv:Body>"
					+map.get("xml")
				+ "		</soapenv:Body>"
				+ "</soapenv:Envelope>";
		log.info("远程调用入口xml参数："+xml);
		
		return xml;
	}

    /**
     * 解析xml
     * @param iterator
     * @return
     */
    @Autowired
	public      List<JSONObject> readStringXml(String xml,Map<String,String> map) {
    	log.info("readStringXml入口参数："+xml);
        Document doc = null;
        List<JSONObject> list = new ArrayList<JSONObject>();
        try {
            doc = DocumentHelper.parseText(xml); // 将字符串转为soap形式的xml
            Element rootElt = doc.getRootElement(); // 获取根节点
            Iterator body = rootElt.elementIterator("Body"); // 获取根节点下的子节点head
            // 遍历head节点
            while (body.hasNext()) {				
            	rootElt = (Element) body.next();   //AuxiliaryBalanceDetailsResponse  
				Iterator resp = rootElt.elementIterator(map.get("type")+"Response"); // 获取子节点head下的子节点script
                // 遍历Response节点下的return节点
                while (resp.hasNext()) {
                	rootElt = (Element) resp.next();
                    Iterator returns = rootElt.elementIterator("return");
                    rootElt = (Element) returns.next();
					doc = DocumentHelper.parseText(rootElt.getData().toString());//得到纯xml				
					rootElt = doc.getRootElement(); // 获取xml根节点
			        Iterator array = rootElt.elementIterator("nc.itf.hk.report."+map.get("type")+"BackVo-array");
			        while (array.hasNext()) {								
			        	rootElt = (Element) array.next();                   
			        	Iterator iters = rootElt.elementIterator("nc.itf.hk.report."+map.get("type")+"BackVo"); // 获取子节点head下的子节点script
		                while (iters.hasNext()) {
		                	
		                	JSONObject object = new JSONObject();
		                    Element itemEle = (Element) iters.next();
		                    List elements = itemEle.elements();
		                    for (int i = 0; i < elements.size(); i++) {
		                    	Element ele=(Element)elements.get(i);
		                    	if("AuxiliaryBalanceDetails__AccassitemVoS".equals(ele.getName())) {
		                    		Element element2 = ele.element("item");
		                    		List list2 = ele.elements();
		                    		for (int j = 0; j < list2.size(); j++) {
		                    			Element object2 = (Element) list2.get(j);
		                    			JSONObject toObject = convertXmlStrToObject(Items.class,object2.asXML());
		                    			if(toObject.get("checktypecode") !=null && "KS".equals(toObject.get("checktypecode"))) {
		                    	    		object.put("kscode", toObject.get("checkvaluecode"));
		                    	    		object.put("ksname", toObject.get("checkvaluename"));
		                    	    	}
		                    	    	if(toObject.get("checktypecode") !=null && "XM".equals(toObject.get("checktypecode"))) {
		                    	    		object.put("xmcode", toObject.get("checkvaluecode"));
		                    	    		object.put("xmname", toObject.get("checkvaluename"));
		                    	    	}
		                    	    	if(toObject.get("checktypecode") !=null && "RY".equals(toObject.get("checktypecode"))) {
		                    	    		object.put("rycode", toObject.get("checkvaluecode"));
		                    	    		object.put("ryname", toObject.get("checkvaluename"));
		                    	    	}
		                    	    	if(toObject.get("checktypecode") !=null && "DJH".equals(toObject.get("checktypecode"))) {
		                    	    		object.put("djhcode", toObject.get("checkvaluecode"));
		                    	    		object.put("djhname", toObject.get("checkvaluename"));
		                    	    	}
									}
		                    	}
		                    	String amount = itemEle.elementTextTrim(ele.getName());
		                    	object.put(ele.getName(), amount);
							}
		                    list.add(object);
		                  
		                }
			        }
                }
            }
            if(list.size()==0) {
            	json.put("result", "数据为空！");
            	json.put("status", "0");
            }
        } catch (Exception e) {
        	json.put("result", "解析实时Nc返回xml异常");
        	json.put("status", "-2");
            e.printStackTrace();
            log.error("xml解析异常"+e);
        }
        log.info("readStringXml出口参数："+list.toString());
		return list;
    }
    
    /**
     * xml 转bean 公共方法
     * @param clazz
     * @param xmlStr
     * @return
     */
    public  JSONObject convertXmlStrToObject(Class clazz, String xmlStr) {  
    	JSONObject jsonObject = null;  
        
        try {  
            JAXBContext context = JAXBContext.newInstance(clazz);  
            // 进行将Xml转成对象的核心接口  
            Unmarshaller unmarshaller = context.createUnmarshaller();  
            StringReader sr = new StringReader(xmlStr);  
            Object xmlObject = unmarshaller.unmarshal(sr);  
            String jsonStr = JSONObject.toJSONString(xmlObject);
            jsonObject = JSONObject.parseObject(jsonStr);
            System.err.println(jsonObject.toJSONString());
        } catch (JAXBException e) {  
        	json.put("result", "xml转实体类异常");
        	json.put("status", "-2");
            e.printStackTrace();  
        }  
        return jsonObject;  
    }  
    
}
