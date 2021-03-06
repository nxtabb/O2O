package com.hrbeu.O2O.dao;

import com.hrbeu.O2O.Pojo.ProductImg;

import java.util.List;

public interface ProductImgDao {
    int batchInsertProductImg(List<ProductImg> productImgList);
    int deleteProductImgByProductId(long product_id);
    List<ProductImg> queryProductImgList(Long productId);
}
