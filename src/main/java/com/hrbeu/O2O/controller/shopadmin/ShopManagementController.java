package com.hrbeu.O2O.controller.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrbeu.O2O.Pojo.Area;
import com.hrbeu.O2O.Pojo.PersonInfo;
import com.hrbeu.O2O.Pojo.Shop;
import com.hrbeu.O2O.Pojo.ShopCategory;
import com.hrbeu.O2O.Pojo_sup.ImageHolder;
import com.hrbeu.O2O.Pojo_sup.ShopExecution;
import com.hrbeu.O2O.enums.ShopStateEnum;
import com.hrbeu.O2O.service.AreaService;
import com.hrbeu.O2O.service.ShopCategoryService;
import com.hrbeu.O2O.service.ShopService;
import com.hrbeu.O2O.utils.CodeUtil;
import com.hrbeu.O2O.utils.HttpServletRequestUtil;
import com.hrbeu.O2O.utils.ImgUtil;
import com.hrbeu.O2O.utils.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private AreaService areaService;


    @RequestMapping(value = "/getshopinitinfo",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getShopInitInfo(){
        Map<String,Object> modelMap = new HashMap<String, Object>();

        List<ShopCategory> shopCategoryList = new ArrayList<>();
        List<Area> areaList = new ArrayList<>();
        try{
            shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
            areaList = areaService.getAreaList();
            modelMap.put("shopCategoryList",shopCategoryList);
            modelMap.put("areaList",areaList);
            modelMap.put("success",true);
        }
        catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
        }
        return modelMap;

    }


    @RequestMapping(value = "/getshopbyid",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getShopInfo(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        long shopId = HttpServletRequestUtil.getLong(request,"shopId");
        if(shopId>-1){
            try {
                Shop shop= shopService.getByShopId(shopId);
                List<Area> areaList = areaService.getAreaList();
                modelMap.put("shop",shop);
                modelMap.put("areaList",areaList);
                modelMap.put("success",true);
            }
            catch (Exception e){
                modelMap.put("success",false);
                modelMap.put("errMsg",e.getMessage());
            }
        }
        else {
            modelMap.put("success",false);
            modelMap.put("errMsg","shopId不正确");
        }
        return modelMap;
    }



    @RequestMapping(value = "/registershop",method = RequestMethod.POST)
    @ResponseBody
    /**
     *  新增店铺
     *  返回json格式的数据 表示是否成功 或者错误信息
     */
    private Map<String,Object> registerShop(HttpServletRequest request) {
        //接收并转化参数，包括店铺信息和图片信息
        //创建一个map，用来存储返回信息
        Map<String, Object> modelMap = new HashMap<String, Object>();
        if (!CodeUtil.checkVeryifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入了错误的验证码");
            return modelMap;
        }
        //获取前端传过来的shop信息
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        //使用jackson包将字符串转化为Pojo类
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try {
            //开始转化
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (Exception e) {
            //转化失败
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }
        //接受图片
        //使用spring自带的文件上传处理
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver resolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        if (resolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "上传文件不能为空");
            return modelMap;
        }
        //注册店铺
        if (shop != null && shopImg != null) {
            PersonInfo owner = (PersonInfo)request.getSession().getAttribute("user");
            //后面需要使用session获取用户得到owner
            shop.setOwner(owner);
            ShopExecution se = null;
            try {
                ImageHolder imageHolder = new ImageHolder(shopImg.getOriginalFilename(),shopImg.getInputStream());
                //将shop对象，shopImg的文件输入流，以及图片的名称传入addshop方法中，进行数据库和图片的存储。
                se = shopService.addShop(shop, imageHolder);
                List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopList");
                if(shopList==null||shopList.size()==0){
                    shopList = new ArrayList<Shop>();
                }
                shopList.add(se.getShop());
                request.getSession().setAttribute("shopList",shopList);
                if (se.getState() == ShopStateEnum.CHECK.getState()) {
                    //成功
                    modelMap.put("success", true);
                    return modelMap;
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", se.getStateInfo());
                }
            } catch (IOException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", "出错了，错误信息是：" + se.getStateInfo());
            }
            return modelMap;
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "店铺信息输入有误");
            return modelMap;
        }
    }



    @RequestMapping(value = "/modifyshop",method = RequestMethod.POST)
    @ResponseBody
    /**
     *  修改店铺
     *  返回json格式的数据 表示是否成功 或者错误信息
     */
    private Map<String,Object> modifyShop(HttpServletRequest request){
        //接收并转化参数，包括店铺信息和图片信息
        //创建一个map，用来存储返回信息
        Map<String,Object> modelMap = new HashMap<String,Object>();
        if(!CodeUtil.checkVeryifyCode(request)){
            modelMap.put("success",false);
            modelMap.put("errMsg","输入了错误的验证码");
            return modelMap;
        }
        //获取前端传过来的shop信息
        String shopStr = HttpServletRequestUtil.getString(request,"shopStr");
        //使用jackson包将字符串转化为Pojo类
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try {
            //开始转化
            shop = mapper.readValue(shopStr,Shop.class);
        }
        catch (Exception e){
            //转化失败
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }
        //接受图片
        //使用spring自带的文件上传处理
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver resolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        if(resolver.isMultipart(request)){
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        }
        //修改店铺
        if(shop!=null&&shop.getShopId()!=null){
            ShopExecution se = null;
            try {
                //将shop对象，shopImg的文件输入流，以及图片的名称传入addshop方法中，进行数据库和图片的存储。
                if(shopImg==null){
                    se = shopService.modifyShop(shop,null);
                }
                else {
                    ImageHolder imageHolder = new ImageHolder(shopImg.getOriginalFilename(),shopImg.getInputStream());

                    se = shopService.modifyShop(shop,imageHolder);
                }
                if(se.getState()== ShopStateEnum.SUCCESS.getState()){
                    //成功
                    modelMap.put("success",true);
                    return modelMap;
                }
                else {
                    modelMap.put("success",false);
                    modelMap.put("errMsg",se.getStateInfo());
                }
            } catch (IOException e) {
                modelMap.put("success",false);
                modelMap.put("errMsg","出错了，错误信息是："+se.getStateInfo());
            }
            return modelMap;
        } else {
            modelMap.put("success",false);
            modelMap.put("errMsg","请输入店铺ID");
            return modelMap;
        }

    }
    //根据用户信息返回店铺的列表
    @RequestMapping(value = "/getshoplist",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getShopList(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        PersonInfo user = new PersonInfo();
        user.setUserId(1L);
        user.setName("test");
        request.getSession().setAttribute("user",user);
        user = (PersonInfo) request.getSession().getAttribute("user");
        try {
            Shop shop = new Shop();
            shop.setOwner(user);
            ShopExecution shopExecution = shopService.getShopList(shop,0,100);
            modelMap.put("success",true);
            modelMap.put("shopList",shopExecution.getShopList());
            modelMap.put("user",user);
            return modelMap;
        }catch (Exception e){
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }
    }


    //通过session判断是否登录，并且进入相应的店铺管理界面
    @RequestMapping(value = "/getshopmanagementinfo",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getShopManagementInfo(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        long shopId = HttpServletRequestUtil.getLong(request,"shopId");
        if(shopId<=0){
            Object currentShopObj = request.getSession().getAttribute("currentShop");
            if(currentShopObj==null){
                modelMap.put("redirect",true);
                modelMap.put("url","/O2O/shopadmin/shoplist");
            }
            else {
                Shop currentShop = (Shop)currentShopObj;
                modelMap.put("redirect",false);
                modelMap.put("shopId",currentShop.getShopId());
            }
        }
        else {
            Shop shop = new Shop();
            shop.setShopId(shopId);
            request.getSession().setAttribute("currentShop",shop);
            modelMap.put("redirect",false);
        }
        return modelMap;
    }



//将输入流转化为File类型
//    private static void inputStreamToFile(InputStream ins,File file){
//        OutputStream os =null;
//        try {
//            os = new FileOutputStream(file);
//            int bytesRead = 0;
//            byte[] bytes = new byte[1024];
//            while ((bytesRead = ins.read(bytes)) != -1) {
//                os.write(bytes, 0, bytesRead);
//            }
//        }
//        catch (Exception e) {
//            throw new RuntimeException("调用inputStreamToFile错误，原因："+e.getMessage());
//        }
//        finally {
//            if(os!=null){
//                try {
//                    os.close();
//                } catch (IOException e) {
//                    throw new RuntimeException("关闭输出流异常，原因："+e.getMessage());
//                }
//            }
//            if(ins!=null){
//                try {
//                    ins.close();
//                } catch (IOException e) {
//                    throw new RuntimeException("关闭输入流异常，原因："+e.getMessage());
//                }
//            }
//        }
//    }
}
