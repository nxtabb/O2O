package com.hrbeu.O2O.controller.frontend;

import com.hrbeu.O2O.Pojo.Area;
import com.hrbeu.O2O.Pojo.Shop;
import com.hrbeu.O2O.Pojo.ShopCategory;
import com.hrbeu.O2O.Pojo_sup.ShopExecution;
import com.hrbeu.O2O.service.AreaService;
import com.hrbeu.O2O.service.ShopCategoryService;
import com.hrbeu.O2O.service.ShopService;
import com.hrbeu.O2O.utils.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/frontend")
public class ShopListController {
    @Autowired
    AreaService areaService;
    @Autowired
    ShopCategoryService shopCategoryService;
    @Autowired
    ShopService shopService;

    @RequestMapping(value = "/listshoppageinfo",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> listShopPageInfo(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        long parentId = HttpServletRequestUtil.getLong(request,"parentId");
        List<ShopCategory> shopCategoryList = null;
        if(parentId!=-1){
            try {
                ShopCategory shopCategory = new ShopCategory();
                ShopCategory parent = new ShopCategory();
                parent.setShopCategoryId(parentId);
                shopCategory.setParent(parent);
                shopCategoryList = shopCategoryService.getShopCategoryList(shopCategory);
            }catch (Exception e){
                modelMap.put("success",true);
                modelMap.put("errMsg",e.getMessage());
                return modelMap;
            }

        }else {
            try {
                shopCategoryList = shopCategoryService.getShopCategoryList(null);

            }catch (Exception e){
                modelMap.put("success",true);
                modelMap.put("errMsg",e.getMessage());
                return modelMap;
            }
        }
        modelMap.put("shopCategoryList",shopCategoryList);
        List<Area> areaList = null;
        try {
            areaList = areaService.getAreaList();
            modelMap.put("areaList",areaList);
            modelMap.put("success",true);
            return modelMap;
        }catch (Exception e){
            modelMap.put("success",true);
            modelMap.put("errMsg",e.getMessage());
        }
        return modelMap;
    }


    @RequestMapping(value = "/listshops",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> listShops(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        int pageIndex = HttpServletRequestUtil.getInt(request,"pageIndex");
        int pageSize = HttpServletRequestUtil.getInt(request,"pageSize");
        if((pageIndex>-1)&&(pageSize>-1)){
            long parentId = HttpServletRequestUtil.getLong(request,"parentId");
            long shopCategoryId = HttpServletRequestUtil.getLong(request,"shopCategoryId");
            int areaId = HttpServletRequestUtil.getInt(request,"areaId");
            String shopName = HttpServletRequestUtil.getString(request,"shopName");
            Shop shop =compactShopCondition4Search(parentId,shopCategoryId,areaId,shopName);
            ShopExecution shopExecution = shopService.getShopList(shop,pageIndex,pageSize);
            modelMap.put("shopList",shopExecution.getShopList());
            modelMap.put("count",shopExecution.getCount());
            modelMap.put("success",true);
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","页码传入出现问题");

        }
        return modelMap;
    }




    private Shop compactShopCondition4Search(long parentId, long shopCategoryId, int areaId, String shopName) {
        Shop shop = new Shop();
        if(parentId!=-1){
            ShopCategory childCategory = new ShopCategory();
            ShopCategory parentCategory = new ShopCategory();
            parentCategory.setShopCategoryId(parentId);
            childCategory.setParent(parentCategory);
            shop.setShopCategory(childCategory);
        }
        if(shopCategoryId!=-1) {
            ShopCategory shopCategory = new ShopCategory();
            shopCategory.setShopCategoryId(shopCategoryId);
            shop.setShopCategory(shopCategory);
        }
        if(areaId!=-1){
            Area area = new Area();
            area.setAreaId(areaId);
            shop.setArea(area);
        }
        if(shopName!=null){
            shop.setShopName(shopName);
        }
        shop.setEnableStatus(1);
        return shop;
    }


}
