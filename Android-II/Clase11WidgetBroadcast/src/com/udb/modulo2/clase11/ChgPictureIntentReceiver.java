package com.udb.modulo2.clase11;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class ChgPictureIntentReceiver extends BroadcastReceiver {

	private static int clickCount = 0;

	@Override
	public void onReceive(Context context, Intent intent) {
		if(intent.getAction().equals(ChgPictureWidget.ACTIONKEY)){
			updateWidgetPictureAndButtonListener(context);
		}
	}

	private void updateWidgetPictureAndButtonListener(Context context) {
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_change_picture);
		remoteViews.setImageViewResource(R.id.widget_image, getImageToSet());
		remoteViews.setOnClickPendingIntent(R.id.widget_button, ChgPictureWidget.buildButtonPendingIntent(context));

		ChgPictureWidget.pushWidgetUpdate(context.getApplicationContext(), remoteViews);
	}

	private int getImageToSet() {
		clickCount++;
		int imgid=1;
		switch(clickCount){
		case 1:
			imgid = R.drawable.pic1;
			break;
		case 2:
			imgid = R.drawable.pic2;
			break;
		case 3:
			imgid = R.drawable.pic3;
			clickCount = 0;
			break;
		}
		return imgid;
	}
}