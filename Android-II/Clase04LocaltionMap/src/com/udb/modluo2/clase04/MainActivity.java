package com.udb.modluo2.clase04;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements ConnectionCallbacks,OnConnectionFailedListener,LocationListener,
OnMyLocationButtonClickListener,OnMapClickListener, OnMapLongClickListener {
	
	private GoogleMap mMap;
    private LocationClient mLocationClient;
    private TextView txtMylocation;
    private boolean firstloadlocation=false;
    
    private static final LocationRequest REQUEST = LocationRequest.create()
            .setInterval(5000)         // 5 seconds
            .setFastestInterval(16)    // 16ms = 60fps
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		txtMylocation = (TextView) findViewById(R.id.txtMyLocation);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
    protected void onResume() {
        super.onResume();
        setUpMap();
        setUpLocationClient();
        mLocationClient.connect();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mLocationClient != null) {
            mLocationClient.disconnect();
        }
    }
	
	private void setUpMap() {
        if (mMap == null) {
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
            if (mMap != null) {
                mMap.setMyLocationEnabled(true);
                mMap.setOnMyLocationButtonClickListener(this);
            }
        }
    }

    private void setUpLocationClient() {
        if (mLocationClient == null) {
            mLocationClient = new LocationClient(getApplicationContext(),
            		this,  // ConnectionCallbacks
                    this); // OnConnectionFailedListener
            mMap.setOnMapClickListener(this);
            mMap.setOnMapLongClickListener(this);
        }
    }

	@Override
	public void onConnected(Bundle connectionHint) {
		// TODO Auto-generated method stub
		mLocationClient.requestLocationUpdates(REQUEST, this);
		
	}

	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onMyLocationButtonClick() {
		// TODO Auto-generated method stub
		Toast.makeText(this, "Boton MyLocation Clicked", Toast.LENGTH_SHORT).show();
		return false;
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		txtMylocation.setText("Location = " + location);
		if(!firstloadlocation){
			LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
		    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 15);
		    mMap.animateCamera(cameraUpdate);
		    firstloadlocation=true;
		}
	}

	@Override
	public void onConnectionFailed(ConnectionResult result) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onMapLongClick(LatLng point) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "Point Long Click: "+point, Toast.LENGTH_LONG).show();
		mMap.clear();
		CircleOptions circleOptions = new CircleOptions();
		circleOptions.center(point);
		circleOptions.fillColor(Color.HSVToColor(75,new float[] {Color.BLUE,1,1}));
	    circleOptions.radius(100); 
	    circleOptions.strokeWidth(1);

	    @SuppressWarnings("unused")
		Circle circle = mMap.addCircle(circleOptions);
	}

	@Override
	public void onMapClick(LatLng point) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "Point Click: "+point, Toast.LENGTH_LONG).show();
	}

}
