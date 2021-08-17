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
 * @since 2021-04-14
 */
@TableName("DW_ZDSY_ROLE_RELATION")
@KeySequence("DW_ZDSY_ROLE_RELATION_S")
public class DwZdsyRoleRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("ID")
    private Long id;

    @TableField("PLATFORM")
    private String platform;

    @TableField("AREA")
    private String area;

    @TableField("OA_ID")
    private String oaId;

    @TableField("UUID")
    private String uuid;

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
    public String getOaId() {
        return oaId;
    }

    public void setOaId(String oaId) {
        this.oaId = oaId;
    }
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "DwZdsyRoleRelation{" +
            "id=" + id +
            ", platform=" + platform +
            ", area=" + area +
            ", oaId=" + oaId +
            ", uuid=" + uuid +
        "}";
    }
}
