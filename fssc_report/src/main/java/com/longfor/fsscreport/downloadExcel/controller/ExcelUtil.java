package com.longfor.fsscreport.downloadExcel.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.longfor.fsscreport.utils.GetProperties;


public class ExcelUtil {
	
    private final static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

	
	/**
	 * EXCEL导出方法：
	 * 1、将查询结果集的key值替换为坐标
	 * 2、使用XSSFWorkbook读取模板EXCEL
	 * 3、使用SXSSFWorkbook将模板EXCEL和查询结果集整体写入到一个新的EXCEL，并生成流
	 * 4、将流写入文件服务器，并返回生成EXCEL的地址名称。
	 * 
	 * 注：由于XSSFWorkbook在写入大量数据的EXCEL时会占用大量内存甚至造成内存溢出，所以需要选用SXSSFWorkbook写EXCEL
	 */
    public static HashMap<String,String> GetExcelAddredds(HttpServletRequest request, String modeName,
    		String sheet[], String[] row, List<List<Map<String, String>>> queryValue,String fileDownloadName,
    		List<Short> colors){
    	//获取模版路径
    	String modelPath = request.getSession().getServletContext().getRealPath("/formmb")
    			+ File.separator + modeName;
    	logger.info(modeName + "：" + modelPath);
    	//下载文件的名称
		String[] ExcelAddredds = new String[queryValue.size()];
		List<Map<String, Object>> values = new ArrayList<Map<String, Object>>();
		GetProperties getProperties = new GetProperties();
		String path =getProperties.getProperties("downLoadFormPath");
    	
		//将查询结果集的key值替换为坐标
		for(int i = 0 ; i < queryValue.size() ; i ++){
        	//定义集合，需要转换
        	Map<String, Object> valuesTemp = new HashMap<String, Object>();
        	String Addredds ="";
        	//固定坐标的sheet页的
        	if(row[i].indexOf("tuoguan")!=-1){
        		String sheetN = row[i].substring(8);
        		//key值
        		String key =getProperties.getProperties(sheetN+"key");
        		//坐标值
        		String coord =getProperties.getProperties(sheetN+"coord");
        		String[] a =key.split(",");
        		String[] b =coord.split(",");
        		System.out.println(queryValue.get(i).get(0));
                //将查询结果集中的key替换为坐标coord
        		Map<String, String> result1 = (Map<String, String>) replaceKeys2( (Map<String, String>) queryValue.get(i).get(0), a, b);
                
        		valuesTemp = mapValuesZB(result1,sheet[i],row[i]);
                Addredds = coord;
        	}else{
        		valuesTemp = mapValues(queryValue.get(i),sheet[i],row[i]);
        		Addredds = getAddredds(queryValue.get(i), sheet[i], row[i]);
        	}
        	
        	ExcelAddredds[i] = Addredds;
        	values.add(valuesTemp);       	
    	}
		return downloadFile(values,ExcelAddredds,row,modelPath, fileDownloadName,path,colors);
    }
    
    /**
	 * 获取具体坐标（通用方法）
	 * @param List<Map<String, String>> queryeResult
	 * @return
	 */	
	public static String getAddredds(List<Map<String, String>> queryeResult,String sheet,String startRow){
		//定义地址
		StringBuffer addres = new StringBuffer();
		//循环存入全部的坐标
		for (int i = 0 ; i < queryeResult.size() ; i ++){
			//获取第一个map的key值，列
			Map<String, String> mapTemp = queryeResult.get(i);
			//存放到一个数组中，得到需要写入的列
			String [] cols = new String[mapTemp.size()];
			int colIndex = 0;
			for (String key : mapTemp.keySet()) {
				cols[colIndex++] = key;
			}
			for (int j = 0 ; j < cols.length ; j ++){
				//存入开始sheeet页
				addres.append(sheet);
				addres.append(".");
				//行坐标自动递增
				addres.append(Integer.parseInt(startRow)+i);
				addres.append(".");
				//列坐标按查询字段结束数字取值
				Pattern p = Pattern.compile("[^0-9]"); 
				Matcher m = p.matcher(cols[j]);
				addres.append(m.replaceAll(""));
				if(j < cols.length-1){
					addres.append(",");
				}
			}
			if(i < queryeResult.size()-1){
				addres.append(",");
			}
		}
		return addres.toString();
	}
    
