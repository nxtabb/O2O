package com.hrbeu.O2O.utils;

import java.io.File;

public class PathUtil {
    //获取待存储图片的位置
    public static String getImgBasePath(){

        String os = System.getProperty("os.name");
        String basePath = "";
        if(os.toLowerCase().startsWith("win")){
            basePath = "F:/images";
        }
        else {
            basePath = "/home/ningxitong/images";
        }
        basePath = basePath.replace("/",File.separator);
        return basePath;
    }

    public static String getShopImagePath(long shopId){
        String imagePath = "/upload/item/shop/"+shopId+"/";
        imagePath = imagePath.replace("/",File.separator);
        return imagePath;
    }
}
