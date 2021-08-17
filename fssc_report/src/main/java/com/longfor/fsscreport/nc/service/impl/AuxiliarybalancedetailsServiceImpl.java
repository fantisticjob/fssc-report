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
import com.longfor.fsscreport.nc.entity.Auxiliarybalancedetails;
import com.longfor.fsscreport.nc.entity.OdsNcBalanceDetails;
import com.longfor.fsscreport.nc.mapper.AuxiliarybalancedetailsMapper;
import com.longfor.fsscreport.nc.service.IAuxiliarybalancedetailsService;
import com.longfor.fsscreport.nc.service.IOdsNcBalanceDetailsService;

/**
 * <p>
 * 辅助余额明细表 服务实现类
 * </p>
 *
 * @author chenziyao
 * @since 2020-11-17
 */
@Service
public class AuxiliarybalancedetailsServiceImpl extends ServiceImpl<AuxiliarybalancedetailsMapper, Auxiliarybalancedetails> implements IAuxiliarybalancedetailsService {
	@Autowired
	private AuxiliarybalancedetailsMapper mapper;
	
	@Autowired 
	private IOdsNcBalanceDetailsService service;
	
	private static final  String STATUS="status";
	private static final  String RESULT="result";
	private final Logger log = LoggerFactory.getLogger(getClass());

	
	@Transactional
	@Override
	public JSONObject saveList(String uuid) {
		
		JSONObject json = new JSONObject();
		
		try {
			QueryWrapper<Auxiliarybalancedetails> wrapper = new QueryWrapper<Auxiliarybalancedetails>();
			wrapper.eq("BATCHERNUMBER",uuid);
			List<Auxiliarybalancedetails> list = mapper.selectList(wrapper);
			List<OdsNcBalanceDetails> arrayList = new ArrayList<OdsNcBalanceDetails>();
			for (int i = 0; i < list.size(); i++) {
				
				Auxiliarybalancedetails auxiliarybalance = list.get(i);
				OdsNcBalanceDetails balance = new OdsNcBalanceDetails().convertFrom(auxiliarybalance);//实体类互转
				arrayList.add(balance);
				
				if(arrayList.size()==1000) {
					if(service.saveBatch(arrayList)) {
						arrayList.clear();
					}else {
						json.put("result", "辅助余额明细取数失败！" );
						json.put(STATUS, "-1" );
						break;
					}
				}
			}
			json.put(RESULT, "辅助余额表取数成功！" );
			json.put(STATUS, "200" );
		} catch (Exception e) {
			json.put(RESULT, "辅助余额表取数异常！" );
			json.put(STATUS, "-2" );
			log.error("辅助余额明细表取数异常{}",e.toString());
		}
		return json;
	}
}