    /**
	 * 替换map的key值
	 * 以实际需要的格式返回map数据，格式为{"0.0.0=value,..."}
	 * @param listMap	List<Map<String, String>>
	 * @param sheet		写入的sheet页
	 * @param startRow	开始写入的行数
	 * @return
	 */
    public static Map<String, Object> mapValues(List<Map<String, String>> listMap,String sheet,String startRow){
    	// 替换map的key值
    	if(listMap.get(0) == null || listMap.get(0).size() == 0){
    		return null;
    	}
    	Map<String, Object> map = new HashMap<String, Object>();
    	String[] address = getAddreddsArray(listMap, sheet, startRow);
    	int index = 0;
    	for(int i = 0 ; i < listMap.size() ; i ++){
			Map<String, String> mapTemp = listMap.get(i);
			for (String key : mapTemp.keySet()) {
				map.put(address[index++], mapTemp.get(key));
			}
		}
		return map;
    }
    
    /**
	 * 获取坐标 传入的 List<Map<String, String>>
	 * @param queryeResult		List<Map<String, String>>		list
	 * @param sheet				String							写入sheet页
	 * @param startRow			String							开始行数
	 * @return
	 */
	public static String[] getAddreddsArray(List<Map<String, String>> queryeResult,String sheet,String startRow){
		//定义长度----------针对与报表导出，数据  总行数*总列数 < 200万	使用int类型(最大返回数据单元999999999)
		int length = 0;
		//获取长度----------返回的map可能是长短不一的
		for(int i = 0 ; i < queryeResult.size() ; i ++){
			if(queryeResult.get(i).size() == 0 || queryeResult.get(i) == null){
				String[] s = {""};
				return s;
			}
			length += queryeResult.get(i).size();
		}
		//定义地址
		String[] address = new String[length];
		int index = 0;
		//循环存入全部的坐标
		for (int i = 0 ; i < queryeResult.size() ; i ++){
			//获取第i个map的key值，列
			Map<String, String> mapTemp = queryeResult.get(i);
			//获取需要写入的列，存放到一个数组中
			String [] cols = new String[mapTemp.size()];
			int colIndex = 0;
			for (String key : mapTemp.keySet()) {
				cols[colIndex++] = key;
			}
			for (int j = 0 ; j < cols.length ; j ++){
				String str = "";
				//存入开始sheeet页
				str += sheet + ".";
				//行坐标自动递增
				str += Integer.parseInt(startRow)+i+"" + "." ;
				//列坐标按查询字段结束数字取值
				Pattern p = Pattern.compile("[^0-9]"); 
				Matcher m = p.matcher(cols[j]);
				str += m.replaceAll("");
				address[index++] = str;
			}
		}
		return address;
	}
    
    /**
     * EXCEL导入文件服务器
     * @param values		结果集
     * @param execlPoint	写入坐标
     * @param row		开始行
     * @param modelFile		模板
     * @param fileDownloadName		生成文件名
     * @param path		文件服务器路径
     * @return
     */	

