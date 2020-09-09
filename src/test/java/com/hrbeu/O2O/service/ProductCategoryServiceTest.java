package com.hrbeu.O2O.service;

import com.hrbeu.O2O.BaseTest;
import com.hrbeu.O2O.Pojo_sup.ProductCategoryExecution;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductCategoryServiceTest extends BaseTest {
    @Autowired
    public ProductCategoryService productCategoryService;
    @Test
    public void test01(){
        ProductCategoryExecution productCategoryExecution = productCategoryService.deleteProductCategory(6,1);
        productCategoryExecution.getState();
    }
}
