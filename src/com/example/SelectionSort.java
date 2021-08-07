package com.example;

/*
 * 选择排序
 * 不稳定
 * 平均时间复杂度：n^2
 * */
public class SelectionSort {
  public static void main(String[] args) {
    int[] arr = {5, 3, 6, 8, 1, 7, 9, 4, 2};

    sort(arr);
    System.out.print("\n最终排序数组: ");
    print(arr);
  }

  static void sort(int[] arr) {
    // 第一次循环: 1 3 6 8 5 7 9 4 2
    //    int minPos = 0;
    //    for (int j = 1; j < arr.length; j++) {
    //      if (arr[j] < arr[minPos]) minPos = j;
    //    }
    //    System.out.println("minPos: " + minPos);
    //    swap(arr, 0, minPos);

    // 结合外循环
    for (int i = 0; i < arr.length - 1; i++) {
      int minPos = i;
      for (int j = i + 1; j < arr.length; j++) {
        minPos = arr[j] < arr[minPos] ? j : minPos;
      }
      //      System.out.println("minPos: " + minPos);

      swap(arr, i, minPos);

      //      System.out.print("\n经过第" + i + "次循环之后，数组内容是: ");
      //      print(arr);
    }
  }

  static void swap(int[] arr, int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }

  static void print(int[] arr) {
    for (int j : arr) {
      System.out.print(j + " ");
    }
  }
}
