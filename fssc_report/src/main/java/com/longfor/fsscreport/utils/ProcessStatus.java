package com.longfor.fsscreport.utils;

/**
 * <p>
 * 修改审批状态为汉字
 * </p>
 *
 * @author chenziyao
 * @since 2020-07-10
 */
public class ProcessStatus {
	
	public  static String updataStutasToString(Integer type) {
		
		String  state="";
		switch(type){
			case 1:
				 state="流程发起";
			    break;
			case 2:
				state="流程撤回";
			    break;
			case 3:
				state="流程驳回";
			    break;
			case 4:
				state="流程结束";
			    break;
			case 5:
				state="终止";
			    break;
			case 6:
				state="环节审批通过";
			    break;
			case 8:
				state="发起协商";
			    break;
			case 10:
				state="协商通过";
			    break;
			case 11:
				state="推荐成功";
			    break;
		}
		return state;
	}
}
