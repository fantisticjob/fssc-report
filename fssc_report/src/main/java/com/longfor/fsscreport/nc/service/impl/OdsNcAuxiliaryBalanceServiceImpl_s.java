package com.longfor.fsscreport.nc.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotBlank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longfor.fsscreport.nc.entity.NcTask;
import com.longfor.fsscreport.nc.entity.OdsNcAuxiliaryBalance;
import com.longfor.fsscreport.nc.mapper.OdsNcAuxiliaryBalanceMapper;
import com.longfor.fsscreport.nc.service.INcTaskService;
import com.longfor.fsscreport.nc.service.IOdsNcAuxiliaryBalanceService_s;
import com.longfor.fsscreport.utils.RemoteCallUtil2;
import com.longfor.fsscreport.utils.StringUtil;

/**
 * <p>
 * NC外系统查询辅助余额表 服务实现类
 * </p>
 *
 * @author chenziyao
 * @since 2020-10-10
 */
@Service
@SuppressWarnings("all")
public class OdsNcAuxiliaryBalanceServiceImpl_s extends ServiceImpl<OdsNcAuxiliaryBalanceMapper, OdsNcAuxiliaryBalance> implements IOdsNcAuxiliaryBalanceService_s {
	
	private  final   Logger log = LoggerFactory.getLogger(RemoteCallUtil2.class);
	
	
	@Autowired
	private INcTaskService ncTaskService;
	
	@Override
	public JSONObject saveList(Map<String,String> parm) {
		
		JSONObject result = new JSONObject();
		try {
			RemoteCallUtil2 utils = new RemoteCallUtil2();
			
			
			String uuid = StringUtil.getUUID();
			Map<String, String> map = setXml( parm,uuid);
			
			log.info("kemuCode==" + parm.get("kemuCode"));
			
			map.put("type","FRPlugin");
			JSONObject json = utils.sendSyncRequest(map);
			
			if(json.get("data") !=null ) {
				
				NcTask nc = new NcTask();
				nc.setAccountId(parm.get("orgCode"));
				nc.setBatchnumber(uuid);
				nc.setBegintime(new Date());
				nc.setOpraterFlag("2");
				nc.setCompanyName(parm.get("kemuCode"));
				nc.setQuarter(parm.get("quarter"));
				boolean save = ncTaskService.save(nc);
				if(save) {
					
					for (int i=0 ;i<14400; i++) {
						QueryWrapper<NcTask> queryWrapper = new QueryWrapper<NcTask>();
						queryWrapper.eq("BATCHNUMBER", uuid);
						NcTask one = ncTaskService.getOne(queryWrapper);
						if(one.getEndtimeFr()!=null ) {
							result.put("status", "200");
							result.put("data", uuid);
							result.put("result", "nc取数成功！");
							break;
						}
						Thread.sleep(1000);
					}
				}
			}else {
				result.put("status", "0");
				result.put("result", "nc取数失败！");
			}
		} catch (Exception e) {
			log.error("NC外系统查询辅助余额表调用异常"+e);
			result.put("result", "NC外系统查询辅助余额表调用异常");
			result.put("status", "-2");
			e.printStackTrace();
		}

		return  result;
	}
	
	private Map<String,String> setXml( Map<String,String> parm, String uuid){
		
		Map<String,String> map = new HashMap<String, String>();
		Map<String, String> fuzhu = initKemuFuzhu();
		String string = fuzhu.get(parm.get("kemuCode"));
		String xml="<ilh:FRPlugin>"
				+"        <year>"+parm.get("year")+"</year>" + 
				"         <month>"+parm.get("month")+"</month>" + 
				"         <accountcode>"+parm.get("kemuCode")+"</accountcode>" + 
				"         <orgcode>"+parm.get("orgCode")+"</orgcode>" + 
				"         "+string+ 
				"         <batchnumber>"+uuid+"</batchnumber>"+
				"	</ilh:FRPlugin>";
		map.put("xml",xml);
		return map;
		
	}
	//辅助余额对应参数
	public static Map<String,String> initKemuFuzhu() {
		   
		Map<String,String> kemuFuzhu = new HashMap<String,String>();
	      if(kemuFuzhu==null ) {
	    	  kemuFuzhu = new HashMap<String,String>();
	      }
	      
	      kemuFuzhu.put("113303", "<FZX>RY</FZX>");
	      kemuFuzhu.put("113304", "<FZX>KS</FZX><FZX>RY</FZX>");
	      kemuFuzhu.put("113308", "<FZX>KS</FZX><FZX>RY</FZX>");
	      kemuFuzhu.put("115101", "<FZX>KS</FZX><FZX>RY</FZX>");
	      kemuFuzhu.put("115102", "<FZX>KS</FZX><FZX>RY</FZX>");
	      kemuFuzhu.put("115104", "<FZX>KS</FZX><FZX>RY</FZX>"); 
	      kemuFuzhu.put("113301", "<FZX>KS</FZX>");
	      kemuFuzhu.put("218102", "<FZX>KS</FZX>");
	      kemuFuzhu.put("21210401", "<FZX>KS</FZX><FZX>RY</FZX><FZX>DJH</FZX>");
	      kemuFuzhu.put("212102", "<FZX>KS</FZX>");
	      kemuFuzhu.put("218101", "<FZX>KS</FZX>");
	      kemuFuzhu.put("11519998", "<FZX>KS</FZX><FZX>XM</FZX>");
	      kemuFuzhu.put("11519999", "<FZX>KS</FZX><FZX>XM</FZX>");
	   return kemuFuzhu;
	   
	  }
	public static void main(String[] args) {
		
		Calendar currCal = Calendar.getInstance();
		int day = currCal.get(Calendar.DAY_OF_MONTH);
		int month = currCal.get(Calendar.MONTH);
		if (day < 8) {
			currCal.add(Calendar.MONTH, -35);
		}else {
			currCal.add(Calendar.MONTH, -36);
		}
		
		int year = currCal.get(Calendar.YEAR);
		month = currCal.get(Calendar.MONTH);
		ArrayList<String> months = new ArrayList<String>();
		ArrayList<Integer> years = new ArrayList<Integer>();
		
		String newMonth=null;
		for (int i = 1; i <=36; i++) {
			month++;
			if(month==13) {
				month=1;
				year++;
			}
			if(month>9) {
				newMonth=month+"";
			}else {
				newMonth="0"+month;
			}
			months.add(newMonth);
			
			years.add(year);
		}
	}
}
