package com.udb.modulo2.clase21;

import java.io.File;
import java.io.FilenameFilter;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.VideoView;
import android.annotation.TargetApi;
import android.os.Build;

public class ShowVideoActivity extends Activity implements OnItemClickListener {
	
	private Uri[] mUrls;
	String[] mFiles = null;
	ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_video);
		// Show the Up button in the action bar.
		setupActionBar();
		onLoadvideo();
		listView = (ListView) findViewById(R.id.listView1);

		listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mFiles));
		listView.setOnItemClickListener(this);
	}

	public void onLoadvideo() {
		File images = new File(Environment.getExternalStorageDirectory(),"MyVideos");
		File[] imagelist = images.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return (name.endsWith(".mp4"));
			}
		});
		mFiles = new String[imagelist.length];
		for (int i = 0; i < imagelist.length; i++) {
			mFiles[i] = imagelist[i].getAbsolutePath();
		}
		mUrls = new Uri[mFiles.length];

		for (int i = 0; i < mFiles.length; i++) {
			mUrls[i] = Uri.parse(mFiles[i]);
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
		// TODO Auto-generated method stub
		VideoView videoView = (VideoView) findViewById(R.id.videoView1);
        videoView.setVideoURI(mUrls[position]);
        videoView.setMediaController(new MediaController(this));
        videoView.requestFocus();
        videoView.start();
	}
	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}
}
