package com.longfor.fsscreport.reconciliation.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.longfor.fsscreport.approval.mapper.CommonDao;
import com.longfor.fsscreport.reconciliation.entity.BaBalanceAdjustInitialize;
import com.longfor.fsscreport.reconciliation.entity.BaBalanceAdjustTotal;
import com.longfor.fsscreport.reconciliation.entity.StoredProcedure;
import com.longfor.fsscreport.reconciliation.service.IBaBalanceAdjustInitializeService;
import com.longfor.fsscreport.reconciliation.service.IBaBalanceAdjustTotalService;
import com.longfor.fsscreport.reconciliation.service.IBaReconciliationDetailsService;
import com.longfor.fsscreport.utils.StringSql;
import com.longfor.fsscreport.utils.StringUtil;
import com.longfor.fsscreport.vo.Message;

/**
 * <p>
 * 对账明细表 前端控制器
 * </p>
 *
 * @author chenziyao
 * @param <V>
 * @param <E>
 * @since 2020-07-15
 */
@RestController
@RequestMapping("/reconciliation")
public class BaReconciliationDetailsController {
	private final Logger log = LoggerFactory.getLogger(getClass());
	private static final String STATUS = "status";
	private static final String RESULT = "result";

	@Autowired
	private CommonDao dao;
	@Autowired
	private IBaReconciliationDetailsService detailsService;
	@Autowired
	private IBaBalanceAdjustTotalService totalService;
	@Autowired
	private IBaBalanceAdjustInitializeService init;

	/**
	 * 校验余额
	 * 
	 * @param dataType
	 * @param month
	 * @param accountId
	 * @return
	 */
	@CrossOrigin(origins = "*", maxAge = 3600)
	@PostMapping(value = "/getSummaryCheckResult")
	@ResponseBody
	public Message<JSONObject> getSummaryCheckResult(String month, String accountId) {

		return detailsService.getSummaryCheckResult(month, accountId);
	}

	/**
	 * 勾选合计
	 * 
	 * @param dataType
	 * @param month
	 * @param accountId
	 * @return
	 */
	@CrossOrigin(origins = "*", maxAge = 3600)
	@PostMapping(value = "/getSelectedSummary")
	@ResponseBody
	public Message<JSONObject> getSelectedSummary(String month, String accountId, String dataType) {
		return detailsService.getSelectedSummary(month, accountId, dataType);
	}

	/**
	 * 将选择，取消选择的信息入库
	 * 
	 * @param selectType
	 * @param blendingType
	 * @param id           数据id
	 * @param userCode
	 * @param selectFlag
	 * @param month
	 * @param accountId
	 * @return
	 */
	@CrossOrigin(origins = "*", maxAge = 3600)
	@PostMapping(value = "/takeDataBlendingFlag")
	@ResponseBody
	public Message<String> takeDataBlendingFlag(String selectType, String blendingType, String id, String userCode,
			String selectFlag, String month, String accountId, String datatype, @RequestParam Map<String, String> map) {
		String unreciveType = map.get("unrecive_type");
		String string = map.get("nc_voucher_summary");
		log.info("-----------------------unrecive_type---------------------{}", unreciveType);
		log.info("-----------------------string---------------------{}", string);
		log.info("-----------------------selectType---------------------{}", selectType);

		return detailsService.takeDataBlendingFlag(selectType, blendingType, id, userCode, selectFlag, month, accountId,
				datatype, map);
	}

	/**
	 * 进行勾兑/取消勾兑/批量更新
	 * 
	 * @param updateRes15128872384
	 * @param userCode
	 * @param month
	 * @param accountId
	 * @param operateFlag
	 * @return
	 */
	@CrossOrigin(origins = "*", maxAge = 3600)
	@PostMapping(value = "/takeBlendingUpdate")
	@ResponseBody
	public Message<String> takeBlendingUpdate(String updateRes, String userCode, String month, String accountId,
			String operateFlag, String datatype) {
		return detailsService.takeBlendingUpdate(updateRes, userCode, operateFlag, month, accountId, datatype);
	}

