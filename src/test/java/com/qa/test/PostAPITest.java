package com.qa.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.Testbase;
import com.qa.client.Restclient;
import com.qa.data.Users;

import junit.framework.Assert;

public class PostAPITest extends Testbase {
	
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
	
	@Test
	public void PostAPITest() throws ClientProtocolException, IOException
	{
		restclient=new Restclient();
		closeableHttpResponse=restclient.get(ActualURL);
		HashMap<String, String> Headermap=new HashMap<String,String>();
		Headermap.put("Content-Type", "application/json");
		
		ObjectMapper mapper=new ObjectMapper();
		Users users=new Users("morpheus","leader");//expected users object
		
		//Object to json file
		
		mapper.writeValue(new File("C:\\Users\\Ankit\\eclipse-workspace\\restapi2\\src\\main\\java\\com\\qa\\data\\users.json"), users);
		
		//object to json in string
		String usersJSONString=mapper.writeValueAsString(users);
		System.out.println(usersJSONString);
		
		closeableHttpResponse=restclient.get(ActualURL, usersJSONString, Headermap);
		
		int Statuscode=closeableHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(Statuscode, Response_status_code_201);
		String responsestring=EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
		JSONObject jsonobject=new JSONObject(responsestring);
		System.out.println("response json from api"+jsonobject);
		
		// json to java object
		Users userResObj=mapper.readValue(responsestring, Users.class);
		System.out.println(userResObj);
		
		String user=userResObj.getName();
		String job=userResObj.getJob();
		System.out.println("uses:"+user+"job:"+job);
		
	}

}
