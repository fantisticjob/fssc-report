package com.longfor.fsscreport.bpm.service.impl;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.longfor.fsscreport.approval.entity.FrProcessInfo;
import com.longfor.fsscreport.approval.mapper.CommonDao;
import com.longfor.fsscreport.approval.service.IFrProcessInfoService;
import com.longfor.fsscreport.bpm.service.IBpmService;
import com.longfor.fsscreport.utils.CommonConstant;
import com.longfor.fsscreport.utils.ProcessStatus;
import com.longfor.fsscreport.vo.BpmCreateProcessReqParam;

/**
 * bpm流程处理实现类
 * @author chenziyao
 *
 */
@Service
public class BpmServiceImpl implements IBpmService {
	
    private static final  Logger log = LoggerFactory.getLogger(BpmServiceImpl.class);
	
	@Autowired
	private IFrProcessInfoService ifs;
	@Autowired
	private CommonDao cdao;
    @Value("${bpm.host}")
    private String host;
    
    @Value("${am.host}")
    private String hostAm;

    @Value("${bpm.api-key}")
    private String apiKey;
    
    @Value("${am.api-key}")
    private String apiKeyAm;

    @Value("${bpm.sys-code}")
    private String sysCode;

    private String createProcessUrl;
    
    private String withdrawalProcess;
    
    private String pt;
    
    private String dq;
    
    private String haveNopass;
    
    private  String orgUrl;

    @PostConstruct
    private void init() {
        this.createProcessUrl = host + "/bpm-server/api/runtime/process-instances";
        
        this.withdrawalProcess =  host + "/bpm-server/api/runtime/tasks/";
        
        this.orgUrl = hostAm+ "/public/v2/role/getRoleListByUsernames";
        
    }
    @Autowired
    private  RestTemplate template;
    
    
	/**
	 * 创建流程id
	 */
	@Override
	public String createProcess(String userCode, String approvName, BpmCreateProcessReqParam param) {

		log.info("createProcess方法userCode{}", userCode);
		log.info("createProcess方法approvName{}", approvName);
		log.info("createProcess方法param{}", param);
		String flag = getParam(param);

		HttpHeaders requestHeaders = null;
		if (StringUtils.isNotBlank(approvName)) {

			requestHeaders = createBpmHeader(userCode, approvName);
		} else {

			requestHeaders = createBpmHeader(userCode, null);
		}
		HttpEntity<String> requestEntity = new HttpEntity<>(JSON.toJSONString(param), requestHeaders);
		log.info("createProcess方法createProcessUrl：{}", createProcessUrl);
		ResponseEntity<String> response = template.exchange(createProcessUrl, HttpMethod.POST, requestEntity,
				String.class);
		String string = response.toString();

		log.info("创建流程id返回字符串为{}", string);
		String responseBody = response.getBody();
		JSONObject map = JSON.parseObject(responseBody);
		setProcess(userCode, approvName, map.getString("instanceId"), flag);
		return map.getString("instanceId");
	}
    
