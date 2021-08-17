package com.longfor.fsscreport.clear.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 人员基本信息
 * </p>
 *
 * @author chenziyao
 * @since 2021-07-06
 */
@TableName("ODS_HR_EMPLOYEE")
@KeySequence("ODS_HR_EMPLOYEE_S")
public class OdsHrEmployee implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "ID", type = IdType.INPUT)
    private Long id;

    /**
     * 人员ID
     */
    @TableField("EMPLOYEE")
    private String employee;

    /**
     * OA账号
     */
    @TableField("OAACCOUNT")
    private String oaaccount;

    /**
     * 证件姓名
     */
    @TableField("IDNAME")
    private String idname;

    /**
     * 显示姓名
     */
    @TableField("NAME")
    private String name;

    /**
     * 性别
     */
    @TableField("GENDER")
    private String gender;

    /**
     * 证件类型
     */
    @TableField("IDTYPE")
    private String idtype;

    /**
     * 证件号
     */
    @TableField("IDNUM")
    private String idnum;

    /**
     * 个人电子邮件
     */
    @TableField("EMAIL")
    private String email;

    /**
     * 出生日期
     */
    @TableField("BIRTHDAY")
    private String birthday;

    /**
     * 手机号
     */
    @TableField("PHONE")
    private String phone;

    /**
     * HR状态(在职/离职)
     */
    @TableField("EMPSTATUS")
    private String empstatus;

    /**
     * 员工类型
     */
    @TableField("EMPTYPE")
    private String emptype;

    /**
     * 员工类型描述
     */
    @TableField("EMPTYDESC")
    private String emptydesc;

    /**
     * 公司代码（财务）
     */
    @TableField("BUKRS")
    private String bukrs;

    /**
     * 公司名称（财务）
     */
    @TableField("BUKRSNAME")
    private String bukrsname;

    /**
     * 费用类型描述
     */
    @TableField("COSTDESC")
    private String costdesc;

    /**
     * 商务头衔
     */
    @TableField("BUSTITLE")
    private String bustitle;

    /**
     * 入职日期
     */
    @TableField("HIREDATE")
    private String hiredate;

    /**
     * 离职日期
     */
    @TableField("TERMDATE")
    private String termdate;

    /**
     * 转正日期
     */
    @TableField("TURNDATE")
    private String turndate;

    /**
     * 实际工作地代码
     */
    @TableField("BASECODE")
    private String basecode;

    /**
     * 实际工作地描述
     */
    @TableField("BASEDESC")
    private String basedesc;

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
     * 证件类型描述
     */
    @TableField("IDTYPE_DS")
    private String idtypeDs;

    /**
     * 费用类型编码
     */
    @TableField("COSTID")
    private String costid;

    /**
     * 预算分摊部门编码
     */
    @TableField("BUDDEPID")
    private String buddepid;

    /**
     * 预算分摊部门描述
     */
    @TableField("BUDDEPDS")
    private String buddepds;

    /**
     * 费用承担主体编码
     */
    @TableField("COSTSUBID")
    private String costsubid;

    /**
     * 费用承担主体描述
     */
    @TableField("COSTSUBDS")
    private String costsubds;

    /**
     * 职级编码
     */
    @TableField("EMPL_RANK")
    private String emplRank;

    /**
     * 职级描述
     */
    @TableField("EMPLRANKD")
    private String emplrankd;

    /**
     * 职级序列编码
     */
    @TableField("EMPL_SEQ")
    private String emplSeq;

    /**
     * 职级序列描述
     */
    @TableField("EMPL_SEQD")
    private String emplSeqd;

    /**
     * UC_GUID
     */
    @TableField("UC_GUID")
    private String ucGuid;

    /**
     * 系统标识(区分系统)
     */
    @TableField("SYSFLAG")
    private String sysflag;

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
    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }
    public String getOaaccount() {
        return oaaccount;
    }

    public void setOaaccount(String oaaccount) {
        this.oaaccount = oaaccount;
    }
    public String getIdname() {
        return idname;
    }

    public void setIdname(String idname) {
        this.idname = idname;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getIdtype() {
        return idtype;
    }

    public void setIdtype(String idtype) {
        this.idtype = idtype;
    }
    public String getIdnum() {
        return idnum;
    }

    public void setIdnum(String idnum) {
        this.idnum = idnum;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmpstatus() {
        return empstatus;
    }

    public void setEmpstatus(String empstatus) {
        this.empstatus = empstatus;
    }
    public String getEmptype() {
        return emptype;
    }

    public void setEmptype(String emptype) {
        this.emptype = emptype;
    }
    public String getEmptydesc() {
        return emptydesc;
    }

    public void setEmptydesc(String emptydesc) {
        this.emptydesc = emptydesc;
    }
    public String getBukrs() {
        return bukrs;
    }

    public void setBukrs(String bukrs) {
        this.bukrs = bukrs;
    }
    public String getBukrsname() {
        return bukrsname;
    }

    public void setBukrsname(String bukrsname) {
        this.bukrsname = bukrsname;
    }
    public String getCostdesc() {
        return costdesc;
    }

    public void setCostdesc(String costdesc) {
        this.costdesc = costdesc;
    }
    public String getBustitle() {
        return bustitle;
    }

    public void setBustitle(String bustitle) {
        this.bustitle = bustitle;
    }
    public String getHiredate() {
        return hiredate;
    }

    public void setHiredate(String hiredate) {
        this.hiredate = hiredate;
    }
    public String getTermdate() {
        return termdate;
    }

    public void setTermdate(String termdate) {
        this.termdate = termdate;
    }
    public String getTurndate() {
        return turndate;
    }

    public void setTurndate(String turndate) {
        this.turndate = turndate;
    }
    public String getBasecode() {
        return basecode;
    }

    public void setBasecode(String basecode) {
        this.basecode = basecode;
    }
    public String getBasedesc() {
        return basedesc;
    }

    public void setBasedesc(String basedesc) {
        this.basedesc = basedesc;
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
    public String getIdtypeDs() {
        return idtypeDs;
    }

    public void setIdtypeDs(String idtypeDs) {
        this.idtypeDs = idtypeDs;
    }
    public String getCostid() {
        return costid;
    }

    public void setCostid(String costid) {
        this.costid = costid;
    }
    public String getBuddepid() {
        return buddepid;
    }

    public void setBuddepid(String buddepid) {
        this.buddepid = buddepid;
    }
    public String getBuddepds() {
        return buddepds;
    }

    public void setBuddepds(String buddepds) {
        this.buddepds = buddepds;
    }
    public String getCostsubid() {
        return costsubid;
    }

    public void setCostsubid(String costsubid) {
        this.costsubid = costsubid;
    }
    public String getCostsubds() {
        return costsubds;
    }

    public void setCostsubds(String costsubds) {
        this.costsubds = costsubds;
    }
    public String getEmplRank() {
        return emplRank;
    }

    public void setEmplRank(String emplRank) {
        this.emplRank = emplRank;
    }
    public String getEmplrankd() {
        return emplrankd;
    }

    public void setEmplrankd(String emplrankd) {
        this.emplrankd = emplrankd;
    }
    public String getEmplSeq() {
        return emplSeq;
    }

    public void setEmplSeq(String emplSeq) {
        this.emplSeq = emplSeq;
    }
    public String getEmplSeqd() {
        return emplSeqd;
    }

    public void setEmplSeqd(String emplSeqd) {
        this.emplSeqd = emplSeqd;
    }
    public String getUcGuid() {
        return ucGuid;
    }

    public void setUcGuid(String ucGuid) {
        this.ucGuid = ucGuid;
    }
    public String getSysflag() {
        return sysflag;
    }

    public void setSysflag(String sysflag) {
        this.sysflag = sysflag;
    }
    public LocalDateTime getEtlTime() {
        return etlTime;
    }

    public void setEtlTime(LocalDateTime etlTime) {
        this.etlTime = etlTime;
    }

    @Override
    public String toString() {
        return "OdsHrEmployee{" +
            "id=" + id +
            ", employee=" + employee +
            ", oaaccount=" + oaaccount +
            ", idname=" + idname +
            ", name=" + name +
            ", gender=" + gender +
            ", idtype=" + idtype +
            ", idnum=" + idnum +
            ", email=" + email +
            ", birthday=" + birthday +
            ", phone=" + phone +
            ", empstatus=" + empstatus +
            ", emptype=" + emptype +
            ", emptydesc=" + emptydesc +
            ", bukrs=" + bukrs +
            ", bukrsname=" + bukrsname +
            ", costdesc=" + costdesc +
            ", bustitle=" + bustitle +
            ", hiredate=" + hiredate +
            ", termdate=" + termdate +
            ", turndate=" + turndate +
            ", basecode=" + basecode +
            ", basedesc=" + basedesc +
            ", createdat=" + createdat +
            ", createby=" + createby +
            ", updatedat=" + updatedat +
            ", updateby=" + updateby +
            ", idtypeDs=" + idtypeDs +
            ", costid=" + costid +
            ", buddepid=" + buddepid +
            ", buddepds=" + buddepds +
            ", costsubid=" + costsubid +
            ", costsubds=" + costsubds +
            ", emplRank=" + emplRank +
            ", emplrankd=" + emplrankd +
            ", emplSeq=" + emplSeq +
            ", emplSeqd=" + emplSeqd +
            ", ucGuid=" + ucGuid +
            ", sysflag=" + sysflag +
            ", etlTime=" + etlTime +
        "}";
    }
}
