package com.longfor.fsscreport.clear.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longfor.fsscreport.approval.mapper.CommonDao;
import com.longfor.fsscreport.clear.entity.DwCpClearUpBlance;
import com.longfor.fsscreport.clear.entity.DwCpClearUpDetail;
import com.longfor.fsscreport.clear.entity.DwCpClearUpTotal;
import com.longfor.fsscreport.clear.mapper.DwCpClearUpTotalMapper;
import com.longfor.fsscreport.clear.service.IDwCpClearUpBlanceService;
import com.longfor.fsscreport.clear.service.IDwCpClearUpDetailService;
import com.longfor.fsscreport.clear.service.IDwCpClearUpTotalService;
import com.longfor.fsscreport.nc.service.IOdsNcBalanceDetailService;
import com.longfor.fsscreport.nc.service.IOdsNcBalanceVoService;
import com.longfor.fsscreport.utils.CommandLineUtils;
import com.longfor.fsscreport.utils.StringUtil;

/**
 * <p>
 * 往来清理汇总表 服务实现类
 * </p>
 *
 * @author chenziyao
 * @since 2020-10-30
 */
@Service
public class DwCpClearUpTotalServiceImpl extends ServiceImpl<DwCpClearUpTotalMapper, DwCpClearUpTotal>
		implements IDwCpClearUpTotalService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private static final String RESULT = "result";

	private static final String STATUS = "status";

	@Autowired
	private IDwCpClearUpDetailService iDwCpClearUpDetailService;

	@Autowired
	private IDwCpClearUpBlanceService iDwCpClearUpBlanceService;

	@Autowired
	private DwCpClearUpTotalMapper mapper;

	@Autowired
	private IOdsNcBalanceVoService odsNcBalanceVoService;

	@Autowired
	private IOdsNcBalanceDetailService odsNcBalanceDetailService;

	private List<String> err = new ArrayList<>();

	@Autowired
	private CommonDao dao;

	@Override
	public List<DwCpClearUpTotal> getAllListBean(QueryWrapper<DwCpClearUpTotal> wrapper) {

		return mapper.selectList(wrapper);
	}

	@Override
	public JSONObject numberOfLocks(String accountsId, String quarter, String subjectCode) {
		JSONObject json = new JSONObject();
		try {
			//判断subjectCode(科目) 是否为空
			if (StringUtils.isNotBlank(subjectCode)) {
				//如果是明细类科目
				if (getDetailCode(subjectCode)) {
					json = iDwCpClearUpDetailService.numberOfLocksMx(accountsId, subjectCode, quarter);
				}
				//如果是余额类科目
				if (getBlanceCode(subjectCode)) {
					json = iDwCpClearUpBlanceService.numberOfLocksYe(accountsId, subjectCode, quarter);
				}
			} else {
				//循环处理账套信息
				List<String> accounts = StringUtil.getStringList(accountsId);
				QueryWrapper<DwCpClearUpTotal> wrapper = new QueryWrapper<>();
				wrapper.select("distinct SUBJECT_CODE");
				wrapper.in("ACCOUNTS_ID", accounts);
				wrapper.eq("QUARTER", quarter);
				List<Object> list = mapper.selectObjs(wrapper);
				int errCount = 0;
				StringBuffer resultMsg = new StringBuffer();
				if (list != null && list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						//处理明细类科目
						if (getDetailCode(list.get(i).toString())) {
							json = iDwCpClearUpDetailService.numberOfLocksMx(accountsId, list.get(i).toString(), quarter);
							if (errCount <= 5) { //只返回5条锁数失败信息
								if (json != null && StringUtils.isNotBlank(json.getString(STATUS)) && "0".equals(json.getString(STATUS))) {
									errCount++;
									resultMsg.append(json.getString(RESULT));
								}
							}
						}
						//处理余额类科目
						if (getBlanceCode(list.get(i).toString())) {
							json = iDwCpClearUpBlanceService.numberOfLocksYe(accountsId, list.get(i).toString(), quarter);
							if (errCount <= 5) {  //只返回5条锁数失败信息
								if (json != null && StringUtils.isNotBlank(json.getString(STATUS)) && "0".equals(json.getString(STATUS))) {
									errCount++;
									resultMsg.append(json.getString(RESULT));
								}
							}
						}
					}
				}
				if (resultMsg != null && 0 < resultMsg.toString().length()) {
					json.put(RESULT, resultMsg.toString());
					json.put(STATUS, "0");
				} else {
					json.put(RESULT, "锁数成功！");
					json.put(STATUS, "200");
				}
			}
		} catch (Exception e) {
			logger.error("往来清理锁数异常{}", e.toString());
			json.put(RESULT, "锁数异常！");
			json.put(STATUS, "-2");
		}
		return json;
	}

	private boolean getDetailCode(String subjectCode) {

		ArrayList<String> list = new ArrayList<>();
		list.add("113303");
		list.add("113304");
		list.add("113308");
		list.add("115101");
		list.add("115102");
		list.add("115104");
		list.add("113301");
		list.add("113399");
		list.add("218102");
		list.add("218199");
		list.add("21210401");// DW_CP_CLEAR_UP_DETAIL

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).contains(subjectCode)) {
				return true;
			}
		}

		return false;
	}

	private boolean getBlanceCode(String subjectCode) {

		ArrayList<String> list = new ArrayList<>();

		list.add("212102");
		list.add("218101");
		list.add("11519998");
		list.add("11519999");// DW_CP_CLEAR_UP_BLANCE
		list.add("21810399");
		if (list.contains(subjectCode)) {
			return true;
		}
		return false;
	}

	@Override
	public JSONObject initData(String quarter, String accountsId) {

		Map<String, String> map = new HashMap<>();
		JSONObject json = new JSONObject();

		try {

			logger.info("初始化账套 accountsId：{}", accountsId);
			QueryWrapper<DwCpClearUpTotal> wrapper = new QueryWrapper<>();
			wrapper.eq("QUARTER", quarter);
			wrapper.eq("ACCOUNTS_ID", accountsId);
			List<DwCpClearUpTotal> list = getAllListBean(wrapper);

			String string = list.toString();
			logger.info("初始化账套 获取汇总表数据：{}", string);
			for (int i = 0; i < list.size(); i++) {

				DwCpClearUpTotal total = list.get(i);
				String subjectCode = total.getSubjectCode();

				BigDecimal qm = new BigDecimal("0");
				// List<String> yecode = StringUtil.getWlqlYe();//如果包含余额类的科目取最大的年月

				StringBuilder sql = new StringBuilder();
				sql.append("select max(t.data_date) ");
				sql.append("from DW_CP_MONTH_QUARTER t ");

				sql.append("where t.quarter<'");
				sql.append(quarter);
				sql.append("'");

				map = StringUtil.getYearMonth(dao.select(sql.toString()));
				map.put("orgCode", accountsId);
				map.put("kemuCode", subjectCode);
				String string2 = map.toString();
				logger.info("nc入参为：{}", string2);
				json = odsNcBalanceVoService.saveList(map);// 取到实时nc

				qm = new BigDecimal(json.getString("qm"));// 得到nc余额
				//NC余额四舍五入
				if (qm != null) {
					qm = qm.setScale(2, BigDecimal.ROUND_HALF_UP);
				}

				if (json.getString("qmfx").equals("1")) { // 如果为1 ，代表是贷方

					qm = qm.multiply(new BigDecimal("-1"));
				}
				if (getBlanceCode(subjectCode)) {

					/**
					 * logger.info("往来清理进入余额类："); QueryWrapper<DwCpClearUpBlance> querywrapper = new
					 * QueryWrapper<DwCpClearUpBlance>(); querywrapper.eq("QUARTER", quarter);
					 * querywrapper.eq("ACCOUNTS_ID", total.getAccountsId());
					 * querywrapper.eq("SUBJECT_CODE", subjectCode); querywrapper.eq("CSH_FLAG",
					 * "1"); List<DwCpClearUpBlance> listBeanByWrapper =
					 * iDwCpClearUpBlanceService.getListBeanByWrapper(querywrapper);//获取余额类
					 * logger.info("初始化账套 获取余额表数据：" + listBeanByWrapper.toString());
					 * logger.info("初始化账套 获取code：" + subjectCode);
					 * 
					 * if (listBeanByWrapper.size() == 0) { DwCpClearUpBlance detail = new
					 * DwCpClearUpBlance(); detail.setAccountsId(accountsId);
					 * detail.setQuarter(quarter); detail.setAmount(new BigDecimal("0"));
					 * detail.setSubjectCode(subjectCode); json = clearData2(qm, detail); }else {
					 * 
					 * BigDecimal amount = new BigDecimal("0"); for (int y = 0; y <
					 * listBeanByWrapper.size(); y++) {
					 * 
					 * logger.info("======往来清理余额类余额开始相加==="); DwCpClearUpBlance detail =
					 * listBeanByWrapper.get(y); amount = amount.add(detail.getAmount()); }
					 * 
					 * logger.info("往来清理余额金额汇总为："+amount); for (int j = 0; j <
					 * listBeanByWrapper.size(); j++) {
					 * 
					 * logger.info("初始化账套 获取实时nc数据数据：" + json.toJSONString());
					 * 
					 * DwCpClearUpBlance dwCpClearUpDetail = listBeanByWrapper.get(j);// 得到明细实体
					 * dwCpClearUpDetail.setAmount(amount);
					 * 
					 * json = clearData2(qm, dwCpClearUpDetail); if
					 * (!json.get("status").equals("200")) { return json; } } }
					 */
					json.put(STATUS, "200");
				} else {
					logger.info("往来清理进入明细类");
					QueryWrapper<DwCpClearUpDetail> querywrapper = new QueryWrapper<DwCpClearUpDetail>();
					querywrapper.eq("QUARTER", quarter);
					querywrapper.eq("ACCOUNTS_ID", total.getAccountsId());
					querywrapper.eq("SUBJECT_CODE", subjectCode);
					querywrapper.eq("csh_flag", "1");
					List<DwCpClearUpDetail> listBeanByWrapper = iDwCpClearUpDetailService .getListBeanByWrapper(querywrapper);// 获得明细类
					String string3 = listBeanByWrapper.toString();
					
					
					logger.info("初始化账套 获取明细表表数据条数：{}", listBeanByWrapper.size());
					logger.info("初始化账套 获取明细表表数据：{}", string3);
					logger.info("初始化账套 获取code：{}", subjectCode);

					if (listBeanByWrapper.size() == 0) {
						DwCpClearUpDetail detail = new DwCpClearUpDetail();
						detail.setAccountsId(accountsId);
						detail.setQuarter(quarter);
						detail.setAmount(new BigDecimal("0"));
						detail.setSubjectCode(subjectCode);
						json = clearData(qm, detail, "hz");
						
						logger.info("clearData方法返回值：{}",json.toJSONString());
						logger.info("json........",json.getString("status"));
						logger.info("json1........",json.get("status").equals("200"));
						if (!json.getString("status").equals("200")) {
							logger.info("finsh........");
							return json;
						}
						
					} else {

						BigDecimal amount = new BigDecimal("0");
						for (int y = 0; y < listBeanByWrapper.size(); y++) {

							logger.info("======往来清理明细余额开始相加===");
							DwCpClearUpDetail detail = listBeanByWrapper.get(y);
							
							BigDecimal decimal = detail.getAmount();
							
							if(decimal!=null) {
								
								amount = amount.add(decimal);
							}
							
							logger.info("往来清理明细余额：{}",amount);
						}

						logger.info("往来清理汇总余额为：{}", amount);
						for (int j = 0; j < listBeanByWrapper.size(); j++) {

							String jsonString2 = json.toJSONString();
							logger.info("初始化账套 获取实时nc数据数据：{}", jsonString2);

							DwCpClearUpDetail dwCpClearUpDetail = listBeanByWrapper.get(j);// 得到明细实体
							dwCpClearUpDetail.setAmount(amount);
							String jsonString = json.toJSONString();
							logger.info("初始化账套 获取：{}", jsonString);
							json = clearData(qm, dwCpClearUpDetail, null);
							
							logger.info("clearData方法返回值：{}",json.toJSONString());
							logger.info("json........",json.getString("status"));
							logger.info("json1........",json.get("status").equals("200"));
							if (!json.getString("status").equals("200")) {
								logger.info("finsh........");
								return json;
							}
						}
					}
				}
			}

			if (json.getString(STATUS).equals("200")) {
				mapper.initData(quarter, accountsId);
				json.put(RESULT, "初始化成功");
			}
		} catch (Exception e) {
			json.put(STATUS, "-2");
			json.put(RESULT, "往来清理初始化异常");
			logger.error("往来清理初始化异常{}", e.toString());

		}
		return json;
	}

	public JSONObject clearData(BigDecimal qm, DwCpClearUpDetail clear, String flag) {

		logger.info("clearData()qm:{}", qm);
		logger.info("clearData()clear:{}", clear);
		JSONObject json = new JSONObject();

		try {

			if (flag == null) {
				
				Date date =null;
				if(clear.getYear()!=null &&  clear.getMonth()!=null && clear.getDayth()!=null ) {
					
					date = StringUtil.checkDate(clear.getYear(), clear.getMonth(), clear.getDayth());
					
				}
				if (date == null) {

					detailBack(clear.getAccountsId(), clear.getQuarter());
					json.put(RESULT, clear.getSubjectCode() + "科目时间填写错误！");
					json.put(STATUS, "-2");
					return json;
				}
			}

			String reseon = clear.getReseon();// 往来成因
			String isUnGeneral = clear.getIsUnGeneral();// 是否异常
			String ptUser = clear.getPtUser();// 平台填写人
			String clearTime = clear.getClearTime();// 预计清理时间
			String responUser = clear.getResponUser();// 责任人
			String responDept = clear.getResponDept();// 责任部门
			String reMark = clear.getReMarkDq();// 备注
			String dqUser = clear.getDqUser();// 地区填写人

			if(clear.getAmount()==null) {
				clear.setAmount(new BigDecimal("0"));
			}
			
			if (qm == null || qm.compareTo(clear.getAmount()) != 0) {

				logger.info("科目余额不等于nc余额");
				BigDecimal twoDecimal = StringUtil.getTwoDecimal(qm);
				logger.info("科目余额不等于nc余额==");
				logger.info("nc余额{}", twoDecimal);
				logger.info("科目汇总余额{}", clear.getAmount());
				detailBack(clear.getAccountsId(), clear.getQuarter());
				json.put("msg", err.toString());
				json.put(STATUS, "0");
				json.put(RESULT, "科目" + clear.getSubjectCode() + "汇总为" + clear.getAmount() + "，nc余额为" + twoDecimal
						+ " ,金额不一致，无法初始化，请核验后重新初始化"); 
				return json;
			}
			if (flag == null && (StringUtils.isBlank(reseon) || StringUtils.isBlank(ptUser) || clearTime == null
					|| StringUtils.isBlank(responDept) || StringUtils.isBlank(responUser)
					|| ("异常".equals(isUnGeneral) && StringUtils.isBlank(reMark)))) {

				logger.info("必填字段有为空====");
				detailBack(clear.getAccountsId(), clear.getQuarter());
				String string = err.toString();

				logger.error("初始化err值{}", string);
				json.put("msg", err.toString());
				json.put(STATUS, "0");
				json.put(RESULT, "必填字段有未填写的数据，请重试!");
				return json;
			}

			json.put(RESULT, "校验成功");
			json.put(STATUS, "200");

		} catch (Exception e) {
			detailBack(clear.getAccountsId(), clear.getQuarter());
			json.put(STATUS, "-2");
			json.put(RESULT, "初始化异常");
			logger.error("初始化异常{}", e.toString());
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 余额类校验
	 * 
	 * @param qm
	 * @param bance
	 * @return
	 */
	public JSONObject clearData2(BigDecimal qm, DwCpClearUpBlance bance) {

		logger.info("初始化账套 clearData获取qm：{}", qm);
		logger.info("初始化账套 clearData获取clear：{}", bance);
		JSONObject json = new JSONObject();

		try {
			boolean falg = (qm == null || qm.compareTo(bance.getAmount()) != 0);
			logger.info("期末余额校验为:{}", falg);

			if (qm == null || qm.compareTo(bance.getAmount()) != 0) {

				BigDecimal twoDecimal = StringUtil.getTwoDecimal(qm);
				banceBack(bance.getAccountsId(), bance.getQuarter());
				json.put("msg", err.toString());
				json.put(STATUS, "0");
				json.put(RESULT, "科目" + bance.getSubjectCode() + "汇总为" + bance.getAmount() + "，nc余额为" + twoDecimal
						+ " ,金额不一致，无法初始化，请核验后重新初始化");
			} else {
				json.put(RESULT, "校验成功！");
				json.put(STATUS, "200");
			}
		} catch (Exception e) {
			logger.error("clearData2(){}", e.toString());
			json.put(STATUS, "-2");
			json.put(RESULT, "初始化异常");
		}
		return json;
	}

	@Override
	@Transactional
	public JSONObject getNcDate(String syncDateStr, String quarter, String accountsId, String subjectCode) {

		StringBuilder sql = new StringBuilder();
		sql.append("select max(t.data_date) ");
		sql.append("from DW_CP_MONTH_QUARTER t ");
		sql.append("where t.data_date < to_char(sysdate, 'yyyy-mm') ");
		sql.append(" and t.quarter='");
		sql.append(quarter);
		sql.append("'");
		String yearMonth = dao.select(sql.toString());

		JSONObject json = null;
		if(StringUtils.isNotBlank(subjectCode) &&(subjectCode.equals("113399") || subjectCode.equals("218199"))) {
			//余额明细表使用新传递的日期参数，余额表还是用原来的YearMonth
			//查询NC余额明细 - 使用新传递的参数
			json = odsNcBalanceDetailService.saveList(accountsId, subjectCode, syncDateStr);
		}

		if (json != null && StringUtils.isNotBlank(json.getString("status"))
				&& "200".equals(json.getString("status"))) {
			json.put("result", " NC外系统查询科目余额明细表取数失败");
		}
		//查询NC余额 - 还是使用最大月份
		json = odsNcBalanceVoService.saveList(accountsId, subjectCode, yearMonth);
		if (json != null && StringUtils.isNotBlank(json.getString("status"))
				&& "200".equals(json.getString("status"))) {
			json.put("result", "NC外系统查询科目余额表取数失败");
		}

		/***
		 * Map<String,String> map = new HashMap<String, String>(); map =
		 * StringUtil.getYearMonth(dao.select(sql.toString())); map.put("orgCode",
		 * accountsId); map.put("kemuCode", subjectCode); map.put("quarter", quarter);
		 * 
		 * json = odsNcAuxiliaryBalanceService_s.saveList(map);
		 * 
		 * if (json != null && StringUtils.isNotBlank(json.getString("status")) &&
		 * "200".equals(json.getString("status"))) { json.put("result",
		 * "NC外系统查询辅助余额或者明细取数失败"); return json; } json =
		 * odsNcAuxiliaryBalanceService.saveList(map,initKemuFuzhu()); //如果NC余额更新失败 if
		 * (json != null && StringUtils.isNotBlank(json.getString("status")) &&
		 * !"200".equals(json.getString("status"))) { json.put("result",
		 * "NC外系统查询辅助余额取数失败"); return json; } json =
		 * odsNcBalanceDetailsService.saveList(map); //如果NC余额更新失败 if (json != null &&
		 * StringUtils.isNotBlank(json.getString("status")) &&
		 * !"200".equals(json.getString("status"))) { json.put("result",
		 * "NC外系统查询辅助余额明细取数失败"); return json; } json =
		 * odsNcBalanceVoService.saveList(map); //如果NC余额更新失败 if (json != null &&
		 * StringUtils.isNotBlank(json.getString("status")) &&
		 * !"200".equals(json.getString("status"))) { json.put("result",
		 * "NC外系统查询科目余额取数失败"); return json; } json =
		 * odsNcBalanceDetailService.saveList(map); //如果NC余额更新失败 if (json != null &&
		 * StringUtils.isNotBlank(json.getString("status")) &&
		 * !"200".equals(json.getString("status"))) { json.put("result",
		 * "NC外系统查询科目余额明细取数失败"); return json; }
		 * //odsNcAuxiliaryBalanceService_s.saveList(map);
		 * 
		 */

		try {
			StringBuilder sb = new StringBuilder();
			sb.append("java -jar WLQL_DJ.jar ");
			sb.append(syncDateStr); //使用新传递的时间参数，代替quarter
			sb.append(" ");
			sb.append(accountsId);

			logger.info("NC存储过程调用开始。");
			String string = sb.toString();
			logger.info(string);
			CommandLineUtils.exeCmd(sb.toString());
			logger.info("NC存储过程调用结束。");
			mapper.getNcData(syncDateStr, quarter, accountsId, subjectCode);
		} catch (Exception e) {
			json.put("result", "NC取数更新异常");
			json.put("status", "-2");
			logger.info("NC取数更新异常{}", e.toString());
		}
		return json;
	}

	/**
	 * 余额类数据回退
	 * 
	 * @param accounts
	 * @return
	 */
	private List<String> banceBack(String accounts, String quarter) {

		logger.info("banceBack()id:{}", accounts);

		DwCpClearUpBlance blance = new DwCpClearUpBlance();
		blance.setReseon("");
		blance.setIsUnGeneral("");
		blance.setPtUser("");
		blance.setClearTime(null);
		blance.setResponUser("");
		blance.setResponDept("");
		blance.setReMark("");
		blance.setDqUser("");
		QueryWrapper<DwCpClearUpBlance> wrapper2 = new QueryWrapper<>();
		wrapper2.eq("ACCOUNTS_ID", accounts);
		wrapper2.eq("QUARTER", quarter);
		boolean update = iDwCpClearUpBlanceService.update(blance, wrapper2);// 更新该账套对应季度下的余额表中的填报字段数据为空
		if (!update) {
			err.add("更新该账套对应季度下的余额表中的填报字段数据为空失败");

		}
		return err;
	}

	/**
	 * 明细类数据回退
	 * 
	 * @param accounts
	 * @return
	 */
	private List<String> detailBack(String accounts, String quarter) {

		logger.info("detailBack()accounts{}", accounts);
		logger.info("detailBack()quarter{}", quarter);
		/**
		 * 删除明细类数据
		 */
		QueryWrapper<DwCpClearUpDetail> querywrapper = new QueryWrapper<>();
		querywrapper.eq("ACCOUNTS_ID", accounts);
		querywrapper.eq("CSH_FLAG", "1");
		querywrapper.eq("quarter", quarter);

		Integer delete = iDwCpClearUpDetailService.deleteByWrapper(querywrapper);// 删除该账套对应季度下的明细表所有初始化数据
		logger.error("初始化 删除该账套对应季度下的明细表所有初始化数据数量为-----{}", delete);
		if (delete == 0) {
			err.add("删除该账套对应季度下的明细表所有初始化数据失败或者数据为空");
		}

		/**
		 * 删除csh_flag等于1的余额类数据
		 */
		QueryWrapper<DwCpClearUpBlance> wrapper2 = new QueryWrapper<>();
		wrapper2.eq("ACCOUNTS_ID", accounts);
		wrapper2.eq("QUARTER", quarter);
		wrapper2.eq("csh_flag", "1");
		boolean remove = iDwCpClearUpBlanceService.remove(wrapper2);
		if (!remove) {
			err.add("更新该账套对应季度下的余额表中的填报字段数据为空失败或者数据为空");

		}

		/**
		 * 吧csh_flag等于0 的数据置空
		 */
		DwCpClearUpBlance blance = new DwCpClearUpBlance();
		blance.setAmount(new BigDecimal("0"));
		blance.setReseon("");
		blance.setIsUnGeneral("");
		blance.setPtUser("");
		blance.setClearTime(null);
		blance.setResponUser("");
		blance.setResponDept("");
		blance.setReMark("");
		blance.setDqUser("");
		QueryWrapper<DwCpClearUpBlance> wrapper3 = new QueryWrapper<>();
		wrapper3.eq("ACCOUNTS_ID", accounts);
		wrapper3.eq("QUARTER", quarter);
		wrapper3.eq("csh_flag", "0");
		boolean update = iDwCpClearUpBlanceService.update(blance, wrapper2);// 更新该账套对应季度下的余额表中的填报字段数据为空
		if (!update) {
			err.add("更新该账套对应季度下的余额表中的填报字段数据为空失败或者数据为空");

		}

		return err;
	}

	public static Map<String, String> initKemuFuzhu() {

		Map<String, String> kemuFuzhu = new HashMap<>();
		kemuFuzhu.put("113303", "<String>RY</String>");
		kemuFuzhu.put("113304", "<String>KS</String><String>RY</String>");
		kemuFuzhu.put("113308", "<String>KS</String><String>RY</String>");
		kemuFuzhu.put("115101", "<String>KS</String><String>RY</String>");
		kemuFuzhu.put("115102", "<String>KS</String><String>RY</String>");
		kemuFuzhu.put("115104", "<String>KS</String><String>RY</String>");
		kemuFuzhu.put("113301", "<String>KS</String>");
		kemuFuzhu.put("218102", "<String>KS</String>");
		kemuFuzhu.put("21210401", "<String>KS</String><String>RY</String><String>DJH</String>");
		kemuFuzhu.put("212102", "<String>KS</String>");
		kemuFuzhu.put("218101", "<String>KS</String>");
		kemuFuzhu.put("11519998", "<String>KS</String><String>XM</String>");
		kemuFuzhu.put("11519999", "<String>KS</String><String>XM</String>");
		return kemuFuzhu;

	}

	/**
	 * 往来清理初始化 -老方法
	 * 
	 * @param quarter
	 * @param accountsId
	 * @return
	 */
	@Override
	public JSONObject initData1(String quarter, String accountsId) {
		Map<String, String> map = new HashMap<>();
		JSONObject json = new JSONObject();
		try {
			logger.info("初始化账套 accountsId：{}", accountsId);
			QueryWrapper<DwCpClearUpTotal> wrapper = new QueryWrapper<DwCpClearUpTotal>();
			wrapper.eq("QUARTER", quarter);
			wrapper.eq("ACCOUNTS_ID", accountsId);
			List<DwCpClearUpTotal> list = getAllListBean(wrapper);
			logger.info("初始化账套 获取汇总表数据：{}", list);
			for (int i = 0; i < list.size(); ++i) {
				DwCpClearUpTotal total = list.get(i);
				String subjectCode = total.getSubjectCode();

				BigDecimal qm = new BigDecimal("0");

				List<String> yecode = StringUtil.getWlqlYe();
				StringBuilder sql = new StringBuilder();
				sql.append("select max(t.data_date) ");
				sql.append("from DW_CP_MONTH_QUARTER t ");
				if (yecode.contains(subjectCode)) {
					sql.append("where t.quarter='");
				} else {
					sql.append("where t.quarter<'");
				}
				sql.append(quarter);
				sql.append("'");

				map = StringUtil.getYearMonth(dao.select(sql.toString()));
				map.put("orgCode", accountsId);
				map.put("kemuCode", subjectCode);

				String string2 = map.toString();
				logger.info("nc入参为：{}", string2);
				json = odsNcBalanceVoService.saveList(map);

				qm = new BigDecimal(json.getString("qm"));

				if (json.getString("qmfx").equals("1")) {
					qm = qm.multiply(new BigDecimal("-1"));
				}
				if (getBlanceCode(subjectCode)) {

					logger.info("往来清理进入余额类：");
					json.put("status", "200");
				} else {
					logger.info("往来清理进入明细类：");
					QueryWrapper<DwCpClearUpDetail> querywrapper = new QueryWrapper<DwCpClearUpDetail>();
					querywrapper.eq("QUARTER", quarter);
					querywrapper.eq("ACCOUNTS_ID", total.getAccountsId());
					querywrapper.eq("SUBJECT_CODE", subjectCode);
					querywrapper.eq("csh_flag", "1");
					List<DwCpClearUpDetail> listBeanByWrapper = iDwCpClearUpDetailService
							.getListBeanByWrapper(querywrapper);// 获得明细类
					String string = listBeanByWrapper.toString();
					logger.info("初始化账套 获取明细表表数据：{}", string);
					logger.info("初始化账套 获取code：{}", subjectCode);

					if (listBeanByWrapper.size() == 0) {
						DwCpClearUpDetail detail = new DwCpClearUpDetail();
						detail.setAccountsId(accountsId);
						detail.setQuarter(quarter);
						detail.setAmount(new BigDecimal("0"));
						detail.setSubjectCode(subjectCode);
						json = clearData1(qm, detail, "hz");
					} else {
						BigDecimal amount = new BigDecimal("0");
						for (int y = 0; y < listBeanByWrapper.size(); ++y) {
							logger.info("======往来清理明细余额开始相加===");
							DwCpClearUpDetail detail = listBeanByWrapper.get(y);
							
							BigDecimal detailAmount = detail.getAmount();
							
							if(detailAmount!=null) {
								
								amount = amount.add(detailAmount);
							}
							
						}
						logger.info("往来清理汇总余额为：{}", amount);
						for (int j = 0; j < listBeanByWrapper.size(); ++j) {

							String jsonString2 = json.toJSONString();
							logger.info("初始化账套 获取实时nc数据数据：{}", jsonString2);
							DwCpClearUpDetail dwCpClearUpDetail = listBeanByWrapper.get(j);
							dwCpClearUpDetail.setAmount(amount);

							String jsonString = json.toJSONString();
							logger.info("初始化账套 获取：{}", jsonString);
							json = clearData1(qm, dwCpClearUpDetail, null);
							if (!(json.get(STATUS).equals("200"))) {
								return json;
							}
						}
					}
				}
			}

			if (json.getString(STATUS).equals("200")) {
				mapper.initData(quarter, accountsId);
				json.put(RESULT, "初始化成功");
			}
		} catch (Exception e) {
			json.put(STATUS, "-2");
			json.put(RESULT, "初始化异常！");
			logger.error("往来清理初始化异常{}", e.toString());
		}
		return json;
	}

	private List<String> detailBack1(String accounts, String quarter) {
		QueryWrapper<DwCpClearUpDetail> querywrapper = new QueryWrapper<>();
		querywrapper.eq("ACCOUNTS_ID", accounts);
		querywrapper.eq("CSH_FLAG", "1");
		querywrapper.eq("quarter", quarter);

		Integer delete = iDwCpClearUpDetailService.deleteByWrapper(querywrapper);
		logger.error("初始化 删除该账套对应季度下的明细表所有初始化数据数量为-----{}", delete);
		if (delete.intValue() == 0) {
			err.add("删除该账套对应季度下的明细表所有初始化数据失败或者数据为空");
		}

		DwCpClearUpBlance blance = new DwCpClearUpBlance();
		blance.setReseon("");
		blance.setIsUnGeneral("");
		blance.setPtUser("");
		blance.setClearTime(null);
		blance.setResponUser("");
		blance.setResponDept("");
		blance.setReMark("");
		blance.setDqUser("");
		QueryWrapper<DwCpClearUpBlance> wrapper2 = new QueryWrapper<>();
		wrapper2.eq("ACCOUNTS_ID", accounts);
		wrapper2.eq("QUARTER", quarter);
		boolean update = iDwCpClearUpBlanceService.update(blance, wrapper2);
		if (!(update)) {
			err.add(new StringBuilder().append(accounts).append("更新该账套对应季度下的余额表中的填报字段数据为空失败").toString());
		}

		return err;
	}

	public JSONObject clearData1(BigDecimal qm, DwCpClearUpDetail clear, String flag) {
		logger.info("初始化账套 clearData获取qm：{}", qm);
		logger.info("初始化账套 clearData获取clear：{}", clear);
		JSONObject json = new JSONObject();
		try {
			if (flag == null) {
				Date date = StringUtil.checkDate(clear.getYear(), clear.getMonth(), clear.getDayth());
				if (date == null) {
					detailBack1(clear.getAccountsId(), clear.getQuarter());
					json.put("result",
							new StringBuilder().append(clear.getSubjectCode()).append("科目时间填写错误！").toString());
					json.put("status", "-2");
					return json;
				}
			}

			String reseon = clear.getReseon();
			String isUnGeneral = clear.getIsUnGeneral();
			String ptUser = clear.getPtUser();
			String clearTime = clear.getClearTime();
			String responUser = clear.getResponUser();
			String responDept = clear.getResponDept();
			String reMark = clear.getReMarkDq();
			String dqUser = clear.getDqUser();

			if ((qm == null) || (qm.compareTo(clear.getAmount()) != 0)) {

				logger.info("科目余额不等于nc余额");
				BigDecimal twoDecimal = StringUtil.getTwoDecimal(qm);
				logger.info("科目余额不等于nc余额==");
				logger.info("nc余额{}", twoDecimal);
				logger.info("科目汇总余额{}", clear.getAmount());

				detailBack1(clear.getAccountsId(), clear.getQuarter());
				json.put("msg", err.toString());
				json.put("status", "0");
				json.put("result",
						new StringBuilder().append("科目").append(clear.getSubjectCode()).append("汇总为")
								.append(clear.getAmount()).append("，nc余额为").append(twoDecimal)
								.append(" ,金额不一致，无法初始化，请核验后重新初始化").toString());

				return json;
			}
			if ((flag == null) && (((StringUtils.isBlank(reseon)) || (StringUtils.isBlank(ptUser))
					|| (clearTime == null) || (StringUtils.isBlank(responDept)) || (StringUtils.isBlank(responUser))
					|| (("异常".equals(isUnGeneral)) && (StringUtils.isBlank(reMark)))))) {
				logger.info("必填字段有为空====");
				detailBack1(clear.getAccountsId(), clear.getQuarter());
				String string = err.toString();
				logger.error("初始化err值{}", string);
				json.put("msg", err.toString());
				json.put("status", "0");
				json.put("result", "必填字段有未填写的数据，请重试!");
				return json;
			}

			json.put("result", "校验成功！");
			json.put("status", "200");
		} catch (Exception e) {
			detailBack(clear.getAccountsId(), clear.getQuarter());
			json.put("status", "-2");
			json.put("result", "初始化异常！");
			logger.error("初始化异常{}", e.toString());
		}
		return json;
	}
	
	
}
