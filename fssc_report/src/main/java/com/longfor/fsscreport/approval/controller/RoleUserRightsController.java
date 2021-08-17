package com.longfor.fsscreport.approval.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.longfor.fsscreport.approval.entity.RoleUserRelation;
import com.longfor.fsscreport.approval.service.IRoleUserRelationService;
import com.longfor.fsscreport.utils.PushOAMessage;

/**
 * <p>
 *	角色用户权限表 前端控制器
 * </p>
 *
 * @author chenziyao
 * @since 2020-07-30
 */

@RestController
@RequestMapping("/roleuser")
public class RoleUserRightsController {

    private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private IRoleUserRelationService roleUserRelationService;
	/**
     * 更新ao代办
     * @param accountId
     * @param month
     * @return
     */
    @CrossOrigin(origins = "*",maxAge = 3600)
    @PostMapping("/updataOaAgency")
    @ResponseBody
    public JSONObject updataOaAgency(String area,String month) {
    	
    	log.info("更新ao代办参数：{}",area);
    	log.info("更新ao代办参数：{}",month);
    	JSONObject json= new JSONObject();
		try {
		
			List<RoleUserRelation> list = roleUserRelationService.getList(area, month);
			
			
			for (int i = 0; i < list.size(); i++) {
				
				if( list.get(i)!=null && StringUtils.isNotBlank(list.get(i).getUuid())) {
					
					JSONObject jsonString = JSONObject.parseObject(new String(PushOAMessage.doPostJson(list.get(i).getUuid())));
					if(jsonString!=null && "0".equals(jsonString.getString("code"))) {
						json.put("msg","更新成功");
						json.put("code","200");
					}else {
						
						json.put("msg","更新失败");
						json.put("code","-1");
					}
				}else {
					
					json.put("msg","关系表为空");
					json.put("code","-1");
				}
			}
			
		} catch (Exception e) {
			log.error("更新ao代办异常:{}",e);
			json.put("msg","更新异常");
			json.put("code","-2");
		}
		return json;
    }
}
