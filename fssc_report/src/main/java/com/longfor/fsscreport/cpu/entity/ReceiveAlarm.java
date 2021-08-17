package com.longfor.fsscreport.cpu.entity;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("CPU_RECEIVEALARM")
@KeySequence("CPU_RECEIVEALARM_S")
public class ReceiveAlarm implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @TableId(value = "ID", type = IdType.INPUT)
    private Integer id;
    private String sid;
    private String sname;
    private String node_path;
    private String nid;
    private String endpoint;
    private String priority;
    private String event_type;
    private String alert;
    private String category;
    private String status;
    private String hashid;
    private String etime;
    private String value;
    private String info;
    private String last_updator;
    private String created;
    
    private List<String> groups;
    
    private List<String> users;
    
    @TableField("groups")
    private String group;
    @TableField("users")
    private String user;
    
    private List<ReceiveAlarmDetail> detail;
    
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getNode_path() {
		return node_path;
	}
	public void setNode_path(String node_path) {
		this.node_path = node_path;
	}
	public String getNid() {
		return nid;
	}
	public void setNid(String nid) {
		this.nid = nid;
	}
	public String getEndpoint() {
		return endpoint;
	}
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getEvent_type() {
		return event_type;
	}
	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}
	public String getAlert() {
		return alert;
	}
	public void setAlert(String alert) {
		this.alert = alert;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getHashid() {
		return hashid;
	}
	public void setHashid(String hashid) {
		this.hashid = hashid;
	}
	public String getEtime() {
		return etime;
	}
	public void setEtime(String etime) {
		this.etime = etime;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getLast_updator() {
		return last_updator;
	}
	public void setLast_updator(String last_updator) {
		this.last_updator = last_updator;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public List<String> getGroups() {
		return groups;
	}
	public void setGroups(List<String> groups) {
		this.groups = groups;
	}
	public List<String> getUsers() {
		return users;
	}
	public void setUsers(List<String> users) {
		this.users = users;
	}
	public List<ReceiveAlarmDetail> getDetail() {
		return detail;
	}
	public void setDetail(List<ReceiveAlarmDetail> detail) {
		this.detail = detail;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
}
