package com.longfor.fsscreport.approval.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 资金自查帐户明细实体类
 * </p>
 *
 * @author chenziyao
 * @since 2020-11-11
 */
@TableName("DW_FSI_ACCOUNT_DETAIL")
@KeySequence("DW_FSI_ACCOUNT_DETAIL_S")
public class DwFsiAccountDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编码
     */
    @TableId("URID")
    private Integer urid;

    /**
     * 年
     */
    @TableField("YEAR")
    private String year;

    /**
     * 季度
     */
    @TableField("JIDU")
    private String jidu;

    /**
     * 平台
     */
    @TableField("PLAT_NAME")
    private String platName;

    /**
     * 平台CODE
     */
    @TableField("PLAT_CODE")
    private String platCode;

    /**
     * 地区
     */
    @TableField("AREA_NAME")
    private String areaName;

    /**
     * 地区CODE
     */
    @TableField("AREA_CODE")
    private String areaCode;

    /**
     * 板块归属
     */
    @TableField("BLOCK")
    private String block;

    /**
     * 组织
     */
    @TableField("ORGID_NAME")
    private String orgidName;

    /**
     * 组织CODE
     */
    @TableField("ORGID_CODE")
    private String orgidCode;

    /**
     * 航道
     */
    @TableField("CHNNAL")
    private String chnnal;

    /**
     * 银行账户
     */
    @TableField("ACCOUNTNUMBER")
    private String accountnumber;

    /**
     * 账户名称
     */
    @TableField("ACCOUNT_NAME")
    private String accountName;

    /**
     * 开户银行
     */
    @TableField("BANKLOCATIONID")
    private String banklocationid;

    /**
     * 银行
     */
    @TableField("BANK")
    private String bank;

    /**
     * 是否直联
     */
    @TableField("ISBANKDIRECT")
    private String isbankdirect;

    /**
     * 账户性质
     */
    @TableField("ACCOUNT_XZ")
    private String accountXz;

    /**
     * 前6个月流水条数
     */
    @TableField("LS_NUMBER")
    private Long lsNumber;

    /**
     * 预计销户时间
     */
    @TableField("YJ_XH_TIME")
    private Date yjXhTime;

    /**
     * 不可销户原因
     */
    @TableField("BK_XH_RESEON")
    private String bkXhReseon;

    /**
     * 账户状态
     */
    @TableField("ACOUUNT_STATUS")
    private String acouuntStatus;

    /**
     * 协定存款办理进度
     */
    @TableField("XY_CK_JD")
    private String xyCkJd;

    /**
     * 不可办理协定存款原因
     */
    @TableField("XY_CK_BK_RESEON")
    private String xyCkBkReseon;

    /**
     * 协定存款利率（%）
     */
    @TableField("XY_CK_RATE")
    private String xyCkRate;

    /**
     * 结息方式
     */
    @TableField("LX_PAY_TYPE")
    private String lxPayType;

    /**
     * 资金平台开户申请审批完成日期
     */
    @TableField("ZJ_KH_SP_END_TIME")
    private Date zjKhSpEndTime;

    /**
     * 资金平台开户启用日期
     */
    @TableField("ZJ_PLAT_KH_START_TIME")
    private Date zjPlatKhStartTime;

    /**
     * 开户启用日期的操作时间
     */
    @TableField("KH_QY_OP_TIME")
    private Date khQyOpTime;

    /**
     * 开户流程合规自查
     */
    @TableField("KH_ZC")
    private String khZc;

    /**
     * 资金平台销户申请审批完成日期
     */
    @TableField("ZJ_XH_SP_END_TIME")
    private Date zjXhSpEndTime;

    /**
     * 资金平台销户日期
     */
    @TableField("ZJ_PLAT_XH_TIME")
    private Date zjPlatXhTime;

    /**
     * 录入销户日期的操作时间
     */
    @TableField("XH_OP_TIME")
    private Date xhOpTime;

    /**
     * 消户流程合规自查
     */
    @TableField("XH_ZC")
    private String xhZc;

    /**
     * 前6个月末余额均值
     */
    @TableField("AVE_NUMBER")
    private BigDecimal aveNumber;

    /**
     * 年月
     */
    @TableField("DATA_DATE")
    private String dataDate;

    /**
     * 账户余额
     */
    @TableField("CLOSINGBALANCE")
    private BigDecimal closingbalance;

    /**
     * 币种
     */
    @TableField("CURRENCY")
    private String currency;

    /**
     * etl时间
     */
    @TableField("ETL_TIME")
    private Date etlTime;
    
    /**
     * 审批状态
     */
    @TableField("SP")
    private String sp;
    
    /**
     * 流程id
     */
    @TableField("INSTANCE_ID")
    private String instanceId;

    public Integer getUrid() {
        return urid;
    }

    public void setUrid(Integer urid) {
        this.urid = urid;
    }
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
    public String getJidu() {
        return jidu;
    }

    public void setJidu(String jidu) {
        this.jidu = jidu;
    }
    public String getPlatName() {
        return platName;
    }

    public void setPlatName(String platName) {
        this.platName = platName;
    }
    public String getPlatCode() {
        return platCode;
    }

    public void setPlatCode(String platCode) {
        this.platCode = platCode;
    }
    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }
    public String getOrgidName() {
        return orgidName;
    }

    public void setOrgidName(String orgidName) {
        this.orgidName = orgidName;
    }
    public String getOrgidCode() {
        return orgidCode;
    }

    public void setOrgidCode(String orgidCode) {
        this.orgidCode = orgidCode;
    }
    public String getChnnal() {
        return chnnal;
    }

    public void setChnnal(String chnnal) {
        this.chnnal = chnnal;
    }
    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }
    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    public String getBanklocationid() {
        return banklocationid;
    }

    public void setBanklocationid(String banklocationid) {
        this.banklocationid = banklocationid;
    }
    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }
    public String getIsbankdirect() {
        return isbankdirect;
    }

    public void setIsbankdirect(String isbankdirect) {
        this.isbankdirect = isbankdirect;
    }
    public String getAccountXz() {
        return accountXz;
    }

    public void setAccountXz(String accountXz) {
        this.accountXz = accountXz;
    }
    public Long getLsNumber() {
        return lsNumber;
    }

    public void setLsNumber(Long lsNumber) {
        this.lsNumber = lsNumber;
    }
    public Date getYjXhTime() {
        return yjXhTime;
    }

    public void setYjXhTime(Date yjXhTime) {
        this.yjXhTime = yjXhTime;
    }
    public String getBkXhReseon() {
        return bkXhReseon;
    }

    public void setBkXhReseon(String bkXhReseon) {
        this.bkXhReseon = bkXhReseon;
    }
    public String getAcouuntStatus() {
        return acouuntStatus;
    }

    public void setAcouuntStatus(String acouuntStatus) {
        this.acouuntStatus = acouuntStatus;
    }
    public String getXyCkJd() {
        return xyCkJd;
    }

    public void setXyCkJd(String xyCkJd) {
        this.xyCkJd = xyCkJd;
    }
    public String getXyCkBkReseon() {
        return xyCkBkReseon;
    }

    public void setXyCkBkReseon(String xyCkBkReseon) {
        this.xyCkBkReseon = xyCkBkReseon;
    }
    public String getXyCkRate() {
        return xyCkRate;
    }

    public void setXyCkRate(String xyCkRate) {
        this.xyCkRate = xyCkRate;
    }
    public String getLxPayType() {
        return lxPayType;
    }

    public void setLxPayType(String lxPayType) {
        this.lxPayType = lxPayType;
    }
    public Date getZjKhSpEndTime() {
        return zjKhSpEndTime;
    }

    public void setZjKhSpEndTime(Date zjKhSpEndTime) {
        this.zjKhSpEndTime = zjKhSpEndTime;
    }
    public Date getZjPlatKhStartTime() {
        return zjPlatKhStartTime;
    }

    public void setZjPlatKhStartTime(Date zjPlatKhStartTime) {
        this.zjPlatKhStartTime = zjPlatKhStartTime;
    }
    public Date getKhQyOpTime() {
        return khQyOpTime;
    }

    public void setKhQyOpTime(Date khQyOpTime) {
        this.khQyOpTime = khQyOpTime;
    }
    public String getKhZc() {
        return khZc;
    }

    public void setKhZc(String khZc) {
        this.khZc = khZc;
    }
    public Date getZjXhSpEndTime() {
        return zjXhSpEndTime;
    }

    public void setZjXhSpEndTime(Date zjXhSpEndTime) {
        this.zjXhSpEndTime = zjXhSpEndTime;
    }
    public Date getZjPlatXhTime() {
        return zjPlatXhTime;
    }

    public void setZjPlatXhTime(Date zjPlatXhTime) {
        this.zjPlatXhTime = zjPlatXhTime;
    }
    public Date getXhOpTime() {
        return xhOpTime;
    }

    public void setXhOpTime(Date xhOpTime) {
        this.xhOpTime = xhOpTime;
    }
    public String getXhZc() {
        return xhZc;
    }

    public void setXhZc(String xhZc) {
        this.xhZc = xhZc;
    }
    public BigDecimal getAveNumber() {
        return aveNumber;
    }

    public void setAveNumber(BigDecimal aveNumber) {
        this.aveNumber = aveNumber;
    }
    public String getDataDate() {
        return dataDate;
    }

    public void setDataDate(String dataDate) {
        this.dataDate = dataDate;
    }
    public BigDecimal getClosingbalance() {
        return closingbalance;
    }

    public void setClosingbalance(BigDecimal closingbalance) {
        this.closingbalance = closingbalance;
    }
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public Date getEtlTime() {
        return etlTime;
    }

    public void setEtlTime(Date etlTime) {
        this.etlTime = etlTime;
    }

    public String getSp() {
		return sp;
	}

	public void setSp(String sp) {
		this.sp = sp;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	@Override
    public String toString() {
        return "DwFsiAccountDetail{" +
            "urid=" + urid +
            ", year=" + year +
            ", jidu=" + jidu +
            ", platName=" + platName +
            ", platCode=" + platCode +
            ", areaName=" + areaName +
            ", areaCode=" + areaCode +
            ", block=" + block +
            ", orgidName=" + orgidName +
            ", orgidCode=" + orgidCode +
            ", chnnal=" + chnnal +
            ", accountnumber=" + accountnumber +
            ", accountName=" + accountName +
            ", banklocationid=" + banklocationid +
            ", bank=" + bank +
            ", isbankdirect=" + isbankdirect +
            ", accountXz=" + accountXz +
            ", lsNumber=" + lsNumber +
            ", yjXhTime=" + yjXhTime +
            ", bkXhReseon=" + bkXhReseon +
            ", acouuntStatus=" + acouuntStatus +
            ", xyCkJd=" + xyCkJd +
            ", xyCkBkReseon=" + xyCkBkReseon +
            ", xyCkRate=" + xyCkRate +
            ", lxPayType=" + lxPayType +
            ", zjKhSpEndTime=" + zjKhSpEndTime +
            ", zjPlatKhStartTime=" + zjPlatKhStartTime +
            ", khQyOpTime=" + khQyOpTime +
            ", khZc=" + khZc +
            ", zjXhSpEndTime=" + zjXhSpEndTime +
            ", zjPlatXhTime=" + zjPlatXhTime +
            ", xhOpTime=" + xhOpTime +
            ", xhZc=" + xhZc +
            ", aveNumber=" + aveNumber +
            ", dataDate=" + dataDate +
            ", closingbalance=" + closingbalance +
            ", currency=" + currency +
            ", etlTime=" + etlTime +
        "}";
    }
}
