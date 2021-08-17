package com.longfor.fsscreport.clear.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.longfor.fsscreport.approval.mapper.CommonDao;

/**
 * <p>
 * 银行余额导出===后台控制类
 * </p>
 *
 * @author zhaoxin
 * @since 2021-02-24
 */

@RestController
@RequestMapping("/log")
public class BankDepositLogController  {
	
	private static final  String STATUS="status";
	private static final  String RESULT="result";

	@Autowired
	private CommonDao dao;
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	   
	 /**
     * 进行开始执行操作记录
     * interface1：进行开始执行操作记录
     * @param accountId
     * @param month
     * @return
     */
    @CrossOrigin(origins = "*",maxAge = 3600)
    @PostMapping("/logOperationRecordStart")
    @ResponseBody
    public JSONObject logbegin(
    		String module,
    		String operation,
    		String user_code,
    		String setovertime) {
    	
    	JSONObject json = new JSONObject();
    	
    	// DW_OPERATION_RECORD
    	// DW_OPERATION_RECORD_S
   
    	// module：模块
    	// operation：操作
    	// user_code：用户code
    	// setovertime：超时设置（s）
    	
    	log.info("logOperationRecordStart module:{}" , module);
    	log.info("logOperationRecordStart operation:{}" , operation);
    	log.info("logOperationRecordStart user_code:{}" , user_code);
    	log.info("logOperationRecordStart setovertime:{}" , setovertime);
    	
    	if(StringUtils.isBlank(module)) {
    		json.put(STATUS, -1);
    		return json;
    	}
    	
    	if(StringUtils.isBlank(operation)) {
    		json.put(STATUS, -2);
    		return json;
    	}
    	
    	if(StringUtils.isBlank(user_code)) {
    		json.put(STATUS, -3);
    		return json;
    	}
    	
    	if(StringUtils.isBlank(setovertime)) {
    		json.put(STATUS, -4);
    		return json;
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	sb.append("select DW_OPERATION_RECORD_S.nextVal from dual");
    	
    	// ID 取得
    	BigDecimal id = dao.selectBigDecimalOne(sb.toString());
    	sb = new StringBuilder();
    	sb.append(" insert into DW_OPERATION_RECORD (ID, MODULE,OPERATION,USER_CODE, START_TIME, SETOVERTIME) ");
    	sb.append(" values(");
    	sb.append( id);
    	sb.append( ",'" + module + "'");
    	sb.append( ",'" + operation + "'");
    	sb.append( ",'" + user_code + "'");
    	sb.append( ",sysdate ");
    	sb.append( ",'" + setovertime + "'");
    	sb.append( ")");
    	int result = dao.insert(sb.toString());
    	if(result > 0) {
    		json.put(STATUS, 200);
    		json.put("id", id);
    		return json;
    	} else {
    		json.put(STATUS, -5);
    		return json;
    	}
    
    }
    
    
    /**
     * 进行开始执行操作记录
     * interface2：该接口的目的是进行操作结束时，根据返回的id，需将当前时间录入到end_time字段中
     * @param id
     * @param month
     * @return
     */
    @CrossOrigin(origins = "*",maxAge = 3600)
    @PostMapping("/logOperationRecordEnd")
    @ResponseBody
    public JSONObject logend(
    		String id) {
    	
    	// DW_OPERATION_RECORD
    	// DW_OPERATION_RECORD_S
    	JSONObject json = new JSONObject();
    	
    	log.info("logOperationRecordEnd id{}" , id);
    	StringBuilder sb = new StringBuilder();
    	sb.append(" update DW_OPERATION_RECORD set END_TIME = sysdate where id=" + id);
    	int result = dao.update(sb.toString());
    	if(result > 0) {
    		json.put(STATUS, 200);
    		return json;
    	} else {
    		json.put(STATUS, -1);
    		return json;
    	}
    }
    
    /**
     * 进行开始执行操作记录
     * interface3：进行初始化执行操作记录
     * @param module
     * @param  1:删除对应模块对应操作3个月前的操作（仅保留3个月的操作记录）
     *                  2:对对应模块对应操作的数据中存在的超过overtimeset的设置值的数据进行endtime闭合，闭合时间为当前时间。
     *                  （考虑在过程中或存在仅调用开始未调用结束的情况）
     * @return
     */
    @CrossOrigin(origins = "*",maxAge = 3600)
    @PostMapping("/logOperationRecordInit")
    @ResponseBody
    public JSONObject loginit(
    		String mode,
    		String operation) {
    	JSONObject json = new JSONObject();

    	log.info("loginit mode :{}" , mode);
    	
    	// 1: 删除对应模块对应操作3个月前的操作（仅保留3个月的操作记录）
    	
    	StringBuilder sb = new StringBuilder();
		sb.append(" delete DW_OPERATION_RECORD where START_TIME<=sysdate - 90");
		dao.update(sb.toString());
		
		sb = new StringBuilder();
		sb.append(" update DW_OPERATION_RECORD set END_TIME=sysdate  where (sysdate-START_TIME)*24*60*60 > SETOVERTIME and end_time is null");
		dao.update(sb.toString());
		
		json.put(STATUS, 200);
		return json;
    }
    
	/**
	 * 
	 * 
	 * 往来清理初始化数据回滚操作
	 * 
	 * @return
	 */
	@CrossOrigin(origins = "*", maxAge = 3600)
	@PostMapping("/logOperationRecordBack")
	@ResponseBody
	public JSONObject deleteClearData(String accountsId, String quarter) {

		JSONObject json = new JSONObject();

		log.info("往来清理初期化删除开始");

		log.info("param=={}" , accountsId);
		log.info("quarter=={}" , quarter);

		if (StringUtils.isBlank(accountsId) || StringUtils.isBlank(quarter)) {
			log.info("入口参数不对。");
			json.put(STATUS, "-1");
			json.put(RESULT, "参数不合法");
			return json;
		}

		try {
			StringBuilder sb = new StringBuilder();
			sb.append("update dw_cp_clear_up_total t");
			sb.append(" set t.start_date = ''  ");
			sb.append(" where t.quarter = '");
			sb.append(quarter);
			sb.append("' ");
			sb.append(" and t.accounts_id = '");
			sb.append(accountsId);
			sb.append("' ");
			String string = sb.toString();
			log.info("NO1{}" , string);
			dao.update(sb.toString());
			log.info("NO1");

			// 02
			sb = new StringBuilder();
			sb.append("delete from dw_cp_clear_up_detail t ");
			sb.append(" where t.quarter = '");
			sb.append(quarter);
			sb.append("' ");
			sb.append(" and t.accounts_id = '");
			sb.append(accountsId);
			sb.append("' ");
			sb.append(" and t.csh_flag = '1' ");
			String string2 = sb.toString();
			log.info("NO2{}" ,string2);
			dao.update(sb.toString());
			log.info("NO2");

			// 03
			sb = new StringBuilder();
			sb.append("update dw_cp_clear_up_blance t ");
			sb.append("set t.reseon        = '',  ");
			sb.append("t.is_un_general = '',  ");
			sb.append("t.pt_user       = '',  ");
			sb.append("t.clear_time    = '',  ");
			sb.append("t.respon_user   = '',  ");
			sb.append("t.respon_dept   = '',  ");
			sb.append("t.re_mark       = '',  ");
			sb.append("t.dq_user       = ''   ");
			sb.append(" where t.quarter = '");
			sb.append(quarter);
			sb.append("' ");
			sb.append(" and t.accounts_id = '");
			sb.append(accountsId);
			sb.append("' ");
			String string3 = sb.toString();
			log.info("NO3{}" ,string3 );
			dao.update(string3);
			log.info("NO3");

			log.info("往来清理初期化删除结束");
			json.put(STATUS, "200");
			json.put(RESULT, "初始化数据删除成功！");
		} catch (Exception e) {
			json.put(STATUS, "-2");
			json.put(RESULT, "初始化数据删除异常");
			log.error("往来清理初期化删除异常{}",e.toString());
		}

		return json;
	}
    
}
