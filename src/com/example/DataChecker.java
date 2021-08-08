package com.example;

import java.util.Arrays;
import java.util.Random;

/*
 * 校验其他算法是否正确
 */
public class DataChecker {
  static int[] generateRandomArray() {
    Random r = new Random();
    int[] arr = new int[1000];

    for (int i = 0; i < arr.length; i++) {
      arr[i] = r.nextInt();
    }
    return arr;
  }

  static void check() {
    int[] arr = generateRandomArray();
    int[] arr2 = new int[arr.length];

    System.arraycopy(arr, 0, arr2, 0, arr2.length);
    // 验证选择排序
    //    SelectionSort.sort(arr);
    // 验证冒泡排序
    //    BubbleSort.sort(arr);
    // 验证插入排序
    //    InsertSort.sort(arr);
    // 验证希尔排序
    //    ShellSort.sort(arr);

    // 验证归并排序
    MergeSort.sort(arr, 0, arr.length - 1);

    Arrays.sort(arr2);
    boolean same = true;
    for (int j = 0; j < arr.length; j++) {
      if (arr[j] != arr2[j]) {
        same = false;
        break;
      }
    }
    System.out.print("验证结果: ");
    System.out.println(same ? "success" : "fail");
  }

  public static void main(String[] args) {
    check();
    //    for (int i = 0; i < 3; i++) {
    //      check();
    //    }
  }
}
