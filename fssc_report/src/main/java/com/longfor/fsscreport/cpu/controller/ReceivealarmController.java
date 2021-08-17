package com.longfor.fsscreport.cpu.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.longfor.fsscreport.cpu.entity.ReceiveAlarm;
import com.longfor.fsscreport.cpu.entity.ReceiveAlarmDetail;
import com.longfor.fsscreport.cpu.entity.ReceiveAlarmTamp;
import com.longfor.fsscreport.cpu.service.ICpuReceivealarmDetailService;
import com.longfor.fsscreport.cpu.service.ICpuReceivealarmService;
import com.longfor.fsscreport.cpu.service.ICpuReceivealarmTampService;

/**
 * <p>
 *  监控看板前端控制器
 * </p>
 *
 * @author chenziyao
 * @since 2020-11-19
 */
@RestController
@RequestMapping("/cpu")
public class ReceivealarmController  {

	private static final  String RESULT="result";
	
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private ICpuReceivealarmService receivealarmService;
	
	@Autowired
	private ICpuReceivealarmDetailService receivealarmDetailService;
	
	@Autowired
	private ICpuReceivealarmTampService receivealarmTampService;
	
	@CrossOrigin(origins = "*",maxAge = 3600)
	@RequestMapping("/saveDate")
	@ResponseBody
	public JSONObject numberOfLocks( HttpServletRequest request,@RequestBody Map<String,Object> map) {
		JSONObject json = new JSONObject();
		try {
			ReceiveAlarm reca = JSON.parseObject(JSON.toJSONString(map), ReceiveAlarm.class);

			List<String> groups = reca.getGroups();
			if(groups==null )  groups=new ArrayList<>();
			StringBuilder group = new StringBuilder();//
			
			for (int i = 0; i < groups.size(); i++) {
				group.append(groups.get(i));
				if(i!=groups.size()-1) {
					group.append(",");
				}
				reca.setGroup(group.toString());
				
			}
			
			List<String> users = reca.getUsers();
			if(users==null )  users=new ArrayList<>();
			StringBuilder user = new StringBuilder();//
			for (int i = 0; i < users.size(); i++) {
				
				user.append(users.get(i));
				if(i!=users.size()-1) {
					user.append(",");
				}
				reca.setUser(user.toString());
				
			}
			
			List<ReceiveAlarmDetail> detail = reca.getDetail();
			reca.setGroups(null);
			reca.setDetail(null);
			reca.setUsers(null);
			Integer saveBean = receivealarmService.saveBean(reca);
			
			if(saveBean!=null && saveBean>0) {
				log.info("ReceiveAlar保存成功");
				for (int i = 0; i < detail.size(); i++) {
					
					ReceiveAlarmDetail alarmDetail = detail.get(i);
					List<ReceiveAlarmTamp> points = alarmDetail.getPoints();
					
					alarmDetail.setRid(saveBean);
					alarmDetail.setPoints(null);
					Integer did = receivealarmDetailService.saveBean(alarmDetail);
					log.info("ReceiveAlarmDetail保存成功");
					if(did!=null && did >0) {
						
						for (int j = 0; j < points.size(); j++) {
							ReceiveAlarmTamp tamp = points.get(i);
							tamp.setDid(did);
							receivealarmTampService.save(tamp);
							log.info("ReceiveAlarmTamp保存成功");
						}
					}
					
				}
				json.put(RESULT, "调用成功");
			}else {
				log.info("ReceiveAlarm保存失败");
			}
			
		} catch (Exception e) {
			json.put(RESULT, "调用异常");
			log.error("监控看板调用异常{}",e.toString());
		}
		
		return json;
	}
}
