package com.longfor.fsscreport.cpu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.longfor.fsscreport.cpu.entity.ReceiveAlarmDetail;

/**
 * <p>
 *  监控看板 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-11-19
 */
public interface ICpuReceivealarmDetailService extends IService<ReceiveAlarmDetail> {


	Integer saveBean(ReceiveAlarmDetail race) throws Exception;

}
