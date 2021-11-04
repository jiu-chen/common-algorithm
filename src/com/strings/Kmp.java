package com.strings;

import java.util.Arrays;

/*
 * KMP算法
 * refer: https://zhuanlan.zhihu.com/p/155942820
 */
public class Kmp {

  /** KMP 匹配 */
  public static int kmp(String s, String m) {
    if (s == null || m == null || m.length() < 1 || s.length() <= m.length()) {
      return -1;
    }
    char[] str1 = s.toCharArray();
    char[] str2 = m.toCharArray();
    int i1 = 0;
    int i2 = 0;

    // 1.计算 next 数组
    int[] next = getNextArray(str2);
    System.out.println("next = " + Arrays.toString(next));

    // 2.查找匹配位置
    while (i1 < str1.length && i2 < str2.length) {
      if (str1[i1] == str2[i2]) {
        i1++;
        i2++;
      } else if (next[i2] == -1) { // 等价于if(i1==0), 因为我们规定str2[0]=-1
        i1++;
      } else {
        i2 = next[i2];
      }
    }
    // i2 越界或者i2越界了
    return i2 == str2.length ? i1 - i2 : -1;
  }

  private static int[] getNextArray(char[] ms) {
    if (ms.length == 1) {
      return new int[] {-1};
    }
    int[] next = new int[ms.length];
    next[0] = -1;
    next[1] = 0;

    int i = 2; // Next数组的位置
    int cn = 0; // cn: 与i-1上的字符进行比对的字符的下标
    while (i < next.length) {
      if (ms[i - 1] == ms[cn]) {
        next[i++] = ++cn;
      } else if (cn > 0) { // 当前跳到cn位置的字符，和i-1位置的字符配不上
        cn = next[cn];
      } else {
        next[i++] = 0;
      }
    }
    return next;
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
    //    return index != -1;  // error
    //    return str1.length() == str2.length() && tmp.contains(str2);
    // or
    return str1.length() == str2.length() && index != -1;
  }

  public static void main(String[] args) {
    String a = "BBC ABACABADAD ABCDABDE";
    String b = "ABACABAD";
    int result = kmp(a, b);

    // 打印结果：和字符串获得匹配的位置
    System.out.println("result position: " + result);

    String str1 = "abcdefg";
    String str2 = "defgabc";
    String str3 = "def";
    System.out.println("str1和str2互为旋转词: " + isSpin(str1, str2));
    System.out.println("str1和str3互为旋转词: " + isSpin(str1, str3));
  }
}
