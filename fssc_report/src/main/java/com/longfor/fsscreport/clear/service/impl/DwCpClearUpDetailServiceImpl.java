package com.longfor.fsscreport.clear.service.impl;

import java.math.BigDecimal;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longfor.fsscreport.approval.mapper.CommonDao;
import com.longfor.fsscreport.clear.entity.DwCpClearUpDetail;
import com.longfor.fsscreport.clear.entity.DwCpClearUpTotal;
import com.longfor.fsscreport.clear.entity.DwCpDataCheck;
import com.longfor.fsscreport.clear.mapper.DwCpClearUpDetailMapper;
import com.longfor.fsscreport.clear.service.IDwCpClearUpDetailService;
import com.longfor.fsscreport.clear.service.IDwCpClearUpTotalService;
import com.longfor.fsscreport.clear.service.IDwCpDataCheckService;
import com.longfor.fsscreport.utils.StringUtil;
import com.longfor.fsscreport.vo.Message;

/**
 * <p>
 * 往来清理-往来明细类 服务实现类
 * </p>
 *
 * @author chenziyao
 * @since 2020-10-28
 */
@Service
public class DwCpClearUpDetailServiceImpl extends ServiceImpl<DwCpClearUpDetailMapper, DwCpClearUpDetail>
		implements IDwCpClearUpDetailService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private static final  String RESULT = "result";
	private static final  String STATUS = "status";

	@Autowired
	private DwCpClearUpDetailMapper mapper;


	@Autowired
	private IDwCpClearUpTotalService iDwCpClearUpTotalService;

	@Autowired
	private IDwCpDataCheckService dwCpDataCheckService;
	
	@Autowired
	private CommonDao dao;

	@Override
	public List<DwCpClearUpDetail> getListBeanByWrapper(QueryWrapper<DwCpClearUpDetail> queryWrapper) {
		
		return mapper.selectList(queryWrapper);
	}

	@Override
	public JSONObject updataClearDetailStatus(String ids, List<Long> array) {
		JSONObject json = new JSONObject();
		logger.info("往来清理勾兑入参：{}" , ids);
		try {
			//判断入参明细记录主键列表是否为空 - ids和array都是主键列表，2者选择其一就行
			if (StringUtils.isBlank(ids) && array == null) {
				json.put(STATUS, "-1");
				json.put(RESULT, "参数不合法");
				return json;
			}
			logger.info("往来清理勾兑入参ids：{}" , ids);
			logger.info("往来清理勾兑入参array：{}" , array);
			BigDecimal amount = new BigDecimal(0);
			//运用HashSet的去重原理保存客商名称，用来判断是否存在多个客商
			Set<String> set = new HashSet<>();

			//根据入参获取明细记录列表
			QueryWrapper<DwCpClearUpDetail> queryWrapper = new QueryWrapper<>();

			if (StringUtils.isBlank(ids)) { // 如果ids为空 代表是新接口传来的数据
				queryWrapper.in("id", array);
			} else {
				queryWrapper.in("id", StringUtil.getNumberListId(ids));
			}
			List<DwCpClearUpDetail> list = getListBeanByWrapper(queryWrapper);
			String string = list.toString();
			logger.info(" 往来清理-往来明细类：{}" , string);

			for (int i = 0; i < list.size(); i++) {
				DwCpClearUpDetail clear = list.get(i);
				amount = amount.add(clear.getAmount()); //所有记录的金额汇总应该为0
				set.add(StringUtil.replaceBlank(clear.getKsname())); //根据客商名称勾兑-同一个客商才可以勾兑
			}
			if (amount.compareTo(new BigDecimal(0)) != 0) {
				json.put(STATUS, "0");
				json.put(RESULT, "勾选金额明细不为0！");
				return json;
			}
			if (set.size() > 1) {
				json.put(STATUS, "0");
				json.put(RESULT, "勾兑数据客商不一致！");
				return json;
			}
			DwCpClearUpDetail detail = new DwCpClearUpDetail();
			String uuid = StringUtil.getUUID();
			detail.setMatchingcode(uuid);
			detail.setGdFlag("");
			if (update(detail, queryWrapper)) {
				json.put(RESULT, "插入匹配成功");
				json.put(STATUS, "200");
				return json;
			} else {
				json.put(RESULT, "插入匹配号异常");
				json.put(STATUS, "0");
				return json;
			}

		} catch (Exception e) {
			logger.error(" 往来清理-勾选异常：{}" , e.toString());
			json.put(STATUS, "-2");
			json.put(RESULT, "勾兑异常");
		}
		return null;
	}

	@Override
	public JSONObject updSendMsgFlag(String ids) {
		JSONObject json = new JSONObject();
		logger.info("往来清理龙信消息推送参数：{}" , ids);
		try {
			//判断入参明细记录主键列表是否为空 - ids和array都是主键列表，2者选择其一就行
			if (StringUtils.isBlank(ids)) {
				json.put(STATUS, "-1");
				json.put(RESULT, "参数不合法");
				return json;
			}
			BigDecimal amount = new BigDecimal(0);
			//运用HashSet的去重原理保存客商名称，用来判断是否存在多个客商
			Set<String> set = new HashSet<>();

			//根据入参获取明细记录列表
			QueryWrapper<DwCpClearUpDetail> queryWrapper = new QueryWrapper<>();
			queryWrapper.in("id", StringUtil.getNumberListId(ids));
			queryWrapper.eq("GX_FLAG", "0");
			//获取往来清理明细记录
			List<DwCpClearUpDetail> list = getListBeanByWrapper(queryWrapper);
			if (list == null) {
				list = new ArrayList<DwCpClearUpDetail>();
			}
			//String string = list.toString();
			logger.info(" 往来清理龙信推送记录数量：{}" +  list.size());

			List<DwCpClearUpDetail> updList = new ArrayList<DwCpClearUpDetail>();
			int batchSize = 0;
			DwCpClearUpDetail updRecord = null;
			for (int i = 0; i < list.size(); i++) {
				batchSize++;
				updRecord = list.get(i);
				updRecord.setGxFlag("1");
				updRecord.setSendConfirmDate(new Date());
				updList.add(updRecord);
				if (batchSize/200 == 0) {
					this.updateBatchById(updList, updList.size());
					updList.clear();
				}
			}
			if (0 < updList.size()) {
				this.updateBatchById(updList, updList.size());
			}
		} catch (Exception e) {
			logger.error(" 往来清理-发送确认异常：{}" + e.toString());
			json.put(STATUS, "-2");
			json.put(RESULT, "发送确认异常");
		}
		json.put(RESULT, "发送确认成功");
		json.put(STATUS, "200");
		return json;
	}

	/**
	 * 取消勾兑
	 */
	@Override
	public JSONObject notClearDetailStatus(String uuid, List<String> array) {

		JSONObject json = new JSONObject();
		logger.info("往来清理取消勾兑入参uuid：{}" , uuid);
		logger.info("往来清理取消勾兑入参array：{}" , array);
		try {
			if (StringUtils.isBlank(uuid) && array == null) {
				json.put(STATUS, "-1");
				json.put(RESULT, "非法参数");
				return json;
			}

			QueryWrapper<DwCpClearUpDetail> queryWrapper = new QueryWrapper<>();

			if (StringUtils.isBlank(uuid)) { // 如果ids为空 代表是新接口传来的数据

				queryWrapper.in("MATCHINGCODE", array);
			} else {
				
				queryWrapper.eq("MATCHINGCODE", uuid);
			}

			List<DwCpClearUpDetail> list = getListBeanByWrapper(queryWrapper);

			for (int i = 0; i < list.size(); i++) {

				DwCpClearUpDetail dwCpClearUpDetail = list.get(i);
				String subjectCode = dwCpClearUpDetail.getSubjectCode();
				String accountsId = dwCpClearUpDetail.getAccountsId();
				String quarter = dwCpClearUpDetail.getQuarter();

				QueryWrapper<DwCpClearUpTotal> wrapper = new QueryWrapper<>();
				wrapper.eq("SUBJECT_CODE", subjectCode);
				wrapper.eq("ACCOUNTS_ID", accountsId);
				wrapper.eq("QUARTER", quarter);
				DwCpClearUpTotal one = iDwCpClearUpTotalService.getOne(wrapper);
				if ("1".equals(one.getLockStatus())) {
					json.put(STATUS, "0");
					json.put(RESULT, "已锁数，取消勾兑失败");
					return json;
				}
			}

			DwCpClearUpDetail detail = new DwCpClearUpDetail();
			detail.setMatchingcode("");
			detail.setGdFlag("");
			if (update(detail, queryWrapper)) {
				json.put(RESULT, "取消勾兑成功！");
				json.put(STATUS, "200");
			} else {
				json.put(RESULT, "取消勾兑失败");
				json.put(STATUS, "0");
			}

		} catch (Exception e) {
			logger.error(" 往来清理-勾选异常：{}" , e.toString());
			json.put(STATUS, "-2");
			json.put(RESULT, "勾兑异常");
		}
		return json;
	}

	@Override
	public JSONObject numberOfLocksMx(String accountsIdStr, String subjectCode, String quarter) {
		JSONObject json = new JSONObject();
		logger.info("往来清理校验锁数-明细类科目:accountsId{} "  , accountsIdStr);
		logger.info("往来清理校验锁数-明细类科目:subjectCode{} " , subjectCode);
		logger.info("往来清理校验锁数-明细类科目:quarter{} " , quarter);
		try {
			//待处理账套列表
			List<String> accountList = null;
			if (StringUtils.isNotBlank(accountsIdStr)) {
				//如果传的是多个账套
				if (accountsIdStr.contains(",")) {
					accountList = StringUtil.getStringList(accountsIdStr);
				} else {
					accountList = new ArrayList<String>();
					accountList.add(accountsIdStr);
				}
			}
			//循环处理账套的校验和锁数
			if (accountList != null && 0 < accountList.size()) {
				for (String accountsId : accountList) {
					logger.info("往来清理校验锁数-明细类科目-循环单账套: 账套ID{}"  , accountsId);
					/**
					 * 校验表先删除
					 */
					QueryWrapper<DwCpDataCheck> queryWrapper = new QueryWrapper<>();
					queryWrapper.eq("ACCOUNTS_ID", accountsId);
					queryWrapper.eq("QUARTER", quarter);
					queryWrapper.eq("SUBJECT_CODE", subjectCode);
					Integer deleteByParm = dwCpDataCheckService.deleteByParm(queryWrapper);
					if(deleteByParm == 0) {
						logger.error("校验数据删除失败");
					}

					/**
					 * 保存校验表数据
					 */
					DwCpDataCheck check = new DwCpDataCheck();
					check.setAccountsId(accountsId);
					check.setQuarter(quarter);
					check.setSubjectCode(subjectCode);
					//获取往来清理明细数据
					QueryWrapper<DwCpClearUpDetail> query = new QueryWrapper<>();
					query.eq("ACCOUNTS_ID", accountsId);
					query.eq("QUARTER", quarter);
					query.eq("SUBJECT_CODE", subjectCode);
					query.isNull("MATCHINGCODE");
					List<DwCpClearUpDetail> list = getListBeanByWrapper(query);

					//错误处理列表
					List<String> err = new ArrayList<>();

					StringBuilder buffer = new StringBuilder();  //四项校验
					StringBuilder buffer2 = new StringBuilder(); //往来成因校验
					StringBuilder buffer3 = new StringBuilder(); //余额校验
					//是否有错误flag
					boolean errHave=false;
					Integer errBack=0;
					BigDecimal balance=null;//科目明细总额
					BigDecimal ncBalance = null;//汇总余额
					if (list != null && 0 < list.size()) {
						//循环处理往来明细数据
						for (int i = 0; i < list.size(); i++) {
							int errCount = 0;

							DwCpClearUpDetail detail = list.get(i);
							String string = detail.toString();
							logger.info("明细详细数据为{}" , string);

							String reseon = detail.getReseon();//往来成因
							String clearTime = detail.getClearTime();//预计清理时间
							String matchingcode = detail.getMatchingcode(); //匹配号
							String responUser = detail.getResponUser(); //责任人
							String responDept = detail.getResponDept();//责任部门
							String isUnGeneral = detail.getIsUnGeneral();//是否异常
							String reMarkDq = detail.getReMarkDq();//备注地区

							//预计清理时间、责任人，责任部门
							if(StringUtils.isBlank(matchingcode) ) { //如果匹配号为空,则进行相关校验
								//校验责任人是否为空
								if(StringUtils.isBlank(responUser)) {
									errHave=true;
									if(errCount==0) {
										buffer.append("账套" + accountsId + "锁数失败，科目" + subjectCode + ":");
										buffer.append("第"+(i+1)+"条:");
										buffer.append("责任人为空");
									} else {
										buffer.append(",责任人为空");
									}
									errCount++;
								}
								//校验是否异常是否为空
								if( StringUtils.isBlank(isUnGeneral) ) {
									errHave=true;
									if(errCount==0) {
										buffer.append("账套" + accountsId + "锁数失败，科目" + subjectCode + ":");
										buffer.append("第"+(i+1)+"条:");
										buffer.append("是否异常项为空");
									} else {
										buffer.append(",是否异常项为空");
									}
									errCount++;
								}
								//如果是异常状态，校验被备注地区是否为空
								if("异常".equals(isUnGeneral)){
									if( StringUtils.isBlank(reMarkDq) ) {
										errHave=true;
										if(errCount==0) {
											buffer.append("账套" + accountsId + "锁数失败，科目" + subjectCode + ":");
											buffer.append("第"+(i+1)+"条:");
											buffer.append("备注地区为空");
										} else {
											buffer.append(",备注地区为空");
										}
										errCount++;
									}
								}
								//校验责任部门是否为空
								if( StringUtils.isBlank(responDept) ) {
									errHave=true;
									if(errCount==0) {
										buffer.append("账套" + accountsId + "锁数失败，科目" + subjectCode + ":");
										buffer.append("第"+(i+1)+"条:");
										buffer.append("责任部门");
									} else {
										buffer.append(",责任部门");
									}
									errCount++;
								}
								//校验预计清理时间是否为空
								if( StringUtils.isBlank(clearTime) ) {
									errHave=true;
									if(errCount==0) {
										buffer.append("账套" + accountsId + "锁数失败，科目" + subjectCode + ":");
										buffer.append("第"+(i+1)+"条:");
										buffer.append("预计清理时间为空");
									} else {
										buffer.append(",预计清理时间为空");
									}
									errCount++;
								}
								if(errHave) {
									buffer.append("\n");
									errBack++;
								}
								//校验未来成因是否为空
								if( StringUtils.isBlank(reseon)) {
									if(errCount==0) {
										buffer2.append("账套" + accountsId + "锁数失败，科目" + subjectCode + ":");
										buffer2.append("第"+(i+1)+"条:");
										buffer2.append("所有未核销（没有匹配号）行的往来成因均填写");
									} else {
										buffer2.append(",所有未核销（没有匹配号）行的往来成因均填写");
									}
									buffer2.append("\n");
									errBack++;
								}
								if(errBack >10 ) { //超过10条不予显示,调出校验
									buffer2.append(".....");
									buffer2.append("\n");
									break;
								}
							}
						}
						//type3: 余额校验
						//获取往来汇总数据信息
						QueryWrapper<DwCpClearUpTotal> query2 = new QueryWrapper<>();
						query2.eq("ACCOUNTS_ID", accountsId);

						if (StringUtils.isNotBlank(subjectCode)) {
							query2.eq("SUBJECT_CODE", subjectCode);
						}
						query2.eq("QUARTER", quarter);
						DwCpClearUpTotal total = iDwCpClearUpTotalService.getOne(query2);
						if (total == null) {
							total= new DwCpClearUpTotal();
						}
						//取得汇总表NC余额信息
						ncBalance = total.getNcBalance();
						//计算明细表NC月汇总信息
						StringBuilder sql = new StringBuilder(); //sum出科目明细总额
						sql.append("select sum(t.amount) from ");
						sql.append("DW_CP_CLEAR_UP_DETAIL t  where ");
						sql.append("t.ACCOUNTS_ID='");
						sql.append(accountsId);
						sql.append("' and t.QUARTER='");
						sql.append(quarter);
						sql.append("' and t.SUBJECT_CODE='");
						sql.append(subjectCode);
						sql.append("'");
						//明细表余额信息
						balance = dao.selectBigDecimalOne(sql.toString());
						if(balance == null) {
							balance = new BigDecimal("0");
						}
						if(ncBalance == null) {
							ncBalance = new BigDecimal("0");
						}
						logger.info("科目明细总额:{}" , balance);
						logger.info("nc余额:{}" , ncBalance);

						//TODO  代码需调整 保存摘出来合并，放到for外
						//校验汇总余额与明细余额是否相等
						if (balance.compareTo(ncBalance) != 0) {
							if (buffer.toString().length() <= 0 && buffer2.toString().length() <= 0) {
								buffer3.append("账套" + accountsId + "锁数失败，科目" + subjectCode + ":");
							}
							buffer3.append("科目明细总额不等于nc余额,科目明细总额：" + balance + ",nc余额：" + ncBalance);
							buffer3.append("\n");
							check.setComments("false");
							check.setCheckType("3");
							check.setCheckResult("科目明细总额不等于nc余额,科目明细总额：" + balance + ",nc余额：" + ncBalance);
							dwCpDataCheckService.save(check);
							err.add(buffer3.toString());
						} else {
							check.setComments("true");
							check.setCheckType("3");
							check.setCheckResult("");
							dwCpDataCheckService.save(check);
						}

						//保存相关校验信息
						//保存四项校验
						if(buffer.toString().length() > 0) {
							err.add(buffer.toString());
							check.setComments("false");
							check.setCheckType("1");
							check.setCheckResult(buffer.toString());
							dwCpDataCheckService.save(check);
						} else {
							check.setComments("true");
							check.setCheckType("1");
							check.setCheckResult("");
							dwCpDataCheckService.save(check);
						}
						//保存往来成因校验信息
						if( buffer2.toString().length() > 0) {
							err.add(buffer2.toString());
							check.setComments("false");
							check.setCheckType("2");
							check.setCheckResult("所有未核销（没有匹配号）行的往来成因均填写");
							dwCpDataCheckService.save(check);
						} else {
							check.setComments("true");
							check.setCheckType("2");
							check.setCheckResult("");
							dwCpDataCheckService.save(check);
						}
					} else { //如果没有往来明细数据 - 只校验余额是否相等
						StringBuilder sql = new StringBuilder(); //sum出科目明细总额
						sql.append("select sum(t.amount) from ");
						sql.append("DW_CP_CLEAR_UP_DETAIL t  where ");
						sql.append("t.ACCOUNTS_ID='");
						sql.append(accountsId);
						sql.append("' and t.QUARTER='");
						sql.append(quarter);
						sql.append("' and t.SUBJECT_CODE='");
						sql.append(subjectCode);
						sql.append("'");
						//科目明细余额总额
						balance = dao.selectBigDecimalOne(sql.toString());
						if(balance == null) {
							balance = new BigDecimal("0");
						}
						//校验余额
						if(balance.compareTo(new BigDecimal("0"))!=0) {
							buffer3.append("账套" + accountsId + "锁数失败，科目" + subjectCode + ":");
							json.put(RESULT, "账套" + accountsId + "锁数失败，科目" + subjectCode + ":" + "科目明细总额不等于nc余额,科目明细总额：" + balance + ",nc余额：" + 0 + "\n");
							json.put(STATUS, "0");
							check.setComments("false");
							check.setCheckType("3");
							check.setCheckResult("科目明细总额不等于nc余额,科目明细总额：" + balance + ",nc余额：" + 0);
							dwCpDataCheckService.save(check);
							return json;
						}else {
							check.setComments("true");
							check.setCheckType("3");
							dwCpDataCheckService.save(check);

							check.setComments("true");
							check.setCheckType("2");
							dwCpDataCheckService.save(check);

							check.setComments("true");
							check.setCheckType("1");
							dwCpDataCheckService.save(check);
						}
					}
					//如果校验出错，则返回错误信息
					//TODO 后面需要优化下提示多个账套校验的错误信息
					if (err.size() > 0) {
						json.put(RESULT, err.toString());
						json.put(STATUS, "0");
						return json;
					}
					//更新往来清理汇总锁数状态
					DwCpClearUpTotal total = new DwCpClearUpTotal();
					total.setLockStatus("1");
					UpdateWrapper<DwCpClearUpTotal> updataWapper = new UpdateWrapper<>();
					updataWapper.eq("ACCOUNTS_ID", accountsId);
					if (StringUtils.isNotBlank(subjectCode)) {
						updataWapper.eq("SUBJECT_CODE", subjectCode);
					}
					updataWapper.eq("QUARTER", quarter);
					//更新数据
					boolean update = iDwCpClearUpTotalService.update(total, updataWapper);
					if (update) {
						json.put(RESULT, "锁数成功！");
						json.put(STATUS, "200");
						return json;
					} else {
						json.put(RESULT,"账套" + accountsId + "锁数失败\n" );
						json.put(STATUS, "0");
						return json;
					}
				}
			}
		} catch (Exception e) {
			logger.error("numberOfLocksMx()方法异常{}",e.toString());
			json.put(RESULT, "锁数异常");
			json.put(STATUS, "-2");
		}
		return json;
	}

	@Override
	public Message<Object> takeDataBlendingFlag(String selectType, String blendingType, String id, String userCode,
			String selectFlag, String month, String accountsId, String subjectCode) {
		
		logger.info("takeDataBlendingFlag==selectType:{}",selectType);
		logger.info("takeDataBlendingFlag==blendingType:{}",blendingType);
		logger.info("takeDataBlendingFlag==id:{}",id);
		logger.info("takeDataBlendingFlag==userCode:{}",userCode);
		logger.info("takeDataBlendingFlag==selectFlag:{}",selectFlag);
		logger.info("takeDataBlendingFlag==month:{}",month);
		logger.info("takeDataBlendingFlag==accountsId:{}",accountsId);
		logger.info("takeDataBlendingFlag==subjectCode:{}",subjectCode);
		QueryWrapper<DwCpClearUpDetail> wrapper = new QueryWrapper<>();

		DwCpClearUpDetail detail = new DwCpClearUpDetail();
		try {
			
			if (StringUtils.isBlank(month) || StringUtils.isBlank(accountsId) || StringUtils.isBlank(subjectCode)) {

				return new Message<>("-1", "月份或账套参数为空，不可进行全选选择操作", null);

			}
			if ("全选".equals(selectType) && "未勾兑".equals(blendingType)) {
				// 执行未达数据全选添加flag
				wrapper.eq("ACCOUNTS_ID", accountsId);
				wrapper.eq("QUARTER", month);
				wrapper.eq("SUBJECT_CODE", subjectCode);
				wrapper.isNull("matchingcode");
				if ("选择".equals(selectFlag)) {// 更新当前所选账套所选月份下对应的未达数据flag为用户名
					detail.setGdFlag(userCode);
					boolean update = update(detail, wrapper);
					if (!update) {
						return new Message<>("-2", "网络异常", null);
					}
					return new Message<>("200", accountsId + month + subjectCode + "未达数据选择成功！", null);

				} else if ("取消选择".equals(selectFlag)) {// 更新当前所选账套所选月份下对应的未达数据flag为空

					detail.setGdFlag("");
					boolean update = update(detail, wrapper);
					if (!update) {
						
						return new Message<>("-2", "网络异常", null);
						
					}
					return new Message<>("200", accountsId + month + subjectCode + "未达数据取消选择成功！", null);

				} else {

					return new Message<>("-1", "选择类型不合法！", null);

				}

			} else if ("单选".equals(selectType) && "已勾兑".equals(blendingType)) {

				// 执行未达数据单选添加flag
				if (StringUtils.isBlank(id)) {

					return new Message<>("-1", "单选缺失数据id！", null);
				} else {

					if ("选择".equals(selectFlag)) {

						detail.setGdFlag(userCode);
						wrapper.eq("matchingcode", id);

						// 更新当前所选uuid（匹配号）对应的未达数据flag为用户名，此处的uuid使用的是传递参数id
						boolean update = update(detail, wrapper);
						if (!update) {
							return new Message<>("-2", "网络异常", null);
						}
						return new Message<>("200", accountsId + month + "已达数据单选成功", null);
					} else if ("取消选择".equals(selectFlag)) {

						// 更新当前所选uuid（匹配号）对应的未达数据flag为空
						detail.setGdFlag(null);
						wrapper.eq("matchingcode", id);
						boolean update = update(detail, wrapper);
						if (!update) {
							return new Message<>("-2", "网络异常！", null);
						}
						return new Message<>("200", accountsId + month + "已达数据取消单选成功", null);
					} else {
						return new Message<>("-1", "选择类型不合法", null);
					}
				}

			}else if("单选".equals(selectType) && "未勾兑".equals(blendingType)) {
				
				if (StringUtils.isBlank(id)) {

					return new Message<>("-1", "单选缺失数据id！", null);
				} else {

					if ("选择".equals(selectFlag)) {

						detail.setGdFlag(userCode);
						wrapper.eq("id", id);
						wrapper.isNull("matchingcode");
						// 更新当前所选uuid（匹配号）对应的未达数据flag为用户名，此处的uuid使用的是传递参数id
						boolean update = update(detail, wrapper);
						if (!update) {
							
							return new Message<>("-2", "网络异常！", null);
						}
						return new Message<>("200", accountsId + month + "已达数据单选成功", null);
					}
					else {
						return new Message<>("-1", "选择类型不合法", null);
					}
				}
			}
		} catch (Exception e) {

			logger.error("takeDataBlendingFlag()方法异常{}",e.toString());
			return new Message<>("-2", "往来清理勾兑异常", null);
		}
		return new Message<>("-1", "往来清理勾兑参数异常", null);
	}

	@Override
	public Message<Object> takeBlendingUpdate(String userCode, String operateFlag, String month, String accountsId,
			String subjectCode) {
		
		logger.info("takeBlendingUpdate==userCode:{}",userCode);
		logger.info("takeBlendingUpdate==operateFlag:{}",operateFlag);
		logger.info("takeBlendingUpdate==month:{}",month);
		logger.info("takeBlendingUpdate==accountsId:{}",accountsId);
		logger.info("takeBlendingUpdate==subjectCode:{}",subjectCode);
		
		QueryWrapper<DwCpClearUpDetail> wrapper = new QueryWrapper<>();
		
		try {
			if(StringUtils.isBlank(month) || StringUtils.isBlank(accountsId) || StringUtils.isBlank(subjectCode)) {
				
				return new Message<>("-1", "月份或账套参数为空，不可进行全选选择操作", null);
			}else if("勾兑".equals(operateFlag) ){
				
				//执行勾兑操作，与之前的勾兑操作校验及后续操作相同，区别在于使用数据库中uuid为空的（未达数据）且flag不为空的进行勾兑操作
				wrapper.eq("ACCOUNTS_ID", accountsId);
				wrapper.eq("QUARTER", month);
				wrapper.eq("SUBJECT_CODE", subjectCode);
				wrapper.isNotNull("GD_FLAG");
				wrapper.isNull("matchingcode");
				List<DwCpClearUpDetail> selectList = mapper.selectList(wrapper);
				
				List<Long> list = new ArrayList<>();
				for (int i = 0; i < selectList.size(); i++) {
					list.add(selectList.get(i).getId());
				}
				if(list.size() > 0) {
					
					JSONObject json = updataClearDetailStatus(null, list);
					
					return new Message<>(json.getString(STATUS), json.getString(RESULT), null);
				}else {
					
					return new Message<>("200", "没有数据，请重试！", null);
				}
				
			}else if("取消勾兑".equals(operateFlag)) {
				
				//执行取消勾兑的操作，区别在于使用数据库中uuid不为空且flag不为空的数据执行操作，同上不做赘述。
				//执行操作将对应的勾兑操作的数据flag清除掉
				wrapper.eq("ACCOUNTS_ID", accountsId);
				wrapper.eq("QUARTER", month);
				wrapper.eq("SUBJECT_CODE", subjectCode);
				wrapper.isNotNull("matchingcode");
				wrapper.isNotNull("GD_FLAG");
				
				List<DwCpClearUpDetail> selectList = mapper.selectList(wrapper);
				List<String> list = new ArrayList<>();
				for (int i = 0; i < selectList.size(); i++) {
					list.add(selectList.get(i).getMatchingcode());
				}
				if(list.size() > 0) {
					
					JSONObject json = notClearDetailStatus(null, list);
					
					return new Message<>(json.getString(STATUS), json.getString(RESULT), null);
				}else {
					
					return new Message<>("200", "没有数据，请重试！", null);
				}
				
			}else {
				
				return new Message<>("-1", "参数类型不合法", null);
			}
		} catch (Exception e) {
			
			logger.error("takeBlendingUpdate()方法异常{}",e.toString());
			return new Message<>("-2", "网络异常", null);
		}

	}

	@Override
	public Message<Object> clearBlendInit(String userCode, String operateFlag, String month, String accountsId,
			String subjectCode) {

		logger.info("clearBlendInit==userCode:{}",userCode);
		logger.info("clearBlendInit==operateFlag:{}",operateFlag);
		logger.info("clearBlendInit==month:{}",month);
		logger.info("clearBlendInit==accountsId:{}",accountsId);
		logger.info("clearBlendInit==subjectCode:{}",subjectCode);
		
		QueryWrapper<DwCpClearUpDetail> wrapper = new QueryWrapper<>();
		DwCpClearUpDetail detail = new DwCpClearUpDetail();
		if(StringUtils.isBlank(month) || StringUtils.isBlank(accountsId) || StringUtils.isBlank(subjectCode)) {
			
			return new Message<>("-1", "月份或账套参数为空，不可进行全选选择操作", null);
		/**}else if("全选".equals(operateFlag)) {*/
			
		}else {
			
			//执行操作将对应的勾兑操作的数据flag清除掉
			detail.setGdFlag(" ");
			wrapper.eq("ACCOUNTS_ID", accountsId);
			wrapper.eq("QUARTER", month);
			wrapper.eq("SUBJECT_CODE", subjectCode);
			wrapper.isNull("matchingcode");
			
			boolean update = update(detail, wrapper);
			if(!update) {
				
				return new Message<>("-2", "初始化网络异常", null);
			}
			return new Message<>("200", "更新成功", null);
		}
	}

	@Override
	public Integer deleteByWrapper(QueryWrapper<DwCpClearUpDetail> querywrapper) {
		
		return mapper.delete(querywrapper);
	}

	@Override
	public JSONObject takeBlendingHigh(String quarter, String accountsId, String subjectCode,String userCode) {
		JSONObject json = new JSONObject();
		logger.info("往来清理勾兑入参quarter：{}" , quarter);
		logger.info("往来清理勾兑入参accountsId：{}" , accountsId);
		logger.info("往来清理勾兑入参subjectCode：{}" , subjectCode);
		try {
			//判断入参明细记录主键列表是否为空 - ids和array都是主键列表，2者选择其一就行
			if (StringUtils.isBlank(quarter)||StringUtils.isBlank(accountsId)||StringUtils.isBlank(subjectCode)
					||StringUtils.isBlank(userCode)) {
				json.put(STATUS, "-1");
				json.put(RESULT, "参数不合法");
				return json;
			}
			BigDecimal amount = new BigDecimal(0);
			//运用HashSet的去重原理保存客商名称，用来判断是否存在多个客商
			Set<String> set = new HashSet<>();

			//根据入参获取明细记录列表
			QueryWrapper<DwCpClearUpDetail> queryWrapper = new QueryWrapper<>();

			queryWrapper.eq("QUARTER",quarter);
			queryWrapper.eq("ACCOUNTS_ID",accountsId);
			queryWrapper.eq("SUBJECT_CODE",subjectCode);
			queryWrapper.eq("GD_FLAG",userCode);
			/*if (StringUtils.isBlank(ids)) { // 如果ids为空 代表是新接口传来的数据
				queryWrapper.in("id", array);
			} else {
				queryWrapper.in("id", StringUtil.getNumberListId(ids));
			}*/
			List<DwCpClearUpDetail> list = getListBeanByWrapper(queryWrapper);
			String string = list.toString();
			logger.info(" 往来清理-往来明细类：{}" , string);
			for (int i = 0; i < list.size(); i++) {
				DwCpClearUpDetail clear = list.get(i);
				amount = amount.add(clear.getAmount()); //所有记录的金额汇总应该为0
				set.add(StringUtil.replaceBlank(clear.getKsname())); //根据客商名称勾兑-同一个客商才可以勾兑
			}
			if (amount.compareTo(new BigDecimal(0)) != 0) {
				json.put(STATUS, "0");
				json.put(RESULT, "勾选金额明细不为0！");
				return json;
			}
			if (set.size() > 1) {
				json.put(STATUS, "0");
				json.put(RESULT, "勾兑数据客商不一致！");
				return json;
			}
			DwCpClearUpDetail detail = new DwCpClearUpDetail();
			String uuid = StringUtil.getUUID();
			detail.setMatchingcode(uuid);
			detail.setGdFlag("");
			if (update(detail, queryWrapper)) {
				json.put(RESULT, "插入匹配成功");
				json.put(STATUS, "200");
				return json;
			} else {
				json.put(RESULT, "插入匹配号异常");
				json.put(STATUS, "0");
				return json;
			}
		} catch (Exception e) {
			logger.error(" 往来清理-勾选异常：{}" , e.toString());
			json.put(STATUS, "-2");
			json.put(RESULT, "勾兑异常");
		}
		return null;
	}


	@Override
	public JSONObject notTakeBlendingHigh(String quarter, String accountsId, String subjectCode,String userCode) {
		JSONObject json = new JSONObject();
		logger.info("往来清理取消勾兑入参quarter：{}" , quarter);
		logger.info("往来清理取消勾兑入参accountsId：{}" , accountsId);
		logger.info("往来清理取消勾兑入参subjectCode：{}" , subjectCode);
		try {
			if (StringUtils.isBlank(quarter)||StringUtils.isBlank(accountsId)||StringUtils.isBlank(subjectCode)
					||StringUtils.isBlank(userCode)) {
				json.put(STATUS, "-1");
				json.put(RESULT, "非法参数");
				return json;
			}

			QueryWrapper<DwCpClearUpDetail> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("SUBJECT_CODE", subjectCode);
			queryWrapper.eq("ACCOUNTS_ID", accountsId);
			queryWrapper.eq("QUARTER", quarter);
			queryWrapper.eq("GD_FLAG",userCode);
			List<DwCpClearUpDetail> list = getListBeanByWrapper(queryWrapper);
			logger.info(" 往来清理-往来明细类：{}" , list.toString());
			for (int i = 0; i < list.size(); i++) {
				QueryWrapper<DwCpClearUpTotal> wrapper = new QueryWrapper<>();
				wrapper.eq("SUBJECT_CODE", subjectCode);
				wrapper.eq("ACCOUNTS_ID", accountsId);
				wrapper.eq("QUARTER", quarter);
				DwCpClearUpTotal one = iDwCpClearUpTotalService.getOne(wrapper);
				if ("1".equals(one.getLockStatus())) {
					json.put(STATUS, "0");
					json.put(RESULT, "已锁数，取消勾兑失败");
					return json;
				}
			}

			DwCpClearUpDetail detail = new DwCpClearUpDetail();
			detail.setMatchingcode("");
			detail.setGdFlag("");
			if (update(detail, queryWrapper)) {
				json.put(RESULT, "取消勾兑成功！");
				json.put(STATUS, "200");
			} else {
				json.put(RESULT, "取消勾兑失败");
				json.put(STATUS, "0");
			}

		} catch (Exception e) {
			logger.error(" 往来清理-勾选异常：{}" , e.toString());
			json.put(STATUS, "-2");
			json.put(RESULT, "勾兑异常");
		}
		return json;
	}

	@Override
	public JSONObject updSendMsgFlagCond(String quarter, String accountsId, String subjectCode,String userCode) {
		JSONObject json = new JSONObject();
		logger.info("往来清理龙信消息推送参数quarter：{}" , quarter);
		logger.info("往来清理龙信消息推送参数accountsId：{}" , accountsId);
		logger.info("往来清理龙信消息推送参数subjectCode：{}" , subjectCode);
		logger.info("往来清理龙信消息推送参数userCode：{}" , userCode);
		try {
			//判断入参明细记录主键列表是否为空 - ids和array都是主键列表，2者选择其一就行
			if (StringUtils.isBlank(quarter)||StringUtils.isBlank(accountsId)||StringUtils.isBlank(subjectCode)
					||StringUtils.isBlank(userCode)) {
				json.put(STATUS, "-1");
				json.put(RESULT, "参数不合法");
				return json;
			}
			BigDecimal amount = new BigDecimal(0);
			//运用HashSet的去重原理保存客商名称，用来判断是否存在多个客商
			Set<String> set = new HashSet<>();

			//根据入参获取明细记录列表
			QueryWrapper<DwCpClearUpDetail> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("SUBJECT_CODE", subjectCode);
			queryWrapper.eq("ACCOUNTS_ID", accountsId);
			queryWrapper.eq("QUARTER", quarter);
			queryWrapper.eq("GD_FLAG",userCode);
			queryWrapper.eq("GX_FLAG", "0");
			//获取往来清理明细记录
			List<DwCpClearUpDetail> list = getListBeanByWrapper(queryWrapper);
			if (list == null) {
				list = new ArrayList<DwCpClearUpDetail>();
			}
			//String string = list.toString();
			logger.info(" 往来清理龙信推送记录数量：{}" +  list.size());

			List<DwCpClearUpDetail> updList = new ArrayList<DwCpClearUpDetail>();
			int batchSize = 0;
			DwCpClearUpDetail updRecord = null;
			for (int i = 0; i < list.size(); i++) {
				batchSize++;
				updRecord = list.get(i);
				updRecord.setGxFlag("1");
				updRecord.setSendConfirmDate(new Date());
				updList.add(updRecord);
				if (batchSize/200 == 0) {
					this.updateBatchById(updList, updList.size());
					updList.clear();
				}
			}
			if (0 < updList.size()) {
				this.updateBatchById(updList, updList.size());
			}
		} catch (Exception e) {
			logger.error(" 往来清理-发送确认异常：{}" + e.toString());
			json.put(STATUS, "-2");
			json.put(RESULT, "发送确认异常");
		}
		json.put(RESULT, "发送确认成功");
		json.put(STATUS, "200");
		return json;
	}
}
