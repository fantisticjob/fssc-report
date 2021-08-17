package com.longfor.fsscreport.nc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longfor.fsscreport.approval.mapper.CommonDao;
import com.longfor.fsscreport.nc.entity.OdsNcAuxiliaryBalance;
import com.longfor.fsscreport.nc.entity.OdsNcBalanceDetail;
import com.longfor.fsscreport.nc.entity.OdsNcBalanceDetails;
import com.longfor.fsscreport.nc.mapper.OdsNcBalanceDetailsMapper;
import com.longfor.fsscreport.nc.service.IOdsNcAuxiliaryBalanceService;
import com.longfor.fsscreport.nc.service.IOdsNcBalanceDetailsService;
import com.longfor.fsscreport.utils.RemoteCallUtil2;
import com.longfor.fsscreport.utils.StringUtil;

/**
 * <p>
 * NC外系统查询辅助余额明细表 服务实现类
 * </p>
 *
 * @author chenziyao
 * @since 2020-10-12
 */
@SuppressWarnings("all")
@Service
public class OdsNcBalanceDetailsServiceImpl extends ServiceImpl<OdsNcBalanceDetailsMapper, OdsNcBalanceDetails> implements IOdsNcBalanceDetailsService {
	
	private  final static   Logger log = LoggerFactory.getLogger(RemoteCallUtil2.class);
	
	@Autowired
	private IOdsNcAuxiliaryBalanceService odsNcAuxiliaryBalanceService;
	@Autowired
	private CommonDao dao;
	

	@Override
	public JSONObject saveList(Map<String, String> map) {
		
		JSONObject result = new JSONObject();

		try {
			QueryWrapper<OdsNcBalanceDetails> wrapper = new QueryWrapper<OdsNcBalanceDetails>();
			wrapper.eq("accountcode", map.get("kemuCode"));
			wrapper.eq("beginyear", map.get("year"));
			wrapper.eq("beginmonth", map.get("month"));
			wrapper.eq("endyear", map.get("year"));
			wrapper.eq("endmonth", map.get("month"));
			remove(wrapper);
			
			List<OdsNcAuxiliaryBalance> list 
				= odsNcAuxiliaryBalanceService.getAllbyOrgCode(map.get("orgCode"));
			if(list.size() > 0) {
				result = save(list,map);
			}
			
		} catch (Exception e) {
			log.error("NC外系统查询辅助余额明细表异常"+e);
			result.put("status", "-2");
			e.printStackTrace();
		}
		
		
		return  result;
	}
	
	public JSONObject save(List<OdsNcAuxiliaryBalance> bean,Map<String,String> parm) {
		
		JSONObject result = new JSONObject();
		RemoteCallUtil2 utils = new RemoteCallUtil2();
		ArrayList<OdsNcBalanceDetails> list = new ArrayList<OdsNcBalanceDetails>();
		Integer count = 0;
		try {
			for (int i = 0; i < bean.size(); i++) {
				OdsNcAuxiliaryBalance balance = bean.get(i);

				Map<String, String> map = setXml(balance,parm);
				map.put("type","AuxiliaryBalanceDetails");
				result = utils.sendSyncRequest(map);
				
				if(result.get("data") !=null ) {
					List<JSONObject> object = (List<JSONObject>) result.get("data");
					for (int j = 0; j < object.size();j++) {
						OdsNcBalanceDetails vo = JSONObject.toJavaObject(object.get(j), OdsNcBalanceDetails.class);
						vo.setBalanceid(balance.getId());
						vo.setBeginmonth(parm.get("year"));
						vo.setBeginyear(parm.get("month"));
						vo.setEndmonth(parm.get("year"));
						vo.setEndyear(parm.get("month"));
						vo.setEndyear(parm.get("month"));
						vo.setFlag("1");
						if(StringUtils.isBlank(vo.getAccountcode())) {
							continue;
						}
						list.add(vo);
					}
					if(saveBatch(list)) {
						result.put("status", "200");
						log.info("第"+count+++"次保存！");
					}else {
						result.put("result", "nc保存数据失败");
						result.put("status", "0");
					}
				}
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return result;
		
	}
	
	private Map<String,String> setXml(OdsNcAuxiliaryBalance balance,Map<String,String> parm){
		
		Map<String,String> map = new HashMap<String, String>();
		
		
		
		StringBuilder sb = new StringBuilder();
		
		if(StringUtils.isNotBlank(balance.getKscode())) {
			sb.append("<accassitem> ");
			sb.append("<checktypecode>KS</checktypecode>");
			sb.append("<checktypename>客商</checktypename>");
			sb.append("<checkvaluecode>"+balance.getKscode()+"</checkvaluecode>");
			sb.append("<checkvaluename>"+balance.getKsname()+"</checkvaluename>");
			sb.append("</accassitem> ");
		}
		if(StringUtils.isNotBlank(balance.getXmcode())) {
			sb.append("<accassitem> ");
			sb.append("<checktypecode>XM</checktypecode>");
			sb.append("<checktypename>项目</checktypename>");
			sb.append("<checkvaluecode>"+balance.getXmcode()+"</checkvaluecode>");
			sb.append("<checkvaluename>"+balance.getXmname()+"</checkvaluename>");
			sb.append("</accassitem> ");
		}
		if(StringUtils.isNotBlank(balance.getRycode())) {
			sb.append("<accassitem> ");
			sb.append("<checktypecode>RY</checktypecode>");
			sb.append("<checktypename>人员</checktypename>");
			sb.append("<checkvaluecode>"+balance.getRycode()+"</checkvaluecode>");
			sb.append("<checkvaluename>"+balance.getRyname()+"</checkvaluename>");
			sb.append("</accassitem> ");
		}
		if(StringUtils.isNotBlank(balance.getDjhcode())) {
			sb.append("<accassitem> ");
			sb.append("<checktypecode>DJH</checktypecode>");
			sb.append("<checktypename>单据号</checktypename>");
			sb.append("<checkvaluecode>"+balance.getDjhcode()+"</checkvaluecode>");
			sb.append("<checkvaluename>"+balance.getDjhname()+"</checkvaluename>");
			sb.append("</accassitem> ");
		}
		String xml="				<ilh:AuxiliaryBalanceDetails>"
				+ "					<string> <![CDATA["
				+ "				<AuxiliaryBalanceDetails> " + 
				"  <orgcode>"+balance.getOrgcode()+"</orgcode>  " + 
				"  <accountcode>"+balance.getAccountcode()+"</accountcode>  " + 
				"  <accassitems> " + 
								sb.toString()+
				"  </accassitems>  " + 
				"  <beginyear>"+parm.get("year")+"</beginyear>  " + 
				"  <beginmonth>"+parm.get("month")+"</beginmonth>  " + 
				"  <endyear>"+parm.get("year")+"</endyear>  " + 
				"  <endmonth>"+parm.get("month")+"</endmonth> " + 
				"</AuxiliaryBalanceDetails>"
				+ "]]></string>"
				+ "				</ilh:AuxiliaryBalanceDetails>";
		map.put("xml",xml);
		return map;
		
	}
}

