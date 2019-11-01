package com.example.shadowstep.animation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public void fade(View view){

        ImageView bart = (ImageView) findViewById(R.id.bart);
        /*ImageView homer = (ImageView) findViewById(R.id.homer);

        if(bart.getAlpha() > 0){
            homer.animate().alpha(1).setDuration(2000);
            bart.animate().alpha(0).setDuration(2000);
        }else{
            homer.animate().alpha(0).setDuration(2000);
            bart.animate().alpha(1).setDuration(2000);
        }*/



    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView bart = (ImageView) findViewById(R.id.bart);

        bart.setTranslationX(-1020);
        bart.setScaleX(0.5f);
        bart.setScaleY(0.5f);

        bart.animate().translationXBy(1020).rotation(1800).scaleX(1.0f).scaleY(1.0f).setDuration(2000);
    }
}
