package com.udb.modulo2.clase41;

import android.os.Bundle;
import android.provider.UserDictionary;
import android.support.v4.widget.SimpleCursorAdapter;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView1); 
        loadList();
    }

    @SuppressWarnings("unused")
	public void loadList(){
        String[] mProjection =
        {
        	    UserDictionary.Words._ID,    
        	    UserDictionary.Words.WORD,   
        	    UserDictionary.Words.LOCALE  
        };
        
        String mSelectionClause = UserDictionary.Words.WORD + " = ?";
        String[] mSelectionArgs = {"pupusas"};
        String mOrderby = UserDictionary.Words.WORD + " DESC";
        
        Cursor mCursor = getContentResolver().query(
        	    UserDictionary.Words.CONTENT_URI,   
        	    null,                        
        	    null,                    	
        	    null,                    
        	    mOrderby);   
        
        /*Cursor mCursor = getContentResolver().query(
        	    UserDictionary.Words.CONTENT_URI,   
        	    mProjection,                        
        	    mSelectionClause,                    	
        	    mSelectionArgs,                    
        	    null);  */
        
        if (null == mCursor) {
        	Toast.makeText(this, "Error al obtener Registros", Toast.LENGTH_SHORT).show();
        } else if (mCursor.getCount() < 1) {
        	Toast.makeText(this, "No hay Registros", Toast.LENGTH_SHORT).show();
        } else {
        	String[] mWordListColumns =
        		{
        		    UserDictionary.Words.WORD,   
        		    UserDictionary.Words.LOCALE  
        		};
        	
        		int[] mWordListItems = { R.id.dictWord, R.id.locale};

        		SimpleCursorAdapter mCursorAdapter = new SimpleCursorAdapter(
        		    getApplicationContext(),              
        		    R.layout.listword,                 
        		    mCursor,                               
        		    mWordListColumns,                     
        		    mWordListItems,                       
        		    0);   
        		 listView.setAdapter(mCursorAdapter);

        }
    }
    

    @Override
  	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
  		// TODO Auto-generated method stub
    	loadList();
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
        case R.id.action_new:
        	Intent intent = new Intent(this, AddActivity.class);
        	startActivityForResult(intent, 100);
        	break;
        }
        return true;
    }

}
