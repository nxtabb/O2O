package com.hrbeu.O2O.service;

import com.hrbeu.O2O.Pojo.Shop;
import com.hrbeu.O2O.Pojo_sup.ImageHolder;
import com.hrbeu.O2O.Pojo_sup.ShopExecution;
import com.hrbeu.O2O.exceptions.ShopOperationException;

import java.io.File;
import java.io.InputStream;
import java.util.List;

public interface ShopService {
    ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;
    Shop getByShopId(long shopId);
    ShopExecution modifyShop(Shop shop,ImageHolder thumbnail) throws ShopOperationException;
    //shopExcution中存在count属性，因此可以使用shopExcution得到总数
    ShopExecution getShopList(Shop shopCondition,int pageIndex,int pageSize);
}
