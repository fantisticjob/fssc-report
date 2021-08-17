package com.longfor.fsscreport.approval.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longfor.fsscreport.approval.entity.DwZdsyOaAccounts;
import com.longfor.fsscreport.approval.mapper.DwZdsyOaAccountsMapper;
import com.longfor.fsscreport.approval.service.IDwZdsyOaAccountsService;

/**
 * <p>
 * 重点税源审批公司与财务经理映射信息表 服务实现类
 * </p>
 *
 * @author chenziyao
 * @since 2021-05-18
 */
@Service
public class DwZdsyOaAccountsServiceImpl extends ServiceImpl<DwZdsyOaAccountsMapper, DwZdsyOaAccounts> implements IDwZdsyOaAccountsService {

	
	@Override
	public String getBean(String accountsId){
		
    	QueryWrapper<DwZdsyOaAccounts> wrapper3 = new QueryWrapper<>();
    	wrapper3.eq("ACCOUNTS_ID", accountsId);
    	DwZdsyOaAccounts one = this.getOne(wrapper3);
    	
    	String oaName ="";
    	
    	if( one!=null ) {
    		oaName = one.getOaName();
    	}
    	
		return oaName;
		
	}
}
