package com.longfor.fsscreport.clear.service;

import com.longfor.fsscreport.clear.entity.DwCpClearUpDetail;
import com.longfor.fsscreport.vo.Message;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 往来清理-往来明细类 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-10-28
 */
public interface IDwCpClearUpDetailService extends IService<DwCpClearUpDetail> {


	List<DwCpClearUpDetail> getListBeanByWrapper(QueryWrapper<DwCpClearUpDetail> queryWrapper);

	JSONObject numberOfLocksMx(String accountsId, String subjectCode, String quarter);

	Message<Object> takeDataBlendingFlag(String selectType, String blendingType, String id, String userCode,
			String selectFlag, String month, String accountsId, String subjectCode);


	Message<Object> takeBlendingUpdate(String userCode, String operateFlag, String month, String accountsId,
			String subjectCode);

	JSONObject updataClearDetailStatus(String ids, List<Long> list);

	JSONObject updSendMsgFlag(String ids);

	/**
	 * 取消勾兑
	 */
	JSONObject notClearDetailStatus(String uuid, List<String> array);

	Message<Object> clearBlendInit(String userCode, String operateFlag, String month, String accountsId,
			String subjectCode);

	Integer deleteByWrapper(QueryWrapper<DwCpClearUpDetail> querywrapper);


    JSONObject takeBlendingHigh(String quarter, String accountsId, String subjectCode,String userCode);

	JSONObject notTakeBlendingHigh(String quarter, String accountsId, String subjectCode,String userCode);

	JSONObject updSendMsgFlagCond(String quarter, String accountsId, String subjectCode,String userCode);
}
