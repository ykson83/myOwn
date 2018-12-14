package com.ksign.prod.util;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import com.ksign.prod.ctx.ConstantContext;

import lombok.Setter;

@Setter
public class Tag extends SimpleTagSupport{

	private String str = "";
	private String name = "";
	

	@Override
	public void doTag() throws JspException, IOException {
		try {
			if(name.equals("type")) { //pd_type일 경우
				for(int i : ConstantContext.PD_TYPE) {
					str += "<option value ='" + i + "'>" + ConstantContext.toPDTypeStr(i) + "</option>";
				}
			} else if(name.equals("status")) { //status인 경우
				for(int i : ConstantContext.STATUS) {
					str += "<option value='" + i + "'>" + ConstantContext.toStatusStr(i) + "</option>";
				}
			}
			
			getJspContext().getOut().write(str);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
