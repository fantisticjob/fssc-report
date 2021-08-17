package com.longfor.fsscreport.approval.service.impl;

import com.longfor.fsscreport.approval.entity.DwCaListOfMonth;
import com.longfor.fsscreport.approval.mapper.DwCaListOfMonthMapper;
import com.longfor.fsscreport.approval.service.IDwCaListOfMonthService;
import com.longfor.fsscreport.reconciliation.entity.StoredProcedure;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenziyao
 * @since 2020-07-11
 */
@Service
public class DwCaListOfMonthServiceImpl extends ServiceImpl<DwCaListOfMonthMapper, DwCaListOfMonth> implements IDwCaListOfMonthService {

	@Autowired
	private DwCaListOfMonthMapper mapper;
	@Override
	public void updataDwCaListOfMonth(StoredProcedure sp) {
		mapper.updataDwCaListOfMonth(sp);
	}

}
