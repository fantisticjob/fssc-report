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
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longfor.fsscreport.approval.mapper.CommonDao;
import com.longfor.fsscreport.nc.entity.OdsNcBalanceVo;
import com.longfor.fsscreport.nc.mapper.OdsNcBalanceVoMapper;
import com.longfor.fsscreport.nc.service.IOdsNcBalanceVoService;
import com.longfor.fsscreport.utils.RemoteCallUtil2;
import com.longfor.fsscreport.utils.StringUtil;

/**
 * <p>
 * NC外系统查询科目余额表 服务实现类
 * </p>
 *
 * @author chenziyao
 * @since 2020-10-09
 */
@SuppressWarnings("all")
@Service("OdsBalanceVoServiceImpl")
public class OdsNcBalanceVoServiceImpl extends ServiceImpl<OdsNcBalanceVoMapper, OdsNcBalanceVo> implements IOdsNcBalanceVoService {

	private  final static   Logger log = LoggerFactory.getLogger(RemoteCallUtil2.class);
	private static final  String STATUS = "status";

	@Autowired
	private CommonDao dao;
	
	
	@Override
	public JSONObject saveList(String orgCode,  String kemuCode,String yearMonth) {
		
		JSONObject result = new JSONObject();
		try {
			String[] split = yearMonth.split("-");
			
			String sql="delete from ODS_NC_BALANCE_VO t "
					+ " where t.endyear='"+split[0]+"'"
					+ " and t.endmonth='"+split[1]+"'"
					+ " and t.accountcode='"+kemuCode+"'"
					+ " and t.accountingbookcode like '"+orgCode+"-%'"
					+ " and t.flag=1";
			Integer delete = dao.delete(sql);
			log.info("NC外系统查询科目余额表删除条数：{}" , delete);
			RemoteCallUtil2 utils = new RemoteCallUtil2();
			
				
			Map<String, String> map = setXml(orgCode, kemuCode,split[0],split[1]);
			map.put("type","Balance");
			JSONObject json = utils.sendSyncRequest(map);
			log.info("orgCode==" + orgCode);
			log.info("kemuCode==" + kemuCode);
			
			if(json.get("data") !=null ) {
				List<JSONObject> object = (List<JSONObject>) json.get("data");
				ArrayList<OdsNcBalanceVo> list = new ArrayList<OdsNcBalanceVo>();
				for (int k= 0; k < object.size(); k++) {
					OdsNcBalanceVo vo = JSONObject.toJavaObject(object.get(k), OdsNcBalanceVo.class);
					vo.setEndyear(split[0]);
					vo.setEndmonth(split[1]);
					vo.setFlag("1");
					if(!"总计".equals(vo.getAccountcode())) {
						list.add(vo);
					}
				}
				this.saveBatch(list);
			}
					
			result.put("status", "200");
			
		} catch (Exception e) {
			log.error("NC外系统查询科目余额表调用异常"+e);
			result.put("status", "-2");
			e.printStackTrace();
		}
		
		return  result;
	}
	
	
	
	@Override
	public JSONObject saveList(Map<String,String> parm) {
		
		JSONObject result = new JSONObject();
		result.put(STATUS, "0");
		try {
			RemoteCallUtil2 utils = new RemoteCallUtil2();
		
			QueryWrapper<OdsNcBalanceVo> wrapper = new QueryWrapper<>();
			wrapper.eq("accountcode", parm.get("kemuCode"));
			wrapper.eq("accountingbookcode", parm.get("orgCode"));
			wrapper.eq("beginyear", parm.get("year"));
			wrapper.eq("beginmonth", parm.get("month"));
			wrapper.eq("endyear", parm.get("year"));
			wrapper.eq("endmonth", parm.get("month"));
			remove(wrapper);
			Map<String, String> map = setXml(parm);
			map.put("type","Balance");
			result = utils.sendSyncRequest(map);
			
			if(result.get("data") !=null ) {
				List<JSONObject> object = (List<JSONObject>) result.get("data");
				for (int k= 0; k < object.size(); k++) {
					OdsNcBalanceVo vo = JSONObject.toJavaObject(object.get(k), OdsNcBalanceVo.class);
					
					if(parm.get("kemuCode").equals(vo.getAccountcode())) {
						vo.setBeginmonth(parm.get("year"));
						vo.setBeginyear(parm.get("month"));
						vo.setEndmonth(parm.get("year"));
						vo.setEndyear(parm.get("month"));
						vo.setFlag("1");
						result.put("qmfx", vo.getQmfx());
						result.put("qm", vo.getQm());
						if(save(vo)) {
							
							result.put("status", "200");
						}else {
							result.put("result", "nc保存数据失败");
							result.put("status", "0");
						}
					}
				}
			}else {
				
				result.put("qmfx", "0");
				result.put("qm", "0");
			}
				
		} catch (Exception e) {
			log.error("NC外系统查询科目余额表调用异常{}",e.toString());
			result.put("status", "-2");
		}
		
		return  result;
	}

	
	/**
	 * 
	 * @param parm
	 * @return
	 */
	private Map<String,String> setXml(Map<String,String> parm){
		
		Map<String,String> map = new HashMap<>();
		
		String xml="			<ilh:Balance>" + 
				"					<string> <![CDATA[<BalanceVo> " + 
				"  					<orgcode>" + 
				"  					<String>"+ parm.get("orgCode") + "</String> " + 
				"  					</orgcode>  " + 
				"  					<beginaccountcode>"+ parm.get("kemuCode") +"</beginaccountcode> " + 
				" 					<endaccountcode>"+ parm.get("kemuCode") +"</endaccountcode>  " + 
				"  					<beginyear>"+parm.get("year")+"</beginyear>  " + 
				"  					<beginmonth>"+parm.get("month")+"</beginmonth>  " + 
				"  					<endyear>"+parm.get("year")+"</endyear>  " + 
				" 					 <endmonth>"+parm.get("month")+"</endmonth> " + 
				"				</BalanceVo>]]></string>"+
				"				</ilh:Balance>";
		map.put("xml",xml);
		log.info("setXml(){}",xml);
		return map;
	}
	/**
	 * 
	 * @param orgCode
	 * @param kemuCode
	 * @param year
	 * @param lastMonth
	 * @return
	 */
	private Map<String,String> setXml(String orgCode, String kemuCode,String year,String lastMonth){
		
		Map<String,String> map = new HashMap<String, String>();
		
		String xml="			<ilh:Balance>" + 
				"					<string> <![CDATA[<BalanceVo> " + 
				"  					<orgcode>" + 
				"  					<String>"+ orgCode + "</String> " + 
				"  					</orgcode>  " + 
				"  					<beginaccountcode>"+ kemuCode +"</beginaccountcode> " + 
				" 					<endaccountcode>"+ kemuCode +"</endaccountcode>  " + 
				"  					<beginyear>"+year+"</beginyear>  " + 
				"  					<beginmonth>"+lastMonth+"</beginmonth>  " + 
				"  					<endyear>"+year+"</endyear>  " + 
				" 					 <endmonth>"+lastMonth+"</endmonth> " + 
				"				</BalanceVo>]]></string>"+
				"				</ilh:Balance>";
		map.put("xml",xml);
		return map;
	}
}
