package com.example.trainingapp1;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        TableLayout tableLayout =findViewById(R.id.tab_layout);
        //androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        TabLayout tabLayout = findViewById(R.id.tab_layout);

//        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_label1));
//        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_label2));
//        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_label3));
        // Set the tabs to fill the entire layout.
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final ViewPager pager =findViewById(R.id.pager);
        final PagerAdapter adapter =new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        pager.setAdapter(adapter);


        pager.addOnPageChangeListener(new
                TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(
                new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        pager.setCurrentItem(tab.getPosition());

                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                    }
                });

       FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, CreateNewContactActivity.class);
                startActivity(i);
            }
        });

    }
}