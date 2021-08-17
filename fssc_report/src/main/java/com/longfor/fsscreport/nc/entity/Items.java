package com.longfor.fsscreport.nc.entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * nc 辅助余额工具
 * @author 
 *
 */
@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.FIELD)
public class Items {

   
	private String checktypecode;
	private String checktypename;
	private String checkvaluecode;
	private String checkvaluename;
	public String getChecktypecode() {
		return checktypecode;
	}
	public void setChecktypecode(String checktypecode) {
		this.checktypecode = checktypecode;
	}
	public String getChecktypename() {
		return checktypename;
	}
	public void setChecktypename(String checktypename) {
		this.checktypename = checktypename;
	}
	public String getCheckvaluecode() {
		return checkvaluecode;
	}
	public void setCheckvaluecode(String checkvaluecode) {
		this.checkvaluecode = checkvaluecode;
	}
	public String getCheckvaluename() {
		return checkvaluename;
	}
	public void setCheckvaluename(String checkvaluename) {
		this.checkvaluename = checkvaluename;
	}
	
}
