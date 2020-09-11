package com.hrbeu.O2O.utils;

import com.google.code.kaptcha.Constants;

import javax.servlet.http.HttpServletRequest;

public class CodeUtil {

    public static boolean checkVeryifyCode(HttpServletRequest request){
        String verifyCodeExpected = (String)request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        String verifyCodeInput = HttpServletRequestUtil.getString(request,"verifyCodeActual");
        if(verifyCodeExpected.equals(verifyCodeInput)||verifyCodeExpected.equals(verifyCodeInput.toUpperCase())){
            return true;
        }
        else {
            return false;
        }
    }
}
