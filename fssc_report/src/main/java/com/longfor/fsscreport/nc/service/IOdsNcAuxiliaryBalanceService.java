package com.longfor.fsscreport.nc.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.longfor.fsscreport.nc.entity.OdsNcAuxiliaryBalance;

/**
 * <p>
 * NC外系统查询辅助余额表 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-10-10
 */
public interface IOdsNcAuxiliaryBalanceService extends IService<OdsNcAuxiliaryBalance> {


	List<OdsNcAuxiliaryBalance> getAllbyOrgCode(String orgCode);

	JSONObject saveList(Map<String, String> parm, Map<String, String> fuzhuMap);

}
