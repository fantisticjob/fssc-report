package com.longfor.fsscreport.cpu.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longfor.fsscreport.cpu.entity.ReceiveAlarmDetail;
import com.longfor.fsscreport.cpu.mapper.CpuReceivealarmDetailMapper;
import com.longfor.fsscreport.cpu.service.ICpuReceivealarmDetailService;

/**
 * <p>
 *  监控看板 服务实现类
 * </p>
 *
 * @author chenziyao
 * @since 2020-11-19
 */
@Service
public class CpuReceivealarmDetailServiceImpl extends ServiceImpl<CpuReceivealarmDetailMapper, ReceiveAlarmDetail> implements ICpuReceivealarmDetailService {

	
	
	@Override
	public Integer saveBean(ReceiveAlarmDetail race)throws Exception{
		
		save(race);
		
		return race.getId();
	}
}
