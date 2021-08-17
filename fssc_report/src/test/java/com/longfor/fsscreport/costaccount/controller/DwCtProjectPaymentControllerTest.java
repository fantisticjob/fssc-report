package com.longfor.fsscreport.costaccount.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.longfor.fsscreport.approval.mapper.CommonDao;
import com.longfor.fsscreport.costaccount.entity.DwCtProjectPayment;
import com.longfor.fsscreport.costaccount.service.IDwCtProjectPaymentService;
import com.longfor.fsscreport.costaccount.service.impl.DwCtProjectPaymentServiceImpl;
import com.longfor.fsscreport.reconciliation.entity.StoredProcedure;

/**
 * <p>
 * 成本台账项目付款单明细表 前端控制器
 * </p>
 *
 * @author chenziyao
 * @since 2020-08-07
 */
@RunWith(MockitoJUnitRunner.Silent.class) 
public class DwCtProjectPaymentControllerTest  {


	@InjectMocks
    private DwCtProjectPaymentController controller;
	
	@Mock
	private IDwCtProjectPaymentService idcp;
	@Mock
	private CommonDao dao;
	
	@Test
	public void updataCbtz() throws Exception {
		
		JSONObject cbtz = controller.updataCbtz(null, null, null);
		Assert.assertEquals(-1, cbtz.get("status")); //
		
		DwCtProjectPaymentServiceImpl impl = mock(DwCtProjectPaymentServiceImpl.class);
		doNothing().when(impl).updataCbtz(any(StoredProcedure.class));
	
		cbtz = controller.updataCbtz("100", "212", "521");
		Assert.assertEquals(200, cbtz.get("status")); //
		
	}
	
	
	@Test
	public void updataPayment() {
		
		JSONObject object = controller.updataPayment(null, null, null);
		Assert.assertEquals(-1, object.get("status")); //
		
		
		
		when(idcp.update(any(DwCtProjectPayment.class), any(UpdateWrapper.class))).thenReturn(false);
		object = controller.updataPayment("5412", "521", "true");
		Assert.assertEquals(0, object.get("status")); //
		
		
		when(idcp.update(any(DwCtProjectPayment.class), any(UpdateWrapper.class))).thenReturn(false);
		object = controller.updataPayment("5412", "521", "false");
		Assert.assertEquals(0, object.get("status")); //
		
		when(idcp.update(any(DwCtProjectPayment.class), any(UpdateWrapper.class))).thenReturn(true);
		object = controller.updataPayment("5412", "521", "true");
		Assert.assertEquals(200, object.get("status")); //
		
		when(idcp.update(any(DwCtProjectPayment.class), any(UpdateWrapper.class))).thenReturn(true);
		object = controller.updataPayment("5412", "521", "false");
		Assert.assertEquals(200, object.get("status")); //
		
		when(idcp.update(any(DwCtProjectPayment.class), any(UpdateWrapper.class))).thenThrow(new RuntimeException("异常"));
		object = controller.updataPayment("5412", "521", "false");
		Assert.assertEquals(0, object.get("status")); //
	}
	@Test
    public void manualConfirmation() {
    	String date="2020-08"; String company="北京百度科技股份有限公司"; String fq="1515";
    	JSONObject object = controller.manualConfirmation(null,null,null);
    	Assert.assertEquals(-1, object.get("status")); //无参测试
    	
    	when(dao.update(any(String.class))).thenReturn(0);
    	object = controller.manualConfirmation(date,company,fq);
    	Assert.assertEquals(0, object.get("status")); //无参测试
    	
    	when(dao.update(any(String.class))).thenReturn(1);
    	object = controller.manualConfirmation(date,company,fq);
    	Assert.assertEquals(200, object.get("status")); //无参测试
    	
    	when(dao.update(any(String.class))).thenThrow(new RuntimeException("异常"));
    	object = controller.manualConfirmation(date,company,fq);
    	Assert.assertEquals(0, object.get("status")); //异常测试
    	
    }
}
