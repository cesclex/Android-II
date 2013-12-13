package com.udb.modulo2.clase30;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements AnimationListener {
	TextView txtAnim;
	ImageView iman;
	Animation animin;
	Animation animout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		txtAnim = (TextView) findViewById(R.id.txtAnim);
		iman=(ImageView) findViewById(R.id.imageView1);
	    //inicio de animacion
		//animin = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink); 
        //animin.setAnimationListener(this);
        
        //agregando animacion
        //animin = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_in); 
        //animin.setAnimationListener(this);
        
      //agregando animacion
        //animout = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_out); 
        //animout.setAnimationListener(this);
		
		//agregando animacion
      //  animin = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.sequential); 
       // animin.setAnimationListener(this);
      //agregando animacion
        animin = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.spiral); 
        animin.setAnimationListener(this);
        
        iman.setVisibility(View.VISIBLE);        
        iman.startAnimation(animin);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		// TODO Auto-generated method stub
		if (animation == animin) {
            Toast.makeText(getApplicationContext(), "Animacion Termino.", Toast.LENGTH_SHORT).show();
            //txtAnim.startAnimation(animout);
        }
		if (animation == animout) {
            //Toast.makeText(getApplicationContext(), "Animacion Termino.", Toast.LENGTH_SHORT).show();
        }
		
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub
		
	}
	public void startAnim(View view){
		txtAnim.setVisibility(View.VISIBLE);        
        txtAnim.startAnimation(animin);              
	}
	
	

}
