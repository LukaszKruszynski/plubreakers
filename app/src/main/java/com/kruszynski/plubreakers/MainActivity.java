package com.kruszynski.plubreakers;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.kruszynski.plubreakers.codefinder.activity.CodeFinderActivity;
import com.kruszynski.plubreakers.codetest.activity.TestConfigActivity;

public class MainActivity extends AppCompatActivity {
    private ImageButton testPluBt;
    private Button codesBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        initViews();
        initViewsClickListener();
    }

    private void initViews() {
        testPluBt = findViewById(R.id.test_plu_bt);
        codesBt = findViewById(R.id.codes_bt);
    }

    private void initViewsClickListener() {
        testPluBt.setOnClickListener(l -> {
            startActivity(new Intent(getApplicationContext(), TestConfigActivity.class));
        });
        codesBt.setOnClickListener(l -> {
            startActivity(new Intent(getApplicationContext(), CodeFinderActivity.class));
        });
    }
}
