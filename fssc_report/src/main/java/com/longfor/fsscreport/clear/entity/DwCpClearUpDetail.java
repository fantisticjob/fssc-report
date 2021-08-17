package com.longfor.fsscreport.clear.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 往来清理-往来明细类
 * </p>
 *
 * @author chenziyao
 * @since 2020-10-28
 */
@TableName("DW_CP_CLEAR_UP_DETAIL")
@KeySequence("DW_CP_CLEAR_UP_DETAIL_S")
public class DwCpClearUpDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编码
     * 
     */
    @TableId(value = "ID", type = IdType.INPUT)
    private Long id;

    /**
     * 数据日期
     */
    @TableField("DATA_DATE")
    private String dataDate;

    /**
     * 账套code
     */
    @TableField("ACCOUNTS_ID")
    private String accountsId;

    /**
     * 账套名称
     */
    @TableField("ACCOUNTS_NAME")
    private String accountsName;

    /**
     * 科目code
     */
    @TableField("SUBJECT_CODE")
    private String subjectCode;

    /**
     * 科目名称
     */
    @TableField("SUBJECT_NAME")
    private String subjectName;

    /**
     * 客商code
     */
    @TableField("KSCODE")
    private String kscode;

    /**
     * 客商名称
     */
    @TableField("KSNAME")
    private String ksname;

    /**
     * 人员code
     */
    @TableField("RYCODE")
    private String rycode;

    /**
     * 人员名称
     */
    @TableField("RYNAME")
    private String ryname;

    /**
     * 单据code
     */
    @TableField("DJHCODE")
    private String djhcode;

    /**
     * 单据名称
     */
    @TableField("DJHNAME")
    private String djhname;

    /**
     * 年
     */
    @TableField("YEAR")
    private Long year;

    /**
     * 月
     */
    @TableField("MONTH")
    private Long month;

    /**
     * 日
     */
    @TableField("DAYTH")
    private Long dayth;

    /**
     * 凭证号
     */
    @TableField("VOUCHERNUM")
    private String vouchernum;

    /**
     * 摘要
     */
    @TableField("NOTE")
    private String note;

    /**
     * 金额
     */
    @TableField("AMOUNT")
    private BigDecimal amount;

    /**
     * 付款编号
     */
    @TableField("PAYMENT_CODE")
    private String paymentCode;

    /**
     * 借款编号
     */
    @TableField("LOAD_CODE")
    private String loadCode;

    /**
     * 账龄
     */
    @TableField("AGING")
    private Long aging;

    /**
     * 往来成因
     */
    @TableField("RESEON")
    private String reseon;

    /**
     * 是否异常
     */
    @TableField("IS_UN_GENERAL")
    private String isUnGeneral;

    /**
     * 平台填写人
     */
    @TableField("PT_USER")
    private String ptUser;

    /**
     * 备注
     */
    @TableField("RE_MARK")
    private String reMark;

    /**
     * 预计清理时间
     */
    @TableField("CLEAR_TIME")
    private String clearTime;

    /**
     * 责任人
     */
    @TableField("RESPON_USER")
    private String responUser;

    /**
     * 责任部门
     */
    @TableField("RESPON_DEPT")
    private String responDept;

    /**
     * 备注地区
     */
    @TableField("RE_MARK_DQ")
    private String reMarkDq;

    /**
     * 地区填写人
     */
    @TableField("DQ_USER")
    private String dqUser;

    /**
     * etl时间
     */
    @TableField("ETL_TIME")
    private Date etlTime;

    /**
     * 凭证日期
     */
    @TableField("DAY")
    private Date day;

    /**
     * 匹配码
     */
    @TableField("MATCHINGCODE")
    private String matchingcode;

    /**
     * 季度
     */
    @TableField("QUARTER")
    private String quarter;

    /**
     * 初始化标识（0-非初始化数据 1-初始化数据）
     */
    @TableField("CSH_FLAG")
    private String cshFlag;
    
    /**
     * 勾兑标识
     */
    @TableField("GD_FLAG")
    private String  gdFlag;

    /**
     * 发送标志
     * @return
     */
    @TableField("GX_FLAG")
    private String gxFlag;

    /**
     * etl时间
     */
    @TableField("FSSJ_TIME")
    private Date sendConfirmDate;

    
    public String getGdFlag() {
		return gdFlag;
	}

	public void setGdFlag(String gdFlag) {
		this.gdFlag = gdFlag;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getDataDate() {
        return dataDate;
    }

    public void setDataDate(String dataDate) {
        this.dataDate = dataDate;
    }
    public String getAccountsId() {
        return accountsId;
    }

    public void setAccountsId(String accountsId) {
        this.accountsId = accountsId;
    }
    public String getAccountsName() {
        return accountsName;
    }

    public void setAccountsName(String accountsName) {
        this.accountsName = accountsName;
    }
    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }
    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
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
    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }
    public Long getMonth() {
        return month;
    }

    public void setMonth(Long month) {
        this.month = month;
    }
    public Long getDayth() {
        return dayth;
    }

    public void setDayth(Long dayth) {
        this.dayth = dayth;
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
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }
    public String getLoadCode() {
        return loadCode;
    }

    public void setLoadCode(String loadCode) {
        this.loadCode = loadCode;
    }
    public Long getAging() {
        return aging;
    }

    public void setAging(Long aging) {
        this.aging = aging;
    }
    public String getReseon() {
        return reseon;
    }

    public void setReseon(String reseon) {
        this.reseon = reseon;
    }
    public String getIsUnGeneral() {
        return isUnGeneral;
    }

    public void setIsUnGeneral(String isUnGeneral) {
        this.isUnGeneral = isUnGeneral;
    }
    public String getPtUser() {
        return ptUser;
    }

    public void setPtUser(String ptUser) {
        this.ptUser = ptUser;
    }
    public String getReMark() {
        return reMark;
    }

    public void setReMark(String reMark) {
        this.reMark = reMark;
    }
    public String getClearTime() {
        return clearTime;
    }

    public void setClearTime(String clearTime) {
        this.clearTime = clearTime;
    }
    public String getResponUser() {
        return responUser;
    }

    public void setResponUser(String responUser) {
        this.responUser = responUser;
    }
    public String getResponDept() {
        return responDept;
    }

    public void setResponDept(String responDept) {
        this.responDept = responDept;
    }
    public String getReMarkDq() {
        return reMarkDq;
    }

    public void setReMarkDq(String reMarkDq) {
        this.reMarkDq = reMarkDq;
    }
    public String getDqUser() {
        return dqUser;
    }

    public void setDqUser(String dqUser) {
        this.dqUser = dqUser;
    }
    public Date getEtlTime() {
        return etlTime;
    }

    public void setEtlTime(Date etlTime) {
        this.etlTime = etlTime;
    }
    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }
    public String getMatchingcode() {
        return matchingcode;
    }

    public void setMatchingcode(String matchingcode) {
        this.matchingcode = matchingcode;
    }
    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }
    public String getCshFlag() {
        return cshFlag;
    }

    public void setCshFlag(String cshFlag) {
        this.cshFlag = cshFlag;
    }
    public String getGxFlag() {
        return gxFlag;
    }

    public void setGxFlag(String gxFlag) {
        this.gxFlag = gxFlag;
    }

    public Date getSendConfirmDate() {
        return sendConfirmDate;
    }

    public void setSendConfirmDate(Date sendConfirmDate) {
        this.sendConfirmDate = sendConfirmDate;
    }

    @Override
    public String toString() {
        return "DwCpClearUpDetail{" +
            "id=" + id +
            ", dataDate=" + dataDate +
            ", accountsId=" + accountsId +
            ", accountsName=" + accountsName +
            ", subjectCode=" + subjectCode +
            ", subjectName=" + subjectName +
            ", kscode=" + kscode +
            ", ksname=" + ksname +
            ", rycode=" + rycode +
            ", ryname=" + ryname +
            ", djhcode=" + djhcode +
            ", djhname=" + djhname +
            ", year=" + year +
            ", month=" + month +
            ", dayth=" + dayth +
            ", vouchernum=" + vouchernum +
            ", note=" + note +
            ", amount=" + amount +
            ", paymentCode=" + paymentCode +
            ", loadCode=" + loadCode +
            ", aging=" + aging +
            ", reseon=" + reseon +
            ", isUnGeneral=" + isUnGeneral +
            ", ptUser=" + ptUser +
            ", reMark=" + reMark +
            ", clearTime=" + clearTime +
            ", responUser=" + responUser +
            ", responDept=" + responDept +
            ", reMarkDq=" + reMarkDq +
            ", dqUser=" + dqUser +
            ", etlTime=" + etlTime +
            ", day=" + day +
            ", matchingcode=" + matchingcode +
            ", quarter=" + quarter +
            ", cshFlag=" + cshFlag +
        "}";
    }
}
