package com.tree;

/*
 * 平衡二叉树
 * 定义
 *    abs(左子树高度-右子树高度) < 2
 *  判断一颗二叉树是否是平衡二叉树 用 树形DP 思路
 */
public class BalanceBinTree {
  public static class Info {
    public int height;
    public boolean isBalance;

    public Info(int h, boolean b) {
      height = h;
      isBalance = b;
    }
  }

  public static boolean isBalance(Node head) {
    return process(head).isBalance;
  }

  private static Info process(Node head) {
    if (head == null) {
      return new Info(0, true);
    }

    Info leftData = process(head.left);
    Info rightData = process(head.right);

    int height = Math.max(leftData.height, rightData.height) + 1;
    boolean isBalance =
        leftData.isBalance
            && rightData.isBalance
            && (Math.abs(leftData.height - rightData.height) < 2);

    return new Info(height, isBalance);
  }

  private static Node createBalanceTree() {
    Node head = new Node(1);
    head.left = new Node(3);
    head.left.right = new Node(5);
    return head;
  }

  public static void main(String[] args) {
    int[] arr = {1, 2, 3, 4, 5, 6};
    Node rootNode = BinTree.createBinaryTree(arr, 0);
    System.out.print("层序遍历: ");
    BinTree.level(rootNode);
    System.out.println("是否是平衡二叉树: " + isBalance(rootNode));

    Node root = createBalanceTree();
    System.out.print("层序遍历: ");
    BinTree.level(root);
    System.out.println("是否是平衡二叉树: " + isBalance(root));
  }
}
