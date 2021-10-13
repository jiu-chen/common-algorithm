package com.leetcode;

import java.util.Arrays;

public class CountOneNums {

  public int[] countBits(int n) {
    int[] arr = new int[n + 1];
    for (int i = 0; i <= n; i++) {
      arr[i] = countOneNums(i);
    }
    return arr;
  }

  private int countOneNums(int n) {
    int cnt = 0;
    while (n != 0) {
      if ((n & 1) != 0) {
        cnt++;
      }
      n >>= 1;
    }
    return cnt;
  }

  public static void main(String[] args) {
    CountOneNums c = new CountOneNums();
    System.out.println("result: " + Arrays.toString(c.countBits(5)));
  }
}
