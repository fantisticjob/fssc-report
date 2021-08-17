package com.longfor.fsscreport.clear.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 组织架构信息
 * </p>
 *
 * @author chenziyao
 * @since 2021-07-06
 */
@TableName("ODS_HR_ORG")
@KeySequence("ODS_HR_ORG_S")
public class OdsHrOrg implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "ID", type = IdType.INPUT)
    private Long id;

    /**
     * 组架版本ID
     */
    @TableField("VERSIONIF")
    private String versionif;

    /**
     * 组织ID
     */
    @TableField("ORG_ID")
    private String orgId;

    /**
     * 组织形态
     */
    @TableField("ORG_TYPE")
    private String orgType;

    /**
     * 组织名称
     */
    @TableField("ORG_NM")
    private String orgNm;

    /**
     * 组织简称
     */
    @TableField("ORG_SN")
    private String orgSn;

    /**
     * 部门类型ID
     */
    @TableField("DEPTY_ID")
    private String deptyId;

    /**
     * 部门类型描述
     */
    @TableField("DEPTY_DS")
    private String deptyDs;

    /**
     * 部门类别ID
     */
    @TableField("DEPCL_ID")
    private String depclId;

    /**
     * 部门类别描述
     */
    @TableField("DEPCL_DS")
    private String depclDs;

    /**
     * 父节点ID
     */
    @TableField("PARENTID")
    private String parentid;

    /**
     * 所属公司
     */
    @TableField("COMP_ID")
    private String compId;

    /**
     * 公司描述
     */
    @TableField("COMP_DS")
    private String compDs;

    /**
     * 生效日期
     */
    @TableField("ACT_DATE")
    private String actDate;

    /**
     * 状态
     */
    @TableField("STATUS")
    private String status;

    /**
     * 组织标签ID
     */
    @TableField("ORGLB_ID")
    private String orglbId;

    /**
     * 组织标签描述
     */
    @TableField("ORGLB_DS")
    private String orglbDs;

    /**
     * 组织层级
     */
    @TableField("ORG_HRC")
    private String orgHrc;

    /**
     * 组织负责人
     */
    @TableField("ORG_DIRCT")
    private String orgDirct;

    /**
     * 组织排序
     */
    @TableField("ORG_RANK")
    private String orgRank;

    /**
     * 城市
     */
    @TableField("CITY")
    private String city;

    /**
     * 成本中心
     */
    @TableField("COST_CT")
    private String costCt;

    /**
     * 接管类型
     */
    @TableField("TAKEO_TY")
    private String takeoTy;

    /**
     * 物业业态
     */
    @TableField("PROP_FORM")
    private String propForm;

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
     * 组织层级描述
     */
    @TableField("ORG_HRCDS")
    private String orgHrcds;

    /**
     * 组织负责人名称
     */
    @TableField("ORGDIRCTD")
    private String orgdirctd;

    /**
     * 城市描述
     */
    @TableField("CITY_DS")
    private String cityDs;

    /**
     * 成本中心描述
     */
    @TableField("COST_CTDS")
    private String costCtds;

    /**
     * 接管类型描述
     */
    @TableField("TAKEOTYDS")
    private String takeotyds;

    /**
     * 物业业态描述
     */
    @TableField("PROPFORMD")
    private String propformd;

    /**
     * UC GUID
     */
    @TableField("UC_GUID")
    private String ucGuid;

    /**
     * 父节点描述
     */
    @TableField("PARENT_DS")
    private String parentDs;

    /**
     * 龙信是否显示
     */
    @TableField("IF_L_SHOW")
    private String ifLShow;

    /**
     * 投资属性
     */
    @TableField("INV_ATTR")
    private String invAttr;

    /**
     * 投资属性名称
     */
    @TableField("INVATTRNM")
    private String invattrnm;

    /**
     * 织负责人岗位编码
     */
    @TableField("POSCODEUP")
    private String poscodeup;

    /**
     * 系统标识
     */
    @TableField("SYSFLAG")
    private String sysflag;

    /**
     * 项目信息
     */
    @TableField("PRJLIST")
    private String prjlist;

    /**
     * MDM项目（项目身份证）
     */
    @TableField("PROJECT")
    private String project;

    /**
     * MDM项目名称
     */
    @TableField("PROJECTDS")
    private String projectds;

    /**
     * 组织状态标识
     */
    @TableField("PRJSTATUS")
    private String prjstatus;

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
    public String getVersionif() {
        return versionif;
    }

    public void setVersionif(String versionif) {
        this.versionif = versionif;
    }
    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }
    public String getOrgNm() {
        return orgNm;
    }

    public void setOrgNm(String orgNm) {
        this.orgNm = orgNm;
    }
    public String getOrgSn() {
        return orgSn;
    }

    public void setOrgSn(String orgSn) {
        this.orgSn = orgSn;
    }
    public String getDeptyId() {
        return deptyId;
    }

    public void setDeptyId(String deptyId) {
        this.deptyId = deptyId;
    }
    public String getDeptyDs() {
        return deptyDs;
    }

    public void setDeptyDs(String deptyDs) {
        this.deptyDs = deptyDs;
    }
    public String getDepclId() {
        return depclId;
    }

    public void setDepclId(String depclId) {
        this.depclId = depclId;
    }
    public String getDepclDs() {
        return depclDs;
    }

    public void setDepclDs(String depclDs) {
        this.depclDs = depclDs;
    }
    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }
    public String getCompId() {
        return compId;
    }

    public void setCompId(String compId) {
        this.compId = compId;
    }
    public String getCompDs() {
        return compDs;
    }

    public void setCompDs(String compDs) {
        this.compDs = compDs;
    }
    public String getActDate() {
        return actDate;
    }

    public void setActDate(String actDate) {
        this.actDate = actDate;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getOrglbId() {
        return orglbId;
    }

    public void setOrglbId(String orglbId) {
        this.orglbId = orglbId;
    }
    public String getOrglbDs() {
        return orglbDs;
    }

    public void setOrglbDs(String orglbDs) {
        this.orglbDs = orglbDs;
    }
    public String getOrgHrc() {
        return orgHrc;
    }

    public void setOrgHrc(String orgHrc) {
        this.orgHrc = orgHrc;
    }
    public String getOrgDirct() {
        return orgDirct;
    }

    public void setOrgDirct(String orgDirct) {
        this.orgDirct = orgDirct;
    }
    public String getOrgRank() {
        return orgRank;
    }

    public void setOrgRank(String orgRank) {
        this.orgRank = orgRank;
    }
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    public String getCostCt() {
        return costCt;
    }

    public void setCostCt(String costCt) {
        this.costCt = costCt;
    }
    public String getTakeoTy() {
        return takeoTy;
    }

    public void setTakeoTy(String takeoTy) {
        this.takeoTy = takeoTy;
    }
    public String getPropForm() {
        return propForm;
    }

    public void setPropForm(String propForm) {
        this.propForm = propForm;
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
    public String getOrgHrcds() {
        return orgHrcds;
    }

    public void setOrgHrcds(String orgHrcds) {
        this.orgHrcds = orgHrcds;
    }
    public String getOrgdirctd() {
        return orgdirctd;
    }

    public void setOrgdirctd(String orgdirctd) {
        this.orgdirctd = orgdirctd;
    }
    public String getCityDs() {
        return cityDs;
    }

    public void setCityDs(String cityDs) {
        this.cityDs = cityDs;
    }
    public String getCostCtds() {
        return costCtds;
    }

    public void setCostCtds(String costCtds) {
        this.costCtds = costCtds;
    }
    public String getTakeotyds() {
        return takeotyds;
    }

    public void setTakeotyds(String takeotyds) {
        this.takeotyds = takeotyds;
    }
    public String getPropformd() {
        return propformd;
    }

    public void setPropformd(String propformd) {
        this.propformd = propformd;
    }
    public String getUcGuid() {
        return ucGuid;
    }

    public void setUcGuid(String ucGuid) {
        this.ucGuid = ucGuid;
    }
    public String getParentDs() {
        return parentDs;
    }

    public void setParentDs(String parentDs) {
        this.parentDs = parentDs;
    }
    public String getIfLShow() {
        return ifLShow;
    }

    public void setIfLShow(String ifLShow) {
        this.ifLShow = ifLShow;
    }
    public String getInvAttr() {
        return invAttr;
    }

    public void setInvAttr(String invAttr) {
        this.invAttr = invAttr;
    }
    public String getInvattrnm() {
        return invattrnm;
    }

    public void setInvattrnm(String invattrnm) {
        this.invattrnm = invattrnm;
    }
    public String getPoscodeup() {
        return poscodeup;
    }

    public void setPoscodeup(String poscodeup) {
        this.poscodeup = poscodeup;
    }
    public String getSysflag() {
        return sysflag;
    }

    public void setSysflag(String sysflag) {
        this.sysflag = sysflag;
    }
    public String getPrjlist() {
        return prjlist;
    }

    public void setPrjlist(String prjlist) {
        this.prjlist = prjlist;
    }
    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
    public String getProjectds() {
        return projectds;
    }

    public void setProjectds(String projectds) {
        this.projectds = projectds;
    }
    public String getPrjstatus() {
        return prjstatus;
    }

    public void setPrjstatus(String prjstatus) {
        this.prjstatus = prjstatus;
    }
    public LocalDateTime getEtlTime() {
        return etlTime;
    }

    public void setEtlTime(LocalDateTime etlTime) {
        this.etlTime = etlTime;
    }

    @Override
    public String toString() {
        return "OdsHrOrg{" +
            "id=" + id +
            ", versionif=" + versionif +
            ", orgId=" + orgId +
            ", orgType=" + orgType +
            ", orgNm=" + orgNm +
            ", orgSn=" + orgSn +
            ", deptyId=" + deptyId +
            ", deptyDs=" + deptyDs +
            ", depclId=" + depclId +
            ", depclDs=" + depclDs +
            ", parentid=" + parentid +
            ", compId=" + compId +
            ", compDs=" + compDs +
            ", actDate=" + actDate +
            ", status=" + status +
            ", orglbId=" + orglbId +
            ", orglbDs=" + orglbDs +
            ", orgHrc=" + orgHrc +
            ", orgDirct=" + orgDirct +
            ", orgRank=" + orgRank +
            ", city=" + city +
            ", costCt=" + costCt +
            ", takeoTy=" + takeoTy +
            ", propForm=" + propForm +
            ", createdat=" + createdat +
            ", createby=" + createby +
            ", updatedat=" + updatedat +
            ", updateby=" + updateby +
            ", orgHrcds=" + orgHrcds +
            ", orgdirctd=" + orgdirctd +
            ", cityDs=" + cityDs +
            ", costCtds=" + costCtds +
            ", takeotyds=" + takeotyds +
            ", propformd=" + propformd +
            ", ucGuid=" + ucGuid +
            ", parentDs=" + parentDs +
            ", ifLShow=" + ifLShow +
            ", invAttr=" + invAttr +
            ", invattrnm=" + invattrnm +
            ", poscodeup=" + poscodeup +
            ", sysflag=" + sysflag +
            ", prjlist=" + prjlist +
            ", project=" + project +
            ", projectds=" + projectds +
            ", prjstatus=" + prjstatus +
            ", etlTime=" + etlTime +
        "}";
    }
}
