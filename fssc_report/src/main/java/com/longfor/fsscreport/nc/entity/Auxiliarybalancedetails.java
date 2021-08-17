package com.longfor.fsscreport.nc.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 辅助余额明细表
 * </p>
 *
 * @author chenziyao
 * @since 2020-11-17
 */
@TableName("AUXILIARYBALANCEDETAILS")
public class Auxiliarybalancedetails implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 删除标记
     */
    @TableField("DR")
    private String dr;

    /**
     * 时间戳
     */
    @TableField("TS")
    private String ts;

    /**
     * 主键
     */
    @TableField("ID")
    private String id;

    /**
     * 年
     */
    @TableField("YEAR")
    private String year;

    /**
     * 月
     */
    @TableField("MONTH")
    private String month;

    /**
     * 天
     */
    @TableField("DAY")
    private String day;

    /**
     * 账簿名称
     */
    @TableField("ACCOUNTNAME")
    private String accountname;

    /**
     * 账簿编码
     */
    @TableField("ACCOUNTCODE")
    private String accountcode;

    /**
     * 凭证号
     */
    @TableField("VOUCHERNUM")
    private String vouchernum;

    /**
     * 备注
     */
    @TableField("NOTE")
    private String note;

    /**
     * 对方科目名称
     */
    @TableField("ADVERSEACCOUNTNAME")
    private String adverseaccountname;

    /**
     * 对方账簿编码
     */
    @TableField("ADVERSEACCOUNTCODE")
    private String adverseaccountcode;

    /**
     * 借方
     */
    @TableField("DEBITAMOUNT")
    private String debitamount;

    /**
     * 贷方
     */
    @TableField("CREDITAMOUNT")
    private String creditamount;

    /**
     * 方向
     */
    @TableField("DIRECTION")
    private String direction;

    /**
     * 金额
     */
    @TableField("AMOUNT")
    private String amount;

    /**
     * 组织编码
     */
    @TableField("ORGCODE")
    private String orgcode;

    /**
     * 开始年
     */
    @TableField("BEGINYEAR")
    private String beginyear;

    /**
     * 开始月
     */
    @TableField("BEGINMONTH")
    private String beginmonth;

    /**
     * 截至年
     */
    @TableField("ENDYEAR")
    private String endyear;

    /**
     * 截至月
     */
    @TableField("ENDMONTH")
    private String endmonth;

    /**
     * 辅助核算类型编码1
     */
    @TableField("CHECKTYPECODE1")
    private String checktypecode1;

    /**
     * 辅助核算类型名称1
     */
    @TableField("CHECKTYPENAME1")
    private String checktypename1;

    /**
     * 辅助核算值编码1
     */
    @TableField("CHECKVALUECODE1")
    private String checkvaluecode1;

    /**
     * 辅助核算值名称1
     */
    @TableField("CHECKVALUENAME1")
    private String checkvaluename1;

    /**
     * 辅助核算类型编码2
     */
    @TableField("CHECKTYPECODE2")
    private String checktypecode2;

    /**
     * 辅助核算类型名称2
     */
    @TableField("CHECKTYPENAME2")
    private String checktypename2;

    /**
     * 辅助核算值编码2
     */
    @TableField("CHECKVALUECODE2")
    private String checkvaluecode2;

    /**
     * 辅助核算值名称2
     */
    @TableField("CHECKVALUENAME2")
    private String checkvaluename2;

    /**
     * 辅助核算类型编码3
     */
    @TableField("CHECKTYPECODE3")
    private String checktypecode3;

    /**
     * 辅助核算类型名称3
     */
    @TableField("CHECKTYPENAME3")
    private String checktypename3;

    /**
     * 辅助核算值编码3
     */
    @TableField("CHECKVALUECODE3")
    private String checkvaluecode3;

    /**
     * 辅助核算值名称3
     */
    @TableField("CHECKVALUENAME3")
    private String checkvaluename3;

    /**
     * 辅助核算类型编码4
     */
    @TableField("CHECKTYPECODE4")
    private String checktypecode4;

    /**
     * 辅助核算类型名称4
     */
    @TableField("CHECKTYPENAME4")
    private String checktypename4;

    /**
     * 辅助核算值编码4
     */
    @TableField("CHECKVALUECODE4")
    private String checkvaluecode4;

    /**
     * 辅助核算值名称4
     */
    @TableField("CHECKVALUENAME4")
    private String checkvaluename4;

    /**
     * 辅助核算类型编码5
     */
    @TableField("CHECKTYPECODE5")
    private String checktypecode5;

    /**
     * 辅助核算类型名称5
     */
    @TableField("CHECKTYPENAME5")
    private String checktypename5;

    /**
     * 辅助核算值编码5
     */
    @TableField("CHECKVALUECODE5")
    private String checkvaluecode5;

    /**
     * 辅助核算值名称5
     */
    @TableField("CHECKVALUENAME5")
    private String checkvaluename5;

    /**
     * 辅助核算类型编码6
     */
    @TableField("CHECKTYPECODE6")
    private String checktypecode6;

    /**
     * 辅助核算类型名称6
     */
    @TableField("CHECKTYPENAME6")
    private String checktypename6;

    /**
     * 辅助核算值编码6
     */
    @TableField("CHECKVALUECODE6")
    private String checkvaluecode6;

    /**
     * 辅助核算值名称6
     */
    @TableField("CHECKVALUENAME6")
    private String checkvaluename6;

    /**
     * 辅助核算类型编码7
     */
    @TableField("CHECKTYPECODE7")
    private String checktypecode7;

    /**
     * 辅助核算类型名称7
     */
    @TableField("CHECKTYPENAME7")
    private String checktypename7;

    /**
     * 辅助核算值编码7
     */
    @TableField("CHECKVALUECODE7")
    private String checkvaluecode7;

    /**
     * 辅助核算值名称7
     */
    @TableField("CHECKVALUENAME7")
    private String checkvaluename7;

    /**
     * 辅助核算类型编码8
     */
    @TableField("CHECKTYPECODE8")
    private String checktypecode8;

    /**
     * 辅助核算类型名称8
     */
    @TableField("CHECKTYPENAME8")
    private String checktypename8;

    /**
     * 辅助核算值编码8
     */
    @TableField("CHECKVALUECODE8")
    private String checkvaluecode8;

    /**
     * 辅助核算值名称8
     */
    @TableField("CHECKVALUENAME8")
    private String checkvaluename8;

    /**
     * 辅助核算类型编码9
     */
    @TableField("CHECKTYPECODE9")
    private String checktypecode9;

    /**
     * 辅助核算类型名称9
     */
    @TableField("CHECKTYPENAME9")
    private String checktypename9;

    /**
     * 辅助核算值编码9
     */
    @TableField("CHECKVALUECODE9")
    private String checkvaluecode9;

    /**
     * 辅助核算值名称9
     */
    @TableField("CHECKVALUENAME9")
    private String checkvaluename9;

    /**
     * 批次号
     */
    @TableField("BATCHERNUMBER")
    private String batchernumber;
    /**
     * 
     */
    @TableField("FID")
    private String fid;

    public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getDr() {
        return dr;
    }

    public void setDr(String dr) {
        this.dr = dr;
    }
    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
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
    public String getAdverseaccountcode() {
        return adverseaccountcode;
    }

    public void setAdverseaccountcode(String adverseaccountcode) {
        this.adverseaccountcode = adverseaccountcode;
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
    public String getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(String orgcode) {
        this.orgcode = orgcode;
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
    public String getChecktypecode1() {
        return checktypecode1;
    }

    public void setChecktypecode1(String checktypecode1) {
        this.checktypecode1 = checktypecode1;
    }
    public String getChecktypename1() {
        return checktypename1;
    }

    public void setChecktypename1(String checktypename1) {
        this.checktypename1 = checktypename1;
    }
    public String getCheckvaluecode1() {
        return checkvaluecode1;
    }

    public void setCheckvaluecode1(String checkvaluecode1) {
        this.checkvaluecode1 = checkvaluecode1;
    }
    public String getCheckvaluename1() {
        return checkvaluename1;
    }

    public void setCheckvaluename1(String checkvaluename1) {
        this.checkvaluename1 = checkvaluename1;
    }
    public String getChecktypecode2() {
        return checktypecode2;
    }

    public void setChecktypecode2(String checktypecode2) {
        this.checktypecode2 = checktypecode2;
    }
    public String getChecktypename2() {
        return checktypename2;
    }

    public void setChecktypename2(String checktypename2) {
        this.checktypename2 = checktypename2;
    }
    public String getCheckvaluecode2() {
        return checkvaluecode2;
    }

    public void setCheckvaluecode2(String checkvaluecode2) {
        this.checkvaluecode2 = checkvaluecode2;
    }
    public String getCheckvaluename2() {
        return checkvaluename2;
    }

    public void setCheckvaluename2(String checkvaluename2) {
        this.checkvaluename2 = checkvaluename2;
    }
    public String getChecktypecode3() {
        return checktypecode3;
    }

    public void setChecktypecode3(String checktypecode3) {
        this.checktypecode3 = checktypecode3;
    }
    public String getChecktypename3() {
        return checktypename3;
    }

    public void setChecktypename3(String checktypename3) {
        this.checktypename3 = checktypename3;
    }
    public String getCheckvaluecode3() {
        return checkvaluecode3;
    }

    public void setCheckvaluecode3(String checkvaluecode3) {
        this.checkvaluecode3 = checkvaluecode3;
    }
    public String getCheckvaluename3() {
        return checkvaluename3;
    }

    public void setCheckvaluename3(String checkvaluename3) {
        this.checkvaluename3 = checkvaluename3;
    }
    public String getChecktypecode4() {
        return checktypecode4;
    }

    public void setChecktypecode4(String checktypecode4) {
        this.checktypecode4 = checktypecode4;
    }
    public String getChecktypename4() {
        return checktypename4;
    }

    public void setChecktypename4(String checktypename4) {
        this.checktypename4 = checktypename4;
    }
    public String getCheckvaluecode4() {
        return checkvaluecode4;
    }

    public void setCheckvaluecode4(String checkvaluecode4) {
        this.checkvaluecode4 = checkvaluecode4;
    }
    public String getCheckvaluename4() {
        return checkvaluename4;
    }

    public void setCheckvaluename4(String checkvaluename4) {
        this.checkvaluename4 = checkvaluename4;
    }
    public String getChecktypecode5() {
        return checktypecode5;
    }

    public void setChecktypecode5(String checktypecode5) {
        this.checktypecode5 = checktypecode5;
    }
    public String getChecktypename5() {
        return checktypename5;
    }

    public void setChecktypename5(String checktypename5) {
        this.checktypename5 = checktypename5;
    }
    public String getCheckvaluecode5() {
        return checkvaluecode5;
    }

    public void setCheckvaluecode5(String checkvaluecode5) {
        this.checkvaluecode5 = checkvaluecode5;
    }
    public String getCheckvaluename5() {
        return checkvaluename5;
    }

    public void setCheckvaluename5(String checkvaluename5) {
        this.checkvaluename5 = checkvaluename5;
    }
    public String getChecktypecode6() {
        return checktypecode6;
    }

    public void setChecktypecode6(String checktypecode6) {
        this.checktypecode6 = checktypecode6;
    }
    public String getChecktypename6() {
        return checktypename6;
    }

    public void setChecktypename6(String checktypename6) {
        this.checktypename6 = checktypename6;
    }
    public String getCheckvaluecode6() {
        return checkvaluecode6;
    }

    public void setCheckvaluecode6(String checkvaluecode6) {
        this.checkvaluecode6 = checkvaluecode6;
    }
    public String getCheckvaluename6() {
        return checkvaluename6;
    }

    public void setCheckvaluename6(String checkvaluename6) {
        this.checkvaluename6 = checkvaluename6;
    }
    public String getChecktypecode7() {
        return checktypecode7;
    }

    public void setChecktypecode7(String checktypecode7) {
        this.checktypecode7 = checktypecode7;
    }
    public String getChecktypename7() {
        return checktypename7;
    }

    public void setChecktypename7(String checktypename7) {
        this.checktypename7 = checktypename7;
    }
    public String getCheckvaluecode7() {
        return checkvaluecode7;
    }

    public void setCheckvaluecode7(String checkvaluecode7) {
        this.checkvaluecode7 = checkvaluecode7;
    }
    public String getCheckvaluename7() {
        return checkvaluename7;
    }

    public void setCheckvaluename7(String checkvaluename7) {
        this.checkvaluename7 = checkvaluename7;
    }
    public String getChecktypecode8() {
        return checktypecode8;
    }

    public void setChecktypecode8(String checktypecode8) {
        this.checktypecode8 = checktypecode8;
    }
    public String getChecktypename8() {
        return checktypename8;
    }

    public void setChecktypename8(String checktypename8) {
        this.checktypename8 = checktypename8;
    }
    public String getCheckvaluecode8() {
        return checkvaluecode8;
    }

    public void setCheckvaluecode8(String checkvaluecode8) {
        this.checkvaluecode8 = checkvaluecode8;
    }
    public String getCheckvaluename8() {
        return checkvaluename8;
    }

    public void setCheckvaluename8(String checkvaluename8) {
        this.checkvaluename8 = checkvaluename8;
    }
    public String getChecktypecode9() {
        return checktypecode9;
    }

    public void setChecktypecode9(String checktypecode9) {
        this.checktypecode9 = checktypecode9;
    }
    public String getChecktypename9() {
        return checktypename9;
    }

    public void setChecktypename9(String checktypename9) {
        this.checktypename9 = checktypename9;
    }
    public String getCheckvaluecode9() {
        return checkvaluecode9;
    }

    public void setCheckvaluecode9(String checkvaluecode9) {
        this.checkvaluecode9 = checkvaluecode9;
    }
    public String getCheckvaluename9() {
        return checkvaluename9;
    }

    public void setCheckvaluename9(String checkvaluename9) {
        this.checkvaluename9 = checkvaluename9;
    }
    public String getBatchernumber() {
        return batchernumber;
    }

    public void setBatchernumber(String batchernumber) {
        this.batchernumber = batchernumber;
    }

    @Override
    public String toString() {
        return "Auxiliarybalancedetails{" +
            "dr=" + dr +
            ", ts=" + ts +
            ", id=" + id +
            ", year=" + year +
            ", month=" + month +
            ", day=" + day +
            ", accountname=" + accountname +
            ", accountcode=" + accountcode +
            ", vouchernum=" + vouchernum +
            ", note=" + note +
            ", adverseaccountname=" + adverseaccountname +
            ", adverseaccountcode=" + adverseaccountcode +
            ", debitamount=" + debitamount +
            ", creditamount=" + creditamount +
            ", direction=" + direction +
            ", amount=" + amount +
            ", orgcode=" + orgcode +
            ", beginyear=" + beginyear +
            ", beginmonth=" + beginmonth +
            ", endyear=" + endyear +
            ", endmonth=" + endmonth +
            ", checktypecode1=" + checktypecode1 +
            ", checktypename1=" + checktypename1 +
            ", checkvaluecode1=" + checkvaluecode1 +
            ", checkvaluename1=" + checkvaluename1 +
            ", checktypecode2=" + checktypecode2 +
            ", checktypename2=" + checktypename2 +
            ", checkvaluecode2=" + checkvaluecode2 +
            ", checkvaluename2=" + checkvaluename2 +
            ", checktypecode3=" + checktypecode3 +
            ", checktypename3=" + checktypename3 +
            ", checkvaluecode3=" + checkvaluecode3 +
            ", checkvaluename3=" + checkvaluename3 +
            ", checktypecode4=" + checktypecode4 +
            ", checktypename4=" + checktypename4 +
            ", checkvaluecode4=" + checkvaluecode4 +
            ", checkvaluename4=" + checkvaluename4 +
            ", checktypecode5=" + checktypecode5 +
            ", checktypename5=" + checktypename5 +
            ", checkvaluecode5=" + checkvaluecode5 +
            ", checkvaluename5=" + checkvaluename5 +
            ", checktypecode6=" + checktypecode6 +
            ", checktypename6=" + checktypename6 +
            ", checkvaluecode6=" + checkvaluecode6 +
            ", checkvaluename6=" + checkvaluename6 +
            ", checktypecode7=" + checktypecode7 +
            ", checktypename7=" + checktypename7 +
            ", checkvaluecode7=" + checkvaluecode7 +
            ", checkvaluename7=" + checkvaluename7 +
            ", checktypecode8=" + checktypecode8 +
            ", checktypename8=" + checktypename8 +
            ", checkvaluecode8=" + checkvaluecode8 +
            ", checkvaluename8=" + checkvaluename8 +
            ", checktypecode9=" + checktypecode9 +
            ", checktypename9=" + checktypename9 +
            ", checkvaluecode9=" + checkvaluecode9 +
            ", checkvaluename9=" + checkvaluename9 +
            ", batchernumber=" + batchernumber +
        "}";
    }
}
