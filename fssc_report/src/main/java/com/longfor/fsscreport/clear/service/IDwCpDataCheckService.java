package com.longfor.fsscreport.clear.service;

import com.longfor.fsscreport.clear.entity.DwCpDataCheck;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 往来清理-数据校验表 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-11-13
 */
public interface IDwCpDataCheckService extends IService<DwCpDataCheck> {

	Integer deleteByParm(QueryWrapper<DwCpDataCheck> queryWrapper);

}
