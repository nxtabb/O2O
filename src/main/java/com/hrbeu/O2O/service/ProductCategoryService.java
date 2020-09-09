package com.hrbeu.O2O.service;

import com.hrbeu.O2O.Pojo.ProductCategory;
import com.hrbeu.O2O.Pojo_sup.ProductCategoryExecution;
import com.hrbeu.O2O.dao.ProductCategoryDao;
import com.hrbeu.O2O.exceptions.ProductCategoryException;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductCategoryService {
    List<ProductCategory> getProductCategoryList(Long shopId);
    ProductCategoryExecution batchInsertProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryException;
    //将商品类别删除后必须将所属商品的shopId属性设为空，在进行删除
    ProductCategoryExecution deleteProductCategory(long productCategoryId,long shopId) throws ProductCategoryException;
}
