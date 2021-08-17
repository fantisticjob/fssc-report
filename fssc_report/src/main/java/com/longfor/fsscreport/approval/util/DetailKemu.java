package com.longfor.fsscreport.approval.util;


/**
 * 月度关账科目名称转换工具
 * @author de'l'l
 *
 */
public abstract class DetailKemu {

	public static final  String Judge(String msg) {
		
		
		msg=msg.replace("amp;", "");
		
		String detail=null;
		switch (msg) {
		case "全部":
			detail="0000000";
			break;
		case "科目检查-进项税":
			detail="1201001";
			break;
		case "科目检查-固定资产":
			detail="1201002";
			break;
		case "科目检查-预收收据&预收发票":
			detail="1201003";
			break;
		case "科目检查-内外部单位往来科目客商辅助":
			detail="1201004";
			break;
		case "协同检查":
			detail="1201005";
			break;
		case "科目检查-损益类科目":
			detail="1201006";
			break;
		case "现流分析-长期借款":
			detail="1201007";
			break;
		case "现流分析-保理检查":
			detail="1201008";
			break;
		case "现流分析-工程造价、研发设计、报建支出、工程相关（含税）":
			detail="1201009";
			break;
		case "现流分析-增值税及附加、土增税、企业所得税":
			detail="1201010";
			break;
		case "科目检查-银行存款":
			detail="1202001";
			break;
		case "科目检查-销项税":
			detail="1202002";
			break;
		case "现流分析-票据检查":
			detail="1202003";
			break;
		case "现流分析-管费、土地支出，构建资产、表内往来现金流":
			detail="1202004";
			break;
		case "现流分析-其他流入流出":
			detail="1202005";
			break;
		case "科目检查-过渡性科目":
			detail="1202006";
			break;
		case "科目检查-应收应付科目":
			detail="1202007";
			break;
		}
		return detail;
	}
}
