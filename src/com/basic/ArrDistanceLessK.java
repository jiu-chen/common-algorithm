package com.basic;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class ArrDistanceLessK {

  public static class Mycomp implements Comparator<Integer> {
    /*
     * 规定:
     * 如果返回负数，则前面数小
     * 如果返回正数，则前面数大
     */
    @Override
    public int compare(Integer o1, Integer o2) {
      return o2 - o1;
    }
  }
  /* *
   * 已知一个几乎有序的数组。几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离一定不超过k,
   * 并且k相对于数组长度来说是比较小的。
   * 请选择一个合适的排序策略，对这个数组进行排序
   *
   * 思路: 使用小根堆
   * 假设k=5, 那么元数组中只有0-5位置上的元素可能最小（移动到0位置），否则不满足题目要求
   * 所以搞一个小根堆，将前6个数放到小根堆，弹出的第一个数就是最小数。
   */
  public static void sortedArrDistanceLessK(int[] arr, int k) {
    // 默认小根堆
    PriorityQueue<Integer> queue = new PriorityQueue<>();
    int index = 0;
    for (; index <= Math.min(arr.length - 1, k); index++) {
      queue.add(arr[index]);
    }

    int i = 0;
    for (; index < arr.length; i++, index++) {
      arr[i] = queue.poll();
      queue.add(arr[index]);
    }

    while (!queue.isEmpty()) {
      arr[i++] = queue.poll();
    }
  }

  public static void main(String[] args) {
    // java语言中的PriorityQueue 默认是小根堆
    //    PriorityQueue<Integer> queue = new PriorityQueue<>();

    // 构造大根堆
    PriorityQueue<Integer> queue = new PriorityQueue<>(new Mycomp());
    queue.add(3);
    queue.add(4);
    queue.add(2);
    queue.add(1);
    queue.add(5);
    queue.add(7);
    queue.add(6);

    System.out.println("打印PriorityQueue: ");
    while (!queue.isEmpty()) {
      System.out.print(queue.poll() + " "); // 先进先出 小根堆（默认）  1 2 3 4 5 6 7
      //         大根堆，自定义比较器 7 6 5 4 3 2 1
    }
    System.out.println("\n========");

    int[] arr = {3, 4, 2, 1, 5, 7, 6};
    sortedArrDistanceLessK(arr, 4);
    System.out.println("arr: " + Arrays.toString(arr));
  }
}
