package com.longfor.fsscreport.nc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longfor.fsscreport.approval.mapper.CommonDao;
import com.longfor.fsscreport.nc.entity.OdsNcBalanceDetail;
import com.longfor.fsscreport.nc.mapper.OdsNcBalanceDetailMapper;
import com.longfor.fsscreport.nc.service.IOdsNcBalanceDetailService;
import com.longfor.fsscreport.utils.RemoteCallUtil2;
import com.longfor.fsscreport.utils.StringUtil;

/**
 * <p>
 * NC外系统查询科目余额明细表 服务实现类
 * </p>
 *
 * @author chenziyao
 * @since 2020-10-12
 */
@SuppressWarnings("all")
@Service
public class OdsNcBalanceDetailServiceImpl extends ServiceImpl<OdsNcBalanceDetailMapper, OdsNcBalanceDetail> implements IOdsNcBalanceDetailService {
	
	private  final static   Logger log = LoggerFactory.getLogger(RemoteCallUtil2.class);

	@Autowired
	private CommonDao dao;
	
	@Override
	public JSONObject saveList(String orgCode, String kemuCode, String yearMonth) {
		JSONObject result = new JSONObject();
		try {
			
			String[] split = yearMonth.split("-");
			//
			String sql="delete from ODS_NC_BALANCE_DETAIL t "
					+ " where t.year='"+split[0]+"'"
					+ " and t.month='"+split[1]+"'"
					+ " and t.accountcode='"+kemuCode+"'"
					+ " and t.accountingbookcode like '"+orgCode+"-%'"
					+ " and t.flag=1";
			Integer delete = dao.delete(sql);
			log.info("NC外系统查询科目余额明细表删除条数：{}" , delete);
			RemoteCallUtil2 utils = new RemoteCallUtil2();
			
			
			Map<String, String> map = setXml(orgCode, kemuCode,split[0],split[1]);
			
			map.put("type","BalanceDetail");
			JSONObject json = utils.sendSyncRequest(map);
			log.info("orgCode==" , orgCode);
			log.info("orgCode==" , orgCode);
			log.info("kemuCode==" , kemuCode);
			
			if(json.get("data") !=null ) {
				List<JSONObject> object = (List<JSONObject>) json.get("data");
				ArrayList<OdsNcBalanceDetail> list = new ArrayList<OdsNcBalanceDetail>();
				for (int k = 0; k < object.size(); k++) {
					OdsNcBalanceDetail vo = JSONObject.toJavaObject(object.get(k), OdsNcBalanceDetail.class);
					if("null".equals(vo.getMonth()) || StringUtils.isBlank(vo.getAccountcode())) {
						continue;
					}
					vo.setFlag("1");
					list.add(vo);
				}
				if(list!=null && list.size()>0) {
					this.saveBatch(list);
				}
			}
			
			
			result.put("status", "200");
		} catch (Exception e) {
			log.error("NC外系统查询科目余额明细表调用异常"+e);
			result.put("status", "-2");
			e.printStackTrace();
		}

		return  result;
	}
	
	private Map<String,String> setXml(String orgName, String kemuCode,String year,String month){
		
		Map<String,String> map = new HashMap<String, String>();
		
		String xml="<ilh:BalanceDetail>"
				+ "					<string> <![CDATA["
				+ "<BalanceDetail>"+
				"		  <orgcode>"+orgName+"</orgcode>  " + 
				"		  <accountcode>"+kemuCode+"</accountcode> " + 
				"		   <beginyear>"+year+"</beginyear>  " + 
				"		  <beginmonth>"+month+"</beginmonth>  " + 
				"		  <endyear>"+year+"</endyear>  " + 
				"		  <endmonth>"+month+"</endmonth> " + 
				"	</BalanceDetail>"
				+ "]]></string>"+
				"	</ilh:BalanceDetail>"	;
		map.put("xml",xml);
		return map;
		
	}
	
	
	public static void main(String[] args) {
		
		String sql="delete from ODS_NC_BALANCE_DETAIL t "
				+ " where t.year='"+2020+"'"
				+ " and t.month='"+05+"'"
				+ " and t.accountcode='"+1111+"'"
				+ " and t.accountcode like '"+02551+"-%'"
				+ " and t.flag=1";
		
		
		System.err.println(sql);
		
		
	}
	

}
