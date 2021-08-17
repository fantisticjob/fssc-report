package com.longfor.fsscreport.costaccount.controller;


import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.longfor.fsscreport.approval.mapper.CommonDao;
import com.longfor.fsscreport.costaccount.entity.DwCtProjectPayment;
import com.longfor.fsscreport.costaccount.service.IDwCtProjectPaymentService;
import com.longfor.fsscreport.reconciliation.entity.StoredProcedure;

/**
 * <p>
 * 成本台账项目付款单明细表 前端控制器
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-07
 */
@RestController
@RequestMapping("/costaccount")
public class DwCtProjectPaymentController  {
	
	private final static String STATUS="status";
	private final static String RESULT="result";
	
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private IDwCtProjectPaymentService idwctp;
	@Autowired
	private CommonDao dao;
	
	 /**
     * 更新成本台账存储过程
     * @param accountId
     * @param month
     * @return
     */
    @CrossOrigin(origins = "*",maxAge = 3600)
    @PostMapping(value = "updataCbtz")
    @ResponseBody
    public JSONObject updataCbtz(String company,String month,String flag) {
    	JSONObject json = new JSONObject();
    	
    	if(company ==null || month == null || flag==null) {
    		json.put(STATUS, -1);
    		return json;
    	}
    	try {
    		StoredProcedure sp = new StoredProcedure();
    		sp.setP_date(month);
    		sp.setP_company(company);
    		sp.setP_flag(flag);
    		idwctp.updataCbtz(sp);
    		json.put(STATUS, 200);
    	} catch (Exception e) {
    		json.put(STATUS, 0);
    		log.error("updataCbtz()方法异常{}",e.toString());
    	}
    	return json;
    }
    /**
     * 
     * @param accountId
     * @param month
     * @return
     */
    @CrossOrigin(origins = "*",maxAge = 3600)
    @PostMapping(value = "updataPayment")
    @ResponseBody
    public JSONObject updataPayment(String month,String companyName,String flag) {
    	JSONObject json = new JSONObject();
    	if(month ==null || companyName == null ||flag ==null) {
    		json.put(STATUS, -1);
    		return json;
    	}
    	
    	try {
    	
    		DwCtProjectPayment payment = new DwCtProjectPayment();
    		payment.setTjDate(new Date());
    		payment.setTjStatus("1");
    		UpdateWrapper<DwCtProjectPayment> wrapper = new  UpdateWrapper<DwCtProjectPayment>();
    		
    		if("true".equals(flag)) {
    			
    			wrapper.eq("DATEKEY",month);
        	}else {
        		
        		wrapper.le("DATEKEY",month);
        	}
    		
    		wrapper.eq("COMPANY_NAME",companyName);
    		if(idwctp.update(payment, wrapper)) {
    			json.put(STATUS, 200);
    		}else {
    			json.put(STATUS, 0);
    		}
    	} catch (Exception e) {
    		json.put(STATUS, -2);
    		log.error("updataPayment()方法异常{}",e.toString());
    	}
    	
    	return json;
    }
	
    /**
     * 
     * @param accountId
     * @param month
     * @return
     */
    @CrossOrigin(origins = "*",maxAge = 3600)
    @PostMapping(value = "/manualConfirmation")
    @ResponseBody
    public JSONObject manualConfirmation(String date,String company,String fq) {
    	JSONObject json = new JSONObject();
    	if(date ==null || company == null ||fq ==null) {
    		json.put(STATUS, -1);
    		return json;
    	}
    	try {
    		String sql=" UPDATE dw_ct_cost_total d SET  d.isrgqr='1' "
    				+ " WHERE d.COMPANY_NAME ='"+company+"'"
    				+ " and d.DATEKEY = '"+date+"'"
    				+ " and d.fq= '"+fq+"'";
    		if(dao.update(sql) > 0) {
    			json.put(STATUS, 200);
        		return json;
    		}else {
    			json.put(STATUS, 0);
    			json.put(RESULT, "更新失败");
        		return json;
    		}
    		
    	} catch (Exception e) {
    		json.put(STATUS, -2);
    		json.put(RESULT, "更新异常");
    		log.error("manualConfirmation()方法异常{}",e.toString());
    	}
    	return json;
    }
    
}
