package com.longfor.fsscreport.approval.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.longfor.fsscreport.approval.entity.RoleUserRelation;

/**
 * <p>
 * 角色账户关系表 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2021-01-13
 */
public interface IRoleUserRelationService extends IService<RoleUserRelation> {

	List<RoleUserRelation> getList(String area, String month);

}
