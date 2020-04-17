package com.whu.bookstore_server.utils;

import java.math.BigDecimal;

public class Util {

    // 格式化double
    public static double round(double value, int scale, int roundingMode) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(scale, roundingMode);
        double d = bd.doubleValue();
        bd = null;
        return d;
    }
}
