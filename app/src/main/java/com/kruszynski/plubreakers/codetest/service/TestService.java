package com.kruszynski.plubreakers.codetest.service;

import android.content.Context;
import android.os.Build;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.kruszynski.plubreakers.codetest.model.ProductTest;
import com.kruszynski.plubreakers.codetest.model.ProductType;
import com.kruszynski.plubreakers.codetest.reply.TestReply;
import com.kruszynski.plubreakers.db.AppDatabase;
import com.kruszynski.plubreakers.decoder.ImageDecoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class TestService {
    private List<ProductTest> productTests;

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void fetchProducts(Context context,Set<ProductType> productTypes) {
        if (productTests == null) {
            productTests = new ArrayList<>();
           productTypes.forEach(productType -> {
                productTests.addAll(AppDatabase.instance(context).productTestDao().getProductsByType(productType));
            });
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<ProductTest> getRandomProducts(Context context, int quantity, Set<ProductType> productTypes) {

        fetchProducts(context, productTypes);
        Random random = new Random();
        List<ProductTest> randomProductTests = new ArrayList<>();
        if (quantity >= productTests.size()) {
            Collections.shuffle(productTests);
            return productTests;
        }
        while (randomProductTests.size() < quantity) {
            int randomNumber = random.nextInt(productTests.size() - 1);
            if (!randomProductTests.contains(productTests.get(randomNumber))) {
                randomProductTests.add(productTests.get(randomNumber));
            }
        }
        Collections.shuffle(randomProductTests);
        return randomProductTests;
    }

    public Integer calculatePercentOfCorrectReplies(List<TestReply> replies) {
        Integer correctReplies = howMuchCorrectReplies(replies);
        return correctReplies * 100 / (correctReplies + howMuchIncorrectReplies(replies));
    }

    public Integer howMuchCorrectReplies(List<TestReply> replies) {
        int correctReplies = 0;
        for (TestReply reply : replies) {
            if (reply.isCorrect()) {
                correctReplies++;
            }
        }
        return correctReplies;
    }

    public Integer howMuchIncorrectReplies(List<TestReply> replies) {
        int incorrectReplies = 0;
        for (TestReply reply : replies) {
            if (!reply.isCorrect()) {
                incorrectReplies++;
            }
        }
        return incorrectReplies;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<TestReply> fetchUnfinishedReplies(List<TestReply> finishedReplies, List<ProductTest> testProductTests) {
        List<TestReply> allReplies = new ArrayList<>();
        List<String> testProductNames = testProductTests.stream().map(ProductTest::getName).collect(Collectors.toList());
        List<String> repliesProductNames = finishedReplies.stream().map(TestReply::getProductName).collect(Collectors.toList());
        for (String name : testProductNames) {
            if (!repliesProductNames.contains(name)) {
                ProductTest unfinished = findProductByName(testProductTests, name);
                allReplies.add(new TestReply(unfinished.getName(), "PLU", unfinished.getCodePLU(), ImageDecoder.bytes2Drawable(unfinished.getImage())));
            }
        }
        return allReplies;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public ProductTest findProductByName(List<ProductTest> productTests, String name) {
        return productTests.stream().filter(p -> p.getName().equals(name)).findAny().get();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initButtonConfig(Button button, TextView textView) {
        button.setOnClickListener(l -> {
            if (textView.length() < 4) {
                textView.append(button.getText());
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void initDoubleTextButtonConfig(Button button, TextView textView) {
        button.setOnClickListener(l -> {
            if (textView.length() < 3) {
                textView.append(button.getText());
            } else if (textView.length() == 3) {
                textView.append(button.getText().toString().substring(0, 1));
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void initClearButtonFunction(Button button, TextView textView) {
        button.setOnClickListener(l -> {
            int length = textView.getText().length();
            if (length != 0) {
                String substring = textView.getText().toString().substring(0, length - 1);
                textView.setText(substring);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void init0To9Buttons(TextView textView, Button... buttons) {
        for (Button button : buttons) {
            this.initButtonConfig(button, textView);
        }
    }
}

