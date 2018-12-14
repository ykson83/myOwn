package com.ksign.prod.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
	Logger log = LoggerFactory.getLogger(ViewController.class);

	@GetMapping(value="/views/**")
	String view(HttpServletRequest req) {
		String ctxPath = req.getContextPath();
		String reqURI = req.getRequestURI();

		String viewName = reqURI.substring((ctxPath + "/views/").length());
		
		if(log.isDebugEnabled()) log.debug("viewName is: " + viewName);
		req.setAttribute("pageHeaderName", getViewHeader(viewName));
		
		req.setAttribute("x", req.getParameter("x"));
		return viewName;
	}
	
	public String getViewHeader(String viewName) {
		if(viewName.startsWith("boardCategory")) {
			return "Board Category";
			
		} else if(viewName.startsWith("board")) {
			return "Board";
			
		} else if(viewName.startsWith("productCategory")) {
			return "Product Category";
			
		} else if(viewName.startsWith("product")) {
			return "Product";
			
		} else if(viewName.startsWith("user")) {
			return "User";
			
		} else if(viewName.startsWith("download")) {
			return "Download History";
		}
		
		return "";
	}
}