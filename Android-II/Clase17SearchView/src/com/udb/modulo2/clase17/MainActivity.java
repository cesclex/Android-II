package com.udb.modulo2.clase17;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {
	ListView list;
	@SuppressWarnings({ "rawtypes", "unchecked"})
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		list=(ListView)findViewById(R.id.list);
		DBHelper helper = new DBHelper(this);
		Intent intent = getIntent();
		if (intent.getAction().equals(Intent.ACTION_SEARCH)) {
			String query = intent.getStringExtra(SearchManager.QUERY);
			ArrayList items = helper.getNamesSearch(query);
			ArrayAdapter adapter = new ArrayAdapter(this,
					android.R.layout.simple_list_item_1, items);
			list.setAdapter(adapter);
		} else {
			ArrayList items = helper.getNames();
			ArrayAdapter adapter = new ArrayAdapter(this,
					android.R.layout.simple_list_item_1, items);
			list.setAdapter(adapter);
		}
		helper.close();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		MenuItem searchItem = menu.findItem(R.id.action_search);
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) MenuItemCompat
				.getActionView(searchItem);
		searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
		return super.onCreateOptionsMenu(menu);
	}

}
