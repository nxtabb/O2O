package com.hrbeu.O2O.service.Impl;

import com.hrbeu.O2O.Pojo.ShopCategory;
import com.hrbeu.O2O.dao.ShopCategoryDao;
import com.hrbeu.O2O.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {
    @Autowired
    ShopCategoryDao shopCategoryDao;
    @Override
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {
        return shopCategoryDao.queryShopCategory(shopCategoryCondition);
    }
}