    private static HashMap<String,String> downloadFile(List<Map<String, Object>> values, String[] execlPoint,String[] row,
    		String modelFile,String fileDownloadName,  String path,List<Short> colors) {
		// 下载报表
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		HashMap<String,String> resultMap =new HashMap<String,String>();
		try {
			//EXCEL写入方法，生成流
			ByteArrayOutputStream os = writeDataToExecl(modelFile, execlPoint, row, values, colors, fileDownloadName);

			byte[] content = os.toByteArray();
			InputStream is = new ByteArrayInputStream(content);
			String fileDownloadPath = path+fileDownloadName;
			File f = new File(fileDownloadPath);
			resultMap.put("fileDownloadPath", fileDownloadPath);
			FileOutputStream down = new FileOutputStream(f);
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(down);
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return resultMap;
	}
    
    /**
     * 读取EXCEL模板并写入数据
     * @param modelFile		模板excel名称
     * @param execlPoint	写入坐标
     * @param values		写入值
     * @return
     */
    public static ByteArrayOutputStream writeDataToExecl(String modelFile, String[] execlPoint, String[] row, 
    		List<Map<String, Object>> values,List<Short> colors, String fileDownloadName) {
        ByteArrayOutputStream os = null;
        FileInputStream input = null;
        try {
        	os = new ByteArrayOutputStream();
        	logger.info(modelFile);
        	input = new FileInputStream(new File(modelFile));
        	XSSFWorkbook workBook = new XSSFWorkbook(new BufferedInputStream(input));
        	SXSSFWorkbook SworkBook = new SXSSFWorkbook(100);
        	
        	for(int i = 0 ; i < values.size() ; i ++){
            	//如果没有数据，那么不执行写的操作
            	if(values.get(i).size()==0 || values.get(i) == null){
            		continue;
            	}
                String[] ss = execlPoint[i].split(",");
                //获取结束的列
                String endCell = getEndCell(values.get(i));
                //写EXCEL
                Short color=null;
                if(colors.size()>i){
                    color =colors.get(i);
                }
                
                workBook = SwriteWorkBook(SworkBook,workBook,ss, values.get(i),endCell, row[i],color);
              
            }
        	workBook.write(os);
            os.flush();
        } catch (Exception e) {
        	logger.info(e.toString());
            e.printStackTrace();
        }finally {
			try {
				if(os != null){
					os.close();
				}
			} catch (IOException e) {
	        	logger.info(e.toString());
				e.printStackTrace();
			}
			try {
				if(input != null){
					input.close();
				}
			} catch (IOException e) {
	        	logger.info(e.toString());
				e.printStackTrace();
			}
		}
        return os;  
    }
    
    /**
     * 写入EXCEL
     * @param workBook  XSSFWorkbook  一个工作薄
     * @param ss		String[]  绝对坐标
     * @param values	Map<String, Object>  与绝对值对应的map集合
     * @return
	 * @throws IOException 
     */
    public static XSSFWorkbook SwriteWorkBook(SXSSFWorkbook SworkBook,XSSFWorkbook workBook,String[] ss,
    		Map<String, Object> values,String endCell,String row,Short color) throws IOException {

		// 设置公共单元格样式，包括单元格的四周框线、单元格格式，背景色
		if (color == null) {
			color = 0;
		}
		// 一次性将统一创建样式相对于每个单元格分别创建样式，可提高写入效率
		Map<String, CellStyle> styleMap = new HashMap<String, CellStyle>();
		CellStyle styleSL = NMCellStyle(workBook, color);
		for (int i = 0; i < ss.length; i++) {
			String[] sss = ss[i].split("\\.");

			/*
			 * 思路： 1、判断该值（即坐标sss）所在的sheet模板是否已经写入SworkBook 2、
			 * 若没有写入sheet模板，将sheet模板写入SworkBook（包括样式），并将该值写入对应坐标
			 * 3、若已经读取sheet页模板，则判断是否已经写入该值所在的行 3.1、若该行已经存在，则插入数值 3.2、若该行不存在，则生成该行，并插入数据
			 * 
			 */
			XSSFSheet sheetMB = workBook.getSheetAt(Integer.parseInt(sss[0]));
			String sheetName = sheetMB.getSheetName();
			int lastRows = sheetMB.getLastRowNum();
//            SXSSFSheet sheetS = (SXSSFSheet) SworkBook.getSheet(sheetName);
			XSSFSheet sheetS1 = (XSSFSheet) workBook.getSheet(sheetName);
			// sheet页已经存在,模板已经读取，则根据坐标写入数据XSSFRow
//        	SXSSFRow RowS = (SXSSFRow) sheetS.getRow(Integer.parseInt(sss[1]));
//        	SXSSFCell cellS = (SXSSFCell) RowS.getCell(Integer.parseInt(sss[2]));

			XSSFRow RowS = (XSSFRow) sheetS1.getRow(Integer.parseInt(sss[1]));
			if (RowS == null) {
				RowS = (XSSFRow) sheetS1.createRow(Integer.parseInt(sss[1]));
				int lastCell = Integer.parseInt(endCell);
				for (int k = 0; k < lastCell + 1; k++) {
					XSSFCell cellS = (XSSFCell) RowS.getCell(k);

					if (cellS == null) {
						cellS = (XSSFCell) RowS.createCell(k);
					}
					String value = null;
					if (k == Integer.parseInt(sss[2])) {
						if (values.get(ss[i]) != null) {
							value = values.get(ss[i]).toString();
						}
					}
					cellS = setCell(workBook, cellS, RowS, value, styleSL);
				}
			} else {

				XSSFCell cellS = (XSSFCell) RowS.getCell(Integer.parseInt(sss[2]));

				if (cellS == null) {
					cellS = (XSSFCell) RowS.createCell(Integer.parseInt(sss[2]));
				}

				String value = null;
				if (values.get(ss[i]) != null) {
					value = values.get(ss[i]).toString();
				}
				cellS = setCell(workBook, cellS, RowS, value, styleSL);
			}
		}
		return workBook;
    }
    
  //设置cell的值和样式
    public static XSSFCell setCell(XSSFWorkbook SworkBook,XSSFCell cellS,XSSFRow RowS ,String value,CellStyle styleSL ){
    		//给单元格赋值
		if (value == null) {
			cellS.setCellValue("-");// 给单元格赋值
			cellS.setCellStyle(styleSL);
        } else {  	
        	cellS.setCellValue(value+"");// 给单元格赋String值
//    		cellS.setCellStyle(styleSL);
        }
		return cellS;
    }
    
    //设置cell的值和样式
    public static SXSSFCell setCell1(SXSSFWorkbook SworkBook,SXSSFCell cellS,SXSSFRow RowS ,String value,Map<String,CellStyle> styleMap ){
    		//给单元格赋值
		if (value == null) {
			cellS.setCellValue("-");// 给单元格赋值
			cellS.setCellStyle(styleMap.get("0"));
        } else {
        	String val="";
        	
        	cellS.setCellValue(value+"");// 给单元格赋String值
    		cellS.setCellStyle(styleMap.get("0"));
        }
		return cellS;
    }
    
    
    /**
     * 获取结束列
     * @param values
     * @return
     */
    private static String getEndCell(Map<String, Object> values) {
    	Integer endCell = 0;
		for (String key : values.keySet()) {
			String[] keys = key.split("\\.");
    		if(endCell < Integer.parseInt(keys[2])){
    			endCell = Integer.parseInt(keys[2]);
    		}
		}
		return endCell.toString();
	}
        
    
    /*
	 * 用于处理坐标明确的sheet页，
	 * 将查询结果集的key值与坐标进行替换
	 * */
    public static Map<String, Object> mapValuesZB(Map<String, String> listMap,String sheet,String startRow){
    	if(listMap == null || listMap.size() == 0){
    		return null;
    	}
    	Map<String, Object> map = new HashMap<String, Object>();
    		
				for(String key : listMap.keySet()){
					map.put(key, listMap.get(key));
				}
    		return map;
    }
    
    
    public static Map<String, String> replaceKeys2(Map<String, String> map,
			String[] a, String[] b) {
		Map<String,String> result = new HashMap<String,String>();
        for (String key : map.keySet()) {
            System.out.println("key= "+ key + " and value= " + map.get(key));
            for (int i = 0 ; i< a.length;i++){
                if (key.equals(a[i])){
                    result.put(b[i],map.get(key));
                }
            }
        }
        return result;
	}
    
  //设置通用Cell样式
    public static CellStyle NMCellStyle(XSSFWorkbook workBook,short color){
    	
    	
    	CellStyle style =  workBook.createCellStyle();
    	/*if(color!=0&&color!=HSSFColor.WHITE.index){
    		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        	style.setFillForegroundColor(color);
    	}*/
    	
    	style.setBorderBottom(CellStyle.BORDER_THIN);
    	style.setBorderLeft(CellStyle.BORDER_THIN);
    	style.setBorderRight(CellStyle.BORDER_THIN);
    	style.setBorderTop(CellStyle.BORDER_THIN);
    	Font sfont =  workBook.createFont();//单元格字体
    	//常规字符类型的
    	Short high = 14;
    	sfont.setFontHeightInPoints(high);//字体大小
    	sfont.setFontName("宋体");//字体类型
    	
    	style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
    	style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
    	style.setFont(sfont);
    	return style;
    }

    
}
