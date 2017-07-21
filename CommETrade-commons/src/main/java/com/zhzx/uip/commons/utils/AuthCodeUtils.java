package com.zhzx.uip.commons.utils;

import java.util.Random;

/**
 * Created by Administrator on 2017/7/20.
 */
public class AuthCodeUtils {
    // 获得随机验证码
    public static String getCode() {
        Random random = new Random();
        StringBuffer sj = new StringBuffer();
        while (sj.length() < 6) {
            // 将符合ASCII码的数值转为char值,并追加到字符串中
            sj.append((char) getEnableLetter(random));
        }
        return sj.toString();
    }

    /**
     * 获取可用随机字母及数字
     * @param random
     * @return
     */
    private static int getEnableLetter(Random random) {
        int num = random.nextInt(123);
        while (isUnEnableLetter(num)) {
            num = random.nextInt(123);
        }
        return num;
    }

    /**
     * 是否是可用字母
     * @param num
     * @return
     */
    private static boolean isUnEnableLetter(int num) {
        return !isEnableLetter(num);
    }

    /**
     * 是否是可用字母
     * @param num
     * @return
     */
    private static boolean isEnableLetter(int num) {
        return isNum(num);
    }

    /**
     * 是否是数字
     * @param num
     * @return
     */
    private static boolean isNum(int num) {
        return (num >= 48 && num <= 57);
    }

    /**
     * 是否是字母 大写和小写
     * @param num
     * @return
     */
    private static boolean isLetter(int num) {
        return isCapitialLetter(num) || isLowerCase(num);
    }

    private static boolean isCapitialLetter(int num) {
        return (num >= 65 && num <= 90);
    }

    private static boolean isLowerCase(int num) {
        return (num >= 97 && num <= 122);
    }

}
