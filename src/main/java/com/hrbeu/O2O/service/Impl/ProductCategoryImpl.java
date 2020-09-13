package com.hrbeu.O2O.service.Impl;

import com.hrbeu.O2O.Pojo.ProductCategory;
import com.hrbeu.O2O.Pojo_sup.ProductCategoryExecution;
import com.hrbeu.O2O.dao.ProductCategoryDao;
import com.hrbeu.O2O.dao.ProductDao;
import com.hrbeu.O2O.enums.ProductCategoryStateEnum;
import com.hrbeu.O2O.exceptions.ProductCategoryException;
import com.hrbeu.O2O.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductCategoryImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryDao productCategoryDao;
    @Autowired
    private ProductDao productDao;
    @Override
    public List<ProductCategory> getProductCategoryList(Long shopId) {
        return productCategoryDao.queryProductList(shopId);
    }

    @Override
    public ProductCategoryExecution batchInsertProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryException {
        if(productCategoryList!=null&&productCategoryList.size()>0){
            try {
                int effNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
                if(effNum<=0){
                    throw new ProductCategoryException(ProductCategoryStateEnum.INNER_ERROR.getStateInfo());
                }else {
                    return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS,productCategoryList);
                }
            }catch (Exception e){
                throw new ProductCategoryException(ProductCategoryStateEnum.INNER_ERROR.getStateInfo());
            }
        }
        else {
            return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
        }
    }

    @Override
    //@Transactional
    public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId) throws ProductCategoryException {
        //todo:将此商品下的商品id置为空
        try {
            int effNum1 = productDao.updateProductCategoryToNull(productCategoryId);
            if(effNum1<0){
                throw new ProductCategoryException("商品类别更新失败");
            }
        }
        catch (Exception e) {
            throw new ProductCategoryException("删除商品失败" + e.getMessage());
        }
        try {
            int effNum = productCategoryDao.deleteProductCategory(productCategoryId,shopId);
            if(effNum<=0){
                throw new ProductCategoryException("商品类别删除失败");
            }
            else {
                return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
            }
        }catch (Exception e){
            throw new ProductCategoryException(e.getMessage());
        }
    }
}
