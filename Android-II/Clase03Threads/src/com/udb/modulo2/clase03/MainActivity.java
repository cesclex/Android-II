package com.udb.modulo2.clase03;

import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	boolean continueprocess = true;
	int delay = 100;
	int cont = 0;
	NotificationManager manager;
	Notification notification;
	NotificationCompat.Builder mBuilder;
	CharSequence notificationTitle = "Thread Funcionando";
	CharSequence notificationText = "";
	Context context;
	Intent notificationIntent;
	PendingIntent pendingIntent;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
		
    }
	public void iniciar_hilo(){
		String servicio = Context.NOTIFICATION_SERVICE;
		manager = (NotificationManager) getSystemService(servicio);
		
		mBuilder = new NotificationCompat.Builder(this)
		        .setSmallIcon(R.drawable.ic_launcher)
		        .setContentTitle(notificationTitle)
		        .setContentText(notificationText);
		
		notificationIntent = new Intent(this, MainActivity.class);
		
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		stackBuilder.addParentStack(MainActivity.class);
		stackBuilder.addNextIntent(notificationIntent);

		context = getApplicationContext();
		
		pendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
		
		mBuilder.setContentIntent(pendingIntent);
		manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		manager.notify(1, mBuilder.build());
		
		Counter counter = new Counter();
		Thread thread = new Thread(counter);
		thread.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void clicStop(View view){
		continueprocess = false;
	}
	
	class Counter implements Runnable{

		@Override
		public void run() {
			int numMessages = 0;
			// TODO Auto-generated method stub
			while(continueprocess){
				try{
					Thread.sleep(delay);
				}catch(Exception e){}
				cont++;
				notificationText="contador="+cont;				
				mBuilder.setContentText(notificationText).setNumber(++numMessages);
				manager.notify(1,mBuilder.build());

			}
		}
		
	}
}
