package com.longfor.fsscreport.clear.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.longfor.fsscreport.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longfor.fsscreport.clear.entity.DwCpClearUpBlance;
import com.longfor.fsscreport.clear.entity.DwCpClearUpTotal;
import com.longfor.fsscreport.clear.entity.DwCpDataCheck;
import com.longfor.fsscreport.clear.mapper.DwCpClearUpBlanceMapper;
import com.longfor.fsscreport.clear.service.IDwCpClearUpBlanceService;
import com.longfor.fsscreport.clear.service.IDwCpClearUpTotalService;
import com.longfor.fsscreport.clear.service.IDwCpDataCheckService;

/**
 * <p>
 * 往来清理-往来余额类 服务实现类
 * </p>
 *
 * @author chenziyao
 * @since 2020-10-30
 */
@Service
public class DwCpClearUpBlanceServiceImpl extends ServiceImpl<DwCpClearUpBlanceMapper, DwCpClearUpBlance>
		implements IDwCpClearUpBlanceService {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	private static final  String RESULT = "result";
	private static final  String STATUS = "status";

	@Autowired
	private IDwCpClearUpTotalService iDwCpClearUpTotalService;

	@Autowired
	private IDwCpDataCheckService dwCpDataCheckService;

	@Autowired
	private DwCpClearUpBlanceMapper mapper;

	@Override
	public List<DwCpClearUpBlance> getListBeanByWrapper(QueryWrapper<DwCpClearUpBlance> queryWrapper) {
		return mapper.selectList(queryWrapper);
	}

	@Override
	public JSONObject numberOfLocksYe(String accountsIdStr,String subjectCode,String quarter){
		
		JSONObject json = new JSONObject();
		logger.info("往来清理校验锁数-余额类科目：accountsId:{} ",accountsIdStr);
		logger.info("往来清理校验锁数-余额类科目：subjectCode:{} ",subjectCode);
		logger.info("往来清理校验锁数-余额类科目：quarter:{} ",quarter);
		
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
					logger.info("往来清理校验锁数-余额类科目-循环单账套: 账套ID{}"  , accountsId);
					/**
					 * 校验表先删除
					 */
					QueryWrapper<DwCpDataCheck> queryWrapper = new QueryWrapper<>();
					queryWrapper.eq("ACCOUNTS_ID", accountsId);
					queryWrapper.eq("QUARTER", quarter);
					queryWrapper.eq("SUBJECT_CODE", subjectCode);
					Integer deleteByParm = dwCpDataCheckService.deleteByParm(queryWrapper);
					if(deleteByParm==0) {
						logger.error("校验数据删除失败");
					}

					/**
					 * 保存校验表数据
					 */
					DwCpDataCheck check = new DwCpDataCheck();
					check.setAccountsId(accountsId);
					check.setQuarter(quarter);
					check.setSubjectCode(subjectCode);
					//获取往来清余额明细数据
					QueryWrapper<DwCpClearUpBlance> query = new  QueryWrapper<>();
					query.eq("ACCOUNTS_ID", accountsId);
					query.eq("SUBJECT_CODE", subjectCode);
					query.eq("QUARTER", quarter);
					query.eq("csh_flag", "0");
					query.ne("amount", "0");
					List<DwCpClearUpBlance> list = getListBeanByWrapper(query);

					//校验相关变量初始化
					BigDecimal balance = new BigDecimal(0);
					List<String> err = new ArrayList<>();
					StringBuilder buffer = new StringBuilder();  //四项校验
					StringBuilder buffer2 = new StringBuilder(); //往来成因校验
					StringBuilder buffer3 = new StringBuilder(); //余额校验
					boolean errHave=false;
					Integer errBack=0;
					//如果存余额明细数据，则循环校验
					if (list != null && 0 < list.size()) {
						for (int i = 0; i < list.size(); i++) {
							int errCount = 0;
							//①该科目辅助余额加总合计，同NC最新余额匹配；
							//②所有未核销行的往来成因均填写，
							//③自动带出的是否异常为“是”的，必填项（平台的备注、地区的备注、预计清理时间）已填。
							//所有未核销行的均需填写完整。（平台填写人、预计清理时间、责任人，责任部门）已填，异常时备注必填
							DwCpClearUpBlance bance = list.get(i);
							String string = bance.toString();
							logger.info("余额详细数据为===={}",string);

							String isUnGeneral = bance.getIsUnGeneral(); //是否异常
							String reseon = bance.getReseon();  //往来成因
							String responUser = bance.getResponUser();//责任人
							String responDept = bance.getResponDept();//责任部门
							String reMarkDq = bance.getReMark(); //备注地区
							String ptUser = bance.getPtUser();//平台填写人
							String clearTime = bance.getClearTime();//清理时间
							balance = balance.add(bance.getAmount());
							//type1:四项校验
							//校验平台填写人是否为空
							if(StringUtils.isBlank(ptUser)) {
								errHave=true;
								if(errCount==0) {
									buffer.append("账套" + accountsId + "锁数失败，科目" + subjectCode + ":");
									buffer.append("第"+(i+1)+"条:");
									buffer.append("平台填写人为空");
								} else {
									buffer.append(",平台填写人为空");
								}
								errCount++;
							}
                            //校验责任人是否为空
							if( StringUtils.isBlank(responUser) ) {
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
                            //校验责任部门是否为空
							if( StringUtils.isBlank(responDept) ) {
								errHave=true;
								if(errCount==0) {
									buffer.append("账套" + accountsId + "锁数失败，科目" + subjectCode + ":");
									buffer.append("第"+(i+1)+"条:");
									buffer.append("责任部门为空");
								} else {
									buffer.append(",责任部门为空");
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
							//校验是否异常项是否为空
							if(StringUtils.isBlank(isUnGeneral)){
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
							//如果是异常，检验备注是否为空
							if("异常".equals(isUnGeneral) ) {
								if( StringUtils.isBlank(reMarkDq) ) {
									errHave=true;
									if(errCount==0) {
										buffer.append("第"+(i+1)+"条:");
										buffer.append("备注为空");
									} else {
										buffer.append(",备注为空");
									}
									errCount++;
								}
							}

							if(errHave) {
								buffer.append("\n");
								errBack++;
							}
							//type2:校验往来成因是否为空
							if( StringUtils.isBlank(reseon)) {
								if(errCount==0) {
									buffer2.append("账套" + accountsId + "锁数失败，科目" + subjectCode + ":");
									buffer2.append("第"+(i+1)+"条:");
									buffer2.append("所有未核销行的往来成因均填写");
								} else {
									buffer2.append(",所有未核销行的往来成因均填写");
								}
								buffer2.append("\n");
								errBack++;
							}
							if(errBack>10) { //超过10条不予显示
								buffer2.append(".....");
								buffer2.append("\n");
								break;
							}
						}
						//保存校验结果 - 4项校验
						if(buffer.toString().length()>0) {
							err.add(buffer.toString());
							check.setCheckType("1");
							check.setComments("false");
							check.setCheckResult(buffer.toString());
							dwCpDataCheckService.save(check);
						}else {
							check.setComments("true");
							check.setCheckType("1");
							check.setCheckResult("");
							dwCpDataCheckService.save(check);
						}
						//保存校验结果 - 往来成因
						if( buffer2.toString().length() > 0) {
							err.add(buffer2.toString());
							check.setCheckType("2");
							check.setComments("false");
							check.setCheckResult("所有未核销行的往来成因均填写");
							dwCpDataCheckService.save(check);
						}else {
							check.setComments("true");
							check.setCheckType("2");
							check.setCheckResult("");
							dwCpDataCheckService.save(check);
						}
					}
					//type3：余额校验
					QueryWrapper<DwCpClearUpTotal> query2 = new  QueryWrapper<>();
					query2.eq("ACCOUNTS_ID", accountsId);
					query2.eq("SUBJECT_CODE", subjectCode);
					query2.eq("QUARTER", quarter);
					DwCpClearUpTotal total = iDwCpClearUpTotalService.getOne(query2);
					if(total==null) {
						total=new DwCpClearUpTotal();
					}
					BigDecimal ncBalance = total.getNcBalance();//得到nc数据
					if (ncBalance == null) {
						ncBalance = new BigDecimal("0");
					}

					logger.info("科目明细总额:{}",balance);
					logger.info("nc余额:{}",ncBalance);
					//TODO  代码需调整 保存摘出来合并，放到for外
					if(balance.compareTo(ncBalance) != 0) {
						if (buffer.toString().length() <= 0 && buffer2.toString().length() <= 0) {
							buffer3.append("账套" + accountsId + "锁数失败，科目" + subjectCode + ":");
						}
						buffer3.append("科目明细总额不等于nc余额,科目明细总额：" + balance + ",nc余额：" + ncBalance);
						buffer3.append("\n");
						check.setCheckType("3");
						check.setComments("false");
						check.setCheckResult("科目明细总额不等于nc余额,科目明细总额：" + balance + ",nc余额：" + ncBalance);
						dwCpDataCheckService.save(check);
						err.add(buffer3.toString());
					}else {
						check.setComments("true");
						check.setCheckType("3");
						check.setCheckResult("");
						dwCpDataCheckService.save(check);
					}
					//如果校验错误返回错误信息
					if(err.size() > 0 ) {
						json.put(RESULT, err.toString());
						json.put(STATUS, "0");
						return json;
					}
					total.setLockStatus("1");
					UpdateWrapper<DwCpClearUpTotal> updataWapper = new  UpdateWrapper<>();
					updataWapper.eq("ACCOUNTS_ID", accountsId);
					updataWapper.eq("SUBJECT_CODE", subjectCode);
					updataWapper.eq("QUARTER", quarter);
					boolean update = iDwCpClearUpTotalService.update(total, updataWapper);
					if(update) {
						json.put(RESULT, "锁数成功！");
						json.put(STATUS, "200");
						return json;
					}else {
						json.put(RESULT, "账套" + accountsId + "锁数失败！\n");
						json.put(STATUS, "0");
						return json;
					}
				}
			}
		} catch( Exception e) {
			logger.error("numberOfLocksYe()方法异常{}",e.toString());
			json.put(RESULT, "锁数异常");
			json.put(STATUS, "-2");
		}
		return json;
	}
}