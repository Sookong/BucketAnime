package com.tanwuyu.ivrtym.bucketanime.utils;

/**
 * Created by ivrty on 2017/4/27.
 */

public class StringUtil {
    public static Boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }

    /**
     *
     * @param num
     * @return 中文数学结果: example: 九百九十九
     */
    public static String numToExactChineseMathCharacter(int num){
        return String.valueOf(num);
    }

    /**
     *
     * @param num
     * @return 中文近似数学结果: example: 10万
     */
    public static String numToSimilarChineseMathUnitCharacter(int num){
        return String.valueOf(num);
    }
    /**
     *
     * @param num
     * @return 中文近似数学结果: example: 十万
     */
    public static String numToSimilarChineseMathCharacter(int num){
        return String.valueOf(num);
    }
}
