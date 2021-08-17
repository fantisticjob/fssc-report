package com.longfor.fsscreport.approval.service;

import com.longfor.fsscreport.approval.entity.DwCaListOfMonth;
import com.longfor.fsscreport.reconciliation.entity.StoredProcedure;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  月度关帐清单 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-07-11
 */
public interface IDwCaListOfMonthService extends IService<DwCaListOfMonth> {

	void updataDwCaListOfMonth(StoredProcedure sp);

}
