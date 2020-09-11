package com.hrbeu.O2O.controller.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrbeu.O2O.Pojo.Product;
import com.hrbeu.O2O.Pojo.Shop;
import com.hrbeu.O2O.Pojo_sup.ImageHolder;
import com.hrbeu.O2O.Pojo_sup.ProductExecution;
import com.hrbeu.O2O.enums.ProductStateEnum;
import com.hrbeu.O2O.exceptions.ProductException;
import com.hrbeu.O2O.service.ProductService;
import com.hrbeu.O2O.utils.CodeUtil;
import com.hrbeu.O2O.utils.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shopadmin")
public class ProductManageController {
    @Autowired
    private ProductService productService;
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


}
