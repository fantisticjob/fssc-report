package com.longfor.fsscreport.vo;

import java.io.Serializable;

/**
 * BPM3 回调消息
 */
public class BpmCallbackVo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BpmCallbackVo() {
    }

    public BpmCallbackVo(Long id, Integer type, String instanceId, String sponsorLoginName, String operatorLoginName, String opinion, String currentActivityId) {
        this.id = id;
        this.type = type;
        this.instanceId = instanceId;
        this.sponsorLoginName = sponsorLoginName;
        this.operatorLoginName = operatorLoginName;
        this.opinion = opinion;
        this.currentActivityId = currentActivityId;
    }

    /**
     * 主键
     */
    private Long id;

    /**
     * 回调类型
     */
    private Integer type;

    /**
     * 流程实例ID
     */
    private String instanceId;

    /**
     * 发起人LoginName
     */
    private String sponsorLoginName;

    /**
     * 操作人LoginName
     */
    private String operatorLoginName;

    /**
     * 审批意见
     */
    private String opinion;

    /**
     * 当前活动编号
     */
    private String currentActivityId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getSponsorLoginName() {
        return sponsorLoginName;
    }

    public void setSponsorLoginName(String sponsorLoginName) {
        this.sponsorLoginName = sponsorLoginName;
    }

    public String getOperatorLoginName() {
        return operatorLoginName;
    }

    public void setOperatorLoginName(String operatorLoginName) {
        this.operatorLoginName = operatorLoginName;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getCurrentActivityId() {
        return currentActivityId;
    }

    public void setCurrentActivityId(String currentActivityId) {
        this.currentActivityId = currentActivityId;
    }
}
