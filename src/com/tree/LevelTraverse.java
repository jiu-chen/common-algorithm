package com.tree;

import java.util.LinkedList;
import java.util.Queue;

/* *
 * 二叉树的层序遍历:
 * 弹出即打印，然后添加弹出节点的左右节点
 */
public class LevelTraverse {
  static void level(BinTree.Node head) {
    System.out.print("level traverse: ");
    Queue<BinTree.Node> queue = new LinkedList<>();
    queue.add(head);

    while (!queue.isEmpty()) {
      BinTree.Node cur = queue.poll();
      System.out.print(cur.value + " ");
      if (cur.left != null) {
        queue.add(cur.left);
      }
      if (cur.right != null) {
        queue.add(cur.right);
      }
    }
    System.out.println();
  }

  /* *
   * 求二叉树最大层宽度
   */

  public static int maxWidth(BinTree.Node head) {
    if (head == null) return 0;

    Queue<BinTree.Node> queue = new LinkedList<>();
    queue.add(head);
    BinTree.Node curEnd = head; // 标记当前层最右节点
    BinTree.Node nextEnd = null; // 标记下一层最右节点

    int max = 0; // 最大宽度
    int curLevelNodes = 0; // 当前层节点数

    while (!queue.isEmpty()) {
      BinTree.Node cur = queue.poll();
      if (cur.left != null) {
        queue.add(cur.left);
        nextEnd = cur.left;
      }
      if (cur.right != null) {
        queue.add(cur.right);
        nextEnd = cur.right;
      }
      curLevelNodes++;

      if (cur == curEnd) {
        max = Math.max(max, curLevelNodes);
        curLevelNodes = 0;
        curEnd = nextEnd;
      }
    }
    return max;
  }

  public static void main(String[] args) {

    int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

    // 多看构建方法， 递归的奥秘
    // index就是数组的下标
    BinTree.Node rootNode = BinTree.createBinaryTree(arr, 0);
    //                 1
    //          2            3
    //      4       5     6     7
    //     8 9    10 11 12
    level(rootNode);

    int maxWidth = maxWidth(rootNode);
    System.out.println("max width: " + maxWidth);
  }
}
