package com.longfor.fsscreport.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * 全局工具类
 * </p>
 *
 * @author chenziyao
 * @since 2020-07-10
 */
public class StringUtil {

    private static final Logger log = LoggerFactory.getLogger(StringUtil.class);


	public static boolean isNotNull(Object param) {

		
		
		if (param != null && !"".equals(param) && "null".equals(param)) {
			return true;
		}
		return false;
	}

	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 根据list获取带单引号的字符串 例：'1','2'
	 * 
	 * @return
	 */
	public static String getIds(List<String> list) {
		StringBuilder ids = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {

			if (i == list.size() - 1) {

				ids.append("'" + list.get(i) + "'");
			} else {

				ids.append("'" + list.get(i) + "',");
			}
		}
		return ids.toString();
	}

	/**
	 * 获取带单引号的字符串 例：'1','2'
	 * 
	 * @return
	 */
	public static String getListStringIds(String id) {
		StringBuilder ids = new StringBuilder();
		List<String> list = getStringList(id);
		for (int i = 0; i < list.size(); i++) {

			if (i == list.size() - 1) {

				ids.append("'" + list.get(i) + "'");
			} else {

				ids.append("'" + list.get(i) + "',");
			}
		}
		return ids.toString();
	}

	public static List<String> getStringList(String id) {

		List<String> accounts = new ArrayList<>();
		if (id != null && !"".equals(id)) {
			String all = id.replaceAll("'", "");
			String[] split = all.split(",");
			for (int i = 0; i < split.length; i++) {

				accounts.add(split[i]);
			}
		}
		return accounts;
	}

	public static List<String> getNumberListId(String id) {

		List<String> accounts = new ArrayList<>();
		if (id != null && !"".equals(id)) {
			String all = id.replaceAll("'", "");
			String[] split = all.split(",");
			for (int i = 0; i < split.length; i++) {
				accounts.add(split[i]);
			}
		}
		return accounts;
	}

	/**
	 * 根据日期格式返回当前月份有多少天
	 * 
	 * @param yearMonth
	 * @return
	 */
	public static int getLastDayOfMonth(String yearMonth) {
		Integer day = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");

		try {
			Date date = format.parse(yearMonth);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			day = cal.getActualMaximum(Calendar.DATE);
		} catch (ParseException e) {
			log.error("日期转换异常{}",e.toString());
		}
		// 某年某月的最后一天
		return day;
	}

	/**
	 * 获得当年的时间
	 * 
	 * @return
	 */
	public static String getYear() {
		Calendar a = Calendar.getInstance();
		a.add(Calendar.MONTH, -1);
		int year = a.get(Calendar.YEAR);
		return String.valueOf(year);
	}

	/**
	 * 获得上个月的数据
	 * 
	 * @return
	 */
	public static String getLastMonth() {
		Calendar a = Calendar.getInstance();
		a.add(Calendar.MONTH, -1);
		int month = a.get(Calendar.MONTH);
		month = month + 1;
		String temp = "";

		if (month < 10) {
			temp = temp + "0";
			temp = temp + month;
		} else {
			temp = temp + month;
		}
		return temp;
	}

	/**
	 * 2020-01拆开年月
	 * 
	 * @param month
	 * @return
	 */
	public static Map<String, String> getYearMonth(String month) {
		HashMap<String, String> map = new HashMap<>();
		if (month.contains("-")) {
			String[] split = month.split("-");
			map.put("year", split[0]);
			map.put("month", split[1]);
		}
		return map;
	}

	/**
	 * 得到上一年至上个月的年月
	 * 
	 * @param month
	 * @return
	 */
	public static Map<String, List<String>> getOldYearMonth() {
		Map<String, List<String>> map = new HashMap<>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");// 设置日期格式
		List<String> years = new ArrayList<>();
		List<String> months = new ArrayList<>();

		String format = df.format(new Date());
		String[] split = format.split("-");
		int year = Integer.parseInt(split[0]);
		int month = Integer.parseInt(split[1]);

		for (int i = 1; i <= 12; i++) {
			years.add(year - 1 + "");
			if (i < 10) {
				months.add("0" + i);
			} else {
				months.add(i + "");
			}
		}

		for (int i = 1; i < month; i++) {
			years.add(year + "");
			if (i < 10) {
				months.add("0" + i);
			} else {
				months.add(i + "");
			}
		}
		map.put("year", years);
		map.put("month", months);
		return map;
	}
	//判断逗号一共出现了几次
	public static int FindChar(String str){
		String[] split = str.split(",");
		int length = split.length;
		return length;
	}
	
	
	//往来清理校验余额的科目 
	public static List<String> getWlqlYe(){
		
		ArrayList<String> arrayList = new ArrayList<>();
		arrayList.add("212102");
		arrayList.add("218101");
		arrayList.add("11519998");
		arrayList.add("11519999");
		arrayList.add("21810399");
		return arrayList;
	}
 	
	/**
	 * 校验往来清理的年月日
	 */
	public static Date checkDate(Long year,Long month,Long day) {
		Date parse=null;
		try {
			if (year == null || month == null || day == null) {
				throw new ParseException("日期转换异常", 1);
			}
			//年份你不是4位
			if (String.valueOf(year).length() != 4) {
				throw new ParseException("日期转换异常", 1);
			}
			String date ="";
			if(month<10) {
				date = year+"-0"+month+"-"+day;
			}else {
				date = year+"-"+month+"-"+day;
			}
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			df.setLenient(false);
			parse = df.parse(date);
		} catch (Exception e) {
			log.error("日期转换异常{}",e.toString());
			return null;
		}
		return parse;
	}

	/**
	 * 去除字符串中的回车换行符制表符号
	 */
	public static String replaceBlank(String str) {
		String dest = null;
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

/*    public static void main(String[] arg) {
		Date date1 = StringUtil.checkDate(201903l,3l,22l);
		Date date2 = StringUtil.checkDate(2020l,30l,22l);
		Date date3 = StringUtil.checkDate(2019l,11l,35l);
		Date date4 = StringUtil.checkDate(19l,null,null);
		Date date5 = StringUtil.checkDate(2021l,3l,22l);
		System.out.println("date1=" + date1);
		System.out.println("date2=" + date2);
		System.out.println("date3=" + date3);
		System.out.println("date4=" + date4);
		System.out.println("date5=" + date5);



*//*	    String testStr = "  我是大西瓜b  ";
		String testStr1 = "  我 是大西瓜b  ";
		String testStr3 = "  我是大西瓜b \n\r ";
		String testStr4 = "我是大  西瓜b \n\r ";
		String testStr5= "我是大西瓜b";

		Set<String> set = new HashSet<>();
		set.add(replaceBlank(testStr));
		set.add(replaceBlank(testStr1));
		set.add(replaceBlank(testStr3));
		set.add(replaceBlank(testStr4));
		set.add(replaceBlank(testStr5));*//*
		System.out.println(replaceBlank(null));
	}*/

	public static BigDecimal getTwoDecimal(BigDecimal big) {
		DecimalFormat df = new DecimalFormat("#.00");
		return new BigDecimal(df.format(big));
	}
	
	
	
}
