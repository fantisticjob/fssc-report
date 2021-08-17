package com.longfor.fsscreport.clear.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 往来清理-数据校验表
 * </p>
 *
 * @author chenziyao
 * @since 2020-11-13
 */
@TableName("DW_CP_DATA_CHECK")
@KeySequence("dw_cp_data_check_s")
public class DwCpDataCheck implements Serializable {


	
    private static final long serialVersionUID = 1L;

    /**
     * 编码
     */
    @TableId(value = "ID", type = IdType.INPUT)
    private Integer id;

    /**
     * 账套id
     */
    @TableField("ACCOUNTS_ID")
    private String accountsId;

    /**
     * 科目code
     */
    @TableField("SUBJECT_CODE")
    private String subjectCode;

    /**
     * 季度
     */
    @TableField("QUARTER")
    private String quarter;

    /**
     * 校验类别
     */
    @TableField("CHECK_TYPE")
    private String checkType;

    /**
     * 校验结果
     */
    @TableField("CHECK_RESULT")
    private String checkResult;

    /**
     * 备注
     */
    @TableField("COMMENTS")
    private String comments;

    /**
     * etl时间
     */
    @TableField("ETL_TIME")
    private Date etlTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getAccountsId() {
        return accountsId;
    }

    public void setAccountsId(String accountsId) {
        this.accountsId = accountsId;
    }
    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }
    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }
    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }
    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    public Date getEtlTime() {
        return etlTime;
    }

    public void setEtlTime(Date etlTime) {
        this.etlTime = etlTime;
    }

}
