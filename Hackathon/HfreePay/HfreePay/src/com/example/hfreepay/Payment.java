package com.example.hfreepay;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Payment extends Activity {

	
	TextView t1,t2,t3;
	Button b1;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_payment);
		t1=(TextView)findViewById(R.id.textView6);
		t2=(TextView)findViewById(R.id.textView8);
		final String data = getIntent().getExtras().getString("amount");
		final String data1 = getIntent().getExtras().getString("merc");
		t2.setText("$"+ data);
		t1.setText(data1);
		final Intent i1=new Intent(this,Pay.class);
		
		b1=(Button)findViewById(R.id.button1);
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				i1.putExtra("amount", data);
				i1.putExtra("merc",data1);
				startActivity(i1);
				
				
				
			}
		});
		
		
	}

}
