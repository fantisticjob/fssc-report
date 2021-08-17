 package com.longfor.fsscreport.reconciliation.controller;


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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.longfor.fsscreport.approval.mapper.CommonDao;
import com.longfor.fsscreport.reconciliation.entity.RprtTask;
import com.longfor.fsscreport.reconciliation.service.IBaBalanceAdjustTotalService;
import com.longfor.fsscreport.reconciliation.service.IRprtTaskService;
import com.longfor.fsscreport.utils.GetProperties;
import com.longfor.fsscreport.utils.StringUtil;

/**
 * <p>
 * 银行余额  前端控制器
 * </p>
 *
 * @author chenziyao
 * @param <E>
 * @since 2020-07-28
 */
@RestController
@RequestMapping("/task")
public class RprtTaskController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private static final  String STATUS="status";
	private static final  String RESULT="result";
    private GetProperties properties = new  GetProperties();
    
	@Autowired
	private IRprtTaskService irs;
	
	@Autowired
	private IBaBalanceAdjustTotalService baBalanceAdjustTotalService;
	
	@Autowired
	private CommonDao dao;
	
	@Autowired
	private RestTemplate template;
	
	/**
	 * 银行余额删除
	 **/
	 private void deleteBankDepposite(String dataDate, String companyName, String accnum) {
		 // DELETE FROM RPRT_BANKDEPOSIT where DATA_DATE = #dataDate# and ORG_CODENM = #orgCodnm# and SINGLEFLAG = '1' 
		 // DELETE FROM RPRT_BANKDEPOSIT_HZ where DATA_DATE = #dataDate# and ORGNM = #orgCodnm# and SINGLEFLAG = '1' 
		 // DELETE FROM RPRT_BANKDEPOSIT_DETAILS where DATA_DATE = #dataDate#   
		 //  and( ACCNUM = #accnum# or  ZJ_ACCNUM= #zjAccnum# ) and SINGLEFLAG = '1' 
		 
		 if(companyName.contains("-")) {
	    		int indexOf = companyName.indexOf("-");
	    		companyName = companyName.substring(0,indexOf);	
		 }
		 log.info("companyName before{}" , companyName);		
	    		
		 StringBuilder sb = new StringBuilder();
		 sb.append("select orgname from ODS_ACCOUNTING_ORG_AREA where orgcode = '" +companyName + "'");
		 String orgName = dao.select(sb.toString());
		 
		 companyName = companyName + '-' + orgName;
		 log.info("companyName{}" , companyName);
				  
		 sb = new StringBuilder();
		 sb.append("delete from RPRT_BANKDEPOSIT where DATA_DATE ='" + dataDate + "' and ORG_CODENM='" + companyName +"' and SINGLEFLAG = '1'" );
		 dao.delete(sb.toString());
		 String string = sb.toString();
		 log.info("DELETE FROM RPRT_BANKDEPOSIT:{}",string);
		 
		 sb = new StringBuilder();
		 sb.append("delete from RPRT_BANKDEPOSIT_HZ where DATA_DATE ='" + dataDate + "' and ORGNM ='" + companyName +"' and SINGLEFLAG = '1' and NC_BANKACCNUM ='" +accnum +"'");
		 dao.delete(sb.toString());
		 String string2 = sb.toString();
		 log.info("DELETE FROM RPRT_BANKDEPOSIT_HZ:{}",string2);
		 
		 sb = new StringBuilder();
		 sb.append("delete from RPRT_BANKDEPOSIT_DETAILS where DATA_DATE ='" + dataDate + "' and( ACCNUM ='" + accnum +"' OR ZJ_ACCNUM= '"+ accnum +"' )   and SINGLEFLAG = '1'");
		 dao.delete(sb.toString());
		 String string3 = sb.toString();
		 log.info("DELETE FROM RPRT_BANKDEPOSIT_DETAILS:{}",string3);
	 }
	 
	/**
	 * 
	 * @param companyName
	 * @param accountid
	 * @param yearMonth
	 * @return
	 */
	@CrossOrigin(origins = "*",maxAge = 3600)
	@PostMapping("/getRpaData")
	@ResponseBody
	public synchronized JSONObject getData(String companyName,String accountid,String yearMonth) {
    	log.info("getData()companyName:{}",companyName);
    	log.info("getData()accountid:{}",accountid);
    	log.info("getData()yearMonth:{}",yearMonth);
    	JSONObject json =new JSONObject();
    	if(  accountid== null || yearMonth==null) {
    		json.put(STATUS,"-1");
    		json.put(RESULT,"参数不合法！！！");
			return json;
    	}
		try {
			String uuid = StringUtil.getUUID();
			RprtTask task = new RprtTask();
			String sql="select count(*) from  RPRT_TASK r "
					+ " where r.ACCOUNT_ID='"+accountid+"' "
					+ " and ( r.ENDTIME_IRA is null or r.ENDTIME_FR is null )";
			
			if(dao.selectCount(sql) > 0) {
				log.info("当前账户正在执行，请勿重复操作");
				json.put(RESULT,"当前账户正在执行，请勿重复操作");
				return json;
			}
			sql="select count(*) from  RPRT_TASK r "
					+ " where  r.ENDTIME_IRA is null or r.ENDTIME_FR is null ";
			Integer selectCount = dao.selectCount(sql);
			if(dao.selectCount(sql) > 40) {
				log.info("超过最大执行限制{}",selectCount);
				json.put(RESULT,"超过最大执行限制"+selectCount);
				return json;
			}
			
			log.info("before delete 20210101");
			deleteBankDepposite(yearMonth, companyName, accountid);
			log.info("after delete 20210101");
			
			task.setAccountId(accountid);
			task.setCompanyName(companyName);
			task.setDealid(uuid);
			task.setYearmonth(yearMonth);
			task.setOpraterFlag("2");
			task.setBegintime(new Date());
			task.setFinishStatus("2");
			if(irs.save(task)) {
				String string3 = task.toString();
				log.info("保存task表成功！！！：{}",string3);
				Map<String, String> map = new HashMap<>();
				int dayOfMonth = StringUtil.getLastDayOfMonth(yearMonth);
				map.put("dealid",uuid);
				map.put("accountid",accountid);
			    
			    if(companyName.contains("-")) {
		    		int indexOf = companyName.indexOf("-");
		    		map.put("companyName",companyName.substring(0,indexOf));
		    	}else {
		    		map.put("companyName",companyName);
		    		
		    	}
			    map.put("startDate",yearMonth+"-01");
			    map.put("endDate",yearMonth+"-"+dayOfMonth);
			    String string = map.toString();
			    log.info("银行单家入参{}：",string);
				JSONObject openHttp =openHttp(map);
				String string2 = openHttp.toString();
				log.info("银行余额单加转json：{}",string2);
				if(openHttp.getString(RESULT).equals("开始单家跑银行存款数据")) {
					json.put(STATUS, "200");
					json.put("id", task.getDealid());
					json.put(RESULT, "数据正在同步中，请耐心等待！");
				}else {
					json.put(STATUS, "0");
					json.put(RESULT, "银行余额单家调用失败");
				}
			}else {
				log.info("保存task失败：{}",task);
				json.put(STATUS, "0");
				json.put(RESULT, "银行余额单家调用失败");
			}
			
		} catch (Exception e) {
			json.put(STATUS, "-2");
			json.put(RESULT, "rpa-task调用异常");
			log.error("rpa-task调用异常:{}",e.toString());
			return json;
		}
		return json;
		
	}
	
	/**
	 * 打开远程连接，请求数据
	 * @param map 请求参数 companyName，accountid，accountid，startDate，endDate
	 * @return
	 */
    public JSONObject openHttp(Map<String, String> map) {
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(map);
        ResponseEntity<String> response = template.exchange(properties.getProperties("rptTaskUrl"), HttpMethod.POST, requestEntity, String.class);
        String string = response.toString();
        log.info("银行余额单家取数：{}",string);
        String responseBody = response.getBody();
        JSONObject json = JSON.parseObject(responseBody);
        return json;
    }
    /**
     * 
     * @param map 
     * @return
     */
    @CrossOrigin(origins = "*",maxAge = 3600)
	@PostMapping(value = "/taskTimed")
	@ResponseBody
    public JSONObject taskTimed(String uuid) {
    	
    	JSONObject json = new JSONObject();
    	log.info("taskTimed():{}",uuid);
    	if( StringUtils.isBlank(uuid)) {
    		
    		json.put(STATUS,"-1");
    		json.put(RESULT,"非法参数");
			return json;
    	}
    	
    	try {
    		QueryWrapper<RprtTask> wrapper = new QueryWrapper<>();
    		wrapper.eq("DEALID", uuid);
    		Integer Count=0;
    		for (; ;) {
        		RprtTask rprtTask = irs.getOne(wrapper);
        		String string = rprtTask.getFinishStatus();
        		if(string.equals("1")) {
        			json.put(STATUS,"200");
        			json.put(RESULT,"银行余额跑数已完成");
        			return json;
        		}
        		if(string.equals("3")) {
        			json.put(STATUS,"200");
        			json.put(RESULT,"银行余额跑数异常结束");
        			return json;
        		}
        		Count++;
        		log.info("taskTimed()查询未反馈次数{}",Count);
        		Thread.sleep(5000);
        		
    		}
		} catch (Exception e) {
			json.put(RESULT,"银行余额校验跑数异常");
			log.error("taskTimed()方法异常{}",e.toString());
		}
		return json;
    }
    
    
    //select t.*,rowid from ba_balance_adjust_total t
    
    
    /**
     * 余额调节表获取账户及余额信息接口
     * @param map 
     * @return
     */
    @CrossOrigin(origins = "*",maxAge = 3600)
	@RequestMapping(value = "/getBankBalanceByAccount")
	@ResponseBody
    public JSONObject getBankBalanceByAccount(@RequestBody String string) {
    	JSONObject json = new JSONObject();
    	
    	
		try {
			JSONObject jsonString =JSONObject.parseObject(string);
	    	String accountDate = jsonString.getString("accountDate");
	    	String accountNum = jsonString.getString("accountNum");
	    	
	    	log.info("getBankBalanceByAccount：accountDate{}",accountDate);
	    	log.info("getBankBalanceByAccount：accountNum{}",accountNum);
	    	 
			json = baBalanceAdjustTotalService.getListByParameter(accountNum, accountDate);
			
		} catch (Exception e) {
			log.error("getBankBalanceByAccount：方法异常{}",e.toString());
			json.put("returnCode", -1);
			json.put("returnMsg", e.toString());
		}
		return json;
    }
    
}
