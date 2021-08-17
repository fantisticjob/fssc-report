package com.longfor.fsscreport.approval.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longfor.fsscreport.approval.entity.DwCpOaAccounts;
import com.longfor.fsscreport.approval.mapper.DwCpOaAccountsMapper;
import com.longfor.fsscreport.approval.service.IDwCpOaAccountsService;

/**
 * <p>
 * 审批提交OA账号 服务实现类
 * </p>
 *
 * @author chenziyao
 * @since 2020-11-12
 */
@Service
public class DwCpOaAccountsServiceImpl extends ServiceImpl<DwCpOaAccountsMapper, DwCpOaAccounts> implements IDwCpOaAccountsService {
	
	
	@Autowired
	private DwCpOaAccountsMapper mapper;
	


	@Override
	public List<DwCpOaAccounts> selectList(QueryWrapper<DwCpOaAccounts> qwrapper) {

		return mapper.selectList(qwrapper);
	}}
