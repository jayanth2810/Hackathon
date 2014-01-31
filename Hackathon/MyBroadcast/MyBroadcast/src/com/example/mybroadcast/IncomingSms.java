package com.example.mybroadcast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

@SuppressLint("DefaultLocale")
public class IncomingSms extends BroadcastReceiver
{
	final SmsManager sms=SmsManager.getDefault();
	
	@Override
	public void onReceive(Context arg0, Intent arg1) {
		final Bundle bundle = arg1.getExtras();
		 try {
             
	            if (bundle != null) {
	                 
	                final Object[] pdusObj = (Object[]) bundle.get("pdus");
	                 
	                for (int i = 0; i < pdusObj.length; i++) {
	                     
	                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
	                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();
	                     
	                    String senderNum = phoneNumber;
	                    String message = currentMessage.getDisplayMessageBody();
	                    message=message.replace(' ', '_');
	                    System.out.println(message);
	                    Log.e("SmsReceiver", "senderNum: "+ senderNum + "; message: " + message);
	                    
	                    Bupdate b1=new Bupdate();
	                    b1.getcon(message, senderNum, "asda");
	                    // messagesplit(message,senderNum);
	                    
	                   // Show Alert
	                   //int duration = Toast.LENGTH_LONG;
	                   //Toast.makeText(arg0,"updated", duration).show();
	                  //  toast.show();
	                     
	                } // end for loop
	              } // bundle is null
	 
	        } catch (Exception e) {
	            Log.e("SmsReceiver", "Exception smsReceiver" +e);
	             
	        }
		 
	    } 
	

	
	}
