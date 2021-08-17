package com.longfor.fsscreport.xycq.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.longfor.fsscreport.xycq.entity.DwShareExportTable;

/**
 * <p>
 * 线下导入表 服务类
 * </p>
 *
 * @author chenziyao
 * @since 2020-12-25
 */
public interface IDwShareExportTableService extends IService<DwShareExportTable> {

	JSONObject saveList(String type);

}
