package com.udb.modulo2.clase08;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

public class BanckgroundService extends Service {
private static final String TAG = "BackgroundService";
	
	private NotificationCompat.Builder mBuilder;
	private Intent notificationIntent;
	private PendingIntent pendingIntent;	
	private NotificationManager notificationMgr;
	private ThreadGroup myThreads = new ThreadGroup("ServiceWorker");
	
	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(TAG, "en onCreate()");
		displayNotificationMessage("Background Service esta Corriendo");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);
		int counter = intent.getExtras().getInt("counter");
		Log.d(TAG, "en onStartCommand(), Contador = " + counter + ", Id = "+ startId);
		new Thread(myThreads, new ServiceWorker(counter), "BackgroundService").start();
		return START_STICKY;
	}

	class ServiceWorker implements Runnable {
		private int counter = -1;

		public ServiceWorker(int counter) {
			this.counter = counter;
		}

		public void run() {
			final String TAG2 = "ServiceWorker:"+ Thread.currentThread().getId();
			try {
				Log.d(TAG2, "Durmiendo por 30 sec. Contador = " + counter);
				Thread.sleep(30000);
				Log.d(TAG2, "... Despertando");
			} catch (InterruptedException e) {
				Log.d(TAG2, "... sleep Interrumpido");
			}
		}
	}

	@Override
	public void onDestroy() {
		Log.d(TAG,"En onDestroy(). Interrupiendo todos los Hilos y Denteniendo notificacion.");
		myThreads.interrupt();
		notificationMgr.cancelAll();
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent intent) {
		Log.d(TAG, "en onBind()");
		return null;
	}

	private void displayNotificationMessage(String message) {
		
		mBuilder = new NotificationCompat.Builder(this)
	        .setSmallIcon(R.drawable.ic_launcher)
	        .setContentTitle(TAG)
	        .setContentText(message);
	
		notificationIntent = new Intent(this, MainActivity.class);

		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		stackBuilder.addParentStack(MainActivity.class);
		stackBuilder.addNextIntent(notificationIntent);

		pendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
	
		mBuilder.setContentIntent(pendingIntent);	
		notificationMgr = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		notificationMgr.notify(1, mBuilder.build());
	}

}
