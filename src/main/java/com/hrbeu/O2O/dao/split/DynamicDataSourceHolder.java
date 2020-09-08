package com.hrbeu.O2O.dao.split;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class DynamicDataSourceHolder {
    private static Logger logger = LoggerFactory.getLogger(DynamicDataSourceHolder.class);
    private static ThreadLocal<String> contextHolder = new ThreadLocal<>();
    public static final String DB_master ="master";
    public static final String Db_slave="slave";
    //获取线程dbtype
    public static String getDbType() {
        String db = contextHolder.get();
        if(db==null){
            db= DB_master;
        }
        return db;
    }
    //设置线程dbtype
    public static void setDbType(String str) {
        logger.debug("所使用的数据源是"+str);
        contextHolder.set(str);
    }
    //清楚连接类型
    public static void clearDbType(){
        contextHolder.remove();
    }
}
