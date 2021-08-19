package com.tree;

import java.util.LinkedList;
import java.util.Queue;
/* *
 * 序列化就是将二叉树以数组的方式存放起来
 * 思路:
 * 类似与先序遍历
 */
public class TreeSerialization {

  // 先序方式序列化
  public static Queue<String> preSerial(BinTree.Node head) {
    Queue<String> ans = new LinkedList<>();
    pres(head, ans);
    return ans;
  }

  private static void pres(BinTree.Node head, Queue<String> ans) {
    if (head == null) ans.add(null);
    else {
      ans.add(String.valueOf(head.value));
      pres(head.left, ans);
      pres(head.right, ans);
    }
  }

  public static void main(String[] args) {
    int[] arr = {1, 2, 3, 4, 5, 6, 7};
    BinTree.Node rootNode = BinTree.createBinaryTree(arr, 0);
    System.out.print("先序方式序列化: ");
    Queue<String> ans = preSerial(rootNode);
    while (!ans.isEmpty()) {
      System.out.print(ans.poll() + " ");
    }
    System.out.println();
  }
}
