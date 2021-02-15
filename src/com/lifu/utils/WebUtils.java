package com.lifu.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

/**
 * @Description
 * @Author lifu
 * @Date 2021/1/29 12:47
 */
public class WebUtils {
    /**
     * 把请求中的参数的map注入到javabean
     * @param value
     * @param bean
     * @param <T>
     * @return
     */
    public static <T> T copyParamToBean(Map value, T bean){
        try {
            BeanUtils.populate(bean,value);
            System.out.println("注入之后：" + bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }
    public static int parseInt(String strInt,int defaultValue){
        try {
            return Integer.parseInt(strInt);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return defaultValue;
    }
}
