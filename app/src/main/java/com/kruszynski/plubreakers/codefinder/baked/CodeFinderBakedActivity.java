package com.kruszynski.plubreakers.codefinder.baked;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.kruszynski.plubreakers.MainActivity;
import com.kruszynski.plubreakers.R;
import com.kruszynski.plubreakers.codefinder.baked.all.CodeFinderAllBakedActivity;
import com.kruszynski.plubreakers.codefinder.baked.baguette.CodeFinderBaguetteActivity;
import com.kruszynski.plubreakers.codefinder.baked.bread.CodeFinderBreadActivity;
import com.kruszynski.plubreakers.codefinder.baked.donuts.CodeFinderDonutsActivity;
import com.kruszynski.plubreakers.codefinder.baked.rolls.CodeFinderRollsActivity;
import com.kruszynski.plubreakers.codefinder.baked.salty.CodeFinderSnacksSaltyActivity;
import com.kruszynski.plubreakers.codefinder.baked.sweet.CodeFinderSnacksSweetActivity;

public class CodeFinderBakedActivity extends AppCompatActivity {

    private Button breadBt, rollsBt, baguetteBt, sweetBt, saltyBt, donutsBt, allBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_finder_baked);
        initComponents();
        initComponentsBehavior();
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.home);
        getSupportActionBar().setTitle(getString(R.string.baked));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(mainIntent);
        return true;
    }

    private void initComponents() {
        breadBt = findViewById(R.id.bread_finder_bt);
        rollsBt = findViewById(R.id.rolls_finder_bt);
        baguetteBt = findViewById(R.id.baguette_finder_bt);
        sweetBt = findViewById(R.id.snacks_sweet_finder_bt);
        saltyBt = findViewById(R.id.snacks_salty_finder_bt);
        donutsBt = findViewById(R.id.donuts_finder_bt);
        allBt = findViewById(R.id.all_baked_finder_bt);
    }

    private void initComponentsBehavior() {
        initBreadBtBehavior();
        initRollsBtBehavior();
        initBaguetteBtBehavior();
        initSweetBtBehavior();
        initSaltyBtBehavior();
        initDonutsBtBehavior();
        initAllBtBehavior();
    }

    private void initBreadBtBehavior() {
        breadBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CodeFinderBreadActivity.class));
            }
        });
    }

    private void initRollsBtBehavior() {
        rollsBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CodeFinderRollsActivity.class));
            }
        });
    }

    private void initBaguetteBtBehavior() {
        baguetteBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CodeFinderBaguetteActivity.class));
            }
        });
    }

    private void initSweetBtBehavior() {
        sweetBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CodeFinderSnacksSweetActivity.class));
            }
        });
    }

    private void initSaltyBtBehavior() {
        saltyBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CodeFinderSnacksSaltyActivity.class));
            }
        });
    }

    private void initDonutsBtBehavior() {
        donutsBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CodeFinderDonutsActivity.class));
            }
        });
    }

    private void initAllBtBehavior() {
        allBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CodeFinderAllBakedActivity.class));
            }
        });
    }
}