	/**
	 * 自动勾兑
	 * 
	 * @param updateRes15128872384
	 * @param userCode
	 * @param month
	 * @param accountId
	 * @param operateFlag
	 * @return
	 */
	@CrossOrigin(origins = "*", maxAge = 3600)
	@PostMapping(value = "/autoTakeBlendingUpdate")
	@ResponseBody
	public Message<String> autoTakeBlendingUpdate(String month, String accountId, String userCode) {

		if (StringUtils.isBlank(accountId) || StringUtils.isBlank(month)) {

			return new Message<>("-1", "参数为空", null);
		}
		return detailsService.autoTakeBlendingUpdate(month, accountId, userCode);
	}

	/**
	 * flag置空
	 * 
	 * @param updateRes
	 * @param userCode
	 * @param month
	 * @param accountId
	 * @param operateFlag
	 * @return
	 */
	@CrossOrigin(origins = "*", maxAge = 3600)
	@PostMapping(value = "/takeBlendingInit")
	@ResponseBody
	public Message<String> takeBlendingInit(String accountId, String month) {
		return detailsService.takeBlendingInit(accountId, month);
	}

	/**
	 * 勾选
	 * 
	 * @param id    账套id
	 * @param month 年月
	 * @return
	 */
	@CrossOrigin(origins = "*", maxAge = 3600)
	@PostMapping(value = "/selectStete")
	@ResponseBody
	public JSONObject selectStatus(String id, String month) {
		log.info("selectStatus(id):{}", id);
		log.info("selectStatus(month){}:", month);
		return detailsService.selectStatus(id, null, month);
	}

	/**
	 * 取消勾选
	 * 
	 * @param rid   匹配号
	 * @param month 年月
	 * @return
	 */
	@CrossOrigin(origins = "*", maxAge = 3600)
	@PostMapping(value = "/notSelectStete")
	@ResponseBody
	public JSONObject notSelectStatus(String rid, String month) {
		return detailsService.notSelectStatus(rid, null, month);

	}

	/**
	 * 锁数并且校验hh
	 * 
	 * @param status 锁数状态
	 * @param id     账户
	 * @param month  年月
	 * @return
	 */
	@CrossOrigin(origins = "*", maxAge = 3600)
	@PostMapping(value = "/lockState")
	@ResponseBody
	public JSONObject lockState(String status, String id, String month) {

		log.info("lockState status=={}", status);
		log.info("lockState id=={}", id);
		log.info("lockState month =={}", month);
		JSONObject json = new JSONObject();
		try {
			if (month == null || id == null || status == null) {
				json.put(STATUS, "-1");
				log.info("参数错误");
				return json;
			}
			ArrayList<String> list = new ArrayList<>();
			list.add(id);
			List<BaBalanceAdjustInitialize> checkInit = init.checkInit(list);

			log.info("余额调节表初始化：{}", checkInit);
			if (checkInit == null || checkInit.size() == 0) {
				json.put(STATUS, "0");
				json.put(RESULT, "账户未初始化");
				return json;
			}
			if (status.equals("0")) {// 手动锁数状态
				json = detailsService.getCheckAccountId(id, month, 2);
				String string = json.toString();
				log.info("锁数校验：{}", string);

				if (json.get(STATUS) != null && !"200".equals(json.get(STATUS))) {
					json.put(STATUS, "0");
					json.put(RESULT, "校验不通过，锁数失败！");
				} else {

					String updataSql = "UPDATE BA_balance_adjust_total  SET lock_status ='2'  " + " WHERE  ACCOUNT_ID='"
							+ id + "'  AND ACCOUNT_TIME='" + month + "'" + " AND START_DATE is not null";
					if (dao.update(updataSql) > 0) {
						log.info("锁数成功！");
						json.put(STATUS, "200");
						json.put(RESULT, "锁数成功！");
					} else {
						log.info("锁数失败！");
						json.put(STATUS, "0");
						json.put(RESULT, "锁数失败！");
					}
					return json;
				}
			} else if (status.equals("1") || status.equals("2")) {// 解锁状态

				String updataSql = "UPDATE BA_balance_adjust_total  SET lock_status ='0'  " + " WHERE  ACCOUNT_ID='"
						+ id + "'  AND ACCOUNT_TIME='" + month + "'" + " AND START_DATE is not null";
				if (dao.update(updataSql) > 0) {
					log.info("解锁成功！");
					json.put(STATUS, "200");
				} else {
					log.info("解锁失败！");
					json.put(STATUS, "0");
				}
				return json;
			}
			json.put(STATUS, 0);
		} catch (Exception e) {
			json.put(STATUS, -2);
			log.error("锁数状态变更异常！{}", e.toString());
		}
		log.info("lockState(return):{}", json);
		return json;
	}

