package com.greedy;

import java.util.PriorityQueue;

/*
 * 题目:
 *  一块金条切成两半，是需要花费和长度数值一样的铜板的。比如长度为20的金条，不管切成长度多大的两半，都需要花费20个铜板
 * 一群人想正分一块金条，怎么分最省铜板？
 * 例如，给定数组{10,20,30}, 代表一共三个人，整块金条长度为10+20+30=60
 * 金条要分成10，20，30三个部分。如果先把长度60的金条分成10和50，花费60；
 * 再把长度为50的金条分成20和30，花费50；一共花费110铜板
 * 但是如果先把长度60的金条分成30，30，花费60，再把长度为30的金条分成10和20，花费30。一共花费90铜板
 *
 * 输入一个数组，返回分割的最小代价
 * // 哈夫曼编码问题
 * 使用小根堆，每次弹出两个数字，相加之后再次扔到小根堆中
 */
public class LessMoneySplitGold {
  public static int lowestPrice(int[] arr) {
    PriorityQueue<Integer> queue = new PriorityQueue<>();
    int sum = 0, cur = 0;
    for (int a : arr) {
      queue.add(a);
    }
    while (queue.size() > 1) {
      cur = queue.poll() + queue.poll();
      sum += cur;
      queue.add(cur);
    }
    return sum;
  }

  public static void main(String[] args) {
    int[] arr = {10, 30, 20};
    System.out.println("最小代价: " + lowestPrice(arr)); // 90
    int[] arr2 = {10, 30, 20, 50};
    System.out.println("最小代价: " + lowestPrice(arr2)); // 200
  }
}
