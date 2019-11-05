package com.common.utils;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtils {
	 
	public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
	public static final MediaType FORM = MediaType.get("application/x-www-form-urlencoded");
	public static final MediaType XML = MediaType.get("application/xml; charset=utf-8");
	public static OkHttpClient client = new OkHttpClient();//可以设置为单例，perform best  
	
	 
	public static String PostJson(String url, String paramster) {
		try {
			return post(url, paramster, JSON);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String postXML(String url, String paramster) {
		try {
			return post(url, paramster, XML);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String postForm(String url, String paramster) {
		try {
			return post(url, paramster, FORM);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	private static String post(String url, String paramster,MediaType type) throws IOException {
		   //json   queryString 
		  RequestBody body = RequestBody.create(type,paramster);
		  //建造者模式。
		  Request request = new Request.Builder()
		      .url(url)
		      .post(body)
		      .build();
		  try (Response response = client.newCall(request).execute()) {
		    return response.body().string();
		  }
	}
	
	public static String get(String url) throws IOException {
		  Request request = new Request.Builder()
		      .url(url)
		      .build();
		  try (Response response = client.newCall(request).execute()) {
		    return response.body().string();
		  }
	}
}
