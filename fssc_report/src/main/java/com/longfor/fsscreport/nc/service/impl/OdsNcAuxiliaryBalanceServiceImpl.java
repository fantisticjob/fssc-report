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
import com.longfor.fsscreport.nc.entity.OdsNcAuxiliaryBalance;
import com.longfor.fsscreport.nc.mapper.OdsNcAuxiliaryBalanceMapper;
import com.longfor.fsscreport.nc.service.IOdsNcAuxiliaryBalanceService;
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
public class OdsNcAuxiliaryBalanceServiceImpl extends ServiceImpl<OdsNcAuxiliaryBalanceMapper, OdsNcAuxiliaryBalance> implements IOdsNcAuxiliaryBalanceService {
	
	private  final   Logger log = LoggerFactory.getLogger(RemoteCallUtil2.class);

	@Autowired
	private CommonDao dao;
	
	@Override
	public JSONObject saveList(Map<String,String> parm, Map<String, String> fuzhuMap) {
		
		JSONObject result = new JSONObject();
		try {
			RemoteCallUtil2 utils = new RemoteCallUtil2();

			QueryWrapper<OdsNcAuxiliaryBalance> wrapper = new QueryWrapper<OdsNcAuxiliaryBalance>();
			wrapper.eq("accountcode", parm.get("kemuCode"));
			wrapper.eq("accountingbookcode", parm.get("orgCode"));
			wrapper.eq("beginyear", parm.get("year"));
			wrapper.eq("beginmonth", parm.get("month"));
			wrapper.eq("endyear", parm.get("year"));
			wrapper.eq("endmonth", parm.get("month"));
			remove(wrapper);
			
			JSONObject jsonResult = new JSONObject();
			Map<String, String> map = setXml(parm, fuzhuMap.get(parm.get("kemuCode")));
			
			log.info("orgCode==" + parm.get("orgCode"));
			log.info("kemuCode==" + parm.get("kemuCode"));
			log.info("fuzhu==" + fuzhuMap.get(parm.get("subjectCode")));
			
			map.put("type","AuxiliaryBalance");
			JSONObject json = utils.sendSyncRequest(map);
			
			if(json.get("data") !=null ) {
				List<JSONObject> object = (List<JSONObject>) json.get("data");
				ArrayList<OdsNcAuxiliaryBalance> list = new ArrayList<OdsNcAuxiliaryBalance>();
				for (int k= 0; k < object.size(); k++) {
					 OdsNcAuxiliaryBalance vo = JSONObject.toJavaObject(object.get(k), OdsNcAuxiliaryBalance.class);
					 vo.setBeginmonth(parm.get("year"));
					 vo.setBeginyear(parm.get("month"));
					 vo.setEndmonth(parm.get("year"));
					 vo.setEndyear(parm.get("month"));
					 vo.setFlag("1");
					 list.add(vo);
				}
				if( saveBatch(list)) {
					
					result.put("status", "200");
				}else {
					result.put("result", "nc保存数据失败");
					result.put("status", "0");
				}
			}
		} catch (Exception e) {
			log.error("NC外系统查询辅助余额表调用异常"+e);
			result.put("status", "-2");
			e.printStackTrace();
		}
		return  result;
	}
	
	private Map<String,String> setXml(Map<String,String> parm,String string){
		
		Map<String,String> map = new HashMap<String, String>();
		
		String xml="<ilh:AuxiliaryBalance>"
				+ "					<string> <![CDATA["
				+ "	<AuxiliaryBalance> " + 
				"  <orgcode>" + 
				"  <String>"+ parm.get("orgCode") +"</String> " + 
				"  </orgcode>  " + 
				"  <accountcodes> " + 
				"  <String>"+ parm.get("kemuCode") +"</String> " + 
				"  </accountcodes>  " + 
				"  <accassitemcode> " + 
				string +
				"  </accassitemcode>  " + 
				"  <beginyear>"+parm.get("year") +"</beginyear>  " + 
				"  <beginmonth>"+parm.get("month") +"</beginmonth>  " + 
				"  <endyear>"+parm.get("year") +"</endyear>  " + 
				"  <endmonth>"+parm.get("month") +"</endmonth> " + 
				"</AuxiliaryBalance>"
				+ "]]></string>"+
				"	</ilh:AuxiliaryBalance>";
		map.put("xml",xml);
		return map;
		
	}

	@Override
	 public List<OdsNcAuxiliaryBalance> getAllbyOrgCode(String orgCode) {
	  String sql="SELECT * from ODS_NC_AUXILIARY_BALANCE where ORGCODE='" + orgCode + "'";
	  return dao.getListOdsNcAuxiliaryBalance(sql);
	 }
}
