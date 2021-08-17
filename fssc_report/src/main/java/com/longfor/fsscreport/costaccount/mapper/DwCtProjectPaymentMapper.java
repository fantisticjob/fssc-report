package com.longfor.fsscreport.costaccount.mapper;

import com.longfor.fsscreport.costaccount.entity.DwCtProjectPayment;
import com.longfor.fsscreport.reconciliation.entity.StoredProcedure;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 成本台账项目付款单明细表 Mapper 接口
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-07
 */
public interface DwCtProjectPaymentMapper extends BaseMapper<DwCtProjectPayment> {
	@Update("{call fsscreport.cost_account_pkg.update_contract_installment(#{p_company,jdbcType=VARCHAR,mode=IN},#{p_date,jdbcType=VARCHAR,mode=IN},#{p_flag,jdbcType=VARCHAR,mode=IN})}")
	@Options(statementType = StatementType.CALLABLE)
	void updataCbtz(StoredProcedure sp) throws Exception;
}
