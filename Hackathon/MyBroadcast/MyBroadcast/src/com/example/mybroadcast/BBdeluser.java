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

public class BBdeluser {
	@SuppressLint("NewApi") public void getcon(String phone) throws Exception
	{
		HttpEntity content;
		try {
			 SmsManager sms = SmsManager.getDefault();
			 StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		     StrictMode.setThreadPolicy(policy);
		     System.out.println("Bupdate.getcon()");
		    HttpClient httpclient = new DefaultHttpClient();
		    HttpResponse response =httpclient.execute(new HttpGet("http://jugaad.org.in/bbank/bb1.php?phone="+phone));
		    content = response.getEntity();
			String su =EntityUtils.toString(content);
			String[] str=su.split("\n");
			String[] str1=str[0].split("#");
			System.out.println(str1[0]);
			if(str1[1].equalsIgnoreCase("pass") && !str1[0].equalsIgnoreCase(""))
		      {
		    	  System.out.println("sms sent to "+phone);
		    	  sms.sendTextMessage(phone, null, "Hi "+str1[0]+",\nWe appreciate your efforts!\nYour Account has been Deactivated :)", null, null);  
		      }
			else
			{
				System.out.println("sms sent to "+phone);
		    	  sms.sendTextMessage(phone, null, "Hi "+str1[0]+",\nThis Number Does not belong\nto a MyBlood Account!\nSend ADD<space>NAME<space>BGroup", null, null);
				
			}
		    } catch (Exception e) {
		    e.printStackTrace();
		  }
	}

}