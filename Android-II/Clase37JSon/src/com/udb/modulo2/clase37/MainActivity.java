package com.udb.modulo2.clase37;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	ArrayAdapter<String> elements;
	ListView listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		elements = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        listView = (ListView) findViewById(R.id.listJson);
        listView.setAdapter(elements);
        new JSONTask().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	 private class JSONTask extends AsyncTask<Void, Void, String> {
	        

	        protected void onPostExecute(String string) {
	        	String readFeed = string;
	            try {
	            	
	            	JSONObject jsonobj = new JSONObject(readFeed);
	            	JSONArray jsonArray = jsonobj.getJSONArray("items");
	              Log.i(MainActivity.class.getName(), "Numero de Entradas " + jsonArray.length());
	              for (int i = 0; i < jsonArray.length(); i++) {
	                JSONObject jsonObject = jsonArray.getJSONObject(i);
	                elements.add("Nombre: "+jsonObject.getString("name")+" quantity: "+jsonObject.getString("quantity"));
	                Log.i(MainActivity.class.getName(), jsonObject.getString("name"));
	              }
	            } catch (Exception e) {
	              e.printStackTrace();
	            }
	        }

			@Override
			protected String doInBackground(Void... params) {
				// TODO Auto-generated method stub		
				return readFeed();
			}
	        
		    public String readFeed() {
		        StringBuilder builder = new StringBuilder();
		        HttpClient client = new DefaultHttpClient();
		        HttpGet httpGet = new HttpGet("http://192.168.0.101:8080/JSONAndroid/JsonGenerateObjectModel");
		        try {
		          HttpResponse response = client.execute(httpGet);
		          StatusLine statusLine = response.getStatusLine();
		          int statusCode = statusLine.getStatusCode();
		          if (statusCode == 200) {
		            HttpEntity entity = response.getEntity();
		            InputStream content = entity.getContent();
		            BufferedReader reader = new BufferedReader(new InputStreamReader(content));
		            String line;
		            while ((line = reader.readLine()) != null) {
		              builder.append(line);
		            }
		          } else {
		            Log.e(MainActivity.class.toString(), "Failed to download file");
		          }
		        } catch (ClientProtocolException e) {
		          e.printStackTrace();
		        } catch (IOException e) {
		          e.printStackTrace();
		        }
		        return builder.toString();
		      }
	    }


}
