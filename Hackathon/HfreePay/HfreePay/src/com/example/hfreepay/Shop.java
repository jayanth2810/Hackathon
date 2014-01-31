package com.example.hfreepay;

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
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class Shop extends Activity {
	
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
	        getActionBar().setTitle("Shopping List");   

	        setContentView(R.layout.activity_shop);
	        mText = (TextView) findViewById(R.id.textView2);
	        mText.setText("Merchant id : No items Tapped");
	        t1 = (TextView) findViewById(R.id.textView3);
	        t2 = (TextView) findViewById(R.id.textView4);
	        t3 = (TextView) findViewById(R.id.textView5);
	        t4 = (TextView) findViewById(R.id.textView7);
	        b1=(Button)findViewById(R.id.button1);
	        final Intent i1=new Intent(this,Payment.class);
	        
	        b1.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					i1.putExtra("amount",Integer.toString(i));
					i1.putExtra("merc",mText.getText().toString().split(":")[1]);
					startActivity(i1);
					
				}
			});
	        
	        
	       

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
	       String s1[]=S.split(" ");
	       t1.append("\n"+s1[0]);
	       t2.append("\n"+s1[1]);
	       t3.append("\n$"+s1[2]);
	       mText.setText("Merchant id : "+s1[3]);
	       i=i+Integer.parseInt(s1[2]);
	       t4.setText("$"+Integer.toString(i));
	       
	       
	       // mText.setText(S);
	      
	    }

	    @SuppressLint("NewApi")
		@Override
	    public void onPause() {
	        super.onPause();
	        if (mAdapter != null) mAdapter.disableForegroundDispatch(this);
	    }
	}