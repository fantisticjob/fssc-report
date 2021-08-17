package com.longfor.fsscreport.xycq.thrend;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.longfor.fsscreport.xycq.entity.DwEtlTask;
import com.longfor.fsscreport.xycq.service.IDwEtlTaskService;
import com.longfor.fsscreport.xycq.service.IDwShareExportTableService;

/**
 * 线下导入表线程工具
 * @author chenziyao
 *
 */
@Component("JobNcThread")
@Scope("prototype")
public class JobNcThread extends Thread{
	
	private  final    Logger log = LoggerFactory.getLogger(JobNcThread.class);
    
	@Autowired
	private IDwShareExportTableService service;

	@Autowired
    private IDwEtlTaskService taskService;

	private String type;

	private String uuid;
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
    public void run() {
		
		
		String moduleName="";
		if("3".equals(type)) {//判断入参的类型
			
			type="getXyfkQlc3Days";
			moduleName="信用付款全流程超3天单量";
		}else if("4".equals(type)) {
			
			type="getLjkQlc4Days";
			moduleName="领借款全流程超4天单量";
		}else if("5".equals(type)) {
			
			type="getXshfQlc5Days";
			moduleName="先审后付全流程超5天单量";
		}
		log.info("名称{}",moduleName);
		log.info("数据id{}",uuid);
		try {
			log.info("线程开始休眠================");
			//Thread.sleep(1000*60);
			log.info("线程结束休眠================");
			JSONObject json = service.saveList(type);//用数据湖
			DwEtlTask task = new DwEtlTask();
			UpdateWrapper<DwEtlTask> wrapper = new UpdateWrapper<>();
			wrapper.eq("ID", uuid);
			String object = json.getString("status");
			log.info("取数结果：{}",object);
			if(json.get("status").equals("200")) {
				
				task.setEndDate(new Date());
			}else {
			
				task.setExceptionDesc(moduleName+"数据入湖失败");
			}
			
			taskService.update(task, wrapper);
			log.info("数据入湖成功{}",moduleName);
			
		} catch (Exception e) {
			
			log.error("数据入湖异常{}",e.toString());
		}

	}
}
