package com.longfor.fsscreport.cpu.entity;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 监控看板实体类
 * </p>
 *
 * @author chenziyao
 * @since 2020-11-13
 */
@TableName("CPU_RECEIVEALARM_DETAIL")
@KeySequence("CPU_RECEIVEALARM_DETAIL_S")
public class ReceiveAlarmDetail implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @TableId(value = "ID", type = IdType.INPUT)
    private Integer id;
    private Integer rid;//ReceiveAlarm 表id
    private String metric;
    private String tags;
    private List<ReceiveAlarmTamp> points;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public String getMetric() {
		return metric;
	}
	public void setMetric(String metric) {
		this.metric = metric;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public List<ReceiveAlarmTamp> getPoints() {
		return points;
	}
	public void setPoints(List<ReceiveAlarmTamp> points) {
		this.points = points;
	}
    
}
