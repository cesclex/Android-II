package com.udb.modulo2.clase23;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends Activity {

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VideoView videoView = (VideoView) findViewById(R.id.video);
        //videoView.setVideoURI(Uri.parse("android.resource://com.udb.modulo2.clase19/raw/video1"));
        videoView.setVideoPath("http://www.mp4point.com/downloads/c1c5190aca15.mp4");
        videoView.setMediaController(new MediaController(this));
        videoView.requestFocus();
        videoView.start();
    }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
