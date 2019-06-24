package com.alice.springboot.util;

/**
 * 字符串工具类
 */
public class StringUtil {
    /**
     * 字符串首字母大写
     * @param string 字符串
     * @return 首字母大写的字符串
     */
    public static String getInitialsUp(String string)
    {
        if (CommonUtil.isEmpty(string))
        {
            return "";
        }
        byte[] items;
        try
        {
            items = string.getBytes("UTF-8");
            items[0] = (byte) ((char) items[0] - 'a' + 'A');
            return new String(items, "UTF-8");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return string;
    }

    /**
     * 字符串首字母小写
     * @param string 字符串
     * @return 首字母小写的字符串
     */
    public static String getInitialsDown(String string)
    {
        byte[] items;
        try
        {
            items = string.getBytes("UTF-8");
            items[0] = (byte) ((char) items[0] + 'a' - 'A');
            return new String(items, "UTF-8");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return string;
    }
}
