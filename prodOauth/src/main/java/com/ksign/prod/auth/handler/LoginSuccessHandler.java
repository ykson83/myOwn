package com.ksign.prod.auth.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication auth)
			throws IOException, ServletException {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("utf-8");
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		//result.put("returnUrl", req.getContextPath()+"/views/main/main");
		result.put("returnUrl", getReturnUrl(req, resp));
		result.put("result", "success");
		result.put("loginedId", auth.getName());
		
		ObjectMapper mapper = new ObjectMapper();
		
		PrintWriter out = resp.getWriter();
		out.println(mapper.writeValueAsString(result));//Json으로 바꿔 보내기
		out.flush();
		out.close();

	}
	
	
	private String getReturnUrl(HttpServletRequest request, HttpServletResponse response) {
		RequestCache requestCache = new HttpSessionRequestCache();
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		if(savedRequest == null) {
			return request.getSession().getServletContext().getContextPath() + "/views/main/main";
		}
		
		String target = savedRequest.getRedirectUrl();
		//if(!target.contains("views")) target = request.getSession().getServletContext().getContextPath() + "/views/main/main";
		return target;
	}
}
