package com.longfor.fsscreport.downloadExcel.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.io.Serializable;

/**
 * <p>
 * NC外系统查询辅助余额明细
 * </p>
 *
 * @author chenziyao
 * @since 2020-10-12
 */
@TableName("ODS_NC_BALANCE_DETAILS")
@KeySequence("ODS_NC_BALANCE_DETAILS_S")
public class OdsNcBalanceDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.INPUT)
    @Excel(name = "序列", width = 20, orderNum = "1")
    private BigDecimal id;

    /**
     * 年
     */
    @Excel(name = "年", width = 20, orderNum = "2")
    private String year;

    /**
     * 月
     */
    @Excel(name = "月", width = 20, orderNum = "3")
    private String month;

    /**
     * 日
     */
    @Excel(name = "日", width = 20, orderNum = "4")
    private String day;

    /**
     * 账簿名称
     */
    @Excel(name = "账簿名称", width = 20, orderNum = "5")
    private String accountname;

    /**
     * 账簿编码
     */
    @Excel(name = "账簿编码", width = 20, orderNum = "6")
    private String accountcode;

    /**
     * 辅助核算项编码
     */
    @Excel(name = "辅助核算项编码", width = 20, orderNum = "7")
    private String checktypecode;

    /**
     * 辅助核算项名称
     */
    @Excel(name = "辅助核算项名称", width = 20, orderNum = "8")
    private String checktypename;

    /**
     * 辅助核算名称
     */
    @Excel(name = "辅助核算名称", width = 20, orderNum = "9")
    private String checkvaluename;

    /**
     * 凭证号
     */
    @Excel(name = "凭证号", width = 20, orderNum = "10")
    private String vouchernum;

    /**
     * 摘要
     */
    @Excel(name = "摘要", width = 20, orderNum = "11")
    private String note;

    /**
     * 对方科目名称
     */
    @Excel(name = "对方科目名称", width = 20, orderNum = "12")
    private String adverseaccountname;

    /**
     * 借方
     */
    @Excel(name = "借方", width = 20, orderNum = "13")
    private String debitamount;

    /**
     * 贷方
     */
    @Excel(name = "贷方", width = 20, orderNum = "14")
    private String creditamount;

    /**
     * 方向
     */
    @Excel(name = "方向", width = 20, orderNum = "15")
    private String direction;

    /**
     * 余额
     */
    @Excel(name = "余额", width = 20, orderNum = "16")
    private String amount;

    /**
     * 客商code
     */
    @Excel(name = "客商code", width = 20, orderNum = "17")
    private String kscode;

    /**
     * 客商name
     */
    @Excel(name = "客商name", width = 20, orderNum = "18")
    private String ksname;

    /**
     * 项目code
     */
    @Excel(name = "项目code", width = 20, orderNum = "19")
    private String xmecode;

    /**
     * 项目name
     */
    @Excel(name = "项目name", width = 20, orderNum = "20")
    private String xmname;

    /**
     * 人员code
     */
    @Excel(name = "人员code", width = 20, orderNum = "21")
    private String rycode;

    /**
     * 人员name
     */
    @Excel(name = "人员name", width = 20, orderNum = "22")
    private String ryname;

    /**
     * 单据code
     */
    @Excel(name = "单据code", width = 20, orderNum = "23")
    private String djhcode;

    /**
     * 单据name
     */
    @Excel(name = "单据name", width = 20, orderNum = "24")
    private String djhname;

    /**
     * 辅助核算编码
     */
    @Excel(name = "djhname", width = 20, orderNum = "25")
    private String checkvaluecode;
    
    /**
     * 
     * 辅助余额id
     */
    @Excel(name = "辅助余额id", width = 20, orderNum = "26")
    private BigDecimal balanceid;
    
    
    /**
     * 开始年
     */
    @Excel(name = "开始年", width = 20, orderNum = "27")
    private String beginyear;
    
    /**
     * 开始月
     */
    @Excel(name = "开始月", width = 20, orderNum = "28")
    private String beginmonth;
    
    /**
     * 结束年
     */
    @Excel(name = "结束年", width = 20, orderNum = "7")
    private String endyear;
    
    /**
     * 结束月
     */
    @Excel(name = "结束月", width = 20, orderNum = "29")
    private String endmonth;
    
    @Excel(name = "fid", width = 20, orderNum = "30")
    private String fid;
    
    @Excel(name = "组织code", width = 20, orderNum = "31")
    private String orgcode;
    
    /**
     * 手动or自动
     */
    @Excel(name = "手动or自动", width = 20, orderNum = "32")
    private String flag;
    

    public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
    public String getOrgcode() {
		return orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
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

    public BigDecimal getBalanceid() {
		return balanceid;
	}

	public void setBalanceid(BigDecimal balanceid) {
		this.balanceid = balanceid;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
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

	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}

	public String getAccountcode() {
		return accountcode;
	}

	public void setAccountcode(String accountcode) {
		this.accountcode = accountcode;
	}

	public String getChecktypecode() {
		return checktypecode;
	}

	public void setChecktypecode(String checktypecode) {
		this.checktypecode = checktypecode;
	}

	public String getChecktypename() {
		return checktypename;
	}

	public void setChecktypename(String checktypename) {
		this.checktypename = checktypename;
	}

	public String getCheckvaluename() {
		return checkvaluename;
	}

	public void setCheckvaluename(String checkvaluename) {
		this.checkvaluename = checkvaluename;
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

	public String getAdverseaccountname() {
		return adverseaccountname;
	}

	public void setAdverseaccountname(String adverseaccountname) {
		this.adverseaccountname = adverseaccountname;
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

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getKscode() {
		return kscode;
	}

	public void setKscode(String kscode) {
		this.kscode = kscode;
	}

	public String getKsname() {
		return ksname;
	}

	public void setKsname(String ksname) {
		this.ksname = ksname;
	}

	public String getXmecode() {
		return xmecode;
	}

	public void setXmecode(String xmecode) {
		this.xmecode = xmecode;
	}

	public String getXmname() {
		return xmname;
	}

	public void setXmname(String xmname) {
		this.xmname = xmname;
	}

	public String getRycode() {
		return rycode;
	}

	public void setRycode(String rycode) {
		this.rycode = rycode;
	}

	public String getRyname() {
		return ryname;
	}

	public void setRyname(String ryname) {
		this.ryname = ryname;
	}

	public String getDjhcode() {
		return djhcode;
	}

	public void setDjhcode(String djhcode) {
		this.djhcode = djhcode;
	}

	public String getDjhname() {
		return djhname;
	}

	public void setDjhname(String djhname) {
		this.djhname = djhname;
	}

	public String getCheckvaluecode() {
		return checkvaluecode;
	}

	public void setCheckvaluecode(String checkvaluecode) {
		this.checkvaluecode = checkvaluecode;
	}

}
