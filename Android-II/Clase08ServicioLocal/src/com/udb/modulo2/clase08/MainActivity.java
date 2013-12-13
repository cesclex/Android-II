package com.udb.modulo2.clase08;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	private int counter = 1;
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
	public void doClick(View view) {
		switch (view.getId()) {
		case R.id.startBtn:
			Log.d(TAG, "Iniciando servicio... Contador = " + counter);
			Intent intent = new Intent(MainActivity.this,BanckgroundService.class);
			intent.putExtra("counter", counter++);
			startService(intent);
			break;
		case R.id.stopBtn:
			stopService();
		}
	}

	private void stopService() {
		Log.v(TAG, "Deteniendo Servicio...");
		if (stopService(new Intent(MainActivity.this, BanckgroundService.class)))
			Log.v(TAG, "stopService fue Exitoso");
		else
			Log.v(TAG, "stopService no fue Exitoso");
	}

	@Override
	public void onDestroy() {
		stopService();
		super.onDestroy();
	}
}
