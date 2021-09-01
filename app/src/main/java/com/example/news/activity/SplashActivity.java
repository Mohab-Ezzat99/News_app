package com.example.news.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.news.R;
import com.example.news.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    private Animation anim_left;
    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        anim_left = AnimationUtils.loadAnimation(this, R.anim.anim_left);
        binding.splashIv.setAnimation(anim_left);
        binding.splashTvAppName.setAnimation(anim_left);
        binding.splashTvBy.setAnimation(anim_left);
        binding.splashTvMe.setAnimation(anim_left);

        new Handler().postDelayed(() -> {
            startActivity(new Intent(getBaseContext(),MainActivity.class));
            finish();
        },1800);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}