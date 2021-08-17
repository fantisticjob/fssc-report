package com.longfor.fsscreport.xycq.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.longfor.fsscreport.FsscReportApplication;
import com.longfor.fsscreport.utils.StringUtil;
import com.longfor.fsscreport.xycq.entity.DwEtlTask;
import com.longfor.fsscreport.xycq.service.IDwEtlTaskService;
import com.longfor.fsscreport.xycq.thrend.JobNcThread;

import groovy.util.logging.Slf4j;

/**
 * <p>
 * 信用超期 前端控制器
 * </p>
 *
 * @author chenziyao
 * @param <E>
 * @since 2020-07-28
 */
@RestController
@RequestMapping("/xycq")
@Component
@Slf4j
public class DwShareExportTableController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private IDwEtlTaskService taskService;
    
	@CrossOrigin(origins = "*",maxAge = 3600)
    @RequestMapping(value = "/syncOfflineData/{type}")
	@ResponseBody
	public JSONObject syncOfflineData(@PathVariable String type ) {
		
		JSONObject json = new JSONObject();
		if(StringUtils.isBlank(type)) {
			
			json.put("code", "1");
			json.put("id", "");
			json.put("msg", "参数为空");
			log.info("超期任务保存失败");
			return json;
		}
		log.info("超期付款远程连接type："+type);
		
		try {
			DwEtlTask task = new DwEtlTask();
			String uuid = StringUtil.getUUID();
			Date date = new Date();
			
			String moduleName = "";
			if("3".equals(type)) {//判断入参的类型
				
				moduleName="信用付款全流程超3天单量";
			}else if("4".equals(type)) {
				
				moduleName="领借款全流程超4天单量";
			}else if("5".equals(type)) {
				
				moduleName="先审后付全流程超5天单量";
			}
			log.info("moduleName："+moduleName);
			task.setId(uuid);
			task.setDataDate(date);
			task.setJobName("J_CQFK_ANALYE");
			task.setStartDate(date);
			task.setModuleName(moduleName);
			if(taskService.save(task)) {
				
				//如果保存成功，开启线程，数据入湖
				JobNcThread thread = (JobNcThread)FsscReportApplication.configurableApplicationContext.getBean("JobNcThread"); 
				thread.setType(type);
				thread.setUuid(uuid);
				thread.run();
				
				json.put("code", "0");
				json.put("id", uuid);
				json.put("msg", "正常启动");
				log.info("超期任务保存成功");
				return json;
			}else {
				json.put("code", "1");
				json.put("id", "");
				json.put("msg", "异常启动");
				log.info("超期任务保存失败");
				return json;
			}
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.put("code", "500");
			json.put("id", "");
			json.put("msg", "程序异常，请联系管理员");
			return json;
		}
		
	}
	@CrossOrigin(origins = "*",maxAge = 3600)
    @RequestMapping(value = "/checkJobStatus/{id}")
	@ResponseBody
	public JSONObject checkJobStatus(@PathVariable String id ) {
		
		JSONObject json = new JSONObject();
		if(StringUtils.isBlank(id)) {
			json.put("code", "2");
			json.put("data", "failed");
			return json;
		}
		QueryWrapper<DwEtlTask> wrapper = new QueryWrapper<DwEtlTask>();
		wrapper.eq("ID", id);
		DwEtlTask task = taskService.getOne(wrapper);
		if(task != null && task.getEndDate()!=null ){
			json.put("code", "0");
			json.put("data", "success");
		}else if(StringUtils.isNotBlank(task.getExceptionDesc())){
			json.put("code", "2");
			json.put("data", "failed");
		}else {
			json.put("code", "1");
			json.put("data", "running");
		}
		
		return json;
	}
}
