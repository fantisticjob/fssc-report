package com.longfor.fsscreport.approval.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.longfor.fsscreport.approval.entity.RprtTaskYg;
import com.longfor.fsscreport.approval.mapper.CommonDao;
import com.longfor.fsscreport.approval.service.IRprtTaskYgService;
import com.longfor.fsscreport.approval.util.DelMonthlyClosingUtils;
import com.longfor.fsscreport.approval.util.DetailKemu;
import com.longfor.fsscreport.utils.GetProperties;
import com.longfor.fsscreport.utils.StringUtil;

/**
 * <p>
 *	月度关帐清单 前端控制器
 * </p>
 *
 * @author chenziyao
 * @since 2020-07-30
 */
@RestController
@RequestMapping("/dwcalistofmonth")
public class DwCaListOfMonthController2 {

    private final Logger log = LoggerFactory.getLogger(getClass());

	private static final  String STATUS="status";
	private static final  String RESULT="result";
	private GetProperties properties = new  GetProperties();
	
	@Autowired
	private CommonDao dao;
	
	@Autowired
	private IRprtTaskYgService ygservice;

	@Autowired
	private DelMonthlyClosingUtils delMonthlyClosingUtils;
	
	@Autowired
	private RestTemplate template;
    
    /**
	 * 打开远程连接，请求数据
	 * @param map 请求参数
	 * @return dealid、codeCr（机构编码例如03046）、startDate、endDate
	 */
    @CrossOrigin(origins = "*",maxAge = 3600)
    @PostMapping(value = "/ydgzTion")
    public synchronized JSONObject  MonthlyClosingAccess(String codeCr,String month,String dealType,String detailKemu) {
    	
    	JSONObject json = new JSONObject();
    	log.info("ydgzTion():codeCr{}",codeCr);
    	log.info("ydgzTion():month{}",month);
    	log.info("ydgzTion():dealType{}",dealType);
    	log.info("ydgzTion():detailKemu{}",detailKemu);
    	
    	
		try {
			String sql="select count(*) from  RPRT_TASK_YG r "
					+ " where r.ACCOUNT_ID='"+codeCr+"' "
					+ " and ( r.ENDTIME_IRA is null or r.ENDTIME_FR is null )";
			
			if(dao.selectCount(sql) > 0) {
				log.info("当前账户正在执行，请勿重复操作");
				json.put(RESULT,"当前账户正在执行，请勿重复操作");
				return json;
			}
			
			
			int dayOfMonth = StringUtil.getLastDayOfMonth(month);
			Map<String, String> map = new HashMap<>();
			String uuid = StringUtil.getUUID();
			
			String orgCode ="";
			String xhx="-";
	    	if(codeCr.contains(xhx)) {
	    		int indexOf = codeCr.indexOf(xhx);
	    		orgCode = codeCr.substring(0,indexOf);
	    		map.put("codeCr",codeCr.substring(0,indexOf));
	    	}else {
	    		orgCode = codeCr;
	    		map.put("codeCr",codeCr);
	    	}
	    	
	    	// 月度关帐数据删除删除
	    	try {
	    		String type = "2";
	    		// type.equals("2") 月度关帐汇总  type.equals("3") 月度关帐明细
	    		if("1".contentEquals(dealType)) {
	    			type  = "2";
	    		} 
	    		if ("2".contentEquals(dealType)) {
	    			type  = "3";
	    		}	
	    		// 
	    		delMonthlyClosingUtils.deleteDateByType(type, orgCode, month, detailKemu);
	    		
	    	} catch (Exception e) {
	    		log.error("月度关帐删除异常{}",e.toString());
	    	}
	    	
	    	StringBuilder builder = new StringBuilder();
	    		
        	if(StringUtils.isNotBlank(detailKemu)) { //判断科目明细是否为空
        		
        		List<String> list = StringUtil.getStringList(detailKemu); //得到科目明细的数组
        		
        		for (int i = 0; i < list.size(); i++) {
        			String judge = DetailKemu.Judge(list.get(i)); //根据科目名称得到科目编号
            		
            		if(i==list.size()-1) {
            			builder.append(judge);
            			
            		}else {
            			builder.append(judge+",");
            			
            		}
    			}
        		
        	}
        	String string = builder.toString();
        	log.info("output:detailKemu{}",string);
        	
	    	map.put("detailKemu",builder.toString());
			map.put("dealid",uuid);
			map.put("dealType",dealType);
			map.put("startDate",month+"-01");
			map.put("endDate",month+"-"+dayOfMonth);
			
			RprtTaskYg taskYg = new RprtTaskYg();
			taskYg.setAccountId(codeCr);
			taskYg.setDealid(uuid);
			taskYg.setYearmonth(month);
			taskYg.setOpraterFlag("2");
			taskYg.setFinishStatus("2");
			taskYg.setBegintime(new Date());
			
			if("1".contentEquals(dealType)) {
				
				taskYg.setUpdateFlag("1");//汇总表标识
			}else {
				
				taskYg.setDetailKemu(detailKemu);
				taskYg.setUpdateFlag("2");//明细表标识
			}
			
			ygservice.save(taskYg);
			
			HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(map);
			ResponseEntity<String> response = template.exchange(
					properties.getProperties("ydgzdj"), HttpMethod.POST, requestEntity, String.class);
			String responseBody = response.getBody();
			log.info("开始获取明细：{}",responseBody);
			
			if(responseBody!=null && !"".equals(responseBody)) {
				JSONObject  resp= JSON.parseObject(responseBody);
				if(resp.getString("msg").equals("正在执行异步月度关账单家汇总或明细接口服务拆分")  ) {
				
					
					if("1".contentEquals(dealType) ) {
						
						json.put("id", taskYg.getDealid());
						json.put(STATUS, "200");
						json.put(RESULT, "开始获取汇总表");
					}else {
						
						json.put("id", taskYg.getDealid());
						json.put(STATUS, "200");
						json.put(RESULT, "开始获取明细表");
					}
					
			    	return json;
				}
			}
		} catch (Exception e) {
			json.put(STATUS, "-2");
			json.put(RESULT, "rpa-task获取明细调用异常");
			log.error("rpa-task获取明细调用异常{}",e.toString());
		}
        return json;
    }
    
}
