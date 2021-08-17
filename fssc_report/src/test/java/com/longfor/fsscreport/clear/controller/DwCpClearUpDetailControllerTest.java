package com.longfor.fsscreport.clear.controller;


import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.longfor.fsscreport.approval.mapper.CommonDao;
import com.longfor.fsscreport.clear.entity.DwCpClearUpDetail;
import com.longfor.fsscreport.clear.service.IDwCpClearUpDetailService;
import com.longfor.fsscreport.clear.service.IDwCpClearUpTotalService;

/**
 * <p>
 * 往来清理-往来明细类 前端控制器
 * </p>
 *
 * @author chenziyao
 * @param <E>
 * @since 2020-10-28
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class DwCpClearUpDetailControllerTest  {

	@InjectMocks
    private DwCpClearUpDetailController controller;
	
	@Autowired
	private IDwCpClearUpDetailService iDwCpClearUpDetailService;
	@Autowired
	private IDwCpClearUpTotalService dwCpClearUpTotalService;
	
    @Mock
    private CommonDao cdao;
	
    

	/**
	 * 往来清理勾兑
	 * @param ids 主键id
	 * @return
	 */
    @Test
	public void updataClearDetailStatus() {
    	
    	//IDwCpClearUpDetailService mock = Mockito.mock(IDwCpClearUpDetailService.class);
        //Mockito.verify(mock).getListBeanByWrapper(new QueryWrapper<DwCpClearUpDetail>());
		//when(iDwCpClearUpDetailService.getListBeanByWrapper(anyQueryWrapper())).thenReturn(getListBean());
		JSONObject status = controller.updataClearDetailStatus("sadasdasd");
		Assert.assertEquals("-1", status.get("status"));
		
	}
	private QueryWrapper<DwCpClearUpDetail> anyQueryWrapper() {
		QueryWrapper<DwCpClearUpDetail> mock = new QueryWrapper<DwCpClearUpDetail>();
		mock.eq("", "");
		return mock;
	}
	/**
	 * 往来清理取消勾兑
	 * @param ids 主键id
	 * @return
	 */
	public JSONObject notClearDetailStatus(String uuid) {
		
		JSONObject json = new JSONObject();
		
		return json;
	}
	private List<DwCpClearUpDetail> getListBean() {
		
		ArrayList<DwCpClearUpDetail> list = new ArrayList<DwCpClearUpDetail>();
		DwCpClearUpDetail detail = new DwCpClearUpDetail();
		detail.setAmount(new BigDecimal("121231"));
		list.add(detail);
		return list;
	}
	
	
}
