package com.longfor.fsscreport.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;



/**
 * 分页工具类
 * @author chenziyao
 *
 * @param <T>
 */
public class PageVo<T> extends Message {

    private static final long serialVersionUID = 6106316980761177773L;
    private Long total;
    private List<T> data;

    public PageVo(IPage<T> page) {
		super("200","成功");
		this.total = page.getTotal();
		this.data = page.getRecords();
	}

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
