package com.longfor.fsscreport.nc.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.google.common.base.Converter;


/**
 * <p>
 * NC外系统查询辅助余额表
 * </p>
 *
 * @author chenziyao
 * @since 2020-10-12
 */
@TableName("ODS_NC_AUXILIARY_BALANCE")
@KeySequence("ODS_NC_AUXILIARY_BALANCE_S")
@XmlAccessorType(XmlAccessType.FIELD)
public class OdsNcAuxiliaryBalance implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private BigDecimal id;
    /**
     * 账套code
     */
    private String accountingbookcode;

    /**
     * 凭证主键
     */
    private String accountcode;

    /**
     * 制单人
     */
    private String accountname;

    /**
     * 期初方向
     */
    private String qcfx;

    /**
     * 期初余额
     */
    private String qcamount;

    /**
     * 借方发生
     */
    private String debitamount;

    /**
     * 贷方发生
     */
    private String creditamount;

    /**
     * 借方累计
     */
    private String debitaccumamount;

    /**
     * 贷方累计
     */
    private String creditaccumamount;
    
    /**
     * 期末方向
     */
    private String qmfx;

    /**
     * 期末余额
     */
    private String qmamount;

    /**
     * 账套名称
     */
    private String accountingbookname;

    /**
     * 客商code
     */
    private String kscode;

    /**
     * 客商名称
     */
    private String ksname;

    /**
     * 项目code
     */
    private String xmcode;

    /**
     * 项目名称
     */
    private String xmname;

    /**
     * 人员code
     */
    private String rycode;

    /**
     * 人员姓名
     */
    private String ryname;

    /**
     * 单据code
     */
    private String djhcode;

    /**
     * 单据name
     */
    private String djhname;
    
    /**
     * 组织code
     */
    private String orgcode;
    
    /**
     * 开始年
     */
    private String beginyear;
    
    /**
     * 开始月
     */
    private String beginmonth;
    
    /**
     * 结束年
     */
    private String endyear;
    
    /**
     * 结束月
     */
    private String endmonth;
    
    private String fid;
    
    /**
     * 手动or自动
     */
    private String flag;
    

    public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getBeginyear() {
		return beginyear;
	}

	public void setBeginyear(String beginyear) {
		this.beginyear = beginyear;
	}

	public String getBeginmonth() {
		return beginmonth;
	}

	public void setBeginmonth(String beginmonth) {
		this.beginmonth = beginmonth;
	}

	public String getEndyear() {
		return endyear;
	}

	public void setEndyear(String endyear) {
		this.endyear = endyear;
	}

	public String getEndmonth() {
		return endmonth;
	}

	public void setEndmonth(String endmonth) {
		this.endmonth = endmonth;
	}

	public String getOrgcode() {
		return orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}



	public String getAccountingbookcode() {
		return accountingbookcode;
	}

	public void setAccountingbookcode(String accountingbookcode) {
		this.accountingbookcode = accountingbookcode;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getAccountcode() {
		return accountcode;
	}

	public void setAccountcode(String accountcode) {
		this.accountcode = accountcode;
	}

	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}

	public String getQcfx() {
		return qcfx;
	}

	public void setQcfx(String qcfx) {
		this.qcfx = qcfx;
	}

	public String getQcamount() {
		return qcamount;
	}

	public void setQcamount(String qcamount) {
		this.qcamount = qcamount;
	}

	public String getDebitamount() {
		return debitamount;
	}

	public void setDebitamount(String debitamount) {
		this.debitamount = debitamount;
	}

	public String getCreditamount() {
		return creditamount;
	}

	public void setCreditamount(String creditamount) {
		this.creditamount = creditamount;
	}

	public String getDebitaccumamount() {
		return debitaccumamount;
	}

	public void setDebitaccumamount(String debitaccumamount) {
		this.debitaccumamount = debitaccumamount;
	}

	public String getCreditaccumamount() {
		return creditaccumamount;
	}

	public void setCreditaccumamount(String creditaccumamount) {
		this.creditaccumamount = creditaccumamount;
	}

	public String getQmfx() {
		return qmfx;
	}

	public void setQmfx(String qmfx) {
		this.qmfx = qmfx;
	}

	public String getQmamount() {
		return qmamount;
	}

	public void setQmamount(String qmamount) {
		this.qmamount = qmamount;
	}

	public String getAccountingbookname() {
		return accountingbookname;
	}

	public void setAccountingbookname(String accountingbookname) {
		this.accountingbookname = accountingbookname;
	}

	public String getKscode() {
		return kscode;
	}

	public void setKscode(String kscode) {
		this.kscode = kscode;
	}

	public String getKsname() {
		return ksname;
	}

	public void setKsname(String ksname) {
		this.ksname = ksname;
	}

	public String getXmcode() {
		return xmcode;
	}

	public void setXmcode(String xmcode) {
		this.xmcode = xmcode;
	}

	public String getXmname() {
		return xmname;
	}

	public void setXmname(String xmname) {
		this.xmname = xmname;
	}

	public String getRycode() {
		return rycode;
	}

	public void setRycode(String rycode) {
		this.rycode = rycode;
	}

	public String getRyname() {
		return ryname;
	}

	public void setRyname(String ryname) {
		this.ryname = ryname;
	}

	public String getDjhcode() {
		return djhcode;
	}

	public void setDjhcode(String djhcode) {
		this.djhcode = djhcode;
	}

	public String getDjhname() {
		return djhname;
	}

	public void setDjhname(String djhname) {
		this.djhname = djhname;
	}
	
	public Auxiliarybalance convertTo() {
        OrderConverter convert = new OrderConverter();
        return convert.convert(this);
	}

    public OdsNcAuxiliaryBalance convertFrom(Auxiliarybalance order) {
        OrderConverter convert = new OrderConverter();
        return convert.reverse().convert(order);
    }
    private static class OrderConverter extends Converter<OdsNcAuxiliaryBalance, Auxiliarybalance> {

        @Override
        protected Auxiliarybalance doForward(OdsNcAuxiliaryBalance dto) {

            return null;
        }
        //具体的转换内容
        @Override
        protected OdsNcAuxiliaryBalance doBackward(Auxiliarybalance aux) {
        	OdsNcAuxiliaryBalance dto = new OdsNcAuxiliaryBalance();
            dto.setAccountcode(aux.getAccountcode());
            dto.setAccountingbookcode(aux.getAccountingbookcode());
            dto.setAccountingbookname(aux.getAccountingbookname());
            dto.setAccountingbookname(aux.getAccountingbookname());
            dto.setAccountname(aux.getAccountname());
            dto.setBeginmonth(aux.getBeginmonth());
            dto.setBeginyear(aux.getBeginyear());
            dto.setCreditaccumamount(aux.getCreditaccumamount());
            dto.setCreditamount(aux.getCreditamount());
            dto.setDebitaccumamount(aux.getDebitaccumamount());
            dto.setDebitamount(aux.getDebitamount());
            dto.setEndmonth(aux.getEndmonth());
            dto.setEndyear(aux.getEndyear());
            dto.setOrgcode(aux.getOrgcode());
            dto.setQcamount(aux.getQcamount());
            dto.setQcfx(aux.getQcfx());
            dto.setQmamount(aux.getQmamount());
            dto.setQmfx(aux.getQmfx());
            
            //========================begin==============
            dto.setFid(aux.getId());
            // dto.setOrgcode(aux.getOrgcode());
            
            if("KS".equals(aux.getChecktypecode1())
            		|| "KS".equals(aux.getChecktypecode2())
            		|| "KS".equals(aux.getChecktypecode3())) {
            	
            	if("KS".equals(aux.getChecktypecode1())) {
            		dto.setKscode(aux.getCheckvaluecode1());
            		dto.setKsname(aux.getCheckvaluename1());
            	}
            	
            	if("KS".equals(aux.getChecktypecode2())) {
            		dto.setKscode(aux.getCheckvaluecode2());
            		dto.setKsname(aux.getCheckvaluename2());
            	}
            	
            	
            	if("KS".equals(aux.getChecktypecode3())) {
            		dto.setKscode(aux.getCheckvaluecode3());
            		dto.setKsname(aux.getCheckvaluename3());
            	}
            }
            
            if("XM".equals(aux.getChecktypecode1())
            		|| "XM".equals(aux.getChecktypecode2())
            		|| "XM".equals(aux.getChecktypecode3())) {
            	
            	if("XM".equals(aux.getChecktypecode1())) {
            		dto.setXmcode(aux.getCheckvaluecode1());
            		dto.setXmname(aux.getCheckvaluename1());
            	}
            	
            	if("XM".equals(aux.getChecktypecode2())) {
            		dto.setXmcode(aux.getCheckvaluecode2());
            		dto.setXmname(aux.getCheckvaluename2());
            	}
            	
            	
            	if("XM".equals(aux.getChecktypecode3())) {
            		dto.setXmcode(aux.getCheckvaluecode3());
            		dto.setXmname(aux.getCheckvaluename3());
            	}
            }
            
            if("RY".equals(aux.getChecktypecode1())
            		|| "RY".equals(aux.getChecktypecode2())
            		|| "RY".equals(aux.getChecktypecode3())) {
            	
            	if("RY".equals(aux.getChecktypecode1())) {
            		dto.setRycode(aux.getCheckvaluecode1());
            		dto.setRyname(aux.getCheckvaluename1());
            	}
            	
            	if("RY".equals(aux.getChecktypecode2())) {
            		dto.setRycode(aux.getCheckvaluecode2());
            		dto.setRyname(aux.getCheckvaluename2());
            	}
            	
            	
            	if("RY".equals(aux.getChecktypecode3())) {
            		dto.setRycode(aux.getCheckvaluecode3());
            		dto.setRyname(aux.getCheckvaluename3());
            	}
            }
            
            if("DJH".equals(aux.getChecktypecode1())
            		|| "DJH".equals(aux.getChecktypecode2())
            		|| "DJH".equals(aux.getChecktypecode3())) {
            	
            	if("DJH".equals(aux.getChecktypecode1())) {
            		dto.setDjhcode(aux.getCheckvaluecode1());
            		dto.setDjhname(aux.getCheckvaluename1());
            	}
            	
            	if("DJH".equals(aux.getChecktypecode2())) {
            		dto.setDjhcode(aux.getCheckvaluecode2());
            		dto.setDjhname(aux.getCheckvaluename2());
            	}
            	
            	
            	if("DJH".equals(aux.getChecktypecode3())) {
            		dto.setDjhcode(aux.getCheckvaluecode3());
            		dto.setDjhname(aux.getCheckvaluename3());
            	}
            }

            return dto;
        }
    }
	
}
