package com.longfor.fsscreport.approval.service.impl;

import com.longfor.fsscreport.approval.entity.RoleUserRelation;
import com.longfor.fsscreport.approval.mapper.RoleUserRelationMapper;
import com.longfor.fsscreport.approval.service.IRoleUserRelationService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色账户关系表 服务实现类
 * </p>
 *
 * @author chenziyao
 * @since 2021-01-13
 */
@Service
public class RoleUserRelationServiceImpl extends ServiceImpl<RoleUserRelationMapper, RoleUserRelation> implements IRoleUserRelationService {

	
	@Autowired
	private  RoleUserRelationMapper mapper;
	
	@Override
	public List<RoleUserRelation> getList(String area ,String month){
		
		QueryWrapper<RoleUserRelation> wrapper = new  QueryWrapper<>();
		wrapper.eq("area", area);
		wrapper.eq("month", month);
		return mapper.selectList(wrapper);
	}
	
}
