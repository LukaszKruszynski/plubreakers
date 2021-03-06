package com.kruszynski.plubreakers.codetest.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.kruszynski.plubreakers.MainActivity;
import com.kruszynski.plubreakers.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestConfigActivity extends AppCompatActivity {
    private EditText countProductsTestET;
    private Button startTestBt;
    private Switch bakedSw;
    private Switch fruitsSw;
    private Switch vegetablesSw;
    private Switch candiesSw;
    private Switch breadsSw;
    private Switch rollsSw;
    private Switch snacksSweetSw;
    private Switch snacksSaltySw;
    private Switch baguetteSw;
    private Switch donutsSw;
    private Switch nutsSw;
    private List<Switch> parentSwitches;
    private List<Switch> childrenSwitches;

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(mainIntent);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.home);
        getSupportActionBar().setTitle(getString(R.string.test_config));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_test_config);
        initViewComponents();
        initStartButtonLogic();
        fillParentSwitchesList();
        fillChildrenSwitchesList();
        setCheckedSwitches(true, parentSwitches, childrenSwitches);
        initBehaviorSwitches();
    }

    private void initViewComponents() {
        countProductsTestET = findViewById(R.id.count_codes_test_et);
        startTestBt = findViewById(R.id.start_test_bt);
        bakedSw = findViewById(R.id.baked_sw);
        fruitsSw = findViewById(R.id.fruits_sw);
        candiesSw = findViewById(R.id.candies_sw);
        breadsSw = findViewById(R.id.breads_sw);
        rollsSw = findViewById(R.id.rolls_sw);
        snacksSweetSw = findViewById(R.id.snacks_sweet_sw);
        snacksSaltySw = findViewById(R.id.snacks_salty_sw);
        baguetteSw = findViewById(R.id.baguette_sw);
        donutsSw = findViewById(R.id.donats_sw);
        vegetablesSw = findViewById(R.id.vegetables_sw);
        nutsSw = findViewById(R.id.nuts_sw);
    }

    private void fillParentSwitchesList() {
        parentSwitches = new ArrayList<>();
        parentSwitches.add(bakedSw);
        parentSwitches.add(fruitsSw);
        parentSwitches.add(vegetablesSw);
        parentSwitches.add(candiesSw);
        parentSwitches.add(nutsSw);
    }

    private void fillChildrenSwitchesList() {
        childrenSwitches = new ArrayList<>();
        childrenSwitches.add(breadsSw);
        childrenSwitches.add(rollsSw);
        childrenSwitches.add(snacksSweetSw);
        childrenSwitches.add(snacksSaltySw);
        childrenSwitches.add(donutsSw);
        childrenSwitches.add(baguetteSw);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private List<Switch> getChildrenBakeryUncheckSwitches() {
        return childrenSwitches.stream().filter(s -> !s.isChecked()).collect(Collectors.toList());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private List<Switch> getChildrenBakeryCheckedSwitches() {
        return childrenSwitches.stream().filter(s -> s.isChecked()).collect(Collectors.toList());
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private List<Switch> getParentsUncheckSwitches() {
        return parentSwitches.stream().filter(s -> !s.isChecked()).collect(Collectors.toList());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private List<Switch> getParentsCheckSwitches() {
        return parentSwitches.stream().filter(s -> s.isChecked()).collect(Collectors.toList());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initFamilySwitching(Switch parentSwitch, Switch... childrenSwitches) {
        parentSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Arrays.stream(childrenSwitches).forEach(s -> {
                        parentSwitch.setChecked(true);
                        s.setAlpha(1f);
                        s.setChecked(true);
                        s.setClickable(true);
                    });
                } else {
                    Arrays.stream(childrenSwitches).forEach(s -> {
                        s.setAlpha(0.4f);
                        s.setChecked(false);
                        s.setClickable(false);
                    });
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initChildrenSwitchesBehavior(Switch parentSwitch, Switch... children) {
        Arrays.stream(children).forEach(s -> {
            s.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (getChildrenBakeryCheckedSwitches().size() > 1) {
                        Arrays.stream(children).forEach(s -> s.setClickable(true));
                    }
                    if (!isChecked && getChildrenBakeryUncheckSwitches().size() == 2 && getParentsCheckSwitches().size() < 2) {
                        getChildrenBakeryCheckedSwitches().stream().findAny().get().setClickable(false);
                    }
                    if (getChildrenBakeryCheckedSwitches().isEmpty() && getParentsCheckSwitches().size() > 1) {
                        parentSwitch.setChecked(false);
                    }
                }
            });
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initParentSwitchesBehavior(Switch... parents) {
        Arrays.stream(parents).forEach(s -> {
            s.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked && getParentsCheckSwitches().size() > 1) {
                        parentSwitches.forEach(s -> s.setClickable(true));
                        childrenSwitches.forEach(s -> s.setClickable(true));
                    }
                    if (!isChecked && getParentsCheckSwitches().size() == 1) {
                        getParentsCheckSwitches().stream().findAny().get().setClickable(false);
                    }
                    if (!isChecked && getParentsCheckSwitches().size() == 1) {
                        getChildrenBakeryCheckedSwitches().stream().findAny().ifPresent(s -> s.setClickable(false));
                    }
                }
            });
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initBehaviorSwitches() {
        initFamilySwitching(bakedSw, breadsSw, rollsSw, snacksSweetSw, snacksSaltySw, baguetteSw, donutsSw);
        initChildrenSwitchesBehavior(bakedSw, breadsSw, rollsSw, snacksSweetSw, snacksSaltySw, baguetteSw, donutsSw);
        initParentSwitchesBehavior(fruitsSw,vegetablesSw,candiesSw,nutsSw);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setCheckedSwitches(boolean checked, List<Switch>... switches) {
        Arrays.stream(switches).forEach(l -> {
            l.forEach(s -> {
                s.setChecked(checked);
            });
        });
    }

    private void initStartButtonLogic() {
        startTestBt.setOnClickListener(l -> {
            Intent intent = new Intent(getApplicationContext(), TestActivity.class);
            if (countProductsTestET.getText().length() > 0) {
                int count = Integer.parseInt(countProductsTestET.getText().toString());
                if (count > 0 && count <= 50) {
                    intent.putExtra(getString(R.string.codes_count_extra), count);
                    intent.putExtra(getString(R.string.bread_extra_is_checked), breadsSw.isChecked());
                    intent.putExtra(getString(R.string.rolls_extra_is_checked), rollsSw.isChecked());
                    intent.putExtra(getString(R.string.baguette_extra_is_checked), baguetteSw.isChecked());
                    intent.putExtra(getString(R.string.snacks_sweet_extra_is_checked), snacksSweetSw.isChecked());
                    intent.putExtra(getString(R.string.snacks_salty_extra_is_checked), snacksSaltySw.isChecked());
                    intent.putExtra(getString(R.string.donuts_extra_is_checked), donutsSw.isChecked());
                    intent.putExtra(getString(R.string.fruits_extra_sw_is_checked), fruitsSw.isChecked());
                    intent.putExtra(getString(R.string.vegetables_extra_sw_is_checked), vegetablesSw.isChecked());
                    intent.putExtra(getString(R.string.candies_extra_sw_is_checked), candiesSw.isChecked());
                    intent.putExtra(getString(R.string.nuts_extra_is_checked),nutsSw.isChecked());
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), R.string.incorrect_count_codes_test_toast, Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), R.string.empty_count_codes_test_toast, Toast.LENGTH_SHORT).show();
            }
        });
    }

}