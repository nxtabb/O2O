package com.hrbeu.O2O.service;

import com.hrbeu.O2O.BaseTest;
import com.hrbeu.O2O.Pojo.Area;
import com.hrbeu.O2O.Pojo.PersonInfo;
import com.hrbeu.O2O.Pojo.Shop;
import com.hrbeu.O2O.Pojo.ShopCategory;
import com.hrbeu.O2O.Pojo_sup.ShopExecution;
import com.hrbeu.O2O.enums.ShopStateEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class ShopServiceTest extends BaseTest {
    @Autowired
    private ShopService shopService;
    @Test
    public void insertShop01() throws FileNotFoundException {
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory category = new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(2);
        category.setShopCategoryId(1L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(category);
        shop.setShopName("test1");
        shop.setShopDesc("test1");
        shop.setShopAddr("test1");
        shop.setPhone("test1");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        File shopImg = new File("F:"+File.separator+"test.png");
        InputStream inputStream = new FileInputStream(shopImg);
        ShopExecution shopExecution = shopService.addShop(shop,inputStream,shopImg.getName());
        assertEquals(ShopStateEnum.CHECK.getState(),shopExecution.getState());
    }
    @Test
    public void modifyShop01() throws FileNotFoundException {
        Shop shop = shopService.getByShopId(1L);
        ShopExecution shopExecution = shopService.modifyShop(shop, new FileInputStream(new File("D:" + File.separator + "test1.png")), "test1.png");
        System.out.println(shopExecution.getState() + shopExecution.getStateInfo());
    }
    @Test
    public void getShopTest(){
        Shop shop =new Shop();
        ShopCategory shopCategory= new ShopCategory();
        shopCategory.setShopCategoryId(1L);
        shop.setShopCategory(shopCategory);
        ShopExecution shopExecutions = shopService.getShopList(shop,1,5);
        int count = shopExecutions.getCount();
        System.out.println(count);
    }
}
