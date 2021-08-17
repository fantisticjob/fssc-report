package com.longfor.fsscreport.approval.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * <p>
 * 机器人调用任务表
 * </p>
 *
 * @author chenziyao
 * @since 2020-07-28
 */
@TableName("RPRT_TASK_YG")
public class RprtTaskYg implements Serializable {


    private static final long serialVersionUID = 1L;

    @TableId("DEALID")
    private String dealid;

    @TableField("OPRATER_FLAG")
    private String opraterFlag;

    @TableField("COMPANY_NAME")
    private String companyName;

    @TableField("ACCOUNT_ID")
    private String accountId;

    @TableField("YEARMONTH")
    private String yearmonth;

    @TableField("BEGINTIME")
    private Date begintime;

    @TableField("ENDTIME_IRA")
    private Date endtimeIra;

    @TableField("ENDTIME_FR")
    private Date endtimeFr;
    
    @TableField("FINISH_STATUS")
    private String finishStatus;
    
    @TableField("UPDATE_FLAG")
    private String updateFlag;
    
    @TableField("DETAILKEMU")
    private String detailKemu;
    
    public String getDetailKemu() {
		return detailKemu;
	}

	public void setDetailKemu(String detailKemu) {
		this.detailKemu = detailKemu;
	}

	public String getUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(String updateFlag) {
		this.updateFlag = updateFlag;
	}

	public String getDealid() {
		return dealid;
	}

	public void setDealid(String dealid) {
		this.dealid = dealid;
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

	public String getYearmonth() {
		return yearmonth;
	}

	public void setYearmonth(String yearmonth) {
		this.yearmonth = yearmonth;
	}

	public Date getBegintime() {
		return begintime;
	}

	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}

	public Date getEndtimeIra() {
		return endtimeIra;
	}

	public void setEndtimeIra(Date endtimeIra) {
		this.endtimeIra = endtimeIra;
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
}
