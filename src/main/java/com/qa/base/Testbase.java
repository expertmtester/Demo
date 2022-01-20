package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Testbase {

	public Properties prop;
	
	public int Response_status_code_200=200;
	public int Response_status_code_500=500;
	public int Response_status_code_201=201;

	public Testbase()
	{
		try{
			 prop=new Properties();
		
		FileInputStream fis=new FileInputStream("C:\\Users\\Ankit\\eclipse-workspace\\restapi2\\src\\main\\java\\com\\qa\\config\\config.properties");
		prop.load(fis);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		}
}
