package com.longfor.fsscreport.clear.service.impl;

import com.longfor.fsscreport.clear.entity.DwCpDataCheck;
import com.longfor.fsscreport.clear.mapper.DwCpDataCheckMapper;
import com.longfor.fsscreport.clear.service.IDwCpDataCheckService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 往来清理-数据校验表 服务实现类
 * </p>
 *
 * @author chenziyao
 * @since 2020-11-13
 */
@Service
public class DwCpDataCheckServiceImpl extends ServiceImpl<DwCpDataCheckMapper, DwCpDataCheck> implements IDwCpDataCheckService {

	@Autowired
	private DwCpDataCheckMapper mapper;

	@Override
	public Integer deleteByParm(QueryWrapper<DwCpDataCheck> queryWrapper) {
		// TODO Auto-generated method stub
		return mapper.delete(queryWrapper);
	}
	
	
}
