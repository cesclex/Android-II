package com.udb.modulo2.clase12;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class WidgetProvider extends AppWidgetProvider {
	public static final String WIDGET_KEY = "com.udb.modulo2.clase12.WIDGET_KEY";
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,int[] appWidgetIds) {
		// TODO Auto-generated method stub
		String title = "Seleccione: ";
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
		
		views.setTextViewText(R.id.txtMessage, title);

		int contador = 11;
		
		Intent startActivityIntent = new Intent(context, ResponseWidgetActivity.class);
        startActivityIntent.putExtra(WIDGET_KEY, contador);
		PendingIntent startActivityPendingIntent = PendingIntent.getActivity(context, 0, startActivityIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.imageButton1, startActivityPendingIntent);
		
		ComponentName myWidget = new ComponentName(context, WidgetProvider.class);
	    AppWidgetManager manager = AppWidgetManager.getInstance(context);
	    manager.updateAppWidget(myWidget, views);
	}
}
