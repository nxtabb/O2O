package com.hrbeu.O2O.dao;

import com.hrbeu.O2O.BaseTest;
import com.hrbeu.O2O.Pojo.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sun.dc.pr.PRError;

import java.util.Date;
import java.util.List;

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


    @Test
    public void test02(){
        Product product = new Product();
        product.setProductId(1L);
        product.setProductName("店铺111");
        product.setNormalPrice("200");
        product.setPromotionPrice("100");
        product.setPriority(2);
        product.setLastEditTime(new Date());
        productDao.updateProduct(product);

    }

    @Test
    public void test03(){
        long a = 1L;
        Product product = productDao.queryProductById(a);
        System.out.println(product.getShop().getShopId());
        System.out.println(product.getProductName());
        List<ProductImg> list = product.getProductImgList();
        System.out.println(list);
    }

    @Test
    public void test04(){
        Product productCondition = new Product();
        productCondition.setProductId(1L);
        productCondition.setProductName("店铺");
        List<Product > products = productDao.queryProductList(productCondition,0,5);
        int a = productDao.queryProductCount(productCondition);
        System.out.println(a);
        for(Product product:products){
            System.out.println(product.getImgAddr());
        }
    }
}
