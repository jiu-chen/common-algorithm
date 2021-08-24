package com.strings;

import java.util.Arrays;

/*
 * KMP算法
 * refer: https://zhuanlan.zhihu.com/p/155942820
 */
public class Kmp {

  /** 计算部分匹配表 */
  public static int[] kmpnext(String dest) {
    int[] next = new int[dest.length()];
    next[0] = 0;

    for (int i = 1, j = 0; i < dest.length(); i++) {
      while (j > 0 && dest.charAt(j) != dest.charAt(i)) {
        j = next[j - 1];
      }
      if (dest.charAt(i) == dest.charAt(j)) {
        j++;
      }
      next[i] = j;
    }
    return next;
  }

  /** KMP 匹配 */
  public static int kmp(String str, String dest) {
    // 1.首先计算出 部分匹配表
    int[] next = kmpnext(dest);

    System.out.println("next =" + Arrays.toString(next));
    // 2.查找匹配位置
    for (int i = 0, j = 0; i < str.length(); i++) {
      while (j > 0 && str.charAt(i) != dest.charAt(j)) {
        j = next[j - 1];
      }
      if (str.charAt(i) == dest.charAt(j)) {
        j++;
      }
      if (j == dest.length()) {
        return i - j + 1;
      }
    }
    return -1;
  }

  /*
   * 判断str1和str2是否互为旋转词: str1可以通过str2旋转获得，str2也可以通过str1旋转获得
   * str1 = "abcd"
   * str2 = "cdab"
   * str1与str2互为旋转词
   */
  public static boolean isSpin(String str1, String str2) {
    String tmp = str1 + str1;
    int index = kmp(tmp, str2);
    return index != -1;
  }

  public static void main(String[] args) {
    String a = "ABACABAD";
    String b = "BBC ABACABACABAD ABCDABDE";
    int result = kmp(b, a);

    // 打印结果：和字符串获得匹配的位置
    System.out.println("result position: " + result);

    String str1 = "abcdefg";
    //    String str2 = "defgabc";
    String str2 = "defgacc";
    System.out.println("str1和str互为旋转词: " + isSpin(str1, str2));
  }
}
