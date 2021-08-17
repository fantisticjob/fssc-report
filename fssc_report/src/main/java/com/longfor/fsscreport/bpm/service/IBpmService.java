package com.longfor.fsscreport.bpm.service;

import java.util.List;

import com.longfor.fsscreport.vo.BpmCreateProcessReqParam;


/**
 * bpm流程处理接口
 * @author de'l'l
 *
 */
public interface IBpmService {

    /**
     *
     * @param userCode
     * @param string 
     * @param param
     * @return instanceId
     */
    String createProcess(String userCode,String username, BpmCreateProcessReqParam param);

	void updateStateByInstanceId(String string);


	public List<String> getTaskId(String userCode, String param);

	/**
	 * 根据流程id 得到taskId
	 */
	String withdrawalProcess(String userCode, String param);

	String getRoleName(String flag, List<Object> roleInfo);

}
