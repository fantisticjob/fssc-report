package com.longfor.fsscreport.nc.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.longfor.fsscreport.nc.entity.Auxiliarybalance;

/**
 * <p>
 * 辅助余额表 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-11-17
 */
public interface IAuxiliarybalanceService extends IService<Auxiliarybalance> {


	JSONObject saveList(String uuid);


}
