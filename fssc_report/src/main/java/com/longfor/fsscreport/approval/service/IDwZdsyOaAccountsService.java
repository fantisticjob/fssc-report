package com.longfor.fsscreport.approval.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.longfor.fsscreport.approval.entity.DwZdsyOaAccounts;

/**
 * <p>
 * 重点税源审批公司与财务经理映射信息表 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2021-05-18
 */
public interface IDwZdsyOaAccountsService extends IService<DwZdsyOaAccounts> {


	String getBean(String accountsId);

	
}