    /**
     * 设置流程参数
     */
    public void setProcess(String userCode,String username,String instanceId,String flag) {
    	
    	log.info("setProcess方法instanceId：{}",instanceId);
    	log.info("setProcess方法userCode：{}",userCode);
    	
    	JSONObject json = new JSONObject();
    	String startOrg = getOrgCode(userCode,flag);  //根据提交人查询组织角色等信息
    	
    	if(flag.equals("ydgz")) {  //月度关帐
    		
    		json.put("startOrg",startOrg);
        	json.put("pt",pt);
        	json.put("haveNopass",haveNopass);
    	}else if(flag.equals("yhye")) { //银行余额
    		
    		json.put("pt",pt);
    		json.put("startOrg",startOrg);
    	}else if(flag.equals("zjzc")) { //资金自查
    		
    		json.put("dq",dq);
    		json.put("startOrg",startOrg);
    		
    	}else if(flag.equals("zdsy") && StringUtils.isNotBlank(startOrg)){ //如果orgcode不为空，下一级审批人传过去 //重点税源
    		
    		json.put("startOrg",startOrg);
    		
    		json.put("sp_oa",username);
    		
    		log.info("重点税源设置流程审批人：{}", json.toJSONString());
    		
    	}else {  //其他流程
    		
    		json.put("startOrg",startOrg);
    	}
     	StringBuilder buffer = new StringBuilder();
    	buffer.append(createProcessUrl);
    	buffer.append("/"+instanceId);
    	buffer.append("/actions/variables");
        HttpHeaders requestHeaders = createBpmHeader(userCode,username);
        HttpEntity<JSONObject> requestEntity = new HttpEntity<>(json, requestHeaders);
        ResponseEntity<String> response = template.exchange(buffer.toString(), HttpMethod.PUT, requestEntity, String.class);
        String string = response.toString();
        log.info("setProcess()字符串为：{}",string);
        if(response.getStatusCodeValue() == 200) {
        	log.info("设置流程参数成功！");
        }else {
        	log.error("设置流程参数失败！");
        }
        
    }
    /**
     *  HttpHeaders 参数放入HttpHeaders
     * @param userCode
     * @param username 
     * @return
     */
    private HttpHeaders createBpmHeader(String userCode, String approvName) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("X-Gaia-Api-key", apiKey);
        requestHeaders.add("X-ER-system-Info", sysCode);
        requestHeaders.add("X-ER-User-Info", Base64.getEncoder().encodeToString(userCode.getBytes()));
        if(StringUtils.isNotBlank(approvName)) { //如：重点税源等审批需要传入审批人
            requestHeaders.add("SP_OA", Base64.getEncoder().encodeToString(approvName.getBytes()));
        }
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        return requestHeaders;
    }

    /**
     * 根据流程id 得到taskId
     */
    @Override
    public List<String> getTaskId(String userCode, String param) {
    	log.info("getTaskId方法userCode：{}",userCode);
    	log.info("getTaskId方法param：{}",param);
    	List<String> list = new ArrayList<>();
    	HttpHeaders requestHeaders = createBpmHeader(userCode,null);
    	StringBuilder buffer = new  StringBuilder();
    	buffer.append(createProcessUrl+"/");
    	buffer.append(param);
    	buffer.append("/task-info");
    	HttpEntity<String> requestEntity = new HttpEntity<>(requestHeaders);
    	ResponseEntity<String> response = template.exchange(buffer.toString(), HttpMethod.GET, requestEntity, String.class);
    	String  responseBody= response.getBody();
    	
    	log.info("根据流程id 得到taskId的字符串为:{}",responseBody);
    	boolean flag=false;
    	if(StringUtils.isNotBlank(responseBody) ) {
    		JSONArray array = JSONArray.parseArray(responseBody);
        	for (int i = 0; i < array.size(); i++) {
        		JSONObject object = JSONObject.parseObject(array.getString(i));
        		//工作项当前状态  待领取（4）、运行（10）、完成（12）、终止（13）、挂起（8）
        		if( "10".equals(object.getString("currentState")) ) {
            		flag = true;
            	}
    		}
        	for (int i = 0; i < array.size(); i++) {
        		
        		JSONObject object = JSONObject.parseObject(array.getString(i));
        		if(object.getString("participant").equals(userCode) && flag &&
            		"12".equals(object.getString("currentState"))) {
            			list.add(object.getString("workItemId"));
            	}
			}
    	}
    	String string = list.toString();
    	log.info("getTaskId方法返回：{}",string);
    	return list;
    }
    /**
     * 根据taskId,撤销流程
     */
    @Override
    public String withdrawalProcess(String userCode, String param) {
    	
    	log.info("withdrawalProcess方法userCode：{}",userCode);
    	log.info("withdrawalProcess方法param：{}",param);
    	HttpHeaders requestHeaders = createBpmHeader(userCode,null);
    	
    	String string3 = requestHeaders.toString();
    	log.info("requestHeaders:{}",string3);
    	StringBuilder buffer = new  StringBuilder();
    	buffer.append(withdrawalProcess);
    	buffer.append(param);
    	buffer.append("/actions/cancel");
    	String string2 = buffer.toString();
    	log.info("withdrawalProcess方法访问地址为:{}",string2);
    	HttpEntity<String> requestEntity = new HttpEntity<>(requestHeaders);
    	ResponseEntity<String> response = template.exchange(buffer.toString(), HttpMethod.PUT, requestEntity, String.class);
    	
    	String string = response.toString();
    	log.info("根据taskId,撤销流程字符串为:{}",string);
    	HttpStatus code = response.getStatusCode();
    	return code.toString();
    }
    /**
     * 监听mq消息回调以后，修改相应的业务表的流程状态！
     */
	@Override
	public void updateStateByInstanceId(String string) {
		log.info("msg消息：{}" , string);
		try {
			//将BPM的MQ消息字符串转化为Json对象
			JSONObject object = JSONObject.parseObject(string);
			log.info("msg转json消息：{}" , object);

			//获取消息里面的流程审批ID
			String instanceId = String.valueOf(object.get("instanceId"));//审批表与instanceId关系映射表
			//根据流程审批ID获取FR_PROCESS_INFO表中对应的需要更新的业务数据表名称
			List<FrProcessInfo> list = ifs.getList(instanceId);
			//如果获取不到业务表信息，则直接返回
			if (list != null && 0 < list.size()) {
				//流程状态
				String type ="";
				//根据返回消息这是审批类型
				if(object.get("type")!=null && !"".equals(object.get("type"))) {
					type = ProcessStatus.updataStutasToString(Integer.parseInt(object.get("type").toString()));
				}
				//循环处理业务表
				String outFrTableInfo = "";
				for (FrProcessInfo tmp : list) {
					//拼接处理的业务表名，打印输出，方便排查问题
					outFrTableInfo += (tmp.getTableName() != null ? tmp.getTableName() : "");
					outFrTableInfo += ", ";
					//获取表名
					String tableName = tmp.getTableName();
					if(StringUtils.isNotBlank(type) && StringUtils.isNotBlank(tableName)) {
						//重点税源业务表需要特殊处理
						Integer integer = 0; //更新结果
						if("DW_ZDSY_MONTHLY_TAX_REPORT".equals(tableName) ||
								"DW_ZDSY_MONTHLY_TAX_REPORT2".equals(tableName)) {
							String sql="";
							if("流程撤回".equals(type)) {
								sql = "UPDATE "+tableName+" SET STATUS='0'  WHERE INSTANCE_ID= '"+instanceId+"'";//修改相应的业务表的流程状态！
							}else {
								sql = "UPDATE "+tableName+" SET STATUS='1'  WHERE INSTANCE_ID= '"+instanceId+"'";//修改相应的业务表的流程状态！
							}
							integer = cdao.update(sql);
							log.info("mq回调修改重点税源表名：{}" , tableName);
							log.info("重点税源修改审批状态：{}" , integer);
						} else {
							String sql="UPDATE "+tableName+" SET SP='"+type+"'  WHERE INSTANCE_ID= '"+instanceId+"'";//修改相应的业务表的流程状态！
							integer = cdao.update(sql);
							log.info("mq回调修改表名：{}" , tableName);
							log.info("的状态为：{}",integer);
						}
						if(integer==0 ) {
							log.info("审批状态修改失败：{}" , string);
						}
					}
				}
				log.info("FrProcessInfo表查询：{}" ,outFrTableInfo);
			}
		} catch (Exception e) {
			log.error("修改审批状态异常{}" , string);
		}
	}
	/**
	 * 判断是那个流程做出相应的参数设置
	 * @param param
	 * @return
	 */
	private String getParam(BpmCreateProcessReqParam param) {

		String flag=null;
		if("FSSGXYY_GDFZCKB".equals(param.getProcessDefName())) {//如果为工抵房流程，置空这两个参数
    		param.setPt(null);
    		param.setHaveNopass(null);
    		param.setDq(null);
    		flag="gdf";
    	}else if("FSSGXYY_YETJB".equals(param.getProcessDefName())) {//如果为银行余额流程，置空二个参数 
    		pt = param.getPt();
    		flag="yhye";
    		param.setHaveNopass(null);
    		param.setPt(null);
    		param.setDq(null);
    	}else if("FSSGXYY_DPZHKBZC".equals(param.getProcessDefName()) ) {//资金自查
    		flag="zjzc";
    		dq=param.getDq();
    		param.setHaveNopass(null);
    		param.setPt(null);
    		param.setDq(null);
    	}else if("FSSGXYY_SJBBWLQLJCSJ".equals(param.getProcessDefName()) ) {//往来清理
    		flag="wlql";
    		param.setHaveNopass(null);
    		param.setPt(null);
    		param.setDq(null);
    	}else if("FSSGXYY_SJBBZDSYJCSSJ".equals(param.getProcessDefName()) ) {//重点税源
    		flag="zdsy";
    		param.setHaveNopass(null);
    		param.setPt(null);
    		param.setDq(null);
    	}else  {
    		haveNopass = param.getHaveNopass();
    		pt = param.getPt();
    		param.setHaveNopass(null);
    		param.setPt(null);
    		param.setDq(null);
    		flag="ydgz";
    	}
		return flag;
	}
	
	/**
	 * 根据用户名取角色
	 * @param userName
	 * @param flag
	 * @return
	 */
	public  String getOrgCode(String userName,String flag) {
		List<String> list = new ArrayList<>();
		list.add(userName);
		JSONObject object = new JSONObject();
		String orgCode=null;
		
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("X-Gaia-Api-key",apiKeyAm );
		requestHeaders.add("Content-Type", "application/json");
		object.put("userNames", list);
		
		HttpEntity<JSONObject> requestEntity = new HttpEntity<>(object, requestHeaders);
		ResponseEntity<String> response = template.exchange(orgUrl, HttpMethod.POST, requestEntity, String.class);
		log.info("getOrgCode()符串为：{}",response);
		JSONObject json = JSON.parseObject(response.getBody());
    	List<Object> data = JSONArray.parseArray(json.getString("data"));
    	JSONObject parseObject = JSONObject.parseObject(json.getString("respCode"));
    	
    	if(json.getString("respCode")!=null && parseObject.get("message").equals("success")) {
			JSONObject jsonObject = JSONObject.parseObject(data.get(0).toString());
			List<Object> roleInfo = JSONArray.parseArray(jsonObject.getString("roleInfo"));
			orgCode = getRoleName(flag,roleInfo);  //匹配具体角色
    	}
		return orgCode;
	}
	
	/**
	 * 判断是那个流程得到当前负责人的岗位code
	 * @param param
	 * @return
	 */
	@Override
	public  String getRoleName(String flag,List<Object> roleInfo) {
		String orgCode=null;
		
		for (int i = 0; i < roleInfo.size(); i++) {
			JSONObject roleInfoObj = JSONObject.parseObject(roleInfo.get(i).toString());
			String  roleName = roleInfoObj.getString("roleName");
			if( flag.equals("gdf") && CommonConstant.DQDCSSZCMKFZR.equals(roleName)) {
				orgCode=roleInfoObj.getString("orgCode");
				//============add by zhaoxin==========20201124 begin
				break;
				//============add by zhaoxin==========20201124 end
			}
			
			if(flag.equals("ydgz") && pt.equals("XN")) {
				if(CommonConstant.XNSRHSZYG.equals(roleName) || CommonConstant.XNZZHSZYG.equals(roleName)) {
					orgCode=roleInfoObj.getString("orgCode");
				}
			}
			if(flag.equals("ydgz") && pt.equals("HN")) {
				if(CommonConstant.HNZZHSZYG.equals(roleName) || CommonConstant.HNSRHSZYG.equals(roleName) ||
					CommonConstant.HNFYZCZYG.equals(roleName) || CommonConstant.HNCBHSZYG.equals(roleName)	) {
					orgCode=roleInfoObj.getString("orgCode");
				}
			}
			if(flag.equals("ydgz") && pt.equals("CSJ")) {
				if(CommonConstant.CSJZZHSZYG.equals(roleName) || CommonConstant.CSJSRHSZYG.equals(roleName) ||
					CommonConstant.CSJFYZCZYG.equals(roleName) || CommonConstant.CSJCBHSZYG.equals(roleName) ||
					CommonConstant.CSJSJBBZYG.equals(roleName)) {
					orgCode=roleInfoObj.getString("orgCode");
				}
			}
			if(flag.equals("ydgz") && pt.equals("HBH")) {
				if(CommonConstant.HBHZZHSZYG.equals(roleName) || CommonConstant.HBHSRHSZYG.equals(roleName) ||
					CommonConstant.HBHFYZCZYG.equals(roleName) || CommonConstant.HBHCBHSZYG.equals(roleName) ||
					CommonConstant.HBHSJBBZYG.equals(roleName)) {
					orgCode=roleInfoObj.getString("orgCode");
				}
			}
			if(flag.equals("yhye") && pt.equals("XN")) {
				if(CommonConstant.XNZZHSZFZR.equals(roleName)) {
					orgCode=roleInfoObj.getString("orgCode");
				}
			}
			if(flag.equals("yhye") && pt.equals("HN")) {
				if(CommonConstant.HNZZHSZFZR.equals(roleName)	) {
					orgCode=roleInfoObj.getString("orgCode");
				}
			}
			if(flag.equals("yhye") && pt.equals("CSJ")) {
				if(CommonConstant.CSJZZHSZFZR.equals(roleName)) {
					orgCode=roleInfoObj.getString("orgCode");
				}
			}
			if(flag.equals("yhye") && pt.equals("HBH")) {
				if(CommonConstant.HBHZZHSZFZR.equals(roleName)) {
					orgCode=roleInfoObj.getString("orgCode");
				}
			}
			
			if("zjzc".equals(flag) && null!=dq) {
				if(CommonConstant.DQZJZY.equals(roleName)) {
					orgCode=roleInfoObj.getString("orgCode");
				}
			}
			List<String> zdsyList = CommonConstant.getZdsyList();

			if("zdsy".equals(flag) || "wlql".equals(flag)){ // 重点税源跟往来清理
				if(zdsyList.contains(roleName)) {
					orgCode=roleInfoObj.getString("orgCode");
				}
			}
		}
		log.info("getRoleName:{}",orgCode);
		return orgCode;
	}
}
