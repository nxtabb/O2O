package com.hrbeu.O2O.utils;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.servlet.http.HttpServletRequest;

public class HttpServletRequestUtil {
    public static int getInt(HttpServletRequest request,String key){
        try {
            return Integer.parseInt(request.getParameter(key));
        }catch (Exception e){
            return -1;
        }
    }

    public static long getLong(HttpServletRequest request,String key){
        try {
            return Long.parseLong(request.getParameter(key));
        }catch (Exception e){
            return -1L;
        }
    }

    public static double getDouble(HttpServletRequest request,String key){
        try {
            return Double.parseDouble(request.getParameter(key));
        }catch (Exception e){
            return -1d;
        }
    }

    public static boolean getBoolean(HttpServletRequest request,String key){
        try {
            return Boolean.parseBoolean(request.getParameter(key));
        }catch (Exception e){
            return false;
        }
    }

    public static String getString(HttpServletRequest request,String key){
        try {
            String result = request.getParameter(key);
            if(result!=null&&!result.equals("")){
                result = result.trim();
            }
            else {
                result=null;
            }
            return result;
        }
        catch (Exception e){
            return null;
        }
    }
}
