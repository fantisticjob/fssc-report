package com.longfor.fsscreport.utils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;


/**
 * 重点税源辅助类
 * @author chenziyao
 *
 */
public class StringSql {

	
	public static String getString(String accounts, String month) {
		
		List<String> id = StringUtil.getStringList(accounts);
		
		String sql="with w1 as" + 
				" (select a.account_id" + 
				"        ,a.account_time" + 
				"        ,sum(nvl(t.nc_amount, 0)) as ncamount" + 
				"        ,sum(nvl(t.ls_amount, 0)) as lsamount" + 
				"    from ba_balance_adjust_total a" + 
				"    left join (select *" + 
				"                from ba_reconciliation_details" + 
				"               where matching_no is not null) t" + 
				"      on a.account_id = t.account_id" + 
				"     and a.account_time = t.account_time" + 
				"   where a.accounts_id in ('"+StringUtils.strip(id.toString(),"[]")+"')" + 
				"     and a.account_time = '" +month +"'" +
				"     and a.start_date is not null" + 
				"     and (a.lock_status = 0 or a.lock_status is null)" + 
				"   group by a.account_id, a.account_time)," + 
				"w2 as" + 
				" (select a.account_id" + 
				"        ,a.account_time" + 
				"        ,a.enter_balance" + 
				"        ,a.bank_balance" + 
				"        ,count(c.id) count2" + 
				"        ,count(b.id) count1" + 
				"        ,count(b.id)" + 
				"    from ba_balance_adjust_total a" + 
				"    left join (select *" + 
				"                from tmp_ba_reconciliation_detail" + 
				"               where matching_no is null) b" + 
				"      on a.account_time = b.account_time" + 
				"     and a.account_id = b.account_id" + 
				"    left join (select *" + 
				"                from ba_reconciliation_details" + 
				"               where matching_no is null) c" + 
				"      on a.account_time = c.account_time" + 
				"     and a.account_id = c.account_id" + 
				"   where a.enter_balance = a.bank_balance" + 
				"     and a.accounts_id in ('"+StringUtils.strip(id.toString(),"[]")+"')" + 
				"     and a.account_time = '" +month +"'" +
				"     and a.start_date is not null" + 
				"     and (a.lock_status = 0 or a.lock_status is null)" + 
				"   group by a.account_id" + 
				"           ,a.account_time" + 
				"           ,a.enter_balance" + 
				"           ,a.bank_balance" + 
				"           ,a.not_reached)," + 
				"w3 as" + 
				" (select a.account_id" + 
				"        ,a.account_time" + 
				"        ,a.BANK_BALANCE_QC" + 
				"        ,a.BANK_BALANCE" + 
				"        ,nvl(lsamount, 0) lsamount" + 
				"    from ba_balance_adjust_total a" + 
				"    left join (select account_id" + 
				"                    ,account_time" + 
				"                    ,sum(nvl(t.ls_amount, 0)) as lsamount" + 
				"                from ba_reconciliation_details t" + 
				"               where t.account_time = '" +month +"'" +
				"                 AND (to_char(ls_date, 'YYYY-MM') <='" +month +"' or" +
				"                     ls_date is null)" + 
				"                 and t.matching_no is not null" + 
				"               group by account_id, account_time) b" + 
				"      on a.account_id = b.account_id" + 
				"     and a.account_time = b.account_time" + 
				"   where a.accounts_id in ('"+StringUtils.strip(id.toString(),"[]")+"')" + 
				"     and a.account_time = '" +month +"'" +
				"     and a.start_date is not null" + 
				"     and (a.lock_status = 0 or a.lock_status is null))" + 
				"select w1.account_id, w1.account_time" + 
				"  from w1, w2, w3" + 
				" where w1.account_id = w2.account_id" + 
				"   and w1.account_time = w2.account_time" + 
				"   and w1.account_id = w3.account_id" + 
				"   and w1.account_time = w3.account_time" + 
				"   and w1.ncamount = w1.lsamount" + 
				"   and w2.enter_balance = w2.bank_balance" + 
				"   and w2.count1 = 0" + 
				"   and w2.count2 = 0" + 
				"   and w3.BANK_BALANCE_QC + w3.lsamount = w3.BANK_BALANCE";
		return sql;
	}
}