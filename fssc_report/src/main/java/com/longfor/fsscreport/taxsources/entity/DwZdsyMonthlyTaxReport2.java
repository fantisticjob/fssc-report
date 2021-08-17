package com.longfor.fsscreport.taxsources.entity;

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
 * 财务信息（季报）表
 * </p>
 *
 * @author chenziyao
 * @since 2020-11-21
 */
@TableName("DW_ZDSY_MONTHLY_TAX_REPORT2")
@KeySequence("DW_ZDSY_MONTHLY_TAX_REPORT2_S")
public class DwZdsyMonthlyTaxReport2 implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.INPUT)
    private Long id;

    /**
     * 所属期
     */
    @TableField("ACCOUNT_TIME")
    private String accountTime;

    /**
     * 地区
     */
    @TableField("AREA")
    private String area;

    /**
     * 组织
     */
    @TableField("ORG")
    private String org;

    /**
     * 指标名称
     */
    @TableField("ZB_NAME")
    private String zbName;

    /**
     * 画面表示顺序
     */
    @TableField("SEQ")
    private Long seq;

    /**
     * 指标类别 1：营收 2：资产
     */
    @TableField("ZB_TYPE")
    private String zbType;

    /**
     * 金额（营收）
     */
    @TableField("AMOUNT")
    private BigDecimal amount;

    /**
     * 去年同期金额（营收）
     */
    @TableField("AMOUNT_LAST")
    private BigDecimal amountLast;

    /**
     * 金额年初
     */
    @TableField("AMOUNT_BEGIN")
    private BigDecimal amountBegin;

    /**
     * 金额期末
     */
    @TableField("AMOUNT_END")
    private BigDecimal amountEnd;

    /**
     * 金额去年年初
     */
    @TableField("AMOUNT_BEGIN_QN")
    private BigDecimal amountBeginQn;

    /**
     * 金额去年期末
     */
    @TableField("AMOUNT_BEGIN_QM")
    private BigDecimal amountBeginQm;

    /**
     * 平台
     */
    @TableField("PLATFORM")
    private String platform;

    /**
     * etl时间
     */
    @TableField("ETL_TIME")
    private Date etlTime;

    /**
     * 流程id
     */
    @TableField("INSTANCE_ID")
    private String instanceId;

    /**
     * 审批状态
     */
    @TableField("SP")
    private String sp;

    /**
     * 提交状态
     */
    @TableField("STATUS")
    private String status;

    /**
     * 组织code
     */
    @TableField("ORG_CODE")
    private String orgCode;

    /**
     * 季度（2020Q3）
     */
    @TableField("QUARTER")
    private String quarter;

    /**
     * 组织（汇总）
     */
    @TableField("ORG_HZ")
    private String orgHz;

    /**
     * 组织code（汇总）
     */
    @TableField("ORG_CODE_HZ")
    private String orgCodeHz;

    /**
     * 保存状态（1-已保存 0-未保存）
     */
    @TableField("SAVE_STATE")
    private String saveState;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getAccountTime() {
        return accountTime;
    }

    public void setAccountTime(String accountTime) {
        this.accountTime = accountTime;
    }
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }
    public String getZbName() {
        return zbName;
    }

    public void setZbName(String zbName) {
        this.zbName = zbName;
    }
    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }
    public String getZbType() {
        return zbType;
    }

    public void setZbType(String zbType) {
        this.zbType = zbType;
    }
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public BigDecimal getAmountLast() {
        return amountLast;
    }

    public void setAmountLast(BigDecimal amountLast) {
        this.amountLast = amountLast;
    }
    public BigDecimal getAmountBegin() {
        return amountBegin;
    }

    public void setAmountBegin(BigDecimal amountBegin) {
        this.amountBegin = amountBegin;
    }
    public BigDecimal getAmountEnd() {
        return amountEnd;
    }

    public void setAmountEnd(BigDecimal amountEnd) {
        this.amountEnd = amountEnd;
    }
    public BigDecimal getAmountBeginQn() {
        return amountBeginQn;
    }

    public void setAmountBeginQn(BigDecimal amountBeginQn) {
        this.amountBeginQn = amountBeginQn;
    }
    public BigDecimal getAmountBeginQm() {
        return amountBeginQm;
    }

    public void setAmountBeginQm(BigDecimal amountBeginQm) {
        this.amountBeginQm = amountBeginQm;
    }
    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
    public Date getEtlTime() {
        return etlTime;
    }

    public void setEtlTime(Date etlTime) {
        this.etlTime = etlTime;
    }
    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }
    public String getSp() {
        return sp;
    }

    public void setSp(String sp) {
        this.sp = sp;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }
    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }
    public String getOrgHz() {
        return orgHz;
    }

    public void setOrgHz(String orgHz) {
        this.orgHz = orgHz;
    }
    public String getOrgCodeHz() {
        return orgCodeHz;
    }

    public void setOrgCodeHz(String orgCodeHz) {
        this.orgCodeHz = orgCodeHz;
    }
    public String getSaveState() {
        return saveState;
    }

    public void setSaveState(String saveState) {
        this.saveState = saveState;
    }

    @Override
    public String toString() {
        return "DwZdsyMonthlyTaxReport2{" +
            "id=" + id +
            ", accountTime=" + accountTime +
            ", area=" + area +
            ", org=" + org +
            ", zbName=" + zbName +
            ", seq=" + seq +
            ", zbType=" + zbType +
            ", amount=" + amount +
            ", amountLast=" + amountLast +
            ", amountBegin=" + amountBegin +
            ", amountEnd=" + amountEnd +
            ", amountBeginQn=" + amountBeginQn +
            ", amountBeginQm=" + amountBeginQm +
            ", platform=" + platform +
            ", etlTime=" + etlTime +
            ", instanceId=" + instanceId +
            ", sp=" + sp +
            ", status=" + status +
            ", orgCode=" + orgCode +
            ", quarter=" + quarter +
            ", orgHz=" + orgHz +
            ", orgCodeHz=" + orgCodeHz +
            ", saveState=" + saveState +
        "}";
    }
}
