package com.longfor.fsscreport.approval.service;

import com.longfor.fsscreport.approval.entity.DwFsiAccountDetail;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 资金自查帐户明细 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-11-11
 */
public interface IDwFsiAccountDetailService extends IService<DwFsiAccountDetail> {

	List<DwFsiAccountDetail> getList(List<String> list);

}
