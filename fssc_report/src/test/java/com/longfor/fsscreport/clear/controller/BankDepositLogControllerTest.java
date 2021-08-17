package com.longfor.fsscreport.clear.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.alibaba.fastjson.JSONObject;
import com.longfor.fsscreport.approval.mapper.CommonDao;

/**
 * <p>
 * 银行余额导出===后台控制类
 * </p>
 *
 * @author zhaoxin
 * @since 2021-02-24
 */

@RunWith(MockitoJUnitRunner.Silent.class)
public class BankDepositLogControllerTest  {
	
	@InjectMocks
    private BankDepositLogController controller;
	
	@Mock
	private CommonDao dao;
	
	
	//@Test
	public void logbegin() {
		
		
		JSONObject logbegin = controller.logbegin("", "", "", "");
		Assert.assertEquals(-1, logbegin.get("status")); //测试无参1
		
		logbegin = controller.logbegin("111", "", "", "");
		Assert.assertEquals(-2, logbegin.get("status"));//测试无参2
		
		logbegin = controller.logbegin("111", "222", "", "");
		Assert.assertEquals(-3, logbegin.get("status"));//测试无参3
		
		
		logbegin = controller.logbegin("111", "222", "333", "");
		Assert.assertEquals(-4, logbegin.get("status"));//测试无参4
		
		
		when(dao.insert(any(String.class))).thenReturn(0);
		logbegin = controller.logbegin("111", "222", "333", "444");
		Assert.assertEquals(-5, logbegin.get("status"));
		
		when(dao.insert(any(String.class))).thenReturn(4);
		logbegin = controller.logbegin("111", "222", "333", "444");
		Assert.assertEquals(200, logbegin.get("status"));
	}
	
	//@Test
	public void logend() {
		
		when(dao.update(any(String.class))).thenReturn(4);
		JSONObject logend = controller.logend("111");
		Assert.assertEquals(200, logend.get("status"));
		
		when(dao.update(any(String.class))).thenReturn(0);
		logend = controller.logend("111");
		Assert.assertEquals(-1, logend.get("status"));
	}
	
	//@Test
	public void loginit() {
		
		when(dao.update(any(String.class))).thenReturn(4);
		JSONObject loginit = controller.logend("111");
		Assert.assertEquals(200, loginit.get("status"));
		
	}
	@Test
	public void deleteClearData() {
		
		
		JSONObject delete = controller.deleteClearData("111",null);
		Assert.assertEquals("-1", delete.get("status"));//测试缺参数
		
		when(dao.update(any(String.class))).thenReturn(1);
		delete = controller.deleteClearData("111","222");
		Assert.assertEquals("200", delete.get("status"));
		
		when(dao.update(any(String.class))).thenThrow(new RuntimeException("异常"));
		delete = controller.deleteClearData("111","222");
		Assert.assertEquals("-2", delete.get("status"));
		
		
	}
}
