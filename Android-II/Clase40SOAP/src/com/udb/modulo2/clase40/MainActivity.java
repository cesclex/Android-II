package com.udb.modulo2.clase40;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private final String NAMESPACE = "http://service.modulo2.udb.com/";
	private final String URL = "http://192.168.0.102:8080/SOAPAndroid/AndroidWS?WSDL";
	private final String SOAP_ACTION = "http://service.modulo2.udb.com/hello";
	private final String METHOD_NAME = "hello";
	
	private EditText edtName;
	private TextView txtResponse;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		edtName = (EditText) findViewById(R.id.edtName);
        txtResponse = (TextView) findViewById(R.id.txtResponse);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void sendSOAP(View view){
    	try{
    		
    		new SOAPTask().execute();
    		
    		
    	}catch(Exception e){
    		Log.e(MainActivity.class.getName(), "Error: "+e.getMessage());
    		Toast.makeText(this, "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
    	}
    }
    
    
    private class SOAPTask extends AsyncTask<Void, Void, String> {
        
    	
        protected void onPostExecute(String string) {
        	txtResponse.setText(string);
        }

		@Override
		protected String doInBackground(Void... params) {
	
			String responseStr="";
		    SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
		    PropertyInfo helloPI = new PropertyInfo();
		    helloPI.setName("name");
		    helloPI.setValue(edtName.getText().toString());
		    helloPI.setType(String.class);

		    request.addProperty(helloPI);

		    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

		    envelope.setOutputSoapObject(request);

		    HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		 
		    try {

		        androidHttpTransport.call(SOAP_ACTION, envelope);
		        SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
		        responseStr = response.toString();
		 
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
			
			return responseStr;
		}
        

    }


}
