package com.zxf.util;

/**
 * @author zhuxiangfei
 * @Description:
 * @date 2018/4/11
 */
public class StringUtils {

    public static final String CHINESE = "[\u0391-\uFFE5]";

    /**
     * 获取字符串的长度，如果有中文，则每个中文字符计为2位
     *
     * @param validateStr
     *            指定的字符串
     * @return 字符串的长度
     */
    public static int getChineseLength(String validateStr) {
        int valueLength = 0;
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        for (int i = 0; i < validateStr.length(); i++) {
            /* 获取一个字符 */
            String temp = validateStr.substring(i, i + 1);
            /* 判断是否为中文字符 */
            if (temp.matches(CHINESE)) {
                /* 中文字符长度为2 */
                valueLength += 2;
            } else {
                /* 其他字符长度为1 */
                valueLength += 1;
            }
        }
        return valueLength;
    }

    public static boolean isChineseOneWord(String validateStr){
        return validateStr.matches(CHINESE);
    }

    public static String nSpace(int n){
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < n; i++){
            s.append(" ");
        }
        return s.toString();
    }

    public static String nLetter(int n, char c){
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < n; i++){
            s.append(c);
        }
        return s.toString();
    }


}
