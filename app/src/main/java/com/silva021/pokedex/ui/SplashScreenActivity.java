package com.silva021.pokedex.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.silva021.pokedex.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashScreenActivity extends AppCompatActivity {
    @BindView(R.id.imgPokeball)
    ImageView imgPokeball;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);
        Handler handler = new Handler();

        animateView();
        handler.postDelayed(() -> {
            openActivityMain();
        }, 2000);
    }

    private void animateView() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        imgPokeball.startAnimation(animation);
    }

    void openActivityMain(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}