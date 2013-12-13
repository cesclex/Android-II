package com.udb.modulo2.clase18;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.res.Configuration;
import android.view.Menu;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends Activity {

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VideoView videoView = (VideoView) findViewById(R.id.videoView1);
        videoView.setVideoURI(Uri.parse("android.resource://com.udb.modulo2.clase18/raw/video1"));//toma la url del video 
        videoView.setMediaController(new MediaController(this));//asigna la barra de controles
        videoView.requestFocus();//le da un focus
        videoView.start(); //inicia con la reproduccion
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
