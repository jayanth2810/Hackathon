package com.example.hfreepay;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Paym extends Activity {

	@SuppressLint("NewApi")
	
	EditText e1,e2;
	Button b1;
	@SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 getActionBar().setTitle("Pay Merchant"); 
		setContentView(R.layout.activity_paym);
		e1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText2);
		b1=(Button)findViewById(R.id.button1);
		final Intent i1=new Intent(this,Payment.class);
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String S1,S2;
				S1=e1.getText().toString();
				S2=e2.getText().toString();
				i1.putExtra("amount",S1);
				i1.putExtra("merc",S2);
				startActivity(i1);
				
			}
		});
		
		  
	}


}
