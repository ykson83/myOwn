package com.ksign.prod.controller;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.api.plus.Person;
import org.springframework.social.google.api.plus.PlusOperations;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.ksign.prod.dto.OauthVO;

@Controller
public class OauthController {
	Logger log = LoggerFactory.getLogger(this.getClass());
	Gson gson = new Gson();
	
	@Autowired private GoogleConnectionFactory googleConnectionFactory;
	@Autowired private OAuth2Parameters oauth2Parameters;
	final static String apiURL = "https://www.googleapis.com/oauth2/v4/token";
	final static String calApiURL = "https://www.googleapis.com/calendar/v3/users/me/calendarList?access_token=";

	String authorization_code;
	String access_token;
	String refresh_token;
	
	//authorization_code
	@RequestMapping(value = "/oauth")
	public ModelAndView oauthCode(HttpServletRequest req, HttpServletResponse resp, OauthVO code) {
		
		//authorization_code 받기
		if(log.isDebugEnabled()) log.debug("/oauth :" + code);
		authorization_code = code.getCode().toString();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("result");
		mav.addObject("result", code);
		return mav;
	}
	
	//access_token
	@RequestMapping(value = "/oauth/token")
	public ModelAndView getToken(HttpServletRequest req, HttpServletResponse resp) {
		ModelAndView mav = new ModelAndView();
		
		HttpsURLConnection conn = null;
		OutputStreamWriter wr = null;
		BufferedReader in = null;
		StringBuffer response = null;
		
		try  {
			URL url = new URL(apiURL);
			conn = (HttpsURLConnection) url.openConnection();
			conn.setRequestMethod("POST");

			String urlParameters = "code=" + authorization_code + "&"
					+ "client_id=225613869215-3c9ar7d1srjmjp9jk2eihoq6jvmdkgsv.apps.googleusercontent.com&"
					+ "client_secret=41jtCAQi-FgFWjoB6fYIgaQK&"
					+ "redirect_uri=http%3A%2F%2Flocalhost%3A9080%2Fprod%2Foauth&"
					+ "grant_type=authorization_code";
			
			//send post request
			conn.setDoOutput(true);
			
			wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(urlParameters);
			wr.flush();
			
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String inputLine;
			response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			
			//print result
			if(log.isDebugEnabled()) log.debug(response.toString());
			
			HashMap<String, String> m = gson.fromJson(response.toString(), new TypeToken<HashMap<String, String>>(){}.getType());
			this.access_token = m.get("access_token");
			if(log.isInfoEnabled()) log.debug("access_token : " + access_token);

			mav.setViewName("result");
			mav.addObject("result", response);
			mav.addObject("access_token", access_token);

			//refresh_token
			if(m.get("refresh_token") != null) {
				if(log.isDebugEnabled()) log.debug("refresh_token : " + m.get("refresh_token").toString());
				refresh_token = m.get("refresh_token");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(in);
			close(wr);
			if(conn != null) conn.disconnect();
		}
			
		return mav;
	}
	
	
	public void close(Closeable obj) {
		try { if(obj != null) obj.close(); } catch(Exception ignored) { }
	}
	
	
	//GET으로 access_token 보내기
	@RequestMapping(value="/oauth/calendarInfo")
	public Object getCalendarInfo(HttpServletRequest req, HttpServletResponse resp) {
		if(log.isInfoEnabled()) log.debug("calendarInfo/access_token : " + access_token);
		ModelAndView mav = new ModelAndView();
		
		PrintWriter pr = null;
		HttpsURLConnection conn = null;
		BufferedReader br = null;
		StringBuffer response = null;
		
		try  {
			URL url = new URL(calApiURL + access_token);
			conn = (HttpsURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			pr = resp.getWriter();

			//send post request
			conn.setDoOutput(true);
			
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String inputLine;
			response = new StringBuffer();

			
			int respCode = conn.getResponseCode();
			if(respCode == 200) {
				br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			
			//print result
			if(log.isDebugEnabled()) log.debug(response.toString());
			
			pr.println(response);
			mav.setViewName("result");
			mav.addObject("result", response);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(br);
			if(conn != null) conn.disconnect();
		}
		
		return null;
	}
	
	
	//refresh token으로 access token 받기
	@RequestMapping(value="/oauth/refreshToken")
	public Object refreshedToken(HttpServletRequest req, HttpServletResponse resp) {
		if(log.isDebugEnabled()) log.debug("refreshToken 도착");
		ModelAndView mav = new ModelAndView();
		
		HttpsURLConnection conn = null;
		OutputStreamWriter wr = null;
		BufferedReader in = null;
		StringBuffer response = null;
		
		try  {
			URL url = new URL(apiURL);
			conn = (HttpsURLConnection) url.openConnection();
			conn.setRequestMethod("POST");

			String urlParameters = "refresh_token=" + refresh_token + "&"
					+ "client_id=225613869215-3c9ar7d1srjmjp9jk2eihoq6jvmdkgsv.apps.googleusercontent.com&"
					+ "client_secret=41jtCAQi-FgFWjoB6fYIgaQK&"
					+ "redirect_uri=http%3A%2F%2Flocalhost%3A9080%2Fprod%2Foauth&"
					+ "grant_type=refresh_token";
			
			//send post request
			conn.setDoOutput(true);
			
			wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(urlParameters);
			wr.flush();
			
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String inputLine;
			response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			
			//print result
			if(log.isDebugEnabled()) log.debug(response.toString());
			
			HashMap<String, String> m = gson.fromJson(response.toString(), new TypeToken<HashMap<String, String>>(){}.getType());
			this.access_token = m.get("access_token");
			if(log.isInfoEnabled()) log.debug("access_token : " + access_token);

			mav.setViewName("result");
			mav.addObject("result", response);
			mav.addObject("access_token", access_token);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(in);
			close(wr);
			if(conn != null) conn.disconnect();
		}
			
		return mav;
	}
	
	
	//refresh token으로 access token 받기
	@RequestMapping(value="/oauth/oauth/refreshToken")
	public Object afterRefreshedToken(HttpServletRequest req, HttpServletResponse resp) {
		if(log.isDebugEnabled()) log.debug("refreshToken 도착");
		ModelAndView mav = new ModelAndView();
		
		HttpsURLConnection conn = null;
		OutputStreamWriter wr = null;
		BufferedReader in = null;
		StringBuffer response = null;
		
		try  {
			URL url = new URL(apiURL);
			conn = (HttpsURLConnection) url.openConnection();
			conn.setRequestMethod("POST");

			String urlParameters = "refresh_token=" + refresh_token + "&"
					+ "client_id=225613869215-3c9ar7d1srjmjp9jk2eihoq6jvmdkgsv.apps.googleusercontent.com&"
					+ "client_secret=41jtCAQi-FgFWjoB6fYIgaQK&"
					+ "redirect_uri=http%3A%2F%2Flocalhost%3A9080%2Fprod%2Foauth&"
					+ "grant_type=refresh_token";
			
			//send post request
			conn.setDoOutput(true);
			
			wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(urlParameters);
			wr.flush();
			
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String inputLine;
			response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			
			//print result
			if(log.isDebugEnabled()) log.debug(response.toString());
			
			HashMap<String, String> m = gson.fromJson(response.toString(), new TypeToken<HashMap<String, String>>(){}.getType());
			this.access_token = m.get("access_token");
			if(log.isInfoEnabled()) log.debug("access_token : " + access_token);

			mav.setViewName("result");
			mav.addObject("result", response);
			mav.addObject("access_token", access_token);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(in);
			close(wr);
			if(conn != null) conn.disconnect();
		}
			
		return mav;
	}
	
	
	//access_token 받기
	@RequestMapping("/token")
	public Object test() {
		log.info("submit access_token:" + access_token);
		
		Google google = new GoogleTemplate(access_token); 
		PlusOperations plusOperations = google.plusOperations(); 
		Person person = plusOperations.getGoogleProfile();

		if(log.isDebugEnabled()) log.debug(person.getAccountEmail());
		return null;
	}
	
	//code랑 token 쉽게 받기
	public Object codeAndToken(HttpServletRequest req, HttpServletResponse resp, OauthVO code) {

		authorization_code = code.getCode().toString();

		OAuth2Operations oauthOperations = googleConnectionFactory.getOAuthOperations();
		String url = oauthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, oauth2Parameters);

		// authorization_code, redirect_uri를 주고 교환해 온 json(access_token 포함)
		AccessGrant accessGrant = oauthOperations.exchangeForAccess(code.getCode(), oauth2Parameters.getRedirectUri(), null);
		access_token = accessGrant.getAccessToken();

		log.info("access_token:" + url);
		Long expireTime = accessGrant.getExpireTime();

		if (expireTime != null && expireTime < System.currentTimeMillis()) {
			access_token = accessGrant.getRefreshToken();
			log.info("accessToken is expired. refresh token = {}", access_token);
		}

		Connection<Google> connection = googleConnectionFactory.createConnection(accessGrant);
		Google google = connection == null ? new GoogleTemplate(access_token) : connection.getApi();
		PlusOperations plusOperations = google.plusOperations();
		Person person = plusOperations.getGoogleProfile();

		if (log.isDebugEnabled()) log.debug(person.getAccountEmail());

		return "redirect:/oauth/token";
	}
	
}
