package com.longfor.fsscreport.approval.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 月度关账清单实体类
 * </p>
 *
 * @author chenziyao
 * @since 2020-07-11
 */
@TableName("DW_CA_LIST_OF_MONTH")
public class DwCaListOfMonth implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private Integer id;

    @TableField("AREA")
    private String area;

    @TableField("COMPANY")
    private String company;

    @TableField("CLOS_STATUS")
    private String closStatus;

    @TableField("YZ")
    private String yz;

    @TableField("APPV_STATUS")
    private String appvStatus;

    @TableField("DATA_DATE")
    private Date dataDate;

    @TableField("ETL_TIME")
    private Date etlTime;

    @TableField("UPDATE_BY")
    private String updateBy;

    @TableField("UPDATE_DATE")
    private Date updateDate;

    @TableField("FLAG")
    private String flag;

    /**
     * 流程
     */
    @TableField("INSTANCEID")
    private String instanceid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
    public String getClosStatus() {
        return closStatus;
    }

    public void setClosStatus(String closStatus) {
        this.closStatus = closStatus;
    }
    public String getYz() {
        return yz;
    }

    public void setYz(String yz) {
        this.yz = yz;
    }
    public String getAppvStatus() {
        return appvStatus;
    }

    public void setAppvStatus(String appvStatus) {
        this.appvStatus = appvStatus;
    }
    public Date getDataDate() {
        return dataDate;
    }

    public void setDataDate(Date dataDate) {
        this.dataDate = dataDate;
    }
    public Date getEtlTime() {
        return etlTime;
    }

    public void setEtlTime(Date etlTime) {
        this.etlTime = etlTime;
    }
    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
    public String getInstanceid() {
        return instanceid;
    }

    public void setInstanceid(String instanceid) {
        this.instanceid = instanceid;
    }

    @Override
    public String toString() {
        return "DwCaListOfMonth{" +
            "id=" + id +
            ", area=" + area +
            ", company=" + company +
            ", closStatus=" + closStatus +
            ", yz=" + yz +
            ", appvStatus=" + appvStatus +
            ", dataDate=" + dataDate +
            ", etlTime=" + etlTime +
            ", updateBy=" + updateBy +
            ", updateDate=" + updateDate +
            ", flag=" + flag +
            ", instanceid=" + instanceid +
        "}";
    }
}
