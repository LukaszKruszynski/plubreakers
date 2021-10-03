package com.kruszynski.plubreakers.codetest.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Arrays;
import java.util.Objects;

@Entity(tableName = "Products_Test",indices = {@Index(value = {"Product_Name","Code_Plu"},unique = true)})
public class ProductTest {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "Product_Name")
    private String name;
    @ColumnInfo(name = "Code_Plu")
    private String codePLU;
    @ColumnInfo(name = "Product_Type")
    private ProductType productType;
    @ColumnInfo(name = "Product_Image", typeAffinity = ColumnInfo.BLOB)
    private byte[] image;

    public ProductTest(int id, String name, String codePLU, ProductType productType, byte[] image) {
        this.id = id;
        this.name = name;
        this.codePLU = codePLU;
        this.productType = productType;
        this.image = image;
    }

    @Ignore
    public ProductTest(String name, String codePLU, ProductType productType, byte[] image) {
        this.name = name;
        this.codePLU = codePLU;
        this.productType = productType;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodePLU() {
        return codePLU;
    }

    public void setCodePLU(String codePLU) {
        this.codePLU = codePLU;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductTest that = (ProductTest) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(codePLU, that.codePLU) && productType == that.productType && Arrays.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, codePLU, productType);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }

    @Override
    public String toString() {
        return "ProductTest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", codePLU='" + codePLU + '\'' +
                ", productType=" + productType +
                '}';
    }
}