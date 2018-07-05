package com.ksign.prod.util;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.ksign.prod.dto.DataTableVO;

@Component
public class WebUtil {
	//@Value("#{adminProp['parameter.encode.type']}") String encodeType;
	public String encodeType = "UTF-8";
	public void setDateRange(HttpServletRequest req, HashMap<String, Object> param) {
		String startDate = null;
		String endDate = null;
		String eventDateRange = req.getParameter("eventDateRange");

		if(!StringUtils.isEmpty(eventDateRange)) {
			String tmp[] = eventDateRange.split("-");

			startDate = tmp[0].trim();
			endDate = tmp[1].trim();

			param.put("startDate", startDate);
			param.put("endDate", endDate);
		}
	}
	
	public void setPagi(HttpServletRequest req, DataTableVO table, HashMap<String, Object> param) {
		param.put("pageNo", table.getPageNo() + "");
		param.put("pageSize", table.getLength()  + "");
		
		param.put("orderBy", table.getOrderColumn());
		param.put("orderDir", table.getOrderDir());
		
		int status = parseInt(req.getParameter("status"), -1);
		param.put("status", status);
	}
	
	public String toKor(String s) {
		if(s == null) return null;
		
		try {
			if(!encodeType.equals("none")) return new String(s.getBytes("ISO_8859_1"), encodeType);
		} catch(Exception ignored) {
			// do nothing..
		}

		return s;
	}
	
	public int parseInt(String s, int defValue) {
		if(StringUtils.isEmpty(s)) return defValue;
		
		int value = defValue;
		try { value = Integer.parseInt(s.trim()); } catch(Exception ignored) {}
		
		return value;
	}
	
	public int parseInt(String s) {
		return parseInt(s, -1);
	}
}