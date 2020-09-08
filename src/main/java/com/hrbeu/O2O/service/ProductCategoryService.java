package com.hrbeu.O2O.service;

import com.hrbeu.O2O.Pojo.ProductCategory;
import com.hrbeu.O2O.dao.ProductCategoryDao;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductCategoryService {
    List<ProductCategory> getProductCategoryList(Long shopId);
}
