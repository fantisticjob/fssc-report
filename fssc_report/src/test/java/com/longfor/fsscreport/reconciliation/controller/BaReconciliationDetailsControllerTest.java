package com.longfor.fsscreport.reconciliation.controller;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
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
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.longfor.fsscreport.approval.mapper.CommonDao;
import com.longfor.fsscreport.costaccount.entity.DwCtProjectPayment;
import com.longfor.fsscreport.reconciliation.entity.BaBalanceAdjustInitialize;
import com.longfor.fsscreport.reconciliation.entity.BaBalanceAdjustTotal;
import com.longfor.fsscreport.reconciliation.entity.BaReconciliationDetails;
import com.longfor.fsscreport.reconciliation.entity.StoredProcedure;
import com.longfor.fsscreport.reconciliation.service.IBaBalanceAdjustInitializeService;
import com.longfor.fsscreport.reconciliation.service.IBaBalanceAdjustTotalService;
import com.longfor.fsscreport.reconciliation.service.IBaReconciliationDetailsService;
import com.longfor.fsscreport.reconciliation.service.impl.BaBalanceAdjustTotalServiceImpl;
import com.longfor.fsscreport.reconciliation.service.impl.BaReconciliationDetailsServiceImpl;



/**
 * @author lishuang11
 * @date 2020-08-07 18:38
 **/
//@RunWith(MockitoJUnitRunner.class)
@RunWith(MockitoJUnitRunner.Silent.class) 
@SuppressWarnings("all")
public class BaReconciliationDetailsControllerTest {
	@InjectMocks
    private BaReconciliationDetailsController controller;
    
    @Mock
	private IBaBalanceAdjustTotalService ibal;
    @Mock
	private IBaReconciliationDetailsService detailsService;
    @Mock
    private CommonDao dao;
    @Mock
    private IBaBalanceAdjustInitializeService init;
    
    /****************???????????????********************/
    
    /**
     * ??????????????????
     */
    
    public void selectStatus() {
    	String  id="03085','03050";
    	String month="2020-07";
    	
    	JSONObject status = controller.selectStatus(null, null);
    	Assert.assertEquals(0, status.get("status"));
    	
    	when(detailsService.getListById(anyList())).thenReturn(anyListOf());
    	when(ibal.getOne(any(QueryWrapper.class))).thenReturn(getLock1());
    	status = controller.selectStatus(id, month);
    	Assert.assertEquals(0, status.get("status"));
    	
    	when(detailsService.getListById(anyList())).thenReturn(anyListOf());
    	when(ibal.getOne(any(QueryWrapper.class))).thenReturn(getLock());
    	when(detailsService.getReconciliationDetailBean(any(String.class))).thenReturn(getAmount2());
    	status = controller.selectStatus(id, month);
    	Assert.assertEquals(0, status.get("status"));
    	
    	
    	when(detailsService.getListById(anyList())).thenReturn(anyListOf());
    	when(ibal.getOne(any(QueryWrapper.class))).thenReturn(getLock());
    	when(detailsService.getReconciliationDetailBean(any(String.class))).thenReturn(getAmount());
    	when(detailsService.update(any(BaReconciliationDetails.class),any(UpdateWrapper.class))).thenReturn(false);
    	status = controller.selectStatus(id, month);
    	Assert.assertEquals(0, status.get("status"));
    	
    	when(detailsService.getListById(anyList())).thenReturn(anyListOf());
    	when(ibal.getOne(any(QueryWrapper.class))).thenReturn(getLock());
    	when(detailsService.getReconciliationDetailBean(any(String.class))).thenReturn(getAmount());
    	when(detailsService.update(any(BaReconciliationDetails.class),any(UpdateWrapper.class))).thenReturn(true);
    	status = controller.selectStatus(id, month);
    	Assert.assertEquals(200, status.get("status"));
    	
    	BaBalanceAdjustTotalServiceImpl totalService = mock(BaBalanceAdjustTotalServiceImpl.class);
    	when(detailsService.getListById(anyList())).thenReturn(anyListOf());
		when(totalService.getOne(any(QueryWrapper.class))).thenThrow(new RuntimeException("??????"));
		status = controller.selectStatus(id, month);
    	Assert.assertEquals(0, status.get("status"));
    }
    /**
     * ????????????????????????
     */
    
