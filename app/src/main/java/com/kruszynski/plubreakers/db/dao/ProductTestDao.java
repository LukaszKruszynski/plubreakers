package com.kruszynski.plubreakers.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.kruszynski.plubreakers.codetest.model.ProductTest;
import com.kruszynski.plubreakers.codetest.model.ProductType;

import java.util.List;

@Dao
public interface ProductTestDao {
    @Insert
    void insert(ProductTest... productTest);

    @Query("SELECT * FROM Products_Test")
    List<ProductTest> getAll();

    @Query("SELECT * FROM PRODUCTS_TEST WHERE Product_Type = :productType")
    List<ProductTest> getProductsByType(ProductType productType);
}
