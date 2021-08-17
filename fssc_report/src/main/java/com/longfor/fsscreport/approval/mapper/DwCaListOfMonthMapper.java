package com.longfor.fsscreport.approval.mapper;

import com.longfor.fsscreport.approval.entity.DwCaListOfMonth;
import com.longfor.fsscreport.reconciliation.entity.StoredProcedure;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chenziyao
 * @since 2020-07-11
 */
public interface DwCaListOfMonthMapper extends BaseMapper<DwCaListOfMonth>{
	@Update("{call fsscreport.ca_list_of_months_pkg.dw_ca_list_of_month(#{p_date,jdbcType=VARCHAR,mode=IN},#{p_company,jdbcType=VARCHAR,mode=IN})}")
	@Options(statementType = StatementType.CALLABLE)
	void updataDwCaListOfMonth(StoredProcedure sp);
}
