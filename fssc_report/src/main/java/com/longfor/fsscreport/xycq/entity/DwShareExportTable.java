package com.longfor.fsscreport.xycq.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 线下导入表
 * </p>
 *
 * @author chenziyao
 * @since 2020-12-25
 */
@TableName("DW_SHARE_EXPORT_TABLE")
public class DwShareExportTable implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 所属看板
     */
    private String billboard;

    /**
     * 所属流程
     */
    private String process;

    /**
     * 单据编号
     */
    @TableId(value = "code_receipts", type = IdType.INPUT)
    private String  djbh;
    
    /**
     * 单据类别
     */
    @TableField("type_receipts")
    private String qlc_ejfl;
    
    /**
     * 单据类别-新
     */
    @TableField(exist = false)
    private String djlb_new;

    /**
     * 情况详述
     */
    private String description;
    
    /**
     * 平台
     */
    @TableField("platform")
    private String pladform;
    
    /**
     * 地区
     */
    private String area;
    
    /**
     * 支付状态
     */
    @TableField("pay_status")
    private String zfzt;
    
    /**
     * 超期三天的 经办人字段
     */
    private String jnr;

    /**
     * 备注
     */
    private String bz;

    /**
     * 超时情况
     */
    private String csqk;

    /**
     * 处理时长_分钟
     */
    private Double deal_long;

    /**
     * 收款单位
     */
    private String skdw;

    /**
     * 业务系统审批时间
     */
    private String spsj;

    /**
     * 超四天，五天的  经办人
     */
    @TableField(exist = false)
    private String f_sqr;

    /**
     * 应付金额
     */
    private BigDecimal yfje;

    /**
     * 付款单位
     */
    private String fkdw;

    /**
     * 合作方标签
     */
    private String hzf_flag;

    /**
     * 创建时间
     */
    private String createdon_zj;

    /**
     * 签收时间
     */
    private String qssj_gx;

    /**
     * 扫描完成时间
     */
    private String f_scan_time_gx;

    /**
     * 资金制单完成时间
     */
    private String zjzdwcsj_zj;

    /**
     * 支付方式
     */
    private String f_zffs_gx;

    /**
     * 入池时间
     */
    private String f_entsc_time_gx;

    /**
     * 初审派单状态
     */
    private String f_sta_fdisp_gx;

    /**
     * 初审派单时间
     */
    private String f_fdisp_time_gx;

    /**
     * 初审时间
     */
    private String f_fvep_time_gx;

    /**
     * 初审人
     */
    private String f_fvep_name_gx;

    /**
     * 复审派单状态
     */
    private String f_sta_sdisp_gx;

    /**
     * 复审派单时间
     */
    private String f_sdisp_time_gx;

    /**
     * 复审时间
     */
    private String f_svep_time_gx;

    /**
     * 复审人
     */
    private String f_sdisp_name_gx;

    /**
     * 流程状态
     */
    private String lczt_gx;

    /**
     * 是否先付后审
     */
    private String f_sfxzf_gx;

    /**
     * 是否单据挂起
     */
    private String sfdjgq_gx;

    /**
     * 是否超期停付
     */
    private String cqtf_gx;

    /**
     * 预警类原因
     */
    private String reason_yj;

    /**
     * 单据来源系统
     */
    private String f_lyxt_gx;

    /**
     * 支付时间
     */
    private String f_asyn_time_fk;

	public String getBillboard() {
		return billboard;
	}

	public void setBillboard(String billboard) {
		this.billboard = billboard;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getDjbh() {
		return djbh;
	}

	public void setDjbh(String djbh) {
		this.djbh = djbh;
	}

	public String getQlc_ejfl() {
		return qlc_ejfl;
	}

	public void setQlc_ejfl(String qlc_ejfl) {
		this.qlc_ejfl = qlc_ejfl;
	}

	public String getDjlb_new() {
		return djlb_new;
	}

	public void setDjlb_new(String djlb_new) {
		this.djlb_new = djlb_new;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPladform() {
		return pladform;
	}

	public void setPladform(String pladform) {
		this.pladform = pladform;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getZfzt() {
		return zfzt;
	}

	public void setZfzt(String zfzt) {
		this.zfzt = zfzt;
	}

	public String getJnr() {
		return jnr;
	}

	public void setJnr(String jnr) {
		this.jnr = jnr;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getCsqk() {
		return csqk;
	}

	public void setCsqk(String csqk) {
		this.csqk = csqk;
	}

	public Double getDeal_long() {
		return deal_long;
	}

	public void setDeal_long(Double deal_long) {
		this.deal_long = deal_long;
	}

	public String getSkdw() {
		return skdw;
	}

	public void setSkdw(String skdw) {
		this.skdw = skdw;
	}

	public String getSpsj() {
		return spsj;
	}

	public void setSpsj(String spsj) {
		this.spsj = spsj;
	}

	public String getF_sqr() {
		return f_sqr;
	}

	public void setF_sqr(String f_sqr) {
		this.f_sqr = f_sqr;
	}

	public BigDecimal getYfje() {
		return yfje;
	}

	public void setYfje(BigDecimal yfje) {
		this.yfje = yfje;
	}

	public String getFkdw() {
		return fkdw;
	}

	public void setFkdw(String fkdw) {
		this.fkdw = fkdw;
	}

	public String getHzf_flag() {
		return hzf_flag;
	}

	public void setHzf_flag(String hzf_flag) {
		this.hzf_flag = hzf_flag;
	}

	public String getCreatedon_zj() {
		return createdon_zj;
	}

	public void setCreatedon_zj(String createdon_zj) {
		this.createdon_zj = createdon_zj;
	}

	public String getQssj_gx() {
		return qssj_gx;
	}

	public void setQssj_gx(String qssj_gx) {
		this.qssj_gx = qssj_gx;
	}

	public String getF_scan_time_gx() {
		return f_scan_time_gx;
	}

	public void setF_scan_time_gx(String f_scan_time_gx) {
		this.f_scan_time_gx = f_scan_time_gx;
	}

	public String getZjzdwcsj_zj() {
		return zjzdwcsj_zj;
	}

	public void setZjzdwcsj_zj(String zjzdwcsj_zj) {
		this.zjzdwcsj_zj = zjzdwcsj_zj;
	}

	public String getF_zffs_gx() {
		return f_zffs_gx;
	}

	public void setF_zffs_gx(String f_zffs_gx) {
		this.f_zffs_gx = f_zffs_gx;
	}

	public String getF_entsc_time_gx() {
		return f_entsc_time_gx;
	}

	public void setF_entsc_time_gx(String f_entsc_time_gx) {
		this.f_entsc_time_gx = f_entsc_time_gx;
	}

	public String getF_sta_fdisp_gx() {
		return f_sta_fdisp_gx;
	}

	public void setF_sta_fdisp_gx(String f_sta_fdisp_gx) {
		this.f_sta_fdisp_gx = f_sta_fdisp_gx;
	}

	public String getF_fdisp_time_gx() {
		return f_fdisp_time_gx;
	}

	public void setF_fdisp_time_gx(String f_fdisp_time_gx) {
		this.f_fdisp_time_gx = f_fdisp_time_gx;
	}

	public String getF_fvep_time_gx() {
		return f_fvep_time_gx;
	}

	public void setF_fvep_time_gx(String f_fvep_time_gx) {
		this.f_fvep_time_gx = f_fvep_time_gx;
	}

	public String getF_fvep_name_gx() {
		return f_fvep_name_gx;
	}

	public void setF_fvep_name_gx(String f_fvep_name_gx) {
		this.f_fvep_name_gx = f_fvep_name_gx;
	}

	public String getF_sta_sdisp_gx() {
		return f_sta_sdisp_gx;
	}

	public void setF_sta_sdisp_gx(String f_sta_sdisp_gx) {
		this.f_sta_sdisp_gx = f_sta_sdisp_gx;
	}

	public String getF_sdisp_time_gx() {
		return f_sdisp_time_gx;
	}

	public void setF_sdisp_time_gx(String f_sdisp_time_gx) {
		this.f_sdisp_time_gx = f_sdisp_time_gx;
	}

	public String getF_svep_time_gx() {
		return f_svep_time_gx;
	}

	public void setF_svep_time_gx(String f_svep_time_gx) {
		this.f_svep_time_gx = f_svep_time_gx;
	}

	public String getF_sdisp_name_gx() {
		return f_sdisp_name_gx;
	}

	public void setF_sdisp_name_gx(String f_sdisp_name_gx) {
		this.f_sdisp_name_gx = f_sdisp_name_gx;
	}

	public String getLczt_gx() {
		return lczt_gx;
	}

	public void setLczt_gx(String lczt_gx) {
		this.lczt_gx = lczt_gx;
	}

	public String getF_sfxzf_gx() {
		return f_sfxzf_gx;
	}

	public void setF_sfxzf_gx(String f_sfxzf_gx) {
		this.f_sfxzf_gx = f_sfxzf_gx;
	}

	public String getSfdjgq_gx() {
		return sfdjgq_gx;
	}

	public void setSfdjgq_gx(String sfdjgq_gx) {
		this.sfdjgq_gx = sfdjgq_gx;
	}

	public String getCqtf_gx() {
		return cqtf_gx;
	}

	public void setCqtf_gx(String cqtf_gx) {
		this.cqtf_gx = cqtf_gx;
	}

	public String getReason_yj() {
		return reason_yj;
	}

	public void setReason_yj(String reason_yj) {
		this.reason_yj = reason_yj;
	}

	public String getF_lyxt_gx() {
		return f_lyxt_gx;
	}

	public void setF_lyxt_gx(String f_lyxt_gx) {
		this.f_lyxt_gx = f_lyxt_gx;
	}

	public String getF_asyn_time_fk() {
		return f_asyn_time_fk;
	}

	public void setF_asyn_time_fk(String f_asyn_time_fk) {
		this.f_asyn_time_fk = f_asyn_time_fk;
	}
    
}
