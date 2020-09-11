package com.hrbeu.O2O.dao;

import com.hrbeu.O2O.BaseTest;
import com.hrbeu.O2O.Pojo.ProductImg;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductImgDaoTest extends BaseTest {
    @Autowired
    ProductImgDao productImgDao;
    @Test
    public void test01(){
        ProductImg img1= new ProductImg();
        img1.setImgAddr("test");
        img1.setImgDesc("test");
        img1.setPriority(1);
        img1.setCreateTime(new Date());
        img1.setProductId(1L);
        ProductImg img2 = new ProductImg();
        img2.setImgAddr("test1");
        img2.setImgDesc("test1");
        img2.setPriority(2);
        img2.setCreateTime(new Date());
        img2.setProductId(1L);
        List<ProductImg> imgs = new ArrayList<>();
        imgs.add(img1);
        imgs.add(img2);
        productImgDao.batchInsertProductImg(imgs);
    }
}