    public void notSelectStatus() {
    	String  rid="1597633872383gihgggig','1597634011619cfificef";
    	String month="2020-07";
    	JSONObject status = controller.notSelectStatus(null, null);
    	Assert.assertEquals(0, status.get("status"));
    	
    	when(detailsService.getListByMatchingNo(anyList())).thenReturn(anyList());
    	status = controller.notSelectStatus(rid, month);
    	Assert.assertEquals(0, status.get("status"));
    	
    	when(detailsService.getListByMatchingNo(anyList())).thenReturn(anyListOf());
    	when(ibal.getOne(any(QueryWrapper.class))).thenReturn(getLock1());
    	status = controller.notSelectStatus(rid, month);
    	Assert.assertEquals(0, status.get("status"));
    	
    	when(detailsService.getListByMatchingNo(anyList())).thenReturn(anyListOf());
    	when(ibal.getOne(any(QueryWrapper.class))).thenReturn(getLock());
    	when(detailsService.update(any(BaReconciliationDetails.class),any(UpdateWrapper.class))).
    	thenReturn(false);
    	status = controller.notSelectStatus(rid, month);
    	Assert.assertEquals(0, status.get("status"));
    	
    	when(detailsService.getListByMatchingNo(anyList())).thenReturn(anyListOf());
    	when(ibal.getOne(any(QueryWrapper.class))).thenReturn(getLock());
		when(detailsService.update(any(BaReconciliationDetails.class),any(UpdateWrapper.class))).
		thenReturn(true);
    	status = controller.notSelectStatus(rid, month);
    	Assert.assertEquals(200, status.get("status"));
    	
    	when(detailsService.getListByMatchingNo(anyList())).thenThrow(new RuntimeException("??????"));
    	status = controller.notSelectStatus(rid, month);
    	Assert.assertEquals(0, status.get("status"));
    }
    
    /**
     * ?????????????????????????????????????????????
     */
    
	public void lockState1() {
    	String id ="44353401040013347";
    	String month="2020-07";
    	
    	//JSONObject lockState = null;
    	JSONObject lockState = controller.lockState(null, null, null);
    	Assert.assertEquals("-1", lockState.get("status"));//????????????
    	
    	when(init.checkInit(anyList())).thenReturn(anyList());
    	lockState = controller.lockState("0", id, month);
    	Assert.assertEquals("0", lockState.get("status"));//????????????????????????
    	
    	when(init.checkInit(anyList())).thenReturn(anyListOf2());
		when(detailsService.getCheckAccountId(any(String.class),any(String.class),any(Integer.class)))
		.thenReturn(getJson1());
    	lockState = controller.lockState("0", id, month);
		Assert.assertEquals(0, lockState.get("status"));//?????????????????? ?????????????????????????????????
		
		when(init.checkInit(anyList())).thenReturn(anyListOf2());
		when(detailsService.getCheckAccountId(any(String.class),any(String.class),any(Integer.class)))
		.thenReturn(getJson());
		when(dao.update(any(String.class))).thenReturn(0);
    	lockState = controller.lockState("0", id, month);
		Assert.assertEquals(0, lockState.get("status"));//?????????????????????
		
		when(init.checkInit(anyList())).thenReturn(anyListOf2());
		when(detailsService.getCheckAccountId(any(String.class),any(String.class),any(Integer.class)))
		.thenReturn(getJson());
		when(dao.update(any(String.class))).thenReturn(1);
    	lockState = controller.lockState("0", id, month);
		Assert.assertEquals(0, lockState.get("status"));//?????????????????????
		
		
		when(init.checkInit(anyList())).thenReturn(anyListOf2());
		when(dao.update(any(String.class))).thenReturn(1);
    	lockState = controller.lockState("1", id, month);
		Assert.assertEquals(0, lockState.get("status"));//?????????????????????
		
		when(init.checkInit(anyList())).thenReturn(anyListOf2());
		when(dao.update(any(String.class))).thenReturn(0);
    	lockState = controller.lockState("1", id, month);
		Assert.assertEquals(0, lockState.get("status"));//?????????????????????
		
		when(init.checkInit(anyList())).thenThrow(new RuntimeException("??????"));  //????????????
		lockState = controller.lockState("1", id, month);
		Assert.assertEquals(0, lockState.get("status"));
    }
    
	/**
	 * ??????????????????
	 */
    
