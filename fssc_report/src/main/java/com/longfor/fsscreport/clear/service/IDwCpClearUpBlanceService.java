package com.longfor.fsscreport.clear.service;

import com.longfor.fsscreport.clear.entity.DwCpClearUpBlance;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 往来清理-往来余额类 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-10-30
 */
public interface IDwCpClearUpBlanceService extends IService<DwCpClearUpBlance> {

	List<DwCpClearUpBlance> getListBeanByWrapper(QueryWrapper<DwCpClearUpBlance> queryWrapper);
	

	JSONObject numberOfLocksYe(String accountsId, String subjectCode, String quarter);


}
