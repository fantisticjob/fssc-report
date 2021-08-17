package com.longfor.fsscreport.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.alibaba.fastjson.JSONObject;
import com.longfor.fsscreport.approval.entity.FrProcessInfo;
import com.longfor.fsscreport.approval.mapper.CommonDao;
import com.longfor.fsscreport.approval.service.IFrProcessInfoService;
import com.longfor.fsscreport.bpm.service.IBpmService;
import com.longfor.fsscreport.vo.BpmCreateProcessReqParam;
import com.longfor.fsscreport.vo.Message;

/**
 * @author lishuang11
 * @date 2020-08-07 18:38
 **/
@RunWith(MockitoJUnitRunner.Silent.class)
public class BpmControllerTest {
    @InjectMocks
    private BpmController bpmController;
    @Mock
    private IBpmService bpmService;
    @Mock
    private IFrProcessInfoService ifs;
    @Mock
    private CommonDao cdao;

    public void testLoadUserById_success() {
    	BpmCreateProcessReqParam param = new BpmCreateProcessReqParam();
    	when(cdao.selectCount(any(String.class))).thenReturn(1);
    	Message message = bpmController.createProcess("1", "2", "3",param);
    	Assert.assertEquals("0", message.getCode());
    	
    	
    	when(cdao.selectCount(any(String.class))).thenReturn(0);
       // when(bpmService.createProcess(any(String.class), any(BpmCreateProcessReqParam.class))).thenReturn("123");
        when(cdao.update( any(String.class))).thenReturn(1);
        message = bpmController.createProcess("1", "2", "3",param);
    	Assert.assertEquals("0", message.getCode());
    	
    	
    	when(cdao.selectCount(any(String.class))).thenReturn(0);
    	//when(bpmService.createProcess(any(String.class), any(BpmCreateProcessReqParam.class))).thenReturn("123");
    	 when(cdao.update( any(String.class))).thenReturn(1);
        when(ifs.save(any(FrProcessInfo.class))).thenReturn(true);
    	message = bpmController.createProcess("1", "2", "3",param);
    	Assert.assertEquals("1", message.getCode());
       
		when(cdao.selectCount(any(String.class))).thenThrow(new RuntimeException("异常"));
		message = bpmController.createProcess("1", "2", "3",param);
	    Assert.assertEquals("0", message.getCode());
    }
    
   
    public void processId() {
    	
    	BpmCreateProcessReqParam param = new BpmCreateProcessReqParam();
    	when(cdao.selectMap(any(String.class))).thenReturn(anyMaps());
    	when(cdao.selectCount(any(String.class))).thenReturn(1);
    	Message message = bpmController.processId("1", "2",param);
    	Assert.assertEquals("0", message.getCode());
    	
    	when(cdao.selectMap(any(String.class))).thenReturn(anyMaps());
    	when(cdao.selectCount(any(String.class))).thenReturn(0);
    	//when(bpmService.createProcess(any(String.class), any(BpmCreateProcessReqParam.class))).thenReturn("123");
    	when(cdao.update(any(String.class))).thenReturn(0);
    	message = bpmController.processId("1", "2",param);
    	Assert.assertEquals("0", message.getCode());
    	
    	when(cdao.selectMap(any(String.class))).thenReturn(anyMaps());
    	when(cdao.selectCount(any(String.class))).thenReturn(0);
    	//when(bpmService.createProcess(any(String.class), any(BpmCreateProcessReqParam.class))).thenReturn("123");
    	when(cdao.update(any(String.class))).thenReturn(1);
    	when(ifs.save(any(FrProcessInfo.class))).thenReturn(false);
    	message = bpmController.processId("1", "2",param);
    	Assert.assertEquals("0", message.getCode());
    	
    	when(cdao.selectMap(any(String.class))).thenReturn(anyMaps());
    	when(cdao.selectCount(any(String.class))).thenReturn(0);
    	//when(bpmService.createProcess(any(String.class), any(BpmCreateProcessReqParam.class))).thenReturn("123");
    	when(cdao.update(any(String.class))).thenReturn(1);
    	when(ifs.save(any(FrProcessInfo.class))).thenReturn(true);
    	message = bpmController.processId("1", "2",param);
    	Assert.assertEquals("1", message.getCode());

    	when(cdao.selectMap(any(String.class))).thenThrow(new RuntimeException("异常"));
		message = bpmController.processId("1", "2", param);
	    Assert.assertEquals("0", message.getCode());
    }

    @Test
    public  void withdrawalProcess() {
    	String userCode="zhangsan";
    	String instId="151545121";
    	JSONObject json = bpmController.withdrawalProcess(null, null);
    	Assert.assertEquals("-1", json.get("status"));
    	
    	when(bpmService.getTaskId(any(String.class), any(String.class) )).thenReturn(anyListOf2());
    	json = bpmController.withdrawalProcess(userCode,instId);
    	Assert.assertEquals("0", json.get("status"));
    	
    	when(bpmService.getTaskId(any(String.class), any(String.class) )).thenReturn(anyListOf());
    	when(bpmService.withdrawalProcess(any(String.class), any(String.class) )).thenReturn("0");
    	json = bpmController.withdrawalProcess(userCode,instId);
    	Assert.assertEquals("0", json.get("status"));
    	
    	when(bpmService.getTaskId(any(String.class), any(String.class) )).thenReturn(anyListOf());
    	when(bpmService.withdrawalProcess(any(String.class), any(String.class) )).thenReturn("200");
    	json = bpmController.withdrawalProcess(userCode,instId);
    	Assert.assertEquals("200", json.get("status"));
    	
    	when(bpmService.getTaskId(any(String.class), any(String.class) )).thenThrow(new RuntimeException("异常"));
    	json = bpmController.withdrawalProcess(userCode,instId);
    	Assert.assertEquals("0", json.get("status"));
    	
    	
    }
	private List<String> anyListOf() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("123");
		return list;
	}
	private List<String> anyListOf2() {
		ArrayList<String> list = new ArrayList<String>();
		return list;
	}

	private Map<String, Object> anyMaps() {
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("TABLENAME", "ba_balance_adjust_total");
		return map;
	}
}
