package com.basic;

/*
 * 局部最小定义
 * 数组中相邻元素不想等，且左侧和右侧两个元素均大于当前数，则该数是数组中的局部最小 可以使用二分法
 * */

public class PartMininum {
  public static void main(String[] args) {
    //    int[] arr = {5, 1, 4, 3, 5, 8, 6};
    //    int[] arr = {5, 1, 4, 3, 5, 8, 9};
    int[] arr = {3, 5, 6, 7, 5, 8, 9};
    int v = getPartMin(arr);
    System.out.println("第一个找到的局部最小值为: " + v);
  }

  /*
   * 二分查找: 找出数组中第一个局部最小值
   */
  static int getPartMin(int[] arr) {
    int L, R, mid;
    L = 0;
    R = arr.length - 1;

    while (L < R) {
      mid = L + ((R - L) >> 1);
      if (arr[mid - 1] < arr[mid]) {
        R = mid - 1;
      } else if (arr[mid + 1] < arr[mid]) {
        L = mid + 1;
      } else {
        return arr[mid];
      }
    }
    return arr[L]; // ! essential
  }
}
