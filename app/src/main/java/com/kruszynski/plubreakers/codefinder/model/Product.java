package com.kruszynski.plubreakers.codefinder.model;

import com.kruszynski.plubreakers.codetest.model.ProductType;

public class Product {
    private int id;
    private String name;
    private String codePlu;
    private String codeLong;
    private ProductType productType;
    private byte [] image;

    public Product(String name, String codePlu, String codeLong, ProductType productType, byte[] image) {
        this.name = name;
        this.codePlu = codePlu;
        this.codeLong = codeLong;
        this.productType = productType;
        this.image = image;
    }

    public Product(String name, String codePlu, ProductType productType, byte[] image) {
        this.name = name;
        this.codePlu = codePlu;
        this.productType = productType;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCodePlu() {
        return codePlu;
    }

    public String getCodeLong() {
        return codeLong;
    }

    public ProductType getProductType() {
        return productType;
    }

    public byte[] getImage() {
        return image;
    }
}