	/**
	 * 提交
	 * 
	 * @param id
	 * @return
	 */
	@CrossOrigin(origins = "*", maxAge = 3600)
	@PostMapping(value = "/submit")
	@ResponseBody
	@Transactional
	public JSONObject submit(String id, String month) {
		log.info("submit(id):{}", id);
		log.info("submit(month):{}", month);
		JSONObject json = new JSONObject();
		try {
			if (StringUtils.isBlank(month) || StringUtils.isBlank(id)) {
				json.put(STATUS, "-1");
				json.put(RESULT, "参数错误");
				log.info("参数错误");
				return json;
			}

			String flag = "";
			List<String> list = new ArrayList<String>();
			List<String> errlist = new ArrayList<>();
			List<String> accounts = StringUtil.getStringList(id);
			for (int i = 0; i < accounts.size(); i++) {
				StringBuilder sb = new StringBuilder();
				sb.append("select distinct(c.account_id)  ");
				sb.append(" from ba_area_accounts b ");
				sb.append(" left join ba_accounts_account c ");
				sb.append(" on b.accounts_id = c.accounts_id ,");
				sb.append(" BA_BALANCE_ADJUST_TOTAL d");
				sb.append(" where b.accounts_id = substr(d.accounts_id, 0 , instr(d.accounts_id,'-')-1) ");
				sb.append(" and  START_DATE is not null ");
				sb.append(" and b.accountingbookcode= '");
				sb.append(accounts.get(i));
				sb.append("'");
				sb.append(" and c.account_id=d.account_id ");
				sb.append(" and  d.account_time='");
				sb.append(month);
				sb.append("'");

				list = dao.selects(sb.toString());

				if (list == null || list.size() == 0) {
					json.put(STATUS, "0");
					json.put(RESULT, "账套下未查到有账户！");
					return json;
				}
				String string2 = list.toString();
				log.info("查询账号列表：{}", string2);
				ArrayList<String> newList = new ArrayList<>();
				Integer err = 0;// 记录当前账套下有没有校验不通过账户
				for (int j = 0; j < list.size(); j++) {
					String string = list.get(j);
					json = detailsService.getCheckAccountId(string, month, 2);
					if (json.get(STATUS).equals("200")) {
						String jsonString = json.toJSONString();
						log.info("余额调节表提交校验：{}", jsonString);
						QueryWrapper<BaBalanceAdjustTotal> querywrapper = new QueryWrapper<BaBalanceAdjustTotal>();
						querywrapper.eq("ACCOUNT_ID", string);
						querywrapper.eq("ACCOUNT_TIME", month);
						querywrapper.isNotNull("START_DATE");
						BaBalanceAdjustTotal one = totalService.getOne(querywrapper);
						String lockStatus = one.getLockStatus();
						log.info("提交锁数状态lockStatus：{}", lockStatus);
						if ("0".equals(lockStatus) || "".equals(lockStatus) || null == lockStatus) {

							flag = "1";
							BaBalanceAdjustTotal total = new BaBalanceAdjustTotal();
							UpdateWrapper<BaBalanceAdjustTotal> wrapper = new UpdateWrapper<BaBalanceAdjustTotal>();
							wrapper.eq("ACCOUNT_ID", string);
							wrapper.eq("ACCOUNT_TIME", month);
							wrapper.isNotNull("START_DATE");
							total.setLockStatus("1");
							boolean update = totalService.update(total, wrapper);
							if (!update) {
								log.error("余额调节表提交不通过锁数失败！");
								json.put(STATUS, "0");
								json.put(RESULT, "余额调节表提交不通过锁数失败,请联系管理员！");
								return json;

							} else {

								newList.add(string);
							}
						} else if ("2".equals(lockStatus)) { // 如果为2 为手动锁数~！

							flag = "2";
							newList.add(string);
						} else if ("1".equals(lockStatus)) {

							flag = "1";
							newList.add(string);
						}
					} else {
						err++;
						errlist.add(string);

					}
				}
				String string = errlist.toString();
				String string3 = newList.toString();
				log.info("====err账户：{}", string);
				log.info("====newList账户：{}", string3);

				if (err > 0) {

					continue;// 如果当前账户有未通过校验数据，结束本次循环
				}

				if (newList != null && newList.size() > 0) {

					BaBalanceAdjustTotal total = new BaBalanceAdjustTotal();
					UpdateWrapper<BaBalanceAdjustTotal> wrapper = new UpdateWrapper<BaBalanceAdjustTotal>();
					wrapper.in("ACCOUNT_ID", newList);
					wrapper.eq("ACCOUNT_TIME", month);
					wrapper.isNotNull("START_DATE");
					total.setInnerStatus("已提交");
					total.setLockStatus(flag);
					if (!totalService.update(total, wrapper)) {
						log.info("汇总余额调节表更新失败");
						json.put(STATUS, "0");
						json.put(RESULT, "汇总余额调节表提交失败,请联系管理员！");
						return json;
					}

				}
			}
			if (errlist != null && errlist.size() > 0) {// 如果有错误未通过校验账户，查询详细信息，提示给前端页面

				List<BaBalanceAdjustTotal> totals = totalService.getListTotals(errlist, month);
				StringBuilder stringBuilder = new StringBuilder();
				Map<String, String> map = new HashMap<>();

				for (int j = 0; j < totals.size(); j++) { // 遍历每一条数据

					String accountsName = totals.get(j).getAccountsName(); // 账套名
					String accountId = totals.get(j).getAccountId();
					if (StringUtils.isBlank(map.get(accountsName))) {// 如果没有直接添加

						map.put(accountsName, accountId);
					} else {// 如果存在逗号分开
						String string = map.get(accountsName);
						map.put(accountsName, string + "," + accountId);
					}
				}

				Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
				while (it.hasNext()) {

					Map.Entry<String, String> entry = it.next();
					int char1 = StringUtil.FindChar(entry.getValue()); // 判断逗号出现了几次
					stringBuilder.append(entry.getKey());
					stringBuilder.append("\n");
					stringBuilder.append("当前有" + char1 + "个账户未通过校验");
					stringBuilder.append("\n");
					stringBuilder.append(entry.getValue());
				}
				stringBuilder.append("\n");
				stringBuilder.append("请核实");
				json.put(STATUS, "0");
				json.put(RESULT, stringBuilder.toString());
				return json;
			} else {

				json.put(STATUS, "200");
				json.put(RESULT, "提交成功！");
			}
		} catch (Exception e) {
			json.put(STATUS, "-2");
			log.error("调节表提交异常{}", e.toString());
		}
		String jsonString = json.toJSONString();
		log.info("submit()返回值{}", jsonString);
		return json;
	}

