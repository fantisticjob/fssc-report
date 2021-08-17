package com.longfor.fsscreport.taxsources.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenziyao
 * @since 2021-04-13
 */
@TableName("DW_ZDSY_ROLE")
@KeySequence("DW_ZDSY_ROLE_S")
public class DwZdsyRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableField("ID")
    private Long id;

    /**
     * 平台
     */
    @TableField("PLATFORM")
    private String platform;

    /**
     * 地区
     */
    @TableField("AREA")
    private String area;

    /**
     * 公司名称
     */
    @TableField("ACCOUNTS_NAME")
    private String accountsName;

    /**
     * oa名称
     */
    @TableField("OA_NAME")
    private String oaName;

    /**
     * oa账号
     */
    @TableField("OA_ID")
    private String oaId;

    /**
     * 标准角色
     */
    @TableField("ROLE")
    private String role;
    
    /**
     * 标识地区还是平台（1是地区2是平台）
     */
    @TableField("FLAG")
    private String flag;
    
    public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
    public String getAccountsName() {
        return accountsName;
    }

    public void setAccountsName(String accountsName) {
        this.accountsName = accountsName;
    }
    public String getOaName() {
        return oaName;
    }

    public void setOaName(String oaName) {
        this.oaName = oaName;
    }
    public String getOaId() {
        return oaId;
    }

    public void setOaId(String oaId) {
        this.oaId = oaId;
    }
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "DwZdsyRole{" +
            "id=" + id +
            ", platform=" + platform +
            ", area=" + area +
            ", accountsName=" + accountsName +
            ", oaName=" + oaName +
            ", oaId=" + oaId +
            ", role=" + role +
        "}";
    }
}
