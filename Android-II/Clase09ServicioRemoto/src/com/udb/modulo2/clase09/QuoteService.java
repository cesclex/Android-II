package com.udb.modulo2.clase09;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class QuoteService extends Service {
	private static final String TAG = "QuoteService";
	
	public class StockQuoteServiceImpl extends IQuoteService.Stub {
		@Override
		public double getQuote(String ticker) throws RemoteException {
			Log.v(TAG, "getQuote() por " + ticker);
			return 20.0;
		}
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.v(TAG, "onCreate()");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.v(TAG, "onDestroy()");
	}

	@Override
	public IBinder onBind(Intent intent) {
		Log.v(TAG, "onBind()");
		return new StockQuoteServiceImpl();
	}

}