	/**
	 * 撤回提交
	 * 
	 * @param id
	 * @return
	 */
	@CrossOrigin(origins = "*", maxAge = 3600)
	@PostMapping(value = "/notSubmit")
	@ResponseBody
	public JSONObject notSubmit(String id, String month) {
		log.info("notSubmit()month{}", month);
		log.info("notSubmit()id{}", id);
		JSONObject json = new JSONObject();
		if (month == null || id == null) {
			json.put(STATUS, "-1");
			log.info("参数错误！");
			return json;
		}
		try {

			List<String> list = new ArrayList<String>();
			List<String> accounts = StringUtil.getStringList(id);
			for (int i = 0; i < accounts.size(); i++) {
				StringBuilder sb = new StringBuilder();
				sb.append("select distinct(c.account_id)  ");
				sb.append(" from ba_area_accounts b ");
				sb.append(" left join ba_accounts_account c ");
				sb.append(" on b.accounts_id = c.accounts_id ,");
				sb.append(" BA_BALANCE_ADJUST_TOTAL d");
				sb.append(" where b.accounts_id = substr(d.accounts_id, 0 , instr(d.accounts_id,'-')-1) ");
				sb.append(" and  START_DATE is not null ");
				sb.append(" and b.accountingbookcode= '");
				sb.append(accounts.get(i));
				sb.append("'");
				sb.append(" and c.account_id=d.account_id ");
				list = dao.selects(sb.toString());
				if (list == null || list.size() == 0) {
					json.put(STATUS, "0");
					json.put(RESULT, "账套下未查到有账户！");
					return json;
				}
				String updataSql = " update ba_balance_adjust_total " + "set lock_status = 0, inner_status = '未提交' "
						+ "where account_id in (" + StringUtil.getIds(list) + ")  " + "and account_time = '" + month
						+ "' " + "and (sp not in ('审批中', '审批完成') or sp is null)";
				if (dao.update(updataSql) > 0) {
					json.put(STATUS, "200");
					return json;
				}
				json.put(STATUS, "0");
			}

		} catch (Exception e) {
			json.put(STATUS, "-2");
			log.error("调节表撤回提交异常{}", e.toString());
		}
		String jsonString = json.toJSONString();
		log.info("notSubmit()返回值{}", jsonString);
		return json;
	}

