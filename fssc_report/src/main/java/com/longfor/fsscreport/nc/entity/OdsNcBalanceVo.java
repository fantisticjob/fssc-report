package com.longfor.fsscreport.nc.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * NC外系统查询科目余额表
 * </p>
 *
 * @author chenziyao
 * @since 2020-10-09
 */
@TableName("ODS_NC_BALANCE_VO")
@KeySequence("ODS_NC_BALANCE_VO_S")
public class OdsNcBalanceVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private BigDecimal id;

    /**
     * 科目编码
     */
    private String accountcode;

    /**
     * 科目名称
     */
    private String accountname;

    /**
     * 账簿编码
     */
    private String accountingbookcode;

    /**
     * 账簿名称
     */
    private String accountingbookname;

    /**
     * 期初方向
     */
    private String qcfx;

    /**
     * 期初
     */
    private String qc;

    /**
     * 本期借
     */
    private String debitamount;

    /**
     * 本期贷
     */
    private String creditamount;

    /**
     * 借方累计
     */
    private String debitaccumamount;

    /**
     * 贷方累计
     */
    private String creditaccumamount;

    /**
     * 期末方向
     */
    private String qmfx;

    /**
     * 期末
     */
    private String qm;

    /**
     * 开始年
     */
    private String beginyear;
    
    /**
     * 开始月
     */
    private String beginmonth;
    
    /**
     * 结束年
     */
    private String endyear;
    
    /**
     * 结束月
     */
    private String endmonth;
    
    /**
     * 1是手动
     */
    private String flag;
    
    public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

    public String getBeginyear() {
		return beginyear;
	}

	public void setBeginyear(String beginyear) {
		this.beginyear = beginyear;
	}

	public String getBeginmonth() {
		return beginmonth;
	}

	public void setBeginmonth(String beginmonth) {
		this.beginmonth = beginmonth;
	}

	public String getEndyear() {
		return endyear;
	}

	public void setEndyear(String endyear) {
		this.endyear = endyear;
	}

	public String getEndmonth() {
		return endmonth;
	}

	public void setEndmonth(String endmonth) {
		this.endmonth = endmonth;
	}
    
	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getAccountcode() {
		return accountcode;
	}

	public void setAccountcode(String accountcode) {
		this.accountcode = accountcode;
	}

	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}

	public String getAccountingbookcode() {
		return accountingbookcode;
	}

	public void setAccountingbookcode(String accountingbookcode) {
		this.accountingbookcode = accountingbookcode;
	}

	public String getAccountingbookname() {
		return accountingbookname;
	}

	public void setAccountingbookname(String accountingbookname) {
		this.accountingbookname = accountingbookname;
	}

	public String getQcfx() {
		return qcfx;
	}

	public void setQcfx(String qcfx) {
		this.qcfx = qcfx;
	}

	public String getQc() {
		return qc;
	}

	public void setQc(String qc) {
		this.qc = qc;
	}

	public String getDebitamount() {
		return debitamount;
	}

	public void setDebitamount(String debitamount) {
		this.debitamount = debitamount;
	}

	public String getCreditamount() {
		return creditamount;
	}

	public void setCreditamount(String creditamount) {
		this.creditamount = creditamount;
	}

	public String getDebitaccumamount() {
		return debitaccumamount;
	}

	public void setDebitaccumamount(String debitaccumamount) {
		this.debitaccumamount = debitaccumamount;
	}

	public String getCreditaccumamount() {
		return creditaccumamount;
	}

	public void setCreditaccumamount(String creditaccumamount) {
		this.creditaccumamount = creditaccumamount;
	}

	public String getQmfx() {
		return qmfx;
	}

	public void setQmfx(String qmfx) {
		this.qmfx = qmfx;
	}

	public String getQm() {
		return qm;
	}

	public void setQm(String qm) {
		this.qm = qm;
	}

}
