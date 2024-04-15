package com.example.dictionaries.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionaries.Activity.MainActivity;
import com.example.dictionaries.Database.DataAccessAnhViet;
import com.example.dictionaries.Database.DataAccessVietAnh;
import com.example.dictionaries.Database.Loading;
import com.example.dictionaries.R;
import com.example.dictionaries.WordAdapter;
import com.example.dictionaries.WordAndDefinition;
import com.example.dictionaries.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    Loading loading = Loading.getInstance();
    private ArrayList<WordAndDefinition> wordAndDefinition;
    private ArrayList<WordAndDefinition> wordAndDefinitionsOriginal;
    private WordAdapter wordAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
         return binding.getRoot();

    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("Debug", "HomeFragment onViewCreated");
        if(loading.isEnViDicLoaded()) {
            wordAndDefinitionsOriginal = new ArrayList<>(loading.getWordAndDefinitionsEnVi());
            wordAndDefinition = new ArrayList<>(loading.getWordAndDefinitionsEnVi());
            wordAdapter = new WordAdapter(wordAndDefinition, getContext());
            binding.rvAnhViet.setAdapter(wordAdapter);
            binding.rvAnhViet.setLayoutManager(new LinearLayoutManager(getContext()));
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
//    public void updateUIWithLoadedData() {
////        loading = Loading.getInstance();
////        if(loading.isEnViDicLoaded()) {
////            wordAndDefinitionsOriginal = new ArrayList<>(loading.getWordAndDefinitionsEnVi());
////            wordAndDefinition = new ArrayList<>(loading.getWordAndDefinitionsEnVi());
////            wordAdapter = new WordAdapter(wordAndDefinition, getContext());
////            binding.rvAnhViet.setAdapter(wordAdapter);
////            binding.rvAnhViet.setLayoutManager(new LinearLayoutManager(getContext()));
////            wordAdapter.notifyDataSetChanged();
////        }
//    }
}