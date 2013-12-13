package com.udb.modulo2.clase07;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.widget.Toast;

public class CountService extends IntentService {
	
	private final String tagintent = "com.udb.modulo2.clase07.services.CountService";
	public static final String namebitmap = "com.udb.modulo2.clase07.services.CountService.BITMAP";
	String url = "";
	NotificationManager manager;
	Notification notification;
	NotificationCompat.Builder mBuilder;
	CharSequence notificationTitle = "Intent Services";
	CharSequence notificationText = "";
	Intent notificationIntent;
	PendingIntent pendingIntent;
	
	public CountService() {
		super("Andr");
		// TODO Auto-generated constructor stub
		Log.d(tagintent, "Servicio CountService Iniciado");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		try {
       	 url = intent.getStringExtra(MainActivity.URLID);
       	 Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(url).getContent());
       	 String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
       	 OutputStream outStream = null;
       	 File file = new File(extStorageDirectory, "android.jpg");
       	 
       	 outStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
            outStream.flush();
            outStream.close();
            mBuilder = new NotificationCompat.Builder(this)
		        .setSmallIcon(R.drawable.ic_launcher)
		        .setContentTitle(notificationTitle)
		        .setContentText(notificationText);
		
			notificationIntent = new Intent(this, ShowBitmapActivity.class);
			notificationIntent.putExtra(namebitmap, extStorageDirectory+"/android.jpg");
			
			TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
			stackBuilder.addParentStack(ShowBitmapActivity.class);
			stackBuilder.addNextIntent(notificationIntent);
			
			pendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
			
			mBuilder.setContentIntent(pendingIntent);
			mBuilder.setContentText("Imgen Descargada.");
			manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
			manager.notify(1, mBuilder.build());
			mBuilder.setAutoCancel(true);
    		
            Log.d(tagintent, "Finalizo proceos CountService");
         } catch (Exception e) {
       	  Log.d(tagintent, "Error: "+e.getMessage());
         }
	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.d(tagintent, "Proceso CountService onCreate");
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Toast.makeText(this, "Proceso CountService onDestroy", Toast.LENGTH_SHORT).show();
		Log.d(tagintent, "Proceso CountService onDestroy");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "Proceso CountService onStartCommand", Toast.LENGTH_SHORT).show();
		Log.d(tagintent, "Proceso CountService onStartCommand");
		return super.onStartCommand(intent, flags, startId);
	}

}
