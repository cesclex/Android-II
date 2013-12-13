package com.udb.modulo2.clase01;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {
	private GoogleMap mMap;
	private EditText edtLatitud, edtLongitud;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    	setUpMap();
        edtLatitud = (EditText) findViewById(R.id.edtLatitud);
        edtLongitud = (EditText) findViewById(R.id.edtLongitud);

    }
    @Override
	protected void onResume() {
		super.onResume();
		setUpMap();
	}

	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
        case R.id.menu_location:
        	double latitud = Double.valueOf(edtLatitud.getText().toString());
        	double longitud = Double.valueOf(edtLongitud.getText().toString());
        	 mMap.addMarker(new MarkerOptions().position(new LatLng(latitud, longitud)).title("Marker"));
        	 break;
        case R.id.menu_clear:
        	mMap.clear();
        	break;
        }
        return true;
    }
	
    private void setUpMap() {
        if (mMap == null) {
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
