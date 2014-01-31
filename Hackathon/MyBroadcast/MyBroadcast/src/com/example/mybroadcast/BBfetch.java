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

public class BBfetch {
	@SuppressLint("NewApi") public void getcon(String name,String phone,String Bg,String mail,String area) throws Exception
	{
		
		HttpEntity content;
		try {
			 StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		     StrictMode.setThreadPolicy(policy);
		     System.out.println("Bupdate.getcon()");
		    HttpClient httpclient = new DefaultHttpClient();
		    System.out.println("http://jugaad.org.in/bbank/bb.php?name="+name+"&phone="+phone+"&Bg="+Bg+"&mail="+mail+"&area="+area);
		    HttpResponse response=httpclient.execute(new HttpGet("http://jugaad.org.in/bbank/bb.php?name="+name+"&phone="+phone+"&Bg="+Bg+"&mail="+mail+"&area="+area));
		    content = response.getEntity();
			String su =EntityUtils.toString(content);
			String[] str=su.split("\n");
		    SendStat stat=new SendStat();
		    stat.sendsms(phone, str[0], name);
		    
		    } catch (Exception e) {
		    e.printStackTrace();
		  }
	}

}