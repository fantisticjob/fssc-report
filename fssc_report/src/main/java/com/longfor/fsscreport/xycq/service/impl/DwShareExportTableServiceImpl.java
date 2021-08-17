package com.longfor.fsscreport.xycq.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longfor.fsscreport.utils.GetProperties;
import com.longfor.fsscreport.utils.HttpUtils;
import com.longfor.fsscreport.xycq.entity.DwShareExportTable;
import com.longfor.fsscreport.xycq.mapper.DwShareExportTableMapper;
import com.longfor.fsscreport.xycq.service.IDwShareExportTableService;

/**
 * <p>
 * 线下导入表 服务实现类
 * </p>
 *
 * @author chenziyao
 * @since 2020-12-25
 */
@Service
public class DwShareExportTableServiceImpl extends ServiceImpl<DwShareExportTableMapper, DwShareExportTable> implements IDwShareExportTableService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private  GetProperties properties = new GetProperties();
	
	private static final  String STATUS = "status";
	
	@Autowired
	private DwShareExportTableMapper mapper;

	private List<String> delete = new ArrayList<>();
	
	@Override
	public JSONObject saveList(String type){
		
		boolean flag =true;
		Integer total=0;
		Integer count=0;
		int errorCount = 0;
		HttpUtils utils = new  HttpUtils();
		JSONObject json = new JSONObject();

		try {
			
			delete= getDjhList(type);//得到当前类型下所有的单据编号数据
			do {
				StringBuilder buffer = new StringBuilder();
				JSONObject token = utils.getToken(utils.getParmMap());
				if(token.get("access_token")!=null && !"".equals(token.get("access_token"))) {
					
					buffer.append(properties.getProperties("getbdapi"));
					buffer.append(type);
					buffer.append("?access_token=");
					buffer.append(token.get("access_token"));
					buffer.append("&offset=");
					buffer.append(total);
				}
				JSONObject datas = utils.getDataByToken(buffer.toString());
				if("-1".equals(String.valueOf(datas.get("result")))) {
					errorCount ++;
					continue;
				}
				if("200".equals(String.valueOf(datas.get("resultcode")))) {
					
					Map<String, List<DwShareExportTable>> date = StringToDate(datas.get("records"),type);
					total += Integer.parseInt(datas.get("total").toString());
					count = Integer.parseInt(datas.get("total").toString());  
					boolean batch = true;
					if(date.get("save")!=null && date.get("save").size()>0) {
						batch = saveBatch(date.get("save"));
					}
					if(!batch) {
						logger.error("信用借款数据湖取数保存失败！报错条数为{}",total);
						json.put(STATUS,"0");
						return  json;
					}
					
					if(date.get("updata")!=null&& date.get("updata").size()>0) {
						
						batch = updateBatchById(date.get("updata"));
					}
					if(!batch) {
						logger.error("信用借款数据湖取数更新失败！报错条数为{}",total);
						json.put(STATUS,"0");
						return  json;
					}
					
					if(count>-1 && count<1000) {
						
						flag=false;
					}
					logger.info("当前数量为{}",total);
					
					if(errorCount > 1000) break;
					logger.info("error time ====={}",errorCount);
				}else {
					errorCount++;
				}
			}while(flag);
			Integer deleteByDjbh = deleteByDjbh(delete);
			logger.info("删除条数为： ====={}",deleteByDjbh);
			json.put("status", "200");
		} catch (Exception e) {
			logger.error("信用借款表数据湖取数保存失败{}",e.toString());
			json.put(STATUS, 0);
		}
		String string = json.toString();
		logger.info("json ====={}",string);
		return  json;
	}
	public Map<String,List<DwShareExportTable>>  StringToDate(Object object,String type) {
		
		Map<String,List<DwShareExportTable>> map = new HashMap<>();
		
		JSONArray array = JSONArray.parseArray(object.toString());
		ArrayList<DwShareExportTable> save = new ArrayList<>();
		ArrayList<DwShareExportTable> updata = new ArrayList<>();
		
		for (int i = 0; i < array.size(); i++) {
			DwShareExportTable obj = JSONObject.parseObject(array.get(i).toString(),DwShareExportTable.class);
			
			//如果监控看板返回了新的单据类比数据，使用该字段更新数据库
			String djlbNew = obj.getDjlb_new();
			if(StringUtils.isNotBlank(djlbNew)&& !"-".equals(djlbNew)) {	
				obj.setQlc_ejfl(djlbNew);
				obj.setDjlb_new(null);
			}
			
			
			if(!"getXyfkQlc3Days".equals(type)) {//因为经办人的字段三天跟四五天的不一致，需要转换
				String fSqr = obj.getF_sqr();//四天五天的经办人
				obj.setJnr(fSqr);
			}
			System.err.println(obj.getDjbh());
			if(delete.contains(obj.getDjbh())) {//判断所有单据号中是否包含当前的单据号		
				updata.add(obj);//如果有就更新
				delete.remove(obj.getDjbh());//并且移除当前的单据编号，从而筛选出数据库有而数据湖没有的数据
			}else {
				
				if(type.equals("getXyfkQlc3Days")) {
					
					obj.setBillboard("信用付款");
					obj.setProcess("信用付款全流程超3天单量");
				}else if(type.equals("getLjkQlc4Days")){
					
					obj.setBillboard("领借款");
					obj.setProcess("领借款全流程超4天单量");
				}else if(type.equals("getXshfQlc5Days")){
					
					obj.setProcess("先审后付全流程超5天单量");
					obj.setBillboard("先审后付");
				}
				save.add(obj);//如果没有就新增
			}
			
		}
		map.put("updata",updata);
		map.put("save",save);
		return map;
	}

	public Integer deleteByDjbh(List<String> list){
		
		int delete=0;
		for (int i = 0; i < list.size(); i++) {
			QueryWrapper<DwShareExportTable> wrapper =new  QueryWrapper<>();
			wrapper.eq("code_receipts", list.get(i));
			delete= delete +mapper.delete(wrapper);
		}
		return delete;
	}
	
	public List<String> getDjhList(String type){
		
		if(type.equals("getXyfkQlc3Days")) {
			
			type="信用付款全流程超3天单量";
		}else if(type.equals("getLjkQlc4Days")){
			
			type="领借款全流程超4天单量";
		}else if(type.equals("getXshfQlc5Days")){
			
			type="先审后付全流程超5天单量";
		}
		QueryWrapper<DwShareExportTable> wrapper =new  QueryWrapper<>();
		wrapper.isNotNull("code_receipts");
		wrapper.eq("process",type);
		List<DwShareExportTable> list = mapper.selectList(wrapper);
		ArrayList<String> djbh = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			djbh.add(list.get(i).getDjbh());
		}
		return djbh;
	}
}
