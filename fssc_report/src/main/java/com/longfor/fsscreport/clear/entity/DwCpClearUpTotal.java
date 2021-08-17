package com.longfor.fsscreport.clear.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 往来清理汇总表
 * </p>
 *
 * @author chenziyao
 * @since 2020-10-30
 */
@TableName("DW_CP_CLEAR_UP_TOTAL")
public class DwCpClearUpTotal implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId("ID")
    private Long id;

    /**
     * 平台Code
     */
    @TableField("PLAT_CODE")
    private String platCode;

    /**
     * 平台名称
     */
    @TableField("PLAT_NAME")
    private String platName;

    /**
     * 地区Code
     */
    @TableField("AREA_CODE")
    private String areaCode;

    /**
     * 地区名称
     */
    @TableField("AREA_NAME")
    private String areaName;

    /**
     * 账套Code
     */
    @TableField("ACCOUNTS_ID")
    private String accountsId;

    /**
     * 账套名称
     */
    @TableField("ACCOUNTS_NAME")
    private String accountsName;

    /**
     * 年月
     */
    @TableField("DATA_DATE")
    private String dataDate;

    /**
     * 科目Code
     */
    @TableField("SUBJECT_CODE")
    private String subjectCode;

    /**
     * 科目名称
     */
    @TableField("SUBJECT_NAME")
    private String subjectName;

    /**
     * NC余额
     */
    @TableField("NC_BALANCE")
    private BigDecimal ncBalance;

    /**
     * 备注
     */
    @TableField("REMARK")
    private String remark;

    /**
     * 状态
     */
    @TableField("SP")
    private String sp;

    /**
     * ETL_TIME
     */
    @TableField("ETL_TIME")
    private Date etlTime;

    /**
     * 流程id
     */
    @TableField("INSTANCE_ID")
    private String instanceId;

    /**
     * 锁数状态（0-未锁数 1-锁数）
     */
    @TableField("LOCK_STATUS")
    private String lockStatus;

    /**
     * 取数更新状态（0-未更新 ）
     */
    @TableField("UPDATE_STATUS")
    private String updateStatus;

    /**
     * 启用日期
     */
    @TableField("START_DATE")
    private Date startDate;

    /**
     * 航道
     */
    @TableField("CHANAL")
    private String chanal;

    /**
     * 季度
     */
    @TableField("QUARTER")
    private String quarter;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getPlatCode() {
        return platCode;
    }

    public void setPlatCode(String platCode) {
        this.platCode = platCode;
    }
    public String getPlatName() {
        return platName;
    }

    public void setPlatName(String platName) {
        this.platName = platName;
    }
    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
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
    public String getDataDate() {
        return dataDate;
    }

    public void setDataDate(String dataDate) {
        this.dataDate = dataDate;
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
    public BigDecimal getNcBalance() {
        return ncBalance;
    }

    public void setNcBalance(BigDecimal ncBalance) {
        this.ncBalance = ncBalance;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getSp() {
        return sp;
    }

    public void setSp(String sp) {
        this.sp = sp;
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
    public String getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(String lockStatus) {
        this.lockStatus = lockStatus;
    }
    public String getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(String updateStatus) {
        this.updateStatus = updateStatus;
    }
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public String getChanal() {
        return chanal;
    }

    public void setChanal(String chanal) {
        this.chanal = chanal;
    }
    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    @Override
    public String toString() {
        return "DwCpClearUpTotal{" +
            "id=" + id +
            ", platCode=" + platCode +
            ", platName=" + platName +
            ", areaCode=" + areaCode +
            ", areaName=" + areaName +
            ", accountsId=" + accountsId +
            ", accountsName=" + accountsName +
            ", dataDate=" + dataDate +
            ", subjectCode=" + subjectCode +
            ", subjectName=" + subjectName +
            ", ncBalance=" + ncBalance +
            ", remark=" + remark +
            ", sp=" + sp +
            ", etlTime=" + etlTime +
            ", instanceId=" + instanceId +
            ", lockStatus=" + lockStatus +
            ", updateStatus=" + updateStatus +
            ", startDate=" + startDate +
            ", chanal=" + chanal +
            ", quarter=" + quarter +
        "}";
    }
}
