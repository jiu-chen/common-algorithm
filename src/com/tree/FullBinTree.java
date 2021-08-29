package com.tree;

/*
 * 定义:
 *     一个二叉树，如果每一个层的结点数都达到最大值，则这个二叉树就是满二叉树。
 *     也就是说，如果一个二叉树的层数为K，且结点总数是(2^k) -1 ，则它就是满二叉树。
 */
public class FullBinTree {
  // 此类题目都可以通过树形DP思路去解答
  public static class Info {
    public int height;
    public int nodes;

    public Info(int h, int n) {
      height = h;
      nodes = n;
    }
  }

  public static boolean isF(Node X) {
    if (X == null) {
      return true;
    }
    Info data = f(X);

    // 2^height-1 == nodes
    System.out.println(" nodes: " + data.nodes + ", height: " + data.height);
    return data.nodes == ((1 << data.height) - 1);
  }

  private static Info f(Node x) {
    if (x == null) { // base, 表示递归的终结情况
      return new Info(0, 0);
    }
    Info leftData = f(x.left);
    Info rightData = f(x.right);
    int height = Math.max(leftData.height, rightData.height) + 1;
    int nodes = leftData.nodes + rightData.nodes + 1;
    return new Info(height, nodes);
  }

  public static void main(String[] args) {

    int[] arr = {1, 2, 3, 4, 5, 6, 7};
    Node rootNode = BinTree.createBinaryTree(arr, 0);
    System.out.print("层序遍历: ");
    BinTree.level(rootNode);

    System.out.println("是否是满二叉树: " + isF(rootNode));

    int[] arr2 = {1, 2, 3, 4, 5, 6};
    Node root = BinTree.createBinaryTree(arr2, 0);
    System.out.print("层序遍历: ");
    BinTree.level(root);
    System.out.println("是否是满二叉树: " + isF(root));
  }
}
