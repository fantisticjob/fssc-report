package com.longfor.fsscreport.taxsources.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 企业景气调查问卷（季报）
 * </p>
 *
 * @author chenziyao
 * @since 2020-07-29
 */
@TableName("DW_ZDSY_3MONTHLY_TAX_REPORT")
@KeySequence(value = "SEQ_DEMO_ID")

public class DwZdsy3monthlyTaxReport implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "ID", type = IdType.INPUT)
    private BigDecimal id;

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
     * 金额（下季预测）
     */
    @TableField("AMOUNT_XJYC")
    private BigDecimal amountXjyc;

    /**
     * 金额（季度去年同期）
     */
    @TableField("AMOUNT_QYTQ")
    private BigDecimal amountQytq;

    /**
     * 金额（全年预测）
     */
    @TableField("AMOUNT_QNYC")
    private BigDecimal amountQnyc;

    /**
     * 金额（去年实际）
     */
    @TableField("AMOUNT_QNSJ")
    private BigDecimal amountQnsj;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
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
    public BigDecimal getAmountXjyc() {
        return amountXjyc;
    }

    public void setAmountXjyc(BigDecimal amountXjyc) {
        this.amountXjyc = amountXjyc;
    }
    public BigDecimal getAmountQytq() {
        return amountQytq;
    }

    public void setAmountQytq(BigDecimal amountQytq) {
        this.amountQytq = amountQytq;
    }
    public BigDecimal getAmountQnyc() {
        return amountQnyc;
    }

    public void setAmountQnyc(BigDecimal amountQnyc) {
        this.amountQnyc = amountQnyc;
    }
    public BigDecimal getAmountQnsj() {
        return amountQnsj;
    }

    public void setAmountQnsj(BigDecimal amountQnsj) {
        this.amountQnsj = amountQnsj;
    }

    @Override
    public String toString() {
        return "DwZdsy3monthlyTaxReport{" +
            "id=" + id +
            ", accountTime=" + accountTime +
            ", area=" + area +
            ", org=" + org +
            ", zbName=" + zbName +
            ", amountXjyc=" + amountXjyc +
            ", amountQytq=" + amountQytq +
            ", amountQnyc=" + amountQnyc +
            ", amountQnsj=" + amountQnsj +
        "}";
    }
}
