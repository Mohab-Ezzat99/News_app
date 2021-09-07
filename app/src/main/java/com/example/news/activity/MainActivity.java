package com.example.news.activity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;

import com.example.news.R;
import com.example.news.adapter.ViewPagerAdapter;
import com.example.news.databinding.ActivityMainBinding;
import com.example.news.network.RetrofitBuilder;
import com.example.news.viewmodel.NewsViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

import io.reactivex.rxjava3.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ViewPagerAdapter pagerAdapter;
    public static NewsViewModel newsViewModel;
    private int currentTab=0;
    public static SharedPreferences sharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        sharedPreference= PreferenceManager.getDefaultSharedPreferences(this);

        setSupportActionBar(binding.mainToolbar.appBar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(sharedPreference.getString("title","USA News"));

        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);
        binding.mainViewPager.setAdapter(pagerAdapter);
        binding.mainTabLayout.setupWithViewPager(binding.mainViewPager);
        setTabsIcons();

        binding.mainTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                currentTab = tab.getPosition();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
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
                Objects.requireNonNull(getSupportActionBar()).setTitle("USA News");
                sharedPreference.edit().putString("title","USA News").apply();
                sharedPreference.edit().putString("country","us").apply();
                break;

            case R.id.menu_eg:
                Objects.requireNonNull(getSupportActionBar()).setTitle("Egypt News");
                sharedPreference.edit().putString("title","Egypt News").apply();
                sharedPreference.edit().putString("country","eg").apply();
                break;

            case R.id.menu_in:
                Objects.requireNonNull(getSupportActionBar()).setTitle("India News");
                sharedPreference.edit().putString("title","India News").apply();
                sharedPreference.edit().putString("country","in").apply();
        }

        switch (currentTab) {
            case 0:
                newsViewModel.refreshHomeNews();
                break;

            case 1:
                newsViewModel.refreshSportsNews();
                break;

            case 2:
                newsViewModel.refreshHealthNews();
                break;

            case 3:
                newsViewModel.refreshScienceNews();
                break;

            case 4:
                newsViewModel.refreshEntertainmentNews();
                break;

            case 5:
                newsViewModel.refreshTechnologyNews();
        }
        return true;
    }
}