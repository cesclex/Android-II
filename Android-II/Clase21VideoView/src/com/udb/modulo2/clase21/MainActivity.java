package com.udb.modulo2.clase21;

import java.io.File;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	SharedPreferences data;
	private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 200;
	private static final int RESULT_LOAD_VIDEO = 300;
	private Uri fileUri;
	private int contvideo = 0;
	@Override	
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = getSharedPreferences("preferencias", MODE_PRIVATE);
        contvideo = data.getInt("contvideo", 0);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
    public void doRecvideo(View view){
    	contvideo++;
    	Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        File videosFolder = new File(Environment.getExternalStorageDirectory(), "MyVideos");
        videosFolder.mkdirs();
        File image = new File(videosFolder, "video_"+contvideo+".mp4");
        fileUri = Uri.fromFile(image); 
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); 
        startActivityForResult(intent, CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE);
    }
    
    public void showVideo(View view){
    	Intent intent = new Intent(this, ShowVideoActivity.class);
 	   startActivity(intent);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Video Guardado: "+"video_"+contvideo+".mp4", Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
            	Toast.makeText(this, "Usuario Cancelo la Accion", Toast.LENGTH_LONG).show();
            } else {
            	Toast.makeText(this, "Error al tomar el Video", Toast.LENGTH_LONG).show();
            }
        }else if(requestCode == RESULT_LOAD_VIDEO ){
        	Toast.makeText(this, "Error al tomar el Video", Toast.LENGTH_LONG).show();
        }
    }
    
    @Override
	protected void onSaveInstanceState(Bundle outState) {
    	SharedPreferences.Editor preferences = data.edit();
		preferences.putInt("contvideo", contvideo);
		preferences.commit();
    }

}
