package com.example.dictionaries.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.dictionaries.Database.DataAccessAnhViet;
import com.example.dictionaries.R;
import com.example.dictionaries.WordAndDefinition;

import java.util.Locale;

public class DefinitionActivity extends AppCompatActivity {

    private TextView tvWord;
    private TextToSpeech textToSpeech;
    private ImageButton btnSound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definition);

        Toolbar toolbar = findViewById(R.id.toolbar_definition);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        WebView myWebView = (WebView) findViewById(R.id.webview);
        tvWord = findViewById(R.id.tv_word);
        btnSound = findViewById(R.id.btn_sound);

        Intent intent = getIntent();
        String word = intent.getStringExtra("word");
        String definition = intent.getStringExtra("definition");
        getSupportActionBar().setTitle("Definition");
        tvWord.setText(word);
        myWebView.loadDataWithBaseURL(null, definition, "text/html", "utf-8", null);

        //Speaker
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.US);
                }
            }
        });
        btnSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = tvWord.getText().toString();
                textToSpeech.speak(word, TextToSpeech.QUEUE_FLUSH, null);
            }

        });

    }
    @Override
    protected void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}