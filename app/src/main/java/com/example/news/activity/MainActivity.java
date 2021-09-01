package com.example.news.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.news.R;
import com.example.news.adapter.RVNewsAdapter;
import com.example.news.adapter.ViewPagerAdapter;
import com.example.news.databinding.ActivityMainBinding;
import com.example.news.network.RetrofitBuilder;
import com.example.news.viewmodel.NewsViewModel;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ViewPagerAdapter pagerAdapter;
    public static NewsViewModel newsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);

        setSupportActionBar(binding.mainToolbar.appBar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("USA News");

        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),0);
        binding.mainViewPager.setAdapter(pagerAdapter);
        binding.mainTabLayout.setupWithViewPager(binding.mainViewPager);
        setTabsIcons();
    }

    private void setTabsIcons() {
        binding.mainTabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        binding.mainTabLayout.getTabAt(1).setIcon(R.drawable.ic_sport);
        binding.mainTabLayout.getTabAt(2).setIcon(R.drawable.ic_health);
        binding.mainTabLayout.getTabAt(3).setIcon(R.drawable.ic_science);
        binding.mainTabLayout.getTabAt(4).setIcon(R.drawable.ic_entertainment);
        binding.mainTabLayout.getTabAt(5).setIcon(R.drawable.ic_technology);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options,menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.menu_us:
                if(RetrofitBuilder.COUNTRY.equals("us"))
                    break;

                RetrofitBuilder.COUNTRY="us";
                Objects.requireNonNull(getSupportActionBar()).setTitle("USA News");
                newsViewModel.refreshNews();
                break;

            case R.id.menu_eg:
                if(RetrofitBuilder.COUNTRY.equals("eg"))
                    break;

                RetrofitBuilder.COUNTRY="eg";
                Objects.requireNonNull(getSupportActionBar()).setTitle("Egypt News");
                newsViewModel.refreshNews();
                break;

            case R.id.menu_in:
                if(RetrofitBuilder.COUNTRY.equals("in"))
                    break;

                RetrofitBuilder.COUNTRY="in";
                Objects.requireNonNull(getSupportActionBar()).setTitle("India News");
                newsViewModel.refreshNews();
        }
        return true;
    }
}