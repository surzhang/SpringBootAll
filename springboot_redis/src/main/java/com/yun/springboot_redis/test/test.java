package com.yun.springboot_redis.test;

import java.util.Scanner;

public class test {
   
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int len = str.length();
        //用来检测字母的正则表达式
        String reg1 = "[a-zA-Z]";
        int count1 = 0;
        //用于统计数字个数
        String reg2 = "[0-9]";
        int count2 = 0;
        //获得的键盘输入都是String,转换为字符数组
        char[] charArr = str.toCharArray();
        String[] strArr = new String[charArr.length];
        for (int i = 0; i < charArr.length; i++) {
            strArr[i] = String.valueOf(charArr[i]);
            if (strArr[i].matches(reg1)) {
                count1++;
            }
            if (strArr[i].matches(reg2)) {
                count2++;
            }
        }
        //其他字符
        int other = len - count1 - count2;
        System.out.println(count1 + "," + count2 + "," + other);
    }

}



