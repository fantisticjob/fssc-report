package com.longfor.fsscreport.nc.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.longfor.fsscreport.nc.entity.OdsNcBalanceDetail;

/**
 * <p>
 * NC外系统查询科目余额明细表 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-10-12
 */
public interface IOdsNcBalanceDetailService extends IService<OdsNcBalanceDetail> {

	JSONObject saveList(String orgCodeList, String kemuCodeList, String yearMonth);
}
