package com.kruszynski.plubreakers;

import android.content.Intent;
import android.database.CursorWindow;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.kruszynski.plubreakers.codefinder.CodeFinderActivityMain;
import com.kruszynski.plubreakers.codetest.activity.TestConfigActivity;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {
    private ImageButton testPluBt;
    private Button codesBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBiggerSizeBlob();
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
            startActivity(new Intent(getApplicationContext(), CodeFinderActivityMain.class));
        });
    }
    private void setBiggerSizeBlob() {
        try {
            Field field = CursorWindow.class.getDeclaredField("sCursorWindowSize");
            field.setAccessible(true);
            field.set(null, 100 * 1024 * 1024); //the 100MB is the new size
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
