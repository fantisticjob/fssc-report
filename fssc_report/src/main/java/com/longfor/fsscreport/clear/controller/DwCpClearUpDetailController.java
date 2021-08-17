package com.longfor.fsscreport.clear.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.longfor.fsscreport.clear.service.IDwCpClearUpDetailService;
import com.longfor.fsscreport.vo.Message;

/**
 * <p>
 * 往来清理-往来明细类 前端控制器
 * </p>
 *
 * @author chenziyao
 * @since 2020-10-28
 */
@RestController
@RequestMapping("/clear")
public class DwCpClearUpDetailController  {

	
	
	
	@Autowired
	private IDwCpClearUpDetailService dwCpClearUpDetailService;
	
	
	/**
	 * 往来清理勾兑
	 * @param ids 主键id
	 * @return old
	 *
	 */
	@CrossOrigin(origins = "*",maxAge = 3600)
	@PostMapping(value = "/updataClearDetailStatus" )
	@ResponseBody
	public JSONObject updataClearDetailStatus(String ids) {

		return dwCpClearUpDetailService.updataClearDetailStatus(ids,null);
	}
	/**
	 * 取消勾兑
	 * @param quarter：季度
	 * accountsId：账套id
	 * subjectCode：科目
	 * @return old
	 *
	 */
	@CrossOrigin(origins = "*",maxAge = 3600)
	@PostMapping(value = "/notTakeBlendingHigh" )
	@ResponseBody
	public JSONObject notTakeBlendingHigh(String quarter,String accountsId,String subjectCode,String userCode) {

		return dwCpClearUpDetailService.notTakeBlendingHigh(quarter,accountsId,subjectCode,userCode);
	}

	/**
	 * 进行勾兑
	 * @param quarter：季度
	 * accountsId：账套id
	 * subjectCode：科目
	 * @return old
	 *
	 */
	@CrossOrigin(origins = "*",maxAge = 3600)
	@PostMapping(value = "/takeBlendingHigh" )
	@ResponseBody
	public JSONObject takeBlendingHigh(String quarter,String accountsId,String subjectCode,String userCode) {

		return dwCpClearUpDetailService.takeBlendingHigh(quarter,accountsId,subjectCode,userCode);
	}
	/**
	 * 发送龙信确认
	 * @param quarter：季度 accountsId：账套id  subjectCode：科目
	 * @return old
	 *
	 */
	@CrossOrigin(origins = "*",maxAge = 3600)
	@PostMapping(value = "/sendLongforMsgConfirmCondition" )
	@ResponseBody
	public JSONObject sendLongforMsgConfirmCondition(String quarter,String accountsId,String subjectCode,String userCode) {

		return dwCpClearUpDetailService.updSendMsgFlagCond(quarter,accountsId,subjectCode,userCode);
	}
	/**
	 * 发送龙信确认
	 * @param ids 主键ids
	 * @return old
	 *
	 */
	@CrossOrigin(origins = "*",maxAge = 3600)
	@PostMapping(value = "/sendLongforMsgConfirm" )
	@ResponseBody
	public JSONObject sendLongforMsgConfirm(String ids) {

		return dwCpClearUpDetailService.updSendMsgFlag(ids);
	}


	/**
	 * 往来清理取消勾兑
	 * @param uuid 主键id
	 * @return
	 */
	@CrossOrigin(origins = "*",maxAge = 3600)
	@PostMapping(value = "/notClearDetailStatus")
	@ResponseBody
	public JSONObject notClearDetailStatus(String uuid) {
		
		return dwCpClearUpDetailService.notClearDetailStatus(uuid,null);
	}
	
	
	/**
	 * 进行数据选择/取消选择操作
	 * selectType：文本内容： 全选/单选
		blendingType ：文本内容：已勾兑/未勾兑
		id：该参数需根据blendingType进行区分，若为已勾兑，此处的含义为匹配号（uuid），若为未勾兑，此处的含义为数据id（id）
		userCode：用户名
		selectFlag：文本内容：选择/取消选择
		month：月度
		accountsId：账套id
		subjectCode：科目

	 */
	@CrossOrigin(origins = "*",maxAge = 3600)
	@PostMapping(value = "/takeDataBlendingFlag")
	@ResponseBody
	public Message<Object> takeDataBlendingFlag(String selectType,String blendingType,String id,
			String userCode,String selectFlag,String month,String accountsId,String subjectCode){
		
		return dwCpClearUpDetailService.takeDataBlendingFlag( selectType,blendingType,id,userCode,selectFlag,month,accountsId,subjectCode);
	}
	
	/**
	 * 
	 *进行勾兑/取消勾兑
	 *userCode：用户名
	 *operateFlag：文本内容：勾兑/取消勾兑/批量更新
	 *month：月度
	 *accountsId：账套id
	 *subjectCode：科目
	 *
	 */
	@CrossOrigin(origins = "*",maxAge = 3600)
	@PostMapping(value = "/takeBlendingUpdate")
	@ResponseBody
	public Message<Object> takeBlendingUpdate(String userCode,String operateFlag,String month,String accountsId,String subjectCode){
		
		return dwCpClearUpDetailService.takeBlendingUpdate(userCode,operateFlag,month,accountsId,subjectCode);
		
	}
	/**
	 * 进行勾选初始化
	 	userCode：用户名
		operateFlag：文本内容：全选/为空
		month：月度
		accountsId：账套id
		subjectCode：科目

	 *
	 */
	@CrossOrigin(origins = "*",maxAge = 3600)
	@PostMapping(value = "/clearBlendInit")
	@ResponseBody
	public Message<Object> clearBlendInit(String userCode,String operateFlag,String month,String accountsId,String subjectCode){
		
		return dwCpClearUpDetailService.clearBlendInit(userCode,operateFlag,month,accountsId,subjectCode);
		
	}
	
}
