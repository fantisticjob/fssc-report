package com.longfor.fsscreport.xycq.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * etl任务监控
 * </p>
 *
 * @author chenziyao
 * @since 2020-12-29
 */
@TableName("DW_ETL_TASK")
public class DwEtlTask implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableField("ID")
    private String id;

    /**
     * 日期
     */
    @TableField("DATA_DATE")
    private Date dataDate;

    /**
     * job名称
     */
    @TableField("JOB_NAME")
    private String jobName;

    /**
     * 模块名称
     */
    @TableField("MODULE_NAME")
    private String moduleName;

    /**
     * 开始时间
     */
    @TableField("START_DATE")
    private Date startDate;

    /**
     * 结束时间
     */
    @TableField("END_DATE")
    private Date endDate;

    /**
     * 异常信息
     */
    @TableField("EXCEPTION_DESC")
    private String exceptionDesc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public Date getDataDate() {
        return dataDate;
    }

    public void setDataDate(Date dataDate) {
        this.dataDate = dataDate;
    }
    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public String getExceptionDesc() {
        return exceptionDesc;
    }

    public void setExceptionDesc(String exceptionDesc) {
        this.exceptionDesc = exceptionDesc;
    }

    @Override
    public String toString() {
        return "DwEtlTask{" +
            "id=" + id +
            ", dataDate=" + dataDate +
            ", jobName=" + jobName +
            ", moduleName=" + moduleName +
            ", startDate=" + startDate +
            ", endDate=" + endDate +
            ", exceptionDesc=" + exceptionDesc +
        "}";
    }
}
