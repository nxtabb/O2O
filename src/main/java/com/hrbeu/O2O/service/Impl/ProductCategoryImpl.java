package com.hrbeu.O2O.service.Impl;

import com.hrbeu.O2O.Pojo.ProductCategory;
import com.hrbeu.O2O.dao.ProductCategoryDao;
import com.hrbeu.O2O.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryDao productCategoryDao;
    @Override
    public List<ProductCategory> getProductCategoryList(Long shopId) {
        return productCategoryDao.queryProductList(shopId);
    }
}
