package com.longfor.fsscreport.approval.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 重点税源审批公司与财务经理映射信息表
 * </p>
 *
 * @author chenziyao
 * @since 2021-05-18
 */
@TableName("DW_ZDSY_OA_ACCOUNTS")
public class DwZdsyOaAccounts implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 公司CODE
     */
    @TableField("ACCOUNTS_ID")
    private String accountsId;

    /**
     * 公司名称
     */
    @TableField("ORG_NAME")
    private String orgName;

    /**
     * 财务经理OA号
     */
    @TableField("OA_NAME")
    private String oaName;

    /**
     * 入库时间
     */
    @TableField("ETL_TIME")
    private Date etlTime;

    public String getAccountsId() {
        return accountsId;
    }

    public void setAccountsId(String accountsId) {
        this.accountsId = accountsId;
    }
    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
    public String getOaName() {
        return oaName;
    }

    public void setOaName(String oaName) {
        this.oaName = oaName;
    }
    public Date getEtlTime() {
        return etlTime;
    }

    public void setEtlTime(Date etlTime) {
        this.etlTime = etlTime;
    }

    @Override
    public String toString() {
        return "DwZdsyOaAccounts{" +
            "accountsId=" + accountsId +
            ", orgName=" + orgName +
            ", oaName=" + oaName +
            ", etlTime=" + etlTime +
        "}";
    }
}
