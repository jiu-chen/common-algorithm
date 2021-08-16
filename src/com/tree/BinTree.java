package com.tree;

import java.util.Stack;

public class BinTree {
  public static class Node {
    public int value;
    public Node left = null;
    public Node right = null;

    public Node(int v) {
      value = v;
    }
  }

  private static Node createBinaryTree(int[] arr, int index) {
    Node treeNode = null;
    if (index < arr.length) {
      treeNode = new Node(arr[index]);
      // 对于顺序存储的完全二叉树，如果某个节点的索引为index，其对应的左子树的索引为2*index+1，右子树为2*index+1
      treeNode.left = createBinaryTree(arr, 2 * index + 1);
      treeNode.right = createBinaryTree(arr, 2 * index + 2);
    }
    return treeNode;
  }

  // 递归序
  // 先序遍历 * 中序遍历 * 后序遍历
  public static void f(Node head) {
    if (head == null) {
      return;
    }
    // 先序遍历: 头左右
    //    System.out.print(head.value + " ");
    f(head.left);
    // 中序遍历: 左头右
    //    System.out.print(head.value + " ");
    f(head.right);
    // 后序遍历: 左右头
    System.out.print(head.value + " ");
  }

  // 非递归方法 遍历
  // 先序遍历: 根左右
  /* *
   * 思路: 压栈
   * 1. 弹出栈就打印
   * 2. 如果有右节点，压右节点
   * 3. 如果有左节点，压左节点
   */
  public static void preOrder(Node head) {
    if (head == null) {
      System.exit(1);
    }
    Stack<Node> stack = new Stack<>();
    stack.add(head);
    while (!stack.empty()) {
      head = stack.pop();
      System.out.print(head.value + " ");
      if (head.right != null) {
        stack.add(head.right);
      }
      if (head.left != null) {
        stack.add(head.left);
      }
    }
  }

  /* *
   *  后续遍历:
   *  由于先序遍历是: 根左右
   *  不妨构造一个 `根右左`的遍历，
   *  然后将其倒序输出，即是直线后续遍历: `左右根`
   */
  public static void postOrder(Node head) {
    if (head == null) {
      System.exit(1);
    }
    Stack<Node> stack = new Stack<>();
    Stack<Node> stack2 = new Stack<>();
    stack.add(head);
    while (!stack.empty()) {
      head = stack.pop();
      //      System.out.println(head.value + " ");  //根右左

      stack2.add(head);

      if (head.left != null) {
        stack.add(head.left);
      }
      if (head.right != null) {
        stack.add(head.right);
      }
    }
    while (!stack2.empty()) {
      System.out.print(stack2.pop().value + " ");
    }
  }

  /* *
   * 中序遍历: 左根右 (多看多想)
   * 1. 整条左边届一次入栈
   * 2. 如果1完成，弹出节点并打印，执行右子树
   *
   */
  public static void inOrder(Node head) {
    if (head == null) {
      System.exit(1);
    }

    Stack<Node> stack = new Stack<>();
    while (!stack.isEmpty() || head != null) {
      if (head != null) {
        stack.push(head);
        head = head.left;
      } else {
        head = stack.pop();
        System.out.print(head.value + " ");
        head = head.right;
      }
    }
    System.out.println();
  }

  public static void main(String[] args) {
    int[] arr = {1, 2, 3, 4, 5, 6, 7};
    Node rootNode = createBinaryTree(arr, 0);
    System.out.print("递归遍历: ");
    f(rootNode);
    System.out.println();
    System.out.print("非递归先序遍历: ");
    preOrder(rootNode);
    System.out.println();
    System.out.print("非递归后序遍历: ");
    postOrder(rootNode);
    System.out.println();
    System.out.print("非递归中序遍历: ");
    inOrder(rootNode);
  }
}
