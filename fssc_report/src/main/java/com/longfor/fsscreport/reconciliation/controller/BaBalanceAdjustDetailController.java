package com.longfor.fsscreport.reconciliation.controller;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.longfor.fsscreport.reconciliation.service.IBaBalanceAdjustDetailService;

/**
 * <p>
 * 资金余额明细历史 前端控制器
 * </p>
 *
 * @author chenziyao
 * @since 2020-07-31
 */

@RestController
@RequestMapping("/reconciliation")
public class BaBalanceAdjustDetailController  {
	
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final static String STATUS="status";
	private final static String RESULT="result";
	@Autowired
	private IBaBalanceAdjustDetailService ibs;
	/**
     * 明细表删除记录
     * @param accountId
     * @param month
     * @return
     */
    @CrossOrigin(origins = "*",maxAge = 3600)
    @PostMapping(value = "/copyDetail")
    @ResponseBody
    public JSONObject copyDetail(String deleteParm) {
    	
    	log.info("copyDetail()deleteParm{}:",deleteParm);
    	JSONObject jsonObject = new JSONObject();
		try {
			JSONObject json = JSONObject.parseObject(deleteParm);
			if(json.get("id") == null || "".equals(json.get("id"))) {
				jsonObject.put(STATUS,"-1");
				return jsonObject;
				
			}
			String sql = getSql(json);
			
			if(ibs.insertDetal(sql) > 0) {
				log.info("明细表插入成功！！！");
				jsonObject.put(STATUS,"200");
			}else {
				jsonObject.put(STATUS,"0");
				log.info("明细表插入失败！！！");
				jsonObject.put(RESULT,"插入失败");
			}
		} catch (Exception e) {
			log.error("明细表删除记录异常！！！{}",e.toString());
			jsonObject.put(STATUS,"-2");
		}
		log.info("copyDetail(return):{}",jsonObject);
    	return jsonObject;
    }
    
    /**
     * 明细表删除记录sql
     * @param json
     * @return
     */
    public String getSql(JSONObject json) {
    	String sql="INSERT INTO BA_BALANCE_ADJUST_DETAIL" + 
				"  SELECT BA_BALANCE_ADJUST_DETAIL_S.NEXTVAL ID," + 
				"         R.ACCOUNTS_ID," + 
				"         R.ACCOUNTS_NAME," + 
				"         D.ACCOUNT_ID," + 
				"         R.ACCOUNT_NAME," + 
				"         D.ACCOUNT_TIME," + 
				"         '删除' REMARK," + 
				"         D.LS_REMARKS LS_REMARK," + 
				"         D.LS_TIME LS_TIME," + 
				"         D.DS_ACCOUNT_NAME DF_ACCOUNT_NAME," + 
				"         D.DS_BANK DF_BANK_NAME," + 
				"         D.DS_ACCOUNT DF_ACCOUNT," + 
				"         D.LS_AMOUNT," + 
				"         SYSDATE UPDATE_TIME," + 
				"         '"+json.get("user")+"' UPDATE_USER," + 
				"         D.LS_DATE XGH_LS_TIME," + 
				"         '"+json.get("headerid")+"' HEAD_ID," + 
				"         D.LS_PURPOSE," + 
				"         D.ID MX_ID" + 
				"    FROM (SELECT A.*, R.LS_TIME" + 
				"            FROM (SELECT *" + 
				"                    FROM BA_RECONCILIATION_DETAILS RD" + 
				"                   WHERE MATCHING_NO IS NULL) A" + 
				"            LEFT JOIN (SELECT *" + 
				"                        FROM (SELECT D.MX_ID," + 
				"                                     D.LS_TIME," + 
				"                                     D.ACCOUNT_ID," + 
				"                                     D.ACCOUNT_TIME," + 
				"                                     ROW_NUMBER() OVER(PARTITION BY MX_ID ORDER BY UPDATE_TIME DESC) NUM" + 
				"                                FROM BA_BALANCE_ADJUST_DETAIL D) RES" + 
				"                       WHERE RES.NUM = 1) R" + 
				"              ON A.ID = R.MX_ID" + 
				"             AND A.ACCOUNT_ID = R.ACCOUNT_ID" + 
				"             AND A.ACCOUNT_TIME = R.ACCOUNT_TIME" + 
				"           ORDER BY LS_AMOUNT NULLS LAST) D" + 
				"    LEFT JOIN (SELECT NVL(C.PLAT_CODE, D.PLAT_CODE) PLAT_CODE" + 
				"         ,NVL(C.PLAT_NAME, D.PLAT_NAME) PLAT_NAME" + 
				"         ,B.AREA_CODE" + 
				"         ,B.AREA_NAME" + 
				"         ,B.ACCOUNTINGBOOKCODE ACCOUNTS_ID" + 
				"         ,B.ACCOUNTS_NAME" + 
				"         ,A.ACCOUNT_ID" + 
				"         ,A.ACCOUNT_NAME" + 
				"       FROM BA_ACCOUNTS_ACCOUNT A" + 
				"       LEFT JOIN BA_AREA_ACCOUNTS B" + 
				"        ON A.ACCOUNTS_ID = B.ACCOUNTS_ID" + 
				"       LEFT JOIN (SELECT * FROM BA_PLAT_AREA WHERE AREA_NAME <> '集团') C" + 
				"        ON B.AREA_CODE = C.AREA_CODE" + 
				"       LEFT JOIN (SELECT * FROM BA_PLAT_AREA_FKDW WHERE AREA_NAME = '集团') D" + 
				"        ON B.ACCOUNTS_NAME = D.FKDW) R" + 
				"      ON D.ACCOUNT_ID = R.ACCOUNT_ID" + 
				"   WHERE D.ID IN ("+StringUtils.strip(json.get("id").toString(),"[]")+") ";   
    	
		return sql;
    }
}
