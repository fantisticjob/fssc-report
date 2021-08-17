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
 * 重点税源企业税收信息（月报）表_详细
 * </p>
 *
 * @author chenziyao
 * @since 2020-11-21
 */
@TableName("DW_ZDSY_MONTHLY_TAX_REPORT")
@KeySequence("DW_ZDSY_MONTHLY_TAX_REPORT_S")
public class DwZdsyMonthlyTaxReport implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编码
     */
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
     * 画面表示顺序
     */
    @TableField("SEQ")
    private Long seq;

    /**
     * 指标名称
     */
    @TableField("ZB_NAME")
    private String zbName;

    /**
     * 本月金额
     */
    @TableField("AMOUNT1")
    private BigDecimal amount1;

    /**
     * 本年金额
     */
    @TableField("AMOUNT2")
    private BigDecimal amount2;

    /**
     * 上月金额
     */
    @TableField("AMOUNT3")
    private BigDecimal amount3;

    /**
     * 上年金额
     */
    @TableField("AMOUNT4")
    private BigDecimal amount4;

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
     * 指标大类
     */
    @TableField("ZB_TYPES")
    private String zbTypes;

    /**
     * 指标取数（自动、手动、自动+手动）
     */
    @TableField("ZB_ACCESS")
    private String zbAccess;

    /**
     * 来源系统
     */
    @TableField("SOURCE_SYSTEM")
    private String sourceSystem;

    /**
     * 取数表单
     */
    @TableField("ACCESS_FORM")
    private String accessForm;

    /**
     * 取数逻辑
     */
    @TableField("ACCESS_LOGIC")
    private String accessLogic;

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
     * 提交状态（1-流程已提交 0-未提交 2 -报表已提交）
     */
    @TableField("STATUS")
    private String status;

    /**
     * 组织code
     */
    @TableField("ORG_CODE")
    private String orgCode;

    /**
     * 组织code（汇总）
     */
    @TableField("ORG_CODE_HZ")
    private String orgCodeHz;

    /**
     * 组织（汇总）
     */
    @TableField("ORG_HZ")
    private String orgHz;

    /**
     * 季度
     */
    @TableField("QUARTER")
    private String quarter;

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
    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }
    public String getZbName() {
        return zbName;
    }

    public void setZbName(String zbName) {
        this.zbName = zbName;
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
    public String getZbTypes() {
        return zbTypes;
    }

    public void setZbTypes(String zbTypes) {
        this.zbTypes = zbTypes;
    }
    public String getZbAccess() {
        return zbAccess;
    }

    public void setZbAccess(String zbAccess) {
        this.zbAccess = zbAccess;
    }
    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }
    public String getAccessForm() {
        return accessForm;
    }

    public void setAccessForm(String accessForm) {
        this.accessForm = accessForm;
    }
    public String getAccessLogic() {
        return accessLogic;
    }

    public void setAccessLogic(String accessLogic) {
        this.accessLogic = accessLogic;
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
    public String getOrgCodeHz() {
        return orgCodeHz;
    }

    public void setOrgCodeHz(String orgCodeHz) {
        this.orgCodeHz = orgCodeHz;
    }
    public String getOrgHz() {
        return orgHz;
    }

    public void setOrgHz(String orgHz) {
        this.orgHz = orgHz;
    }
    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }
    public String getSaveState() {
        return saveState;
    }

    public void setSaveState(String saveState) {
        this.saveState = saveState;
    }

    @Override
    public String toString() {
        return "DwZdsyMonthlyTaxReport{" +
            "id=" + id +
            ", accountTime=" + accountTime +
            ", area=" + area +
            ", org=" + org +
            ", seq=" + seq +
            ", zbName=" + zbName +
            ", amount1=" + amount1 +
            ", amount2=" + amount2 +
            ", amount3=" + amount3 +
            ", amount4=" + amount4 +
            ", platform=" + platform +
            ", etlTime=" + etlTime +
            ", zbTypes=" + zbTypes +
            ", zbAccess=" + zbAccess +
            ", sourceSystem=" + sourceSystem +
            ", accessForm=" + accessForm +
            ", accessLogic=" + accessLogic +
            ", instanceId=" + instanceId +
            ", sp=" + sp +
            ", status=" + status +
            ", orgCode=" + orgCode +
            ", orgCodeHz=" + orgCodeHz +
            ", orgHz=" + orgHz +
            ", quarter=" + quarter +
            ", saveState=" + saveState +
        "}";
    }
}
