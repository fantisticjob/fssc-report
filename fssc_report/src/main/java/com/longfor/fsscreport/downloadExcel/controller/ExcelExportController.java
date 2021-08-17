package com.longfor.fsscreport.downloadExcel.controller;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.longfor.fsscreport.approval.mapper.CommonDao;
import com.longfor.fsscreport.downloadExcel.MyExcelExportUtil;
import com.longfor.fsscreport.downloadExcel.entity.OdsNcBalanceDetails;

/**
 * @ClassName: StudentController
 * @Description: Excel导出测试
 * @Author: sunt
 * @Date: 2019/8/30 14:59
 * @Version 1.0
 **/
@Controller
@RequestMapping("/export")
public class ExcelExportController {

    @Autowired
	private CommonDao dao;
	
    @RequestMapping("/exportNc")
    public void exportNc(HttpServletResponse response) {
        try {
        	
        	String sql ="select * " + 
        			"  from ods_nc_balance_details t" + 
        			
        			" where t.year in ('2021', '2020')";
        	
        	List<OdsNcBalanceDetails> list = dao.selectList(sql);
        	
            MyExcelExportUtil.exportExcel(list,OdsNcBalanceDetails.class,"NC外系统查询辅助余额明细","数据展示",response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}