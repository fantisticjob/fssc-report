package com.longfor.fsscreport.clear.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.longfor.fsscreport.approval.entity.FrProcessInfo;
import com.longfor.fsscreport.approval.mapper.CommonDao;
import com.longfor.fsscreport.approval.service.IFrProcessInfoService;
import com.longfor.fsscreport.utils.ProcessStatus;

/**
 * <p>
 * 往来清理-往来余额类 前端控制器
 * </p>
 *
 * @author chenziyao
 * @since 2020-10-30
 */
@RestController
@RequestMapping("/clear")
public class DwCpClearUpBlanceController  {


	
	private static final  String RESULT="result";

	
	@Autowired
	private IFrProcessInfoService ifs;
	
	@Autowired
	private CommonDao cdao;
	/**
	 * 根据用户名取角色
	 * @param userName
	 * @param flag
	 * @return
	
	@CrossOrigin(origins = "*",maxAge = 3600)
	@RequestMapping("/ag")
	@ResponseBody
	public  String getOrgCode(String userName,String flag) {
			
	@Autowired
	private  RestTemplate template;
	
	@Autowired
	private IBpmService service;

		userName="wangyang";
		flag="gdf";
		List<String> list = new ArrayList<String>();
		list.add(userName);
		JSONObject object = new JSONObject();
		String orgCode=null;
		
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("X-Gaia-Api-key","40e15609-9aeb-4871-af96-84131100f24c" );
		requestHeaders.add("Content-Type", "application/json");
		object.put("userNames", list);
		HttpEntity<JSONObject> requestEntity = new HttpEntity<>(object, requestHeaders);
		ResponseEntity<String> response = template.exchange("https://api.longhu.net/iam-openapi/public/v2/role/getRoleListByUsernames", HttpMethod.POST, requestEntity, String.class);
		JSONObject json = JSON.parseObject(response.getBody());
    	List<Object> data = JSONArray.parseArray(json.getString("data"));
    	JSONObject parseObject = JSONObject.parseObject(json.getString("respCode"));
    	
    	if(json.getString("respCode")!=null && parseObject.get("message").equals("success")) {
			JSONObject jsonObject = JSONObject.parseObject(data.get(0).toString());
			List<Object> roleInfo = JSONArray.parseArray(jsonObject.getString("roleInfo"));
			orgCode = service.getRoleName(flag,roleInfo);
    	}
		return orgCode;
	}
	 */
	
	/**
	 * 修改mq回调
	 * @param instanceId
	 * @param type
	 * @return
	 */
	@CrossOrigin(origins = "*",maxAge = 3600)
	@PostMapping("/aabb")
	@ResponseBody
	public  JSONObject updateStateByInstanceId(String instanceId,String type) {
		
		JSONObject json = new JSONObject();
		List<FrProcessInfo> list = ifs.getList(instanceId);
		for (int i = 0; i < list.size(); i++) {
			String tableName = list.get(i).getTableName();
				type = ProcessStatus.updataStutasToString(Integer.parseInt(type));
				String sql="UPDATE "+tableName+" SET SP='"+type+"'  WHERE INSTANCE_ID= '"+instanceId+"'";//修改相应的业务表的流程状态！
				Integer integer = cdao.update(sql);
				if(integer==0 ) {
					json.put(RESULT, "修改失败");
				}else {
					json.put(RESULT, "修改表名：" + tableName+"的状态为："+type);
				}
		}
		return json;
	}}
