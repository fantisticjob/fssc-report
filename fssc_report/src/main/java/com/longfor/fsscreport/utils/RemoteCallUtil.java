package com.longfor.fsscreport.utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

/**
 * nc远程调用工具
 * @author chenziyao
 *
 */
@SuppressWarnings("all")
public class RemoteCallUtil {
	
	
	private  final     Logger log = LoggerFactory.getLogger(RemoteCallUtil.class);

	public    JSONObject sendSyncRequest(Map<String,Object> map) {
		GetProperties properties = new GetProperties();
		JSONObject json = new JSONObject();
	    HttpURLConnection httpConn;
	    BufferedReader reader = null;
	    log.info("远程连接:"+properties.getProperties("ncss"));
	    try {
	        httpConn = (HttpURLConnection) new URL(properties.getProperties("ncss")).openConnection();
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
	            return readStringXml(retMsg.toString());
	        } else {
	        	json.put("result", "远程调用失败");
	        	log.info("远程调用失败");
	            return json;
	        }
	    } catch ( Exception e ) {
	    	log.error("远程调用异常"+e);
	        e.printStackTrace();
	        json.put("result", "远程调用异常");
	        return json;
	    } finally {
	        if (null != reader) {
	            try {     
	                reader.close();
	            } catch (IOException e) {
	            	
	            }
	        }
	    }
	}
	private      String makeParameter(Map<String,Object> map) {
		
		//11042601040014925
		//农业银行11042601040014925
		//00003
		String xml="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ilh=\"http://report.hk.itf.nc/ILhReport\">"
				+ "		<soapenv:Header/>"
				+ "			<soapenv:Body>"
				+ "				<ilh:AuxiliaryBalanceDetails>"
				+ "					<string> <![CDATA[<AuxiliaryBalanceDetails>"
				+ "						<orgcode>"+map.get("orgCode")+"</orgcode>"
				+ "					    <accountcode>100202</accountcode>"
				+ "					   	<accassitems>"
				+ "					     	<accassitem>"
				+ "								<checktypecode>YHZH</checktypecode>"
				+ "								<checktypename>银行账户</checktypename>"
				+ "						        <checkvaluecode>"+map.get("accountId")+"</checkvaluecode>"
				+ "							    <checkvaluename>"+map.get("accountName")+"</checkvaluename>"
				+ " 						</accassitem>"
				+ "						</accassitems>"
				+ "					    <beginyear>"+map.get("startYear")+"</beginyear>"
				+ "					    <beginmonth>"+map.get("startMonth")+"</beginmonth>"
				+ "					    <endyear>"+map.get("endYear")+"</endyear>  "
				+ "					    <endmonth>"+map.get("endMonth")+"</endmonth>"
				+ "					</AuxiliaryBalanceDetails>]]></string>"
				+ "				</ilh:AuxiliaryBalanceDetails>"
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
	public    JSONObject readStringXml(String xml) {
    	log.info("readStringXml入口参数："+xml);
        Document doc = null;
        ArrayList<String> list = new ArrayList<String>();
        JSONObject json = new JSONObject();
        try {
            doc = DocumentHelper.parseText(xml); // 将字符串转为soap形式的xml
            Element rootElt = doc.getRootElement(); // 获取根节点
            Iterator body = rootElt.elementIterator("Body"); // 获取根节点下的子节点head
            // 遍历head节点
            while (body.hasNext()) {
            	rootElt = (Element) body.next();
                Iterator resp = rootElt.elementIterator("AuxiliaryBalanceDetailsResponse"); // 获取子节点head下的子节点script
                // 遍历Response节点下的return节点
                while (resp.hasNext()) {
                	rootElt = (Element) resp.next();
                    Iterator returns = rootElt.elementIterator("return");
                    rootElt = (Element) returns.next();
					doc = DocumentHelper.parseText(rootElt.getData().toString());//得到纯xml
					rootElt = doc.getRootElement(); // 获取xml根节点
			        Iterator array = rootElt.elementIterator("nc.itf.hk.report.AuxiliaryBalanceDetailsBackVo-array");
			        while (array.hasNext()) {
			        	rootElt = (Element) array.next();
			        	Iterator iters = rootElt.elementIterator("nc.itf.hk.report.AuxiliaryBalanceDetailsBackVo"); // 获取子节点head下的子节点script
		                while (iters.hasNext()) {
		                    Element itemEle = (Element) iters.next();
							String noteStr = itemEle.elementTextTrim("note");
							if (StringUtils.isNotBlank(noteStr) && "本年累计".equals(noteStr)) {
								String amount = itemEle.elementTextTrim("amount"); // 拿到head下的子节点script下的字节点username的值
								list.add(amount);
								BigDecimal twoDecimal = StringUtil.getTwoDecimal(new BigDecimal(list.get(list.size()-1)));
								try {
									String direction = itemEle.elementTextTrim("direction");
									if("1".equals(direction)) {
										// System.out.println("-----贷方为1----");
										twoDecimal = twoDecimal.multiply(new BigDecimal(-1));
									}
								} catch (Exception e) {
									System.out.println(e);
								}
								json.put("ncData",twoDecimal);
								break;
							}

		                }
			        }
                }
            }
        } catch (Exception e) {
        	json.put("result", "解析实时Nc返回xml异常");
            e.printStackTrace();
            log.error("xml解析异常"+e);
        }
        log.info("readStringXml出口参数："+json.toJSONString());
		return json;
    }
}
