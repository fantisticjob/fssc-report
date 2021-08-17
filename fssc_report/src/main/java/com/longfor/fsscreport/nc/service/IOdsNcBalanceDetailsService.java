package com.longfor.fsscreport.nc.service;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.longfor.fsscreport.nc.entity.OdsNcBalanceDetails;

/**
 * <p>
 * NC外系统查询辅助余额明细表 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-10-12
 */
public interface IOdsNcBalanceDetailsService extends IService<OdsNcBalanceDetails> {


	JSONObject saveList(Map<String, String> map);

}
