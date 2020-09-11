package com.hrbeu.O2O.dao;

import com.hrbeu.O2O.Pojo.Product;

public interface ProductDao {
    Product queryProductById(long productId);
    int insertProduct(Product product);
    int updateProduct(Product product);

}
