package com.longfor.fsscreport.cpu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.longfor.fsscreport.cpu.entity.ReceiveAlarm;

/**
 * <p>
 *  监控看板 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-11-19
 */
public interface ICpuReceivealarmService extends IService<ReceiveAlarm> {

	Integer saveBean(ReceiveAlarm race) throws Exception;
}
