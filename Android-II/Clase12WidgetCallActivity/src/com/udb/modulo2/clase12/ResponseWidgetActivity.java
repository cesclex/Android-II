package com.udb.modulo2.clase12;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class ResponseWidgetActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_response_widget);
		
		Intent intent = getIntent();
		int contador = 0;
		if(intent!=null){
			contador = intent.getIntExtra(WidgetProvider.WIDGET_KEY, 0); 
		}
		
		TextView txtContador = (TextView) findViewById(R.id.txtContador);
		txtContador.setText("Cantidad de veces: "+contador);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.response_widget, menu);
		return true;
	}

}
