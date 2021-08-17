package com.longfor.fsscreport.nc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longfor.fsscreport.nc.entity.Auxiliarybalance;
import com.longfor.fsscreport.nc.entity.OdsNcAuxiliaryBalance;
import com.longfor.fsscreport.nc.mapper.AuxiliarybalanceMapper;
import com.longfor.fsscreport.nc.service.IAuxiliarybalanceService;
import com.longfor.fsscreport.nc.service.IOdsNcAuxiliaryBalanceService;

/**
 * <p>
 * 辅助余额表 服务实现类
 * </p>
 *
 * @author chenziyao
 * @since 2020-11-17
 */
@Service
public class AuxiliarybalanceServiceImpl extends ServiceImpl<AuxiliarybalanceMapper, Auxiliarybalance> implements IAuxiliarybalanceService {

	@Autowired
	private AuxiliarybalanceMapper mapper;
	
	@Autowired 
	private IOdsNcAuxiliaryBalanceService service;
	

	private static final  String STATUS="status";
	private static final  String RESULT="result";
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Transactional
	@Override
	public JSONObject saveList(String uuid) {
		
		JSONObject json = new JSONObject();
		
		try {
			QueryWrapper<Auxiliarybalance> wrapper = new QueryWrapper<Auxiliarybalance>();
			wrapper.eq("BATCHERNUMBER",uuid);
			List<Auxiliarybalance> list = mapper.selectList(wrapper);
			List<OdsNcAuxiliaryBalance> arrayList = new ArrayList<OdsNcAuxiliaryBalance>();
			for (int i = 0; i < list.size(); i++) {
				
				Auxiliarybalance auxiliarybalance = list.get(i);
				OdsNcAuxiliaryBalance balance = new OdsNcAuxiliaryBalance().convertFrom(auxiliarybalance);//实体类互转
				arrayList.add(balance);
				if(arrayList.size()==1000) {
					if(service.saveBatch(arrayList)) {
						arrayList.clear();
					}else {
						json.put("result", "辅助余额表取数失败！" );
						json.put("status", "-1" );
						break;
					}
				}
			}
			json.put(RESULT, "辅助余额表取数成功！" );
			json.put(STATUS, "200" );
		} catch (Exception e) {
			json.put(RESULT, "辅助余额表取数异常！" );
			json.put(STATUS, "-2" );
			log.error("辅助余额表取数异常{}",e.toString());
		}
		return json;
	}
}
