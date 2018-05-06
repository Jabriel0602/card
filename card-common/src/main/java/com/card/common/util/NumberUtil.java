package com.card.common.util;

import java.util.regex.Pattern;

public class NumberUtil {

    private static Pattern numberPattern = Pattern.compile("[0-9]*\\.*[0-9]*");

    public static boolean isNumeric(String str){
        if (null == str || "".equals(str.trim())) {
            return false;
        } else {
            return numberPattern.matcher(str).matches();
        }
    }
}
