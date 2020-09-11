package com.hrbeu.O2O.service;

import com.hrbeu.O2O.BaseTest;
import com.hrbeu.O2O.Pojo.Product;
import com.hrbeu.O2O.Pojo.ProductCategory;
import com.hrbeu.O2O.Pojo.ProductImg;
import com.hrbeu.O2O.Pojo.Shop;
import com.hrbeu.O2O.Pojo_sup.ImageHolder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductServiceTest extends BaseTest {
    @Autowired
    private ProductService productService;
    @Test
    public void test01() throws FileNotFoundException {
        Product product = new Product();
        Shop shop = new Shop();
        ProductCategory category = new ProductCategory();
        category.setProductCategoryId(4L);
        shop.setShopId(1L);
        product.setShop(shop);
        product.setProductCategory(category);
        product.setProductName("test1");
        product.setProductDesc("test1");
        product.setPriority(5);
        product.setNormalPrice("100");
        product.setPromotionPrice("50");
        product.setCreateTime(new Date());
        File file = new File("F:"+ File.separator+"test.png");
        InputStream inputStream = new FileInputStream(file);
        ImageHolder holder = new ImageHolder(file.getName(),inputStream);
        File file1 = new File("F:"+ File.separator+"test.png");
        InputStream inputStream1 = new FileInputStream(file1);
        File file2 = new File("F:"+ File.separator+"test.png");
        InputStream inputStream2 = new FileInputStream(file2);
        List<ImageHolder> holderList = new ArrayList<>();
        ImageHolder imageHolder1 = new ImageHolder(file1.getName(),inputStream1);
        ImageHolder imageHolder2 = new ImageHolder(file2.getName(),inputStream2);
        holderList.add(imageHolder1);
        holderList.add(imageHolder2);
        productService.addProduct(product,holder,holderList);




    }
}
