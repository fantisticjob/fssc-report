package com.longfor.fsscreport.nc.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.google.common.base.Converter;

import java.io.Serializable;

/**
 * <p>
 * NC外系统查询辅助余额明细
 * </p>
 *
 * @author chenziyao
 * @since 2020-10-12
 */
@TableName("ODS_NC_BALANCE_DETAILS")
@KeySequence("ODS_NC_BALANCE_DETAILS_S")
public class OdsNcBalanceDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private BigDecimal id;

    /**
     * 年
     */
    private String year;

    /**
     * 月
     */
    private String month;

    /**
     * 日
     */
    private String day;

    /**
     * 账簿名称
     */
    private String accountname;

    /**
     * 账簿编码
     */
    private String accountcode;

    /**
     * 辅助核算项编码
     */
    private String checktypecode;

    /**
     * 辅助核算项名称
     */
    private String checktypename;

    /**
     * 辅助核算名称
     */
    private String checkvaluename;

    /**
     * 凭证号
     */
    private String vouchernum;

    /**
     * 摘要
     */
    private String note;

    /**
     * 对方科目名称
     */
    private String adverseaccountname;

    /**
     * 借方
     */
    private String debitamount;

    /**
     * 贷方
     */
    private String creditamount;

    /**
     * 方向
     */
    private String direction;

    /**
     * 余额
     */
    private String amount;

    /**
     * 客商code
     */
    private String kscode;

    /**
     * 客商name
     */
    private String ksname;

    /**
     * 项目code
     */
    private String xmecode;

    /**
     * 项目name
     */
    private String xmname;

    /**
     * 人员code
     */
    private String rycode;

    /**
     * 人员name
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
     * 辅助核算编码
     */
    private String checkvaluecode;
    
    /**
     * 
     * 辅助余额id
     */
    private BigDecimal balanceid;
    
    
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
    
    private String orgcode;
    
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
    public String getOrgcode() {
		return orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
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

    public BigDecimal getBalanceid() {
		return balanceid;
	}

	public void setBalanceid(BigDecimal balanceid) {
		this.balanceid = balanceid;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}

	public String getAccountcode() {
		return accountcode;
	}

	public void setAccountcode(String accountcode) {
		this.accountcode = accountcode;
	}

	public String getChecktypecode() {
		return checktypecode;
	}

	public void setChecktypecode(String checktypecode) {
		this.checktypecode = checktypecode;
	}

	public String getChecktypename() {
		return checktypename;
	}

	public void setChecktypename(String checktypename) {
		this.checktypename = checktypename;
	}

	public String getCheckvaluename() {
		return checkvaluename;
	}

	public void setCheckvaluename(String checkvaluename) {
		this.checkvaluename = checkvaluename;
	}

	public String getVouchernum() {
		return vouchernum;
	}

	public void setVouchernum(String vouchernum) {
		this.vouchernum = vouchernum;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getAdverseaccountname() {
		return adverseaccountname;
	}

	public void setAdverseaccountname(String adverseaccountname) {
		this.adverseaccountname = adverseaccountname;
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

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
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

	public String getXmecode() {
		return xmecode;
	}

	public void setXmecode(String xmecode) {
		this.xmecode = xmecode;
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

	public String getCheckvaluecode() {
		return checkvaluecode;
	}

	public void setCheckvaluecode(String checkvaluecode) {
		this.checkvaluecode = checkvaluecode;
	}

	public Auxiliarybalancedetails convertTo() {
        OrderConverter convert = new OrderConverter();
        return convert.convert(this);
	}

    public OdsNcBalanceDetails convertFrom(Auxiliarybalancedetails order) {
        OrderConverter convert = new OrderConverter();
        return convert.reverse().convert(order);
    }
    private static class OrderConverter extends Converter<OdsNcBalanceDetails, Auxiliarybalancedetails> {

        @Override
        protected Auxiliarybalancedetails doForward(OdsNcBalanceDetails dto) {

            return null;
        }
        //具体的转换内容
        @Override
        protected OdsNcBalanceDetails doBackward(Auxiliarybalancedetails aux) {
        	OdsNcBalanceDetails dto = new OdsNcBalanceDetails();
            dto.setAccountcode(aux.getAccountcode());
            dto.setAccountname(aux.getAccountname());
            dto.setAdverseaccountname(aux.getAdverseaccountname());
            dto.setAmount(aux.getAmount());
            dto.setCreditamount(aux.getCreditamount());
            dto.setDay(aux.getDay());
            dto.setDebitamount(aux.getDebitamount());
            dto.setDirection(aux.getDirection());
            dto.setEndmonth(aux.getEndmonth());
            dto.setEndyear(aux.getEndyear());
            dto.setMonth(aux.getMonth());
            dto.setNote(aux.getNote());
            dto.setVouchernum(aux.getVouchernum());
            dto.setYear(aux.getYear());
           /**
            dto.setChecktypecode(aux.);
            dto.setChecktypename(aux.);
            dto.setCheckvaluecode(aux);
            dto.setCheckvaluename(aux.);
            */
          //========================begin==============
            dto.setFid(aux.getFid());
            dto.setOrgcode(aux.getOrgcode());
            
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
            		dto.setXmecode(aux.getCheckvaluecode1());
            		dto.setXmname(aux.getCheckvaluename1());
            	}
            	
            	if("XM".equals(aux.getChecktypecode2())) {
            		dto.setXmecode(aux.getCheckvaluecode2());
            		dto.setXmname(aux.getCheckvaluename2());
            	}
            	
            	
            	if("XM".equals(aux.getChecktypecode3())) {
            		dto.setXmecode(aux.getCheckvaluecode3());
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
