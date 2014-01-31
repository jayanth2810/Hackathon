package com.example.receiver;

import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.tech.NfcF;
import android.os.Bundle;
import android.os.Parcelable;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentFilter.MalformedMimeTypeException;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	 private NfcAdapter mAdapter;
	    private PendingIntent mPendingIntent;
	    private IntentFilter[] mFilters;
	    private String[][] mTechLists;
	    private TextView mText;
	    private int mCount = 0;
	    TextView t1,t2,t3,t4;
	    Button b1;
	    int i=0;
	    int j=0;

	    @SuppressLint("NewApi")
		@Override
	    public void onCreate(Bundle savedState) {
	        super.onCreate(savedState);
	        getActionBar().setTitle("Admin Payment");   

	        setContentView(R.layout.activity_main);
	        mText = (TextView) findViewById(R.id.textView1);
	        mText.setText("Waiting for Customer");
	       
	      	       

	        mAdapter = NfcAdapter.getDefaultAdapter(this);
	        mPendingIntent = PendingIntent.getActivity(this, 0,
	                new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

	      
	        IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
	        try {
	            ndef.addDataType("*/*");
	        } catch (MalformedMimeTypeException e) {
	            throw new RuntimeException("fail", e);
	        }
	        mFilters = new IntentFilter[] {
	                ndef,
	        };

	        
	        mTechLists = new String[][] { new String[] { NfcF.class.getName() } };
	    }

	    @SuppressLint("NewApi")
		@Override
	    public void onResume() {
	        super.onResume();
	        if (mAdapter != null) mAdapter.enableForegroundDispatch(this, mPendingIntent, mFilters,
	                mTechLists);
	    }

	    @SuppressLint("NewApi")
		@Override
	    public void onNewIntent(Intent intent) {
	        Log.i("Foreground dispatch", "Discovered tag with intent: " + intent);
	        Parcelable[] rawMsgs = intent.getParcelableArrayExtra(
	                NfcAdapter.EXTRA_NDEF_MESSAGES);
	       
	        NdefMessage msg = (NdefMessage) rawMsgs[0];
	       String S=new String(msg.getRecords()[0].getPayload());
	      // SmsManager sms = SmsManager.getDefault();
	     //  sms.sendTextMessage("+917200339919", null, S, null, null);	       
	       sendSms(S);
	      
	       
	       // mText.setText(S);
	      
	    }
	    
	    public void sendSms(String s)
	    {
	    	 SmsManager sms = SmsManager.getDefault();
	    	sms.sendTextMessage("+h918056292470", null, s, null, null);
	    }

	    @SuppressLint("NewApi")
		@Override
	    public void onPause() {
	        super.onPause();
	        if (mAdapter != null) mAdapter.disableForegroundDispatch(this);
	    }
	}