package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class Restclient {
	//getMethod without header
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException
	{
		
		CloseableHttpClient httpclient=HttpClients.createDefault();
		HttpGet httpget=new HttpGet(url);
		CloseableHttpResponse closeableHttpResponse	= httpclient.execute(httpget);
		return closeableHttpResponse;

	}
	
	//getMethod with header
	
	public CloseableHttpResponse get(String url, HashMap<String,String> Headermap) throws ClientProtocolException, IOException
	{
		
		CloseableHttpClient httpclient=HttpClients.createDefault();
		HttpGet httpget=new HttpGet(url);
		for(Map.Entry<String,String> entry : Headermap.entrySet()){
			
			httpget.addHeader(entry.getKey(), entry.getValue());
					}

		CloseableHttpResponse closeableHttpResponse	= httpclient.execute(httpget);
		return closeableHttpResponse;

	}

	//post method
	public CloseableHttpResponse get(String url, String entityString, HashMap<String,String> Headermap) throws ClientProtocolException, IOException
	{
		
		CloseableHttpClient httpclient=HttpClients.createDefault();
		HttpPost httppost=new HttpPost(url);
		
		httppost.setEntity(new StringEntity(entityString));
		for(Map.Entry<String,String> entry : Headermap.entrySet()){
			
			httppost.addHeader(entry.getKey(), entry.getValue());
					}

		CloseableHttpResponse closeableHttpResponse	= httpclient.execute(httppost);
		return closeableHttpResponse;

	}

}
