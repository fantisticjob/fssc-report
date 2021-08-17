package com.longfor.fsscreport.nc.service;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.longfor.fsscreport.nc.entity.OdsNcBalanceVo;

/**
 * <p>
 * NC外系统查询科目余额表 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-10-09
 */
public interface IOdsNcBalanceVoService extends IService<OdsNcBalanceVo> {

	JSONObject saveList(Map<String, String> map);

	JSONObject saveList(String orgCode, String kemuCode,String yearMonth);

}
