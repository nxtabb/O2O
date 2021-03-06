package com.hrbeu.O2O.controller.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/shopadmin")
public class ShopAdminController {

    @RequestMapping(value = "/shopoperation")
    public String shopOperation(){
        return "shop/shopoperation";

    }
    @RequestMapping(value = "/shoplist",method = RequestMethod.GET)
    public String shopList(){
        return "/shop/shoplist";
    }


    @RequestMapping(value = "/shopmanagement",method = RequestMethod.GET)
    public String shopManagement(){
        return "/shop/shopmanagement";
    }


    @RequestMapping(value = "/productcategorymanagement")
    public String productCategoryManagement(){
        return "/shop/productcategorymanagement";
    }

    @RequestMapping(value = "/productoperation")
    public String productOperation(){
        return "/product/productoperation";
    }


    @RequestMapping(value = "/productmanagement",method = RequestMethod.GET)
    public String productManagement(){
        return "/product/productmanagement";
    }
}
