package com.example.mybroadcast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.Window;
import android.webkit.WebView;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	TextView t1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		t1=(TextView)findViewById(R.id.textView1);
		Typeface face = Typeface.createFromAsset(getAssets(),"b.ttf");
    	t1.setTypeface(face);
    	
	}

}

