package com.longfor.fsscreport.approval.service.impl;

import com.longfor.fsscreport.approval.entity.DwFsiAccountDetail;
import com.longfor.fsscreport.approval.mapper.DwFsiAccountDetailMapper;
import com.longfor.fsscreport.approval.service.IDwFsiAccountDetailService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 资金自查帐户明细 服务实现类
 * </p>
 *
 * @author chenziyao
 * @since 2020-11-11
 */
@Service
public class DwFsiAccountDetailServiceImpl extends ServiceImpl<DwFsiAccountDetailMapper, DwFsiAccountDetail> implements IDwFsiAccountDetailService {

	
	@Autowired
	private DwFsiAccountDetailMapper mapper;
	
	@Override
	public List<DwFsiAccountDetail> getList(List<String> list){
		
		//查询业务表，根据季度 状态 地区判断流程是否存在！
		QueryWrapper<DwFsiAccountDetail> queryWrapper = new QueryWrapper<>();
		queryWrapper.in("URID", list);
		queryWrapper.isNotNull("sp");
		queryWrapper.ne("sp", "流程撤回");
		queryWrapper.ne("sp", "流程驳回");
		queryWrapper.ne("sp", "终止");
		
		return mapper.selectList(queryWrapper);
		
	}
}
