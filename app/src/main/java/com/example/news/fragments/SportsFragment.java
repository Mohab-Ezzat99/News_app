package com.example.news.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.news.R;
import com.example.news.activity.MainActivity;
import com.example.news.activity.WebViewActivity;
import com.example.news.adapter.RVNewsAdapter;
import com.example.news.databinding.FragmentSportsBinding;
import com.example.news.network.NewsAPIService;
import com.example.news.network.RetrofitBuilder;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SportsFragment extends Fragment {

    private FragmentSportsBinding binding;
    private RVNewsAdapter adapter;
    private String category="sports";

    public SportsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sports, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding= FragmentSportsBinding.bind(view);

        adapter=new RVNewsAdapter(getContext());
        binding.sportsFRv.setAdapter(adapter);
        binding.sportsFRv.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.sportsFRv.setHasFixedSize(true);
        adapter.setListener(url -> requireActivity().startActivity(new Intent(requireContext(), WebViewActivity.class).putExtra("url",url)));
        getNews();
        MainActivity.newsViewModel.refreshSportsNews().observe(getViewLifecycleOwner(), aBoolean -> getNews());
    }

    public void getNews(){
        binding.sportsFPb.setVisibility(View.VISIBLE);
        binding.sportsFRv.setVisibility(View.GONE);
        MainActivity.newsViewModel.getCategoryNews(MainActivity.sharedPreference.getString("country","us"),category,100)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result ->{
                    binding.sportsFPb.setVisibility(View.GONE);
                    binding.sportsFRv.setVisibility(View.VISIBLE);
                    adapter.setList(result.getArticles());
                },error->{
                    binding.sportsFPb.setVisibility(View.GONE);
                    binding.sportsFRv.setVisibility(View.VISIBLE);
                    Toast.makeText(requireActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}