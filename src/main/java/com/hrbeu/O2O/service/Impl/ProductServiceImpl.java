package com.hrbeu.O2O.service.Impl;

import com.hrbeu.O2O.Pojo.Product;
import com.hrbeu.O2O.Pojo.ProductImg;
import com.hrbeu.O2O.Pojo_sup.ImageHolder;
import com.hrbeu.O2O.Pojo_sup.ProductExecution;
import com.hrbeu.O2O.dao.ProductDao;
import com.hrbeu.O2O.dao.ProductImgDao;
import com.hrbeu.O2O.enums.ProductStateEnum;
import com.hrbeu.O2O.exceptions.ProductCategoryException;
import com.hrbeu.O2O.exceptions.ProductException;
import com.hrbeu.O2O.service.ProductService;
import com.hrbeu.O2O.utils.ImgUtil;
import com.hrbeu.O2O.utils.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductImgDao productImgDao;

    /*
    thumbnail:包含缩略图信息：imageName+InputStream
    imageHolderList包含商品详情信息
     */

    @Override
    public ProductExecution addProduct(Product product, ImageHolder thumbnail,List<ImageHolder> imageHolderList) throws ProductCategoryException {
        //1.处理缩略图，获取缩略图相对路径，赋值给product
        if(product!=null&&product.getShop()!=null&&product.getShop().getShopId()!=null) {
            product.setCreateTime(new Date());
            product.setLastEditTime(new Date());
            product.setEnableStatus(1);
            if (thumbnail != null) {
                //添加缩略图
                addThubmnail(product,thumbnail);
            }
            try {
                //2.向product表中写入商品信息,并获取productId
                int effNum = productDao.insertProduct(product);
                if (effNum<=0){
                    throw new ProductException("添加商品失败");
                }
            }catch (Exception e){
                throw new ProductException("创建商品失败"+e.getMessage());
            }
        //3.通过productId进行图片批量上传
            if(imageHolderList!=null&&imageHolderList.size()>0){
                //4.向tb_product_img插入数据
                addProductImgList(product,imageHolderList);
            }
            return new ProductExecution(ProductStateEnum.SUCCESS,product);
        }else {
            return new ProductExecution(ProductStateEnum.EMPTY_LIST);
        }

    }





    //批量处理商品详情图片，并操作tb_shop_img表
    private void addProductImgList(Product product, List<ImageHolder> imageHolderList) {
        //获取根路径
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        ProductImg img = new ProductImg();
        List<ProductImg> productImgList = new ArrayList<>();
        for(ImageHolder imageHolder:imageHolderList){
            String addr = ImgUtil.generateNormalImg(imageHolder,dest);
            img.setImgAddr(addr);
            img.setProductId(product.getProductId());
            img.setCreateTime(new Date());
            productImgList.add(img);
        }
        if(productImgList.size()>0){
            try {
                int effNum = productImgDao.batchInsertProductImg(productImgList);
                if(effNum<=0){
                    throw new ProductException("创建商品图片详情失败");
                }
            }catch (Exception e){
                throw new ProductException("创建商品详情图片失败："+e.getMessage());
            }
        }
    }

    //添加缩略图
    private void addThubmnail(Product product, ImageHolder thumbnail) {
        //获取基准路径
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        //生成缩略图并返回其相对路径
        String addr = ImgUtil.generateThumbnail(thumbnail,dest);
        product.setImgAddr(addr);
    }
}
