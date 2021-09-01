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
import com.example.news.databinding.FragmentEntertainmentBinding;
import com.example.news.network.RetrofitBuilder;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class EntertainmentFragment extends Fragment {

    private FragmentEntertainmentBinding binding;
    private RVNewsAdapter adapter;
    private String category = "entertainment";

    public EntertainmentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_entertainment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentEntertainmentBinding.bind(view);

        adapter = new RVNewsAdapter(getContext());
        binding.entertainmentFRv.setAdapter(adapter);
        binding.entertainmentFRv.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.entertainmentFRv.setHasFixedSize(true);
        adapter.setListener(url -> {
            requireActivity().startActivity(new Intent(requireContext(), WebViewActivity.class).putExtra("url", url));
        });
        getNews();
        MainActivity.newsViewModel.refreshNews().observe(getViewLifecycleOwner(), aBoolean -> getNews());
    }

    public void getNews() {
        binding.entertainmentFPb.setVisibility(View.VISIBLE);
        binding.entertainmentFRv.setVisibility(View.GONE);
        MainActivity.newsViewModel.getCategoryNews(RetrofitBuilder.COUNTRY, category, 100)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    binding.entertainmentFPb.setVisibility(View.GONE);
                    binding.entertainmentFRv.setVisibility(View.VISIBLE);
                    adapter.setList(result.getArticles());
                },error-> Toast.makeText(requireActivity(), error.getMessage(), Toast.LENGTH_SHORT).show());
    }

    @Override
    public void onStop() {
        super.onStop();
        MainActivity.newsViewModel.refreshNews().removeObservers(getViewLifecycleOwner());
    }
}