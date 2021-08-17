package com.longfor.fsscreport.nc.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * nc任务表
 * </p>
 *
 * @author chenziyao
 * @since 2020-11-16
 */
@TableName("NC_TASK")
public class NcTask implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 处理ID
     */
    @TableField("BATCHNUMBER")
    private String batchnumber;

    /**
     * 处理标识符 1：自动  2：手动
     */
    @TableField("OPRATER_FLAG")
    private String opraterFlag;

    /**
     * 账套ID
     */
    @TableField("COMPANY_NAME")
    private String companyName;

    /**
     * 账户ID
     */
    @TableField("ACCOUNT_ID")
    private String accountId;

    /**
     * 开始年月
     */
    @TableField("YEARMONTH_BEGIN")
    private String yearmonthBegin;

    /**
     * 结束年月
     */
    @TableField("YEARMONTH_END")
    private String yearmonthEnd;

    /**
     * 开始时间
     */
    @TableField("BEGINTIME")
    private Date begintime;

    /**
     * NC处理完成时间
     */
    @TableField("ENDTIME_NC")
    private Date endtimeNc;

    /**
     * 帆软处理完成时间
     */
    @TableField("ENDTIME_FR")
    private Date endtimeFr;

    /**
     * 1: 正常 2：异常
     */
    @TableField("FINISH_STATUS")
    private String finishStatus;
    
    /**
     * 季度
     */
    @TableField("QUARTER")
    private String quarter;
    
    public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}

	public String getBatchnumber() {
        return batchnumber;
    }

    public void setBatchnumber(String batchnumber) {
        this.batchnumber = batchnumber;
    }
    public String getOpraterFlag() {
        return opraterFlag;
    }

    public void setOpraterFlag(String opraterFlag) {
        this.opraterFlag = opraterFlag;
    }
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    public String getYearmonthBegin() {
        return yearmonthBegin;
    }

    public void setYearmonthBegin(String yearmonthBegin) {
        this.yearmonthBegin = yearmonthBegin;
    }
    public String getYearmonthEnd() {
        return yearmonthEnd;
    }

    public void setYearmonthEnd(String yearmonthEnd) {
        this.yearmonthEnd = yearmonthEnd;
    }
    public Date getBegintime() {
        return begintime;
    }

    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }
    public Date getEndtimeNc() {
        return endtimeNc;
    }

    public void setEndtimeNc(Date endtimeNc) {
        this.endtimeNc = endtimeNc;
    }
    public Date getEndtimeFr() {
        return endtimeFr;
    }

    public void setEndtimeFr(Date endtimeFr) {
        this.endtimeFr = endtimeFr;
    }
    public String getFinishStatus() {
        return finishStatus;
    }

    public void setFinishStatus(String finishStatus) {
        this.finishStatus = finishStatus;
    }

    @Override
    public String toString() {
        return "NcTask{" +
            "batchnumber=" + batchnumber +
            ", opraterFlag=" + opraterFlag +
            ", companyName=" + companyName +
            ", accountId=" + accountId +
            ", yearmonthBegin=" + yearmonthBegin +
            ", yearmonthEnd=" + yearmonthEnd +
            ", begintime=" + begintime +
            ", endtimeNc=" + endtimeNc +
            ", endtimeFr=" + endtimeFr +
            ", finishStatus=" + finishStatus +
        "}";
    }
}
