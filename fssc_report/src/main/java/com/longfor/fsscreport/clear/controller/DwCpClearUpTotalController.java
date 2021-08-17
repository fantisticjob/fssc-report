
package com.longfor.fsscreport.clear.controller;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.longfor.fsscreport.approval.entity.DwCpOaAccounts;
import com.longfor.fsscreport.approval.entity.FrProcessInfo;
import com.longfor.fsscreport.approval.mapper.CommonDao;
import com.longfor.fsscreport.approval.service.IDwCpOaAccountsService;
import com.longfor.fsscreport.approval.service.IFrProcessInfoService;
import com.longfor.fsscreport.bpm.service.IBpmService;
import com.longfor.fsscreport.clear.entity.DwCpClearUpTotal;
import com.longfor.fsscreport.clear.service.IDwCpClearUpTotalService;
import com.longfor.fsscreport.utils.StringUtil;
import com.longfor.fsscreport.vo.BpmCreateProcessReqParam;
/**
 * <p>
 * 往来清理汇总表 前端控制器
 * </p>
 *
 * @author chenziyao
 * @since 2020-10-30
 */
@RestController
@RequestMapping("/clear")
public class DwCpClearUpTotalController {
	
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	private static final  String  RESULT="result";
	private static final  String  STATUS="status";
	
	@Autowired
	private  CommonDao  dao;
	@Autowired
    private  IBpmService bpmService;
    
    @Autowired
    private IFrProcessInfoService ifs;
    
    @Autowired
    private IDwCpOaAccountsService dwCpOaAccounts;
	
	@Autowired
	private IDwCpClearUpTotalService dwCpClearUpTotalService;
	
	/**
	 * 往来清理锁数
	 * @param accountsId  账套ID
	 * @param quarter    季度
	 * @param subjectCode 科目Code
	 * @return
	 */
	@CrossOrigin(origins = "*",maxAge = 3600)
	@PostMapping(value = "/numberOfLocks")
	@ResponseBody
	public JSONObject numberOfLocks(String accountsId,String quarter,String subjectCode) {
		JSONObject json = new JSONObject();
		try {
			if(StringUtils.isBlank(accountsId)) {
				json.put(RESULT,"非法参数");
				json.put(STATUS,"-1");
				return json;
			}
			json = dwCpClearUpTotalService.numberOfLocks(accountsId, quarter, subjectCode);
		} catch (Exception e) {
			log.error("往来清理锁数异常{}",e);
			json.put(RESULT, "锁数异常！");
			json.put(STATUS, "-2");
		}
		return json;
	}
	
