package com.udb.modulo2.clase15;

import java.util.ArrayList;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {

	ListView list;
    
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list=(ListView)findViewById(R.id.list);

        DBHelper helper=new DBHelper(this);
        Intent intent=getIntent();
        if(intent.getAction().equals(Intent.ACTION_SEARCH)){
        		String query=intent.getStringExtra(SearchManager.QUERY);
        		ArrayList items=helper.getNamesSearch(query);
        		Bundle bundle=intent.getBundleExtra(SearchManager.APP_DATA);
        	    String info=bundle.getString("extra");
                ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,items);
                list.setAdapter(adapter);
        }else{
        	ArrayList items=helper.getNames();
            ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,items);
            list.setAdapter(adapter);
        }
        helper.close();
    }


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
        case R.id.action_search:
        	onSearchRequested();
        	 break;

        }
        return true;
    }
	    
    @Override
    public boolean onSearchRequested() {
    	Bundle bundle=new Bundle();
		bundle.putString("extra", "extra info");
		// search initial query
		startSearch("", false, bundle, false);
		return true;
    }
}
