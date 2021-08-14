package com.basic;

public class BSExist {
  public static void main(String[] args) {
    //
    int[] arr = {1, 2, 4, 6, 9, 11, 12, 14};
    int target = 12;
    int index = binarySearch(arr, target);
    System.out.println("target index: " + index);
  }

  static int binarySearch(int[] arr, int target) {
    int L, R, mid, index;
    L = 0;
    R = arr.length - 1;
    index = -1;

    while (L < R) {
      mid = L + ((R - L) >> 1);
      System.out.println("current mid: " + mid);
      if (arr[mid] == target) {
        index = mid;
      } else if (arr[mid] > target) {
        R = mid - 1;
      } else {
        L = mid + 1;
      }
    }
    return index;
  }
}
