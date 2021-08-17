package com.longfor.fsscreport.cpu.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longfor.fsscreport.cpu.entity.ReceiveAlarm;
import com.longfor.fsscreport.cpu.mapper.CpuReceivealarmMapper;
import com.longfor.fsscreport.cpu.service.ICpuReceivealarmService;

/**
 * <p>
 * 监控看板 服务实现类
 * </p>
 *
 * @author chenziyao
 * @since 2020-11-19
 */
@Service
public class CpuReceivealarmServiceImpl extends ServiceImpl<CpuReceivealarmMapper, ReceiveAlarm> implements ICpuReceivealarmService {

	
	
	
	@Override
	public Integer saveBean(ReceiveAlarm race)throws Exception{
		
		
		save(race);
		
		return race.getId();
	}
}
