package com.longfor.fsscreport.clear.mapper;

import com.longfor.fsscreport.clear.entity.DwCpClearUpTotal;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 往来清理汇总表 Mapper 接口
 * </p>
 *
 * @author chenziyao
 * @since 2020-10-30
 */
@Repository
public interface DwCpClearUpTotalMapper extends BaseMapper<DwCpClearUpTotal> {

	@Update("{call fsscreport.BA_BALANCE_PKG.update_balance_total(#{p_account,jdbcType=VARCHAR,mode=IN},#{p_date,jdbcType=VARCHAR,mode=IN})}")
	@Options(statementType = StatementType.CALLABLE)
	void updateStoredProcedure(String p_account,String p_date);

	@Update("{call cp_clear_up_pkg.update_detail_aging_day(#{p_quarter,jdbcType=VARCHAR,mode=IN},#{p_accounts,jdbcType=VARCHAR,mode=IN})}")
	@Options(statementType = StatementType.CALLABLE)
	void initData(String p_quarter, String p_accounts);
	//p_date 取数更新优化调整，新增时间参数
	@Update("{call cp_clear_up_pkg.update_cp(#{p_date,jdbcType=VARCHAR,mode=IN},#{p_quarter,jdbcType=VARCHAR,mode=IN},#{p_accounts,jdbcType=VARCHAR,mode=IN},#{p_subject,jdbcType=VARCHAR,mode=IN})}")
	@Options(statementType = StatementType.CALLABLE)
	void getNcData(String p_date, String p_quarter, String p_accounts,String p_subject);
}
