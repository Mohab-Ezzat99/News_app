package com.example.news.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.news.R;
import com.example.news.activity.MainActivity;
import com.example.news.activity.WebViewActivity;
import com.example.news.adapter.RVNewsAdapter;
import com.example.news.databinding.FragmentHealthBinding;
import com.example.news.network.RetrofitBuilder;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HealthFragment extends Fragment {

    private FragmentHealthBinding binding;
    private RVNewsAdapter adapter;
    private String category = "health";

    public HealthFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_health, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentHealthBinding.bind(view);

        adapter = new RVNewsAdapter(getContext());
        binding.healthFRv.setAdapter(adapter);
        binding.healthFRv.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.healthFRv.setHasFixedSize(true);
        adapter.setListener(url -> requireActivity().startActivity(new Intent(requireContext(), WebViewActivity.class).putExtra("url", url)));
        getNews();
        MainActivity.newsViewModel.refreshHealthNews().observe(getViewLifecycleOwner(), aBoolean -> getNews());
    }

    public void getNews() {
        binding.healthFPb.setVisibility(View.VISIBLE);
        binding.healthFRv.setVisibility(View.GONE);
        MainActivity.newsViewModel.getCategoryNews(MainActivity.sharedPreference.getString("country","us"), category, 100)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    binding.healthFPb.setVisibility(View.GONE);
                    binding.healthFRv.setVisibility(View.VISIBLE);
                    adapter.setList(result.getArticles());
                },error->{
                    binding.healthFPb.setVisibility(View.GONE);
                    binding.healthFRv.setVisibility(View.VISIBLE);
                    Toast.makeText(requireActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}