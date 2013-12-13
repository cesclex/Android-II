package com.udb.modulo2.clase20;

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
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private Uri fileUri;
	private int contimg = 0;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = getSharedPreferences("preferencias", MODE_PRIVATE);
        contimg = data.getInt("contimg", 0);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
    public void onTakepicture(View view){
    	contimg++;
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File imagesFolder = new File(Environment.getExternalStorageDirectory(), "MyPictures");
        imagesFolder.mkdirs();
        File image = new File(imagesFolder, "image_0"+contimg+".jpg");
        fileUri = Uri.fromFile(image); 
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); 
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }
    
   public void onShowPicture(View view){
	   Intent intent = new Intent(this, ShowImageActivity.class);
	   startActivity(intent);
   }
    
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Image Guardada: "+"image_0"+contimg+".jpg", Toast.LENGTH_LONG).show();
            } else if (resultCode == RESULT_CANCELED) {
            	Toast.makeText(this, "Usuario Cancelo la Accion", Toast.LENGTH_LONG).show();
            } else {
            	Toast.makeText(this, "Error al tomar la foto", Toast.LENGTH_LONG).show();
            }
        }
    }
    
    @Override
	protected void onSaveInstanceState(Bundle outState) {
    	SharedPreferences.Editor preferences = data.edit();
		preferences.putInt("contimg", contimg);
		preferences.commit();
    }

}