    public void submit() {
    	String id ="3000360";
    	String month="2020-07";
    	
    	JSONObject lockState = controller.submit("", null); 
		Assert.assertEquals("-1", lockState.get("status")); //????????????
    	
    	when(dao.selects(any(String.class))).thenReturn(ListString());
    	when(init.checkInit(anyList())).thenReturn(anyList());
    	lockState = controller.submit(id, month); 
		Assert.assertEquals("0", lockState.get("status")); //????????????????????????
		
		when(dao.selects(any(String.class))).thenReturn(ListString());
		when(init.checkInit(anyList())).thenReturn(anyListOf2());
		when(ibal.update(any(BaBalanceAdjustTotal.class), any(UpdateWrapper.class))).thenReturn(false);
		lockState = controller.submit(id, month); 
		Assert.assertEquals("0", lockState.get("status")); //??????????????????
		
		when(dao.selects(any(String.class))).thenReturn(ListString());
    	when(init.checkInit(anyList())).thenReturn(anyListOf2());
		when(ibal.update(any(BaBalanceAdjustTotal.class), any(UpdateWrapper.class))).thenReturn(true);
    	lockState = controller.submit(id, month); 
		Assert.assertEquals("200", lockState.get("status")); //??????????????????
		
		when(dao.selects(any(String.class))).thenReturn(ListString());
		when(init.checkInit(anyList())).thenThrow(new RuntimeException("??????"));  //????????????
		lockState = controller.submit(id, month); 
		Assert.assertEquals("0", lockState.get("status")); 
    }
    
    /**
     * ????????????
     */
    
	public void notSubmit() {
		String id ="3000360";
    	String month="2020-07";
    	
		JSONObject json = controller.notSubmit(null, null);
    	Assert.assertEquals("-1", json.get("status")); //????????????
		
    	when(dao.selectStrings(any(String.class))).thenReturn(ListString());
    	when(init.checkInit(anyList())).thenReturn(anyList());
    	json = controller.notSubmit(id, month);
    	Assert.assertEquals("0", json.get("status")); //??????????????????
    	
    	when(dao.update(any(String.class))).thenReturn(1);
    	when(init.checkInit(anyList())).thenReturn(anyListOf2());
    	json = controller.notSubmit(id, month);
    	Assert.assertEquals("200", json.get("status")); //????????????????????????
    	
    	when(dao.update(any(String.class))).thenReturn(0);
    	when(init.checkInit(anyList())).thenReturn(anyListOf2());
    	json = controller.notSubmit(id, month);
    	Assert.assertEquals("0", json.get("status")); //????????????????????????
    	
    	when(dao.selectStrings(any(String.class))).thenReturn(ListString());
		when(init.checkInit(anyList())).thenThrow(new RuntimeException("??????"));  //????????????
		Assert.assertEquals("0", json.get("status")); 
	}

    /**
     * ??????????????????
     */
	
    public void preservation() {
		String id="656900554210866";
		String month="2020-06";
		
		JSONObject object = controller.notSubmit(null, null);
    	Assert.assertEquals("-1", object.get("status")); //????????????
    	
    	when(init.checkInit(anyList())).thenReturn(anyList());
    	object = controller.preservation(id,month);
    	Assert.assertEquals("0", object.get("status")); //??????????????????
    	
    	when(init.checkInit(anyList())).thenReturn(anyListOf2());
		when(detailsService.getCheckAccountId(any(String.class),any(String.class),any(Integer.class))) .thenReturn(getJson());
		object = controller.preservation(id,month);
		Assert.assertEquals("200", object.get("status")); //??????????????????
		
		when(init.checkInit(anyList())).thenReturn(anyListOf2());
		when(detailsService.getCheckAccountId(any(String.class),any(String.class),any(Integer.class))) .thenReturn(getJson2());
		object = controller.preservation(id,month);
		Assert.assertEquals("0", object.get("status")); //??????????????????
		
		when(init.checkInit(anyList())).thenThrow(new RuntimeException("??????"));  //????????????
		object = controller.preservation(id,month);
		Assert.assertEquals("0", object.get("status")); 
    }
	 
	 /**
	  * ???????????????????????????
	  */
	
	public void updateStoredProcedure() {
		BaBalanceAdjustTotalServiceImpl impl = mock(BaBalanceAdjustTotalServiceImpl.class);
		doNothing().when(impl).updateStoredProcedure(any(String.class), any(String.class));
		JSONObject object = controller.updateStoredProcedure("656900554210866", "2020-06");
		System.err.println(object.toString());
		Assert.assertEquals(200, object.get("status")); //??????????????????
			 
	}
    /**
     *  ???????????????????????????
     */
	
