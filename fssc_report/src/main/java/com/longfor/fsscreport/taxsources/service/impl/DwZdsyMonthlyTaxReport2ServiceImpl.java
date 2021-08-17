package com.longfor.fsscreport.taxsources.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longfor.fsscreport.taxsources.entity.DwZdsyMonthlyTaxReport2;
import com.longfor.fsscreport.taxsources.mapper.DwZdsyMonthlyTaxReport2Mapper;
import com.longfor.fsscreport.taxsources.service.IDwZdsyMonthlyTaxReport2Service;

/**
 * <p>
 * 财务信息（季报）表 服务实现类
 * </p>
 *
 * @author chenziyao
 * @since 2020-07-29
 */
@Service
public class DwZdsyMonthlyTaxReport2ServiceImpl extends ServiceImpl<DwZdsyMonthlyTaxReport2Mapper, DwZdsyMonthlyTaxReport2> implements IDwZdsyMonthlyTaxReport2Service {

	
	@Autowired
	private DwZdsyMonthlyTaxReport2Mapper mapper;

	@Override
	public List<DwZdsyMonthlyTaxReport2> getDwZdsyMonthlyTaxReport2List(
			QueryWrapper<DwZdsyMonthlyTaxReport2> wrapper2) {
		
		return mapper.selectList(wrapper2);
	}

}