	/**
	 * 往来清理解锁
	 * @param accountsId

	 * @return
	 */
	@CrossOrigin(origins = "*",maxAge = 3600)
	@PostMapping(value = "/numberOfLocksCancelled")
	@ResponseBody
	public JSONObject numberOfLocksCancelled(String accountsId,String quarter,String subjectCode) {
		
		JSONObject json = new JSONObject();
		try {
			if(StringUtils.isBlank(accountsId)) {
				json.put(RESULT,"非法参数");
				json.put(STATUS,"-1");
				return json;
			}
			QueryWrapper<DwCpClearUpTotal> wrapper = new QueryWrapper<>();
			wrapper.eq("SUBJECT_CODE", subjectCode);
			wrapper.eq("QUARTER", quarter);
			wrapper.eq("ACCOUNTS_ID", accountsId);
			DwCpClearUpTotal one = dwCpClearUpTotalService.getOne(wrapper);
			if(one==null) {
				json.put(RESULT,"未查到数据");
				json.put(STATUS,"0");
				return json;
			}
			
			if(!"流程撤回".equals(one.getSp()) && StringUtils.isNotBlank(one.getSp())) {
				
				json.put(RESULT,"流程已提交，解锁失败");
				json.put(STATUS,"0");
				return json;
			} 
			DwCpClearUpTotal total = new DwCpClearUpTotal();
			total.setLockStatus("0");
			//total.setSp("审批完成");
			
			if(dwCpClearUpTotalService.update(total,wrapper)) {
				json.put(RESULT,"已解锁");
				json.put(STATUS,"200");
				return json;
			}else {
				json.put(RESULT,"解锁失败");
				json.put(STATUS,"0");
				return json;
			}
		} catch (Exception e) {
			log.error("往来清理解锁异常{}",e);
			json.put(RESULT, "解锁异常！");
			json.put(STATUS, "-2");
		}
		return json;
	}
	 /**
     * 流程审批提交  //已废弃
     * @param userCode
     * @param quarter
     * @param accountsId
     * @param param
     * @return
     */
    @CrossOrigin(origins = "*",maxAge = 3600)
    @PostMapping(value = "createProcessId")
    @ResponseBody
    public JSONObject createProcessId(String userCode,String quarter,BpmCreateProcessReqParam param,String accountsId) {
    	log.info("流程审批提交userCode:{}",userCode);
    	log.info("流程审批提交quarter:{}",quarter);
    	log.info("流程审批提交accountsId:{}",accountsId);
    	log.info("流程审批提交param:{}",param);

    	JSONObject json = new JSONObject();
    	String instanceId ="";
    	try {
	    	List<String> accountsIds = StringUtil.getStringList(accountsId);
	    	QueryWrapper<DwCpClearUpTotal> wrapper = new QueryWrapper<DwCpClearUpTotal>();
			wrapper.eq("QUARTER", quarter);
			wrapper.in("ACCOUNTS_ID", accountsIds);
			List<DwCpClearUpTotal> list = dwCpClearUpTotalService.getAllListBean(wrapper);
			ArrayList<String> arrayList = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				DwCpClearUpTotal total = list.get(i);
				arrayList.add(total.getSubjectCode());
			}
			wrapper.in("SUBJECT_CODE", arrayList);
			List<DwCpClearUpTotal> bean = dwCpClearUpTotalService.getAllListBean(wrapper);
			for (int i = 0; i < bean.size(); i++) {
				DwCpClearUpTotal total = bean.get(i);
				if(total.getLockStatus().equals("0")) {
					json.put(STATUS,"0");
					json.put(RESULT, "账套"+total.getAccountsId()+"下"+total.getSubjectCode()+"科目未锁数");
					return json;
				}

			}
			wrapper.ne("sp", "流程驳回");
			wrapper.ne("sp", "待审批");
			wrapper.ne("sp", "流程撤回");
			wrapper.ne("sp", "终止");
			wrapper.isNotNull("sp");
			List<DwCpClearUpTotal> list2 = dwCpClearUpTotalService.getAllListBean(wrapper);

			if(list2!=null && list2.size() > 0) {

				json.put(STATUS,"0");
				json.put(RESULT, "流程已存在");
				return json;
			}

			//根据账套取得审批账户
	    	QueryWrapper<DwCpOaAccounts> qwrapper = new QueryWrapper<>();
	    	qwrapper.in("ACCOUNTS_ID", accountsIds);
	    	List<DwCpOaAccounts> cpOaAccounts = dwCpOaAccounts.selectList(qwrapper);
	    	String string = cpOaAccounts.toString();
	    	log.info("createProcessId(cpOaAccounts):{}",string);
    		List<String> err = new ArrayList<>();
    		List<String> instance = new ArrayList<>();
    		for (int i = 0; i < cpOaAccounts.size(); i++) {
    			DwCpOaAccounts oaAccounts = cpOaAccounts.get(i);
    			instanceId = bpmService.createProcess(userCode,oaAccounts.getOaName(), param);
    			log.info("createProcessId(instance_id):{}",instanceId);
    			instance.add(instanceId);
    			DwCpClearUpTotal total = new DwCpClearUpTotal();
    			total.setInstanceId(instanceId);
    			UpdateWrapper<DwCpClearUpTotal> updateWrapper = new UpdateWrapper<>();
    			updateWrapper.eq("QUARTER", quarter);
    			updateWrapper.eq("ACCOUNTS_ID",accountsIds.get(i));
    			boolean update = dwCpClearUpTotalService.update(total,updateWrapper);
    			if( update) {
    				FrProcessInfo frProcessInfo = new FrProcessInfo();

    		    	frProcessInfo.setInstanceId(instanceId);
    		    	frProcessInfo.setTableName("DW_CP_CLEAR_UP_TOTAL");
    		    	if(!ifs.save(frProcessInfo)) {
    		    		log.info("流程映射表关联失败{}",param.getProcessDefName());

    		    	}
    		    	log.info("instanceId{}",instanceId);

    			}else {

    				err.add(accountsIds.get(i));
    			}
			}
			if(err.size() > 0) {

				json.put("data",instance);
				json.put("STATUS","0");
				json.put("RESULT","账套"+err+"创建失败！");
			}else {
				json.put("data",instance);
				json.put("STATUS","200");
				json.put("RESULT","创建成功！");
			}
		} catch (Exception e) {
			json.put("data",null);
			log.info("流程异常{}",e.toString());
			log.info("instanceId{}",instanceId);
			return json;
		}

