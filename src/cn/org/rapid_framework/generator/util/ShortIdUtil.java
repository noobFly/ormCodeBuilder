package cn.org.rapid_framework.generator.util;

import java.util.Random;

public class ShortIdUtil {
    private static String a = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static int b = a.length();
    private static Random rd = new Random();
    
    public static String randomId() {
        return randomId(System.currentTimeMillis() + rd.nextInt(10000));
    }
    public static String randomId(long n) {
        StringBuilder sb = new StringBuilder();
        gr(n, sb);
        return sb.toString();
    }
    private static void gr(long n, StringBuilder sb) {
        if(n<=0) {
            return;
        }
        long c = n / b;
        gr(c, sb);
        sb.append(a.charAt((int)(n % b)));
    }
}