	/**
	 * 保存更新
	 * 
	 * @param accountId
	 * @param month
	 * @return
	 */
	@CrossOrigin(origins = "*", maxAge = 3600)
	@PostMapping(value = "/preservation")
	public JSONObject preservation(String accountId, String month) {
		log.info("preservation(accountId){}", accountId);
		log.info("preservation(month){}", month);
		JSONObject json = new JSONObject();
		if (month == null || accountId == null) {
			json.put(STATUS, "-1");
			json.put(RESULT, "参数错误！");
			log.info("参数错误！");
			return json;
		}
		try {
			ArrayList<String> list = new ArrayList<>();
			list.add(accountId);
			List<BaBalanceAdjustInitialize> checkInit = init.checkInit(list);
			String string2 = checkInit.toString();
			log.info("余额调节表初始化：{}", string2);

			if (checkInit == null || checkInit.size() == 0) {

				json.put(STATUS, "0");
				json.put(RESULT, "账户未初始化");
				return json;
			}
			json = detailsService.getCheckAccountId(accountId, month, 3);
			String string = json.toString();
			log.info("保存更新校验：{}", string);

			if (json.get(STATUS) != null && !"200".equals(json.get(STATUS))) {
				json.put(STATUS, "0");
				json.put(RESULT, "校验不通过");
			}
		} catch (Exception e) {
			json.put(STATUS, "-2");
			json.put(RESULT, "保存更新异常");
			log.error("保存更新异常:{}", e.toString());
		}
		log.info("preservation(return){}", json);
		return json;
	}

	/**
	 * 调用存储过程更新汇总余额表
	 * 
	 * @param accountId
	 * @param month
	 * @return
	 */
	@CrossOrigin(origins = "*", maxAge = 3600)
	@PostMapping(value = "/updateStoredProcedure")
	public JSONObject updateStoredProcedure(String accountId, String month) {

		log.info("updateStoredProcedure(accountId){}", accountId);
		log.info("updateStoredProcedure(month){}", month);
		JSONObject json = new JSONObject();
		try {
			if (accountId == null || month == null) {
				json.put(STATUS, "-1");
				return json;
			}
			totalService.updateStoredProcedure(accountId, month);
			json.put(STATUS, 200);
		} catch (Exception e) {
			json.put(STATUS, -2);
			log.error("调用存储过程更新汇总余额表：{}", e.toString());
		}
		log.info("updateStoredProcedure(return){}", json);
		return json;
	}

	/**
	 * 手动更新状态
	 * 
	 * @param accountId
	 * @param month
	 * @return
	 */
	@CrossOrigin(origins = "*", maxAge = 3600)
	@PostMapping(value = "getvResults")
	public JSONObject getvResults(String accountId, String month) {
		log.info("getvResults()accountId{}:", accountId);
		log.info("getvResults()month{}:", month);
		JSONObject json = new JSONObject();
		if (accountId == null || month == null) {
			json.put(STATUS, -1);
			return json;
		}
		try {
			StoredProcedure sp = new StoredProcedure();
			sp.setP_account(accountId);
			sp.setP_date(month);
			totalService.getvResults(sp);
			json.put(STATUS, 200);
			json.put(RESULT, sp.getV_results());
		} catch (Exception e) {
			json.put(STATUS, -2);
			log.error("手动更新状态异常:{}", e.toString());
		}
		log.info("getvRESULTs(return):{}", json);
		return json;
	}

