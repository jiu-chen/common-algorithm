package com.forcerecursive;

import java.util.ArrayList;
import java.util.HashSet;

public class AllArrangement {
  public static ArrayList<String> arrange(String s) {
    char[] chs = s.toCharArray();
    ArrayList<String> res = new ArrayList<>();
    process(chs, 0, res);
    return res;
  }

  public static HashSet<String> arrangeNoDuplicated(String s) {
    char[] chs = s.toCharArray();
    HashSet<String> res = new HashSet<>();
    processNoDuplicated(chs, 0, res);
    return res;
  }

  /*
   * 打印一个字符串的全部排列
   */
  // chs[i..]范围上所有的字符，都可以在i位置上，后续都要尝试
  // str[0..i-1]范围上，是之前的选择
  // 把所有的字符形成的全排列，加入到res里面去
  private static void process(char[] chs, int i, ArrayList<String> res) {
    if (i == chs.length) {
      res.add(String.valueOf(chs)); // base case
    } else {
      for (int j = i; j < chs.length; ++j) {
        swap(chs, i, j);
        process(chs, i + 1, res);
        swap(chs, i, j);
      }
    }
  }

  /*
   * 打印一个字符串的全部排列
   * 要求不出现重复字符，可以用Set集合去存储
   */
  private static void processNoDuplicated(char[] chs, int i, HashSet<String> res) {
    if (i == chs.length) {
      res.add(String.valueOf(chs)); // base case
    } else {
      for (int j = i; j < chs.length; ++j) {
        swap(chs, i, j);
        processNoDuplicated(chs, i + 1, res);
        swap(chs, i, j);
      }
    }
  }

  private static void swap(char[] chs, int i, int j) {
    char tmp = chs[i];
    chs[i] = chs[j];
    chs[j] = tmp;
  }

  public static void main(String[] args) {
    String s1 = "abc";
    ArrayList<String> res = arrange(s1);
    System.out.println("s1所有的排列结果: " + res);
    String s2 = "aacc";
    HashSet<String> res2 = arrangeNoDuplicated(s2);
    System.out.println("s2所有的排列结果: " + res2);
  }
}
