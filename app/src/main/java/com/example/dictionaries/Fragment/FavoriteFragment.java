package com.example.dictionaries.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.paging.PositionalDataSource;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dictionaries.Activity.MainActivity;
import com.example.dictionaries.Database.Loading;
import com.example.dictionaries.R;
import com.example.dictionaries.WordAdapter;
import com.example.dictionaries.WordAndDefinition;
import com.example.dictionaries.databinding.FragmentFavoriteBinding;
import com.example.dictionaries.databinding.FragmentHomeBinding;
import com.example.dictionaries.databinding.FragmentVietAnhBinding;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment {

    Loading loading = Loading.getInstance();
    private WordAdapter wordAdapter;
    private RecyclerView rvFavorite;
    private ArrayList<WordAndDefinition> favoriteWords;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        rvFavorite = view.findViewById(R.id.rv_favorite);
        rvFavorite.setLayoutManager(new LinearLayoutManager(getContext()));
        if(loading.isFavoriteLoaded()) {
            wordAdapter = new WordAdapter(favoriteWords, getContext());
            favoriteWords = new ArrayList<>(wordAdapter.getFavoriteWords());
            wordAdapter = new WordAdapter(favoriteWords, getContext());
            rvFavorite.setAdapter(wordAdapter);
            rvFavorite.setLayoutManager(new LinearLayoutManager(getContext()));
            wordAdapter.notifyDataSetChanged();
        }
        return view;
    }

}