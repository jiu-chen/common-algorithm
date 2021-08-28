package com.tree;

import java.util.ArrayList;
import java.util.List;

/*
 * 搜索二叉树:
 * 定义: 搜索二叉树是一种特殊有序的二叉树，如果一棵树不为空，
 *      并且如果它的根节点左子树不为空, 那么它左子树上面的所有节点的值都小于它的根节点的值，
 *      如果它的右子树不为空，那么它右子树任意节点的值都大于他的根节点的值;
 *      它的左右子树也是二叉搜索树。
 * 二叉树上任意一个节点
 *  1. 左节点都比当前节点小
 *  2. 右节点都比当前节点大
 */
public class SearchBT {

  /*
   * 判断一个二叉树是否是搜索二叉树
   * 思路:
   * 搜索二叉树有一个特点: 中序遍历一定是升序的
   */
  public static int preValue = Integer.MIN_VALUE;

  // 方法1: 递归中序遍历
  public static boolean checkBST1(Node head) {
    if (head == null) {
      return true;
    }
    boolean isLeftBst = checkBST1(head.left);
    if (!isLeftBst) {
      return false;
    }
    if (head.value <= preValue) {
      return false;
    } else {
      preValue = head.value;
    }
    return checkBST1(head.right);
  }

  // 方法2: 递归中序遍历
  public static boolean checkBST2(Node head) {
    int min = Integer.MIN_VALUE;
    List<Node> inOrderList = new ArrayList<>();
    process(head, inOrderList);
    for (Node node : inOrderList) {
      if (node.value <= min) {
        return false;
      } else {
        min = node.value;
      }
    }
    return true;
  }

  private static void process(Node head, List<Node> inOrderList) {
    if (head == null) {
      return;
    }
    process(head.left, inOrderList);
    inOrderList.add(head); // 将打印的步骤替换为添加到动态数组中
    process(head.right, inOrderList);
  }

  // 方法2: 非递归中序遍历
  public static boolean checkBST3(Node head) {
    return true;
  }

  private static Node createBSTree() {
    Node root = new Node(5);
    root.left = new Node(7);
    root.right = new Node(8);
    //    root.left.left = new Node(1);
    root.left.right = new Node(4);
    return root;
  }

  public static void main(String[] args) {
    int[] arr = {1, 2, 3, 4, 5, 6, 7};
    Node rootNode = BinTree.createBinaryTree(arr, 0);
    System.out.println("是否是搜索二叉树checkBST1: " + checkBST1(rootNode));
    System.out.println("是否是搜索二叉树checkBST2: " + checkBST2(rootNode));

    Node root = createBSTree();
    System.out.print("层序遍历: ");
    BinTree.level(root);
    System.out.println("是否是搜索二叉树checkBST1: " + checkBST1(root));
    System.out.println("是否是搜索二叉树checkBST2: " + checkBST2(root));
  }
}
