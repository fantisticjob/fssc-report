package com.longfor.fsscreport.approval.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 审批提交OA账号实体类
 * </p>
 *
 * @author chenziyao
 * @since 2020-11-12
 */
@TableName("DW_CP_OA_ACCOUNTS")
public class DwCpOaAccounts implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账套id
     */
    @TableId("ACCOUNTS_ID")
    private String accountsId;

    /**
     * OA账号
     */
    @TableField("OA_NAME")
    private String oaName;

    /**
     * etl时间
     */
    @TableField("ETL_TIME")
    private Date etlTime;

    public String getAccountsId() {
        return accountsId;
    }

    public void setAccountsId(String accountsId) {
        this.accountsId = accountsId;
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
}
