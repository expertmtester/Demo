package com.qa.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.Testbase;
import com.qa.client.Restclient;
import com.qa.util.testutil;

public class GetAPITest extends Testbase {

	Testbase testbase;
	String URL;
	String serviceurl;
	String ActualURL;
	Restclient restclient;
	CloseableHttpResponse closeableHttpResponse;
	
	@BeforeMethod
	public void setup() throws ClientProtocolException, IOException
	{
		testbase=new Testbase();
		URL=prop.getProperty("URL");
		serviceurl=prop.getProperty("ServiceURL");
		ActualURL=URL + serviceurl;
	
	}
	
	//without header
	@Test
	public void getTest() throws ClientProtocolException, IOException
	{
		restclient=new Restclient();
		closeableHttpResponse=restclient.get(ActualURL);
		
		
		//a. status code:
		int statuscode=closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("status code is:"+statuscode);
		Assert.assertEquals(statuscode, Response_status_code_200,"Status code is not 200");
		//b.Json String
		String responsestring=EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
		JSONObject jsonobject=new JSONObject(responsestring);
		System.out.println("response json from api"+jsonobject);
		// single value assertion
		
		String s=testutil.getValueByJPath(jsonobject,"/per_page");
		System.out.println("value of per page is :"+s);
		Assert.assertEquals(Integer.parseInt(s), 3);
		System.out.println(s);
		
		//Multiple value or reading from data array
		String last_name=testutil.getValueByJPath(jsonobject, "/data[0]/last_name");
		String id=testutil.getValueByJPath(jsonobject, "/data[0]/id");
		String avatar=testutil.getValueByJPath(jsonobject, "/data[0]/avatar");
		String first_name=testutil.getValueByJPath(jsonobject, "/data[0]/first_name");
		
		System.out.println("Last name:"+last_name);
		System.out.println("id:"+id);
		System.out.println("Avatar"+avatar);
		System.out.println("first name:"+first_name);
		//c. all headers
		Header [] headerArray=closeableHttpResponse.getAllHeaders();
		HashMap <String, String> allheader= new HashMap<String, String>();
		for (Header header: headerArray)
		{
			allheader.put(header.getName(), header.getValue());
		}
		System.out.println("All headers ---->"+allheader);
	}
	
	
	@Test
	public void getTestWithheader() throws ClientProtocolException, IOException
	{
		restclient=new Restclient();
		HashMap<String, String> Headermap=new HashMap<String,String>();
		Headermap.put("Content-Type", "application/json");
		
		closeableHttpResponse=restclient.get(ActualURL);
		
		
		//a. status code:
		int statuscode=closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("status code is:"+statuscode);
		Assert.assertEquals(statuscode, Response_status_code_200,"Status code is not 200");
		//b.Json String
		String responsestring=EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
		JSONObject jsonobject=new JSONObject(responsestring);
		System.out.println("response json from api"+jsonobject);
		// single value assertion
		
		String s=testutil.getValueByJPath(jsonobject,"/per_page");
		System.out.println("value of per page is :"+s);
		Assert.assertEquals(Integer.parseInt(s), 3);
		System.out.println(s);
		
		//Multiple value or reading from data array
		String last_name=testutil.getValueByJPath(jsonobject, "/data[0]/last_name");
		String id=testutil.getValueByJPath(jsonobject, "/data[0]/id");
		String avatar=testutil.getValueByJPath(jsonobject, "/data[0]/avatar");
		String first_name=testutil.getValueByJPath(jsonobject, "/data[0]/first_name");
		
		System.out.println("Last name:"+last_name);
		System.out.println("id:"+id);
		System.out.println("Avatar"+avatar);
		System.out.println("first name:"+first_name);
		//c. all headers
		Header [] headerArray=closeableHttpResponse.getAllHeaders();
		HashMap <String, String> allheader= new HashMap<String, String>();
		for (Header header: headerArray)
		{
			allheader.put(header.getName(), header.getValue());
		}
		System.out.println("All headers ---->"+allheader);
	}
}



