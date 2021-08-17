package com.longfor.fsscreport.costaccount.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 成本台账项目付款单明细表
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-07
 */
@TableName("DW_CT_PROJECT_PAYMENT")
public class DwCtProjectPayment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId("ID")
    private BigDecimal id;

    /**
     * 年
     */
    @TableField("YEARV")
    private String yearv;

    /**
     * 月
     */
    @TableField("PERIODV")
    private String periodv;

    /**
     * 科目
     */
    @TableField("SUBJCODE")
    private String subjcode;

    /**
     * 科目名称
     */
    @TableField("SUBJNAME")
    private String subjname;

    /**
     * 单据编号
     */
    @TableField("DJBH")
    private String djbh;

    /**
     * 摘要内容
     */
    @TableField("EXPLANATION")
    private String explanation;

    /**
     * 来源
     */
    @TableField("LAIYUAN")
    private String laiyuan;

    /**
     * 合同编号
     */
    @TableField("CONTRACTCODE")
    private String contractcode;

    /**
     * 合同类别
     */
    @TableField("HTTYPENAME")
    private String httypename;

    /**
     * 合同名称
     */
    @TableField("CONTRACTNAME")
    private String contractname;

    /**
     * 对方单位
     */
    @TableField("X_DFPROVIDERNAMES")
    private String xDfprovidernames;

    /**
     * 结算状态
     */
    @TableField("JSSTATE")
    private String jsstate;

    /**
     * 合同最新金额（含税）
     */
    @TableField("X_TOTALAMOUNT_TAX")
    private BigDecimal xTotalamountTax;

    /**
     * 合同最新金额（不含税）
     */
    @TableField("X_TOTALAMOUNT_NONTAX")
    private BigDecimal xTotalamountNontax;

    /**
     * 账面开发间接费用
     */
    @TableField("LOCALDEBITAMOUNT_JJFY")
    private BigDecimal localdebitamountJjfy;

    /**
     * 账面开发成本
     */
    @TableField("LOCALDEBITAMOUNT")
    private BigDecimal localdebitamount;

    /**
     * 土地费用
     */
    @TableField("LOCALDEBITAMOUNT_TD")
    private BigDecimal localdebitamountTd;

    /**
     * 前期工程费
     */
    @TableField("LOCALDEBITAMOUNT_QQ")
    private BigDecimal localdebitamountQq;

    /**
     * 建安工程费
     */
    @TableField("LOCALDEBITAMOUNT_JA")
    private BigDecimal localdebitamountJa;

    /**
     * 基础设施费
     */
    @TableField("LOCALDEBITAMOUNT_JC")
    private BigDecimal localdebitamountJc;

    /**
     * 配套设施费
     */
    @TableField("LOCALDEBITAMOUNT_PT")
    private BigDecimal localdebitamountPt;

    /**
     * 环境景观工程费
     */
    @TableField("LOCALDEBITAMOUNT_HJ")
    private BigDecimal localdebitamountHj;

    /**
     * 工程相关费
     */
    @TableField("LOCALDEBITAMOUNT_GCXG")
    private BigDecimal localdebitamountGcxg;

    /**
     * 工程预估成本
     */
    @TableField("LOCALDEBITAMOUNT_GCYG")
    private BigDecimal localdebitamountGcyg;

    /**
     * 工程后续成本
     */
    @TableField("LOCALDEBITAMOUNT_GCHX")
    private BigDecimal localdebitamountGchx;

    /**
     * 开发间接费转入
     */
    @TableField("LOCALDEBITAMOUNT_KFJJ")
    private BigDecimal localdebitamountKfjj;

    /**
     * NC取票金额（含税）
     */
    @TableField("QP_AMOUNT_HS1")
    private BigDecimal qpAmountHs1;

    /**
     * NC取票金额（不含税）
     */
    @TableField("QP_AMOUNT")
    private BigDecimal qpAmount;

    /**
     * NC取票金额（可抵扣税额）
     */
    @TableField("QP_AMOUNT_HS")
    private BigDecimal qpAmountHs;

    /**
     * 贷方累计发生
     */
    @TableField("CREDITAMOUNT")
    private BigDecimal creditamount;

    /**
     * 成本取票金额（含税）
     */
    @TableField("X_AMOUNT")
    private BigDecimal xAmount;

    /**
     * 成本取票金额（不含税）
     */
    @TableField("X_AMOUNT_NONTAX")
    private BigDecimal xAmountNontax;

    /**
     * 成本取票金额（可抵扣税额）
     */
    @TableField("X_INPUTTAX")
    private BigDecimal xInputtax;

    /**
     * 借方累计发生（NC付款金额）
     */
    @TableField("DEBITAMOUNT")
    private BigDecimal debitamount;

    /**
     * 成本付款金额
     */
    @TableField("APPLYAMOUNT_BZ")
    private BigDecimal applyamountBz;

    /**
     * 合计
     */
    @TableField("X_PAYMENTAMOUNT")
    private BigDecimal xPaymentamount;

    /**
     * 现金
     */
    @TableField("X_PAYMENTAMOUNT_XJ")
    private BigDecimal xPaymentamountXj;

    /**
     * 商票
     */
    @TableField("X_PAYMENTAMOUNT_SP")
    private BigDecimal xPaymentamountSp;

    /**
     * 保理
     */
    @TableField("X_PAYMENTAMOUNT_BL")
    private BigDecimal xPaymentamountBl;

    /**
     * 扣罚款
     */
    @TableField("X_OFFSETAMOUNT_BZ")
    private BigDecimal xOffsetamountBz;

    /**
     * 凭证
     */
    @TableField("NOV")
    private String nov;

    /**
     * 核销凭证
     */
    @TableField("HX_NOV")
    private String hxNov;

    /**
     * 年月
     */
    @TableField("DATEKEY")
    private String datekey;

    /**
     * 取票金额（含税）_人工确认
     */
    @TableField("QPJE_HS")
    private BigDecimal qpjeHs;

    /**
     * 取票金额（不含税）_人工确认
     */
    @TableField("QPJE_BHS")
    private BigDecimal qpjeBhs;

    /**
     * 取票金额（可抵扣税额）_人工确认
     */
    @TableField("QPJE_KDK")
    private BigDecimal qpjeKdk;

    /**
     * 付款金额确认数_人工确认
     */
    @TableField("APPLYAMOUNT_BZ_RG")
    private BigDecimal applyamountBzRg;

    /**
     * 分期
     */
    @TableField("FQ")
    private String fq;

    /**
     * 锁板标识（0-未锁板 1-锁板）
     */
    @TableField("ISSB")
    private String issb;

    @TableField("GZ")
    private String gz;

    /**
     * etl时间
     */
    @TableField("ETL_TIME")
    private Date etlTime;

    /**
     * 公司名称
     */
    @TableField("COMPANY_NAME")
    private String companyName;

    /**
     * 提交状态(0-未提交 1-已提交)
     */
    @TableField("TJ_STATUS")
    private String tjStatus;

    /**
     * 提交时间
     */
    @TableField("TJ_DATE")
    private Date tjDate;

    /**
     * 账面成本不分二级-初始录入
     */
    @TableField("CSLL")
    private BigDecimal csll;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }
    public String getYearv() {
        return yearv;
    }

    public void setYearv(String yearv) {
        this.yearv = yearv;
    }
    public String getPeriodv() {
        return periodv;
    }

    public void setPeriodv(String periodv) {
        this.periodv = periodv;
    }
    public String getSubjcode() {
        return subjcode;
    }

    public void setSubjcode(String subjcode) {
        this.subjcode = subjcode;
    }
    public String getSubjname() {
        return subjname;
    }

    public void setSubjname(String subjname) {
        this.subjname = subjname;
    }
    public String getDjbh() {
        return djbh;
    }

    public void setDjbh(String djbh) {
        this.djbh = djbh;
    }
    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
    public String getLaiyuan() {
        return laiyuan;
    }

    public void setLaiyuan(String laiyuan) {
        this.laiyuan = laiyuan;
    }
    public String getContractcode() {
        return contractcode;
    }

    public void setContractcode(String contractcode) {
        this.contractcode = contractcode;
    }
    public String getHttypename() {
        return httypename;
    }

    public void setHttypename(String httypename) {
        this.httypename = httypename;
    }
    public String getContractname() {
        return contractname;
    }

    public void setContractname(String contractname) {
        this.contractname = contractname;
    }
    public String getxDfprovidernames() {
        return xDfprovidernames;
    }

    public void setxDfprovidernames(String xDfprovidernames) {
        this.xDfprovidernames = xDfprovidernames;
    }
    public String getJsstate() {
        return jsstate;
    }

    public void setJsstate(String jsstate) {
        this.jsstate = jsstate;
    }
    public BigDecimal getxTotalamountTax() {
        return xTotalamountTax;
    }

    public void setxTotalamountTax(BigDecimal xTotalamountTax) {
        this.xTotalamountTax = xTotalamountTax;
    }
    public BigDecimal getxTotalamountNontax() {
        return xTotalamountNontax;
    }

    public void setxTotalamountNontax(BigDecimal xTotalamountNontax) {
        this.xTotalamountNontax = xTotalamountNontax;
    }
    public BigDecimal getLocaldebitamountJjfy() {
        return localdebitamountJjfy;
    }

    public void setLocaldebitamountJjfy(BigDecimal localdebitamountJjfy) {
        this.localdebitamountJjfy = localdebitamountJjfy;
    }
    public BigDecimal getLocaldebitamount() {
        return localdebitamount;
    }

    public void setLocaldebitamount(BigDecimal localdebitamount) {
        this.localdebitamount = localdebitamount;
    }
    public BigDecimal getLocaldebitamountTd() {
        return localdebitamountTd;
    }

    public void setLocaldebitamountTd(BigDecimal localdebitamountTd) {
        this.localdebitamountTd = localdebitamountTd;
    }
    public BigDecimal getLocaldebitamountQq() {
        return localdebitamountQq;
    }

    public void setLocaldebitamountQq(BigDecimal localdebitamountQq) {
        this.localdebitamountQq = localdebitamountQq;
    }
    public BigDecimal getLocaldebitamountJa() {
        return localdebitamountJa;
    }

    public void setLocaldebitamountJa(BigDecimal localdebitamountJa) {
        this.localdebitamountJa = localdebitamountJa;
    }
    public BigDecimal getLocaldebitamountJc() {
        return localdebitamountJc;
    }

    public void setLocaldebitamountJc(BigDecimal localdebitamountJc) {
        this.localdebitamountJc = localdebitamountJc;
    }
    public BigDecimal getLocaldebitamountPt() {
        return localdebitamountPt;
    }

    public void setLocaldebitamountPt(BigDecimal localdebitamountPt) {
        this.localdebitamountPt = localdebitamountPt;
    }
    public BigDecimal getLocaldebitamountHj() {
        return localdebitamountHj;
    }

    public void setLocaldebitamountHj(BigDecimal localdebitamountHj) {
        this.localdebitamountHj = localdebitamountHj;
    }
    public BigDecimal getLocaldebitamountGcxg() {
        return localdebitamountGcxg;
    }

    public void setLocaldebitamountGcxg(BigDecimal localdebitamountGcxg) {
        this.localdebitamountGcxg = localdebitamountGcxg;
    }
    public BigDecimal getLocaldebitamountGcyg() {
        return localdebitamountGcyg;
    }

    public void setLocaldebitamountGcyg(BigDecimal localdebitamountGcyg) {
        this.localdebitamountGcyg = localdebitamountGcyg;
    }
    public BigDecimal getLocaldebitamountGchx() {
        return localdebitamountGchx;
    }

    public void setLocaldebitamountGchx(BigDecimal localdebitamountGchx) {
        this.localdebitamountGchx = localdebitamountGchx;
    }
    public BigDecimal getLocaldebitamountKfjj() {
        return localdebitamountKfjj;
    }

    public void setLocaldebitamountKfjj(BigDecimal localdebitamountKfjj) {
        this.localdebitamountKfjj = localdebitamountKfjj;
    }
    public BigDecimal getQpAmountHs1() {
        return qpAmountHs1;
    }

    public void setQpAmountHs1(BigDecimal qpAmountHs1) {
        this.qpAmountHs1 = qpAmountHs1;
    }
    public BigDecimal getQpAmount() {
        return qpAmount;
    }

    public void setQpAmount(BigDecimal qpAmount) {
        this.qpAmount = qpAmount;
    }
    public BigDecimal getQpAmountHs() {
        return qpAmountHs;
    }

    public void setQpAmountHs(BigDecimal qpAmountHs) {
        this.qpAmountHs = qpAmountHs;
    }
    public BigDecimal getCreditamount() {
        return creditamount;
    }

    public void setCreditamount(BigDecimal creditamount) {
        this.creditamount = creditamount;
    }
    public BigDecimal getxAmount() {
        return xAmount;
    }

    public void setxAmount(BigDecimal xAmount) {
        this.xAmount = xAmount;
    }
    public BigDecimal getxAmountNontax() {
        return xAmountNontax;
    }

    public void setxAmountNontax(BigDecimal xAmountNontax) {
        this.xAmountNontax = xAmountNontax;
    }
    public BigDecimal getxInputtax() {
        return xInputtax;
    }

    public void setxInputtax(BigDecimal xInputtax) {
        this.xInputtax = xInputtax;
    }
    public BigDecimal getDebitamount() {
        return debitamount;
    }

    public void setDebitamount(BigDecimal debitamount) {
        this.debitamount = debitamount;
    }
    public BigDecimal getApplyamountBz() {
        return applyamountBz;
    }

    public void setApplyamountBz(BigDecimal applyamountBz) {
        this.applyamountBz = applyamountBz;
    }
    public BigDecimal getxPaymentamount() {
        return xPaymentamount;
    }

    public void setxPaymentamount(BigDecimal xPaymentamount) {
        this.xPaymentamount = xPaymentamount;
    }
    public BigDecimal getxPaymentamountXj() {
        return xPaymentamountXj;
    }

    public void setxPaymentamountXj(BigDecimal xPaymentamountXj) {
        this.xPaymentamountXj = xPaymentamountXj;
    }
    public BigDecimal getxPaymentamountSp() {
        return xPaymentamountSp;
    }

    public void setxPaymentamountSp(BigDecimal xPaymentamountSp) {
        this.xPaymentamountSp = xPaymentamountSp;
    }
    public BigDecimal getxPaymentamountBl() {
        return xPaymentamountBl;
    }

    public void setxPaymentamountBl(BigDecimal xPaymentamountBl) {
        this.xPaymentamountBl = xPaymentamountBl;
    }
    public BigDecimal getxOffsetamountBz() {
        return xOffsetamountBz;
    }

    public void setxOffsetamountBz(BigDecimal xOffsetamountBz) {
        this.xOffsetamountBz = xOffsetamountBz;
    }
    public String getNov() {
        return nov;
    }

    public void setNov(String nov) {
        this.nov = nov;
    }
    public String getHxNov() {
        return hxNov;
    }

    public void setHxNov(String hxNov) {
        this.hxNov = hxNov;
    }
    public String getDatekey() {
        return datekey;
    }

    public void setDatekey(String datekey) {
        this.datekey = datekey;
    }
    public BigDecimal getQpjeHs() {
        return qpjeHs;
    }

    public void setQpjeHs(BigDecimal qpjeHs) {
        this.qpjeHs = qpjeHs;
    }
    public BigDecimal getQpjeBhs() {
        return qpjeBhs;
    }

    public void setQpjeBhs(BigDecimal qpjeBhs) {
        this.qpjeBhs = qpjeBhs;
    }
    public BigDecimal getQpjeKdk() {
        return qpjeKdk;
    }

    public void setQpjeKdk(BigDecimal qpjeKdk) {
        this.qpjeKdk = qpjeKdk;
    }
    public BigDecimal getApplyamountBzRg() {
        return applyamountBzRg;
    }

    public void setApplyamountBzRg(BigDecimal applyamountBzRg) {
        this.applyamountBzRg = applyamountBzRg;
    }
    public String getFq() {
        return fq;
    }

    public void setFq(String fq) {
        this.fq = fq;
    }
    public String getIssb() {
        return issb;
    }

    public void setIssb(String issb) {
        this.issb = issb;
    }
    public String getGz() {
        return gz;
    }

    public void setGz(String gz) {
        this.gz = gz;
    }
    public Date getEtlTime() {
        return etlTime;
    }

    public void setEtlTime(Date etlTime) {
        this.etlTime = etlTime;
    }
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getTjStatus() {
        return tjStatus;
    }

    public void setTjStatus(String tjStatus) {
        this.tjStatus = tjStatus;
    }
    public Date getTjDate() {
        return tjDate;
    }

    public void setTjDate(Date tjDate) {
        this.tjDate = tjDate;
    }
    public BigDecimal getCsll() {
        return csll;
    }

    public void setCsll(BigDecimal csll) {
        this.csll = csll;
    }

    @Override
    public String toString() {
        return "DwCtProjectPayment{" +
            "id=" + id +
            ", yearv=" + yearv +
            ", periodv=" + periodv +
            ", subjcode=" + subjcode +
            ", subjname=" + subjname +
            ", djbh=" + djbh +
            ", explanation=" + explanation +
            ", laiyuan=" + laiyuan +
            ", contractcode=" + contractcode +
            ", httypename=" + httypename +
            ", contractname=" + contractname +
            ", xDfprovidernames=" + xDfprovidernames +
            ", jsstate=" + jsstate +
            ", xTotalamountTax=" + xTotalamountTax +
            ", xTotalamountNontax=" + xTotalamountNontax +
            ", localdebitamountJjfy=" + localdebitamountJjfy +
            ", localdebitamount=" + localdebitamount +
            ", localdebitamountTd=" + localdebitamountTd +
            ", localdebitamountQq=" + localdebitamountQq +
            ", localdebitamountJa=" + localdebitamountJa +
            ", localdebitamountJc=" + localdebitamountJc +
            ", localdebitamountPt=" + localdebitamountPt +
            ", localdebitamountHj=" + localdebitamountHj +
            ", localdebitamountGcxg=" + localdebitamountGcxg +
            ", localdebitamountGcyg=" + localdebitamountGcyg +
            ", localdebitamountGchx=" + localdebitamountGchx +
            ", localdebitamountKfjj=" + localdebitamountKfjj +
            ", qpAmountHs1=" + qpAmountHs1 +
            ", qpAmount=" + qpAmount +
            ", qpAmountHs=" + qpAmountHs +
            ", creditamount=" + creditamount +
            ", xAmount=" + xAmount +
            ", xAmountNontax=" + xAmountNontax +
            ", xInputtax=" + xInputtax +
            ", debitamount=" + debitamount +
            ", applyamountBz=" + applyamountBz +
            ", xPaymentamount=" + xPaymentamount +
            ", xPaymentamountXj=" + xPaymentamountXj +
            ", xPaymentamountSp=" + xPaymentamountSp +
            ", xPaymentamountBl=" + xPaymentamountBl +
            ", xOffsetamountBz=" + xOffsetamountBz +
            ", nov=" + nov +
            ", hxNov=" + hxNov +
            ", datekey=" + datekey +
            ", qpjeHs=" + qpjeHs +
            ", qpjeBhs=" + qpjeBhs +
            ", qpjeKdk=" + qpjeKdk +
            ", applyamountBzRg=" + applyamountBzRg +
            ", fq=" + fq +
            ", issb=" + issb +
            ", gz=" + gz +
            ", etlTime=" + etlTime +
            ", companyName=" + companyName +
            ", tjStatus=" + tjStatus +
            ", tjDate=" + tjDate +
            ", csll=" + csll +
        "}";
    }
}
