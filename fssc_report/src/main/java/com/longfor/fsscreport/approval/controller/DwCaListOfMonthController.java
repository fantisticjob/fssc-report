package com.longfor.fsscreport.approval.controller;


import java.util.Date;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.longfor.fsscreport.approval.entity.RprtTaskYg;
import com.longfor.fsscreport.approval.mapper.CommonDao;
import com.longfor.fsscreport.approval.service.IDwCaListOfMonthService;
import com.longfor.fsscreport.approval.service.IRprtTaskYgService;
import com.longfor.fsscreport.reconciliation.entity.StoredProcedure;
import com.longfor.fsscreport.utils.GetProperties;
import com.longfor.fsscreport.utils.StringUtil;

/**
 * <p>
 *	月度关帐清单 前端控制器
 *	controller
 * </p>
 *
 * @author chenziyao
 * @since 2020-07-30
 */
@RestController
@RequestMapping("/dwcalistofmonth")
public class DwCaListOfMonthController {


    private final Logger log = LoggerFactory.getLogger(DwCaListOfMonthController.class);
    
    private static final  String STATUS="status";
	private static final  String RESULT="result";
	private GetProperties properties = new  GetProperties();
	
	@Autowired
	private CommonDao dao;
	
	@Autowired
	private IRprtTaskYgService ygservice;

	@Autowired
	private RestTemplate template;
	
	@Autowired
	private IDwCaListOfMonthService service;
	
	
	/**
     * 更新月度关帐汇总表
     * @param accountId
     * @param month
     * @return
     */
    @CrossOrigin(origins = "*",maxAge = 3600)
    @PostMapping(value="/updataDwCaListOfMonth")
    @ResponseBody
    public JSONObject updataDwCaListOfMonth(String company,String month) {
    	JSONObject json = new JSONObject();
    	log.info("更新月度关帐汇总表:company:{}", company);
    	log.info("更新月度关帐汇总表:month:{}",month);
    	if(company ==null || month == null ) {
    		json.put(STATUS, -1);
    		return json;
    	}
    	try{
    		StoredProcedure sp = new StoredProcedure();
    		sp.setP_date(month);
    		sp.setP_company(company);
    		service.updataDwCaListOfMonth(sp);
    		json.put(STATUS, "200");
    	} catch (Exception e) {
    		log.error("更新月度关帐汇总表异常{}",e);
    		json.put(STATUS, "-2");
    	}
    	return json;
    }
    
    
    /**
	 * 打开远程连接，请求数据
	 * @param map 请求参数
	 * @return dealid、codeCr（机构编码例如03046）、startDate、endDate
	 */
    
    @CrossOrigin(origins = "*",maxAge = 3600)
    @PostMapping(value="/getRpaData")
    public synchronized JSONObject  openHttp(String codeCr,String month) {
    	
    	JSONObject json = new JSONObject();
    	log.info("openHttp():codeCr{}",codeCr);
    	log.info("openHttp():month{}",month);
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
			Map<String, String> map = new HashMap<String,String>();
			String uuid = StringUtil.getUUID();
			
			String xhx="-";
	    	if(codeCr.contains(xhx)) {
	    		int indexOf = codeCr.indexOf(xhx);
	    		map.put("codeCr",codeCr.substring(0,indexOf));
	    	}else {
	    		map.put("codeCr",codeCr);
	    	}
			map.put("dealid",uuid);
			map.put("startDate",month+"-01");
			map.put("endDate",month+"-"+dayOfMonth);
			HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(map);
			ResponseEntity<String> response = template.exchange(properties.getProperties("ydgzdj"), HttpMethod.POST, requestEntity, String.class);
			String responseBody = response.getBody();
			log.info("月度关账单家取数返回值{}",responseBody);
			if(responseBody!=null && !"".equals(responseBody)) {
				JSONObject  resp= JSON.parseObject(responseBody);
				if(resp.getString("RESULT").equals("开始单家跑月度关账数据")  ) {
					RprtTaskYg taskYg = new RprtTaskYg();
					taskYg.setAccountId(codeCr);
					taskYg.setDealid(uuid);
					taskYg.setYearmonth(month);
					taskYg.setOpraterFlag("2");
					taskYg.setFinishStatus("2");
					taskYg.setUpdateFlag("1");//汇总表标识
					taskYg.setBegintime(new Date());
					if(ygservice.save(taskYg)) {
						json.put("id", taskYg.getDealid());
						json.put(STATUS, "200");
						json.put(RESULT, resp.getString("RESULT"));
					}else {
						json.put(STATUS, "0");
						json.put(RESULT, "RPA调用失败失败");
					}
			    	return json;
				}
			}
		} catch (Exception e) {
			json.put(STATUS, "-2");
			json.put(RESULT, "rpa-task月度关帐单家调用异常");
			log.error("rpa-task月度关帐单家调用异常{}",e);
		}
        return json;
    }
    /**
     * 
     * @param map 
     * @return
     */
    @CrossOrigin(origins = "*",maxAge = 3600)
    @PostMapping(value="/taskTimed")
	@ResponseBody
    public JSONObject taskTimed(String uuid) {
    	
    	JSONObject json = new JSONObject();
    	log.info("taskTimed():uuid{}",uuid);
    	if( StringUtils.isBlank(uuid)) {
    		
    		json.put(STATUS,"-1");
    		json.put(RESULT,"非法参数");
			return json;
    	}
    	
    	try {
    		QueryWrapper<RprtTaskYg> wrapper = new QueryWrapper<RprtTaskYg>();
    		wrapper.eq("DEALID", uuid);
    		int Count=0;
    		for (; ;) {
    			RprtTaskYg rprtTask = ygservice.getOne(wrapper);
        		String string = rprtTask.getFinishStatus();
        		String updateFlag = rprtTask.getUpdateFlag();
        		if(string.equals("1") && "2".equals(updateFlag)) {
        			json.put(STATUS,"200");
        			json.put(RESULT,"明细获取完毕");
        			
        			return json;
        		}else if(string.equals("1") && "1".equals(updateFlag)) {
        			
        			json.put(STATUS,"200");
        			json.put(RESULT,"汇总表获取完毕");
        			return json;
        		}
        		if(string.equals("3") && "1".equals(updateFlag)) {
        			
        			json.put(STATUS,"200");
        			json.put(RESULT,"汇总表跑数异常结束！");
        			return json;
        		}else if(string.equals("3") && "2".equals(updateFlag)) {
        			
        			json.put(STATUS,"200");
        			json.put(RESULT,"明细跑数异常结束！");
        			return json;
        		}
        		Count++;
        		log.info("taskTimed()第:"+Count+"次查询未反馈");
        		Thread.sleep(5000);
        		
    		}
		} catch (Exception e) {
			json.put(RESULT,"银行余额校验跑数异常");
			log.error("taskTimed():异常{}",e);
		}
		return json;
    }
    
}
