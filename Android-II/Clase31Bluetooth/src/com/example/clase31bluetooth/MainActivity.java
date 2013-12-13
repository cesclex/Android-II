package com.example.clase31bluetooth;

import java.util.Set;

import android.os.Bundle;
import android.os.Handler;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity{
	private static final int REQUEST_ENABLE_BT = 0;
	private static final int REQUEST_DISCOVERABLE_BT = 0;

	TextView msgbluetooth;
	BluetoothAdapter mBluetoothAdapter;
	ArrayAdapter<String> devices;
	Handler handler = new Handler();

	private ListView listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		msgbluetooth = (TextView) findViewById(R.id.out);
		msgbluetooth.setText("");
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		if (mBluetoothAdapter == null) {
			msgbluetooth.append("No Soportado");
		}
		devices =  new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
		listView = (ListView) findViewById(R.id.listDevices);
		listView.setAdapter(devices);

	}
	public void onTurnon(View v) {
		
		if (!mBluetoothAdapter.isEnabled()) {
			Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
			Toast.makeText(this, "Iniciando Bluetooth", Toast.LENGTH_SHORT).show();
			msgbluetooth.setText("Bluetooth On");
		}
	}
	
	public void onTurnoff(View view) {
		mBluetoothAdapter.disable();
		Toast.makeText(this, "Apagando Bluetooth", Toast.LENGTH_SHORT).show();
		msgbluetooth.setText("Bluetooth Off");
	}
	
	public void onDiscoverable(View view) {
		if (!mBluetoothAdapter.isDiscovering()) {
			Toast.makeText(this, "Device es DISCOVERABLE", Toast.LENGTH_SHORT).show();
			Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
			startActivityForResult(enableBtIntent,REQUEST_DISCOVERABLE_BT);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void findPaired(){
		if(mBluetoothAdapter.isEnabled()){
			devices.clear();
			Set<BluetoothDevice> devicespair = mBluetoothAdapter.getBondedDevices();
			for(BluetoothDevice device: devicespair){
				devices.add(device.getName());
			}
		}
	}
	
	
	public void findDevices(){
		if(mBluetoothAdapter.isEnabled()){
			devices.clear();
			IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
	        this.registerReceiver(mReceiver, filter);
	        mBluetoothAdapter.startDiscovery();
		}
 	}
	
	
	private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
        	Toast.makeText(context, "BroadcastReceiver", Toast.LENGTH_SHORT).show();
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                	devices.add(device.getName() + "-" + device.getAddress()+" no paired");
                }else{
                	devices.add(device.getName() + "-" + device.getAddress()+" paired");
                }

            } 
        }
    };

	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
        case R.id.action_search:
        	findDevices();
        	Toast.makeText(this, "Buscando Devices", Toast.LENGTH_SHORT).show();
        	break;
        case R.id.action_paired:
        	Toast.makeText(this, "Buscando Devices Paired", Toast.LENGTH_SHORT).show();
        	findPaired();
        break;
        }
        return true;
    }

}
