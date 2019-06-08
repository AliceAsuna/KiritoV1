package com.alice.springboot.util;

import org.apache.poi.ss.formula.functions.T;

import java.util.List;
import java.util.Map;

/**
 * 通用工具类
 */
public class CommonUtil {
    /**
     * 判断对象是不是null
     * @param object 对象
     * @return 是否为空
     */
    public static <T> boolean isEmpty(T object)
    {
        if (null == object)
        {
            return true;
        }
        return false;
    }

    /**
     * 判断对象是不是null
     * @param list list
     * @return 是否为空
     */
    public static <T> boolean isEmpty(List<T> list)
    {
        if (null == list || list.isEmpty())
        {
            return true;
        }
        return false;
    }

    /**
     * 判断对象是不是null
     * @param map map
     * @return 是否为空
     */
    public static boolean isEmpty(Map<Object, Object> map)
    {
        if (null == map || map.isEmpty())
        {
            return true;
        }
        return false;
    }

    /**
     * 判断对象是不是null
     * @param array 数组
     * @return 是否为空
     */
    public static boolean isEmpty(Object[] array)
    {
        if (null == array || array.length == 0)
        {
            return true;
        }
        return false;
    }

}
