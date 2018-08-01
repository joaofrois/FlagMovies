package pt.flag.flagmovies.screens;


import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import pt.flag.flagmovies.R;

public class SplashScreen extends Screen {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        findViews();
        startAnimation();

    }

    public void findViews(){
        imageView = findViewById(R.id.splash_screen_imageview);
    }

    private void startAnimation(){
        imageView.setScaleX(0.2f);
        imageView.setScaleY(0.2f);
        imageView.setAlpha(0.0f);

        imageView.animate().alpha(1f).scaleX(1f).scaleY(1f).rotation(360).setDuration(3500).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation)
            {
                startActivity(new Intent(SplashScreen.this, HomeScreen.class));
                finish();


            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
            }).start();

        }
    }



