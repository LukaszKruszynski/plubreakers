package com.kruszynski.plubreakers.codetest.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kruszynski.plubreakers.decoder.ImageDecoder;
import com.kruszynski.plubreakers.MainActivity;
import com.kruszynski.plubreakers.R;
import com.kruszynski.plubreakers.codetest.reply.TestReply;
import com.kruszynski.plubreakers.codetest.adapter.TestReplyAdapter;
import com.kruszynski.plubreakers.codetest.service.TestService;
import com.kruszynski.plubreakers.codetest.model.ProductTest;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    private TestService service = new TestService();
    private int timerCounter;
    private int productCounter;
    private int codesCount;
    private List<ProductTest> allTestProducts;
    private List<TestReply> testReplies = new ArrayList<>();
    private Button button0;
    private Button button00;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button buttonPLU;
    private Button buttonClear;
    private TextView codeView;
    private TextView nameView;
    private TextView timer;
    private ImageView imageView;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        codesCount = getCodesCountExtra(getString(R.string.codes_count_extra));
        initViewConfig();
        int size = setProductsConfig(codesCount).size();
        setTimer(size * 3);
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(mainIntent);
        return true;
    }

    private int getCodesCountExtra(String extraName) {
        return getIntent().getIntExtra(getString(R.string.codes_count_extra), 20);
    }

    private void initViews() {
        button0 = findViewById(R.id.button_0);
        button00 = findViewById(R.id.button_00);
        button1 = findViewById(R.id.button_1);
        button2 = findViewById(R.id.button_2);
        button3 = findViewById(R.id.button_3);
        button4 = findViewById(R.id.button_4);
        button5 = findViewById(R.id.button_5);
        button6 = findViewById(R.id.button_6);
        button7 = findViewById(R.id.button_7);
        button8 = findViewById(R.id.button_8);
        button9 = findViewById(R.id.button_9);
        buttonPLU = findViewById(R.id.button_plu);
        buttonClear = findViewById(R.id.button_clear);
        codeView = findViewById(R.id.code_view);
        nameView = findViewById(R.id.product_name);
        timer = findViewById(R.id.timer);
        imageView = findViewById(R.id.product_image);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initViewConfig() {
        initViews();
        service.init0To9Buttons(codeView, button0, button1, button2, button3, button4, button5,
                button6, button7, button8, button9);
        service.initClearButtonFunction(buttonClear, codeView);
        service.initDoubleTextButtonConfig(button00, codeView);
        initButtonPLUConfig();
    }
    private List<ProductTest> setProductsConfig(int recordsCount) {
        List<ProductTest> productTests = initTestProducts(recordsCount);
        fetchFirstTestRecord();
        return productTests;
    }


    private List<ProductTest> initTestProducts(int count) {
        allTestProducts = service.getRandomProducts(getApplicationContext(), count);
        return allTestProducts;
    }

    private void initProductImage() {
        if (allTestProducts != null && allTestProducts.size() > 0) {
            imageView.setImageDrawable(ImageDecoder.bytes2Drawable(allTestProducts.get(0).getImage()));
        }
    }

    public void initProductName() {
        if (allTestProducts != null && allTestProducts.size() > 0) {
            nameView.setText(allTestProducts.get(0).getName());
        }
    }

    public void fetchFirstTestRecord() {
        initProductImage();
        initProductName();
    }


    private void showTestResultInfo() {
        TextView resultView = findViewById(R.id.result_test_info);
        resultView.setText("Twój wynik to " + service.calculatePercentOfCorrectReplies(testReplies) + " %.\n" +
                "Poprawne odpowiedzi " + service.howMuchCorrectReplies(testReplies) + " na " + testReplies.size() + ".");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initButtonPLUConfig() {
        productCounter = 1;
        buttonPLU.setOnClickListener(l -> {
            if (codeView.getText().length() == 0) {
                Toast.makeText(getApplicationContext(), "Kod PLU jest pusty!", Toast.LENGTH_SHORT).show();
            } else if (productCounter < allTestProducts.size()) {
                addReply();
                setNextProductView(productCounter);
                productCounter++;
            } else {
                addReply();
                setContentView(R.layout.result_test);
                displayReplies(testReplies);
            }
        });
    }

    private void setNextProductView(int index) {
        ProductTest productTest = allTestProducts.get(index);
        imageView.setImageDrawable(ImageDecoder.bytes2Drawable(productTest.getImage()));
        nameView.setText(productTest.getName());
        codeView.setText("");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addReply() {
        testReplies.add(new TestReply(
                nameView.getText().toString(),
                codeView.getText().toString(),
                getProductByName(nameView.getText().toString()).getCodePLU(),
                imageView.getDrawable()));

    }

    private void displayReplies(List<TestReply> replies) {
        TestReplyAdapter adapter = new TestReplyAdapter(replies);
        RecyclerView recyclerView = findViewById(R.id.recycler_view_result_test);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false);
        DividerItemDecoration decorator = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(decorator);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        showTestResultInfo();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private ProductTest getProductByName(String name) {
        return allTestProducts.stream().filter(productTest -> productTest.getName().equals(name)).findAny().get();
    }

    private void setTimer(int seconds) {
        timerCounter = seconds;
        new CountDownTimer(timerCounter * 1000l, 1000l) {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onTick(long millisUntilFinished) {
                if (timerCounter >= 60 && timerCounter < 70) {
                    timer.setText("1:0" + (timerCounter - 60));
                } else if (timerCounter > 60) {
                    timer.setText("1:" + (timerCounter - 60));
                } else if (timerCounter <= 60 && timerCounter > 9) {
                    timer.setText("0:" + timerCounter);
                } else {
                    timer.setText("0:0" + timerCounter);
                }
                timerCounter--;
                if (timerCounter == -2) {
                    onFinish();
                }
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onFinish() {
                testReplies.addAll(service.fetchUnfinishedReplies(testReplies, allTestProducts));
                setContentView(R.layout.result_test);
                displayReplies(testReplies);
            }
        }.start();
    }
}
