package com.longfor.fsscreport.approval.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.longfor.fsscreport.downloadExcel.entity.OdsNcBalanceDetails;
import com.longfor.fsscreport.nc.entity.OdsNcAuxiliaryBalance;


@Repository
public interface CommonDao {
	@Select("<script>"
			+"${sql}"
			+ "</script>")
	String select(@Param("sql") String sql);
	@Select("<script>"
			+"${sql}"
			+ "</script>")
	Integer selectCount(@Param("sql") String sql);
	@Select("<script>"
			+"${sql}"
			+ "</script>")
	Map<String,Object> selectMap(@Param("sql") String sql);
	@Select("<script>"
			+"${sql}"
			+ "</script>")
	List<String> selects(@Param("sql") String sql);
	@Select("<script>"
			+"${sql}"
			+ "</script>")
	List<Map<String,Object>> selectMaps(@Param("sql") String sql);
	@Select("<script>"
			+"${sql}"
			+ "</script>")
	List<String> selectStrings(@Param("sql") String sql);
	@Select("<script>"
			+"${sql}"
			+ "</script>")
	List<BigDecimal> selectBigDecimal(@Param("sql") String sql);
	@Insert("<script>"
			+"${sql}"
			+ "</script>")
	Integer insert(@Param("sql") String sql);
	@Update("<script>"
			+"${sql}"
			+ "</script>")
	Integer update(@Param("sql") String sql);
	@Delete("<script>"
			+"${sql}"
			+ "</script>")
	Integer delete(@Param("sql") String sql);
	@Select("<script>"
	   +"${sql}"
	   + "</script>")
	BigDecimal selectBigDecimalOne(@Param("sql") String sql);
	@Select("<script>"
			+"${sql}"
			+ "</script>")
	List<OdsNcAuxiliaryBalance> getListOdsNcAuxiliaryBalance(@Param("sql") String sql);
	
	
	
	@Select("<script>"
			+"${sql}"
			+ "</script>")
	List<OdsNcBalanceDetails> selectList(@Param("sql") String sql);
	
}