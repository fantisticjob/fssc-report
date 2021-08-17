package com.longfor.fsscreport.approval.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 角色账户关系表
 * </p>
 *
 * @author chenziyao
 * @since 2021-01-13
 */
@TableName("ROLE_USER_RELATION")
@KeySequence("ROLE_USER_RELATION_S")
public class RoleUserRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.INPUT)
    private BigDecimal id;

    /**
     * 地区
     */
    @TableField("area")
    private String area;

    /**
     * 月份
     */
    @TableField("MONTH")
    private String month;

    /**
     * 账户
     */
    @TableField("ACCOUNT")
    private String account;

    /**
     * uuid
     */
    @TableField("UUID")
    private String uuid;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }
    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
}
