package com.hrbeu.O2O.service;

import com.hrbeu.O2O.Pojo.Area;
import com.hrbeu.O2O.Pojo.ShopCategory;

import java.util.List;

public interface ShopCategoryService {
    List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);
}
