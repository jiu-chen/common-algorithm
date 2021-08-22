package com.basic;

import java.util.Arrays;

public class MaxHeap {
  private int[] heap;
  private final int limit;
  private int heapSize;

  public MaxHeap(int limit) {
    heap = new int[limit];
    this.limit = limit;
    heapSize = 0;
  }

  public void printHeap() {
    System.out.print("print heap: ");
    for (int i = 0; i < heapSize; i++) {
      System.out.print(heap[i] + " ");
    }
  }

  public void push(int value) {
    if (heapSize == limit) {
      throw new RuntimeException("heap is full");
    }
    heap[heapSize] = value;
    heapInsert(heap, heapSize++);
  }

  /*
   * 构造大根堆:
   * 给定一个下标，将该下标上的元素插入到数组中，并构成大根堆
   * 不断地对比给定下标的元素值与起父节点的大小关系（将数组想象成一个完全二叉树）
   * 如果大于其父节点，则与父节点调换
   * 直到: 1. arr[index] 不比 arr[index]大
   */
  private static void heapInsert(int[] arr, int index) {
    while (arr[index] > arr[(index - 1) / 2]) {
      swap(arr, index, (index - 1) / 2);
      index = (index - 1) / 2;
    }
  }

  private static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  /*
   * 返回最大值，并且在大根堆中，把最大值删掉，剩下的数，依然保持大根堆组织
   */
  public int pop() {
    int max = heap[0];
    swap(heap, 0, --heapSize);
    heapify(heap, 0, heapSize);
    return max;
  }

  /*
   * 堆化:
   * 给定一个数组，以及数组的某个位置和数组的长度
   * 将该数组从index位置之后的元素改造成一个大根堆
   *
   * 思路:
   * 从index位置不断地往下对比，
   * 直到 我的孩子都不在比我大或已经没有孩子了的时候 停止
   */
  private static void heapify(int[] arr, int index, int heapSize) {
    int left = 2 * index + 1;
    while (left < heapSize) {
      int right = left + 1;
      // 从左右孩子中找出较大的节点
      // 右: 1） 有右孩子 2）右孩子的值比左孩子大， 同时满足右孩子的数值大
      // 否则: 左孩子的数值大
      int largest = (heapSize > right && arr[right] > arr[left]) ? right : left;

      // 再将该节点与index的值相比
      largest = arr[largest] > arr[index] ? largest : index;
      if (largest == index) {
        break;
      }

      swap(arr, largest, index);
      index = largest;
      left = 2 * index + 1;
    }
  }

  /*
   * 堆排序：先将数组搞成大根堆
   * 然后将第一个数（最大数）与最后一个位置调用，此时最大数确定了，剩下的数组重新构造大根堆
   * 依次循环
   */
  public static void heapSort(int[] arr) {
    if (arr == null || arr.length <= 1) {
      return;
    }
    // O(N*logN)
    // 构造大根堆
    //    for (int i = 0; i < arr.length; i++) { // O(N)
    //      heapInsert(arr, i); // O(logN)
    //    }

    // 构造大根堆
    // O(N)
    for (int i = arr.length - 1; i >= 0; i--) {
      heapify(arr, i, arr.length);
    }

    int heapSize = arr.length;
    swap(arr, 0, --heapSize);

    // O(N*logN)
    while (heapSize > 0) { // O(N)
      heapify(arr, 0, heapSize); // O(logN)
      swap(arr, 0, --heapSize); // O(1)
    }
  }

  public static void main(String[] args) {
    int[] arr = {5, 3, 7, 6, 2, 4, 1};
    MaxHeap h = new MaxHeap(arr.length);

    for (int i = 0; i < arr.length; i++) {
      h.push(arr[i]);
    }
    h.printHeap();

    // pop
    int max = h.pop();
    System.out.println();
    System.out.println("pop value: " + max);
    h.printHeap();

    System.out.print("\nheap sort: ");
    heapSort(arr);
    System.out.println(Arrays.toString(arr));
  }
}
