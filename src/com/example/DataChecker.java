package com.example;

import java.util.Arrays;
import java.util.Random;

/*
 * 校验其他算法是否正确
 */
public class DataChecker {
  static int[] generateRandomArray() {
    Random r = new Random();
    int[] arr = new int[100];

    for (int i = 0; i < arr.length; i++) {
      //      arr[i] = r.nextInt();

      // for CountSort()
      arr[i] = r.nextInt(10);
    }
    return arr;
  }

  static void check() {
    int[] arr = generateRandomArray();
    System.out.println("随机生成数组: " + Arrays.toString(arr));
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
    //    MergeSort.sort(arr, 0, arr.length - 1);

    // 验证快速排序
    //    QuickSort.sort(arr, 0, arr.length - 1);

    //    Arrays.sort(arr2);
    //    boolean same = true;
    //    for (int j = 0; j < arr.length; j++) {
    //      if (arr[j] != arr2[j]) {
    //        same = false;
    //        break;
    //      }
    //    }

    // 计数排序
    int[] resArr = CountSort.sort(arr);
    Arrays.sort(arr2);
    boolean same = true;
    for (int j = 0; j < resArr.length; j++) {
      if (resArr[j] != arr2[j]) {
        same = false;
        break;
      }
    }

    String ret = same ? "success" : "fail";
    System.out.println("验证结果: " + ret);
  }

  public static void main(String[] args) {
    check();
    //    for (int i = 0; i < 3; i++) {
    //      check();
    //    }
  }
}
