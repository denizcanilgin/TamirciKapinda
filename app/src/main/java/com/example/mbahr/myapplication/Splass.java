package com.example.mbahr.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splass extends Activity{
    private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash);
        getWindow().setBackgroundDrawableResource(R.drawable.backg);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        iv = (ImageView) findViewById(R.id.iv);
        Animation myanim= AnimationUtils.loadAnimation(this,R.anim.mytransition);
        iv.startAnimation(myanim);
        final Intent i =new Intent(this,Login.class);
         Thread timer =new Thread(){
            public void run(){
                try {
                    sleep(5000);
                    startActivity(i);
                }
                catch (InterruptedException e){
                    e.printStackTrace();

                }
                finally {

                    finish();

                }
            }

        };
        timer.start();
    }

}
