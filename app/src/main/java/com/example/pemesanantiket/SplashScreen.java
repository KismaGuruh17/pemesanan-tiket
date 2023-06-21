package com.example.pemesanantiket;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class SplashScreen extends AppCompatActivity {

    TextView judul;

    ImageView gambarSplash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen);

        judul = findViewById(R.id.judul);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.simple_anim);

        judul.setAnimation(animation);

        gambarSplash = findViewById(R.id.gambarSplash);

        Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.simple_anim);

        gambarSplash.setAnimation(animation1);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        }, 1000L);
    }
}