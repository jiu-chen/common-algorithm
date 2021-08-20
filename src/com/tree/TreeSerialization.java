package com.tree;

import java.util.LinkedList;
import java.util.Queue;
/* *
 * 序列化就是将二叉树以数组的方式存放起来
 * 思路:
 * 类似与先序遍历
 */
public class TreeSerialization {

  // 先序方式序列化: 将二叉树存储为队列
  public static Queue<String> preSerial(BinTree.Node head) {
    Queue<String> ans = new LinkedList<>();
    pres(head, ans);
    return ans;
  }

  public static void pres(BinTree.Node head, Queue<String> ans) {
    if (head == null) {
      ans.add(null);
    } else {
      ans.add(String.valueOf(head.value));
      pres(head.left, ans);
      pres(head.right, ans);
    }
  }

  // 二叉树反序列化: 将队列转换为二叉树
  public static BinTree.Node buildByPreQueue(Queue<String> prelist) {
    if (prelist == null || prelist.size() == 0) {
      return null;
    }
    return preb(prelist);
  }

  public static BinTree.Node preb(Queue<String> prelist) {
    String value = prelist.poll();
    if (value == null) {
      return null;
    }
    BinTree.Node head = new BinTree.Node(Integer.parseInt(value));
    head.left = preb(prelist);
    head.right = preb(prelist);

    return head;
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

    Queue<String> queue = new LinkedList<>();
    queue.add(String.valueOf(1));
    queue.add(String.valueOf(2));
    queue.add(null);
    queue.add(String.valueOf(3));
    queue.add(null);
    queue.add(null);
    queue.add(null);
    BinTree.Node root = buildByPreQueue(queue);

    LevelTraverse.level(root);
  }
}
