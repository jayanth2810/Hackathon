package com.example.mybroadcast;


import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.telephony.SmsManager;

public class Bupdate {
	@SuppressLint("NewApi") public void getcon(String name,String phone,String Bg) throws Exception
	{
		
		HttpEntity content;
		try {
			
			 StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		     StrictMode.setThreadPolicy(policy);
		     System.out.println("Bupdate.getcon()");
		    HttpClient httpclient = new DefaultHttpClient();
		   // System.out.println("http://www.a-ligue.com/register.php?val="+phone);
		    HttpResponse response=httpclient.execute(new HttpGet("http://www.a-ligue.com/register.php?val="+name));
		    content = response.getEntity();
			String su =EntityUtils.toString(content);
			String[] str=su.split("\n");
			System.out.println(str[0].length());
			String s1=str[0].substring(0, 8);
			if(s1.equalsIgnoreCase("positive"))
			{
			 SmsManager sms = SmsManager.getDefault();
		    	sms.sendTextMessage("+917200339919", null,"Transaction Succesfull" , null, null);
			}
			else
			{
				 SmsManager sms = SmsManager.getDefault();
			    	sms.sendTextMessage("+917200339919", null,"Transaction Failed.Insufficient Balance" , null, null);
				
			}
		    	
		    //SendStat stat=new SendStat();
		  //  stat.sendsms(phone, str[0], name);
		    
		    } catch (Exception e) {
		    e.printStackTrace();
		  }
	}

}