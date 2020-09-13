package com.hrbeu.O2O.controller.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrbeu.O2O.Pojo.Product;
import com.hrbeu.O2O.Pojo.ProductCategory;
import com.hrbeu.O2O.Pojo.ProductImg;
import com.hrbeu.O2O.Pojo.Shop;
import com.hrbeu.O2O.Pojo_sup.ImageHolder;
import com.hrbeu.O2O.Pojo_sup.ProductExecution;
import com.hrbeu.O2O.enums.ProductStateEnum;
import com.hrbeu.O2O.exceptions.ProductException;
import com.hrbeu.O2O.service.ProductCategoryService;
import com.hrbeu.O2O.service.ProductService;
import com.hrbeu.O2O.utils.CodeUtil;
import com.hrbeu.O2O.utils.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shopadmin")
public class ProductManageController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;
    private static final int IMAGEMAXCOUNT=6;

    @RequestMapping(value = "/addproduct",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addProduct(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        //检查验证码是否正确
        if(!CodeUtil.checkVeryifyCode(request)){
            modelMap.put("success",false);
            modelMap.put("errMsg","输入了错误的验证码");
            return modelMap;
        }
        ObjectMapper mapper = new ObjectMapper();
        Product product = null;
        String productStr = HttpServletRequestUtil.getString(request,"productStr");
        MultipartHttpServletRequest multipartRequest = null;
        ImageHolder thumbnail = null;
        List<ImageHolder> imageHolderList = new ArrayList<>();
        CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        try {
            if(resolver.isMultipart(request)){
                multipartRequest = (MultipartHttpServletRequest) request;
                CommonsMultipartFile thumbnailFile = (CommonsMultipartFile) multipartRequest.getFile("thumbnail");
                thumbnail = new ImageHolder(thumbnailFile.getOriginalFilename(),thumbnailFile.getInputStream());
                for(int i=0;i<IMAGEMAXCOUNT;i++){
                    CommonsMultipartFile productImageFile =(CommonsMultipartFile) multipartRequest.getFile("productImg"+i);
                    if(productImageFile!=null){
                        ImageHolder imageHolder = new ImageHolder(productImageFile.getOriginalFilename(),productImageFile.getInputStream());
                        imageHolderList.add(imageHolder);
                    }
                    else {
                        break;
                    }
                }
            }
            else {
                modelMap.put("success",false);
                modelMap.put("errMsg","上传图片不能为空");
                return modelMap;
            }
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }
        try {
            product = mapper.readValue(productStr,Product.class);
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }
        if(product!=null){
            try {
                Shop currentShop =(Shop)request.getSession().getAttribute("currentShop");
                product.setShop(currentShop);
                ProductExecution productExecution = productService.addProduct(product,thumbnail,imageHolderList);
                if(productExecution.getState()== ProductStateEnum.SUCCESS.getState()){
                    modelMap.put("success",true);
                }else {
                    modelMap.put("success",false);
                    modelMap.put("errMsg",productExecution.getStateInfo());
                }
            }catch (ProductException e){
                modelMap.put("success",false);
                modelMap.put("errMsg",e.getMessage());
                return modelMap;
            }
        }
        else {
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入商品信息");
            return modelMap;
        }
        return modelMap;

    }

    @RequestMapping(value = "/getproductbyid",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getProductById(@RequestParam Long productId){
        Map<String,Object> modelMap = new HashMap<>();
        if(productId>-1){
            Product product= productService.getProductById(productId);
            List<ProductCategory> productCategoryList = productCategoryService.getProductCategoryList(product.getShop().getShopId());
            modelMap.put("product",product);
            modelMap.put("productCategoryList",productCategoryList);
            modelMap.put("success",true);
        }
        else {
            modelMap.put("success",false);
            modelMap.put("errMsg","productId为空");
        }
        return modelMap;
    }

    @RequestMapping(value = "/modifyproduct",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> modifyProduct(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        boolean statusChange = HttpServletRequestUtil.getBoolean(request,"statusChange");
        if(!statusChange&&!CodeUtil.checkVeryifyCode(request)){
            modelMap.put("success",false);
            modelMap.put("errMsg","验证码错误");
            return modelMap;
        }
        ObjectMapper mapper = new ObjectMapper();
        Product product = null;
        ImageHolder thumbnail = null;
        List<ImageHolder> productImgList = new ArrayList<>();
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        try {
            if(multipartResolver.isMultipart(request)){
                MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
                CommonsMultipartFile thumbnailFile = (CommonsMultipartFile) multipartHttpServletRequest.getFile("thumbnail");
                if(thumbnailFile!=null){
                    thumbnail = new ImageHolder(thumbnailFile.getOriginalFilename(),thumbnailFile.getInputStream());
                }
                for (int i=0;i<IMAGEMAXCOUNT;i++){
                    CommonsMultipartFile productImgFile = (CommonsMultipartFile) multipartHttpServletRequest.getFile("productImg"+i);
                    if(productImgFile!=null){
                        ImageHolder imageHolder = new ImageHolder(productImgFile.getOriginalFilename(),productImgFile.getInputStream());
                        productImgList.add(imageHolder);

                    }else {
                        break;
                    }
                }
            }
        } catch (Exception e) {
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }
        try {
            String productStr = HttpServletRequestUtil.getString(request,"productStr");
            product = mapper.readValue(productStr,Product.class);

        }catch (Exception e){
            modelMap.put("success",true);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }
        if(product!=null){
            try {
                Shop current = (Shop) request.getSession().getAttribute("currentShop");
                product.setShop(current);
                ProductExecution productExecution = productService.modifyProduct(product,thumbnail,productImgList);
                if(productExecution.getState()==ProductStateEnum.SUCCESS.getState()){
                    modelMap.put("success",true);
                }
                else {
                    modelMap.put("success",false);
                    modelMap.put("errMsg",productExecution.getStateInfo());
                }
            }catch (ProductException e){
                modelMap.put("success",false);
                modelMap.put("errMsg",e.getMessage());
                return modelMap;

            }
        }
        return modelMap;
    }


    @RequestMapping(value = "/getproductlistbyshop",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getProductListShop(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        int pageIndex = HttpServletRequestUtil.getInt(request,"pageIndex");
        int pageSize = HttpServletRequestUtil.getInt(request,"pageSize");
        Shop currentShop = (Shop)request.getSession().getAttribute("currentShop");
        if(pageIndex>-1&&pageSize>-1&&(currentShop!=null)&&(currentShop.getShopId()!=null)){
            long productCategoryId = HttpServletRequestUtil.getLong(request,"productCategoryId");
            String productName = HttpServletRequestUtil.getString(request,"productName");
            Product productCondition = compactProductCondition(currentShop.getShopId(),productCategoryId,productName);
            ProductExecution productExecution = productService.getProductList(productCondition,pageIndex,pageSize);
            modelMap.put("success",true);
            modelMap.put("productList",productExecution.getProductList());
            modelMap.put("count",productExecution.getCount());
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","商店id为空");
        }
        return modelMap;
    }

    private Product compactProductCondition(Long shopId, Long productCategoryId, String productName) {
        Product product = new Product();
        Shop shop = new Shop();
        shop.setShopId(shopId);
        product.setProductName(productName);
        product.setShop(shop);
        if(productCategoryId!=-1L){
            ProductCategory productCategory = new ProductCategory();
            productCategory.setProductCategoryId(productCategoryId);
            product.setProductCategory(productCategory);
        }
        return product;
    }
}
