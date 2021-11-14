package com.sorts;

/*
 * 希尔排序: 改进的插入排序
 * 希尔排序的目的是让数列变得大概有序
 * 间隔选择: Knuth序列, 间隔h
 *          h=3h+1, h=1,4,13...
 * 在间隔大的时候，元素移动的次数少，在间隔小的时候，元素移动的距离短
 * 间隔中依然使用插入排序
 * 不稳定: 两个相同的元素排序前后的相对位置可能发生变化
 * 平均时间复杂度：n^(1.3)
 * refer: images/ShellSort1.png
 * refer: images/ShellSort2.png
 * */
public class ShellSort {
  public static void main(String[] args) {
    int[] arr = {9, 6, 11, 3, 5, 12, 8, 7, 10, 15, 14, 4, 1, 13, 2};

    sort(arr);
    System.out.print("\n最终排序: ");
    print(arr);
  }

  static void sort(int[] arr) {
    long startTime = System.currentTimeMillis(); // 获取开始时间

    // 插入排序
    // gap=1
    //    for (int i = 1; i < arr.length; i++) {
    //      for (int j = i; j > 0; j--) {
    //        if (arr[j - 1] > arr[j]) {
    //          swap(arr, j - 1, j);
    //        }
    //      }
    //    }
    // todo 将1用gap代替
    //    int gap = 1;
    //    for (int i = gap; i < arr.length; i++) {
    //      for (int j = i; j > gap - 1; j -= gap) {
    //        if (arr[j - gap] > arr[j]) {
    //          swap(arr, j - gap, j);
    //        }
    //      }
    //    }

    // 取gap=4的时得到的结果: 1 6 2 3 5 12 8 4 9 13 11 7 10 15 14
    //     Knuth序列
    int h = 1;
    while (h < arr.length / 3) {
      h = 3 * h + 1;
    }
    int gap = h;
    System.out.println("gap: " + gap);
    while (gap > 0) {
      for (int i = gap; i < arr.length; i++) {
        for (int j = i; j > gap - 1; j -= gap) {
          if (arr[j] < arr[j - gap]) swap(arr, j, j - gap);
        }
      }
      //      gap >>= 1;
      gap = (gap - 1) / 3;
    }

    // 缩短gap: gap/=2 <=> gap >>= 1
    // Knuth序列
    //    int h = 1;
    //    while (h <= arr.length / 3) {
    //      h = 3 * h + 1;
    //    }
    //    for (int gap = h; gap > 0; gap = (gap - 1) / 3) {
    //      for (int i = gap; i < arr.length; i++) {
    //        for (int j = i; j > gap - 1; j -= gap) {
    //          if (arr[j] < arr[j - gap]) swap(arr, j, j - gap);
    //        }
    //      }
    //    }

    long endTime = System.currentTimeMillis(); // 获取结束时间
    System.out.println("程序运行时间： " + (endTime - startTime) + "ms");
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
