package com.longfor.fsscreport.clear.service;

import com.longfor.fsscreport.clear.entity.DwCpClearUpTotal;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 往来清理汇总表 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-10-30
 */
public interface IDwCpClearUpTotalService extends IService<DwCpClearUpTotal> {

	public JSONObject numberOfLocks(String accountsId, String quarter, String subjectCode);

	JSONObject initData(String quarter, String accounts);

	public List<DwCpClearUpTotal> getAllListBean(QueryWrapper<DwCpClearUpTotal> wrapper);

	public JSONObject getNcDate(String syncDateStr, String quarter, String accountsId, String subjectCode);

	public JSONObject initData1(String quarter, String accountsId);


}
