package com.example;

import java.util.Arrays;

/*
 * 计数排序
 * 稳定
 * 平均时间复杂度：n
 * 空间复杂度：n
 * refer: images/CountSort.png
 * */
public class CountSort {
  public static void main(String[] args) {
    int[] arr = {2, 4, 2, 3, 7, 1, 1, 0, 0, 5, 6, 9, 8, 5, 7, 4, 0, 9};

    int[] result = sort(arr);
    System.out.print("\n最终排序: " + Arrays.toString(result));
  }

  static int[] sort(int[] arr) {
    int max = 10;
    int[] countArr = new int[max];
    for (int j : arr) {
      countArr[j]++;
    }
    System.out.println("元素装入桶中之后: " + Arrays.toString(countArr));

    int[] res = new int[arr.length];
    int k = 0;
    for (int i = 0; i < max; i++) {
      //      int tmp = countArr[i];
      //      while (tmp > 0) {
      //        res[k++] = i;
      //        tmp--;
      //      }
      while (countArr[i]-- > 0) {
        res[k++] = i;
      }
    }
    System.out.println("排序后: " + Arrays.toString(res));
    return res;
  }
}
