package com.greedy;

import java.util.Arrays;
import java.util.Comparator;

/*
 * 给你一串字符串数组，打印这串数组可以组成的最小字典序的字符串
 * 思路:
 * 字符串str1和str2的顺序应该是:
 * 如果str1·str2 < str2·str1 那么str1应该排在str2前面
 * 如果str1·str2 > str1·str2 那么str2应该排在str1前面
 * "·"表示字符串拼接
 */
public class LowestLexicographer {
  public static class MyComparator implements Comparator<String> {
    @Override
    public int compare(String a, String b) {
      String ab = a + b;
      String ba = b + a;
      return ab.compareTo(ba);
    }
  }

  public static String lowString(String[] strs) {
    Arrays.sort(strs, new MyComparator());
    StringBuilder res = new StringBuilder();
    for (String str : strs) {
      res.append(str);
    }
    return res.toString();
  }

  public static void main(String[] args) {
    String[] strs1 = {"jibw", "ji", "jp", "bw", "jibw"};
    System.out.print("最小字典序字符串: ");
    System.out.println(lowString(strs1));

    String[] strs2 = {"ba", "b"};
    System.out.print("最小字典序字符串: ");
    System.out.println(lowString(strs2));
  }
}
