package com.udb.modulo2.clase07;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	public static final String URLID = "com.udb.modulo2.clase07.URLID";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void startService(View view){
    	try{
    		
    		Intent intent = new Intent(this, CountService.class);
    		intent.putExtra(URLID, "http://wallpaperswide.com/download/android_motherboard-wallpaper-1920x1200.jpg");
    		startService(intent);
    	}catch(Exception e){
    		Toast.makeText(this, "Error: "+e.getMessage(), Toast.LENGTH_LONG).show();
    	}
    }

}
