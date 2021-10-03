package com.kruszynski.plubreakers.codetest.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

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
    private Switch snacksSw;
    private List<Switch> parentSwitches;
    private List<Switch> childrenSwitches;

    public TestConfigActivity() {
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_config);
        initViewComponents();
        initStartButtonLogic();
        initBakeryBehaviorSwitches();
        fillParentSwitchesList();
        fillChildrenSwitchesList();
        setCheckedSwitches(true,parentSwitches,childrenSwitches);
    }

    private void initViewComponents() {
        countProductsTestET = findViewById(R.id.count_codes_test_et);
        startTestBt = findViewById(R.id.start_test_bt);
        bakedSw = findViewById(R.id.baked_sw);
        fruitsSw = findViewById(R.id.fruits_sw);
        candiesSw = findViewById(R.id.candies_sw);
        breadsSw = findViewById(R.id.breads_sw);
        rollsSw = findViewById(R.id.rolls_sw);
        snacksSw = findViewById(R.id.snacks_sw);
        vegetablesSw = findViewById(R.id.vegetables_sw);
    }

    private void fillParentSwitchesList() {
        parentSwitches = new ArrayList<>();
        parentSwitches.add(bakedSw);
        parentSwitches.add(fruitsSw);
        parentSwitches.add(vegetablesSw);
        parentSwitches.add(candiesSw);
    }

    private void fillChildrenSwitchesList() {
        childrenSwitches = new ArrayList<>();
        childrenSwitches.add(breadsSw);
        childrenSwitches.add(rollsSw);
        childrenSwitches.add(snacksSw);
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
                        s.setAlpha(1f);
                        s.setChecked(true);
                        s.setClickable(true);
                    });
                } else {
                    Arrays.stream(childrenSwitches).forEach(s -> {
                        s.setAlpha(0.5f);
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
                        childrenSwitches.forEach(v -> v.setClickable(true));
                    }
                    if (!isChecked && getChildrenBakeryUncheckSwitches().size() == 2) {
                        getChildrenBakeryCheckedSwitches().stream().findAny().get().setClickable(false);
                    } else if (getChildrenBakeryCheckedSwitches().isEmpty()) {
                        parentSwitch.setChecked(false);
                    }
                }
            });
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initBakeryBehaviorSwitches() {
        initFamilySwitching(bakedSw, breadsSw, rollsSw, snacksSw);
        initChildrenSwitchesBehavior(bakedSw, breadsSw, rollsSw, snacksSw);
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
                    intent.putExtra(getString(R.string.baked_extra_is_checked), bakedSw.isChecked());
                    if (bakedSw.isChecked()) {
                        intent.putExtra(getString(R.string.bread_extra_is_checked), breadsSw.isChecked());
                        intent.putExtra(getString(R.string.rolls_extra_is_checked), rollsSw.isChecked());
                        intent.putExtra(getString(R.string.snacks_extra_is_checked), snacksSw.isChecked());
                    }
                    intent.putExtra(getString(R.string.fruits_extra_sw_is_checked), fruitsSw.isChecked());
                    intent.putExtra(getString(R.string.vegetables_extra_sw_is_checked), vegetablesSw.isChecked());
                    intent.putExtra(getString(R.string.candies_extra_sw_is_checked), candiesSw.isChecked());
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