package com.sorts;

/*
 * 归并排序
 * 稳定
 * 平均时间复杂度: nlog2n
 * refer: images/MergeSort.png
 * 准备三个指针（这里用下标就可以），i, j, k,
 * i是数组第一个元素下标，j指向数组后半部分的第一个元素下标，k是新数组的第一个元素下标
 *
 * Java & Python中对于对象的排序，就是使用归并排序
 * */
public class MergeSort {
  public static void main(String[] args) {
    int[] arr = {5, 3, 6, 8, 1, 7, 9, 4, 2};

    // 尝试将一个左右分别有序的数组排序
    //    int[] arrSorted = {1, 3, 6, 8, 2, 7, 9, 11};

    sort(arr, 0, arr.length - 1);
    System.out.print("\n最终排序: ");
    print(arr);
  }

  static void sort(int[] arr, int left, int right) {
    //    merge(arr, 0, 4, arr.length - 1);
    // 迭代
    if (left == right) return;
    int mid = left + (right - left) / 2;
    // 左边数组
    sort(arr, left, mid);
    // 右边数组
    sort(arr, mid + 1, right);

    // 合并数组
    merge(arr, left, mid + 1, right);
  }

  //  mid = left + (right - left) / 2;
  // 1    3    5    7,    2     4    6
  // left                 mid        right
  // 1    3    5    7,    2     4    6    8
  // left                 mid             right
  static void merge(int[] arr, int left, int mid, int right) {
    int i, j, k;
    int[] temp = new int[right - left + 1];
    i = left;
    j = mid;
    k = 0;

    while (i <= mid - 1 && j <= right) {
      if (arr[i] <= arr[j]) {
        temp[k] = arr[i];
        i++;
        k++;
        //        temp[k++]=arr[i++];
      } else {
        temp[k++] = arr[j++];
      }
    }

    while (i <= mid - 1) temp[k++] = arr[i++];
    while (j <= right) temp[k++] = arr[j++];
    //    print(temp);

    //    for (int m = 0; m < temp.length; m++) {
    //      arr[left + m] = temp[m];
    //    }
    System.arraycopy(temp, 0, arr, left, temp.length);
  }

  static void print(int[] arr) {
    for (int j : arr) {
      System.out.print(j + " ");
    }
  }
}
