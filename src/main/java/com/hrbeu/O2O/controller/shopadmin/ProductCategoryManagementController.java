package com.hrbeu.O2O.controller.shopadmin;

import com.hrbeu.O2O.Pojo.ProductCategory;
import com.hrbeu.O2O.Pojo.Shop;
import com.hrbeu.O2O.Pojo_sup.Result;
import com.hrbeu.O2O.enums.ProductCategoryStateEnum;
import com.hrbeu.O2O.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
}
