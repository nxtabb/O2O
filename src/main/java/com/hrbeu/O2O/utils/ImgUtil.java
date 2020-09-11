package com.hrbeu.O2O.utils;

import com.hrbeu.O2O.Pojo_sup.ImageHolder;
import com.hrbeu.O2O.controller.superadminController.AreaController;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ImgUtil {
    static Logger logger = LoggerFactory.getLogger(AreaController.class);
    //格式化时间
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
    //创建一个Random对象
    private static final Random rd = new Random();
    //图片存储功能

    /**
     *
     * @param thumbnail  图片对象
     * @param targetAddr 存储的目标地址
     * @return 相对路径
     */
    public static String generateThumbnail(ImageHolder thumbnail,String targetAddr){
        //获取上传文件名字：时间+五位随机数
        String realFileName = getRandomFileName();
        //获取文件扩展名
        String extension = getFileExtension(thumbnail.getImageName());
        //创建上传文件的目标文件夹
        mkDirPath(targetAddr);
        //获取到图片的位置+名字的相对路径
        String relativeAddr = targetAddr+realFileName+extension;
        logger.debug("current relativeAddr is: "+relativeAddr);
        //获取到图片位置的绝对路径
        File dest = new File(PathUtil.getImgBasePath()+relativeAddr);
        logger.debug("current complete addr is: "+PathUtil.getImgBasePath()+relativeAddr);
        try {
            //对图片进行打水印和压缩存储
            Thumbnails.of(thumbnail.getImage()).size(200,200).outputQuality(0.8f).toFile(dest);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return relativeAddr;
    }


    /**
     *
     * @param thumbnail  图片对象
     * @param targetAddr 存储的目标地址
     * @return 相对路径
     */
    public static String generateNormalImg(ImageHolder thumbnail,String targetAddr){
        //获取上传文件名字：时间+五位随机数
        String realFileName = getRandomFileName();
        //获取文件扩展名
        String extension = getFileExtension(thumbnail.getImageName());
        //创建上传文件的目标文件夹
        mkDirPath(targetAddr);
        //获取到图片的位置+名字的相对路径
        String relativeAddr = targetAddr+realFileName+extension;
        logger.debug("current relativeAddr is: "+relativeAddr);
        //获取到图片位置的绝对路径
        File dest = new File(PathUtil.getImgBasePath()+relativeAddr);
        logger.debug("current complete addr is: "+PathUtil.getImgBasePath()+relativeAddr);
        try {
            //对图片进行打水印和压缩存储
            Thumbnails.of(thumbnail.getImage()).size(337,640).outputQuality(0.9f).toFile(dest);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return relativeAddr;
    }




    //创建目标路径所涉及的目录
    private static void mkDirPath(String targetAddr) {
        //获取要创建文件夹的绝对路径
        String realFilePath = PathUtil.getImgBasePath()+targetAddr;
        //获取该路径的文件对象
        File dirPath = new File(realFilePath);
        //如果不存在
        if(!dirPath.exists()){
            //递归创建
            dirPath.mkdirs();
        }
    }

    //获取输入文件流的扩展名
    private static String getFileExtension(String fileName) {
        //根据最后一个.截取文件名字得到扩展名
        return fileName.substring(fileName.lastIndexOf("."));
    }

    //生成年月日小时分钟秒中+五位随机数
    public static String getRandomFileName() {
        //大于10000小于99999;
        int randomNum = rd.nextInt(89999)+10000;
        String nowTimeStr = format.format(new Date());
        return nowTimeStr+randomNum;
    }

    public static void delFileOrPath(String storePath){
        //判断storePath是文件路径还是文件夹路径，如果是文件路径，则删除该文件，如果是文件夹，则删除下面的所有文件
        File fileOrPath = new File(PathUtil.getImgBasePath()+storePath);
        //判断是否存在
        if(fileOrPath.exists()){
            //判断是否是文件夹
            if(fileOrPath.isDirectory()){
                //获取文件夹下的所有文件
                File[] files = fileOrPath.listFiles();
                //遍历删除
                for(File f:files){
                    f.delete();
                }
            }
            //最后删除该文件夹或文件。
            fileOrPath.delete();
        }

    }
}
