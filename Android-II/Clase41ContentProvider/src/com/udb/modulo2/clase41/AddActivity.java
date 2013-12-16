package com.udb.modulo2.clase41;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.ContentValues;
import android.os.Build;
import android.provider.UserDictionary;

public class AddActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		// Show the Up button in the action bar.
		setupActionBar();
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	public void save(View view){
		
		EditText edtWord = (EditText) findViewById(R.id.editText1);
		
		Uri mNewUri=UserDictionary.Words.CONTENT_URI;
		ContentValues mNewValues = new ContentValues();
		mNewValues.put(UserDictionary.Words.APP_ID, "com.udb.modulo2.clase41");
		mNewValues.put(UserDictionary.Words.LOCALE, "en_US");
		mNewValues.put(UserDictionary.Words.WORD,edtWord.getText().toString() );
		mNewValues.put(UserDictionary.Words.FREQUENCY, "100");

		mNewUri = getContentResolver().insert(
		    mNewUri,   // the user dictionary content URI
		    mNewValues                          // the values to insert
		);
		
		Toast.makeText(this, "URI: "+mNewUri.toString(), Toast.LENGTH_LONG).show();
		finish();
	}

}