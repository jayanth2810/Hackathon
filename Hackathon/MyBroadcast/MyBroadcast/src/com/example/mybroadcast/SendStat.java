package com.example.mybroadcast;

import android.telephony.SmsManager;

public class SendStat {
	
	public void sendsms (String phone,String status, String name)
	   {
	      name=name.replace("_", " ");
		  SmsManager sms = SmsManager.getDefault();
	      if(status.equalsIgnoreCase("d"))
	      {
	    	  System.out.println(" f sms sent to "+phone);
	    	  sms.sendTextMessage(phone, null, "Hi "+name+",\nNumber already registered!\nNote :\nSend DELETE to completely remove your account!:)", null, null);  
	      }
	      else
	      {
	    	  System.out.println("sms sent to "+phone);
	    	  sms.sendTextMessage(phone, null, "Hi "+name+",\nYou are registered succesfully\nKey: "+status+"\n#DON'T DISCARD \nTo opt out send DELETE", null, null);  
	      }
	       
	    }

}
