package com.udb.modulo2.clase34;

import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

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
	public void connect(View view){
    	try{
    		WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
    		WifiConfiguration wc = new WifiConfiguration();
    		wc.SSID = "\"".concat("dlink").concat("\"");
    		wc.status = WifiConfiguration.Status.DISABLED;
    		wc.priority = 40;
    		//OPEN NETWORK
    		/*wc.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
    		wc.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
    		wc.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
    		wc.allowedAuthAlgorithms.clear();
    		wc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
    		wc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
    		wc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
    		wc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
    		wc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
    		wc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
    	    */
    	    //WEP
    		
    		/*String password="55D52D33F3A78D61162DB444FC";
    		wc.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
    		wc.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
    		wc.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
    		wc.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
    		wc.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.SHARED);
    		wc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
    		wc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
    		wc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
    		wc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
    		 
    		if (password.matches("[0-9A-F]+")){
    			wc.wepKeys[0] = password;
    		}
    		else{
    			wc.wepKeys[0] = "\"".concat(password).concat("\"");
    		}
    		wc.wepTxKeyIndex = 0;
    		*/
    		//WPA AND WPA2
    		String password = "CkwfFFH6S";
    		wc.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
    		wc.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
    		wc.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
    		wc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
    		wc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
    		wc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
    		wc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
    		wc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
    		wc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
    		 
    		wc.preSharedKey = "\"".concat(password).concat("\"");
    	    
    	    int res = wifi.addNetwork(wc);
    	    Log.d("WifiPreference", "add Network returned " + res );
    	    boolean b = wifi.enableNetwork(res, true);        
    	    Log.d("WifiPreference", "enableNetwork returned " + b );
    	}catch(Exception e){
    		Toast.makeText(this, "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
    	}
    }
}
