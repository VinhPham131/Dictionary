package com.example.dictionaries.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;

import com.example.dictionaries.Database.Loading;
import com.example.dictionaries.R;
import com.example.dictionaries.WordAdapter;
import com.example.dictionaries.WordAndDefinition;
import com.example.dictionaries.databinding.FragmentHomeBinding;
import com.example.dictionaries.databinding.FragmentVietAnhBinding;

import java.util.ArrayList;

public class VietAnhFragment extends Fragment {

    private FragmentVietAnhBinding binding;
    Loading loading;
    private ArrayList<WordAndDefinition> wordAndDefinition;
    private ArrayList<WordAndDefinition> wordAndDefinitionsOriginal;
    private WordAdapter wordAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentVietAnhBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loading = Loading.getInstance();
        if(loading.isViEnDicLoaded()) {
            wordAndDefinitionsOriginal = new ArrayList<>(loading.getWordAndDefinitionsViEn());
            wordAndDefinition = new ArrayList<>(loading.getWordAndDefinitionsViEn());
            wordAdapter = new WordAdapter(wordAndDefinition, getContext());
            binding.rvVietAnh.setAdapter(wordAdapter);
            binding.rvVietAnh.setLayoutManager(new LinearLayoutManager(getContext()));
            wordAdapter.notifyDataSetChanged();
        }
        search(view);
    }
    //    private class FetchDataTask extends AsyncTask<Void, Void, List<String>> {
//        @Override
//        protected List<String> doInBackground(Void... voids) {
//            return DataAccessA.getWords();
//        }
//
//        @Override
//        protected void onPostExecute(List<String> words) {
//            adapter = new DatabaseAdapter(words); // Initialize adapter with the fetched data
//
//            // Use DiffUtil to calculate the difference between the old and new list
//            final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DatabaseAdapter.WordDiffCallback(adapter.getData(), words));
//
//            recyclerView.setAdapter(adapter);
//            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//
//            // Apply the result of DiffUtil to the adapter
//            diffResult.dispatchUpdatesTo(adapter);
//        }
//    }
    public void search(View view) {
        AutoCompleteTextView autoCompleteTextView = view.findViewById(R.id.acp_search);
        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                wordAdapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}