        return json;
    }


	/**
	 * 往来清理提交操作
	 * @param quarter 季度
	 * @param accountsId 账套信息
	 *
	 * @return
	 */
	@CrossOrigin(origins = "*",maxAge = 3600)
	@PostMapping(value = "submitClearRecords")
	@ResponseBody
	public JSONObject submitClearRecords(String quarter, String accountsId) {
		log.info("往来清理提交操作quarter:{}",quarter);
		log.info("往来清理提交操作accountsId:{}",accountsId);
		JSONObject json = new JSONObject();
		try {
			if(StringUtils.isBlank(accountsId)) {
				json.put(RESULT,"账套信息为空");
				json.put(STATUS,"-1");
				return json;
			}
			//进行锁数操作 - 暂时不需要锁数
			//json = dwCpClearUpTotalService.numberOfLocks(accountsId, quarter, null);

			//拆分提交的账套信息
			List<String> accountList = StringUtil.getStringList(accountsId);
			//循环检查每个账套下的客户是否存在未锁数科目
			int unlockAcctNum = 0;
			int unlockSubjCode = 0;
			int index = 0;
			StringBuffer errResultStr = new StringBuffer();
			if (accountList != null && 0 < accountList.size()) {
				for (String accountCode : accountList) {
					log.info("++提交账套++" + (index++) + "：" + accountCode);
					boolean hasUnlocked = false;
					unlockSubjCode = 0;
					//根据账套和季度获取所有科目明细数据
					QueryWrapper<DwCpClearUpTotal> wrapper = new QueryWrapper<DwCpClearUpTotal>();
					wrapper.eq("QUARTER", quarter);
					wrapper.eq("ACCOUNTS_ID", accountCode);
					List<DwCpClearUpTotal> clearList =
							dwCpClearUpTotalService.getAllListBean(wrapper);
					if (clearList != null) {
						//循环检查每条记录的锁数状态
						for (int i = 0; i < clearList.size(); i++) {
							DwCpClearUpTotal total = clearList.get(i);
							//如果有未锁数科目，则返回错误信息
							if(total.getLockStatus().equals("0")) {
								//如果有问题账套数量>5, 则不再提示错误信息
								if (unlockAcctNum < 5 ) {
									if (!hasUnlocked ) {
										hasUnlocked = true;
										unlockAcctNum++; //有未锁数的账套数量加1
										unlockSubjCode++; //未锁数科目数量加1
										errResultStr.append("账套" + accountCode +"下未锁数科目：" + total.getSubjectCode());
									} else {
										unlockSubjCode++; //未锁数科目数量加1
										errResultStr.append("," + total.getSubjectCode());
									}
								} else {
									hasUnlocked = true;
									break;
								}
							}
							if (5 <= unlockSubjCode) {
								errResultStr.append(", ......");
								break; //如果超过5个科目未锁，则不继续检查该账套下的其他科目锁数状态
							}
						}

						//账套检查完成进行换行
						if (0 < unlockSubjCode) {
							errResultStr.append("\n");
						}
						//如果该账套不存在未锁数记录，则设置提交完成sp
						//更新流程状态
						if (!hasUnlocked) {
							DwCpClearUpTotal updRecord = new DwCpClearUpTotal();
							//设置流程状态
							updRecord.setSp("审批完成");
							UpdateWrapper<DwCpClearUpTotal> updateWrapper = null;
							updateWrapper = new UpdateWrapper<>();
							updateWrapper.eq("QUARTER", quarter);
							updateWrapper.eq("ACCOUNTS_ID", accountCode);
							//更新记录
							boolean update =
									dwCpClearUpTotalService.update(updRecord, updateWrapper);
						}
					}
				}
			}
			//生产返回结果
			if (unlockAcctNum == 0) {
				json.put(STATUS,"200");
				json.put(RESULT,"提交成功！");
			} else {
				json.put(STATUS,"0");
				json.put(RESULT, errResultStr.toString());
			}
		} catch (Exception e) {
			log.error("往来清理提交异常{}",e.toString());
			json.put(RESULT, "往来清理提交异常！");
			json.put(STATUS, "-2");
		}
		return json;
	}

    /**
     * bpm流程撤回
     * @param userCode
     * @param instanceId
     * @return
     */
    @CrossOrigin(origins = "*",maxAge = 3600)
    @PostMapping(value = "withdrawalProcess")
    @ResponseBody
    public  JSONObject withdrawalProcess(String userCode,String instanceId) {
    	JSONObject json = new JSONObject();
    	log.info("bpm流程撤回userCode:{}",userCode);
    	log.info("bpm流程撤回instanceId:{}",instanceId);
    	if(userCode == null || instanceId==null) {
    		json.put(STATUS,"-1");
    		json.put(RESULT,"参数不合法");
    		return json;
    	}
    	try {
    		List<String> accountsIds = StringUtil.getStringList(instanceId);
    		for (int i = 0; i < accountsIds.size(); i++) {
				
			}
			List<String> list = StringUtil.getNumberListId(instanceId);
			for (int i = 0; i < list.size(); i++) {
				
				List<String> id = bpmService.getTaskId(userCode, list.get(i) );
				if(list==null || id.size()==0) {
					json.put(STATUS,"0");
					json.put(RESULT,"流程已撤回或者流程不存在！");
				}
				for (int j = 0;j < id.size(); j++) {
					String process = bpmService.withdrawalProcess(userCode, id.get(j));
					if(process.contains("200")) {
						json.put(STATUS,"200");
						json.put(RESULT,"流程已撤回！");
					}else {
						json.put(STATUS,"0");
						json.put(RESULT,"流程撤回失败！");
						
					}
				}
			}
		} catch (Exception e) {
			json.put(STATUS,"0");
			json.put(RESULT,"流程撤回异常！");
			log.error("流程撤回异常{}",e);
		}
		return json;
    }
    /**
     * 初始化数据 -老方法
     * @return
     */
    @CrossOrigin(origins = "*",maxAge = 3600)
    @PostMapping(value = "initData")
    @ResponseBody
    public JSONObject initData(String quarter,String accountsId) {
    	log.info("初始化quarter：{}",quarter);
    	log.info("初始化accountsId：{}",accountsId);
    	JSONObject json = new JSONObject();
    	if(StringUtils.isBlank(quarter) || StringUtils.isBlank(accountsId)  ) {
    		json.put(STATUS,"-1");
			json.put(RESULT,"参数不合法！");
			return json;
    	}  
    	//--更新明细表初始化数据的往来成因是否异常
    	StringBuilder buffer = new StringBuilder();
    	buffer.append(" update dw_cp_clear_up_detail t");
    	buffer.append(" set t.is_un_general =");
    	buffer.append(" (select b.is_un_general");
    	buffer.append(" from dw_cp_reseon_dim b");
    	buffer.append(" where b.reseon = t.reseon");
    	buffer.append(" and b.subject_code like '%'");
    	buffer.append(" || t.subject_code || '%')");
    	buffer.append(" where t.accounts_id = '");
    	buffer.append(accountsId);
    	buffer.append("' and t.csh_flag = 1 ");
    	buffer.append("  and t.quarter = '");
    	buffer.append(quarter);
    	buffer.append("' ");
    	dao.update(buffer.toString());
    	
    	//--更新余额表初始化数据的往来成因是否异常
    	buffer = new StringBuilder();
    	buffer.append(" update dw_cp_clear_up_blance t");
    	buffer.append(" set t.is_un_general =");
    	buffer.append(" (select b.is_un_general");
    	buffer.append(" from dw_cp_reseon_dim b");
    	buffer.append(" where b.reseon = t.reseon");
    	buffer.append(" and b.subject_code like '%' ");
    	buffer.append(" || t.subject_code || '%')");
    	buffer.append(" where t.accounts_id ='");
    	buffer.append(accountsId);
    	buffer.append("' and t.quarter = '");
    	buffer.append(quarter);
    	buffer.append("'");
    	dao.update(buffer.toString());
		return dwCpClearUpTotalService.initData1(quarter, accountsId);
    }
    /**
     * 初始化数据
     * @return
     */
    @CrossOrigin(origins = "*",maxAge = 3600)
    @PostMapping(value = "initData1")
    @ResponseBody
    public JSONObject initData1(String quarter,String accountsId) {
    	log.info("初始化数据quarter：{}",quarter);
    	log.info("初始化数据accountsId：{}",accountsId);
    	JSONObject json = new JSONObject();
    	if(StringUtils.isBlank(quarter) || StringUtils.isBlank(accountsId)  ) {
    		json.put(STATUS,"-1");
    		json.put(RESULT,"参数不合法！");
    		return json;
    	} 
    	//--更新明细表初始化数据的往来成因是否异常
    	StringBuilder buffer = new StringBuilder();
    	buffer.append(" update dw_cp_clear_up_detail t");
    	buffer.append(" set t.is_un_general =");
    	buffer.append(" (select b.is_un_general");
    	buffer.append(" from dw_cp_reseon_dim b");
    	buffer.append(" where b.reseon = t.reseon");
    	buffer.append(" and b.subject_code like '%'");
    	buffer.append(" || t.subject_code || '%')");
    	buffer.append(" where t.accounts_id = '");
    	buffer.append(accountsId);
    	buffer.append("' and t.csh_flag = 1 ");
    	buffer.append("  and t.quarter = '");
    	buffer.append(quarter);
    	buffer.append("' ");
    	dao.update(buffer.toString());
    	
    	//--更新余额表初始化数据的往来成因是否异常
    	buffer = new StringBuilder();
    	buffer.append(" update dw_cp_clear_up_blance t");
    	buffer.append(" set t.is_un_general =");
    	buffer.append(" (select b.is_un_general");
    	buffer.append(" from dw_cp_reseon_dim b");
    	buffer.append(" where b.reseon = t.reseon");
    	buffer.append(" and b.subject_code like '%' ");
    	buffer.append(" || t.subject_code || '%')");
    	buffer.append(" where t.accounts_id ='");
    	buffer.append(accountsId);
    	buffer.append("' and t.quarter = '");
    	buffer.append(quarter);
    	buffer.append("'");
    	dao.update(buffer.toString());
    	return dwCpClearUpTotalService.initData(quarter, accountsId);
    }
    
    /**
     * nc 取数更新
	 * @param quarter
     * @param quarter
     * @param accountsId
     * @param subjectCode
     * @return
     */
    @CrossOrigin(origins = "*",maxAge = 3600)
    @PostMapping(value = "getNcDate")
    @ResponseBody
    public JSONObject getNcDate(String syncDateStr, String quarter, String accountsId, String subjectCode) {
    	
    	log.info("===getNcDate===");
		log.info("nc 取数更新syncDateStr{}",syncDateStr);
    	log.info("nc 取数更新quarter{}",quarter);
    	log.info("nc 取数更新accountsId{}",accountsId);
    	log.info("nc 取数更新subjectCode{}",subjectCode);
    	
    	JSONObject json = new JSONObject();
    	if(StringUtils.isBlank(quarter) || StringUtils.isBlank(accountsId) || StringUtils.isBlank(syncDateStr)) {
    		json.put(STATUS, "-1");
    		json.put(RESULT, "参数不合法");
    		return json;
    	}
    	try {
			QueryWrapper<DwCpClearUpTotal> query = new  QueryWrapper<>();
			query.eq("ACCOUNTS_ID", accountsId);
			query.eq("QUARTER", quarter);
			//单科目判断是否已经锁数
			if(StringUtils.isNotBlank(subjectCode)) {
				query.eq("SUBJECT_CODE", subjectCode);
				query.eq("LOCK_STATUS", "1");
				List<DwCpClearUpTotal> list = dwCpClearUpTotalService.getAllListBean(query);
				if(list.size()>0 && list!=null) {
					json.put(STATUS, "0");
					json.put(RESULT, "账套已锁数，取数更新失败！");
					return json;
				}
				json = dwCpClearUpTotalService.getNcDate(syncDateStr, quarter,accountsId,subjectCode);
				json.put(STATUS, "200");
				json.put(RESULT, "取数完成！");
			} else { //账套多科目锁数
				List<DwCpClearUpTotal> list = dwCpClearUpTotalService.getAllListBean(query);
				for (DwCpClearUpTotal tmpObj : list) {
					//如果当前科目所有则跳过处理下一个科目
					if (StringUtils.isNotBlank(tmpObj.getLockStatus()) &&
							"1".equals(tmpObj.getLockStatus())) {
						continue;
					}
					//取数更新
					dwCpClearUpTotalService.getNcDate(syncDateStr, quarter,accountsId,tmpObj.getSubjectCode());
				}
				json.put(STATUS, "200");
				json.put(RESULT, "取数完成！");
			}
		} catch (Exception e) {
			json.put(STATUS, "-2");
			json.put(RESULT, "取数异常！");
			log.error("nc 取数更新异常{}",e.toString());
		}

    	return json;
    }
}
