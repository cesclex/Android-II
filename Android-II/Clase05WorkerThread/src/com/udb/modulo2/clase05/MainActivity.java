package com.udb.modulo2.clase05;

import java.io.InputStream;
import java.net.URL;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends Activity {
	ImageView imageView;
	ProgressBar progressLoad;
	Button btnCargar;
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        imageView = (ImageView) findViewById(R.id.imageView1);
	        imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher));
	        progressLoad = (ProgressBar) findViewById(R.id.progressBar1);
	        progressLoad.setVisibility(ProgressBar.INVISIBLE);
	        btnCargar=(Button) findViewById(R.id.button1);
	    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void onLoadimage(View view) {
    	imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher));
    	progressLoad.setVisibility(ProgressBar.VISIBLE);
    	
    	//deshabilitando elboton
    	btnCargar.setEnabled(false);
        new Thread(new Runnable() {
            public void run() {
                final Bitmap bitmap = loadImageFromNetwork("http://wallpaperswide.com/download/android_motherboard-wallpaper-1920x1200.jpg");
                imageView.post(new Runnable() {
                    public void run() {
                    	imageView.setImageBitmap(bitmap);
                    	progressLoad.setVisibility(ProgressBar.INVISIBLE);
                    	btnCargar.setEnabled(true);
                    }
                });
            }
            private Bitmap loadImageFromNetwork(String url){
                try {
                    Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(url).getContent());
                    return bitmap;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
           }
        }).start();
    }
}
