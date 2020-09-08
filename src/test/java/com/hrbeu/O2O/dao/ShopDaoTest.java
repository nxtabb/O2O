package com.hrbeu.O2O.dao;

import static org.junit.Assert.*;
import com.hrbeu.O2O.BaseTest;
import com.hrbeu.O2O.Pojo.Area;
import com.hrbeu.O2O.Pojo.PersonInfo;
import com.hrbeu.O2O.Pojo.Shop;
import com.hrbeu.O2O.Pojo.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class ShopDaoTest extends BaseTest {
    @Autowired
    ShopDao shopDao;
    @Test
    public void test01(){
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
        shop.setShopName("test");
        shop.setShopDesc("test");
        shop.setShopAddr("test");
        shop.setPhone("test");
        shop.setShopImg("test");
        shop.setPriority(1);
        shop.setCreateTime(new Date());
        shop.setLastEditTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("审核中");
        int effectNum = shopDao.insertShop(shop);
    }

    @Test
    public void test02(){
        Shop shop = new Shop();
        shop.setShopId(1L);
        shop.setShopName("aa");
        shop.setShopDesc("bb");
        shop.setShopAddr("cc");
        shop.setLastEditTime(new Date());
        int effectNum = shopDao.updateShop(shop);
    }

    @Test
    public void test3(){
        long shopId = 1L;
        Shop shop = shopDao.queryByShopId(shopId);
        System.out.println(shop.getShopId()+shop.getShopCategory().getShopCategoryName()+shop.getArea().getAreaName());
    }
    @Test
    public void testQueryShopList(){
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        owner.setUserId(1L);
        shop.setOwner(owner);
        shop.setShopName("tes");
        int a = shopDao.queryShopCount(shop);
        System.out.println(a);
        List<Shop> shopList = shopDao.quertShopList(shop,1,5);
        System.out.println(shopList.get(0).getShopId());
    }

}
