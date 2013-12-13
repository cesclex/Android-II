package com.udb.modulo2.clase14;

import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;
import yuku.ambilwarna.AmbilWarnaDialog;
import yuku.ambilwarna.AmbilWarnaDialog.OnAmbilWarnaListener;

public class WidgetConfigActivity extends Activity {
	private int mAppWidgetId = 0 ;
	 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_config);
 
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
 
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        }

    }
    
    public void colorPicker(View view) {
          	
    	AmbilWarnaDialog dialog = new AmbilWarnaDialog(this, 0xff0000ff, new OnAmbilWarnaListener(){

			@Override
			public void onCancel(AmbilWarnaDialog dialog) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onOk(AmbilWarnaDialog dialog, int color) {
				// TODO Auto-generated method stub
                Intent intent = new Intent(getBaseContext(), WidgetConfigActivity.class);
                
                intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
 
                PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(), 0,
                                                intent, PendingIntent.FLAG_UPDATE_CURRENT);
 
                RemoteViews views = new RemoteViews(getBaseContext().getPackageName(), R.layout.widget_layout);
 
                views.setInt(R.id.widget_aclock, "setBackgroundColor", color);
 
                views.setOnClickPendingIntent(R.id.widget_aclock, pendingIntent);
 
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getBaseContext());
                appWidgetManager.updateAppWidget(mAppWidgetId, views);
 
                Intent resultValue = new Intent();
                resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
                setResult(RESULT_OK, resultValue);
                finish();
			}
    		
    	});
        dialog.show();
    }
}
