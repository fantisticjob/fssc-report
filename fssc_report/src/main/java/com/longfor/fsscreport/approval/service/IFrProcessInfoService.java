package com.longfor.fsscreport.approval.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.longfor.fsscreport.approval.entity.FrProcessInfo;

/**
 * <p>
 *  流程业务信息服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-07-11
 */
public interface IFrProcessInfoService extends IService<FrProcessInfo> {

	List<FrProcessInfo> getList(String instanceId);

}
