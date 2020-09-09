package com.hrbeu.O2O.dao;

import com.hrbeu.O2O.Pojo.ProductCategory;
import com.hrbeu.O2O.Pojo.ShopCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductCategoryDao {
    //查询某个店铺下的所有商品类别
    List<ProductCategory> queryProductList(Long shopId);
    Integer batchInsertProductCategory(List<ProductCategory> productCategoryList);
    Integer deleteProductCategory(@Param("productCategoryId") long productCategoryId,@Param("shopId") long shopId);
}
