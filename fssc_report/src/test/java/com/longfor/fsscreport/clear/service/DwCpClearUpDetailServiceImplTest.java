package com.longfor.fsscreport.clear.service;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.longfor.fsscreport.approval.mapper.CommonDao;
import com.longfor.fsscreport.clear.entity.DwCpClearUpDetail;
import com.longfor.fsscreport.clear.mapper.DwCpClearUpDetailMapper;
import com.longfor.fsscreport.clear.service.impl.DwCpClearUpDetailServiceImpl;

/**
 * <p>
 * 往来清理-往来明细类 服务实现类
 * </p>
 *
 * @author chenziyao
 * @since 2020-10-28
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class DwCpClearUpDetailServiceImplTest   {

	
	
	 @InjectMocks
	 private  DwCpClearUpDetailServiceImpl service;
	
	 @Mock
	 private DwCpClearUpDetailMapper mapper;

	 @Mock
	 private IDwCpClearUpTotalService DwCpClearUpTotalService;

	 @Mock
	 private IDwCpDataCheckService dwCpDataCheckService;
		
	 @Mock
	 private CommonDao dao;
	 
	 
	 
	 @Test
	 public void getListBeanByWrapper() {
		 
		

		 
		 QueryWrapper<DwCpClearUpDetail> wrapper = new QueryWrapper<DwCpClearUpDetail>();
		 when(mapper.selectList(wrapper)).thenReturn(anyListBean());
		 List<DwCpClearUpDetail> listBeanByWrapper = service.getListBeanByWrapper(null);
		 Assert.assertEquals("4654154154454",listBeanByWrapper.get(0).getAccountsId());
	 }
	 
	// @Test
	 public void updataClearDetailStatus() {
		 
		 
		 ArrayList<Long> list = new ArrayList<Long>();
		 JSONObject json = service.updataClearDetailStatus("",null);
		 Assert.assertEquals("-1", json.get("status"));//测试无参
		 

	 }
	 
	private List<DwCpClearUpDetail> anyListBean() {
		DwCpClearUpDetail detail = new DwCpClearUpDetail();
		detail.setAccountsId("4654154154454");
		detail.setAccountsName("AAAAAAA");
		List<DwCpClearUpDetail> list = new ArrayList<DwCpClearUpDetail>();
		list.add(detail);
		return list;
	}
		 
}
