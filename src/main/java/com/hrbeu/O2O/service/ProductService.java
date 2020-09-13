package com.hrbeu.O2O.service;

import com.hrbeu.O2O.Pojo.Product;
import com.hrbeu.O2O.Pojo_sup.ImageHolder;
import com.hrbeu.O2O.Pojo_sup.ProductExecution;
import com.hrbeu.O2O.exceptions.ProductCategoryException;
import com.hrbeu.O2O.exceptions.ProductException;

import java.io.InputStream;
import java.util.List;

public interface ProductService {
    //1.对数据库进行操作，2.处理缩略图，3、处理商品详情图片
    /*
    thumbnail:包含缩略图信息：imageName+InputStream
    imageHolderList包含商品详情信息
     */
    Product getProductById(long productId);
    ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> imageHolderList) throws ProductException;
    ProductExecution modifyProduct(Product product,ImageHolder thumbnail,List<ImageHolder> imageHolderList) throws ProductException;
    ProductExecution getProductList(Product product,int pageIndex,int pageSize);
}
