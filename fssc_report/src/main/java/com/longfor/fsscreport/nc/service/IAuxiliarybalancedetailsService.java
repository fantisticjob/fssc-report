package com.longfor.fsscreport.nc.service;

import com.longfor.fsscreport.nc.entity.Auxiliarybalancedetails;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 辅助余额明细表 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-11-17
 */
public interface IAuxiliarybalancedetailsService extends IService<Auxiliarybalancedetails> {

	JSONObject saveList(String uuid);

}
