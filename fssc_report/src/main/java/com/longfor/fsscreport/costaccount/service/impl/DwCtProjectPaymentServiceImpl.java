package com.longfor.fsscreport.costaccount.service.impl;

import com.longfor.fsscreport.costaccount.entity.DwCtProjectPayment;
import com.longfor.fsscreport.costaccount.mapper.DwCtProjectPaymentMapper;
import com.longfor.fsscreport.costaccount.service.IDwCtProjectPaymentService;
import com.longfor.fsscreport.reconciliation.entity.StoredProcedure;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 成本台账项目付款单明细表 服务实现类
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-07
 */
@Service
public class DwCtProjectPaymentServiceImpl extends ServiceImpl<DwCtProjectPaymentMapper, DwCtProjectPayment> implements IDwCtProjectPaymentService {

	@Autowired
	private DwCtProjectPaymentMapper mapper;
	
	@Override
	public void updataCbtz(StoredProcedure sp) throws Exception {
		mapper.updataCbtz(sp);
	}
}
