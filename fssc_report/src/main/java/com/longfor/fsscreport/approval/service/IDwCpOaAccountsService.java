package com.longfor.fsscreport.approval.service;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.longfor.fsscreport.approval.entity.DwCpOaAccounts;

/**
 * <p>
 * 审批提交OA账号 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-11-12
 */
public interface IDwCpOaAccountsService extends IService<DwCpOaAccounts> {

	List<DwCpOaAccounts> selectList(QueryWrapper<DwCpOaAccounts> qwrapper);

}
