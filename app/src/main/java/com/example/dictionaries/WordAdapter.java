package com.example.dictionaries;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionaries.Activity.DefinitionActivity;
import com.example.dictionaries.Activity.MainActivity;
import com.example.dictionaries.Database.Loading;
import com.example.dictionaries.databinding.DictionaryRowBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.ViewHolder> implements Filterable {
    private ArrayList<WordAndDefinition> wordAndDefinitions;
    private ArrayList<WordAndDefinition> originalWordAndDefinitions;
    private ArrayList<WordAndDefinition> favoriteWords;
    private Context context;
    private DictionaryRowBinding rowBinding;
    Loading loading = Loading.getInstance();


    public WordAdapter(ArrayList<WordAndDefinition> wordAndDefinitions, Context context) {
        this.wordAndDefinitions = wordAndDefinitions;
        this.originalWordAndDefinitions = new ArrayList<>(wordAndDefinitions);
        this.context = context;
        loadFavoriteWordsFromPreferences();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        rowBinding = DictionaryRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(rowBinding);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.rowBinding.setWord(wordAndDefinitions.get(position));
//        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return wordAndDefinitions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        DictionaryRowBinding rowBinding;
        Button btnFavor;
        public ViewHolder(DictionaryRowBinding binding) {
            super(binding.getRoot());
            this.rowBinding = binding;
            //Click on word to see definition
            binding.tvWord.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
//                        WordAndDefinition wordAndDefinition = wordAndDefinitions.get(position);
                        Intent intent = new Intent(context, DefinitionActivity.class);
                        intent.putExtra("word", wordAndDefinitions.get(position).getWord());
                        intent.putExtra("definition", wordAndDefinitions.get(position).getDefinition());
                        context.startActivity(intent);
                    }
                }
            });
            //Click on button to add to favorite
            binding.btnFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        // Change the button's appearance
                        binding.btnFavorite.setBackgroundResource(R.drawable.baseline_favorite_24);
                        // Add the word to the favorites list
                        addFavoriteWord(wordAndDefinitions.get(position));
                    }
                }
            });
        }
    }
    //Handle favorite
    public void addFavoriteWord(WordAndDefinition word) {
        if (favoriteWords == null) {
            favoriteWords = new ArrayList<>();
        }
        favoriteWords.add(word);
        saveFavoriteWordsToPreferences();
    }
    public ArrayList<WordAndDefinition> getFavoriteWords() {
        return favoriteWords;
    }
    private void saveFavoriteWordsToPreferences() {
        SharedPreferences sharedPref = context.getSharedPreferences("FavoriteWords", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(favoriteWords);
        editor.putString("FavoriteWordList", json);
        editor.apply();
    }
    private void loadFavoriteWordsFromPreferences() {
        SharedPreferences sharedPref = context.getSharedPreferences("FavoriteWords", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPref.getString("FavoriteWordList", null);
        Type type = new TypeToken<ArrayList<WordAndDefinition>>() {
        }.getType();
        favoriteWords = gson.fromJson(json, type);

        if (favoriteWords == null) {
            favoriteWords = new ArrayList<>();
        }
    }
    //Handle search
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                ArrayList<WordAndDefinition> filteredList = new ArrayList<>();
                if (constraint == null || constraint.length() == 0) {
                    filteredList.addAll(originalWordAndDefinitions);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();
                    for (WordAndDefinition item : originalWordAndDefinitions) {
                        if (item.getWord().toLowerCase().contains(filterPattern)) {
                            filteredList.add(item);
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = filteredList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                wordAndDefinitions.clear();
                wordAndDefinitions.addAll((ArrayList) results.values);
                notifyDataSetChanged();
            }
        };
    }

}