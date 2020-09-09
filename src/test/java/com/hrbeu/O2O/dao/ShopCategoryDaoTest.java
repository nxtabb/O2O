package com.hrbeu.O2O.dao;

import com.hrbeu.O2O.BaseTest;
import com.hrbeu.O2O.Pojo.ProductCategory;
import com.hrbeu.O2O.Pojo.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ShopCategoryDaoTest extends BaseTest {
    @Autowired
    ShopCategoryDao shopCategoryDao;
    @Test
    public void test1(){
        ShopCategory shopCategory1 = new ShopCategory();
        ShopCategory shopCategory2 = new ShopCategory();
        shopCategory1.setShopCategoryId(2L);
        shopCategory2.setShopCategoryId(1L);
        shopCategory1.setParent(shopCategory2);
        List<ShopCategory> categories = shopCategoryDao.queryShopCategory(shopCategory1);
        assertEquals(2,categories.size());

    }


}
