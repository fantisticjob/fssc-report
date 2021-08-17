package com.longfor.fsscreport.taxsources.service;

import com.longfor.fsscreport.taxsources.entity.DwZdsyMonthlyTaxReport;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 重点税源企业税收信息（月报）表_详细 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-07-29
 */
public interface IDwZdsyMonthlyTaxReportService extends IService<DwZdsyMonthlyTaxReport> {

	List<DwZdsyMonthlyTaxReport> getDwZdsyMonthlyTaxReportList(QueryWrapper<DwZdsyMonthlyTaxReport> wrapper);



}
