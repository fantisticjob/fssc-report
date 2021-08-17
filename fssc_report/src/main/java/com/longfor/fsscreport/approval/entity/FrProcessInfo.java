package com.longfor.fsscreport.approval.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 流程业务信息实体类
 * </p>
 *
 * @author chenziyao
 * @since 2020-07-11
 */
@TableName("FR_PROCESS_INFO")
@KeySequence(value = "SEQ_FR_PROCESS_ID")
public class FrProcessInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id" , type = IdType.INPUT)
    private Integer id;

    @TableField("TABLENAME")
    private String tableName;

    @TableField("INSTANCE_ID")
    private String instanceId;
    

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

}
