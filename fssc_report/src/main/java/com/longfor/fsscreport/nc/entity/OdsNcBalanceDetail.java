package com.longfor.fsscreport.nc.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * NC系统科目余额表明细
 * </p>
 *
 * @author chenziyao
 * @since 2020-10-12
 */
@TableName("ODS_NC_BALANCE_DETAIL")
@KeySequence("ODS_NC_BALANCE_DETAIL_S")
public class OdsNcBalanceDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "ID", type = IdType.INPUT)
    private BigDecimal id;
    /**
     * 账簿编码
     */
    private String accountingbookcode;

    /**
     * 账簿名称
     */
    private String accountingbookname;

    /**
     * 科目编码
     */
    private String accountcode;

    /**
     * 科目名称
     */
    private String accountname;

    /**
     * 年
     */
    private String year;

    /**
     * 月
     */
    private String month;

    /**
     * 日
     */
    private String day;

    /**
     * 凭证号
     */
    private String vouchernum;

    /**
     * 备注
     */
    private String note;

    /**
     * 对方科目
     */
    private String adverseaccount;

    /**
     * 借方
     */
    private String debitamount;

    /**
     * 贷方
     */
    private String crebitamount;

    /**
     * 方向
     */
    private String fx;

    /**
     * 余额
     */
    private String amount;

    /**
     * 来源系统
     */
    private String system;

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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getVouchernum() {
		return vouchernum;
	}

	public void setVouchernum(String vouchernum) {
		this.vouchernum = vouchernum;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getAdverseaccount() {
		return adverseaccount;
	}

	public void setAdverseaccount(String adverseaccount) {
		this.adverseaccount = adverseaccount;
	}

	public String getDebitamount() {
		return debitamount;
	}

	public void setDebitamount(String debitamount) {
		this.debitamount = debitamount;
	}

	public String getCrebitamount() {
		return crebitamount;
	}

	public void setCrebitamount(String crebitamount) {
		this.crebitamount = crebitamount;
	}

	public String getFx() {
		return fx;
	}

	public void setFx(String fx) {
		this.fx = fx;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

}
