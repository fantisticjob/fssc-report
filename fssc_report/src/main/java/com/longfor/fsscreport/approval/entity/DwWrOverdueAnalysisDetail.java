package com.longfor.fsscreport.approval.entity;

import java.io.Serializable;
import java.util.Date;


import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * <p>
 * 实体类
 * </p>
 *
 * @author chenziyao
 * @since 2020-07-06
 */
@TableName("DW_WR_OVERDUE_ANALYSIS_LOCK")
public class DwWrOverdueAnalysisDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date datekey;
    
    private String area;
    
    private String expiration_type;
    
    private String unexpired_analy;
    
    private String buname;
    
    private String parentprojname;
    
    private String projname;
    
    private String roomcode;
    
    private String cstallname;
    
    private Integer rmbye;
    @JsonFormat(pattern = "yyyy-MM-dd ",timezone = "GMT+8")
    private Date due_date;
    
    private Integer amount;
    @JsonFormat(pattern = "yyyy-MM-dd ",timezone = "GMT+8")
    private Date payment_date;
    
    private String subcontractor_name;
    
    private String over_pay_reasons;
    
    private String solution;
    
    private String exp_resolution_time;
    
    private String remarks;
    
    private String solution_categ;
    
    private String reporting_dept;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date etl_time;
    
    private String update_by;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date update_date;
    
    private String feeguid;
    
    private String sp;
    
    private String instance_id;
    
    private String dqspr;
  
	public String getDqspr() {
		return dqspr;
	}

	public void setDqspr(String dqspr) {
		this.dqspr = dqspr;
	}

	public String getInstance_id() {
		return instance_id;
	}

	public void setInstance_id(String instance_id) {
		this.instance_id = instance_id;
	}


	public Date getDatekey() {
		return datekey;
	}

	public void setDatekey(Date datekey) {
		this.datekey = datekey;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getExpiration_type() {
		return expiration_type;
	}

	public void setExpiration_type(String expiration_type) {
		this.expiration_type = expiration_type;
	}

	public String getUnexpired_analy() {
		return unexpired_analy;
	}

	public void setUnexpired_analy(String unexpired_analy) {
		this.unexpired_analy = unexpired_analy;
	}

	public String getBuname() {
		return buname;
	}

	public void setBuname(String buname) {
		this.buname = buname;
	}

	public String getParentprojname() {
		return parentprojname;
	}

	public void setParentprojname(String parentprojname) {
		this.parentprojname = parentprojname;
	}

	public String getProjname() {
		return projname;
	}

	public void setProjname(String projname) {
		this.projname = projname;
	}

	public String getRoomcode() {
		return roomcode;
	}

	public void setRoomcode(String roomcode) {
		this.roomcode = roomcode;
	}

	public String getCstallname() {
		return cstallname;
	}

	public void setCstallname(String cstallname) {
		this.cstallname = cstallname;
	}

	public Integer getRmbye() {
		return rmbye;
	}

	public void setRmbye(Integer rmbye) {
		this.rmbye = rmbye;
	}

	public Date getDue_date() {
		return due_date;
	}

	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Date getPayment_date() {
		return payment_date;
	}

	public void setPayment_date(Date payment_date) {
		this.payment_date = payment_date;
	}

	public String getSubcontractor_name() {
		return subcontractor_name;
	}

	public void setSubcontractor_name(String subcontractor_name) {
		this.subcontractor_name = subcontractor_name;
	}

	public String getOver_pay_reasons() {
		return over_pay_reasons;
	}

	public void setOver_pay_reasons(String over_pay_reasons) {
		this.over_pay_reasons = over_pay_reasons;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public String getExp_resolution_time() {
		return exp_resolution_time;
	}

	public void setExp_resolution_time(String exp_resolution_time) {
		this.exp_resolution_time = exp_resolution_time;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getSolution_categ() {
		return solution_categ;
	}

	public void setSolution_categ(String solution_categ) {
		this.solution_categ = solution_categ;
	}

	public String getReporting_dept() {
		return reporting_dept;
	}

	public void setReporting_dept(String reporting_dept) {
		this.reporting_dept = reporting_dept;
	}

	public Date getEtl_time() {
		return etl_time;
	}

	public void setEtl_time(Date etl_time) {
		this.etl_time = etl_time;
	}

	public String getUpdate_by() {
		return update_by;
	}

	public void setUpdate_by(String update_by) {
		this.update_by = update_by;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public String getFeeguid() {
		return feeguid;
	}

	public void setFeeguid(String feeguid) {
		this.feeguid = feeguid;
	}

	public String getSp() {
		return sp;
	}

	public void setSp(String sp) {
		this.sp = sp;
	}
    
}
