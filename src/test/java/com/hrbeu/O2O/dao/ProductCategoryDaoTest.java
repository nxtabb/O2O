package com.hrbeu.O2O.dao;

import com.hrbeu.O2O.BaseTest;
import com.hrbeu.O2O.Pojo.ProductCategory;
import com.hrbeu.O2O.Pojo.ShopCategory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoTest extends BaseTest {
    @Autowired
    ProductCategoryDao productCategoryDao;
    @Test
    public void test01(){
        List<ProductCategory> categoryList = productCategoryDao.queryProductList(1L);
        System.out.println(categoryList.size());

    }

    @Test
    public void test2(){
        List<ProductCategory> productCategoryList = new ArrayList<>();
        ProductCategory productCategory1 = new ProductCategory();
        ProductCategory productCategory2 = new ProductCategory();
        productCategory1.setProductCategoryName("aaa");
        productCategory1.setPriority(1);
        productCategory1.setShopId(1L);
        productCategory2.setProductCategoryName("bbb");
        productCategory2.setPriority(2);
        productCategory2.setShopId(1L);
        productCategoryList.add(productCategory1);
        productCategoryList.add(productCategory2);
        int a = productCategoryDao.batchInsertProductCategory(productCategoryList);
        Assert.assertEquals(a,2);


    }

    @Test
    public void tese3(){
        productCategoryDao.deleteProductCategory(1,1);
    }
}
