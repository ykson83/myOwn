package com.ksign.prod.auth.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginFailureHandler implements AuthenticationFailureHandler {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
    public static String DEFAULT_TARGET_PARAMETER = "spring-security-redirect-login-failure";
    private String targetUrlParameter = DEFAULT_TARGET_PARAMETER;

	public String getTargetUrlParameter() {
         return targetUrlParameter;
    }

    public void setTargetUrlParameter(String targetUrlParameter) {
         this.targetUrlParameter = targetUrlParameter;
    }	

	@Override
	public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException exception)
			throws IOException, ServletException {
		log.debug("FailureHandler : ");
		
     	resp.setContentType("application/json");
    	resp.setCharacterEncoding("utf-8");

    	ObjectMapper mapper = new ObjectMapper();
    	
    	HashMap<String, String> result = new HashMap<String, String>();
		
		result.put("result", "fail");
		result.put("message", exception.getMessage());

    	PrintWriter out = resp.getWriter();
    	out.print(mapper.writeValueAsString(result));
    	out.flush();
    	out.close();
	}
}