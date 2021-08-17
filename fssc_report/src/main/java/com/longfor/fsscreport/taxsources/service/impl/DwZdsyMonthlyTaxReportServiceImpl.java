package com.longfor.fsscreport.taxsources.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longfor.fsscreport.taxsources.entity.DwZdsyMonthlyTaxReport;
import com.longfor.fsscreport.taxsources.mapper.DwZdsyMonthlyTaxReportMapper;
import com.longfor.fsscreport.taxsources.service.IDwZdsyMonthlyTaxReportService;

/**
 * <p>
 * 重点税源企业税收信息（月报）表_详细 服务实现类
 * </p>
 *
 * @author chenziyao
 * @since 2020-07-29
 */
@Service
public class DwZdsyMonthlyTaxReportServiceImpl extends ServiceImpl<DwZdsyMonthlyTaxReportMapper, DwZdsyMonthlyTaxReport> implements IDwZdsyMonthlyTaxReportService {

	@Autowired
	private DwZdsyMonthlyTaxReportMapper mapper;

	@Override
	public List<DwZdsyMonthlyTaxReport> getDwZdsyMonthlyTaxReportList(QueryWrapper<DwZdsyMonthlyTaxReport> wrapper) {
		// TODO Auto-generated method stub
		return mapper.selectList(wrapper);
	}
}
