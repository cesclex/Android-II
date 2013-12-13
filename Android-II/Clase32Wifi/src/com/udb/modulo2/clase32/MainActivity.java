package com.udb.modulo2.clase32;

import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity implements OnClickListener{
	ToggleButton tbWIFI;
	WifiManager wifiManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tbWIFI = (ToggleButton) findViewById(R.id.toggleWIFI);
	    tbWIFI.setOnClickListener(this);
	    wifiManager = (WifiManager) getBaseContext().getSystemService(Context.WIFI_SERVICE);
	    if(wifiManager.isWifiEnabled()){
	    	tbWIFI.setChecked(true);
	    }
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		try{
			if(tbWIFI.isChecked()){
				wifiManager.setWifiEnabled(true);
				
			}else{
				wifiManager.setWifiEnabled(false);
			}
			
		}catch(Exception e){
			Toast.makeText(this, "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
		}
	}

}
