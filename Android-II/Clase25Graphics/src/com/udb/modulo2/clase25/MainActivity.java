package com.udb.modulo2.clase25;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		GraphicView graphic = new GraphicView(this);
	    setContentView(graphic);
	}
	private class GraphicView extends View{
    	
    	Paint paint = new Paint();
    	
    	public GraphicView(Context context){
    		super(context);
    	}
    	
    	@Override
    	protected void onDraw(Canvas canvas){
    		super.onDraw(canvas);
    		  		
    		
    		paint.setColor(Color.WHITE);
    		canvas.drawPaint(paint);
    		int width = canvas.getWidth();
    		int height = canvas.getHeight();
    		
    		paint.setColor(Color.BLACK);
    		paint.setTextSize(30);
    		paint.setAntiAlias(true);
    		canvas.drawText("Width= "+width+", height= "+height, 20, 40, paint);
    		paint.setColor(Color.rgb(100, 20, 0));
    		canvas.drawLine(0, 40, width, 40, paint);
    		paint.setColor(Color.rgb(0, 100, 20));
    		canvas.drawLine(20, 0, 20, height, paint);
    	}
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
