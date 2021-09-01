package com.example.news.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebViewClient;

import com.example.news.databinding.ActivityWebViewBinding;

public class WebViewActivity extends AppCompatActivity {

    private ActivityWebViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityWebViewBinding.inflate(getLayoutInflater());
        setContentView(binding.webView);

        String url=getIntent().getStringExtra("url");
        binding.webView.setWebViewClient(new WebViewClient());
        binding.webView.loadUrl(url);
    }
}