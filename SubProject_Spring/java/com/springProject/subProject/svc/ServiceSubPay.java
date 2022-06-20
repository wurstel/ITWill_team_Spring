package com.springProject.subProject.svc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

@Service
public class ServiceSubPay {

	@Autowired
	private ServiceImport serviceImport;
	
	public String SubPay(String customer_uid,String price,String merchant_uid,HttpSession session) {
		String token = serviceImport.getToken();
		String access_token;
		token = token.substring(token.lastIndexOf("access_token"),token.lastIndexOf("now"));
		access_token = token.substring(token.lastIndexOf(":")+2,token.lastIndexOf(",")-1); 

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(access_token);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("customer_uid", customer_uid);
		map.put("merchant_uid", (String)session.getAttribute("userId")+new Date().getTime());
		map.put("amount", price);
		map.put("name", (String)session.getAttribute("userId"));

		Gson var = new Gson();
		String json = var.toJson(map);
		System.out.println(json);
		HttpEntity<String> entity = new HttpEntity<String>(json, headers);
		
		return restTemplate.postForObject("https://api.iamport.kr/subscribe/payments/again", entity, String.class);
		
	}
	
	public String SubPaySchedule(String customer_uid,String price,String merchant_uid,HttpSession session) {
		String token = serviceImport.getToken();
		long timestamp = 0;
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd", Locale.KOREA);
		cal.add(Calendar.MINUTE, +1);
		String date = sdf.format(cal.getTime());
		try {
			Date stp = sdf.parse(date);
			timestamp = stp.getTime()/1000;
			System.out.println(timestamp);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String access_token;
		token = token.substring(token.lastIndexOf("access_token"),token.lastIndexOf("now"));
		access_token = token.substring(token.lastIndexOf(":")+2,token.lastIndexOf(",")-1); 

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(access_token);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("customer_uid", customer_uid);
		map.put("merchant_uid", (String)session.getAttribute("userId")+new Date().getTime());
		map.put("amount", Integer.parseInt(price));
		map.put("schedule_at", timestamp);

		Gson var = new Gson();
		String json = var.toJson(map);
		System.out.println(json);
		HttpEntity<String> entity = new HttpEntity<String>(json, headers);
		
		return restTemplate.postForObject("https://api.iamport.kr/subscribe/payments/schedule", entity, String.class);
		
	}
	
	
	
	
}
