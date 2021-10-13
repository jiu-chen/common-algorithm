package com.basic;

/*
 * 局部最小定义
 * 数组中相邻元素不想等，且左侧和右侧两个元素均大于当前数，则该数是数组中的局部最小 可以使用二分法
 * */

public class PartMininum {
  public static void main(String[] args) {
    //    int[] arr = {5, 1, 4, 3, 5, 8, 6};
    //    int[] arr = {5, 1, 4, 3, 5, 8, 9};
    //    int[] arr = {3, 5, 6, 7, 5, 8, 9};
    int[] arr = {3, 5};
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

    // 规律一：如果nums[i] < nums[i+1]，则在i之前一定存在局部最小元素
    // 规律二：如果nums[i] > nums[i+1]，则在i+1之后一定存在局部最小元素
    // 不应该使用mid-1， 因为可能越界
    // 类似题目 leetcode寻找峰值元素: https://leetcode-cn.com/problems/find-peak-element/submissions/
    while (L < R) {
      mid = L + ((R - L) >> 1);
      if (arr[mid] < arr[mid + 1]) {
        R = mid;
      } else {
        L = mid + 1;
      }
    }
    return arr[L]; // ! essential
  }
}
