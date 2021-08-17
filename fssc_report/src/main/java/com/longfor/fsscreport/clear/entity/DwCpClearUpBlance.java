package com.longfor.fsscreport.clear.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 往来清理-往来余额类
 * </p>
 *
 * @author chenziyao
 * @since 2020-10-30
 */
@TableName("DW_CP_CLEAR_UP_BLANCE")
public class DwCpClearUpBlance implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编码
     */
    @TableId("ID")
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
     * 项目code
     */
    @TableField("XMCODE")
    private String xmcode;

    /**
     * 项目名称
     */
    @TableField("XMNAME")
    private String xmname;

    /**
     * 金额
     */
    @TableField("AMOUNT")
    private BigDecimal amount;

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
     * 预计清理时间
     */
    @TableField("CLEAR_TIME")
    private String clearTime;
    
    /**
     * 备注地区
     */
    @TableField("RE_MARK")
    private String reMark;

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
     * 账龄1- 60天
     */
    @TableField("AMOUNT1")
    private BigDecimal amount1;

    /**
     * 账龄61 – 180 天
     */
    @TableField("AMOUNT2")
    private BigDecimal amount2;

    /**
     * 账龄181-365天
     */
    @TableField("AMOUNT3")
    private BigDecimal amount3;

    /**
     * 账龄1年至2年
     */
    @TableField("AMOUNT4")
    private BigDecimal amount4;

    /**
     * 账龄2年至3年
     */
    @TableField("AMOUNT5")
    private BigDecimal amount5;

    /**
     * 账龄3年以上
     */
    @TableField("AMOUNT6")
    private BigDecimal amount6;

    /**
     * 季度
     */
    @TableField("QUARTER")
    private String quarter;
    
    /**
     * 插入行标识（0-非插入行数据 1-插入行数据）
     */
    @TableField("insert_flag")
    private String insertFlag;
    
    /**
     * 本季发生额
     */
    @TableField("fsamount")
    private String fsamount;
    
    /**
     *初始化标识（0-非初始化数据 1-初始化数据）
     */
    @TableField("csh_flag")
    private String cshFlag;

    public String getInsertFlag() {
		return insertFlag;
	}

	public void setInsertFlag(String insertFlag) {
		this.insertFlag = insertFlag;
	}

	public String getFsamount() {
		return fsamount;
	}

	public void setFsamount(String fsamount) {
		this.fsamount = fsamount;
	}

	public String getCshFlag() {
		return cshFlag;
	}

	public void setCshFlag(String cshFlag) {
		this.cshFlag = cshFlag;
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
    public String getXmcode() {
        return xmcode;
    }

    public void setXmcode(String xmcode) {
        this.xmcode = xmcode;
    }
    public String getXmname() {
        return xmname;
    }

    public void setXmname(String xmname) {
        this.xmname = xmname;
    }
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
    public String getReMark() {
        return reMark;
    }

    public void setReMark(String reMark) {
        this.reMark = reMark;
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
    public BigDecimal getAmount1() {
        return amount1;
    }

    public void setAmount1(BigDecimal amount1) {
        this.amount1 = amount1;
    }
    public BigDecimal getAmount2() {
        return amount2;
    }

    public void setAmount2(BigDecimal amount2) {
        this.amount2 = amount2;
    }
    public BigDecimal getAmount3() {
        return amount3;
    }

    public void setAmount3(BigDecimal amount3) {
        this.amount3 = amount3;
    }
    public BigDecimal getAmount4() {
        return amount4;
    }

    public void setAmount4(BigDecimal amount4) {
        this.amount4 = amount4;
    }
    public BigDecimal getAmount5() {
        return amount5;
    }

    public void setAmount5(BigDecimal amount5) {
        this.amount5 = amount5;
    }
    public BigDecimal getAmount6() {
        return amount6;
    }

    public void setAmount6(BigDecimal amount6) {
        this.amount6 = amount6;
    }
    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    @Override
    public String toString() {
        return "DwCpClearUpBlance{" +
            "id=" + id +
            ", dataDate=" + dataDate +
            ", accountsId=" + accountsId +
            ", accountsName=" + accountsName +
            ", subjectCode=" + subjectCode +
            ", subjectName=" + subjectName +
            ", kscode=" + kscode +
            ", ksname=" + ksname +
            ", xmcode=" + xmcode +
            ", xmname=" + xmname +
            ", amount=" + amount +
            ", aging=" + aging +
            ", reseon=" + reseon +
            ", isUnGeneral=" + isUnGeneral +
            ", ptUser=" + ptUser +
            ", clearTime=" + clearTime +
            ", responUser=" + responUser +
            ", responDept=" + responDept +
            ", reMark=" + reMark +
            ", dqUser=" + dqUser +
            ", etlTime=" + etlTime +
            ", amount1=" + amount1 +
            ", amount2=" + amount2 +
            ", amount3=" + amount3 +
            ", amount4=" + amount4 +
            ", amount5=" + amount5 +
            ", amount6=" + amount6 +
            ", quarter=" + quarter +
        "}";
    }
}
