package com.hrbeu.O2O.service.Impl;

import com.hrbeu.O2O.Pojo.Shop;
import com.hrbeu.O2O.Pojo_sup.ImageHolder;
import com.hrbeu.O2O.Pojo_sup.ShopExecution;
import com.hrbeu.O2O.dao.ShopDao;
import com.hrbeu.O2O.enums.ShopStateEnum;
import com.hrbeu.O2O.exceptions.ShopOperationException;
import com.hrbeu.O2O.service.ShopService;
import com.hrbeu.O2O.utils.ImgUtil;
import com.hrbeu.O2O.utils.PageUtil;
import com.hrbeu.O2O.utils.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopDao shopDao;
    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, ImageHolder thumbnail) {
        //
        if(shop==null){
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        try {
            //shop默认刚刚添加时是审核中 因此设置enableStatus为0
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            int effectedNum = shopDao.insertShop(shop);
            if(effectedNum<=0){
                //如果插入的行小于等于0，则插入失败
                throw new ShopOperationException("店铺创建失败");
            }
            else {
                if(thumbnail.getImage()!=null){
                    try {
                        addShopImg(shop,thumbnail);
                    }
                    catch (Exception e){
                        throw new ShopOperationException("addshopImgERROR:"+e.getMessage());
                    }
                    effectedNum = shopDao.updateShop(shop);
                    if(effectedNum<=0){
                        throw new ShopOperationException("更新图片地址失败");
                    }
                }
            }
        }catch (Exception e){
            throw new ShopOperationException("addShopError"+e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK,shop);
    }

    @Override
    public Shop getByShopId(long shopId) {
        //直接返回商铺信息
        return shopDao.queryByShopId(shopId);
    }

    @Override
    public ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException {
        //1.判断是否需要更新图片
        //需要删除旧的图片，并且更新图片
        try {
            if(shop==null||shop.getShopId()==null){
                //返回错误信息，无该商铺
                return new ShopExecution(ShopStateEnum.NULL_SHOP);
            }
            else {
                //判断输入图片是否为空，并且商铺id是否为空
                if(thumbnail!=null&&thumbnail.getImage()!=null&&thumbnail.getImageName()!=null){
                    Shop tempShop = shopDao.queryByShopId(shop.getShopId());
                    if(tempShop.getShopImg()!=null){
                        //删除原图片
                        ImgUtil.delFileOrPath(tempShop.getShopImg());
                    }
                    //加入新图片
                    this.addShopImg(shop,thumbnail);
                    System.out.println(123);
                }
                //更新店铺信息
                //更改数据库
                shop.setLastEditTime(new Date());
                int effectNum = shopDao.updateShop(shop);
                if(effectNum<=0){
                    return new ShopExecution(ShopStateEnum.INNER_ERROR);
                }
                else {
                    shop = shopDao.queryByShopId(shop.getShopId());
                    return new ShopExecution(ShopStateEnum.SUCCESS,shop);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new ShopOperationException("modifyShopError"+e.getMessage());
        }
    }
    //返回带分页的shopExecution列表
    @Override
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
        int rowIndex = PageUtil.pageIndexToRowIndex(pageIndex,pageSize);
        List<Shop> shopList = shopDao.quertShopList(shopCondition,rowIndex,pageSize);
        int count = shopDao.queryShopCount(shopCondition);
        ShopExecution shopExecution = new ShopExecution();
        if(shopList!=null){
            shopExecution.setShopList(shopList);
            shopExecution.setCount(count);

        }else {
            shopExecution.setState(ShopStateEnum.INNER_ERROR.getState());
        }
        return shopExecution;
    }


    //调用ImgUtil的方法generateThumbnail存储图片，并更新shop对象的属性。
    private void addShopImg(Shop shop, ImageHolder thumbnail) {
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        String shopImgAddr = ImgUtil.generateThumbnail(thumbnail,dest);
        shop.setShopImg(shopImgAddr);
    }
}
