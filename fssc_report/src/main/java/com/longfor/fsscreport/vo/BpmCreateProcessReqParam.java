package com.longfor.fsscreport.vo;

import java.io.Serializable;


/**
 * bpm业务实体类
 * @author chenziyao
 *
 */
public class BpmCreateProcessReqParam implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 模板编码；BPM3提供
     */
    private String processDefName;
    /**
     * 我的发起里的标题
     */
    private String processInstName;
    
    /**
     * 描述
     */
    private String processInstDesc;
    
    /**
     * 平台
     */
    private String pt;
    /**
     * 地区
     */
    private String dq;
    
    /**
     * 校验
     */
    private String haveNopass;
    
    
    public String getDq() {
		return dq;
	}

	public void setDq(String dq) {
		this.dq = dq;
	}

	public BpmCreateProcessReqParam() {
    }

    public BpmCreateProcessReqParam(String processDefName, String processInstName, String processInstDesc) {
        this.processDefName = processDefName;
        this.processInstName = processInstName;
        this.processInstDesc = processInstDesc;
    }

    public String getProcessDefName() {
        return processDefName;
    }

    public void setProcessDefName(String processDefName) {
        this.processDefName = processDefName;
    }

    public String getProcessInstName() {
        return processInstName;
    }

    public void setProcessInstName(String processInstName) {
        this.processInstName = processInstName;
    }

    public String getProcessInstDesc() {
        return processInstDesc;
    }

    public void setProcessInstDesc(String processInstDesc) {
        this.processInstDesc = processInstDesc;
    }

	public String getPt() {
		return pt;
	}

	public void setPt(String pt) {
		this.pt = pt;
	}

	public String getHaveNopass() {
		return haveNopass;
	}

	public void setHaveNopass(String haveNopass) {
		this.haveNopass = haveNopass;
	}

	@Override
	public String toString() {
		return "BpmCreateProcessReqParam [processDefName=" + processDefName + ", processInstName=" + processInstName
				+ ", processInstDesc=" + processInstDesc + ", pt=" + pt + ", dq=" + dq + ", haveNopass=" + haveNopass
				+ "]";
	}
    
}
