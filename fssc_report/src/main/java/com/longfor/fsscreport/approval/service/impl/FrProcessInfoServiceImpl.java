package com.longfor.fsscreport.approval.service.impl;

import com.longfor.fsscreport.approval.entity.FrProcessInfo;
import com.longfor.fsscreport.approval.mapper.FrProcessInfoMapper;
import com.longfor.fsscreport.approval.service.IFrProcessInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 流程业务信息服务实现类
 * </p>
 *
 * @author chenziyao
 * @since 2020-07-11
 */
@Service
public class FrProcessInfoServiceImpl extends ServiceImpl<FrProcessInfoMapper, FrProcessInfo> implements IFrProcessInfoService {

	@Autowired
	private FrProcessInfoMapper mapper;
	
	@Override
	public List<FrProcessInfo> getList(String instanceId) {
		
		
		QueryWrapper<FrProcessInfo> wrapper = new QueryWrapper<>();
		wrapper.eq("INSTANCE_ID", instanceId);
		return mapper.selectList(wrapper);
	}

	
	
}
