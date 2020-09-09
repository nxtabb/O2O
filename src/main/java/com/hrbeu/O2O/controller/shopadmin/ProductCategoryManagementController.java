package com.hrbeu.O2O.controller.shopadmin;

import com.hrbeu.O2O.Pojo.ProductCategory;
import com.hrbeu.O2O.Pojo.Shop;
import com.hrbeu.O2O.Pojo_sup.ProductCategoryExecution;
import com.hrbeu.O2O.Pojo_sup.Result;
import com.hrbeu.O2O.enums.ProductCategoryStateEnum;
import com.hrbeu.O2O.exceptions.ProductCategoryException;
import com.hrbeu.O2O.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/shopadmin")
public class ProductCategoryManagementController {
    @Autowired
    private ProductCategoryService productCategoryService;


    @RequestMapping(value = "/getproductcategorylist",method = RequestMethod.GET)
    @ResponseBody
    //定义了一个Result的数据类型用以接收返回信息
    private Result<List<ProductCategory>> getProductCategoryList(HttpServletRequest request){
        //得到当前的shop
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        List<ProductCategory> productCategoryList= productCategoryService.getProductCategoryList(currentShop.getShopId());
        //如果查询成功
        if(productCategoryList!=null&&productCategoryList.size()>0){
            return new Result<List<ProductCategory>>(true,productCategoryList);
        }else {
            //查询失败,返回错误信息
            String errMsg = ProductCategoryStateEnum.INNER_ERROR.getStateInfo();
            Integer errCode = ProductCategoryStateEnum.INNER_ERROR.getState();
            return new Result<>(false, errMsg,errCode);
        }

    }

    @RequestMapping(value = "/addproductcategorys",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addProductCategorys(@RequestBody List<ProductCategory> productCategoryList,HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        for(ProductCategory productCategory:productCategoryList){
            productCategory.setShopId(currentShop.getShopId());
        }
        if(productCategoryList!=null&&productCategoryList.size()>0){
            try {
                ProductCategoryExecution productCategoryExecution = productCategoryService.batchInsertProductCategory(productCategoryList);
                if(productCategoryExecution.getState()==ProductCategoryStateEnum.SUCCESS.getState()){
                    modelMap.put("success",true);
                }
                else {
                    modelMap.put("success",false);
                    modelMap.put("errMsg",productCategoryExecution.getStateInfo());
                }
            }catch (ProductCategoryException e){
                modelMap.put("success",false);
                modelMap.put("errMsg",e.getMessage());
                return modelMap;
            }
        }
        else {
            modelMap.put("success",false);
            modelMap.put("errMsg","请至少输入一个商品类别");

        }
        return modelMap;
    }

    @RequestMapping(value = "/removeproductcategory",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> removeProductCategory(Long productCategoryId,HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        if(productCategoryId!=null&&productCategoryId>0){
            try {
                Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
                ProductCategoryExecution productCategoryExecution = productCategoryService.deleteProductCategory(productCategoryId,currentShop.getShopId());
                if(productCategoryExecution.getState()==ProductCategoryStateEnum.SUCCESS.getState()){
                    modelMap.put("success",true);
                }
                else {
                    modelMap.put("success",false);
                    modelMap.put("errMsg",productCategoryExecution.getStateInfo());
                }
            }catch (ProductCategoryException e){
                modelMap.put("success",false);
                modelMap.put("errMsg",e.getMessage());
                return modelMap;
            }


        }
        else {
            modelMap.put("success",false);
            modelMap.put("errMsg","请选择商品类别");
        }
        return modelMap;
    }
}