	/**
	 * 账户数据初始化
	 * 
	 * @param accountId
	 * @param month
	 * @return
	 */
	@CrossOrigin(origins = "*", maxAge = 3600)
	@PostMapping(value = "initAdjust")
	public JSONObject initAdjust(String accountId, String month) {
		log.info("initAdjust()accountId:{}", accountId);
		log.info("initAdjust()month:{}", month);
		JSONObject json = new JSONObject();
		if (accountId == null || month == null) {
			json.put(STATUS, -1);
			return json;
		}
		try {
			String select = "select count(*) from BA_BALANCE_ADJUST_INITIALIZE"
					+ " where reson_type in('银行对账单余额' ,'单位日记账余额')   " + "	and account_id='" + accountId + "'";
			if (dao.selectCount(select) != 2) {
				String delete = "delete from BA_BALANCE_ADJUST_INITIALIZE t " + "	where t.account_id='" + accountId
						+ "'";
				dao.delete(delete);
				log.info("初始化数据不正确");
				json.put(RESULT, "初始化数据不正确");
				json.put(STATUS, 0);
				return json;
			}
			StoredProcedure sp = new StoredProcedure();
			sp.setP_account(accountId);
			sp.setP_date(month);
			totalService.initAdjust(sp);
			json.put(STATUS, 200);
		} catch (Exception e) {
			json.put(STATUS, -2);
			log.error("账户数据初始化异常{}", e.toString());
		}
		log.info("initAdjust(return):{}", json);
		return json;
	}

	/**
	 * 校验实时nc余额
	 * 
	 * @param accountId
	 * @param month
	 * @return
	 */
	@CrossOrigin(origins = "*", maxAge = 3600)
	@PostMapping(value = "oneTouchTotalSubmit")
	@ResponseBody
	public JSONObject oneTouchTotalSubmit(String accountsId, String month) {

		log.info("oneTouchTotal()accountsId:{}", accountsId);
		log.info("oneTouchTotal()month:{}", month);
		JSONObject json = new JSONObject();
		BaBalanceAdjustTotal total = new BaBalanceAdjustTotal();

		try {
			if (accountsId == null || month == null) {
				json.put(STATUS, "-1");
				return json;
			}

			List<String> list = StringUtil.getNumberListId(accountsId);
			UpdateWrapper<BaBalanceAdjustTotal> updateWrapper = new UpdateWrapper<>();
			total.setLockStatus("1");
			updateWrapper.in("ACCOUNT_ID", list);
			updateWrapper.eq("ACCOUNT_TIME", month);

			if (totalService.update(total, updateWrapper)) {

				json.put(STATUS, 200);
				json.put(RESULT, "更新成功");
			} else {
				json.put(STATUS, 0);
				json.put(RESULT, "更新失败");

			}

		} catch (Exception e) {
			json.put(STATUS, -2);
			log.error("校验实时nc余额异常{}", e.toString());
		}
		log.info("oneTouchTotal(return):{}", json);
		return json;
	}

	/**
	 * 一键生成调节表
	 * 
	 * @param accountId
	 * @param month
	 * @return
	 */
	@CrossOrigin(origins = "*", maxAge = 3600)
	@PostMapping(value = "oneTouchTotal")
	@ResponseBody
	public JSONObject oneTouchTotalVal(String accountsId, String month) {

		log.info("oneTouchTotal(accountsId):{}", accountsId);
		log.info("oneTouchTotal(month):{}", month);
		JSONObject json = new JSONObject();
		try {
			if (accountsId == null || month == null) {
				json.put(STATUS, "-1");
				return json;
			}
			String sql = StringSql.getString(accountsId, month);
			List<Map<String, Object>> maps = dao.selectMaps(sql); // 通过账套id 跟年月，取得账户id跟年月
			List<String> list = new ArrayList<>();
			if (maps != null && maps.size() > 0) {
				for (int i = 0; i < maps.size(); i++) {
					Map<String, Object> map = maps.get(i);
					if (map.get("ACCOUNT_TIME") != null && map.get("ACCOUNT_ID") != null) {
						String accountId = (String) map.get("ACCOUNT_ID");
						String accountTime = (String) map.get("ACCOUNT_TIME");
						boolean checkNc = detailsService.checkNc(accountId, accountTime);
						log.info("一键生成调节表校验:{}", checkNc);
						if (checkNc) {
							list.add(accountId);
							json.put(STATUS, "200");
							json.put(RESULT, list);
							return json;
						} else {
							json.put(RESULT, "校验nc失败！");
							json.put(STATUS, "0");
							return json;
						}
					}
				}
			} else {
				json.put(STATUS, "0");
				json.put(RESULT, "查询为空");
			}
		} catch (Exception e) {
			json.put(STATUS, "-2");
			log.error(" 一键生成调节表异常{}", e.toString());
		}
		log.info("oneTouchTotal()return:{}", json);
		return json;
	}

}
