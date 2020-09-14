package com.hrbeu.O2O.dao;

import com.hrbeu.O2O.Pojo.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopDao {
    //返回1 则插入成功，返回0则插入店铺失败
    int insertShop(Shop shop);
    int updateShop(Shop shop);
    Shop queryByShopId(long shopId);
    //分页查询店铺列表 店铺名（模糊）、店铺状态、店铺类别、区域id、owner
    //rowindex：从第几行开始取
    //pageSize：每页取多少行数据
    List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition, @Param("rowIndex") int rowindex,@Param("pageSize") int pageSize);
    //返回查询结果个数
    int queryShopCount(@Param("shopCondition") Shop shopCondition);
}
