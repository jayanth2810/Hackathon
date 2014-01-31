package com.example.product;

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
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import android.provider.Settings;


@SuppressLint({ "InlinedApi", "NewApi" })
public class MainActivity extends Activity implements CreateNdefMessageCallback {
    NfcAdapter mNfcAdapter;
    TextView textView;
    Spinner s1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.textView1);
        add();
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
    public void add() {
		 
		s1 = (Spinner) findViewById(R.id.spinner1);
		List<String> list = new ArrayList<String>();
		list.add("1 NICard 1000 12345");
		list.add("2 BluetoothRemote 500 12345");
		list.add("3 MouseL 200 12345");
		list.add("4 Ethernetcable 250 12345");
		list.add("5 Keyboard 100 12345");
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
			android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		s1.setAdapter(dataAdapter);
	    }

    @Override
    public NdefMessage createNdefMessage(NfcEvent event) {
    	
        NdefMessage msg = new NdefMessage(NdefRecord.createMime("application/vnd.com.example.android.beam",
        		String.valueOf(s1.getSelectedItem()).getBytes(Charset.forName("US-ASCII"))));
        
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