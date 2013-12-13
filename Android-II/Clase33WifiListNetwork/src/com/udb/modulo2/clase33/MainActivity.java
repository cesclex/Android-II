package com.udb.modulo2.clase33;

import java.util.List;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	ArrayAdapter<String> networks;
	ListView listView;
	WifiManager mainWifi;
    WifiReceiver receiverWifi;

    StringBuilder sb = new StringBuilder();

    Handler handler = new Handler();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		networks =  new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        listView = (ListView) findViewById(R.id.listNetwork);
		listView.setAdapter(networks);
        
        mainWifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        receiverWifi = new WifiReceiver();
        registerReceiver(receiverWifi, new IntentFilter( WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        if(mainWifi.isWifiEnabled()==false)
        {
            mainWifi.setWifiEnabled(true);
        }


        doInback();
	}

	   public void doInback()
	    {
	        handler.postDelayed(new Runnable() {

	            @Override
	            public void run()
	            {
	                mainWifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
	                receiverWifi = new WifiReceiver();
	                registerReceiver(receiverWifi, new IntentFilter( WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
	                mainWifi.startScan();
	                doInback();
	            }
	        }, 2000);

	    }

	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.main, menu);
	        return true;
	    }
	    
	    @Override
	    protected void onPause()
	    {
	        unregisterReceiver(receiverWifi);
	        super.onPause();
	    }

	    @Override
	    protected void onResume()
	    {
	        registerReceiver(receiverWifi, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
	        super.onResume();
	    }
	    
	    class WifiReceiver extends BroadcastReceiver
	    {
	        public void onReceive(Context c, Intent intent)
	        {
	        	networks.clear();
	            sb = new StringBuilder();
	            List<ScanResult> wifiList;
	            wifiList = mainWifi.getScanResults();
	            for(int i = 0; i < wifiList.size(); i++)
	            {
	            	networks.add(wifiList.get(i).SSID+" - f:"+wifiList.get(i).frequency+" - c:"+wifiList.get(i).capabilities);
	            }


	        }
	    }

}
