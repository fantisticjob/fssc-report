package com.longfor.fsscreport.taxsources.service;

import com.longfor.fsscreport.taxsources.entity.DwZdsyMonthlyTaxReport2;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 财务信息（季报）表 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-07-29
 */
public interface IDwZdsyMonthlyTaxReport2Service extends IService<DwZdsyMonthlyTaxReport2> {

	List<DwZdsyMonthlyTaxReport2> getDwZdsyMonthlyTaxReport2List(QueryWrapper<DwZdsyMonthlyTaxReport2> wrapper2);

}
