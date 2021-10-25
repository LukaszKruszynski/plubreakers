package com.kruszynski.plubreakers.codefinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.kruszynski.plubreakers.MainActivity;
import com.kruszynski.plubreakers.R;
import com.kruszynski.plubreakers.codefinder.all.CodeFinderAllActivity;
import com.kruszynski.plubreakers.codefinder.baked.CodeFinderBakedActivity;
import com.kruszynski.plubreakers.codefinder.candies.CodeFinderCandiesActivity;
import com.kruszynski.plubreakers.codefinder.fruits.CodeFinderFruitsActivity;
import com.kruszynski.plubreakers.codefinder.nuts.CodeFinderNutsActivity;
import com.kruszynski.plubreakers.codefinder.others.CodeFinderOthersActivity;
import com.kruszynski.plubreakers.codefinder.vegetables.CodeFinderVegetablesActivity;

public class CodeFinderActivityMain extends AppCompatActivity {

    private Button bakedBt, fruitsBt, vegetablesBt, nutsBt, candiesBt, othersBt, allBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.home);
        getSupportActionBar().setTitle(R.string.codes);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_code_finder_main);
        initComponents();
        initComponentsBehavior();
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(mainIntent);
        return true;
    }

    private void initComponents() {
        bakedBt = findViewById(R.id.baked_finder_bt);
        fruitsBt = findViewById(R.id.fruit_finder_bt);
        vegetablesBt = findViewById(R.id.vegetables_finder_bt);
        nutsBt = findViewById(R.id.nuts_finder_bt);
        candiesBt = findViewById(R.id.candies_finder_bt);
        othersBt = findViewById(R.id.other_finder_bt);
        allBt = findViewById(R.id.all_finder_bt);
    }

    private void initComponentsBehavior() {
        initBakedBtBehavior();
        initFruitsBtBehavior();
        initVegetablesBtBehavior();
        initNutsBtBehavior();
        initCandiesBtBehavior();
        initOthersBtBehavior();
        initAllBtBehavior();
    }

    private void initBakedBtBehavior() {
        bakedBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CodeFinderBakedActivity.class));
            }
        });
    }

    private void initFruitsBtBehavior() {
        fruitsBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CodeFinderFruitsActivity.class));
            }
        });
    }

    private void initVegetablesBtBehavior() {
        vegetablesBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CodeFinderVegetablesActivity.class));
            }
        });
    }

    private void initNutsBtBehavior() {
        nutsBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CodeFinderNutsActivity.class));
            }
        });
    }

    private void initCandiesBtBehavior() {
        candiesBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CodeFinderCandiesActivity.class));
            }
        });
    }

    private void initOthersBtBehavior() {
        othersBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CodeFinderOthersActivity.class));
            }
        });
    }

    private void initAllBtBehavior() {
        allBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CodeFinderAllActivity.class));
            }
        });
    }
}