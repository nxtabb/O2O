package com.hrbeu.O2O.dao;

import com.hrbeu.O2O.BaseTest;
import com.hrbeu.O2O.Pojo.ProductCategory;
import com.hrbeu.O2O.Pojo.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductCategoryDaoTest extends BaseTest {
    @Autowired
    ProductCategoryDao productCategoryDao;
    @Test
    public void test01(){
        List<ProductCategory> categoryList = productCategoryDao.queryProductList(1L);
        System.out.println(categoryList.size());

    }
}
