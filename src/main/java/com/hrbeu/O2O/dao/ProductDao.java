package com.hrbeu.O2O.dao;

import com.hrbeu.O2O.Pojo.Product;
import com.hrbeu.O2O.Pojo.Shop;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.converter.json.GsonBuilderUtils;

import java.util.List;

public interface ProductDao {
    Product queryProductById(long productId);
    int insertProduct(Product product);
    int updateProduct(Product product);
    List<Product> queryProductList(@Param("productCondition")Product productCondition, @Param("rowIndex") int rowindex, @Param("pageSize") int pageSize);
    int queryProductCount(@Param("productCondition")Product productCondition);
    int updateProductCategoryToNull(Long ProductCategoryId);



}
