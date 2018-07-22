package com.example.Inc.tamircikapinda;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends Activity{
    private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash);
        getWindow().setBackgroundDrawableResource(R.drawable.backg);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        ImageView upper_cable = (ImageView) findViewById(R.id.upper_cable);
        ImageView lower_cable = (ImageView) findViewById(R.id.lower_cable);

        upper_cable.setTranslationY(-1000f);
        upper_cable.animate().translationYBy(1000f).setDuration(2000);

        lower_cable.setTranslationY(+1000f);
        lower_cable.animate().translationYBy(-1000f).setDuration(2000);


//        Animation upper_cable_anim = AnimationUtils.loadAnimation(this,R.anim.enter_from_up);
//        Animation lower_cable_anim = AnimationUtils.loadAnimation(this,R.anim.enter_from_down);


        iv = (ImageView) findViewById(R.id.iv);
        Animation logoAnim= AnimationUtils.loadAnimation(this,R.anim.mytransition);
        iv.startAnimation(logoAnim);
//
        final Intent i =new Intent(this,Login.class);
        Thread timer =new Thread(){
            public void run(){
                try {
                    sleep(2000);
                    startActivity(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
                finally {

                    finish();
                    //eeeeeeeeedeniz can ılgınnnnnn

                }
            }

        };
        timer.start();
    }

}
