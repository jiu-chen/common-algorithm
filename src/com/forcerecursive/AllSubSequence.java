package com.forcerecursive;

import java.util.Arrays;

/*
 * 打印一个字符串的全部子序列，包括空字符串
 */
public class AllSubSequence {
  public static void print(String str) {
    process(str.toCharArray(), 0);
  }

  private static void process(char[] str, int i) {
    if (i == str.length) {
      System.out.println(String.valueOf(str));
      //      System.out.println(Arrays.toString(str));
    } else {
      process(str, i + 1); // 要当前字符
      char tmp = str[i];
      str[i] = 0;
      process(str, i + 1); // str[i]被置为0，导致数组str在此终止，所以含义是不要当前字符
      str[i] = tmp;
    }
  }

  public static void main(String[] args) {
    String str = "abc";
    print(str);

    System.out.println("===========================");
    // test char a=0;
    char a = 0; // 0表示一个空白字符，但不同于空格
    char b = '\0';
    char[] arr = {'a', 'b', 'c'};
    arr[0] = 0;
    System.out.println(a);
    System.out.println(b);
    System.out.println("a==b: " + (a == b));
    System.out.println(Arrays.toString(arr));
    String st = "\0abc";
    System.out.println("st: " + st + ", length: " + st.length()); // 4
  }
}
