package com.tree;

import java.util.LinkedList;

/*
 * 完全二叉树
 * 定义:
 * 设二叉树的深度为h，除第 h 层外，其它各层 (1～h-1) 的结点数都达到最大个数， 第 h 层所有的结点都连续集中在最左边
 * 第 h 层所有的结点都连续集中在最左边
 */
public class CcompleteBinTree {
  /*
   * 判断一颗二叉树是否是完全二叉树
   * 方法: 利用层序遍历（宽度优先遍历）
   * 1） 任何节点，如果有右孩子，没有左孩子 则不是完全二叉树，返回false
   * 2） 一旦遇到左右孩子不双全，后续的所有节点都必须是叶子节点
   *
   * 满足以上任意一个条件，则不是完全二叉树
   */
  public static boolean isCBT(Node head) {
    if (head == null) return true;
    LinkedList<Node> queue = new LinkedList<>();
    queue.add(head);
    boolean leaf = false;
    Node l = null;
    Node r = null;

    while (!queue.isEmpty()) {
      head = queue.poll();
      l = head.left;
      r = head.right;
      if ((l == null && r != null) || (leaf && (l != null || r != null))) {
        // 遇到了不双全的节点之后，又发现当前节点不是叶子节点
        return false;
      }
      if (l != null) {
        queue.add(l);
      }
      if (r != null) {
        queue.add(r);
      }

      // 遇到了叶子节点: 左右孩子不双全
      if (l == null || r == null) {
        leaf = true;
      }
    }
    return true;
  }

  private static Node createCBTree() {
    Node root = new Node(5);
    root.left = new Node(3);
    root.right = new Node(8);
    //    root.left.left = new Node(1);
    root.left.right = new Node(4);
    return root;
  }

  public static void main(String[] args) {
    int[] arr = {1, 2, 3, 4, 5, 6, 7};
    Node rootNode = BinTree.createBinaryTree(arr, 0);
    System.out.println("rootNode是否是搜索二叉树checkBST1: " + isCBT(rootNode));

    Node root = createCBTree();
    System.out.println("root是否是搜索二叉树checkBST1: " + isCBT(root));
  }
}
