package com.kruszynski.plubreakers.codetest.reply;

import android.graphics.drawable.Drawable;

public class TestReply {
    private String productName;
    private String insertedCode;
    private String correctCode;
    private Drawable image;
    private Boolean isCorrect;

    public TestReply(String productName, String insertedCode, String correctCode,Drawable image) {
        this.productName = productName;
        this.insertedCode = insertedCode;
        this.correctCode = correctCode;
        this.isCorrect = insertedCode.equals(correctCode);
        this.image = image;
    }

    public String getProductName() {
        return productName;
    }

    public String getInsertedCode() {
        return insertedCode;
    }
    public boolean isCorrect() {
        return isCorrect;
    }
    public String getCorrectCode() {
        return correctCode;
    }
    public Drawable getImage() {
        return image;
    }
}
