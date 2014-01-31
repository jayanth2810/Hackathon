package com.example.hfreepay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcAdapter.CreateNdefMessageCallback;
import android.nfc.NfcEvent;
import android.os.Bundle;
import android.os.Parcelable;
import android.telephony.TelephonyManager;
import android.widget.TextView;
import android.widget.Toast;
import java.nio.charset.Charset;
import android.provider.Settings;


@SuppressLint({ "InlinedApi", "NewApi" })
public class Pay extends Activity implements CreateNdefMessageCallback {
    NfcAdapter mNfcAdapter;
    TextView textView;
    String imeistring="";                        
    String imsistring="";
    String macAddress="";
    String maint="";
    String androidId="";
    

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        TextView textView = (TextView) findViewById(R.id.textView1);
        textView.setText("Make Payment");
        // Check for available NFC Adapter
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (mNfcAdapter == null) {
            Toast.makeText(this, "NFC is not available", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        // Register callback
        mNfcAdapter.setNdefPushMessageCallback(this, this);
    }
    public String createMessage()
    {
    	 String androidId = Settings.Secure.getString(getContentResolver(), 
                                   Settings.Secure.ANDROID_ID);

         TelephonyManager   telephonyManager  = 
         (TelephonyManager)getSystemService( Context.TELEPHONY_SERVICE );
         imeistring = telephonyManager.getDeviceId();
         imsistring = telephonyManager.getSubscriberId();                                      
         WifiManager wifiManager =
         (WifiManager) getSystemService(Context.WIFI_SERVICE);
         WifiInfo wInfo = wifiManager.getConnectionInfo();
         macAddress = wInfo.getMacAddress();
         if(macAddress==null) macAddress="";
         androidId = Settings.Secure.getString(getContentResolver(), 
                       Settings.Secure.ANDROID_ID);
         final String data = getIntent().getExtras().getString("amount");
 		final String data1 = getIntent().getExtras().getString("merc");
         
       return imsistring+" "+imeistring+" "+macAddress+" "+androidId+" "+data+" "+data1;
         
               
    }

    @Override
    public NdefMessage createNdefMessage(NfcEvent event) {
    	
    	String s=createMessage();
        NdefMessage msg = new NdefMessage(NdefRecord.createMime("application/vnd.com.example.android.beam",
        	   s.getBytes(Charset.forName("US-ASCII"))));
        
       return msg;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Check to see that the Activity started due to an Android Beam
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
            processIntent(getIntent());
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        // onResume gets called after this to handle the intent
        setIntent(intent);
    }

    /**
     * Parses the NDEF Message from the intent and prints to the TextView
     */
    void processIntent(Intent intent) {
        textView = (TextView) findViewById(R.id.textView1);
        Parcelable[] rawMsgs = intent.getParcelableArrayExtra(
                NfcAdapter.EXTRA_NDEF_MESSAGES);
        // only one message sent during the beam
        NdefMessage msg = (NdefMessage) rawMsgs[0];
        // record 0 contains the MIME type, record 1 is the AAR, if present
        textView.setText(new String(msg.getRecords()[0].getPayload()));
    }
}