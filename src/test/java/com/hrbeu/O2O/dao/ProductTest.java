package com.hrbeu.O2O.dao;

import com.hrbeu.O2O.BaseTest;
import com.hrbeu.O2O.Pojo.Product;
import com.hrbeu.O2O.Pojo.ProductCategory;
import com.hrbeu.O2O.Pojo.Shop;
import com.hrbeu.O2O.Pojo.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class ProductTest extends BaseTest {
    @Autowired
    ProductDao productDao;
    @Test
    public void test01(){
        Shop shop = new Shop();
        shop.setShopId(1L);
        ProductCategory category = new ProductCategory();
        category.setProductCategoryId(4L);
        Product product =new Product();
        product.setProductId(1L);
        product.setProductName("店铺1");
        product.setProductDesc("店铺1的描述");
        product.setImgAddr("test");
        product.setNormalPrice("100");
        product.setPromotionPrice("50");
        product.setPriority(1);
        product.setCreateTime(new Date());
        product.setLastEditTime(new Date());
        product.setEnableStatus(1);
        product.setProductCategory(category);
        product.setShop(shop);
        productDao.insertProduct(product);

    }
}
