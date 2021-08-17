package com.longfor.fsscreport.cpu.entity;

import java.io.Serializable;

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
@TableName("CPU_RECEIVEALARM_TAMP")
@KeySequence("CPU_RECEIVEALARM_TAMP_S")
public class ReceiveAlarmTamp implements Serializable {
	


    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.INPUT)
    private Integer id;
    private Integer did; //ReceiveAlarmDetail  表id
    private String timestamp;
    private String value;
    private String extra;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDid() {
		return did;
	}
	public void setDid(Integer did) {
		this.did = did;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getExtra() {
		return extra;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}
}
