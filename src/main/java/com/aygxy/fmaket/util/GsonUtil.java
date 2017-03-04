package com.aygxy.fmaket.util;

import java.lang.reflect.Type;

import com.aygxy.fmaket.debug.DebugLog;
import com.google.gson.Gson;

/**
 * Created by YH on 2016/12/23.
 */

public class GsonUtil {
    private static Gson gson;

    static {
        gson = new Gson();
    }

    public static String objectToString(Object obj){
        String str = null;
        try{
            str = gson.toJson(obj);
        }catch (Exception e){
            DebugLog.logger.error("生成json字符串时出错", e);
        }
        return str;
    }


    /**
     * 根据javaBean类型将字符串转换为对象，适用于一个bean对象
     * @param str
     * @param clazz
     * @return
     */
    public static <T> T stringToObjectByBean(String str,Class<T> clazz){
        Object obj = null;
        try{
            obj = gson.fromJson(str, clazz);
        }catch (Exception e){
        	 DebugLog.logger.error("stringToObjectByBean",e);
        }
        return  (T) obj;
    }



    /**
     * 集合json转换
     * @param str
     * @param type new TypeToken<Collection<Foo>>(){}.getType();
     * @return
     */
    public static Object stringToObjectByType(String str,Type type){
        Object obj = null;
        try{
            obj = gson.fromJson(str, type);
        }catch (Exception e){
        	 DebugLog.logger.error("stringToObjectByType",e);
        }
        return obj;
    }

}
