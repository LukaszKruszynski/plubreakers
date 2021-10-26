package com.kruszynski.plubreakers.codefinder.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.kruszynski.plubreakers.codetest.model.ProductType;

@Entity(tableName = "Products",indices = {@Index(value = {"Product_Name","Code_Plu"},unique = true)})
public class Product {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "Product_Name")
    private String name;
    @ColumnInfo(name = "Code_Plu")
    private String codePlu;
    @ColumnInfo(name = "Code_Long")
    private String codeLong;
    @ColumnInfo(name = "Product_Type")
    private ProductType productType;
    @ColumnInfo(name = "Product_Image", typeAffinity = ColumnInfo.BLOB)
    private byte [] image;

    public Product(int id, String name, String codePlu, String codeLong, ProductType productType, byte[] image) {
        this.id = id;
        this.name = name;
        this.codePlu = codePlu;
        this.codeLong = codeLong;
        this.productType = productType;
        this.image = image;
    }

    @Ignore
    public Product(String name, String codePlu, String codeLong, ProductType productType, byte[] image) {
        this.name = name;
        this.codePlu = codePlu;
        this.codeLong = codeLong;
        this.productType = productType;
        this.image = image;
    }

    @Ignore
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
