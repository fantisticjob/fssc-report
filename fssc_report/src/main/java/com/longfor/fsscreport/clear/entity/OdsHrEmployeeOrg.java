package com.longfor.fsscreport.clear.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 人-岗信息
 * </p>
 *
 * @author chenziyao
 * @since 2021-07-06
 */
@TableName("ODS_HR_EMPLOYEE_ORG")
@KeySequence("ODS_HR_EMPLOYEE_ORG_S")
public class OdsHrEmployeeOrg implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "ID", type = IdType.INPUT)
    private Long id;

    /**
     * 人员ID
     */
    @TableField("EMPLOYEEP")
    private String employeep;

    /**
     * 主岗(0)/兼岗(1.2.3...)
     */
    @TableField("CROSSPOS")
    private String crosspos;

    /**
     * 关键人事管理者
     */
    @TableField("KEYLEADER")
    private String keyleader;

    /**
     * 组织负责人
     */
    @TableField("ORGOWNER")
    private String orgowner;

    /**
     * 航道代码
     */
    @TableField("CHANNEL")
    private String channel;

    /**
     * 航道名称
     */
    @TableField("CHANNELNM")
    private String channelnm;

    /**
     * 状态（A有效 I无效）
     */
    @TableField("STATUS")
    private String status;

    /**
     * 岗位代码
     */
    @TableField("POSCODE")
    private String poscode;

    /**
     * 岗位名称
     */
    @TableField("POSNAME")
    private String posname;

    /**
     * 岗位简称
     */
    @TableField("POSDESC")
    private String posdesc;

    /**
     * 地点
     */
    @TableField("LOCATION")
    private String location;

    /**
     * 地点描述
     */
    @TableField("LOCADESC")
    private String locadesc;

    /**
     * 职务代码
     */
    @TableField("JOBCODE")
    private String jobcode;

    /**
     * 职务名称
     */
    @TableField("JOBNAME")
    private String jobname;

    /**
     * 职务简称
     */
    @TableField("JOBDESC")
    private String jobdesc;

    /**
     * 职务层级ID
     */
    @TableField("JOBID")
    private String jobid;

    /**
     * 职务层级描述
     */
    @TableField("JOBIDDESC")
    private String jobiddesc;

    /**
     * 一级职能ID
     */
    @TableField("FIRSTID")
    private String firstid;

    /**
     * 一级职能描述
     */
    @TableField("FIRSTDES")
    private String firstdes;

    /**
     * 二级职能ID
     */
    @TableField("SECONDID")
    private String secondid;

    /**
     * 二级职能描述
     */
    @TableField("SECONDDES")
    private String seconddes;

    /**
     * 三级职能ID
     */
    @TableField("THIRDID")
    private String thirdid;

    /**
     * 三级职能描述
     */
    @TableField("THIRDDES")
    private String thirddes;

    /**
     * 所属组织ID
     */
    @TableField("ORGID")
    private String orgid;

    /**
     * 创建时间
     */
    @TableField("CREATEDAT")
    private String createdat;

    /**
     * 创建人OA账号
     */
    @TableField("CREATEBY")
    private String createby;

    /**
     * 修改时间
     */
    @TableField("UPDATEDAT")
    private String updatedat;

    /**
     * 修改人OA账号
     */
    @TableField("UPDATEBY")
    private String updateby;

    /**
     * 关键人事管理者名称
     */
    @TableField("KEYLEADDS")
    private String keyleadds;

    /**
     * 组织负责人名称
     */
    @TableField("ORGOWNERD")
    private String orgownerd;

    /**
     * 系统标识(区分系统 DC=自有人员 ST=生态 WU=双湖)
     */
    @TableField("SYSFLAG")
    private String sysflag;

    /**
     * 织负责人岗位编码
     */
    @TableField("POSCODEUP")
    private String poscodeup;

    /**
     * 员工公司
     */
    @TableField("EMP_COMP")
    private String empComp;

    /**
     * 一级部门ID
     */
    @TableField("DEPID_FI")
    private String depidFi;

    /**
     * 二级部门ID
     */
    @TableField("DEPID_SE")
    private String depidSe;

    /**
     * 异动生效日期
     */
    @TableField("CH_ACTDT")
    private String chActdt;

    /**
     * etl时间
     */
    @TableField("ETL_TIME")
    private LocalDateTime etlTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getEmployeep() {
        return employeep;
    }

    public void setEmployeep(String employeep) {
        this.employeep = employeep;
    }
    public String getCrosspos() {
        return crosspos;
    }

    public void setCrosspos(String crosspos) {
        this.crosspos = crosspos;
    }
    public String getKeyleader() {
        return keyleader;
    }

    public void setKeyleader(String keyleader) {
        this.keyleader = keyleader;
    }
    public String getOrgowner() {
        return orgowner;
    }

    public void setOrgowner(String orgowner) {
        this.orgowner = orgowner;
    }
    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
    public String getChannelnm() {
        return channelnm;
    }

    public void setChannelnm(String channelnm) {
        this.channelnm = channelnm;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getPoscode() {
        return poscode;
    }

    public void setPoscode(String poscode) {
        this.poscode = poscode;
    }
    public String getPosname() {
        return posname;
    }

    public void setPosname(String posname) {
        this.posname = posname;
    }
    public String getPosdesc() {
        return posdesc;
    }

    public void setPosdesc(String posdesc) {
        this.posdesc = posdesc;
    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public String getLocadesc() {
        return locadesc;
    }

    public void setLocadesc(String locadesc) {
        this.locadesc = locadesc;
    }
    public String getJobcode() {
        return jobcode;
    }

    public void setJobcode(String jobcode) {
        this.jobcode = jobcode;
    }
    public String getJobname() {
        return jobname;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname;
    }
    public String getJobdesc() {
        return jobdesc;
    }

    public void setJobdesc(String jobdesc) {
        this.jobdesc = jobdesc;
    }
    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
        this.jobid = jobid;
    }
    public String getJobiddesc() {
        return jobiddesc;
    }

    public void setJobiddesc(String jobiddesc) {
        this.jobiddesc = jobiddesc;
    }
    public String getFirstid() {
        return firstid;
    }

    public void setFirstid(String firstid) {
        this.firstid = firstid;
    }
    public String getFirstdes() {
        return firstdes;
    }

    public void setFirstdes(String firstdes) {
        this.firstdes = firstdes;
    }
    public String getSecondid() {
        return secondid;
    }

    public void setSecondid(String secondid) {
        this.secondid = secondid;
    }
    public String getSeconddes() {
        return seconddes;
    }

    public void setSeconddes(String seconddes) {
        this.seconddes = seconddes;
    }
    public String getThirdid() {
        return thirdid;
    }

    public void setThirdid(String thirdid) {
        this.thirdid = thirdid;
    }
    public String getThirddes() {
        return thirddes;
    }

    public void setThirddes(String thirddes) {
        this.thirddes = thirddes;
    }
    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }
    public String getCreatedat() {
        return createdat;
    }

    public void setCreatedat(String createdat) {
        this.createdat = createdat;
    }
    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby;
    }
    public String getUpdatedat() {
        return updatedat;
    }

    public void setUpdatedat(String updatedat) {
        this.updatedat = updatedat;
    }
    public String getUpdateby() {
        return updateby;
    }

    public void setUpdateby(String updateby) {
        this.updateby = updateby;
    }
    public String getKeyleadds() {
        return keyleadds;
    }

    public void setKeyleadds(String keyleadds) {
        this.keyleadds = keyleadds;
    }
    public String getOrgownerd() {
        return orgownerd;
    }

    public void setOrgownerd(String orgownerd) {
        this.orgownerd = orgownerd;
    }
    public String getSysflag() {
        return sysflag;
    }

    public void setSysflag(String sysflag) {
        this.sysflag = sysflag;
    }
    public String getPoscodeup() {
        return poscodeup;
    }

    public void setPoscodeup(String poscodeup) {
        this.poscodeup = poscodeup;
    }
    public String getEmpComp() {
        return empComp;
    }

    public void setEmpComp(String empComp) {
        this.empComp = empComp;
    }
    public String getDepidFi() {
        return depidFi;
    }

    public void setDepidFi(String depidFi) {
        this.depidFi = depidFi;
    }
    public String getDepidSe() {
        return depidSe;
    }

    public void setDepidSe(String depidSe) {
        this.depidSe = depidSe;
    }
    public String getChActdt() {
        return chActdt;
    }

    public void setChActdt(String chActdt) {
        this.chActdt = chActdt;
    }
    public LocalDateTime getEtlTime() {
        return etlTime;
    }

    public void setEtlTime(LocalDateTime etlTime) {
        this.etlTime = etlTime;
    }

    @Override
    public String toString() {
        return "OdsHrEmployeeOrg{" +
            "id=" + id +
            ", employeep=" + employeep +
            ", crosspos=" + crosspos +
            ", keyleader=" + keyleader +
            ", orgowner=" + orgowner +
            ", channel=" + channel +
            ", channelnm=" + channelnm +
            ", status=" + status +
            ", poscode=" + poscode +
            ", posname=" + posname +
            ", posdesc=" + posdesc +
            ", location=" + location +
            ", locadesc=" + locadesc +
            ", jobcode=" + jobcode +
            ", jobname=" + jobname +
            ", jobdesc=" + jobdesc +
            ", jobid=" + jobid +
            ", jobiddesc=" + jobiddesc +
            ", firstid=" + firstid +
            ", firstdes=" + firstdes +
            ", secondid=" + secondid +
            ", seconddes=" + seconddes +
            ", thirdid=" + thirdid +
            ", thirddes=" + thirddes +
            ", orgid=" + orgid +
            ", createdat=" + createdat +
            ", createby=" + createby +
            ", updatedat=" + updatedat +
            ", updateby=" + updateby +
            ", keyleadds=" + keyleadds +
            ", orgownerd=" + orgownerd +
            ", sysflag=" + sysflag +
            ", poscodeup=" + poscodeup +
            ", empComp=" + empComp +
            ", depidFi=" + depidFi +
            ", depidSe=" + depidSe +
            ", chActdt=" + chActdt +
            ", etlTime=" + etlTime +
        "}";
    }
}