    public void initAdjust() {
    	when(dao.selectCount(any(String.class))).thenReturn(0);
    	when(dao.delete(any(String.class))).thenReturn(1);
    	JSONObject object = controller.initAdjust("656900554210866", "2020-06");
    	System.err.println(object.toString());
    	Assert.assertEquals(0, object.get("status")); //????????????????????????
    	
    	when(dao.selectCount(any(String.class))).thenReturn(2);
    	when(dao.delete(any(String.class))).thenReturn(1);
    	object = controller.initAdjust("656900554210866", "2020-06");
    	Assert.assertEquals(200, object.get("status")); //?????????????????????
    	
    	object = controller.initAdjust(null, null);
    	Assert.assertEquals(-1, object.get("status")); //?????????????????????
    }
    @Test
    public void oneTouchTotalSubmit() {
    	String accountId="656900554210866"; String month="2020-08";
    	JSONObject object = controller.oneTouchTotalSubmit(null, null);
		Assert.assertEquals("-1", object.get("status"));
		
		
    }
    /**
     * ?????????????????????
     * @param accountsId
     * @param month
     * @return
     */
    public void oneTouchTotalVal() {
		String accountId="656900554210866"; String month="2020-08";
		
		JSONObject object = controller.oneTouchTotalVal(null, null);
		Assert.assertEquals("-1", object.get("status"));
		
		
		when(dao.selectMaps(any(String.class))).thenReturn(anyListMaps2());
		object = controller.oneTouchTotalVal(accountId, month);
		Assert.assertEquals("0", object.get("status"));
		
		when(dao.selectMaps(any(String.class))).thenReturn(anyListMaps());
		//when(detailsService.checkNc(any(String.class),any(String.class))).thenReturn(getJson());
		object = controller.oneTouchTotalVal(accountId, month);
		Assert.assertEquals("200", object.get("status"));
		
		when(dao.selectMaps(any(String.class))).thenThrow(new RuntimeException("??????"));  //????????????
		object = controller.oneTouchTotalVal(accountId, month);
		Assert.assertEquals("500", object.get("status"));
		
    }

	private List<Map<String, Object>> anyListMaps() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("ACCOUNT_TIME","4551346541212");
		map.put("ACCOUNT_ID","2020-08");
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list.add(map);
		return list;
	}
	private List<Map<String, Object>> anyListMaps2() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		return list;
	}
	/*****************???????????????********************/
	
	public List<String> ListString() {
		List<String> list = new ArrayList<String>();
		list.add("755950329010906");
		list.add("656900554210866");
		return list;
	}
	private JSONObject getJson() {
    	JSONObject json = new JSONObject();
    	json.put("status","200");
		return json;
	}
	private JSONObject getJson1() {
		JSONObject json = new JSONObject();
		json.put("status","0");
		return json;
	}
	private JSONObject getJson2() {
    	JSONObject json = new JSONObject();
    	json.put("status","0");
		return json;
	}
	private List<BaBalanceAdjustInitialize> anyListOf2() {
		List<BaBalanceAdjustInitialize> list = new ArrayList<BaBalanceAdjustInitialize>();
		BaBalanceAdjustInitialize initialize = new BaBalanceAdjustInitialize();
		initialize.setId(new BigDecimal(1545));
		list.add(initialize);
		return list;
	}
	private BaReconciliationDetails getAmount() {
		BaReconciliationDetails details = new BaReconciliationDetails();
		details.setLsAmount(new BigDecimal(1111));
		details.setNcAmount(new BigDecimal(1111));
		return details;
	}
	private BaReconciliationDetails getAmount2() {
		BaReconciliationDetails details = new BaReconciliationDetails();
		details.setLsAmount(new BigDecimal(1));
		details.setNcAmount(new BigDecimal(1111));
		return details;
	}
	//??????????????????
	private BaBalanceAdjustTotal getLock() {
		BaBalanceAdjustTotal details = new BaBalanceAdjustTotal();
		details.setLockStatus("0");
		return details;
	}
	//??????????????????
	private BaBalanceAdjustTotal getLock1() {
		BaBalanceAdjustTotal details = new BaBalanceAdjustTotal();
		details.setLockStatus("1");
		return details;
	}
	private List<BaReconciliationDetails> anyListOf() {
		ArrayList<BaReconciliationDetails> list = new ArrayList<BaReconciliationDetails>();
		BaReconciliationDetails details = new BaReconciliationDetails();
		list.add(details);
		return list;
	}
}
