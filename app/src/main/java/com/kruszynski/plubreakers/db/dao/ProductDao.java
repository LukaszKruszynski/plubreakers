package com.kruszynski.plubreakers.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.kruszynski.plubreakers.codefinder.model.Product;
import com.kruszynski.plubreakers.codetest.model.ProductTest;
import com.kruszynski.plubreakers.codetest.model.ProductType;

import java.util.List;

@Dao
public interface ProductDao {
    @Insert
    void insert(Product... product);

    @Query("SELECT * FROM Products")
    List<ProductTest> getAll();

    @Query("SELECT * FROM PRODUCTS WHERE Product_Type = :productType")
    List<ProductTest> getProductsByType(ProductType productType);
}
