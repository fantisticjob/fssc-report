package com.longfor.fsscreport.costaccount.service;

import com.longfor.fsscreport.costaccount.entity.DwCtProjectPayment;
import com.longfor.fsscreport.reconciliation.entity.StoredProcedure;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 成本台账项目付款单明细表 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-07
 */
public interface IDwCtProjectPaymentService extends IService<DwCtProjectPayment> {
	void updataCbtz(StoredProcedure sp) throws Exception;
}
