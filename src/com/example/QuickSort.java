package com.example;

/*
 * 插入排序
 * 不稳定
 * 平均时间复杂度：nlog2n
 * refer: images/QuickSort.png
 * */
public class QuickSort {
  public static void main(String[] args) {
    //    int[] arr = {1, 4, 6, 9, 10, 2, 3, 5, 8, 7, 0};
    //    int[] arr = {7, 3, 2, 8, 1, 9, 5, 4, 6, 10};
    //    int[] arr = {7, 3, 2, 6, 8, 1, 9, 5, 4, 6, 10, 6};
    //    int[] arr = {4, 6};
    int[] arr = {7, 3, 2, 8, 1, 9, 5, 4, 10, 6};

    sort(arr, 0, arr.length - 1);
    System.out.print("\n最终排序: ");
    print(arr);
  }

  static void sort(int[] arr, int left, int right) {
    if (left >= right) return;
    int mid = partition(arr, left, right);
    // 轴的左半部分
    sort(arr, left, mid - 1);
    // 轴的右半部分
    sort(arr, mid + 1, right);
  }

  static int partition(int[] arr, int left, int right) {
    int pivot = arr[right];
    int i = left;
    int j = right - 1;
    // i<=j 需要有等于号，否则当长度仅为2的时候 [4，6]会被排序成[6，4]
    while (i <= j) {
      // 需要满足 i<=j， 否则的话pivot如果最大或者最小就会出现数组越界或错误的交换
      // arr[i] <= pivot, 等于号保证小于等于轴的元素都在轴的左边
      while (i <= j && arr[i] <= pivot) i++;
      while (i <= j && arr[j] > pivot) j--;
      // 需要判断 i是否小于j
      //      System.out.println("before swap: left->" + left + ", right->" + right);
      if (i < j) swap(arr, i, j);
      //      System.out.println("===");
      //      print(arr);
    }
    // 交换使用的是i的下标
    swap(arr, i, right);

    // 返回新的轴的下标
    return i;
  }

  static void swap(int[] arr, int m, int n) {
    int tmp = arr[m];
    arr[m] = arr[n];
    arr[n] = tmp;
  }

  static void print(int[] arr) {
    for (int j : arr) {
      System.out.print(j + " ");
    }
  }
}
