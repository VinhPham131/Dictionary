package com.example.dictionaries.Activity;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.dictionaries.Database.Loading;
import com.example.dictionaries.Fragment.FavoriteFragment;
import com.example.dictionaries.Fragment.HomeFragment;
import com.example.dictionaries.Fragment.VietAnhFragment;
import com.example.dictionaries.Fragment.YourWords;
import com.example.dictionaries.R;
import com.example.dictionaries.WordAndDefinition;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    static Context context;

    private DrawerLayout drawerLayout;
    private TextView tvTitle;


    public MainActivity() {
        context = this;
    }

    public static Context getContext() {
        return context;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_anhViet) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            tvTitle = findViewById(R.id.tv_title);
            tvTitle.setText("ENG-VIE");
        } else if (id == R.id.nav_vietAnh) {
            tvTitle = findViewById(R.id.tv_title);
            tvTitle.setText("VIE-ENG");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new VietAnhFragment()).commit();
        } else if (id == R.id.nav_favorite) {
            tvTitle = findViewById(R.id.tv_title);
            tvTitle.setText("Favorite Words");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FavoriteFragment()).commit();
        } else if (id == R.id.nav_yourWord) {
            tvTitle = findViewById(R.id.tv_title);
            tvTitle.setText("Your Words");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new YourWords()).commit();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_view);
        }
//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AddWordsFragment addWordsFragment = new AddWordsFragment();
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.main_activity, addWordsFragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
////                getSupportFragmentManager().beginTransaction().replace(R.id.main_activity, new AddWordsFragment()).commit();
//            }
//        });
//        NavigationView navigationView = findViewById(R.id.add_view);
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                if (item.getItemId() == R.id.nav_add_word) {
//                    AddWordsFragment addWordsFragment = new AddWordsFragment();
//                    FragmentManager fragmentManager = getSupportFragmentManager();
//                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                    fragmentTransaction.replace(R.id.main_activity, addWordsFragment);
//                    fragmentTransaction.addToBackStack(null);
//                    fragmentTransaction.commit();
//                }
//                // Close the navigation drawer
//                DrawerLayout drawer = findViewById(R.id.main_activity);
//                drawer.closeDrawer(GravityCompat.START);
//                return true;
//            }
//        });
//        search();
    }
}