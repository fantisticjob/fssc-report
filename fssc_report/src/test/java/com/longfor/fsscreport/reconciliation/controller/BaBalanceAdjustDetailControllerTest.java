package com.longfor.fsscreport.reconciliation.controller;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.alibaba.fastjson.JSONObject;
import com.longfor.fsscreport.reconciliation.service.IBaBalanceAdjustDetailService;

/**
 * @author lishuang11
 * @date 2020-08-07 18:38
 **/
@RunWith(MockitoJUnitRunner.Silent.class)
public class BaBalanceAdjustDetailControllerTest {
    @InjectMocks
    private BaBalanceAdjustDetailController controller;
    
    @Mock
	private IBaBalanceAdjustDetailService ibs;
    

    @Test
    public void testLoadUserById_success() {
    	String ddd="{user:'liliping',headerid:'228',id:'[412796,413054]'}";
    	JSONObject detail = controller.copyDetail("{user:'liliping'}");
    	Assert.assertEquals(-1, detail.get("status"));
    	
    	when(ibs.insertDetal(any(String.class))).thenReturn(200);
    	detail = controller.copyDetail(ddd);
    	Assert.assertEquals(200, detail.get("status"));
    	
    	when(ibs.insertDetal(any(String.class))).thenReturn(0);
    	detail = controller.copyDetail(ddd);
    	Assert.assertEquals(0, detail.get("status"));
    	
		when(ibs.insertDetal(any(String.class))).thenThrow(new RuntimeException("异常")); detail = controller.copyDetail(ddd);
		Assert.assertEquals(0, detail.get("status"));
		 
    }
    
}
